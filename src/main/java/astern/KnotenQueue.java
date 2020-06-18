package astern;

public class KnotenQueue {

    private final KnotenListe elements;

    public KnotenQueue() {
        this.elements = new KnotenListe();
    }

    /**
     * Element einfügen, Queue ist sortiert nach geringsten Gesamtkosten
     * @param value - Element, welches eingefügt wird
     */
    public void insert(Knoten value) {
        final int size = this.elements.size();
        if(size==0) {
            this.elements.prepend(value);
        }
        else {
            int z = -1;
            for(int j=0; j<size; j++) {
                if(value.getGesamtkosten() < this.elements.get(j).getGesamtkosten()) {
                    this.elements.insert(j, value);
                    z = j;
                    break;
                }
            }
            if(z == -1) {
                this.elements.insert(size, value);
            }
        }
    }

    /**
     * Entnimmt das erste Element der Queue und entfernt es aus ihr
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >=size())
     * @return Knoten an erster Stelle der Queue
     */
    public Knoten remove() throws IndexOutOfBoundsException {
        if(this.elements.size()==0) {
            throw new IndexOutOfBoundsException("Die Queue ist leer.");
        }
        else {
            Knoten first = this.elements.get(0);
            this.elements.remove(0);
            return first;
        }
    }

    /**
     * ruft die Länge der Liste ab (Hilfsmethode)
     * @return - Länge der Liste
     */
    public int size() {
        return this.elements.size();
    }
}
