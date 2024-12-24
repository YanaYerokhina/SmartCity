
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;


//
// Contains A* path finding algorithm
//
class PathFinder implements IPathFinder {
    @Override
    public LinkedList<State> findPath(State start, int targetX, int targetY, int[][] grid) {
        PriorityQueue<State> queue = new PriorityQueue<>();
        Map<String, Integer> visited = new HashMap<>();
        LinkedList<State> path = new LinkedList<>();
        
        start.priority = start.manhattanDistance(targetX, targetY);
        queue.add(start);

        while (!queue.isEmpty()) {
            State state = queue.poll();
            String key = state.x + "," + state.y;

            if (state.x == targetX && state.y == targetY) {
                while (state != null) {
                    path.addFirst(state);
                    state = state.parent;
                }
                break;
            }

            if (visited.containsKey(key) && visited.get(key) <= state.totalMoves) {
                continue;
            }

            visited.put(key, state.totalMoves);
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            for (int[] dir : directions) {
                int newX = state.x + dir[0];
                int newY = state.y + dir[1];
                if (isValid(newX, newY, grid)) {
                    State successor = new State(newX, newY, state.totalMoves + 1);
                    successor.parent = state;
                    successor.priority = successor.totalMoves + successor.manhattanDistance(targetX, targetY);
                    queue.add(successor);
                }
            }
        }
        return path;
    }

    private boolean isValid(int x, int y, int[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length 
        		&& grid[x][y] != 1 // Not a Building
        		&& grid[x][y] != 4; // Not a Park
        	
    }
}
