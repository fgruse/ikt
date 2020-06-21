package ue5;

public class DoublyLinkedList<T> {

    private ElementDLL<T> head;
    private ElementDLL<T> tail;

    public ElementDLL<T> getHead() {
        return head;
    }

    public ElementDLL<T> getTail() {
        return tail;
    }

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Element am Anfang einfügen
     * @param value - Element, welches eingefügt wird
     */
    public void prepend(T value) {
        ElementDLL<T> newElement = new ElementDLL<>(value);
        if(head==null) {
            head = new ElementDLL<>();
            tail = new ElementDLL<>();
            head.setNext(newElement);
            newElement.setPrev(head);
            tail.setPrev(newElement);
            newElement.setNext(tail);
        }
        else {
            ElementDLL<T> currentFirst = head.getNext();
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
    public void append(T value) {
        ElementDLL<T> newElement = new ElementDLL<>(value);
        if(head==null) {
            this.prepend(value);
        }
        else {
            ElementDLL<T> currentLast = tail.getPrev();
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
    public void insert(int index, T value) throws IndexOutOfBoundsException {
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
                    this.prepend(value);
                }
                else {
                    ElementDLL<T> newElement = new ElementDLL<>(value); // element, welches eingefügt werden soll
                    ElementDLL<T> pointer = head.getNext();

                    for(int i=0; i<index; i++) {
                        pointer = pointer.getNext(); // element, welches gerade am index ist
                    }

                    ElementDLL<T> previous = pointer.getPrev(); // element, welches gerade eins vor dem index ist
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
    public T get(int index) throws IndexOutOfBoundsException {
        T value;
        if(head==null || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else if(index==0) {
            value = head.getNext().getData();
        }
        else {
            ElementDLL<T> indexElement = head.getNext();
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
            ElementDLL<T> toDelete = head.getNext();
            for(int i=0; i<index; i++) {
                toDelete = toDelete.getNext();
            }

            ElementDLL<T> previousElement;
            ElementDLL<T> nextElement;

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
    public boolean contains(T value) throws IndexOutOfBoundsException {
        boolean contains = false;
        if(head!=null) {
            ElementDLL<T> pointer = head.getNext();
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
            ElementDLL<T> pointer = head.getNext();
            int counter = 1;
            while(pointer.getNext()!=null) {
                pointer = pointer.getNext();
                counter++;
            }
            return counter-1;
        }
    }

}
