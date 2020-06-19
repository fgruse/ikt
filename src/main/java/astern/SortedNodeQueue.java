package astern;

public class SortedNodeQueue {

    private final ArrayList<Node> nodes;

    public SortedNodeQueue() {
        this.nodes = new ArrayList<>();
    }

    // TODO - variablen umbennenen!
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
            for(int j=0; j<size; j++) {
                if(node.getfScore() < this.nodes.get(j).getfScore()) {
                    this.nodes.insert(j, node);
                    z = j;
                    break;
                }
            }
            if(z == -1) {
                this.nodes.insert(size, node);
            }
            //this.elements.insert(index, node);
            /*int index = binarySearch(node);
            if(z!=index) {
                System.out.println(z + " " + index);
            }*/
        }
    }

    // TODO - fix ??
    public int binarySearch(Node node) {
        double kosten = node.getfScore();
        int left = 0;
        int right = this.nodes.size()-1;
        int center = (left+right)/2;

        while(left<right && !this.nodes.get(center).equals(node)){
            if(kosten<this.nodes.get(center).getfScore()) {
                right = center-1;
            }
            else {
                left = center+1;
            }
            center = (left+right)/2;
        }

        if(kosten<this.nodes.get(center).getfScore()) {
            return center;
        }
        else {
            return center+1;
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
