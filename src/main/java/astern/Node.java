package astern;

import java.util.Objects;

public class Node {

    private final UndirectedGraph graph;
    private final int index;
    private final int xCoordinate;
    private final int yCoordinate;
    private Node parentNode;
    private double distanceFromStart;
    private double fScore;
    private boolean visited;
    private int numberOfSuccessors;

    public Node(final UndirectedGraph graph, final int index, final int xCoordinate, final int yCoordinate) {
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
        if(parentNode!=null) {
            this.numberOfSuccessors = parentNode.getNumberOfSuccessors() + 1;
        }
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

    // euklidisch
    static public double getDistanceBetween(final Node node, final Node node2) {
        final int x1 = node.getxCoordinate();
        final int x2 = node2.getxCoordinate();
        final int y1 = node.getyCoordinate();
        final int y2 = node2.getyCoordinate();

        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    // TODO - löschen? ersetzen? rename variables
    /**
     * gibt alle Nachbarorte eines Ortes zurüc
     * @return Array der Nachbarorte
     * @throws IndexOutOfBoundsException wenn Index vom Ort zu groß
     */
    public Node[] getNeighbors() throws IndexOutOfBoundsException {
        if(this.index >= this.graph.getNumberOfNodes()) {
            throw new IndexOutOfBoundsException("Index out of bounds. Knoten gibt es nicht!");
        }
        ArrayList<Integer> indices = new ArrayList<>();
        final boolean[] isNeighbor = this.graph.getAdjacencyMatrix()[this.index];
        for (int i = 0; i< isNeighbor.length; i++) {
            if(isNeighbor[i]) {
                indices.append(i);
            }
        }
        Node[] nachbarn = new Node[indices.size()];
        for(int i=0; i<indices.size(); i++) {
            nachbarn[i] = this.graph.getNodes()[indices.get(i)];
        }
        return nachbarn;
    }

    public UndirectedGraph getGraph() {
        return graph;
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

    @Override
    public String toString() {
        return Integer.toString(index);
    }

    public void setNumberOfSuccessors(final int numberOfSuccessors) {
        this.numberOfSuccessors = numberOfSuccessors;
    }
}
