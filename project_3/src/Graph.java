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

    public boolean addEdge(Edge edge) {int index;
        if (( index = edges.indexOf(edge))!= -1)
        {
            Edge edge1 = edges.get(index);
            edge1.capacity += edge.capacity;
            return true;
        }
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

    public Edge getEdge(Vertex source, Vertex destination){
        final List<Edge> collect = edges.stream().filter(edge -> edge.source.equals(source) && edge.destination.equals(destination)).collect(Collectors.toList());
        return collect.get(0);
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

        int min = Integer.MAX_VALUE;
        for (int i = vertexArrayList.size() - 1; i > 0 ; i--) {
            Edge edge = getEdge(vertexArrayList.get(i), vertexArrayList.get(i-1));
            if (min > edge.capacity){
                min = edge.capacity;
            }
        }
        System.out.println("min= " + min);
        for (int i = vertexArrayList.size() - 1; i > 0 ; i--) {
            Edge edge = getEdge(vertexArrayList.get(i), vertexArrayList.get(i-1));
           // add an edge from right vertex to left vertex with the minimum capacity
            addEdge(new Edge(vertexArrayList.get(i-1),vertexArrayList.get(i),min));
            // now reduce the capacity from left to right
            addEdge(new Edge(vertexArrayList.get(i),vertexArrayList.get(i-1),-min));

        }
        System.out.println(vertexArrayList.toString());
        System.out.println(vertices.size());
        System.out.println(edges.size());
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
                if(!hashMap.keySet().contains(vertex))
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
