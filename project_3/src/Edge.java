import java.util.Objects;

public class Edge {
    Vertex source, destination;
    int capacity;

    public Edge(Vertex source, Vertex destination, int capacity) {
        this.source = source;
        this.destination = destination;
        this.capacity = capacity;
    }

    // override the equals method so the ArrayList can use to check if two vertices and edges are same
    @Override
    public boolean equals(Object obj) {
        Edge edge = (Edge) obj;
        if (this.source.equals(edge.source))
            if (this.destination.equals(edge.destination))
                return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, capacity);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", destination=" + destination +
                ", capacity=" + capacity +
                '}';
    }
}
