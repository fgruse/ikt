package ue5;

public class SinglyLinkedList<T> {

    private ElementSLL<T> head;
    private int size;

    public ElementSLL<T> getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    /**
     * Element am Anfang einfügen
     * @param value - Element, welches eingefügt wird
     */
    public void prepend(T value) {
        ElementSLL<T> newFirstElement = new ElementSLL<>(value);
        if(size==0) {
            head = new ElementSLL<>();
            head.setNext(newFirstElement);
        }
        else {
            ElementSLL<T> oldFirstElement = head.getNext();
            head.setNext(newFirstElement);
            newFirstElement.setNext(oldFirstElement);
        }
        size++;
    }

    /**
     * Element am Ende einfügen
     * @param value - Element, welches eingefügt wird
     */
    public void append(T value) {
        ElementSLL<T> newLastElement = new ElementSLL<>(value);
        if(size==0) {
            this.prepend(value);
        }
        else {
            ElementSLL<T> pointer = head.getNext();
            for(int i=1; i<size; i++) {
                pointer = pointer.getNext(); // letztes element
            }
            pointer.setNext(newLastElement);
            size++;
        }
    }

    /**
     * Element an einer angegebenen Position einfügen
     * @param value - Element, welches eingefügt wird
     * @param index - angegebene Position
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >size())
     */
    public void insert(int index, T value) throws IndexOutOfBoundsException {
        ElementSLL<T> newElement = new ElementSLL<>(value);
        if(index>size || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else {
            if(size==0) {
                this.prepend(value);
            }
            else {
                if(index==0) {
                    this.prepend(value); // einfügen an index 0 entspricht am anfang einfügen
                }
                else {
                    ElementSLL<T> pointer = head.getNext();
                    for(int i=0; i<index-1; i++) {
                        pointer = pointer.getNext(); // element, das gerade vor dem index gespeichert ist
                    }
                    ElementSLL<T> oldIndex = pointer.getNext(); // element, das gerade am index gespeichert ist
                    pointer.setNext(newElement);
                    newElement.setNext(oldIndex);
                    size++;
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
        if(index>=size || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else if(index==0) {
            value = head.getNext().getData();
        }
        else {
            ElementSLL<T> indexElement = head.getNext();
            for(int i=0; i<index; i++) {
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
        if(index>=size || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else {
            ElementSLL<T> pointer = head.getNext();
            for(int i=0; i<index-1; i++) {
                pointer = pointer.getNext(); // element, das gerade vor dem index gespeichert ist
            }

            if(index==0) { // erstes element
                ElementSLL<T> currentSecondElement = head.getNext().getNext();
                head.setNext(currentSecondElement);
            }
            else if(index==(size-1)) { // letztes element
                pointer.setNext(null);
            }
            else { // dazwischen
                ElementSLL<T> toDelete = pointer.getNext(); // element, das gerade am index gespeichert ist/ gelöscht werden soll
                ElementSLL<T> afterIndex = toDelete.getNext(); // element, das gerade nach dem index gespeichert ist
                pointer.setNext(afterIndex);
            }
            size--;
        }
    }

    /**
     * prüft, ob das übergebene Element in der Liste enthalten ist
     * @param value - Element, welches übergeben wird
     * @return contains - true, wenn Element in Liste vorhanden ist, false wenn nicht
     */
    public boolean contains(T value){
        boolean contains = false;
        if(size>0) {
            ElementSLL<T> pointer = head.getNext();
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
}
