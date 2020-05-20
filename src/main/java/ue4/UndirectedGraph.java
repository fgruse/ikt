package ue4;

import ue3.FIWHashMap;

public class UndirectedGraph {

    private Menge<NodeUndirectedGraph> nodes;
    private Menge<EdgeUndirectedGraph> edges;

    public UndirectedGraph() {
        this.nodes = new Menge<>();
        this.edges = new Menge<>();
    }

    /**
     * gibt eine Liste mit allen Knoten im Graphen zurück
     * @return Liste mit allen Knoten im Graphen
     */
    public Liste<NodeUndirectedGraph> getNodes() {
        return this.nodes.getList();
    }

    /**
     * fügt einen neuen Knoten in den Graphen ein, falls bereits ein
     * Knoten mit dem Namen vorhanden ist, passiert nichts
     * @param name - Name des Knoten
     */
    public void insertNode(String name) {
        this.nodes.add(new NodeUndirectedGraph(name));
    }

    /**
     * gibt den Knoten mit dem angegebenen Namen zurück
     * @return Knoten mit dem angegebenen Namen
     */
    public NodeUndirectedGraph getNode(String name) {
        int index = -1;
        if(this.nodes.getSize()>0) {
            Listenelement<NodeUndirectedGraph> pointer = this.nodes.getList().getHead().getNext();
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
        NodeUndirectedGraph n1 = this.getNode(node1);
        NodeUndirectedGraph n2 = this.getNode(node2);
        if(n2!=null && n1!=null) {
            this.getNode(node1).addEdge(n2, weight);
            this.getNode(node2).addEdge(n1, weight);
            EdgeUndirectedGraph edge = new EdgeUndirectedGraph(weight, n1, n2);
            this.edges.add(edge);
        }
    }

    /**
     * gibt eine Liste mit allen Kanten im Graphen zurück
     * @return Liste mit allen Kanten im Graphen
     */
    public Liste<EdgeUndirectedGraph> getEdges() {
        return this.edges.getList();
    }

    /**
     * gibt das Gesamtgewicht aller Kanten des Graphen zurück
     * @return Gesamtgewicht aller Knaten des Graphen
     */
    public double getWeight() {
        double sum = 0.0;
        if(this.edges.getSize()>0) {
            Listenelement<EdgeUndirectedGraph> pointer = this.edges.getList().getHead();
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
        Menge<NodeUndirectedGraph> visitedNodes = new Menge<>();
        Menge<EdgeUndirectedGraph> addedEdges = new Menge<>();

        // bilden des spannbaums nur möglich wenn graph knoten enthält
        if(this.getNodes().getSize()>0){
            // gewählten erster Knoten --> Wurzel des DMST
            final NodeUndirectedGraph root = this.getNodes().get(0);

            // erster Knoten ist besucht
            spanningTree.insertNode(root.getName());
            visitedNodes.add(root);

            // bilden des Spannbaums solange noch nicht alle Knoten besucht sind
            while(spanningTree.getNodes().getSize()!=this.nodes.getSize()) {
                System.out.println(iteration);
                iteration++;
                // erfassen aller kanten von allen besuchten knoten, welche noch nicht im spannbaum sind
                Menge<EdgeUndirectedGraph> lowestWeightEdges = new Menge<>();
                for(int i=0; i<visitedNodes.getSize(); i++) {
                    NodeUndirectedGraph currentVisitedNode = visitedNodes.getList().get(i);
                    Menge<EdgeUndirectedGraph> notYetAddedEdges = new Menge<>();
                    for(int j=0; j<this.edges.getSize(); j++) {
                        EdgeUndirectedGraph current = this.edges.getList().get(j);
                        // es werden nur edges betrachtet, die den derzeitig betrachteten besuchten knoten (currentVisitedNode) beinhalten
                        if(current.getNode1().equals(currentVisitedNode) || current.getNode2().equals(currentVisitedNode)) {
                            // er werden nur edges hinzugefügt, wenn sie sich noch nicht im spannbaum befinden
                            if(!addedEdges.contains(current)) {
                                lowestWeightEdges.add(current);
                            }
                        }
                    }
                }
                // finden der kante mit kleinstem gewicht
                EdgeUndirectedGraph lowestWeight = lowestWeightEdges.getList().get(0);
                for(int i=1; i<lowestWeightEdges.getSize(); i++) {
                    final EdgeUndirectedGraph currentEdge = lowestWeightEdges.getList().get(i);
                    if(lowestWeight.getWeight()> currentEdge.getWeight()) {
                        lowestWeight = currentEdge;
                    }
                }
                // knoten aus neuer kante extrahieren
                Node node1 = new Node(lowestWeight.getNode1().getName());
                Node node2 = new Node(lowestWeight.getNode2().getName());
                // neuen knoten + entsprechende kante ermitteln & einfügen
                if(spanningTree.getNodes().contains(node1)) {
                    spanningTree.insertNode(node2.getName());
                    spanningTree.makeEdge(node1.getName(), node2.getName(), lowestWeight.getWeight());
                    addedEdges.add(lowestWeight);
                    visitedNodes.add(lowestWeight.getNode2());
                }
                else {
                    spanningTree.insertNode(node1.getName());
                    spanningTree.makeEdge(node2.getName(), node1.getName(), lowestWeight.getWeight());
                    addedEdges.add(lowestWeight);
                    visitedNodes.add(lowestWeight.getNode1());
                }
            }
        }
        return spanningTree;
    }
}
