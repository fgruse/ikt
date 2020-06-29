package astern;

public class Path {

    private final UndirectedGraph graph;
    private final ArrayList<Node> nodes;
    private final double length;

    public Path(final UndirectedGraph graph, final ArrayList<Node> nodes, final double length) {
        this.graph = graph;
        this.nodes = nodes;
        this.length = length;
    }

    public double getLength() {
        return this.length;
    }

    @Override
    public String toString() {
        return nodes.toString();
    }
}
