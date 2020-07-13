package astern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UndirectedGraph {

    private final static int MAX_NUMBER_OF_NODES = 10000;
    private final boolean[][] adjacencyMatrix;
    private final Node[] nodes;

    // einziger constructor, kann nur innrehalb der klasse aufgerufen werden in fromFile z.b.
    /**
     * Class constructor, creates graph that doesn't contain any nodes or edges yet,
     * maximum number of nodes the graph can contain is 10000
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
     * if node at index already exists, it's overwritten
     * @param index node's index
     * @param xCoordinate node's x-coordinate
     * @param yCoordinate node's y-coordinate
     * @throws IndexOutOfBoundsException if node's index is too big for graph
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
     * @param indexNode1 first node's index
     * @param indexNode2 second node's index
     * @throws IndexOutOfBoundsException if node's index is too big for graph
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
