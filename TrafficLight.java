import java.util.Random;
import javax.swing.Timer;
import java.awt.Point;


// Manages traffic light behavior and the global traffic light timer

class TrafficLight implements GridEntity {
 private String color = "RED";
 private boolean forcedGreen = false;
 private Random random = new Random();
 private long nextToggleTime;
 private static final int MIN_INTERVAL = 2000;
 private static final int MAX_INTERVAL = 6000;
 private Timer forcedGreenTimer;
 private Point position;

 public TrafficLight() {
     resetToggleTimer();
 }

 public void updateWithCentralTimer() {
     if (!forcedGreen && System.currentTimeMillis() >= nextToggleTime) {
         toggle();
     }
 }

 public void resetToggleTimer() {
     int interval = MIN_INTERVAL + random.nextInt(MAX_INTERVAL - MIN_INTERVAL + 1);
     nextToggleTime = System.currentTimeMillis() + interval;
 }

 public void toggle() {
     if (!forcedGreen) {
         color = color.equals("RED") ? "GREEN" : "RED";
         resetToggleTimer();
     }
 }

 public void update() {
     if (!forcedGreen && System.currentTimeMillis() >= nextToggleTime) {
         toggle();
     }
 }

 public String getColor() {
     return color;
 }

 public void forceGreen(boolean force) {
     if (force) {
         forcedGreen = true;
         color = "GREEN";
     } else {
         forcedGreen = false;
         color = "RED";
         resetToggleTimer();
     }
 }

//Ensures lights nearby an emergency vehicle are always green 
 public void forceGreenForDuration(int durationMillis) {
     if (forcedGreenTimer != null) {
         forcedGreenTimer.stop();
     }

     forceGreen(true);

     forcedGreenTimer = new Timer(durationMillis, e -> {
         forceGreen(false);
         forcedGreenTimer = null;
     });
     forcedGreenTimer.setRepeats(false);
     forcedGreenTimer.start();
 }

 public boolean isForcedGreen() {
     return forcedGreen;
 }

 @Override
 public int getGridValue() {
     return 4; // Traffic light value
 }

 @Override
 public Point getPosition() {
     return position;
 }
}