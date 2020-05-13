package ue2;

public class Set {

    private doppeltVerketteteListe list;

    public Set() {
        this.list = new doppeltVerketteteListe();
    }

    public doppeltVerketteteListe getList() {
        return list;
    }

    /**
     * Element in Menge einfügen
     * @param value - Element, welches eingefügt wird
     * @return - true, wenn Element eingefügt wurde, false wenn nicht
     */
    public boolean add(int value){
        if(!this.list.contains(value)) {
            this.list.insert(0, value);
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
        int size = this.size();
        int index = -1;
        if(size>0) {
            Element pointer = this.list.getHead().getNext();
            for(int i=0; i<size; i++) {
                if(pointer.getData()==value) {
                    index = i;
                    break;
                }
                pointer = pointer.getNext();
            }
        }
        if(index!=-1) {
            this.list.remove(index);
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

    /**
     * ruft die Anzahl der Elemente in der Menge ab
     * @return - Anzahl der Elemente in der Menge
     */
    public int size() {
        return this.list.size();
    }


}
