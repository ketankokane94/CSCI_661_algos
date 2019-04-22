import java.util.List;

public class Graph {
    List<Vertex> vertices;
    List<Edge> edges;


    public boolean addEdge(Edge edge){
        // check if the sources and distination exists in the vertex list or not if no then add them
        if (!containsVertex(edge.source) )
            addVertex(edge.source);
        if (!containsVertex(edge.destination) )
            addVertex(edge.destination);
        edges.add(edge);
        return true;
    }

    public  boolean removeEdge(Edge edge){
        edges.remove(edge);
        return false;
    }

    public boolean addVertex(Vertex vertex){
        if (!vertices.contains(vertex))
            vertices.add(vertex);
        return true;
    }

    public boolean removeVertex(Vertex vertex){
        if (vertices.contains(vertex))
            vertices.add(vertex);
        return false;
    }

    public boolean containsVertex(Vertex vertex){
        return vertices.contains(vertex);
    }

}
