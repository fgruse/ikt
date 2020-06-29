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
     * @return Path object for shortest path between start and end node, null if none found
     */
    public Path computeShortestPath(final int startNodeIndex, final int endNodeIndex) {
        final Node[] allNodes = graph.getNodes();
        final Node endNode = allNodes[endNodeIndex];

        this.initializeStates();
        this.setStatesForNode(startNodeIndex, startNodeIndex, 0.0, 0.0);
        queue.insert(allNodes[startNodeIndex]);

        while(queue.size()>0) {
            final Node currentNode = queue.remove();
            final int currentNodeIndex = currentNode.getIndex();

            if(currentNode.equals(endNode)) {
                final ArrayList<Node> path = new ArrayList<>();
                int pointer = endNodeIndex;
                while(this.parent[pointer]!=pointer){
                    path.prepend(allNodes[pointer]);
                    pointer = this.parent[pointer];
                }
                path.prepend(allNodes[startNodeIndex]);
                return new Path(this.graph, path, this.distanceFromStart[endNodeIndex]);
            }

            if(!this.visited[currentNodeIndex]) {
                this.visited[currentNodeIndex] = true;
                final boolean[] isNeighbor = graph.getAdjacencyArrayForNode(currentNode);
                for (int neighborNodeIndex=0; neighborNodeIndex<isNeighbor.length; neighborNodeIndex++) {
                    if(isNeighbor[neighborNodeIndex]) {
                        final Node neighborNode = allNodes[neighborNodeIndex];
                        if(!this.visited[neighborNode.getIndex()]) {
                            final double distanceStartToParent = this.distanceFromStart[currentNodeIndex];
                            final double distanceNeighborToParent = neighborNode.getDistanceTo(currentNode);
                            final double distanceStartToNeighbor = distanceStartToParent + distanceNeighborToParent;
                            final double estimatedDistanceNeighborToEnd = neighborNode.getDistanceTo(endNode);
                            final double fScore = distanceStartToNeighbor + estimatedDistanceNeighborToEnd;
                            if(fScore < this.fScore[neighborNode.getIndex()]) {
                                this.setStatesForNode(neighborNodeIndex, currentNodeIndex, distanceStartToNeighbor, fScore);
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

    private void setStatesForNode(final int nodeIndex, final int parentNodeIndex,
            final double distanceStartToNeighbor, final double fScore) {
        this.parent[nodeIndex] = parentNodeIndex;
        this.distanceFromStart[nodeIndex] = distanceStartToNeighbor;
        this.fScore[nodeIndex] = fScore;
    }

    public double[] getFScore() {
        return fScore;
    }
}
