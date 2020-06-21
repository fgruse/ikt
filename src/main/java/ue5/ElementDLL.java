package ue5;

public class ElementDLL<T> {

    @Override
    public String toString() {
        return " Data: " + data;
    }

    private T data;
    private ElementDLL<T> next;
    private ElementDLL<T> prev;

    public ElementDLL(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public ElementDLL() {
        this.data = null;
        this.next = null;
        this.prev = null;
    }

    public ElementDLL<T> getNext() {
        return next;
    }

    public void setNext(ElementDLL<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public ElementDLL<T> getPrev() {
        return prev;
    }

    public void setPrev(final ElementDLL<T> prev) {
        this.prev = prev;
    }
}
