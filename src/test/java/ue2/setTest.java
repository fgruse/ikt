package ue2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class setTest {

    @Test
    public void testAddAndContains() {
        Set s = new Set();

        assertTrue(s.add(1));
        assertTrue(s.contains(1));

        assertTrue(s.add(2));
        assertTrue(s.contains(1));
        assertFalse(s.contains(5));

        assertTrue(s.add(3));
        assertEquals(3, s.size());
        assertFalse(s.add(3));
        assertEquals(3, s.size());
    }

    @Test
    public void testRemove() {
        Set s = new Set();
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
        Set s = new Set();

        assertEquals(0, s.size());

        s.add(5);
        s.add(2);
        s.add(19);
        assertEquals(3, s.size());
        s.remove(2);
        assertEquals(2, s.size());
    }
}
