package astern;

import java.util.Arrays;

// TODO 2 - Generics hier überhaupt sinnvoll? Wird momentan nur für Node benutzt

public class ArrayList<E> {

    private E[] elements;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.elements = (E[]) new Object[0];
    }

    /**
     * Element am Anfang einfügen
     * @param element - Element, welches eingefügt wird
     */
    @SuppressWarnings("unchecked")
    public void prepend(final E element) {
        E[] newArray = (E[]) new Object[this.size()+1];
        newArray[0] = element;
        System.arraycopy(this.elements, 0, newArray, 1, this.elements.length);
        this.elements = newArray;
    }

    /**
     * Element am Ende einfügen
     * @param element - Element, welches eingefügt wird
     */
    @SuppressWarnings("unchecked")
    public void append(final E element) {
        E[] newArray = (E[]) new Object[this.size()+1];
        newArray[this.size()] = element;
        System.arraycopy(this.elements, 0, newArray, 0, this.elements.length);
        this.elements = newArray;
    }

    /**
     * Element an einer angegebenen Position einfügen
     * @param element - Element, welches eingefügt wird
     * @param index - angegebene Position
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >size())
     */
    @SuppressWarnings("unchecked")
    public void insert(final int index, final E element) throws IndexOutOfBoundsException {
        if(index<0 || index > this.size()) {
            throw new IndexOutOfBoundsException("There is no element at index " + index + ".");
        }
        else {
            E[] newArray = (E[]) new Object[this.size()+1];
            newArray[index] = element;
            System.arraycopy(this.elements, 0, newArray, 0, index);
            System.arraycopy(this.elements, index, newArray, index+1, this.elements.length-index);
            this.elements = newArray;
        }
    }

    /**
     * Element an einer angegebenen Position abrufen
     * @param index - angegebene Position
     * @return value - abgerufenes Element
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >=size())
     */
    public E get(final int index) throws IndexOutOfBoundsException {
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
    public void remove(final int index) throws IndexOutOfBoundsException {
        if(index>=this.size() || index<0) {
            throw new IndexOutOfBoundsException("There is no element at index " + index + ".");
        }
        else {
            E[] newArray = (E[]) new Object[this.size()-1];
            System.arraycopy(this.elements, 0, newArray, 0, index);
            System.arraycopy(this.elements, index+1, newArray, index, this.elements.length-index-1);
            this.elements = newArray;
        }
    }

    /**
     * prüft, ob das übergebene Element in der Liste enthalten ist
     * @param element - Element, welches übergeben wird
     * @return contains - true, wenn Element in Liste vorhanden ist, false wenn nicht
     */
    public boolean contains(final E element){
        for (E el: this.elements) {
            if(el==element) {
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
