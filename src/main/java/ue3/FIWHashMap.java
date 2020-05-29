package ue3;

import java.util.ArrayList;

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
        if(this.table[hash]==null) { // put value for new key
            this.table[hash] = newEntry;
            this.size++;
        }
        else {
            String possiblyExistingKeyForHash = this.table[hash].getKey();
            if(possiblyExistingKeyForHash.equals(key)) { // overwrite value for existing key
                this.table[hash] = newEntry;
            }
            else {
                FIWHashMapPair existingEntry = this.table[hash];
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
    public String get(String key) {
        int hash = FIWHashMapPair.hash(this.buckets, key);
        FIWHashMapPair entry = this.table[hash];
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


    /**
     * Hilfmethode, um die Verteilung der Einträge auf die Buckets zu analysieren
     * @return - Liste, welche die Anzahl der Einträge pro Bucket beinhaltet
     */
    public ArrayList<Integer> verteilung() {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<this.buckets; i++) {
            FIWHashMapPair pointer = this.table[i];
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
