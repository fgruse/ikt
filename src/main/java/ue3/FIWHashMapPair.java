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

    public void setValue(final String value) {
        this.value = value;
    }

    public int hash(int buckets) {
        return hash(buckets, this.getKey());
    }

    public static int hash(int buckets, String key) {
        int result = 0;
        for (char c : key.toCharArray()) {
            result = (result + c*c);
            // result = (result + c); // alte hash-funktion
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
