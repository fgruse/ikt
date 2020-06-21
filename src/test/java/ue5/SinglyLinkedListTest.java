package ue5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SinglyLinkedListTest {

        // Testen der prepend-Methode (hinzufügen am Anfang der Liste)

        @Test
        public void testPrepend() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();

            // bei leerer Liste
            assertNull(l.getHead());
            l.prepend(1);
            assertNotNull(l.getHead());
            assertNull(l.getHead().getData());
            assertEquals(1, (int) l.getHead().getNext().getData());
            assertNull(l.getHead().getNext().getNext());

            // bei nicht-leerer Liste
            l.prepend(2);
            assertEquals(2, (int) l.getHead().getNext().getData());
            assertEquals(1, (int) l.getHead().getNext().getNext().getData());
            assertNull(l.getHead().getNext().getNext().getNext());
        }

        // Testen der append-Methode (hinzufügen am Ende der Liste)

        @Test
        public void testAppend() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();

            // bei leerer Liste
            l.append(5);
            assertEquals(5, (int) l.getHead().getNext().getData());
            assertNull(l.getHead().getNext().getNext());

            // bei nicht-leerer Liste
            l.append(2);
            l.append(19);
            assertEquals(2, (int) l.getHead().getNext().getNext().getData());
            assertEquals(19, (int) l.getHead().getNext().getNext().getNext().getData());
            assertNull(l.getHead().getNext().getNext().getNext().getNext());
        }

        // Testen der insert-Methode (einfügen an einem bestimmten Index)

        @Test
        public void testInsert() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();

            // bei leerer Liste
            l.insert(0, 5);
            assertEquals(1, l.getSize());
            assertEquals(5, (int) l.getHead().getNext().getData());
            assertNull(l.getHead().getNext().getNext());

            // bei nicht-leerer Liste, einfügen bei Index 0
            l.insert(0, 6);
            assertEquals(2, l.getSize());
            assertEquals(6, (int) l.getHead().getNext().getData());
            assertEquals(5, (int) l.getHead().getNext().getNext().getData());

            // bei nicht-leerer Liste, zwischendrin einfügen
            l.insert(1, 7);
            assertEquals(3, l.getSize());
            assertEquals(6, (int) l.getHead().getNext().getData());
            assertEquals(7, (int) l.getHead().getNext().getNext().getData());
            assertEquals(5, (int) l.getHead().getNext().getNext().getNext().getData());
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testInsertAtIndexOutOfBoundsNegative() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
            l.append(5);
            l.append(2);
            l.append(19);
            l.insert(-1, 4);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testInsertAtIndexOutOfBoundsTooBig() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
            l.append(5);
            l.append(2);
            l.append(19);
            l.insert(100, 4);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testaInsertAtIndexOutOfBoundsTooBigEmptyList() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
            l.insert(3, 4);
        }

        // Testen der get-Methode

        @Test
        public void testGet() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(2);
            assertEquals(5, (int) l.get(0));
            assertEquals(2, (int) l.get(1));
            assertEquals(19,(int)  l.get(2));
            assertEquals(2, (int) l.get(3));
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testGetIndexOutOfBoundsNegative() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(2);
            l.get(-100);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testGetIndexOutOfBoundsTooBig() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(2);
            l.get(100);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testGetIndexOutOfBoundsTooBigEmptyList() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
            l.get(100);
        }

        // Testen der remove-Methode an einem bestimmten Index

        @Test
        public void testRemove() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(7);
            l.remove(1);
            assertEquals(3 , l.getSize());
            assertEquals(5, (int) l.getHead().getNext().getData());
            assertEquals(19, (int) l.getHead().getNext().getNext().getData());
            assertEquals(7, (int) l.getHead().getNext().getNext().getNext().getData());
        }

        @Test
        public void testRemoveAtIndex0() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(2);
            l.remove(0);
            assertEquals(3 , l.getSize());
            assertEquals(2, (int) l.getHead().getNext().getData());
        }

        @Test
        public void testRemoveAtLastIndex() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(2);
            l.remove(3);
            assertEquals(3 , l.getSize());
            assertEquals(19, (int) l.getHead().getNext().getNext().getNext().getData());
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testRemoveAtIndexOutOfBoundsNegative() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(2);
            l.remove(-100);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void testRemoveAtIndexOutOfBoundsTooBig() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
            l.append(5);
            l.append(2);
            l.append(19);
            l.append(2);
            l.remove(100);
        }

        // Testen der contains-Methode

        @Test
        public void testContains() {
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
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
            SinglyLinkedList<Integer> l = new SinglyLinkedList<>();

            // bei leerer Liste
            assertEquals(0, l.getSize());

            //bei nicht-leerer Liste
            l.append(5);
            l.append(2);
            l.append(19);
            assertEquals(3, l.getSize());
        }

    // Testen für anderen Elementtyp

    @Test
    public void testForDouble() {
        SinglyLinkedList<Double> l = new SinglyLinkedList<>();

        // bei leerer Liste
        assertNull(l.getHead());
        l.prepend(1.4);
        assertNotNull(l.getHead());
        assertNull(l.getHead().getData());
        assertEquals(1.4, l.getHead().getNext().getData(), 0.0);
        assertNull(l.getHead().getNext().getNext());

        // bei nicht-leerer Liste
        l.prepend(2.73);
        assertEquals(2.73, l.getHead().getNext().getData(), 0.0);
        assertEquals(1.4, l.getHead().getNext().getNext().getData(), 0.0);
        assertNull(l.getHead().getNext().getNext().getNext());
    }
}
