package ue2;

public class doppeltVerketteteListe {

    private Element head;
    private Element tail;

    public Element getHead() {
        return head;
    }

    public Element getTail() {
        return tail;
    }

    public doppeltVerketteteListe() {
        head = null;
        tail = null;
    }


    /**
     * Element am Anfang einfügen
     * @param value - Element, welches eingefügt wird
     */
    public void prepend(int value) {
        Element newElement = new Element(value);
        if(head==null) {
            head = new Element();
            tail = new Element();
            head.setNext(newElement);
            newElement.setPrev(head);
            tail.setPrev(newElement);
            newElement.setNext(tail);
        }
        else {
            Element currentFirst = head.getNext();
            head.setNext(newElement);
            newElement.setNext(currentFirst);
            newElement.setPrev(head);
            currentFirst.setPrev(newElement);
        }
    }

    /**
     * Element am Ende einfügen
     * @param value - Element, welches eingefügt wird
     */
    public void append(int value) {
        Element newElement = new Element(value);
        if(head==null) {
            this.prepend(value);
        }
        else {
            Element currentLast = tail.getPrev();
            tail.setPrev(newElement);
            newElement.setPrev(currentLast);
            newElement.setNext(tail);
            currentLast.setNext(newElement);
        }
    }

    /**
     * Element an einer angegebenen Position einfügen
     * @param value - Element, welches eingefügt wird
     * @param index - angegebene Position
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >size())
     */
    public void insert(int index, int value) throws IndexOutOfBoundsException {
        int size = this.size();
        if(index>size || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else {
            if(head==null) {
                this.prepend(value);
            }
            else {
                Element newElement = new Element(value); // element, welches eingefügt werden soll
                Element pointer = head.getNext();

                for(int i=0; i<index; i++) {
                    pointer = pointer.getNext(); // element, welches gerade am index ist
                }

                if(index==0) {
                    this.prepend(value);
                }
                else {
                    Element previous = pointer.getPrev(); // element, welches gerade eins vor dem index ist
                    newElement.setPrev(previous);
                    newElement.setNext(pointer);
                    previous.setNext(newElement);
                    pointer.setPrev(newElement);
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
            Element toDelete = head.getNext();
            for(int i=0; i<index; i++) {
                toDelete = toDelete.getNext();
            }

            Element previousElement;
            Element nextElement;

            // letztes element löschen
            if(index==(size-1)) {
                previousElement = toDelete.getPrev();
                this.getTail().setPrev(previousElement);
                previousElement.setNext(tail);
            }
            else if(index==0) {
                nextElement = toDelete.getNext();
                this.getHead().setNext(nextElement);
                nextElement.setPrev(head);
            }
            else {
                previousElement = toDelete.getPrev();
                nextElement = toDelete.getNext();
                previousElement.setNext(nextElement);
                nextElement.setPrev(previousElement);
            }

            toDelete.setPrev(null);
            toDelete.setNext(null);
        }

    }

    /**
     * prüft, ob das übergebene Element in der Liste enthalten ist
     * @param value - Element, welches übergeben wird
     * @return contains - true, wenn Element in Liste vorhanden ist, false wenn nicht
     */
    public boolean contains(int value) throws IndexOutOfBoundsException {
        boolean contains = false;
        if(head!=null) {
            Element pointer = head.getNext();
            while(pointer.getNext()!=null) {
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
            return counter-1;
        }
    }

}
