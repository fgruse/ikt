package astern;

public class Node {

    // TODO 7 - muss node graph kennen? eigentlich nicht, keine funktionalität, aber sollte einheitlich mit path sein? (siehe TODO 4)
    private final int index;
    private final int xCoordinate;
    private final int yCoordinate;

    public Node(final int index, final int xCoordinate, final int yCoordinate) {
        this.index = index;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    // berechnung mit euklidischer distanz
    // TODO 3 - richtiger ort oder in andere klasse verschieben (ASTAR oder GRAPH), so lassen oder statisch?
    public double getDistanceTo(final Node node) {
        final int x1 = this.getXCoordinate();
        final int x2 = node.getXCoordinate();
        final int y1 = this.getYCoordinate();
        final int y2 = node.getYCoordinate();
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    public int getIndex() {
        return index;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
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
