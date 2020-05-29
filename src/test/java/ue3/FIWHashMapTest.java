package ue3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FIWHashMapTest {

    @Test
    public void testMethods() {
        FIWHashMap m = new FIWHashMap();

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
}
