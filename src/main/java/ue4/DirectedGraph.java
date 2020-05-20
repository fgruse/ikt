package ue4;

public class DirectedGraph {

    private Menge<Node> nodes;
    private Menge<Edge> edges;

    public DirectedGraph() {
        this.nodes = new Menge<>();
        this.edges = new Menge<>();
    }

    /**
     * gibt eine Liste mit allen Knoten im Graphen zurück
     * @return Liste mit allen Knoten im Graphen
     */
    public Liste<Node> getNodes() {
        return this.nodes.getList();
    }

    /**
     * fügt einen neuen Knoten in den Graphen ein, falls bereits ein
     * Knoten mit dem Namen vorhanden ist, passiert nichts
     * @param name - Name des Knoten
     */
    public void insertNode(String name) {
        this.nodes.add(new Node(name));
    }

    /**
     * gibt den Knoten mit dem angegebenen Namen zurück
     * @return Knoten mit dem angegebenen Namen
     */
     public Node getNode(String name) {
         int index = -1;
         if(this.nodes.getSize()>0) {
             Listenelement<Node> pointer = this.nodes.getList().getHead().getNext();
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
         Node start = this.getNode(from);
         Node end = this.getNode(to);
         if(end!=null && start!=null) {
             this.getNode(from).addChild(end);
             this.getNode(to).addParent(start);
             Edge edge = new Edge(weight, start, end);
             this.edges.add(edge);
         }
     }

    /**
     * gibt eine Liste mit allen Kanten im Graphen zurück
     * @return Liste mit allen Kanten im Graphen
     */
    public Liste<Edge> getEdges() {
        return this.edges.getList();
    }

    /**
     * gibt das Gesamtgewicht aller Kanten des Graphen zurück
     * @return Gesamtgewicht aller Knaten des Graphen
     */
    public double getWeight() {
        double sum = 0.0;
        if(this.edges.getSize()>0) {
            Listenelement<Edge> pointer = this.edges.getList().getHead();
            while(pointer.getNext()!=null) {
                pointer = pointer.getNext();
                sum += pointer.getData().getWeight();
            }
        }
        return sum;
    }
}
