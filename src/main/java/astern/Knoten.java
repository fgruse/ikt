package astern;

import java.util.Objects;

public class Knoten {

    private final Graph graph;
    private final int index;
    private final int x;
    private final int y;
    private Knoten parent;
    private double kostenStartBisZuMir;
    private double kostenBisZielGeschaetzt;
    private boolean abgearbeitet;

    public Knoten(final Graph graph, final int index, final int x, final int y) {
        this.graph = graph;
        this.index = index;
        this.x = x;
        this.y = y;
        this.parent = null;
        this.kostenStartBisZuMir = Double.POSITIVE_INFINITY;
        this.kostenBisZielGeschaetzt = Double.POSITIVE_INFINITY;
        this.abgearbeitet = false;
    }

    public int getIndex() {
        return index;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Knoten getParent() {
        return parent;
    }

    public void setParent(final Knoten parent) {
        this.parent = parent;
    }

    public double getKostenStartBisZuMir() {
        return kostenStartBisZuMir;
    }

    public void setKostenStartBisZuMir(final double kosten) {
        this.kostenStartBisZuMir = kosten;
    }

    public double getKostenBisZielGeschaetzt() {
        return kostenBisZielGeschaetzt;
    }

    public void setKostenBisZielGeschaetzt(final double kostenBisZielGeschaetzt) {
        this.kostenBisZielGeschaetzt = kostenBisZielGeschaetzt;
    }

    public double getGesamtkosten() {
        return kostenStartBisZuMir + kostenBisZielGeschaetzt;
    }

    public boolean isNotAbgearbeitet() {
        return !abgearbeitet;
    }

    public void setAbgearbeitet(final boolean abgearbeitet) {
        this.abgearbeitet = abgearbeitet;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Knoten knoten = (Knoten) o;
        return index == knoten.index &&
                x == knoten.x &&
                y == knoten.y &&
                Objects.equals(graph, knoten.graph);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, x, y);
    }
}
