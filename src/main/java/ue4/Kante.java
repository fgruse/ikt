package ue4;

import ue4.versuch1.NodeDG;

public class Kante {

    private final GerichteterGraph graph;
    private final Knoten from;
    private final Knoten to;
    private final float weight;

    public Kante(final GerichteterGraph graph, final Knoten from, final Knoten to, final float weight) {
        this.graph = graph;
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * gibt das Kantengewicht zurück (eine Fließkommazahl)
     * @return Kantengewicht
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * gibt den Ausgangsknoten der Kante zurück
     * @return Ausgangsknoten
     */
    public Knoten getStart() {
        return this.from;
    }

    /**
     * Gibt den Endknoten der Kante zurückk
     * @return Endknoten
     */
    public Knoten getEnd() {
        return this.to;
    }
}
