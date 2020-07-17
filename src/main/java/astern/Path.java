package astern;

import java.util.Arrays;

/**
 * Diese Klasse modelliert einen Weg bestehend aus Knoten eines Graphen und speichert diese und die Länge des Weges.
 */
public class Path {

    /** Sortiertes Array, welches die Knoten enthält, die den Weg bilden */
    private final Node[] nodes;
    /** Länge des Wegs */
    private final double length;

    /**
     * Konstruktor der Klasse, spezifiziert die Knoten des Wegen und dessen Länge
     * @param nodes Sortiertes Array, welches die Knoten enthält, die den Weg bilden
     * @param length Länge des Wegs
     */
    public Path(final Node[] nodes, final double length) {
        this.nodes = nodes;
        this.length = length;
    }

    /**
     * Gibt Länge des Wegs zurück
     * @return Länge des Wegs
     */
    public double getLength() {
        return this.length;
    }

    /**
     * Gibt eine String-Repräsentation der Knoten im Weg zurück
     * @return String-Repräsentation der Knoten im Weg zurück
     */
    @Override
    public String toString() {
        String returnString = "[";
        for (int nodeIndex=0; nodeIndex<this.nodes.length-1; nodeIndex++) {
            returnString += this.nodes[nodeIndex] + ", ";
        }
        returnString += this.nodes[this.nodes.length-1] + "]";
        return returnString;
    }
}
