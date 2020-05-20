package ue4;

import java.util.Objects;

public class Edge {

    private double weight;
    private Node start;
    private Node end;

    public Edge(final double weight, final Node start, final Node end) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

    /**
     * gibt das Kantengewicht zurück (eine Fließkommazahl)
     * @return Kantengewicht
     */
    public double getWeight() {
        return weight;
    }

    /**
     * gibt den Ausgangsknoten der Kante zurück
     * @return Ausgangsknoten
     */
    public Node getStart() {
        return start;
    }

    /**
     * Gibt den Endknoten der Kante zurückk
     * @return Endknoten
     */
    public Node getEnd() {
        return end;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Edge edge = (Edge) o;
        return Double.compare(edge.getWeight(), this.weight) == 0 &&
                Objects.equals(this.start, edge.getStart()) &&
                Objects.equals(this.end, edge.getEnd());
    }
}
