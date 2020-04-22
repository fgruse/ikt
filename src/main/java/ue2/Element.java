package ue2;

public class Element {

    @Override
    public String toString() {
        return " Data: " + data;
    }

    private Integer data;
    private Element next;
    private Element prev;

    public Element(Integer data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Element() {
        this.data = null;
        this.next = null;
        this.prev = null;
    }

    public Element getNext() {
        return next;
    }

    public void setNext(Element next) {
        this.next = next;
    }

    public Integer getData() {
        return data;
    }

    public Element getPrev() {
        return prev;
    }

    public void setPrev(final Element prev) {
        this.prev = prev;
    }
}
