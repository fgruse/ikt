package ue3;

public class FIWHashMap {

    private int buckets;
    private FIWHashMapPair[] table;
    private int size;

    public FIWHashMap() {
        this.buckets = 256;
        this.table = new FIWHashMapPair[this.buckets];
        this.size = 0;
    }

    public FIWHashMap(final int buckets) {
        this.buckets = buckets;
        this.table = new FIWHashMapPair[this.buckets];
        this.size = 0;
    }

    /**
     * fügt ein Key-Value-Paar in die Map ein
     * @param key - Schlüssel des Key-Value-Paars, welches eingefügt wird
     * @param value - Wert des Key-Value-Paars, welches eingefügt wird
     */
    public void put(String key, String value) {
        FIWHashMapPair newEntry = new FIWHashMapPair(key, value);
        int hash = newEntry.hash(this.buckets);
        if(this.get(key)==null) { // put value for new key
            this.table[hash] = newEntry;
            this.size++;
        }
        else {
            String possiblyExistingKeyForHash = this.table[hash].getKey();
            if(possiblyExistingKeyForHash.equals(key)) { // overwrite value for existing key
                this.table[hash] = newEntry;
            }
            else { // deal with hash collision --> linked list
                FIWHashMapPair existingEntry = this.table[hash];
                while(existingEntry.getNext()!=null) {
                    existingEntry = existingEntry.getNext();
                }
                existingEntry.setNext(newEntry);
                this.size++;
            }
        }
    }

    /**
     * ruft den Wert zu einem Schlüssel eines Key-Value-Paars in der Map ab
     * @param key - Schlüssel des Key-Value-Paars, dessen Wert ausgegeben werden soll
     * @return - Wert des Key-Value-Paars, der ausgegeben wird
     */
    public String get(String key) {
        int result = 0;
        for (char c : key.toCharArray()) {
            result = (result + c);
        }
        int hash = result % this.buckets;

        FIWHashMapPair entry = table[hash];
        String value = "";

        if(entry==null) { // no entry in map for key
             value = null;
        }
        else if(entry.getKey().equals(key)){ // entry is first in bucket
            value = entry.getValue();
        }
        else { // find entry in linked list
            while(entry.getNext()!=null) {
                entry = entry.getNext();
                if(entry.getKey().equals(key)){
                    value = entry.getValue();
                }
            }
        }
        return value;
    }

    /**
     * ruft die Anzahl der Key-Value-Paare in der Map ab
     * @return - Anzahl der Key-Value-Paare in der Map
     */
    public int size() {
        return this.size;
    }
}