# Smart City Traffic Simulation
A Java-based simulation of emergency vehicle routing in an urban environment with dynamic traffic management. The project demonstrates intelligent traffic light control and pathfinding for emergency vehicles in a city grid system.

# Features 
* **Dynamic City Grid**: Procedurally generated city layout with:
  * Two-lane roads
  * Buildings
  * Parks with trees
  * Traffic lights at intersections

* **Intelligent Traffic Management**:
  * Autonomous traffic light system with randomized timing
  * Smart traffic light control that prioritizes emergency vehicles
  * Automatic green light forcing for approaching emergency vehicles

* **Emergency Vehicle Simulation**:
  * A* pathfinding algorithm for optimal route calculation
  * Dynamic rerouting capabilities
  * Automated movement between hospital and emergency locations
  * Multiple target location management

* **Interactive Visualization**:
  * Real-time graphical representation of the city grid
  * Responsive window sizing
  * Click-to-reset functionality
  * Visual indicators for:
    * Emergency vehicles
    * Traffic light states
    * Buildings and parks
    * Trees and urban features

# Technical Implementation
### Core Components
* [StateVisualizer](StateVisualizer.java): Main driver class managing the simulation visualization
* Ambulance: Emergency vehicle implementation with intelligent routing
* Grid: Static utility class for grid management and generation
* PathFinder: A* algorithm implementation for optimal path calculation
* TrafficLight: Traffic signal control with emergency vehicle priority
* TargetManager: Emergency location management system




      
