package ue4.versuch1;

import ue4.Liste;
import ue4.Listenelement;
import ue4.Menge;

public class UndirectedGraph {

    private Menge<NodeUG> nodes;
    private Menge<EdgeUG> edges;

    public UndirectedGraph() {
        this.nodes = new Menge<>();
        this.edges = new Menge<>();
    }

    /**
     * gibt eine Liste mit allen Knoten im Graphen zurück
     * @return Liste mit allen Knoten im Graphen
     */
    public Liste<NodeUG> getNodes() {
        return this.nodes.getList();
    }

    /**
     * fügt einen neuen Knoten in den Graphen ein, falls bereits ein
     * Knoten mit dem Namen vorhanden ist, passiert nichts
     * @param name - Name des Knoten
     */
    public void insertNode(String name) {
        this.nodes.add(new NodeUG(name));
    }

    /**
     * gibt den Knoten mit dem angegebenen Namen zurück
     * @return Knoten mit dem angegebenen Namen
     */
    public NodeUG getNode(String name) {
        int index = -1;
        if(this.nodes.getSize()>0) {
            Listenelement<NodeUG> pointer = this.nodes.getList().getHead().getNext();
            for(int i=0; i<this.nodes.getSize(); i++) {
                if(pointer.getData().getName().equals(name)) {
                    index = i;
                    break;
                }
                pointer = pointer.getNext();
            }
        }
        if(index!=-1) {
            return this.nodes.getList().get(index);
        }
        return null;
    }

    /**
     * erzeugt eine Kante im Graphen, es passiert nichts, wenn die Knoten
     * noch nicht im Graphen vorhanden sind
     * @param node1 - Name des ersten Knoten
     * @param node2 - Name des zweiten Knoten
     * @param weight - Kantengewicht
     */
    public void makeEdge(String node1, String node2, double weight) {
        NodeUG n1 = this.getNode(node1);
        NodeUG n2 = this.getNode(node2);
        if(n2!=null && n1!=null) {
            this.getNode(node1).addEdge(n2, weight);
            this.getNode(node2).addEdge(n1, weight);
            EdgeUG edge = new EdgeUG(weight, n1, n2);
            this.edges.add(edge);
        }
    }

    /**
     * gibt eine Liste mit allen Kanten im Graphen zurück
     * @return Liste mit allen Kanten im Graphen
     */
    public Liste<EdgeUG> getEdges() {
        return this.edges.getList();
    }

    /**
     * gibt das Gesamtgewicht aller Kanten des Graphen zurück
     * @return Gesamtgewicht aller Knaten des Graphen
     */
    public double getWeight() {
        double sum = 0.0;
        if(this.edges.getSize()>0) {
            Listenelement<EdgeUG> pointer = this.edges.getList().getHead();
            while(pointer.getNext()!=null) {
                pointer = pointer.getNext();
                sum += pointer.getData().getWeight();
            }
        }
        return sum;
    }

    /**
     * gibt den minimalen Spannbaum des Graphen zurück (prim)
     * @return minimaler Spannbaum in Form eines gerichteten Graphen
     */
    public DirectedGraph getMinimalSpanningTree() {
        int iteration = 0;
        DirectedGraph spanningTree = new DirectedGraph();
        Menge<NodeUG> visitedNodes = new Menge<>();
        Menge<EdgeUG> addedEdges = new Menge<>();

        // bilden des spannbaums nur möglich wenn graph knoten enthält
        if(this.getNodes().getSize()>0){
            // gewählten erster Knoten --> Wurzel des DMST
            final NodeUG root = this.getNodes().get(0);

            // erster Knoten ist besucht
            spanningTree.insertNode(root.getName());
            visitedNodes.add(root);

            // bilden des Spannbaums solange noch nicht alle Knoten besucht sind
            while(spanningTree.getNodes().getSize()!=this.nodes.getSize()) {
                System.out.println(iteration);
                iteration++;
                // finden der kante, die von einem bereits besuchten knoten ausgeht, mit dem geringsten gewicht
                EdgeUG lowestWeight = new EdgeUG(Double.MAX_VALUE, null, null);
                for(int i=0; i<visitedNodes.getSize(); i++) {
                    NodeUG currentVisitedNode = visitedNodes.getList().get(i);
                    for(int j=0; j<currentVisitedNode.getEdges().getSize(); j++) {
                        EdgeUG current = currentVisitedNode.getEdges().get(j);
                        // er werden nur edges beachtet, die sich noch nicht im spannbaum befinden
                        if(!addedEdges.contains(current) && (visitedNodes.contains(current.getNode1()) ^ visitedNodes.contains(current.getNode2()))) {
                            if(lowestWeight.getWeight()>current.getWeight()) {
                                lowestWeight = current;
                            }
                        }
                    }
                }
                // knoten aus neuer kante extrahieren
                spanningTree.insertNode(lowestWeight.getNode2().getName());
                spanningTree.makeEdge(lowestWeight.getNode1().getName(), lowestWeight.getNode2().getName(), lowestWeight.getWeight());
                addedEdges.add(lowestWeight);
                visitedNodes.add(lowestWeight.getNode2());
            }
        }
        return spanningTree;
    }
}
