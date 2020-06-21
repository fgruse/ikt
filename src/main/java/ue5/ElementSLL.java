package ue5;

public class ElementSLL<T> {

    @Override
    public String toString() {
        return " Data: " + data;
    }

    private T data;
    private ElementSLL<T> next;

    public ElementSLL(T data) {
        this.data = data;
        this.next = null;
    }

    public ElementSLL() {
        this.data = null;
        this.next = null;
    }

    public ElementSLL<T> getNext() {
        return next;
    }

    public void setNext(ElementSLL<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

}
