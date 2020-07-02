package astern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class UndirectedGraph {

    private final static int MAX_NUMBER_OF_NODES = 10000;
    private final boolean[][] adjacencyMatrix;
    private final Node[] nodes;

    /**
     * Class constructor, creates graph that doesn't contain any nodes or edges yet,
     * maximum number of nodes the graph can contain is 10000
     */
    /* TODO 12 - konstruktor wird moment nur in der statischen fromFile Methode aufgerufen
        und ergibt sonst keinen sinn, da insert-Methoden private sind (graph soll immutable sein,
        sobald der konstruktor aufgerufen wurde), darf man den constructor private/protected machen?
        wie kann man das sonst lösen? so umformen, dass constructor liste/array von knoten und
        kanten bekommt stattdessen?
     */
    protected UndirectedGraph() {
        this.adjacencyMatrix = new boolean[MAX_NUMBER_OF_NODES][MAX_NUMBER_OF_NODES];
        for (boolean[] row: this.adjacencyMatrix) {
            Arrays.fill(row, false);
        }
        this.nodes = new Node[MAX_NUMBER_OF_NODES];
        Arrays.fill(this.nodes, null);
    }

    /**
     * static method that creates graph and inserts nodes and edges read from a file
     * @param file contains all nodes and edges that should be inserted into the graph
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
                // TODO 16 - exception wenn keine 10k knoten eingefügt wurden zur sicherheit? eigentlich kann das aber auch nie passieren
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
     * Inserts new node into graph at the specified index,
     * doesn't overwrite if a node at the specified index already exists // TODO necessary?
     * @param index node's index
     * @param xCoordinate node's x-coordinate
     * @param yCoordinate node's y-coordinate
     * @throws IndexOutOfBoundsException if node's index is too big for graph
     */
    private void  insertNode(final int index, final int xCoordinate, final int yCoordinate) throws IndexOutOfBoundsException {
        // TODO 13 - überhaupt notweding? eingabedatei enthält immer exakt 10k knoten, also kann das eigentlich nie passieren
        if(index >= MAX_NUMBER_OF_NODES) {
            throw new IndexOutOfBoundsException("Index out of bounds: This graph can't contain this many nodes!");
        }
        // TODO 14 - überhaupt notwendig? eingabedatei enthält nie doppelte knoten, also kann eigentlich auch nie passieren
        if(this.nodes[index]==null) {
            Node k = new Node(index, xCoordinate, yCoordinate);
            this.nodes[index] = k;
        }
    }

    /**
     * Inserts new edge into the graph between two nodes,
     * doesn't insert edge if one or both nodes don't exist (yet) // TODO necessary?
     * @param indexNode1 first node's index
     * @param indexNode2 second node's index
     * @throws IndexOutOfBoundsException if node's index is too big for graph
     */
    private void insertEdge(final int indexNode1, final int indexNode2) throws IndexOutOfBoundsException {
        // TODO 15 - kanten in eingabedatei sind an die exakt 10k knoten angepasst, kann nie passieren
        if(indexNode1 >= MAX_NUMBER_OF_NODES || indexNode2 >= MAX_NUMBER_OF_NODES) {
            throw new IndexOutOfBoundsException("Index out of bounds: This graph can't contain this many nodes!");
        }
        // TODO 16 - überhaupt notwendig? kanten kommen immer nach all knoten in eingabedatei
        if(this.nodes[indexNode1]!=null && this.nodes[indexNode2]!=null) {
            this.adjacencyMatrix[indexNode1][indexNode2] = true;
            this.adjacencyMatrix[indexNode2][indexNode1] = true;
        }
    }

    public boolean[] getAdjacencyArrayForNode(final Node node) {
        return this.adjacencyMatrix[node.getIndex()];
    }

    /**
     * getter for graph's adjecency matrix
     * @return graph's adjecency matrix as a 2D-array
     */
    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    /**
     * getter for graph's nodes
     * @return an array containing the graph's nodes
     */
    public Node[] getNodes() {
        return nodes;
    }

    /**
     * getter for maximum number of nodes in graph
     * @return int - maximum number of nodes in graph
     */
    public int getMaxNumberOfNodes() {
        return MAX_NUMBER_OF_NODES;
    }
}
