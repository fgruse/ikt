package ue4.directed;

import java.util.Objects;
import ue4.Liste;
import ue4.Menge;

public class NodeDG {

    private String name;
    private Menge<NodeDG> children;
    private Menge<NodeDG> parents;

    public NodeDG(final String name) {
        this.name = name;
        this.children = new Menge<>();
        this.parents = new Menge<>();
    }

    /**
     * gibt eine Liste mit den Kindknoten des Knoten zurück
     * @return Liste mit den Kindknoten des Knoten
     */
    public Liste<NodeDG> getChildren(){
        return this.children.getList();
    }

    /**
     * fügt einen Kindknoten hinzu
     * @param child - Kindknoten
     */
    public void addChild(NodeDG child) {
        this.children.add(child);
    }

    /**
     * fügt einen Elternknoten hinzu
     * @param parent - Kindknoten
     */
    public void addParent(NodeDG parent) {
        this.parents.add(parent);
    }

    /**
     * gibt den Ausgangsgrad des Knoten zurück (Anzahl ausgehender Kanten)
     * @return Ausgangsgrad
     */
    public int getOutputDegree() {
        return children.getSize();
    }

    /**
     * gibt den Eingangsgrad des Knoten zurück (Anzahl eingehender Kanten)
     * @return Eingangsgrad
     */
    public int getInputDegree() {
        return parents.getSize();
    }

    /**
     * gibt den Grad des Knoten zurück (Anzahl ein- und ausgehender Kanten)
     * @return Grad
     */
    public int getDegree() {
        return this.getInputDegree() + this.getOutputDegree();
    }

    /**
     * gibt zurück, ob der Knoten ein Blatt ist (keine ausgehenden Kanten)
     * @return true wenn Blatt, false wenn nicht
     */
    public boolean isLeaf(){
        return children.getSize() == 0;
    }

    /**
     * gibt zurück, ob der Knoten die Wurzel ist (keine eingehenden Kanten)
     * @return true wenn Wurzelb, false wenn nicht
     */
    public boolean isRoot() {
        return parents.getSize() == 0;
    }

    /**
     * gibt einen String zurück, welcher als Identifikator für den Knoten verwendet wird
     * @return Name des Knoten
     */
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NodeDG node = (NodeDG) o;
        return Objects.equals(this.name, node.getName());
    }
}
