package astern;

import java.util.Objects;

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

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Path path = (Path) o;
        return Double.compare(path.length, length) == 0 &&
                Objects.equals(graph, path.graph) &&
                Objects.equals(nodes, path.nodes);
    }

    @Override
    public String toString() {
        return nodes.toString();
    }
}
