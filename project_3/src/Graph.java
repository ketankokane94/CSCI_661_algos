import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    List<Vertex> vertices;
    List<Edge> edges;
    List<Integer> flows  = new ArrayList<>();

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public boolean addEdge(Edge edge) {
        // check if the sources and distination exists in the vertex list or not if no then add them
        if (!containsVertex(edge.source))
            addVertex(edge.source);
        if (!containsVertex(edge.destination))
            addVertex(edge.destination);
        edges.add(edge);
        return true;
    }

    public boolean removeEdge(Edge edge) {
        edges.remove(edge);
        return false;
    }

    public boolean addVertex(Vertex vertex) {
        if (!vertices.contains(vertex))
            vertices.add(vertex);
        return true;
    }

    public boolean removeVertex(Vertex vertex) {
        if (vertices.contains(vertex))
            vertices.add(vertex);
        return false;
    }

    public boolean containsVertex(Vertex vertex) {
        return vertices.contains(vertex);
    }

    public void getPath(Vertex source, Vertex sink) {
        final HashMap<Vertex, Vertex> augmentingPathVertex = getAugmentingPathVertex(source, sink);
        ArrayList<Vertex> vertexArrayList = new ArrayList<>();
        Vertex current = sink;
        while (!current.equals(source)) {
            vertexArrayList.add(current);
            current = augmentingPathVertex.get(current);
        }
        vertexArrayList.add(current);

       // Vertex left = vertexArrayList.re

        System.out.println(vertexArrayList.toString());
    }

    public HashMap<Vertex, Vertex> getAugmentingPathVertex(Vertex source, Vertex sink) {
        Stack<Vertex> stack = new Stack<>();
        Set<Vertex> visited = new TreeSet<>();
        HashMap<Vertex, Vertex> hashMap = new HashMap<>();

        stack.push(source);

        while (!stack.empty()) {
            Vertex current = stack.pop();
            if (visited.contains(current))
                continue;
            for (Vertex vertex : getNeightbors(current)) {
                hashMap.put(vertex, current);
                if (vertex.equals(sink))
                    return hashMap;
                stack.push(vertex);
            }
            visited.add(current);
        }
        return hashMap;
    }

    private List<Vertex> getNeightbors(Vertex current) {
        final List<Edge> result = this.edges.stream().filter(edge -> edge.source.Name.equals(current.Name) && edge.capacity > 0).collect(Collectors.toList());
        List<Vertex> neighborList = new ArrayList<>();
        for (Edge edge : result) {
            neighborList.add(edge.destination);
        }
        return neighborList;
    }
}
