package astern;

public class SortedNodeQueue {

    private final ArrayList<Node> nodes;
    private final AStar aStar;

    public SortedNodeQueue(final AStar aStar) {
        this.nodes = new ArrayList<>();
        this.aStar = aStar;
    }

    /**
     * Element einfügen, Queue ist sortiert nach geringsten Gesamtkosten
     * @param node - Element, welches eingefügt wird
     */
    public void insert(Node node) {
        final int size = this.nodes.size();
        if(size==0) {
            this.nodes.prepend(node);
        }
        else {
            int z = -1;
            final double[] fScores = aStar.getfScore();
            for(int j=0; j<size; j++) {
                if(fScores[node.getIndex()] < fScores[this.nodes.get(j).getIndex()]) {
                    this.nodes.insert(j, node);
                    z = j;
                    break;
                }
            }
            if(z == -1) {
                this.nodes.insert(size, node);
            }
        }
    }

    /**
     * Entnimmt das erste Element der Queue und entfernt es aus ihr
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >=size())
     * @return Knoten an erster Stelle der Queue
     */
    public Node remove() throws IndexOutOfBoundsException {
        if(this.nodes.size()==0) {
            throw new IndexOutOfBoundsException("Die Queue ist leer.");
        }
        else {
            Node first = this.nodes.get(0);
            this.nodes.remove(0);
            return first;
        }
    }

    /**
     * ruft die Länge der Liste ab (Hilfsmethode)
     * @return - Länge der Liste
     */
    public int size() {
        return this.nodes.size();
    }
}
