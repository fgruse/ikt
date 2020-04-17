package ue1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class optimierteEinfachVerketteteListeTest {


        // Testen der prepend-Methode (hinzufügen am Anfang der Liste)

        @Test
        public void testPrepend() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();

            // bei leerer Liste
            l.prepend(1);
            assertEquals(1, l.getHead().getData());
            assertNull(l.getHead().getNext());

            // bei nicht-leerer Liste
            l.prepend(2);
            assertEquals(2, l.getHead().getData());
            assertEquals(1, l.getHead().getNext().getData());
            assertNull(l.getHead().getNext().getNext());
        }

        // Testen der append-Methode (hinzufügen am Ende der Liste)

        @Test
        public void testAppend() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();

            // bei leerer Liste
            l.append(5);
            assertEquals(5, l.getHead().getData());
            assertNull(l.getHead().getNext());

            // bei nicht-leerer Liste
            l.append(2);
            l.append(19);
            assertEquals(2, l.getHead().getNext().getData());
            assertEquals(19, l.getHead().getNext().getNext().getData());
            assertNull(l.getHead().getNext().getNext().getNext());
        }

        // Testen der insert-Methode (einfügen an einem bestimmten Index)

        @Test
        public void testInsert() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();

            // bei leerer Liste
            l.insert(0, 5);
            assertEquals(1, l.getSize());
            assertEquals(5, l.getHead().getData());
            assertNull(l.getHead().getNext());

            // bei nicht-leerer Liste, einfügen bei Index 0
            l.insert(0, 6);
            assertEquals(2, l.getSize());
            assertEquals(6, l.getHead().getData());
            assertEquals(5, l.getHead().getNext().getData());

            // bei nicht-leerer Liste, zwischendrin einfügen
            l.insert(1, 7);
            assertEquals(3, l.getSize());
            assertEquals(6, l.getHead().getData());
            assertEquals(7, l.getHead().getNext().getData());
            assertEquals(5, l.getHead().getNext().getNext().getData());

        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testappendAtIndexOutOfBoundsNegative() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();
            l.append(5);
            l.append(2);
            l.append(19);
            l.insert(-1, 4);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testappendAtIndexOutOfBoundsTooBig() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();
            l.append(5);
            l.append(2);
            l.append(19);
            l.insert(100, 4);
        }

        // Testen der get-Methode

        @Test
        public void testGet() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();
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
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(2);
            l.get(-100);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testGetIndexOutOfBoundsTooBig() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(2);
            l.get(100);
        }

        // Testen der remove-Methode an einem bestimmten Index

        @Test
        public void testRemove() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(7);
            l.remove(1);
            assertEquals(3 , l.getSize());
            assertEquals(l.getHead().getData(), 5);
            assertEquals(l.getHead().getNext().getData(), 19);
            assertEquals(l.getHead().getNext().getNext().getData(), 7);
        }

        @Test
        public void testRemoveAtIndex0() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(2);
            l.remove(0);
            assertEquals(3 , l.getSize());
            assertEquals(l.getHead().getData(), 2);
        }

        @Test
        public void testRemoveAtLastIndex() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(2);
            l.remove(3);
            assertEquals(3 , l.getSize());
            assertEquals(l.getHead().getNext().getNext().getData(), 19);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testRemoveAtIndexOutOfBoundsNegative() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(2);
            l.remove(-100);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testRemoveAtIndexOutOfBoundsTooBig() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(2);
            l.remove(100);
        }

        // Testen der contains-Methode

        @Test
        public void testContains() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();
            l.append(5);
            l.append(2);
            l.append(19);
            assertTrue(l.contains(5));
            assertTrue(l.contains(2));
            assertTrue(l.contains(19));
            assertFalse(l.contains(100));
        }

    // Testen der size

        @Test
        public void testGetSize() {
            optimierteEinfachVerketteteListe l = new optimierteEinfachVerketteteListe();

            // bei leerer Liste
            assertEquals(0, l.getSize());

            //bei nicht-leerer Liste
            l.append(5);
            l.append(2);
            l.append(19);
            l.insert(1,18);
            l.insert(0, 7);
            l.prepend(5);
            assertEquals(6, l.getSize());
        }
}
