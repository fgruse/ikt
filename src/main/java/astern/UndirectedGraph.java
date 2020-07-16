package astern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Diese Klasse modelliert den Graphen der die Knoten in einem Array beinhaltet.
 */

public class UndirectedGraph {

    /** Statische Variable, welche die feste Anzahl der maximalen Knoten enthält*/
    private final static int MAX_NUMBER_OF_NODES = 10000;
    /** zweidimensionales Array, welches Kante zwischen zwei Knoten markiert*/
    private final boolean[][] adjacencyMatrix;
    /** Sortiertes Array, welches alle Knoten im Graphen enthält */
    private final Node[] nodes;


    /**
     * Konstruktor der Klasse , kann nur innerhalb der Klasse aufgerufen werden in fromFile()
     * erzeugt Graphen welche vorerst weder Knoten noch Kanten haben
     * Maximale Anzahl an Knoten ist 10000
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
     * statische Methode, die ein Diagramm erstellt und Knoten und Kanten einfügt, welche aus einer Datei gelesen wurden
     * @param file enthält alle Knoten und Kanten, die in das Diagramm eingefügt werden sollen
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
     * Fügt neuen Knoten am angegebenen Index in das Diagramm ein.
     * Wenn der Knoten am Index bereits vorhanden ist, wird er überschrieben.
     * @param index Knoten Index
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
     * Inserts new edge into the graph between two nodes,
     * doesn't insert edge if one or both nodes don't exist
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

    public boolean[] getAdjacencyArrayForNode(final Node node) {
        return this.adjacencyMatrix[node.getIndex()];
    }

    /**
     * getter für die Adjazenzmatrix des Graphen
     * @return Adjazenzmatrix des Graphen als zweidimensionales Array
     */
    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    /**
     * getter für die Knoten des Graphen
     * @return ein Array, das die Knoten des Graphen enthält
     */
    public Node[] getNodes() {
        return nodes;
    }

    /**
     * getter für die maximale Anzahl an Knoten im Graphen
     * @return int - maximal ANzahl an Knoten im Graphen
     */
    public int getMaxNumberOfNodes() {
        return MAX_NUMBER_OF_NODES;
    }
}
