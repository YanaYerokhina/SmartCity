import java.util.Objects;


// State class 
// Represents a position state of any object located on the grid
class State implements Comparable<State> {
    int x, y;
    int totalMoves;
    int priority;
    State parent;

    public State(int x, int y, int totalMoves) {
        this.x = x;
        this.y = y;
        this.totalMoves = totalMoves;
    }

    public int manhattanDistance(int goalX, int goalY) {
        return Math.abs(x - goalX) + Math.abs(y - goalY);
    }

    @Override
    public int compareTo(State other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return x == state.x && y == state.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
