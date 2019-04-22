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
        return super.equals(obj);
    }
}
