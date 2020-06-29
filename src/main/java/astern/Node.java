package astern;

import java.util.Objects;

public class Node {

    private final int index;
    private final int xCoordinate;
    private final int yCoordinate;

    public Node(final int index, final int xCoordinate, final int yCoordinate) {
        this.index = index;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
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

    // euklidisch
    static public double getDistanceBetween(final Node node, final Node node2) {
        final int x1 = node.getxCoordinate();
        final int x2 = node2.getxCoordinate();
        final int y1 = node.getyCoordinate();
        final int y2 = node2.getyCoordinate();

        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
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
