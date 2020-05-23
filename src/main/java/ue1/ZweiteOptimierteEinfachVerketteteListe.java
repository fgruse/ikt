package ue1;

public class ZweiteOptimierteEinfachVerketteteListe {

    /*
    Optimierungsidee für verbesserte Laufzeit:
    Um zu prüfen, ob die Liste noch leer ist, wird nicht mehr überprüft, ob die Länge der Liste Null ist (size == 0),
    sondern ob der Head-Pointer bereits auf etwas zeigt (head == null). Somit spart man sich z.B. bei der
    append- und prepend-Methode die Schleife zum ermitteln der Länge komplett.
    Die get-Methode kann außerdem auch so umgeschrieben, dass sie die Länge der Liste nicht mehr benötigt,
    somit fällt eine weitere Schleife weg.
    Beim Profiling werden nur diese drei Methoden verwendet und deswegen lag mein Fokus darauf, erst einmal diese
    zu verbessern. Die gleiche Herangehensweise kann aber auch auf einige der anderen Methoden angewandt werden.
    */

    private Element head;

    public Element getHead() {
        return head;
    }

    public ZweiteOptimierteEinfachVerketteteListe() {
        head = null;
    }

    /**
     * Element am Anfang einfügen
     * @param value - Element, welches eingefügt wird
     */
    public void prepend(int value) {
        Element newFirstElement = new Element(value);
        if(head==null) {
            head = new Element();
            head.setNext(newFirstElement);
        }
        else {
            Element oldFirstElement = head.getNext();
            head.setNext(newFirstElement);
            newFirstElement.setNext(oldFirstElement);
        }
    }

    /**
     * Element am Ende einfügen
     * @param value - Element, welches eingefügt wird
     */
    public void append(int value) {
        Element newLastElement = new Element(value);
        if(head==null) {
            this.prepend(value);
        }
        else {
            Element pointer = head.getNext();
            while(pointer.getNext()!=null) {
                pointer = pointer.getNext();
            }
            pointer.setNext(newLastElement);
        }
    }

    /**
     * Element an einer angegebenen Position einfügen
     * @param value - Element, welches eingefügt wird
     * @param index - angegebene Position
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >size())
     */
    public void insert(int index, int value) throws IndexOutOfBoundsException {
        Element newElement = new Element(value);
        int size = this.size();
        if(index>size || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else {
            if(head==null) {
                this.prepend(value);
            }
            else {
                if(index==0) {
                    this.prepend(value); // einfügen an index 0 entspricht am anfang einfügen
                }
                else {
                    Element pointer = head.getNext();
                    for(int i=0; i<index-1; i++) {
                        pointer = pointer.getNext(); // element, das gerade vor dem index gespeichert ist
                    }
                    Element oldIndex = pointer.getNext(); // element, das gerade am index gespeichert ist
                    pointer.setNext(newElement);
                    newElement.setNext(oldIndex);
                }
            }
        }
    }

    /**
     * Element an einer angegebenen Position abrufen
     * @param index - angegebene Position
     * @return value - abgerufenes Element
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >=size())
     */
    public int get(int index) throws IndexOutOfBoundsException {
        int value;
        if(head==null || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else if(index==0) {
            value = head.getNext().getData();
        }
        else {
            Element indexElement = head.getNext();
            for(int i=0; i<index; i++) {
                if(indexElement.getNext()==null) {
                    throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
                }
                indexElement = indexElement.getNext();
            }
            value = indexElement.getData();
        }
        return value;
    }

    /**
     * Element an einer angegebenen Position löschen
     * @param index - angegebene Position
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >=size())
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        int size = this.size();
        if(index>=size || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else {
            Element pointer = head.getNext();
            for(int i=0; i<index-1; i++) {
                pointer = pointer.getNext(); // element, das gerade vor dem index gespeichert ist
            }

            if(index==0) { // erstes element
                Element currentSecondElement = head.getNext().getNext();
                head.setNext(currentSecondElement);
            }
            else if(index==(size-1)) { // letztes element
                pointer.setNext(null);
            }
            else { // dazwischen
                Element toDelete = pointer.getNext(); // element, das gerade am index gespeichert ist/ gelöscht werden soll
                Element afterIndex = toDelete.getNext(); // element, das gerade nach dem index gespeichert ist
                pointer.setNext(afterIndex);
            }
        }
    }

    /**
     * prüft, ob das übergebene Element in der Liste enthalten ist
     * @param value - Element, welches übergeben wird
     * @return contains - true, wenn Element in Liste vorhanden ist, false wenn nicht
     */
    public boolean contains(int value){
        int size = this.size();
        boolean contains = false;
        if(size>0) {
            Element pointer = head.getNext();
            for(int i=0; i<size; i++) {
                if(pointer.getData()==value) {
                    contains = true;
                    break;
                }
                pointer = pointer.getNext();
            }
        }
        return contains;
    }

    /**
     * ruft die Länge der Liste ab (Hilfsmethode)
     * @return - Länge der Liste
     */
    public int size() {
        if(head==null) {
            return 0;
        }
        else {
            Element pointer = head.getNext();
            int counter = 1;
            while(pointer.getNext()!=null) {
                pointer = pointer.getNext();
                counter++;
            }
            return counter;
        }
    }
}
