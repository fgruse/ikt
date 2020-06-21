package ue5;

public class Set<T> {

    private DoublyLinkedList<T> list;
    private int size;

    public Set() {
        this.list = new DoublyLinkedList<>();
        this.size = 0;
    }

    public DoublyLinkedList<T> getList() {
        return list;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * Element in Menge einfügen
     * @param value - Element, welches eingefügt wird
     * @return - true, wenn Element eingefügt wurde, false wenn nicht
     */
    public boolean add(T value){
        if(!this.contains(value)) {
            this.list.prepend(value);
            this.size++;
            return true;
        }
        return false;
    }

    /**
     * Element aus Menge löschen
     * @param value - Element, das gelöscht werden soll
     * @return - true, wenn Element gelöscht wurde, false wenn nicht
     */
    public boolean remove(T value) throws IndexOutOfBoundsException {
        int index = -1;
        if(this.size>0) {
            ElementDLL<T> pointer = this.list.getHead().getNext();
            for(int i=0; i<this.size; i++) {
                if(pointer.getData().equals(value)) {
                    index = i;
                    break;
                }
                pointer = pointer.getNext();
            }
        }
        if(index!=-1) {
            this.list.remove(index);
            this.size--;
            return true;
        }
        return false;
    }

    /**
     * prüft, ob das übergebene Element in der Menge enthalten ist
     * @param value - Element, welches übergeben wird
     * @return contains - true, wenn Element in Menge vorhanden ist, false wenn nicht
     */
    public boolean contains(T value) {
        return this.list.contains(value);
    }
}
