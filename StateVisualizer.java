import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import javax.swing.Timer;


// Driver Class
class StateVisualizer extends JPanel implements ActionListener {
	private static final int BASE_CELL_SIZE = 20; // Base size for cells when panel is resized
    private Map<Point, TrafficLight> trafficLightMap;
    private Ambulance ambulance;
    private int hospitalX;
    private int hospitalY;
    private TargetManager targetManager;
    private GridRenderer gridRenderer;

    public StateVisualizer() {
        Grid.resetGrid();
        hospitalX = 1;
        hospitalY = 1;
        
        targetManager = new TargetManager();
        gridRenderer = new GridRenderer();
        
        Grid.grid[hospitalX][hospitalY] = 2;
        initializeTrafficLights();
        
        ambulance = new Ambulance(hospitalX, hospitalY, 
                                targetManager.getCurrentTargetX(), 
                                targetManager.getCurrentTargetY(), 
                                trafficLightMap, this);
        
        setupTimers();
  
        // Panel setup
        setPreferredSize(new Dimension(800, 600));  // Initial size
        setBackground(Color.WHITE);
 
 
        // Repaint on window resize
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();  // Redraw everything when the window is resized
            }
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetSimulation();
            }
        });
    }

    private void initializeTrafficLights() {
        trafficLightMap = new HashMap<>();
        for (int r = 1; r < Grid.grid.length; r += 4) {
            for (int c = 1; c < Grid.grid[0].length; c += 6) {
                if (Grid.grid[r][c] == 0) {
                    Point lightLocation = new Point(c, r);
                    trafficLightMap.put(lightLocation, new TrafficLight());
                }
            }
        }
    }

    private void setupTimers() {
        Timer pathTimer = new Timer(250, this);
        pathTimer.start();
    }

    public void selectNextTarget() {
        Grid.grid[targetManager.getCurrentTargetX()][targetManager.getCurrentTargetY()] = 0;
        Point newTarget = targetManager.nextTarget();
        Grid.grid[targetManager.getCurrentTargetX()][targetManager.getCurrentTargetY()] = 3;
    }

    public int getGoalX() {
        return targetManager.getCurrentTargetX();
    }

    public int getGoalY() {
        return targetManager.getCurrentTargetY();
    }

    private void resetSimulation() {
        Grid.resetGrid();
        Grid.grid[hospitalX][hospitalY] = 2;
        
        // Reset target manager
        targetManager = new TargetManager();
        Grid.grid[targetManager.getCurrentTargetX()][targetManager.getCurrentTargetY()] = 3;

        // Reset traffic lights
        for (TrafficLight light : trafficLightMap.values()) {
            if (!light.getColor().equals("RED")) {
                light.forceGreen(false);
            }
        }

        // Reset ambulance
        ambulance = new Ambulance(hospitalX, hospitalY, 
                                targetManager.getCurrentTargetX(), 
                                targetManager.getCurrentTargetY(), 
                                trafficLightMap, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (TrafficLight light : trafficLightMap.values()) {
            light.update();
        }
        ambulance.move();
        repaint();
    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Get panel size and calculate cell size based on panel width and height
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int cellWidth = panelWidth / Grid.grid[0].length;
        int cellHeight = panelHeight / Grid.grid.length;
        int CELL_SIZE = Math.min(cellWidth, cellHeight);  // Use smaller of width or height to maintain grid aspect ratio
 
        
        // Render base grid
        gridRenderer.render(g, Grid.grid, CELL_SIZE);
        
        // Render trees
        gridRenderer.renderTrees(g, Grid.treeLocations, CELL_SIZE);
        
        // Render traffic lights
        gridRenderer.renderTrafficLights(g, trafficLightMap, CELL_SIZE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Grid Visualization");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new StateVisualizer());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}