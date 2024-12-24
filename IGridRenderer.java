import java.util.*;
import java.awt.*;


// Simple interface that tells program how to render the grid
interface IGridRenderer {
 void render(Graphics g, int[][] grid, int cellSize);
 void renderTrees(Graphics g, Set<Point> treeLocations, int cellSize);
 void renderTrafficLights(Graphics g, Map<Point, TrafficLight> trafficLights, int cellSize);
}