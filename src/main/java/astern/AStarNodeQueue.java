package astern;

/**
 * Diese Klasse modelliert eine sortierte Warteschlange, welche für die Ausführung des A-Stern-Algorithmus gebraucht wird.
 */
public class AStarNodeQueue {

    /** Aufsteigend nach f-Score sortierte Liste von Knoten */
    private final NodeList nodes;
    /** AStar-Algorithmus-Objekt, gewährt Zugriff auf f-Scores zum Sortieren */
    private final AStar aStar;

    /**
     * Konstruktor der Klasse, spezifiziert zugehöriges AStar-Algorithmus-Objekt
     * @param aStar AStar-Algorithmus-Objekt, auf dessen f-Scores zugegriffen werden muss
     */
    public AStarNodeQueue(final AStar aStar) {
        this.nodes = new NodeList();
        this.aStar = aStar;
    }

    /**
     * Fügt einen Knoten aufsteigend nach f-Score sortiert in die Warteschlange ein
     * @param node Knoten, welcher eingefügt wird
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
     * Entnimmt den ersten Knoten der Warteschlange und entfernt ihn aus ihr
     * @throws IndexOutOfBoundsException wenn die Warteschlange leer ist (size()==0)
     * @return Knoten, der an erster Stelle der Warteschlange stand
     */
    public Node remove() throws IndexOutOfBoundsException {
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
     * Ruft die Anzahl der Knoten, die sich in der Warteschlange befinden, ab
     * @return Anzahl der Knoten in der Warteschlange
     */
    public int size() {
        return this.nodes.size();
    }

    /**
     * Entfert alle Knoten aus der Warteschlange
     */
    public void clear() {
        this.nodes.clear();
    }

}
