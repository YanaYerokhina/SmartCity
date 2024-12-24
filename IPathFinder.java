import java.util.LinkedList;

// Defines path finding behavior requirements
// Additonal modularity for other objects with pathfindng behavior
interface IPathFinder {
    LinkedList<State> findPath(State start, int targetX, int targetY, int[][] grid);
}