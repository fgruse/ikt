package astern;

public class Knoten {

    private final Graph graph;
    private final int index;
    private final int x;
    private final int y;
    private Knoten parent;
    private double kostenStartBisZuMir;
    private double kostenBisZielGeschaetzt;

    public Knoten(final Graph graph, final int index, final int x, final int y) {
        this.graph = graph;
        this.index = index;
        this.x = x;
        this.y = y;
        this.parent = null;
        this.kostenStartBisZuMir = 0;
        this.kostenBisZielGeschaetzt = 0;
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
}
