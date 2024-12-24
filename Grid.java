import java.util.HashSet;
import java.util.Set;
import java.awt.Point;
import java.util.Random;


// Static utility class for grid management & creation
class Grid {
    public static int[][] grid;
    public static Set<Point> intersectionSquares = new HashSet<>();
    public static Set<Point> stopLineSquares = new HashSet<>();
    public static Set<Point> treeLocations = new HashSet<>();

    public static void resetGrid() {
        int rows = 20;
        int cols = 30;
        grid = new int[rows][cols];
        Random random = new Random();

        // Initialize grid with buildings
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = 1;
            }
        }

        // Create horizontal two-lane roads
        for (int i = 1; i < rows; i += 4) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = 0;
                grid[i + 1][j] = 0;
            }
        }

        // Create vertical two-lane roads
        for (int j = 2; j < cols; j += 6) {
            for (int i = 0; i < rows; i++) {
                grid[i][j] = 0;
                grid[i][j + 1] = 0;
            }
        }

        // Clear previous tree locations
        treeLocations.clear();

        // Add parks and trees
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && random.nextDouble() < 0.15) {
                    grid[i][j] = 4;
                    treeLocations.add(new Point(i, j));
                }
            }
        }
    }
}
