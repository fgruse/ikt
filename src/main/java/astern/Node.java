package astern;

/**
 * Diese Klasse modelliert ein Knoten in einem Koordinatensystem.
 *
 */
public class Node {

    /** Identifier vom Knoten */
    private final int index;
    /** x-Koordinate vom Knoten */
    private final int xCoordinate;
    /** y-Koordonate vom Knoten */
    private final int yCoordinate;

    /**
     * Konstruktor der Klasse, spezifiziert Index, x-Koordinate und y-Koordinate
     * @param index Index vom Knoten
     * @param xCoordinate x-Koordinate vom Knoten
     * @param yCoordinate y-Koordinate vom Knoten
     */
    public Node(final int index, final int xCoordinate, final int yCoordinate) {
        this.index = index;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Berechnet die euklidische Distanz vom Knoten zu einem anderen im Koordinatensystem (Umformung vom Satz des Pythagoras, Luftlinie)
     * @param node Knoten, zu welchem die Distanz berechnet werden soll
     * @return Distanz zu node
     */
    public double getDistanceTo(final Node node) {
        final int x1 = this.getXCoordinate();
        final int x2 = node.getXCoordinate();
        final int y1 = this.getYCoordinate();
        final int y2 = node.getYCoordinate();
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    /**
     * Gibt Index vom Knoten zurück
     * @return Index vom Knoten
     */
    public int getIndex() {
        return index;
    }

    /**
     * Gibt x-Koordinate vom Knoten zurück
     * @return x-Koordinate vom Knoten
     */
    public int getXCoordinate() {
        return xCoordinate;
    }

    /**
     * Gibt y-Koordinate vom Knoten zurück
     * @return y-Koordinate vom Knoten
     */
    public int getYCoordinate() {
        return yCoordinate;
    }

    /**
     * Überprüft, ob zwei Knoten gleich sind, vergleicht x- und y-Koordinaten und Indizes
     * @return true, wenn x-, y-Koordinaten und Indizes beider Knoten identisch sind, false wenn nicht
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Node node = (Node) o;
        return index == node.index &&
                xCoordinate == node.xCoordinate &&
                yCoordinate == node.yCoordinate;
    }

    /**
     * Gibt eine String-Repräsentation der Indizes der Knoten zurück
     * @return String-Repräsentation der Indizes der Knoten
     */
    @Override
    public String toString() {
        return Integer.toString(index);
    }
}
