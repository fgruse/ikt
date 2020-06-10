package ue4.versuch1;

import ue4.Liste;
import ue4.Listenelement;
import ue4.Menge;

public class DirectedGraph {

    private Menge<NodeDG> nodes;
    private Menge<EdgeDG> edges;

    public DirectedGraph() {
        this.nodes = new Menge<>();
        this.edges = new Menge<>();
    }

    /**
     * gibt eine Liste mit allen Knoten im Graphen zurück
     * @return Liste mit allen Knoten im Graphen
     */
    public Liste<NodeDG> getNodes() {
        return this.nodes.getList();
    }

    /**
     * fügt einen neuen Knoten in den Graphen ein, falls bereits ein
     * Knoten mit dem Namen vorhanden ist, passiert nichts
     * @param name - Name des Knoten
     */
    public void insertNode(String name) {
        this.nodes.add(new NodeDG(name));
    }

    /**
     * gibt den Knoten mit dem angegebenen Namen zurück
     * @return Knoten mit dem angegebenen Namen
     */
     public NodeDG getNode(String name) {
         int index = -1;
         if(this.nodes.getSize()>0) {
             Listenelement<NodeDG> pointer = this.nodes.getList().getHead().getNext();
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
      * @param from - Name des Ausgangsknoten
      * @param to - Name des Endknoten
      * @param weight - Kantengewicht
      */
     public void makeEdge(String from, String to, double weight) {
         NodeDG start = this.getNode(from);
         NodeDG end = this.getNode(to);
         if(end!=null && start!=null) {
             this.getNode(from).addChild(end);
             this.getNode(to).addParent(start);
             EdgeDG edge = new EdgeDG(weight, start, end);
             this.edges.add(edge);
         }
     }

    /**
     * gibt eine Liste mit allen Kanten im Graphen zurück
     * @return Liste mit allen Kanten im Graphen
     */
    public Liste<EdgeDG> getEdges() {
        return this.edges.getList();
    }

    /**
     * gibt das Gesamtgewicht aller Kanten des Graphen zurück
     * @return Gesamtgewicht aller Knaten des Graphen
     */
    public double getWeight() {
        double sum = 0.0;
        if(this.edges.getSize()>0) {
            Listenelement<EdgeDG> pointer = this.edges.getList().getHead();
            while(pointer.getNext()!=null) {
                pointer = pointer.getNext();
                sum += pointer.getData().getWeight();
            }
        }
        return sum;
    }
}
