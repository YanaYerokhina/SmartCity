import java.util.Map;
import java.awt.Point;



// Implements emergency vehicle behavior (Ambulance)
class Ambulance extends MovingEntity {
 private int hospitalX, hospitalY;
 private int goalX, goalY;
 private boolean goingToGoal = true;
 private State nextState;
 private StateVisualizer stateVisualizer;

 public Ambulance(int hospitalX, int hospitalY, int goalX, int goalY, 
                 Map<Point, TrafficLight> trafficLightMap, StateVisualizer stateVisualizer) {
     super(hospitalX, hospitalY, trafficLightMap);
     this.hospitalX = hospitalX;
     this.hospitalY = hospitalY;
     this.goalX = goalX;
     this.goalY = goalY;
     this.stateVisualizer = stateVisualizer;
     findPath();
 }

 @Override
 public void findPath() {
     int destX = goingToGoal ? goalX : hospitalX;
     int destY = goingToGoal ? goalY : hospitalY;

     State startState = new State(currentState.x, currentState.y, 0);
     pathStates = pathFinder.findPath(startState, destX, destY, Grid.grid);

     if (!pathStates.isEmpty()) {
         pathStates.removeFirst();
     }

     nextState = pathStates.isEmpty() ? null : pathStates.peek();
 }

 @Override
 public void move() {
     forceGreenNearbyLights();

     if (nextState != null) {
         pathStates.poll();
         Grid.grid[currentState.x][currentState.y] = 0;
         Grid.grid[nextState.x][nextState.y] = 2;

         currentState = nextState;
         nextState = pathStates.isEmpty() ? null : pathStates.peek();
     }

     if (pathStates.isEmpty()) {
         if (goingToGoal && currentState.x == goalX && currentState.y == goalY) {
             goingToGoal = false;
             findPath();
         } else if (!goingToGoal && currentState.x == hospitalX && currentState.y == hospitalY) {
             goingToGoal = true;
             stateVisualizer.selectNextTarget();
             this.goalX = stateVisualizer.getGoalX();
             this.goalY = stateVisualizer.getGoalY();
             findPath();
         }
     }
 }

 private void forceGreenNearbyLights() {
     double ambulanceX = currentState.y + 0.5;
     double ambulanceY = currentState.x + 0.5;
     double radius = Math.sqrt(16.0);

     Point closestLight = null;
     double closestDistance = Double.MAX_VALUE;

     for (Point lightPosition : trafficLightMap.keySet()) {
         double lightX = lightPosition.x + 0.5;
         double lightY = lightPosition.y + 0.5;

         double dx = ambulanceX - lightX;
         double dy = ambulanceY - lightY;
         double distance = Math.sqrt(dx * dx + dy * dy);

         if (distance <= radius &&
             (Math.abs(ambulanceX - lightX) < 1.5 || Math.abs(ambulanceY - lightY) < 1.5) &&
             distance < closestDistance) {
             closestDistance = distance;
             closestLight = lightPosition;
         }
     }

     if (closestLight != null) {
         TrafficLight light = trafficLightMap.get(closestLight);
         if (light != null) {
             light.forceGreenForDuration(1000);
         }
     }
 }

 @Override
 public int getGridValue() {
     return 2; // Ambulance value
 }
}