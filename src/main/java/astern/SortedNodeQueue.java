package astern;

// TODO 9 - umebennen oder name so gut? eigentlich spezifisch für den algorithmus? AStarQueue??
public class SortedNodeQueue {

    private final ArrayList<Node> nodes;
    // TODO 10 - queue muss astar kennen um auf die fscores zuzugreifen.. sinnvoll? besserer weg das zu lösen?
    private final AStar aStar;

    public SortedNodeQueue(final AStar aStar) {
        this.nodes = new ArrayList<>();
        this.aStar = aStar;
    }

    /**
     * Knoten einfügen, Queue ist sortiert nach geringstem fScore
     * @param node - Knoten, welcher eingefügt wird
     */
    public void insert(Node node) {
        if(this.size()==0) {
            this.nodes.prepend(node);
        }
        else {
            final double[] fScores = aStar.getFScore();
            boolean isInserted = false;
            for(int queueIndex=0; queueIndex<this.size(); queueIndex++) {
                if(fScores[node.getIndex()] < fScores[this.nodes.get(queueIndex).getIndex()]) {
                    this.nodes.insert(queueIndex, node);
                    isInserted = true;
                    break;
                }
            }
            if(!isInserted) {
                this.nodes.insert(this.size(), node);
            }
        }
    }

    /**
     * Entnimmt den ersten Knoten der Queue und entfernt es aus ihr
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >=size())
     * @return Knoten an erster Stelle der Queue
     */
    public Node remove() throws IndexOutOfBoundsException {
        // TODO 11 - überhaupt notwendig? wenn die queue nur in astar benutzt wird, kann nie auf die leere queue zugegriffen werden
        if(this.nodes.size()==0) {
            throw new IndexOutOfBoundsException("Can't remove node from empty queue.");
        }
        else {
            Node first = this.nodes.get(0);
            this.nodes.remove(0);
            return first;
        }
    }

    /**
     * ruft die Anzahl der Elemente in der Queue ab
     * @return - Länge der Queue
     */
    public int size() {
        return this.nodes.size();
    }
}
