package astern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Diese Klasse modelliert einen ungerichteten Graphen.
 */
public class UndirectedGraph {

    /** Statische Variable, welche die feste Anzahl der maximalen Knoten angibt */
    private final static int MAX_NUMBER_OF_NODES = 10000;
    /** Adjazenzmatrix, dargestellt durch ein zweidimensionales Array, welches angibt, ob sich zwischen Knoten Kanten befinden oder nicht */
    private final boolean[][] adjacencyMatrix;
    /** Sortiertes Array, welches alle Knoten des Graphen enthält */
    private final Node[] nodes;


    /**
     * Konstruktor der Klasse, kann nur innerhalb der Klasse aufgerufen werden.
     * Erzeugt einen Graphen, welcher vorerst weder Knoten noch Kanten hat und initialisiert.
     * Knoten-Array und Adjazenzmatrix mit der Länge der maximalen Knotenanzahl
     */
    private UndirectedGraph() {
        this.nodes = new Node[MAX_NUMBER_OF_NODES];
        this.adjacencyMatrix = new boolean[MAX_NUMBER_OF_NODES][MAX_NUMBER_OF_NODES];
        for (boolean[] row: this.adjacencyMatrix) {
            for(int nodeIndex=0; nodeIndex<row.length; nodeIndex++) {
                row[nodeIndex] = false;
            }
        }
    }

    /**
     * Statische Methode, die einen Graphen erzeugt und Knoten und Kanten einfügt, welche aus einer Datei ausgelesen wurden
     * @param file Pfad zur Eingabedatei, welche alle Knoten und Kanten beinhaltet
     * @return Ungerichteter Graph
     */
    public static UndirectedGraph fromFile(final String file) {
        UndirectedGraph graph = new UndirectedGraph();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                String[] entry = line.split(",");
                if(entry[0].equals("O")) {
                    graph.insertNode(
                            Integer.parseInt(entry[1]),
                            Integer.parseInt(entry[2]),
                            Integer.parseInt(entry[3])
                    );
                }
                else {
                    graph.insertEdge(
                            Integer.parseInt(entry[1]),
                            Integer.parseInt(entry[2])
                    );
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    /**
     * Fügt neuen Knoten am angegebenen Index in den Graphen ein
     * Wenn der Knoten am Index bereits vorhanden ist, wird er überschrieben.
     * @param index Index des Knoten
     * @param xCoordinate X-Koordinate des Knoten
     * @param yCoordinate Y-Koordinate des Knoten
     * @throws IndexOutOfBoundsException wenn der Index des Knotens für den Graphen zu groß ist
     */
    private void insertNode(final int index, final int xCoordinate, final int yCoordinate) throws IndexOutOfBoundsException {
        if(index >= MAX_NUMBER_OF_NODES) {
            throw new IndexOutOfBoundsException("Index out of bounds: This graph can't contain this many nodes!");
        }
        Node node = new Node(index, xCoordinate, yCoordinate);
        this.nodes[index] = node;
    }

    /**
     * Fügt neue Kante zwischen zwei Knoten in den Graphen ein
     * Kante wird nicht erzeugt, falls einer oder beide Knoten nicht existieren
     * @param indexNode1 Index des ersten Knoten
     * @param indexNode2 Index des zweiten Knoten
     * @throws IndexOutOfBoundsException wenn der Index des Knotens für den Graphen zu groß ist
     */
    private void insertEdge(final int indexNode1, final int indexNode2) throws IndexOutOfBoundsException {
        if(indexNode1 >= MAX_NUMBER_OF_NODES || indexNode2 >= MAX_NUMBER_OF_NODES) {
            throw new IndexOutOfBoundsException("Index out of bounds: This graph can't contain this many nodes!");
        }
        if(this.nodes[indexNode1]!=null && this.nodes[indexNode2]!=null) {
            this.adjacencyMatrix[indexNode1][indexNode2] = true;
            this.adjacencyMatrix[indexNode2][indexNode1] = true;
        }
    }

    /**
     * Gibt eine Zeile aus der Adjazenzmatrix für einen bestimmten Knoten zurück
     * @param node Knoten, dessen Zeile aus der Adjazentmatrix ausgelesen werden soll
     * @return Zeile aus der Adjazenzmatrix
     */
    public boolean[] getAdjacencyArrayForNode(final Node node) {
        return this.adjacencyMatrix[node.getIndex()];
    }

    /**
     * Gibt die Adjazenzmatrix des Graphen zurück
     * @return Adjazenzmatrix des Graphen als zweidimensionales Array
     */
    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    /**
     * Gibt Knoten-Array des Graphen zurück
     * @return Array, welches alle Knoten des Graphen enthält
     */
    public Node[] getNodes() {
        return nodes;
    }

    /**
     * Gibt die maximale Anzahl an Knoten im Graphen zurück
     * @return maximal Anzahl an Knoten im Graphen
     */
    public static int getMaxNumberOfNodes() {
        return MAX_NUMBER_OF_NODES;
    }
}
