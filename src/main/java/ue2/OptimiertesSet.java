package ue2;

public class OptimiertesSet {

    private doppeltVerketteteListe list;
    private int size;

    public OptimiertesSet() {
        this.list = new doppeltVerketteteListe();
        this.size = 0;
    }

    public doppeltVerketteteListe getList() {
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
    public boolean add(int value){
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
    public boolean remove(int value) throws IndexOutOfBoundsException {
        int index = -1;
        if(this.size>0) {
            Element pointer = this.list.getHead().getNext();
            for(int i=0; i<this.size; i++) {
                if(pointer.getData()==value) {
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
    public boolean contains(int value) {
        return this.list.contains(value);
    }
}
