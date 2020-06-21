package ue5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SetTest {

    @Test
    public void testAddAndContains() {
        Set<Integer> s = new Set<>();

        assertTrue(s.add(1));
        assertTrue(s.contains(1));

        assertTrue(s.add(2));
        assertTrue(s.contains(1));
        assertFalse(s.contains(5));

        assertTrue(s.add(3));
        assertEquals(3, s.getSize());
        assertFalse(s.add(3));
        assertEquals(3, s.getSize());
    }

    @Test
    public void testRemove() {
        Set<Integer> s = new Set<>();
        s.add(1);
        s.add(2);

        assertTrue(s.contains(1));
        assertTrue(s.contains(2));

        assertTrue(s.remove(1));
        assertTrue(s.remove(2));

        assertFalse(s.contains(1));
        assertFalse(s.contains(2));

        assertFalse(s.contains(5));
        assertFalse(s.remove(5));
        assertFalse(s.contains(5));
    }

    @Test
    public void testSize() {
        Set<Integer> s = new Set<>();

        assertEquals(0, s.getSize());

        s.add(5);
        s.add(2);
        s.add(19);
        assertEquals(3, s.getSize());
        s.remove(2);
        assertEquals(2, s.getSize());
    }

    @Test
    public void testForDouble() {
        Set<Double> s = new Set<>();

        assertTrue(s.add(1.2));
        assertTrue(s.contains(1.2));

        assertTrue(s.add(2.3));
        assertTrue(s.contains(1.2));
        assertFalse(s.contains(5.3));

        assertTrue(s.add(3.4));
        assertEquals(3, s.getSize());
        assertFalse(s.add(3.4));
        assertEquals(3, s.getSize());
    }
}
