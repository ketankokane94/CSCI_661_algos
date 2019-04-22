public class driver {
    public static void main(String args[]) {
        Graph graph = new Graph();
        graph.addEdge(new Edge(new Vertex("0"), new Vertex("1"), 16));
        graph.addEdge(new Edge(new Vertex("0"), new Vertex("2"), 13));

        graph.addEdge(new Edge(new Vertex("1"), new Vertex("2"), 10));
        graph.addEdge(new Edge(new Vertex("1"), new Vertex("3"), 12));

        graph.addEdge(new Edge(new Vertex("2"), new Vertex("1"), 4));
        graph.addEdge(new Edge(new Vertex("2"), new Vertex("4"), 14));


        graph.addEdge(new Edge(new Vertex("3"), new Vertex("5"), 20));
        graph.addEdge(new Edge(new Vertex("3"), new Vertex("2"), 9));

        graph.addEdge(new Edge(new Vertex("4"), new Vertex("5"), 4));
        graph.addEdge(new Edge(new Vertex("4"), new Vertex("3"), 7));

        //System.out.println(graph.edges.toString());
        //System.out.println(graph.vertices.toString());

        System.out.println(graph.vertices.size());
        System.out.println(graph.edges.size());

    }
}
