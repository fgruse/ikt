package astern;

public class Node {

    private final int index;
    private final int xCoordinate;
    private final int yCoordinate;

    public Node(final int index, final int xCoordinate, final int yCoordinate) {
        this.index = index;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    // euklidische distanz/ satz des pythagoras
    // TODO - richtiger ort dafür? oder in andere klasse?
    static public double getDistanceBetween(final Node node1, final Node node2) {
        final int x1 = node1.getxCoordinate();
        final int x2 = node2.getxCoordinate();
        final int y1 = node1.getyCoordinate();
        final int y2 = node2.getyCoordinate();
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    public int getIndex() {
        return index;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Node node = (Node) o;
        return index == node.index &&
                xCoordinate == node.xCoordinate &&
                yCoordinate == node.yCoordinate;
    }

    @Override
    public String toString() {
        return Integer.toString(index);
    }
}
