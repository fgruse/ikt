package ue5;

public class KeyValuePair<K, V> {

    private K key;
    private V value;
    private KeyValuePair<K, V> next;

    public KeyValuePair(final K key, final V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(final V value) {
        this.value = value;
    }

    public int hash(int buckets) {
        return hash(buckets, this.getKey());
    }

    public static <K> int hash(int buckets, K key) {
        int result = 0;
        for (char c : key.toString().toCharArray()) {
            result = (result + c*c);
        }
        return (result % buckets);
    }

    public KeyValuePair<K, V> getNext() {
        return next;
    }

    public void setNext(final KeyValuePair<K, V> next) {
        this.next = next;
    }
}
