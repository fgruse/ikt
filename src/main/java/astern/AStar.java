package astern;

import java.util.Arrays;

public class AStar {

    private final UndirectedGraph graph;
    private final int[] parents;
    private final boolean[] visited;
    private final double[] fScores;
    private final double[] distancesFromStart;

    public AStar(final UndirectedGraph graph) {
        this.graph = graph;
        final int numberOfNodes = graph.getNumberOfNodes();
        this.parents = new int[numberOfNodes];
        Arrays.fill(this.parents, -1);
        this.visited = new boolean[numberOfNodes];
        Arrays.fill(this.visited, false);
        this.fScores = new double[numberOfNodes];
        Arrays.fill(this.fScores, Double.POSITIVE_INFINITY);
        this.distancesFromStart = new double[numberOfNodes];
        Arrays.fill(this.distancesFromStart, Double.POSITIVE_INFINITY);
    }

    /**
     * Computes the shortest path between two nodes within the graph using the a-star-algorithm
     * @param startNodeIndex start node's index
     * @param endNodeIndex end node's index
     * @return Path object for shortest path between start and end node, empty Path object if none found
     */
    public Path computeShortestPath(final int startNodeIndex, final int endNodeIndex) {
        this.resetStates();

        final Node[] allNodes = graph.getNodes();
        final Node startNode = allNodes[startNodeIndex];
        final Node endNode = allNodes[endNodeIndex];
        this.distancesFromStart[startNode.getIndex()] = 0.0;
        this.fScores[startNode.getIndex()] = 0.0;
        this.parents[startNodeIndex] = startNodeIndex;
        SortedNodeQueue queue = new SortedNodeQueue(this);
        queue.insert(startNode);

        while(queue.size()>0) {
            Node currentNode = queue.remove(); // TODO - rename ?
            if(!this.visited[currentNode.getIndex()]) {
                this.visited[currentNode.getIndex()] = true;

                if(currentNode.equals(endNode)) {
                    // TODO refactor --> move to Path ???
                    ArrayList<Node> path = new ArrayList<>();
                    int pointer = endNode.getIndex();
                    while(this.parents[pointer]!=pointer){
                        path.prepend(allNodes[pointer]);
                        pointer = this.parents[pointer];
                    }
                    path.prepend(allNodes[this.parents[pointer]]);
                    return new Path(this.graph, path, this.distancesFromStart[endNode.getIndex()]);
                }

                final boolean[] isNeighbor = graph.getAdjacencyMatrix()[currentNode.getIndex()]; // TODO - rename?? refactor ????
                for (int i=0; i<isNeighbor.length; i++) {
                    if(isNeighbor[i]) {
                        Node currentNeighbor = allNodes[i];
                        // TODO - rename variables! refactor ?? own method ????
                        if(!this.visited[currentNeighbor.getIndex()]) {
                            double estimatedDistanceToEnd = Node.getDistanceBetween(currentNeighbor, endNode);
                            double distanceCurrentNeighborToParent = Node.getDistanceBetween(currentNeighbor, currentNode);
                            double distanceFromStart = this.distancesFromStart[currentNode.getIndex()] + distanceCurrentNeighborToParent;
                            double fScore = distanceFromStart + estimatedDistanceToEnd;
                            if(fScore < this.fScores[currentNeighbor.getIndex()]) {
                                this.parents[currentNeighbor.getIndex()] = currentNode.getIndex();
                               this.distancesFromStart[currentNeighbor.getIndex()] = distanceFromStart;
                                this.fScores[currentNeighbor.getIndex()] = estimatedDistanceToEnd + this.distancesFromStart[currentNeighbor.getIndex()];
                                queue.insert(currentNeighbor);
                            }
                        }
                    }
                }
            }
        }
        // TODO - or return null???
        return new Path(graph, new ArrayList<>(), 0.0);
    }

    private void resetStates() {
        Arrays.fill(this.parents, -1);
        Arrays.fill(this.visited, false);
        Arrays.fill(this.fScores, Double.POSITIVE_INFINITY);
        Arrays.fill(this.distancesFromStart, Double.POSITIVE_INFINITY);
    }

    public double[] getfScores() {
        return fScores;
    }
}
