package ue5;

import java.util.ArrayList;

public class HashMap<K, V> {

    private final int buckets;
    private final KeyValuePair<K, V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        this.buckets = 256;
        this.table = new KeyValuePair[this.buckets];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public HashMap(final int buckets) {
        this.buckets = buckets;
        this.table = new KeyValuePair[this.buckets];
        this.size = 0;
    }

    /**
     * fügt ein Key-Value-Paar in die Map ein
     * @param key - Schlüssel des Key-Value-Paars, welches eingefügt wird
     * @param value - Wert des Key-Value-Paars, welches eingefügt wird
     */
    public void put(K key, V value) {
        KeyValuePair<K, V> newEntry = new KeyValuePair<>(key, value);
        int hash = newEntry.hash(this.buckets);
        if(this.table[hash]==null) { // put value for new key
            this.table[hash] = newEntry;
            this.size++;
        }
        else {
            K possiblyExistingKeyForHash = this.table[hash].getKey();
            if(possiblyExistingKeyForHash.equals(key)) { // overwrite value for existing key
                this.table[hash] = newEntry;
            }
            else {
                KeyValuePair<K, V> existingEntry = this.table[hash];
                boolean existing = false;
                while(existingEntry.getNext()!=null) {
                    existingEntry = existingEntry.getNext();
                    if(existingEntry.getKey().equals(key)) {
                        existing = true;
                        break;
                    }
                }
                if(existing) { // overwrite value for existing key in linked list
                    existingEntry.setValue(value);
                }
                else { // deal with hash collision --> add to linked list
                    existingEntry.setNext(newEntry);
                    this.size++;
                }
            }
        }
    }

    /**
     * ruft den Wert zu einem Schlüssel eines Key-Value-Paars in der Map ab
     * @param key - Schlüssel des Key-Value-Paars, dessen Wert ausgegeben werden soll
     * @return - Wert des Key-Value-Paars, der ausgegeben wird
     */
    public V get(K key) {
        int hash = KeyValuePair.hash(this.buckets, key);
        KeyValuePair<K, V> entry = this.table[hash];

        if(entry==null) { // no entry in map for key
             return null;
        }
        else if(entry.getKey().equals(key)){ // entry is first in bucket
           return entry.getValue();
        }
        else { // find entry in linked list
            while(entry.getNext()!=null) {
                entry = entry.getNext();
                if(entry.getKey().equals(key)){
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    /**
     * ruft die Anzahl der Key-Value-Paare in der Map ab
     * @return - Anzahl der Key-Value-Paare in der Map
     */
    public int size() {
        return this.size;
    }


    /**
     * Hilfmethode, um die Verteilung der Einträge auf die Buckets zu analysieren
     * @return - Liste, welche die Anzahl der Einträge pro Bucket beinhaltet
     */
    public ArrayList<Integer> verteilung() {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<this.buckets; i++) {
            KeyValuePair<K, V> pointer = this.table[i];
            if(pointer!=null) {
                int counter = 1;
                while(pointer.getNext()!=null) {
                    counter++;
                    pointer = pointer.getNext();
                }
                list.add(counter);
            }
        }
        return list;
    }
}
