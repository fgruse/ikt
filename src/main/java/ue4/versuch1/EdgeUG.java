package ue4.versuch1;

import java.util.Objects;

public class EdgeUG {

    private double weight;
    private NodeUG node1;
    private NodeUG node2;

    public EdgeUG(final double weight, final NodeUG node1, final NodeUG node2) {
        this.weight = weight;
        this.node1 = node1;
        this.node2 = node2;
    }

    /**
     * gibt das Kantengewicht zurück (eine Fließkommazahl)
     * @return Kantengewicht
     */
    public double getWeight() {
        return weight;
    }

    /**
     * gibt den ersten Knoten der Kante zurück
     * @return erster Knoten
     */
    public NodeUG getNode1() {
        return node1;
    }

    /**
     * Gibt den zweiten der Kante zurückk
     * @return zweiter Knoten
     */
    public NodeUG getNode2() {
        return node2;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final EdgeUG edge = (EdgeUG) o;
        return Double.compare(edge.getWeight(), this.weight) == 0 &&
                (Objects.equals(this.node1, edge.getNode1()) &&
                Objects.equals(this.node2, edge.getNode2())) ||
                (Objects.equals(this.node2, edge.getNode1()) &&
                Objects.equals(this.node1, edge.getNode2()));
    }
}
