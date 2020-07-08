package astern;

import java.util.Arrays;

public class NodeList {

    private Node[] nodes;

    public NodeList() {
        this.nodes = new Node[0];
    }

    /**
     * Knoten am Anfang einfügen
     * @param node - Knoten, welches eingefügt wird
     */
    public void prepend(final Node node) {
        final Node[] newArray = new Node[this.size()+1];
        newArray[0] = node;
        System.arraycopy(this.nodes, 0, newArray, 1, this.nodes.length);
        this.nodes = newArray;
    }

    /**
     * Knoten am Ende einfügen
     * @param node - Knoten, welcher eingefügt wird
     */
    public void append(final Node node) {
        final Node[] newArray = new Node[this.size()+1];
        newArray[this.size()] = node;
        System.arraycopy(this.nodes, 0, newArray, 0, this.nodes.length);
        this.nodes = newArray;
    }

    /**
     * Knotne an einer angegebenen Position einfügen
     * @param node - Knoten, welcher eingefügt wird
     * @param index - angegebene Position
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >size())
     */
    public void insert(final int index, final Node node) throws IndexOutOfBoundsException {
        if(index<0 || index > this.size()) {
            throw new IndexOutOfBoundsException("There is no node at index " + index + ".");
        }
        else {
            final Node[] newArray = new Node[this.size()+1];
            newArray[index] = node;
            System.arraycopy(this.nodes, 0, newArray, 0, index);
            System.arraycopy(this.nodes, index, newArray, index+1, this.nodes.length-index);
            this.nodes = newArray;
        }
    }

    /**
     * Knoten an einer angegebenen Position abrufen
     * @param index - angegebene Position
     * @return value - abgerufener Knoten
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >=size())
     */
    public Node get(final int index) throws IndexOutOfBoundsException {
        if(index>=this.size() || index<0) {
            throw new IndexOutOfBoundsException("here is no node at index " + index + ".");
        }
        else {
            return this.nodes[index];
        }
    }

    /**
     * Knoten an einer angegebenen Position löschen
     * @param index - angegebene Position
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >=size())
     */
    public void remove(final int index) throws IndexOutOfBoundsException {
        if(index>=this.size() || index<0) {
            throw new IndexOutOfBoundsException("There is no node at index " + index + ".");
        }
        else {
            final Node[] newArray = new Node[this.size()-1];
            System.arraycopy(this.nodes, 0, newArray, 0, index);
            System.arraycopy(this.nodes, index+1, newArray, index, this.nodes.length-index-1);
            this.nodes = newArray;
        }
    }

    /**
     * prüft, ob dere übergebene Knoten in der Liste enthalten ist
     * @param node - Knoten, welcher übergeben wird
     * @return contains - true, wenn Element in Liste vorhanden ist, false wenn nicht
     */
    public boolean contains(final Node node){

        for (final Node nodeInList: this.nodes) {
            if(nodeInList==node) {
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
        return this.nodes.length;
    }

    /**
     * entfernt alle Knoten aus der liste
     */
    public void clear() {
        this.nodes = new Node[0];
    }

    @Override
    public String toString() {
        return Arrays.toString(this.nodes);
    }

    public Node[] getNodes() {
        return nodes;
    }
}
