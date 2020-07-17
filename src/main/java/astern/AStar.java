package astern;

/**
 * Diese Klasse modelliert den A*-Algorithmus und stellt alle benötigten States für ihn bereit
 */

public class AStar {

    /** Graph für den der Algorithmus laufen soll */
    private final UndirectedGraph graph;
    /** Speichert die Indizes der Vorgängerknoten im Array ein  */
    private final int[] parent;
    /** Speichert ein, ob ein Knoten schon abgearbeitet wurde oder nicht  */
    private final boolean[] visited;
    /** Speichert berechnete Werte für f-Scores der Knoten  */
    private final double[] fScore;
    /** Speichert berechnete Werte für g-Scores der Knoten   */
    private final double[] gScore;
    /** Nach f-Score aufsteigend sortierte Warteschlange  */
    private final AStarNodeQueue queue;

    /**
     * Konstruktor der Klasse, welche den Graphen, für den der Algorithmus ausgeführt werden soll, spezifiziert.
     * @param graph übergibt den Graphen, für den der Algorithmus berechnet werden soll
     */

    public AStar(final UndirectedGraph graph) {
        this.graph = graph;
        final int numberOfNodes = UndirectedGraph.getMaxNumberOfNodes();
        this.queue = new AStarNodeQueue(this);
        this.parent = new int[numberOfNodes];
        this.visited = new boolean[numberOfNodes];
        this.fScore = new double[numberOfNodes];
        this.gScore = new double[numberOfNodes];
        this.initializeStates();
    }

    /**
     * Berechnet den kürzesten Weg zwischen zwei Knoten in dem Graphen mit dem A*-Algorithmus
     * @param startNodeIndex Index vom Startknoten
     * @param endNodeIndex Index vom Endknoten
     * @return Path-Objekt für kürzesten Path zwischen Start- und Endknoten, null wenn kein Weg gefunden wurde
     */
    public Path computeShortestPath(final int startNodeIndex, final int endNodeIndex) {
        final Node[] allNodes = graph.getNodes();
        final Node startNode = allNodes[startNodeIndex];
        final Node endNode = allNodes[endNodeIndex];

        this.initializeStates();
        this.setStatesForNode(startNodeIndex, startNodeIndex, 0.0, 0.0);
        queue.insert(startNode);

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
                path.prepend(startNode);
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

    /**
     * Initialisierung, setzt alle States auf Startzustand.
     */

    private void initializeStates() {
        for(int nodeIndex=0; nodeIndex<UndirectedGraph.getMaxNumberOfNodes(); nodeIndex++) {
            this.parent[nodeIndex] = -1;
            this.visited[nodeIndex] = false;
            this.fScore[nodeIndex] = Double.POSITIVE_INFINITY;
            this.gScore[nodeIndex] = Double.POSITIVE_INFINITY;
        }
        this.queue.clear();
    }

    /**
     * Setzt States für einen Knoten
     * @param nodeIndex Index von Knoten, für den die States aktualisiert werden sollen
     * @param parentNodeIndex Index des neuen Vorgängerknoten
     * @param gScore neuer g-Score
     * @param fScore neuer f-Score
     */
    private void setStatesForNode(final int nodeIndex, final int parentNodeIndex,
            final double gScore, final double fScore) {
        this.parent[nodeIndex] = parentNodeIndex;
        this.gScore[nodeIndex] = gScore;
        this.fScore[nodeIndex] = fScore;
    }

    /**
     * Gibt f-Scores aller Knoten zurück
     * @return f-Scores aller Knoten
     */
    public double[] getFScore() {
        return fScore;
    }
}
