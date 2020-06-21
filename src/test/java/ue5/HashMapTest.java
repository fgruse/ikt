package ue5;

import org.junit.Test;
import ue3.FIWHashMapPair;

import static org.junit.Assert.assertEquals;

public class HashMapTest {

    @Test
    public void testMethodsStringString() {
        HashMap<String, String> m = new HashMap<>();

        m.put("FIW 1", "Frieda Gruse");
        assertEquals("Frieda Gruse", m.get("FIW 1"));
        assertEquals(1, m.size());

        m.put("Dozent 1", "Michael Witt");
        assertEquals("Michael Witt", m.get("Dozent 1"));
        assertEquals(2, m.size());

        m.put("Dozent 2", "Juliane Siegeris");
        assertEquals("Juliane Siegeris", m.get("Dozent 2"));
        assertEquals(3, m.size());

        // überschreiben eines eintrags bei vorhandenem key

        m.put("FIW 1", "Anna-Frieda Gruse");
        assertEquals("Anna-Frieda Gruse", m.get("FIW 1"));
        assertEquals(3, m.size());

        // hash collision
        assertEquals(new FIWHashMapPair("FIW 1", "Anna-Frieda Gruse").hash(256),
                new FIWHashMapPair("1 FIW", "Erika Mustermann").hash(256));

        assertEquals(new FIWHashMapPair("FIW 1", "Anna-Frieda Gruse").hash(256),
                new FIWHashMapPair("FI 1W", "Max Mustermann").hash(256));

        m.put("1 FIW", "Erika Mustermann");
        m.put("FI 1W", "Max Mustermann");

        assertEquals("Anna-Frieda Gruse", m.get("FIW 1"));
        assertEquals("Erika Mustermann", m.get("1 FIW"));
        assertEquals("Max Mustermann", m.get("FI 1W"));

        assertEquals(5, m.size());

        m.put("FI 1W", "Maximilian Mustermann");

        assertEquals("Maximilian Mustermann", m.get("FI 1W"));
        assertEquals(5, m.size());
    }

    @Test
    public void testMethodsIntDouble() {
        HashMap<Integer, Double> m = new HashMap<>();

        m.put(123, 1.4);
        assertEquals(1.4, m.get(123), 0.0);
        assertEquals(1, m.size());

        m.put(2, 2.4);
        assertEquals(2.4, m.get(2), 0.0);
        assertEquals(2, m.size());

        m.put(3, 3.7);
        assertEquals(3.7, m.get(3), 0.0);
        assertEquals(3, m.size());

        // überschreiben eines eintrags bei vorhandenem key

        m.put(123, 4.1);
        assertEquals(4.1, m.get(123), 0.0);
        assertEquals(3, m.size());


        // hash collision
        assertEquals(new KeyValuePair<>(123, 4.1).hash(256),
                new KeyValuePair<>(213, 9.4).hash(256));

        assertEquals(new KeyValuePair<>(123, 4.1).hash(256),
                new KeyValuePair<>(312, 91.4).hash(256));

        m.put(213, 9.4);
        m.put(312, 91.4);

        assertEquals(4.1, m.get(123), 0.0);
        assertEquals(9.4, m.get(213), 0.0);
        assertEquals(91.4, m.get(312), 0.0);

        assertEquals(5, m.size());

        m.put(312, 91.8);

        assertEquals(91.8, m.get(312), 0.0);
        assertEquals(5, m.size());
    }
}
