package ue1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class FeldListeTest {


    // Testen der prepend-Methode (hinzufügen am Anfang der Liste)

    @Test
    public void testPrepend() {
        FeldListe l = new FeldListe();

        // bei leerer Liste
        l.prepend(1);
        assertEquals(1 , l.size());
        assertEquals(1, l.get(0));

        // bei nicht-leerer Liste
        l.prepend(2);
        assertEquals(2 , l.size());
        assertEquals(2, l.get(0));
        assertEquals(1, l.get(1));

    }

    // Testen der append-Methode (hinzufügen am Ende der Liste)

    @Test
    public void testAppend() {
        FeldListe l = new FeldListe();

        // bei leerer Liste
        l.append(5);
        assertEquals(1 , l.size());
        assertEquals(5, l.get(0));

        // bei nicht-leerer Liste
        l.append(2);
        l.append(19);
        assertEquals(3 , l.size());
        assertEquals(5, l.get(0));
        assertEquals(2, l.get(1));
        assertEquals(19, l.get(2));
    }

    // Testen der insert-Methode (einfügen an einem bestimmten Index)

    @Test
    public void testInsert() {
        FeldListe l = new FeldListe();

        // bei leerer Liste
        l.insert(0, 5);
        assertEquals(1, l.size());
        assertEquals(5, l.get(0));


        // bei nicht-leerer Liste, einfügen bei Index 0
        l.insert(0, 6);
        assertEquals(2, l.size());
        assertEquals(6, l.get(0));
        assertEquals(5, l.get(1));



        // bei nicht-leerer Liste, zwischendrin einfügen
        l.insert(1, 7);
        assertEquals(3, l.size());
        assertEquals(7, l.get(1));
        assertEquals(6, l.get(0));
        assertEquals(5, l.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertAtIndexOutOfBoundsNegative() {
        FeldListe l = new FeldListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.insert(-1, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertAtIndexOutOfBoundsTooBig() {
        FeldListe l = new FeldListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.insert(100, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testaInsertAtIndexOutOfBoundsTooBigEmptyList() {
        FeldListe l = new FeldListe();
        l.insert(1, 4);
    }

    // Testen der get-Methode

    @Test
    public void testGet() {
        FeldListe l = new FeldListe();
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
        FeldListe l = new FeldListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.get(-100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBoundsTooBig() {
        FeldListe l = new FeldListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.get(100);
    }

    // Testen der remove-Methode an einem bestimmten Index

    @Test
    public void testRemove() {
        FeldListe l = new FeldListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(7);
        l.remove(1);
        assertEquals(3 , l.size());
        assertEquals(5, l.get(0));
        assertEquals(19, l.get(1));
        assertEquals(7, l.get(2));
    }

    @Test
    public void testRemoveAtIndex0() {
        FeldListe l = new FeldListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.remove(0);
        assertEquals(3 , l.size());
        assertEquals(2, l.get(0));
        assertEquals(19, l.get(1));
        assertEquals(2, l.get(2));
    }

    @Test
    public void testRemoveAtLastIndex() {
        FeldListe l = new FeldListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.remove(3);
        assertEquals(3 , l.size());
        assertEquals(5, l.get(0));
        assertEquals(2, l.get(1));
        assertEquals(19, l.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexOutOfBoundsNegative() {
        FeldListe l = new FeldListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.remove(-100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexOutOfBoundsTooBig() {
        FeldListe l = new FeldListe();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.remove(100);
    }

    // Testen der contains-Methode

    @Test
    public void testContains() {
        FeldListe l = new FeldListe();
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
        FeldListe l = new FeldListe();

        // bei leerer Liste
        assertEquals(0, l.size());

        //bei nicht-leerer Liste
        l.append(5);
        l.append(2);
        l.append(19);
        assertEquals(3, l.size());
    }
}
