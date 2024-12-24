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
* [Ambulance](Ambulance.java): Emergency vehicle implementation with intelligent routing
* [Grid](Grid.java): Static utility class for grid management and generation
* [PathFinder](PathFinder.java): A* algorithm implementation for optimal path calculation
* [TrafficLight](TrafficLight.java): Traffic signal control with emergency vehicle priority
* [TargetManager](TargetManager.java): Emergency location management system

### Key Interfaces
* [GridEntity](GridEntity.java): Base interface for all grid-based objects
* [IGridRenderer](IGridRenderer.java): Interface for rendering grid components
* [IPathFinder](IPathFinder.java): Interface for pathfinding implementations

# Getting Started
### Prerequisites 
* Java Development Kit (JDK) 8 or higher
* Java Swing library (included in JDK)

# Running the Simulation 
1. Compile all Java files:
```
javac *.java
```
2. Run the main class:
```
java StateVisualizer
```
## Interaction
* The simulation starts automatically upon launch
* Click anywhere on the window to reset the simulation
* The window can be resized, and the grid will adjust accordingly

## Architecture
The project follows object-oriented design principles with a clear separation of concerns:
* Model: State management and data structures
* View: Grid rendering and visualization
* Controller: User input and simulation logic

The modular design allows for easy extension and modification of components.

## Contributing 
Feel free to fork the project and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.
