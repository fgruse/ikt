package ue4.versuch1;

import java.util.Objects;
import ue4.Liste;
import ue4.Menge;

public class NodeUG {

    private String name;
    private Menge<NodeUG> adjacentNodes;
    private Menge<EdgeUG> edges;

    public NodeUG(final String name) {
        this.name = name;
        this.adjacentNodes = new Menge<>();
        this.edges = new Menge<>();
    }

    /**
     * gibt eine Liste mit benachbarten Knoten des Knoten zurück
     * @return Liste mit den benachbarten Knoten des Knoten
     */
    public Liste<NodeUG> getAdjacentNodes(){
        return this.adjacentNodes.getList();
    }

    /**
     * gibt eine Liste mit allen Kanten des Knoten zurück
     * @return Liste mit allen Kanten des Knoten
     */
    public Liste<EdgeUG> getEdges(){
        return this.edges.getList();
    }

    /**
     * fügt einen benachbarten Knoten hinzu
     * @param adjacentNode - Kindknoten
     */
    private void addAdjacentNode(NodeUG adjacentNode) {
        this.adjacentNodes.add(adjacentNode);
    }

    /**
     * fügt eine Kante hinzu
     * @param adjacentNode - Kindknoten
     * @param weight - Kantengewicht
     */
    public void addEdge(NodeUG adjacentNode, Double weight) {
        this.addAdjacentNode(adjacentNode);
        this.edges.add(new EdgeUG(weight, this, adjacentNode));
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

    public NodeDG toNode() {
        return new NodeDG(this.name);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NodeUG node = (NodeUG) o;
        return Objects.equals(this.name, node.getName());
    }
}
