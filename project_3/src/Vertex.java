public class Vertex {
    String Name;
    boolean isSource;
    boolean isSink;

    public Vertex(String name) {
        Name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return  ((Vertex) obj).Name.equals(this.Name);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "Name='" + Name + '\'' +
                ", isSource=" + isSource +
                ", isSink=" + isSink +
                '}';
    }
}
