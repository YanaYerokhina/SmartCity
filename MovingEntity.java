import java.util.*;
import java.awt.*;


// Abstract base class for moving entities
abstract class MovingEntity implements GridEntity {
    protected State currentState;
    protected LinkedList<State> pathStates;
    protected PathFinder pathFinder;
    protected Map<Point, TrafficLight> trafficLightMap;
    
    public MovingEntity(int startX, int startY, Map<Point, TrafficLight> trafficLightMap) {
        this.currentState = new State(startX, startY, 0);
        this.pathFinder = new PathFinder();
        this.trafficLightMap = trafficLightMap;
        this.pathStates = new LinkedList<>();
    }
    
    abstract void move();
    abstract void findPath();
    
    public int getX() { return currentState.x; }
    public int getY() { return currentState.y; }
    
    @Override
    public Point getPosition() {
        return new Point(currentState.x, currentState.y);
    }
}