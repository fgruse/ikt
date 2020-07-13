package astern;

public class AStar {

    private final UndirectedGraph graph;
    private final int[] parent;
    private final boolean[] visited;
    private final double[] fScore;
    private final double[] gScore;
    private final AStarNodeQueue queue;

    public AStar(final UndirectedGraph graph) {
        this.graph = graph;
        final int numberOfNodes = graph.getMaxNumberOfNodes();
        this.queue = new AStarNodeQueue(this);
        this.parent = new int[numberOfNodes];
        this.visited = new boolean[numberOfNodes];
        this.fScore = new double[numberOfNodes];
        this.gScore = new double[numberOfNodes];
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
        queue.insert(allNodes[startNodeIndex]);

        while(queue.size()>0) {
            final Node currentNode = queue.remove();
            final int currentNodeIndex = currentNode.getIndex();

            if(currentNode.equals(endNode)) {
                final NodeList path = new NodeList();
                int pointer = endNodeIndex;
                while(this.parent[pointer]!=pointer){
                    path.prepend(allNodes[pointer]);
                    pointer = this.parent[pointer];
                }
                path.prepend(allNodes[startNodeIndex]);
                return new Path(path.getNodes(), this.gScore[endNodeIndex]);
            }

            if(!this.visited[currentNodeIndex]) {
                this.visited[currentNodeIndex] = true;
                final boolean[] isNeighbor = graph.getAdjacencyArrayForNode(currentNode);
                for (int neighborNodeIndex=0; neighborNodeIndex<isNeighbor.length; neighborNodeIndex++) {
                    if(isNeighbor[neighborNodeIndex]) {
                        final Node neighborNode = allNodes[neighborNodeIndex];
                        if(!this.visited[neighborNodeIndex]) {
                            final double gScoreCurrentNode = this.gScore[currentNodeIndex];
                            final double distanceCurrentToNeighbor = neighborNode.getDistanceTo(currentNode);
                            final double gScoreNeighborNode = gScoreCurrentNode + distanceCurrentToNeighbor;
                            final double hScoreNeighborNode = neighborNode.getDistanceTo(endNode);
                            final double fScoreNeighborNode = gScoreNeighborNode + hScoreNeighborNode;
                            if(fScoreNeighborNode < this.fScore[neighborNodeIndex]) {
                                this.setStatesForNode(neighborNodeIndex, currentNodeIndex, gScoreNeighborNode, fScoreNeighborNode);
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
        for(int i=0; i<graph.getMaxNumberOfNodes(); i++) {
            this.parent[i] = -1;
            this.visited[i] = false;
            this.fScore[i] = Double.POSITIVE_INFINITY;
            this.gScore[i] = Double.POSITIVE_INFINITY;
        }
        this.queue.clear();
    }

    private void setStatesForNode(final int nodeIndex, final int parentNodeIndex,
            final double distanceStartToNeighbor, final double fScore) {
        this.parent[nodeIndex] = parentNodeIndex;
        this.gScore[nodeIndex] = distanceStartToNeighbor;
        this.fScore[nodeIndex] = fScore;
    }

    public double[] getFScore() {
        return fScore;
    }
}
