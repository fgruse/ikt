package astern;

import java.util.Arrays;

public class Path {

    private final UndirectedGraph graph;
    private final Node[] nodes;

    public Path(final UndirectedGraph graph, final Node[] nodes) {
        this.graph = graph;
        this.nodes = nodes;
    }

    public double getPathLength() {
        if(this.getNumberOfNodes()>0) {
            return this.nodes[nodes.length-1].getDistanceFromStart();
        }
        else {
            return 0.0;
        }
    }

    public int getNumberOfNodes() {
        return nodes.length;
    }

    public static Path fromNodeSuccessors(Node endNode) {
        int length = endNode.getNumberOfSuccessors();
        Node[] path = new Node[length];
        int counter = length-1;
        while(!endNode.getParentNode().equals(endNode)){
            path[counter] = endNode;
            endNode = endNode.getParentNode();
            counter--;
        }
        path[0] = endNode.getParentNode();
        return new Path(endNode.getGraph(), path);
    }

    @Override
    public String toString() {
        return "Nodes in path: " + Arrays.toString(nodes);
    }
}
