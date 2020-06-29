package astern;

import java.util.Arrays;

public class AStar {

    private final UndirectedGraph graph;
    private final int[] parent;
    private final boolean[] visited;
    private final double[] fScore;
    private final double[] distanceFromStart;
    private SortedNodeQueue queue;

    public AStar(final UndirectedGraph graph) {
        this.graph = graph;
        final int numberOfNodes = graph.getMaxNumberOfNodes();
        this.parent = new int[numberOfNodes];
        this.visited = new boolean[numberOfNodes];
        this.fScore = new double[numberOfNodes];
        this.distanceFromStart = new double[numberOfNodes];
        this.initializeStates();
    }

    /**
     * Computes the shortest path between two nodes within the graph using the a-star-algorithm
     * @param startNodeIndex start node's index
     * @param endNodeIndex end node's index
     * @return Path object for shortest path between start and end node, empty Path object if none found
     */
    public Path computeShortestPath(final int startNodeIndex, final int endNodeIndex) {
        this.initializeStates();

        final Node[] allNodes = graph.getNodes();
        final Node startNode = allNodes[startNodeIndex];
        final Node endNode = allNodes[endNodeIndex];

        this.distanceFromStart[startNode.getIndex()] = 0.0;
        this.fScore[startNode.getIndex()] = 0.0;
        this.parent[startNodeIndex] = startNodeIndex;
        queue.insert(startNode);

        while(queue.size()>0) {
            Node currentNode = queue.remove(); // TODO - rename ?
            if(!this.visited[currentNode.getIndex()]) {
                this.visited[currentNode.getIndex()] = true;

                if(currentNode.equals(endNode)) {
                    // TODO refactor --> move to Path ???
                    ArrayList<Node> path = new ArrayList<>();
                    int pointer = endNode.getIndex();
                    while(this.parent[pointer]!=pointer){
                        path.prepend(allNodes[pointer]);
                        pointer = this.parent[pointer];
                    }
                    path.prepend(allNodes[this.parent[pointer]]);
                    return new Path(this.graph, path, this.distanceFromStart[endNode.getIndex()]);
                }

                final boolean[] isNeighbor = graph.getAdjacencyArrayForNode(currentNode);
                for (int i=0; i<isNeighbor.length; i++) {
                    if(isNeighbor[i]) {
                        Node neighborNode = allNodes[i];
                        if(!this.visited[neighborNode.getIndex()]) {
                            double distanceNeighborToParent = neighborNode.getDistanceTo(currentNode);
                            double distanceStartToParent = this.distanceFromStart[currentNode.getIndex()];
                            double distanceStartToNeighbor = distanceStartToParent + distanceNeighborToParent;
                            double estimatedDistanceNeighborToEnd = neighborNode.getDistanceTo(endNode);
                            double fScore = distanceStartToNeighbor + estimatedDistanceNeighborToEnd;
                            if(fScore < this.fScore[neighborNode.getIndex()]) {
                                this.parent[neighborNode.getIndex()] = currentNode.getIndex();
                                this.distanceFromStart[neighborNode.getIndex()] = distanceStartToNeighbor;
                                this.fScore[neighborNode.getIndex()] = estimatedDistanceNeighborToEnd + distanceStartToNeighbor;
                                queue.insert(neighborNode);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private void initializeStates() {
        Arrays.fill(this.parent, -1);
        Arrays.fill(this.visited, false);
        Arrays.fill(this.fScore, Double.POSITIVE_INFINITY);
        Arrays.fill(this.distanceFromStart, Double.POSITIVE_INFINITY);
        this.queue = new SortedNodeQueue(this);
    }

    public double[] getfScore() {
        return fScore;
    }
}
