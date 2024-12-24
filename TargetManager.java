import java.util.*;
import java.awt.*;
import java.util.List;

// Manages patient location and selection for emergency vehicles (Ambulance)
class TargetManager {
    private List<Point> targetLocations;
    private int currentIndex;
    private Point currentTarget;
    
    public TargetManager() {
        initializeTargets();
        currentIndex = 0;
        currentTarget = targetLocations.get(currentIndex);
    }
    
    private void initializeTargets() {
        targetLocations = new ArrayList<>();
        targetLocations.add(new Point(8, 3));   // Target 1
        targetLocations.add(new Point(2, 11));  // Target 2
        targetLocations.add(new Point(14, 5));  // Target 3
        targetLocations.add(new Point(20, 15)); // Target 4
        targetLocations.add(new Point(26, 9));  // Target 5
    }
    
    public Point nextTarget() {
        currentIndex = (currentIndex + 1) % targetLocations.size();
        currentTarget = targetLocations.get(currentIndex);
        return currentTarget;
    }
    
    public Point getCurrentTarget() {
        return currentTarget;
    }
    
    public int getCurrentTargetX() {
        return currentTarget.y;
    }
    
    public int getCurrentTargetY() {
        return currentTarget.x;
    }
}
