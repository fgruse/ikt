package astern;

import java.util.Arrays;

public class AStar {

    private final UndirectedGraph graph;
    private final int[] parent;
    private final boolean[] visited;
    // TODO 8 - variablen umbenennen? entweder fscore, gscore, hscore oder fscore in etwas leichter verständliches
    /*
    gScore - the cost of the cheapest path from start to n currently known == distanceFromStart
    fScore - represents our current best guess == fScore
    hScore - estimates the cost to reach goal from node n == estimatedDistanceNeighborToEnd
    */
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
    // TODO - kommentare löschen
    public Path computeShortestPath(final int startNodeIndex, final int endNodeIndex) {
        final Node[] allNodes = graph.getNodes();
        final Node endNode = allNodes[endNodeIndex];

        // vorbereitung: alle states werden zurückgesetzt
        this.initializeStates();

        /*

        Erklärung der States:

        1) knoten ist UNBEKANNT solange parent == -1, beide scores/ distanzen unendlich, nicht in queue
        --> kein weg zum knoten ist bekannt

        2) sobald scores & parent eingespeichert sind, ist der knoten BEKANNT/ IN DER QUEUE (open list)
        --> das passiert, wenn der knoten nachbar des betrachteten knoten (currentNode) ist
        --> scores werden berechnet und knoten wird wird in die queue eingefügt
        --> ein potentiell kürzester weg zum knoten ist bekannt

        3) knoten ist ABGEARBEIT/ VISITED sobald knoten aus queue genommen wurde
        --> kürzester weg zu diesem knoten ist dann bekannt (auch closed list genannt)
        --> werden nicht nocheinmal untersucht
        */

        // jeder knoten ist zu beginn des algorithmus UNBEKANNT, außer der startknoten
        // states für startknoten werden gesetzt --> scores sind 0, parent ist der knoten selbst
        this.setStatesForNode(startNodeIndex, startNodeIndex, 0.0, 0.0);
        // zu beginn des algorithmus befindet sich nur der startknoten in der queue
        queue.insert(allNodes[startNodeIndex]);

        while(queue.size()>0) {
            // knoten mit höchster priorität (geringster fScore) wird aus queue entnommen
            final Node currentNode = queue.remove();
            final int currentNodeIndex = currentNode.getIndex();

            // falls entnommener knoten == zielknoten --> fertig, weg gefunden!
            if(currentNode.equals(endNode)) {
                final ArrayList<Node> path = new ArrayList<>();
                // rückwärts durchlaufen aller parents beginnend beim endknoten um den weg zu rekonstruieren
                int pointer = endNodeIndex;
                while(this.parent[pointer]!=pointer){
                    path.prepend(allNodes[pointer]);
                    pointer = this.parent[pointer];
                }
                path.prepend(allNodes[startNodeIndex]);
                // path object wird erstellt und zurückgeben
                // länge ist die distanceFromStart des endknoten (wurde beim ermitteln des wegs aufsummiert)
                return new Path(this.graph, path, this.distanceFromStart[endNodeIndex]);
            }

            // knoten die schon abgearbeitet sind (STATE VISITED) werden nicht noch einmal betrachtet! kürzester weg bereits bekannt!
            if(!this.visited[currentNodeIndex]) {
                // knoten wird als VISITED markiert, sobald er aus der queue entnommen wurde
                this.visited[currentNodeIndex] = true;
                // auslesen der zeile für currentNode aus der adjazenzmatrix --> sagt uns welche nachbarknoten currentNode hat
                final boolean[] isNeighbor = graph.getAdjacencyArrayForNode(currentNode);
                // alle potentiellen nachbarn im isNeighbor array werden durchlaufen
                for (int neighborNodeIndex=0; neighborNodeIndex<isNeighbor.length; neighborNodeIndex++) {
                    // knoten ist nachbar von currentNode wenn True in zeile der adjazenzmatrix --> wird betrachtet
                    if(isNeighbor[neighborNodeIndex]) {
                        final Node neighborNode = allNodes[neighborNodeIndex];
                        // knoten die schon VISITED sind, werden nicht erneut zur queue hinzugefügt! kürzester weg bereits bekannt!
                        if(!this.visited[neighborNode.getIndex()]) {
                            // berechnen der scores (f,g,h)
                            final double distanceStartToParent = this.distanceFromStart[currentNodeIndex];
                            final double distanceNeighborToParent = neighborNode.getDistanceTo(currentNode);
                            final double distanceStartToNeighbor = distanceStartToParent + distanceNeighborToParent;
                            final double estimatedDistanceNeighborToEnd = neighborNode.getDistanceTo(endNode);
                            final double fScore = distanceStartToNeighbor + estimatedDistanceNeighborToEnd;
                            /*
                             wenn nachbarknoten noch nicht in queue ist (UNBEKANNT), ist fScore unendlich, also ist der berechnete fScore definitiv besser
                             --> scores & parent (currentNode) werden gespeichert & nachbarknoten wird eingefügt in queue
                             wenn der nachbarknoten sich bereits in queue befindet & neu berechneter fScore besser ist, muss er "aktualisiert" werden
                             es erfolgt aber keine tatsächliche aktualisierung, der nachbarknoten wird einfach nochmal eingefügt,
                             aber weiter vorne und dadurch wird er früher bearbeitet als der mit dem schleteren score
                             --> wenn er dann noch einmal aus der queue genommen wird, wird er übersprungen (ist schon VISITED)
                            */
                            if(fScore < this.fScore[neighborNode.getIndex()]) {
                                this.setStatesForNode(neighborNodeIndex, currentNodeIndex, distanceStartToNeighbor, fScore);
                                queue.insert(neighborNode);
                            }
                        }
                    }
                }
            }
        }
        // ziel wurde nie erreicht aber queue ist leer --> es gibt gar keinen weg
        return null;
    }

    private void initializeStates() {
        Arrays.fill(this.parent, -1); // bisher kein nachbar, da index -1 nicht im graph existieren kann
        Arrays.fill(this.visited, false); // bisher nicht bearbeitet
        Arrays.fill(this.fScore, Double.POSITIVE_INFINITY); // bisher nicht bekannt, da score unendlich groß
        Arrays.fill(this.distanceFromStart, Double.POSITIVE_INFINITY); // bisher nicht bekannt, da score unendlich groß
        this.queue = new SortedNodeQueue(this); // queue wird leer initialisiert
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
