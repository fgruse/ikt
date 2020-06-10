package ue4.versuch1;

import java.util.Objects;

public class EdgeDG {

    private double weight;
    private NodeDG start;
    private NodeDG end;

    public EdgeDG(final double weight, final NodeDG start, final NodeDG end) {
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
    public NodeDG getStart() {
        return start;
    }

    /**
     * Gibt den Endknoten der Kante zurückk
     * @return Endknoten
     */
    public NodeDG getEnd() {
        return end;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final EdgeDG edge = (EdgeDG) o;
        return Double.compare(edge.getWeight(), this.weight) == 0 &&
                Objects.equals(this.start, edge.getStart()) &&
                Objects.equals(this.end, edge.getEnd());
    }
}
