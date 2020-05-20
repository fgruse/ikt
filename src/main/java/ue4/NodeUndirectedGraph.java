package ue4;

import java.util.Objects;

public class NodeUndirectedGraph {

    private String name;
    private Menge<NodeUndirectedGraph> adjacentNodes;
    private Menge<EdgeUndirectedGraph> edges;

    public NodeUndirectedGraph(final String name) {
        this.name = name;
        this.adjacentNodes = new Menge<>();
        this.edges = new Menge<>();
    }

    /**
     * gibt eine Liste mit benachbarten Knoten des Knoten zurück
     * @return Liste mit den benachbarten Knoten des Knoten
     */
    public Liste<NodeUndirectedGraph> getAdjacentNodes(){
        return this.adjacentNodes.getList();
    }

    /**
     * gibt eine Liste mit allen Kanten des Knoten zurück
     * @return Liste mit allen Kanten des Knoten
     */
    public Liste<EdgeUndirectedGraph> getEdges(){
        return this.edges.getList();
    }

    /**
     * fügt einen benachbarten Knoten hinzu
     * @param adjacentNode - Kindknoten
     */
    private void addAdjacentNode(NodeUndirectedGraph adjacentNode) {
        this.adjacentNodes.add(adjacentNode);
    }

    /**
     * fügt eine Kante hinzu
     * @param adjacentNode - Kindknoten
     * @param weight - Kantengewicht
     */
    public void addEdge(NodeUndirectedGraph adjacentNode, Double weight) {
        this.addAdjacentNode(adjacentNode);
        this.edges.add(new EdgeUndirectedGraph(weight, this, adjacentNode));
    }

    /**
     * gibt den Grad des Knoten zurück (Anzahl der Kanten)
     * @return Grad
     */
    public int getDegree() {
        return adjacentNodes.getSize();
    }

    /**
     * gibt zurück, ob der Knoten ein Blatt ist (Knoten mit einem oder keinem benachbarten Knoten)
     * @return true wenn Blatt, false wenn nicht
     */
    public boolean isLeaf(){
        return adjacentNodes.getSize() <= 1;
    }

    /**
     * gibt einen String zurück, welcher als Identifikator für den Knoten verwendet wird
     * @return Name des Knoten
     */
    public String getName() {
        return this.name;
    }

    public Node toNode() {
        return new Node(this.name);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NodeUndirectedGraph node = (NodeUndirectedGraph) o;
        return Objects.equals(this.name, node.getName());
    }
}
