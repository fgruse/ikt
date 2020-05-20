package ue4;

public class Listenelement<E> {

    @Override
    public String toString() {
        return " Data: " + data;
    }

    private E data;
    private Listenelement<E> next;

    public Listenelement(E data) {
        this.data = data;
        this.next = null;
    }

    public Listenelement() {
        this.data = null;
        this.next = null;
    }

    public Listenelement<E> getNext() {
        return next;
    }

    public void setNext(Listenelement<E> next) {
        this.next = next;
    }

    public E getData() {
        return data;
    }
}
