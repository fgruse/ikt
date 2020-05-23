package ue2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DoppeltVerketteteListeTest {

    // Testen der prepend-Methode (hinzufügen am Anfang der Liste)

    @Test
    public void testPrepend() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();

        // bei leerer Liste
        assertNull(l.getHead());
        l.prepend(1);
        assertNotNull(l.getHead());
        assertNotNull(l.getTail());
        assertNull(l.getHead().getData());
        assertNull(l.getTail().getData());
        assertEquals(1, (int) l.getHead().getNext().getData());
        assertEquals(1, (int) l.getTail().getPrev().getData());
        assertEquals(l.getHead(), l.getTail().getPrev().getPrev());
        assertEquals(l.getTail(), l.getHead().getNext().getNext());

        // bei nicht-leerer Liste
        l.prepend(2);
        assertEquals(2, (int) l.getHead().getNext().getData());
        assertEquals(1, (int) l.getHead().getNext().getNext().getData());
        assertEquals(1, (int) l.getTail().getPrev().getData());
        assertEquals(2, (int) l.getTail().getPrev().getPrev().getData());
        assertEquals(l.getHead(), l.getTail().getPrev().getPrev().getPrev());
        assertEquals(l.getTail(), l.getHead().getNext().getNext().getNext());
    }

    // Testen der append-Methode (hinzufügen am Ende der Liste)

    @Test
    public void testAppend() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();

        // bei leerer Liste
        l.append(5);
        assertEquals(5, (int) l.getHead().getNext().getData());
        assertEquals(5, (int) l.getTail().getPrev().getData());
        assertEquals(l.getHead(), l.getTail().getPrev().getPrev());
        assertEquals(l.getTail(), l.getHead().getNext().getNext());

        // bei nicht-leerer Liste
        l.append(2);
        l.append(19);
        assertEquals(2, (int) l.getHead().getNext().getNext().getData());
        assertEquals(19, (int) l.getHead().getNext().getNext().getNext().getData());
        assertEquals(19, (int) l.getTail().getPrev().getData());
        assertEquals(2, (int) l.getTail().getPrev().getPrev().getData());
        assertEquals(l.getHead(), l.getTail().getPrev().getPrev().getPrev().getPrev());
        assertEquals(l.getTail(), l.getHead().getNext().getNext().getNext().getNext());
    }

    // Testen der insert-Methode (einfügen an einem bestimmten Index)

    @Test
    public void testInsert() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();

        // bei leerer Liste
        l.insert(0, 5);
        assertEquals(1, l.size());
        assertEquals(5, (int) l.getHead().getNext().getData());
        assertEquals(5, (int) l.getTail().getPrev().getData());
        assertEquals(l.getHead(), l.getTail().getPrev().getPrev());
        assertEquals(l.getTail(), l.getHead().getNext().getNext());

        // bei nicht-leerer Liste, einfügen bei Index 0
        l.insert(0, 6);
        assertEquals(2, l.size());
        assertEquals(6, (int) l.getHead().getNext().getData());
        assertEquals(5, (int) l.getHead().getNext().getNext().getData());
        assertEquals(5, (int) l.getTail().getPrev().getData());
        assertEquals(l.getHead(), l.getTail().getPrev().getPrev().getPrev());
        assertEquals(l.getTail(), l.getHead().getNext().getNext().getNext());

        // bei nicht-leerer Liste, zwischendrin einfügen
        l.insert(1, 7);
        assertEquals(3, l.size());
        assertEquals(6, (int) l.getHead().getNext().getData());
        assertEquals(7, (int) l.getHead().getNext().getNext().getData());
        assertEquals(5, (int) l.getHead().getNext().getNext().getNext().getData());
        assertEquals(5, (int) l.getTail().getPrev().getData());
        assertEquals(7, (int) l.getTail().getPrev().getPrev().getData());
        assertEquals(l.getHead(), l.getTail().getPrev().getPrev().getPrev().getPrev());
        assertEquals(l.getTail(), l.getHead().getNext().getNext().getNext().getNext());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertAtIndexOutOfBoundsNegative() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.insert(-1, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertAtIndexOutOfBoundsTooBig() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.insert(100, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testaInsertAtIndexOutOfBoundsTooBigEmptyList() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();
        l.insert(1, 4);
    }

    // Testen der get-Methode

    @Test
    public void testGet() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        assertEquals(5, l.get(0));
        assertEquals(2, l.get(1));
        assertEquals(19, l.get(2));
        assertEquals(2, l.get(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBoundsNegative() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.get(-100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBoundsTooBig() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.get(100);
    }

    // Testen der remove-Methode an einem bestimmten Index

    @Test
    public void testRemove() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(7);
        l.remove(1);
        assertEquals(3 , l.size());
        assertEquals(5, (int) l.getHead().getNext().getData());
        assertEquals(19, (int) l.getHead().getNext().getNext().getData());
        assertEquals(7, (int) l.getHead().getNext().getNext().getNext().getData());
        assertEquals(l.getHead(), l.getTail().getPrev().getPrev().getPrev().getPrev());
        assertEquals(l.getTail(), l.getHead().getNext().getNext().getNext().getNext());
    }

    @Test
    public void testRemoveAtIndex0() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.remove(0);
        assertEquals(3 , l.size());
        assertEquals(2, (int) l.getHead().getNext().getData());
        assertEquals(l.getHead(), l.getTail().getPrev().getPrev().getPrev().getPrev());
        assertEquals(l.getTail(), l.getHead().getNext().getNext().getNext().getNext());
    }

    @Test
    public void testRemoveAtLastIndex() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.remove(3);
        assertEquals(3 , l.size());
        assertEquals(19, (int) l.getHead().getNext().getNext().getNext().getData());
        assertEquals(l.getHead(), l.getTail().getPrev().getPrev().getPrev().getPrev());
        assertEquals(l.getTail(), l.getHead().getNext().getNext().getNext().getNext());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexOutOfBoundsNegative() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.remove(-100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexOutOfBoundsTooBig() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.remove(100);
    }

    // Testen der contains-Methode

    @Test
    public void testContains() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();
        l.append(5);
        l.append(2);
        l.append(19);
        assertTrue(l.contains(5));
        assertTrue(l.contains(2));
        assertTrue(l.contains(19));
        assertFalse(l.contains(100));
    }

    // Testen der size-Methode

    @Test
    public void testSize() {
        DoppeltVerketteteListe l = new DoppeltVerketteteListe();

        // bei leerer Liste
        assertEquals(0, l.size());

        //bei nicht-leerer Liste
        l.append(5);
        l.append(2);
        l.append(19);
        assertEquals(3, l.size());
    }

}
