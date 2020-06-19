package astern;

import java.util.Arrays;

public class Graph {

    private final int numberOfNodes;
    private final boolean[][] adjacencyMatrix;
    private final Node[] nodes;

    public Graph(final int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        this.adjacencyMatrix = new boolean[numberOfNodes][numberOfNodes];
        for (boolean[] row: this.adjacencyMatrix) {
            Arrays.fill(row, false);
        }
        this.nodes = new Node[numberOfNodes];
        Arrays.fill(this.nodes, null);
    }

    /**
     * fügt einen neuen Ort hinzu, es passiert nicht, wenn am index schon ein Ort existiert
     * @param index Index vom  Ort
     * @param xCoordinate xCoordinate-Koordinate des Orts
     * @param yCoordinate yCoordinate-Koordinate des Orts
     * @return true, wenn Ort eingefügt wurde, false wenn nicht
     * @throws IndexOutOfBoundsException wenn Index vom Ort zu groß
     */
    public boolean insertNode(final int index, final int xCoordinate, final int yCoordinate) throws IndexOutOfBoundsException {
        if(index >= numberOfNodes) {
            throw new IndexOutOfBoundsException("Index out of bounds. Erstelle einen neuen Graphen mit mehr Knoten!");
        }
        if(this.nodes[index]==null) {
            Node k = new Node(this, index, xCoordinate, yCoordinate);
            this.nodes[index] = k;
            return true;
        }
        return false;
    }

    /**
     * fügt eine neue Strasse zwischen zwei Orten ein
     * @param indexNode1 Index vom ersten Ort
     * @param indexNode2 Index vom zweiten Ort
     * @return true, wenn Strasse eingefügt wurde, false wenn nicht
     * @throws IndexOutOfBoundsException wenn Index vom Ort zu groß
     */
    public boolean insertEdge(final int indexNode1, final int indexNode2) throws IndexOutOfBoundsException {
        if(indexNode1 >= numberOfNodes || indexNode2 >= numberOfNodes) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds. Erstelle einen neuen Graphen mit mehr Knoten! Knoten gibt es nicht!"
            );
        }
        if(this.nodes[indexNode1]!=null && this.nodes[indexNode2]!=null) {
            this.adjacencyMatrix[indexNode1][indexNode2] = true;
            this.adjacencyMatrix[indexNode2][indexNode1] = true;
            return true;
        }
        return false;
    }


    // TODO - move to Node ??
    /**
     * gibt alle Nachbarorte eines Ortes zurück
     * @param indexOrt Index vom  Ort
     * @return Array der Nachbarorte
     * @throws IndexOutOfBoundsException wenn Index vom Ort zu groß
     */
    public Node[] getNeighbors(int indexOrt) throws IndexOutOfBoundsException {
        if(indexOrt >= numberOfNodes) {
            throw new IndexOutOfBoundsException("Index out of bounds. Knoten gibt es nicht!");
        }
        ArrayList<Integer> indizes = new ArrayList<>();
        for (int i = 0; i<this.adjacencyMatrix[indexOrt].length; i++) {
            if(this.adjacencyMatrix[indexOrt][i]) {
                indizes.append(i);
            }
        }
        Node[] nachbarn = new Node[indizes.size()];
        for(int i=0; i<indizes.size(); i++) {
            nachbarn[i] = this.nodes[indizes.get(i)];
        }
        return nachbarn;
    }

    // TODO - move to node ???
    // euklidisch
    static public double getDistance(final Node node, final Node node2) {
        final int x1 = node.getxCoordinate();
        final int x2 = node2.getxCoordinate();
        final int y1 = node.getyCoordinate();
        final int y2 = node2.getyCoordinate();

        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public Node[] getNodes() {
        return nodes;
    }
}
