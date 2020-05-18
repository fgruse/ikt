package ue3;

public class FIWHashMapPair {

    private String key;
    private String value;
    private FIWHashMapPair next;

    public FIWHashMapPair(final String key, final String value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int hash(int buckets) {
        int result = 0;
        for (char c : this.getKey().toCharArray()) {
            result = (result + c);
        }
        return (result % buckets);
    }

    public FIWHashMapPair getNext() {
        return next;
    }

    public void setNext(final FIWHashMapPair next) {
        this.next = next;
    }
}
