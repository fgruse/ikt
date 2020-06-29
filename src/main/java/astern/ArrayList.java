package astern;

import java.util.Arrays;

public class ArrayList<E> {

    private E[] elements;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.elements = (E[]) new Object[0];
    }

    /**
     * Element am Anfang einfügen
     * @param value - Element, welches eingefügt wird
     */
    @SuppressWarnings("unchecked")
    public void prepend(E value) {
        E[] neu = (E[]) new Object[this.size()+1];
        neu[0] = value;
        System.arraycopy(this.elements, 0, neu, 1, this.elements.length);
        this.elements = neu;
    }

    /**
     * Element am Ende einfügen
     * @param value - Element, welches eingefügt wird
     */
    @SuppressWarnings("unchecked")
    public void append(E value) {
        E[] neu = (E[]) new Object[this.size()+1];
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
    @SuppressWarnings("unchecked")
    public void insert(int index, E value) throws IndexOutOfBoundsException {
        if(index<0 || index > this.size()) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else {
            E[] neu = (E[]) new Object[this.size()+1];
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
    public E get(int index) throws IndexOutOfBoundsException {
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
    @SuppressWarnings("unchecked")
    public void remove(int index) throws IndexOutOfBoundsException {
        if(index>=this.size() || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else {
            E[] neu = (E[]) new Object[this.size()-1];
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
    public boolean contains(E value){
        for (E i: this.elements) {
            if(i==value) {
                return true;
            }
        }
        return false;
    }

    /**
     * ruft die Länge der Liste ab
     * @return - Länge der Liste
     */
    public int size() {
        return this.elements.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}
