import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    List<Vertex> vertices;
    List<Edge> edges;
    List<Integer> flows;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        flows = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        if (edgeExistUpdateTheCapacity(edge))
            return;
        addVerticesIfNotExists(edge);
        edges.add(edge);
        return;
    }


    public void addVertex(Vertex vertex) {
        if (!vertices.contains(vertex))
            vertices.add(vertex);
    }


    public boolean containsVertex(Vertex vertex) {
        return vertices.contains(vertex);
    }

    public Edge getEdge(Vertex source, Vertex destination) {
        final List<Edge> collect = edges.stream().filter(edge -> edge.source.equals(source) && edge.destination.equals(destination)).collect(Collectors.toList());
        return collect.get(0);
    }

    public boolean getPathFrom(Vertex source, Vertex sink) {

        final HashMap<Vertex, Vertex> augmentingPathVertex = getAugmentingPathVertex(source, sink);
        // if the found path does not contain sink then stop
        if (!augmentingPathVertex.keySet().contains(sink))
            return false;

        ArrayList<Vertex> vertexArrayList = getVerticesInFoundPath(source, sink, augmentingPathVertex);

        int minCapacityFlowInFoundPath = findMinCapacityFlowInFoundPath(vertexArrayList);

        updateResidualGraph(vertexArrayList, minCapacityFlowInFoundPath);

        return true;
    }

    private boolean edgeExistUpdateTheCapacity(Edge edge) {
        int index;
        if ((index = edges.indexOf(edge)) != -1) {
            Edge edge1 = edges.get(index);
            edge1.capacity += edge.capacity;
            return true;
        }
        return false;
    }

    private void addVerticesIfNotExists(Edge edge) {
        if (!containsVertex(edge.source))
            addVertex(edge.source);
        if (!containsVertex(edge.destination))
            addVertex(edge.destination);
    }

    private void updateResidualGraph(ArrayList<Vertex> vertexArrayList, int minCapacityFlowInFoundPath) {
        for (int index = vertexArrayList.size() - 1; index > 0; index--) {
            Edge edge = getEdge(vertexArrayList.get(index), vertexArrayList.get(index - 1));
            // add an edge from right vertex to left vertex with the minimum capacity
            addEdge(new Edge(vertexArrayList.get(index - 1), vertexArrayList.get(index), minCapacityFlowInFoundPath));
            // now reduce the capacity from left to right
            addEdge(new Edge(vertexArrayList.get(index), vertexArrayList.get(index - 1), -minCapacityFlowInFoundPath));

        }
    }

    private int findMinCapacityFlowInFoundPath(ArrayList<Vertex> vertexArrayList) {
        int min = Integer.MAX_VALUE;
        for (int index = vertexArrayList.size() - 1; index > 0; index--) {
            Edge edge = getEdge(vertexArrayList.get(index), vertexArrayList.get(index - 1));
            if (min > edge.capacity) {
                min = edge.capacity;
            }
        }
        System.out.println("Flow = " + min);
        flows.add(min);
        return min;
    }

    private ArrayList<Vertex> getVerticesInFoundPath(Vertex source, Vertex sink, HashMap<Vertex, Vertex> augmentingPathVertex) {
        ArrayList<Vertex> vertexArrayList = new ArrayList<>();
        Vertex current = sink;
        while (!current.equals(source)) {
            vertexArrayList.add(current);
            current = augmentingPathVertex.get(current);
        }
        vertexArrayList.add(current);
        return vertexArrayList;
    }

    private HashMap<Vertex, Vertex> getAugmentingPathVertex(Vertex source, Vertex sink) {
        Stack<Vertex> stack = new Stack<>();
        Set<Vertex> visited = new TreeSet<>();
        HashMap<Vertex, Vertex> hashMap = new HashMap<>();
        stack.push(source);
        while (!stack.empty()) {
            Vertex current = stack.pop();
            if (visited.contains(current))
                continue;
            for (Vertex vertex : getNeighbors(current)) {
                if (!hashMap.keySet().contains(vertex))
                    hashMap.put(vertex, current);
                if (vertex.equals(sink))
                    return hashMap;
                stack.push(vertex);
            }
            visited.add(current);
        }
        return hashMap;
    }

    private List<Vertex> getNeighbors(Vertex current) {
        final List<Edge> result = this.edges.stream().filter(edge -> edge.source.Name.equals(current.Name) && edge.capacity > 0).collect(Collectors.toList());
        List<Vertex> neighborList = new ArrayList<>();
        for (Edge edge : result) {
            neighborList.add(edge.destination);
        }
        return neighborList;
    }
}
