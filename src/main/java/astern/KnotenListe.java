package astern;

public class KnotenListe {

    private Knoten[] elements;

    public KnotenListe() {
        this.elements = new Knoten[0];
    }

    /**
     * Element am Anfang einfügen
     * @param value - Element, welches eingefügt wird
     */
    public void prepend(Knoten value) {
        Knoten[] neu = new Knoten[this.size()+1];
        neu[0] = value;
        System.arraycopy(this.elements, 0, neu, 1, this.elements.length);
        this.elements = neu;
    }

    /**
     * Element am Ende einfügen
     * @param value - Element, welches eingefügt wird
     */
    public void append(Knoten value) {
        Knoten[] neu = new Knoten[this.size()+1];
        neu[this.size()] = value;
        System.arraycopy(this.elements, 0, neu, 0, this.elements.length);
        this.elements = neu;
    }

    /**
     * Element an einer angegebenen Position einfügen
     * @param value - Element, welches eingefügt wird
     * @param index - angegebene Position
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >size())
     */
    public void insert(int index, Knoten value) throws IndexOutOfBoundsException {
        if(index<0 || index > this.size()) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else {
            Knoten[] neu = new Knoten[this.size()+1];
            neu[index] = value;
            System.arraycopy(this.elements, 0, neu, 0, index);
            System.arraycopy(this.elements, index, neu, index+1, this.elements.length-index);
            this.elements = neu;
        }
    }

    /**
     * Element an einer angegebenen Position abrufen
     * @param index - angegebene Position
     * @return value - abgerufenes Element
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >=size())
     */
    public Knoten get(int index) throws IndexOutOfBoundsException {
        if(index>=this.size() || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else {
            return this.elements[index];
        }
    }

    /**
     * Element an einer angegebenen Position löschen
     * @param index - angegebene Position
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >=size())
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        if(index>=this.size() || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else {
            Knoten[] neu = new Knoten[this.size()-1];
            System.arraycopy(this.elements, 0, neu, 0, index);
            System.arraycopy(this.elements, index+1, neu, index, this.elements.length-index-1);
            this.elements = neu;
        }
    }

    /**
     * prüft, ob das übergebene Element in der Liste enthalten ist
     * @param value - Element, welches übergeben wird
     * @return contains - true, wenn Element in Liste vorhanden ist, false wenn nicht
     */
    public boolean contains(Knoten value){
        for (Knoten k: this.elements) {
            if(k.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * ruft die Länge der Liste ab (Hilfsmethode)
     * @return - Länge der Liste
     */
    public int size() {
        return this.elements.length;
    }
}
