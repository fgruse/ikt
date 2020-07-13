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
        for(int i=1; i<newArray.length;i++) {
             newArray[i] = this.nodes[i-1];
        }
        this.nodes = newArray;
    }

    /**
     * Knoten am Ende einfügen
     * @param node - Knoten, welcher eingefügt wird
     */
    public void append(final Node node) {
        final Node[] newArray = new Node[this.size()+1];
        newArray[this.size()] = node;
        for(int i=0; i<newArray.length-1;i++) {
            newArray[i] = this.nodes[i];
        }
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
            if(index==0) {
                this.prepend(node);
            }
            else if(index==this.size()) {
                this.append(node);
            }
            else {
                final Node[] newArray = new Node[this.size()+1];

                for(int i=0; i<index;i++) {
                    newArray[i] = this.nodes[i];
                }
                newArray[index] = node;
                for(int i=index+1; i<newArray.length;i++) {
                    newArray[i] = this.nodes[i-1];
                }
                this.nodes = newArray;
            }
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
            throw new IndexOutOfBoundsException("There is no node at index " + index + ".");
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
            if(index==0) {
                for(int i=1; i<=newArray.length; i++) {
                    newArray[i-1] = this.nodes[i];
                }
            }
            else if(index==this.size()-1) {
                for(int i=0; i<newArray.length;i++) {
                    newArray[i] = this.nodes[i];
                }
            }
            else {
                for(int i=0; i<index;i++) {
                    newArray[i] = this.nodes[i];
                }
                for(int i=index+1; i<=newArray.length;i++) {
                    newArray[i-1] = this.nodes[i];
                }
            }
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
            if(nodeInList.equals(node)) {
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
