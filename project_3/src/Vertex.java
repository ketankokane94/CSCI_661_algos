public class Vertex implements Comparable<Vertex>{
    String Name;
    boolean isSource;
    boolean isSink;

    public Vertex(String name) {
        Name = name;
    }

    @Override
    public boolean equals(Object obj) {
        Vertex temp = (Vertex) obj;
        return temp.Name.equals(this.Name);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "Name='" + Name + '\'' +
                ", isSource=" + isSource +
                ", isSink=" + isSink +
                '}';
    }


    @Override
    public int compareTo(Vertex temp) {
        if (temp.Name.equals(this.Name))
        return 0;
        else
            return 1;
    }
}
