package astern;

import java.util.Arrays;

/**
 * Diese Klasse ist eine nicht-generische ArrayList, welche Knoten speichern kann.
 */
public class NodeList {

    /** Array, welches Knoten enthält */
    private Node[] nodes;

    /**
     * Konstruktor der Klasse, erzeugt zunächst leeres Knoten-Array
     */
    public NodeList() {
        this.nodes = new Node[0];
    }

    /**
     * Fügt Knoten am Anfang ein
     * @param node Knoten, welcher eingefügt wird
     */
    public void prepend(final Node node) {
        final Node[] newArray = new Node[this.size()+1];
        newArray[0] = node;
        for(int nodeIndex=1; nodeIndex<newArray.length;nodeIndex++) {
             newArray[nodeIndex] = this.nodes[nodeIndex-1];
        }
        this.nodes = newArray;
    }

    /**
     * Fügt Knoten am Ende ein
     * @param node Knoten, welcher eingefügt wird
     */
    public void append(final Node node) {
        final Node[] newArray = new Node[this.size()+1];
        newArray[this.size()] = node;
        for(int nodeIndex=0; nodeIndex<newArray.length-1;nodeIndex++) {
            newArray[nodeIndex] = this.nodes[nodeIndex];
        }
        this.nodes = newArray;
    }

    /**
     * Fügt Knoten an einer angegebenen Position ein
     * @param node Knoten, welcher eingefügt wird
     * @param index angegebene Position
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

                for(int nodeIndex=0; nodeIndex<index;nodeIndex++) {
                    newArray[nodeIndex] = this.nodes[nodeIndex];
                }
                newArray[index] = node;
                for(int nodeIndex=index+1; nodeIndex<newArray.length;nodeIndex++) {
                    newArray[nodeIndex] = this.nodes[nodeIndex-1];
                }
                this.nodes = newArray;
            }
        }
    }

    /**
     * Ruft Knoten an einer angegebenen Position ab
     * @param index angegebene Position
     * @return abgerufener Knoten
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
     * Löscht Knoten an einer angegebenen Position
     * @param index angegebene Position
     * @throws IndexOutOfBoundsException wenn der Index nicht vorhanden ist (<0 or >=size())
     */
    public void remove(final int index) throws IndexOutOfBoundsException {
        if(index>=this.size() || index<0) {
            throw new IndexOutOfBoundsException("There is no node at index " + index + ".");
        }
        else {
            final Node[] newArray = new Node[this.size()-1];
            if(index==0) {
                for(int nodeIndex=1; nodeIndex<=newArray.length; nodeIndex++) {
                    newArray[nodeIndex-1] = this.nodes[nodeIndex];
                }
            }
            else if(index==this.size()-1) {
                for(int nodeIndex=0; nodeIndex<newArray.length;nodeIndex++) {
                    newArray[nodeIndex] = this.nodes[nodeIndex];
                }
            }
            else {
                for(int nodeIndex=0; nodeIndex<index;nodeIndex++) {
                    newArray[nodeIndex] = this.nodes[nodeIndex];
                }
                for(int nodeIndex=index+1; nodeIndex<=newArray.length;nodeIndex++) {
                    newArray[nodeIndex-1] = this.nodes[nodeIndex];
                }
            }
            this.nodes = newArray;
        }
    }

    /**
     * Prüft, ob der übergebene Knoten in der Liste enthalten ist
     * @param node Knoten, welcher übergeben wird
     * @return true, wenn Element in Liste vorhanden ist, false wenn nicht
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
     * Ruft die Länge der Liste ab
     * @return Länge der Liste
     */
    public int size() {
        return this.nodes.length;
    }

    /**
     * Entfernt alle Knoten aus der liste
     */
    public void clear() {
        this.nodes = new Node[0];
    }

    /**
     * Gibt eine String-Repräsentation der Knoten in der Liste zurück
     * @return String-Repräsentation der Knoten in der Liste zurück
     */
    @Override
    public String toString() {
        return Arrays.toString(this.nodes);
    }

    /**
     * Gibt Array aller Knoten, die sich in der Liste befinden, zurück
     * @return Knoten der Liste
     */
    public Node[] getNodes() {
        return nodes;
    }
}
