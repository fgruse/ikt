package astern;

import java.util.Objects;

public class Node {

    private final Graph graph;
    private final int index;
    private final int xCoordinate;
    private final int yCoordinate;
    private Node parentNode;
    private double distanceFromStart;
    private double fScore;
    private boolean visited;
    private int numberOfSuccessors;

    public Node(final Graph graph, final int index, final int xCoordinate, final int yCoordinate) {
        this.graph = graph;
        this.index = index;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.parentNode = null;
        this.distanceFromStart = Double.POSITIVE_INFINITY;
        this.fScore = Double.POSITIVE_INFINITY;
        this.visited = false;
        this.numberOfSuccessors = 0;
    }

    public int getIndex() {
        return index;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(final Node parentNode) {
        this.parentNode = parentNode;
        this.numberOfSuccessors = parentNode.getNumberOfSuccessors() + 1;
    }

    public int getNumberOfSuccessors() {
        return numberOfSuccessors;
    }

    public double getDistanceFromStart() {
        return distanceFromStart;
    }

    public void setDistanceFromStart(final double kosten) {
        this.distanceFromStart = kosten;
    }

    public void updatefScore(final double estimatedDistanceToEnd) {
        this.fScore = estimatedDistanceToEnd + this.distanceFromStart;
    }

    public double getfScore() {
        return fScore;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(final boolean visited) {
        this.visited = visited;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Node node = (Node) o;
        return index == node.index &&
                xCoordinate == node.xCoordinate &&
                yCoordinate == node.yCoordinate &&
                Objects.equals(graph, node.graph);
    }
}
