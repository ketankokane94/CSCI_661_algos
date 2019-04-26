public class driver {
    public static void main(String args[]) {
        Graph graph = new Graph();
        Vertex source = new Vertex("S");
        source.isSource = true;
        Vertex sink = new Vertex("T");
        sink.isSink = true;
        graph.addEdge(new Edge(source, new Vertex("S_1"), 50));
        graph.addEdge(new Edge(source, new Vertex("S_2"), 36));
        graph.addEdge(new Edge(source, new Vertex("S_3"), 11));
        graph.addEdge(new Edge(source, new Vertex("S_4"), 8));


        graph.addEdge(new Edge(new Vertex("S_1"), new Vertex("T_1"), 50));
        graph.addEdge(new Edge(new Vertex("S_1"), new Vertex("T_2"), 50));
        graph.addEdge(new Edge(new Vertex("S_1"), new Vertex("T_3"), 50));
        graph.addEdge(new Edge(new Vertex("S_1"), new Vertex("T_4"), 50));

        graph.addEdge(new Edge(new Vertex("S_2"), new Vertex("T_2"), 36));
        graph.addEdge(new Edge(new Vertex("S_2"), new Vertex("T_4"), 36));

        graph.addEdge(new Edge(new Vertex("S_3"), new Vertex("T_3"), 11));
        graph.addEdge(new Edge(new Vertex("S_3"), new Vertex("T_4"), 11));

        graph.addEdge(new Edge(new Vertex("S_4"), new Vertex("T_4"), 36));

        graph.addEdge(new Edge(new Vertex("T_1"), sink,45));
        graph.addEdge(new Edge(new Vertex("T_2"), sink,42));
        graph.addEdge(new Edge(new Vertex("T_3"), sink,10));
        graph.addEdge(new Edge(new Vertex("T_4"), sink,3));


        while (graph.getPathFrom(source,sink)){}
        int x = 0;
        for (int i = 0; i < graph.flows.size(); i++) {
            x += graph.flows.get(i);
        }
        System.out.println("Maximum flow= " + x);

    }
}
