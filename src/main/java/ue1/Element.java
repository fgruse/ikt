package ue1;

public class Element {

    @Override
    public String toString() {
        return " Data: " + data;
    }

    private Integer data;
    private Element next;

    public Element(Integer data) {
        this.data = data;
        this.next = null;
    }

    public Element() {
        this.data = null;
        this.next = null;
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

}
