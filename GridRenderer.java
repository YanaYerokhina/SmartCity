import java.awt.*;
import java.util.Map;
import java.util.Set;

//Enhanced DefaultGridRenderer implementation
class GridRenderer implements IGridRenderer {
    @Override
    public void render(Graphics g, int[][] grid, int cellSize) {
        // Set background
        g.setColor(new Color(200, 220, 255));
        g.fillRect(0, 0, grid[0].length * cellSize, grid.length * cellSize);

        // Draw grid cells
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                switch (grid[r][c]) {
                    case 1: // Buildings
                        g.setColor(new Color(150, 150, 150));
                        g.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
                        break;
                    case 0: // Roads
                        g.setColor(Color.BLACK);
                        g.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
                        break;
                    case 2: // Ambulance
                    	
                    	g.setColor(Color.BLACK);
                    	g.fillRect(c * cellSize,  r * cellSize,  cellSize,  cellSize);
                        // Draw car body
                        g.setColor(new Color(255, 0, 0));
                        g.fillRoundRect(
                            c * cellSize + cellSize / 8,
                            r * cellSize + cellSize / 4,
                            cellSize * 3 / 4,
                            cellSize / 2,
                            cellSize / 4,
                            cellSize / 4
                        );
                        // Draw car roof
                        g.setColor(Color.WHITE);
                        g.fillRect(
                            c * cellSize + cellSize / 3,
                            r * cellSize + cellSize / 4,
                            cellSize / 3,
                            cellSize / 4
                        );
                        // Add wheels
                        g.setColor(Color.GRAY);
                        g.fillOval(
                            c * cellSize + cellSize / 8,
                            r * cellSize + cellSize * 3 / 4 - cellSize / 8,
                            cellSize / 6,
                            cellSize / 6
                        );
                        g.fillOval(
                            c * cellSize + cellSize * 5 / 8,
                            r * cellSize + cellSize * 3 / 4 - cellSize / 8,
                            cellSize / 6,
                            cellSize / 6
                        );
                        break;
                    case 4: // Parks
                        g.setColor(new Color(34, 139, 34));
                        g.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
                        break;
                    default:
                        g.setColor(Color.WHITE);
                        g.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
                }
                // Draw cell borders
                g.setColor(Color.BLACK);
                g.drawRect(c * cellSize, r * cellSize, cellSize, cellSize);
            }
        }
    }

    @Override
    public void renderTrees(Graphics g, Set<Point> treeLocations, int cellSize) {
        for (Point treeLoc : treeLocations) {
            int x = treeLoc.y * cellSize;
            int y = treeLoc.x * cellSize;

            // Tree trunk - scaled with cell size
            g.setColor(new Color(139, 69, 19));
            g.fillRect(
                x + cellSize / 4,
                y + cellSize / 2,
                cellSize / 4,
                cellSize / 2
            );

            // Tree canopy - scaled with cell size
            g.setColor(Color.GREEN);
            g.fillOval(
                x + cellSize / 6,
                y + cellSize / 6,
                cellSize / 2,
                cellSize / 2 
            );
        }
    }

    @Override
    public void renderTrafficLights(Graphics g, Map<Point, TrafficLight> trafficLights, int cellSize) {
        for (Map.Entry<Point, TrafficLight> entry : trafficLights.entrySet()) {
            Point location = entry.getKey();
            TrafficLight light = entry.getValue();

            int centerX = location.x * cellSize + cellSize * 2;
            int centerY = location.y * cellSize + cellSize;

           

            // Light color
            g.setColor(light.getColor().equals("RED") ? Color.RED : Color.GREEN);
            g.fillOval(
                centerX - cellSize / 4,
                centerY - cellSize / 4,
                cellSize / 2,
                cellSize / 2
            );
        }
    }
}