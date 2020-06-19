package astern;

import de.htwberlin.fiw.profiler.ProfiledClass;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class UndirectedGraph extends ProfiledClass {

    private final int numberOfNodes;
    private final boolean[][] adjacencyMatrix;
    private final Node[] nodes;

    /**
     * Class constructor, creates graph that doesn't contain any nodes or edges yet,
     * specifies maximum number of nodes the graph can contain
     * @param numberOfNodes maximum number of nodes the graph can contain
     */
    public UndirectedGraph(final int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        this.adjacencyMatrix = new boolean[numberOfNodes][numberOfNodes];
        for (boolean[] row: this.adjacencyMatrix) {
            Arrays.fill(row, false);
        }
        this.nodes = new Node[numberOfNodes];
        Arrays.fill(this.nodes, null);
    }

    /**
     * Class constructor, creates graph and inserts nodes and edges from file,
     * specifies maximum number of nodes the graph can contain
     */
    public UndirectedGraph(final String file, final Integer numberOfNodes) {
        this(numberOfNodes);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                String[] entry = line.split(",");
                if(entry[0].equals("O")) {
                    this.insertNode(
                            Integer.parseInt(entry[1]),
                            Integer.parseInt(entry[2]),
                            Integer.parseInt(entry[3])
                    );
                }
                else {
                    this.insertEdge(
                            Integer.parseInt(entry[1]),
                            Integer.parseInt(entry[2])
                    );
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts new node into graph at the specified index,
     * doesn't overwrite if a node at the specified index already exists
     * @param index node's index
     * @param xCoordinate node's x-coordinate
     * @param yCoordinate node's y-coordinate
     * @return boolean - true, if node is inserted, false if not
     * @throws IndexOutOfBoundsException if node's index is too big for graph
     */
    public boolean insertNode(final int index, final int xCoordinate, final int yCoordinate) throws IndexOutOfBoundsException {
        if(index >= numberOfNodes) {
            throw new IndexOutOfBoundsException("Index out of bounds: This graph can't contain this many nodes!");
        }
        if(this.nodes[index]==null) {
            Node k = new Node(this, index, xCoordinate, yCoordinate);
            this.nodes[index] = k;
            return true;
        }
        return false;
    }

    /**
     * Inserts new edge into the graph between two nodes,
     * doesn't insert edge if one or both nodes don't exist yet
     * @param indexNode1 first node's index
     * @param indexNode2 second node's index
     * @return boolean - true, if edge is inserted, false if not
     * @throws IndexOutOfBoundsException if node's index is too big for graph
     */
    public boolean insertEdge(final int indexNode1, final int indexNode2) throws IndexOutOfBoundsException {
        if(indexNode1 >= numberOfNodes || indexNode2 >= numberOfNodes) {
            throw new IndexOutOfBoundsException("Index out of bounds: This graph can't contain this many nodes!");
        }
        if(this.nodes[indexNode1]!=null && this.nodes[indexNode2]!=null) {
            this.adjacencyMatrix[indexNode1][indexNode2] = true;
            this.adjacencyMatrix[indexNode2][indexNode1] = true;
            return true;
        }
        return false;
    }

    /**
     * Computes the shortest path between two nodes within the graph using the a-star-algorithm
     * @param startNodeIndex start node's index
     * @param endNodeIndex end node's index
     * @return Path object for shortest path between start and end node, empty Path object if none found
     */
    public Path computeShortestPath(final int startNodeIndex, final int endNodeIndex) {
        for(Node node: this.nodes) {
            node.setParentNode(null);
            node.setDistanceFromStart(Double.POSITIVE_INFINITY);
            node.updatefScore(Double.POSITIVE_INFINITY);
            node.setVisited(false);
            node.setNumberOfSuccessors(0);
        }

        final Node startNode = this.nodes[startNodeIndex];
        final Node endNode = this.nodes[endNodeIndex];
        startNode.setDistanceFromStart(0.0);
        startNode.updatefScore(0.0);
        startNode.setParentNode(startNode);
        SortedNodeQueue queue = new SortedNodeQueue();
        queue.insert(startNode);

        while(queue.size()>0) {
            Node currentNode = queue.remove(); // TODO - rename ?
            if(!currentNode.isVisited()) {
                currentNode.setVisited(true);

                if(currentNode.equals(endNode)) {
                    return Path.fromNodeSuccessors(endNode);
                }

                final boolean[] isNeighbor = adjacencyMatrix[currentNode.getIndex()]; // TODO - rename?? refactor ????
                for (int i=0; i<isNeighbor.length; i++) {
                    if(isNeighbor[i]) {
                        Node currentNeighbor = this.nodes[i];
                        // TODO - rename variables! refactor ?? own method ????
                        if(!currentNeighbor.isVisited()) {
                            double estimatedDistanceToEnd = Node.getDistanceBetween(currentNeighbor, endNode);
                            double distanceCurrentNeighborToParent = Node.getDistanceBetween(currentNeighbor, currentNode);
                            double distanceFromStart = currentNode.getDistanceFromStart() + distanceCurrentNeighborToParent;
                            double fScore = distanceFromStart + estimatedDistanceToEnd;
                            if(fScore < currentNeighbor.getfScore()) {
                                currentNeighbor.setParentNode(currentNode);
                                currentNeighbor.setDistanceFromStart(distanceFromStart);
                                currentNeighbor.updatefScore(estimatedDistanceToEnd);
                                queue.insert(currentNeighbor);
                            }
                        }
                    }
                }
            }
        }
        // TODO - or return null???
        return new Path(this, new Node[0]);
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
    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    /**
     * profiling graph construction from file and computing shortest path and its length
     */
    @Override
    public void run() {
        Path shortestPath = this.computeShortestPath(0, 1);
        int numberOfNodesInPath = shortestPath.getNumberOfNodes();
        if(numberOfNodesInPath>0) {
            System.out.println(shortestPath.toString());
            System.out.println("Length: " + shortestPath.getPathLength());
            System.out.println();
        }
        else {
            System.out.println("Couldn't find any path!");
            System.out.println();
        }
    }
}
