package astern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayListTest {

    // Testen der prepend-Methode (hinzufügen am Anfang der Liste)

    @Test
    public void testPrepend() {
        ArrayList<Integer> l = new ArrayList<>();

        // bei leerer Liste
        l.prepend(1);
        assertEquals(1 , l.size());
        assertEquals(1, (int) l.get(0));

        // bei nicht-leerer Liste
        l.prepend(2);
        assertEquals(2 , l.size());
        assertEquals(2, (int) l.get(0));
        assertEquals(1, (int) l.get(1));

    }

    // Testen der append-Methode (hinzufügen am Ende der Liste)

    @Test
    public void testAppend() {
        ArrayList<Double> l = new ArrayList<>();

        // bei leerer Liste
        l.append(5.0);
        assertEquals(1 , l.size());
        assertEquals(5.0, (double) l.get(0), 0.0);

        // bei nicht-leerer Liste
        l.append(2.0);
        l.append(19.0);
        assertEquals(3 , l.size());
        assertEquals(5.0, (double) l.get(0), 0.0);
        assertEquals(2.0, (double) l.get(1), 0.0);
        assertEquals(19, (double) l.get(2), 0.0);
    }

    // Testen der insert-Methode (einfügen an einem bestimmten Index)

    @Test
    public void testInsert() {
        ArrayList<Node> l = new ArrayList<>();
        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);

        // bei leerer Liste
        l.insert(0, node1);
        assertEquals(1, l.size());
        assertEquals(node1, l.get(0));

        // bei nicht-leerer Liste, einfügen bei Index 0
        l.insert(0, node2);
        assertEquals(2, l.size());
        assertEquals(node2, l.get(0));
        assertEquals(node1, l.get(1));

        // bei nicht-leerer Liste, zwischendrin einfügen
        l.insert(1, node3);
        assertEquals(3, l.size());
        assertEquals(node3, l.get(1));
        assertEquals(node2, l.get(0));
        assertEquals(node1, l.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertAtIndexOutOfBoundsNegative() {
        ArrayList<Integer> l = new ArrayList<>();
        l.append(5);
        l.append(2);
        l.append(19);
        l.insert(-1, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertAtIndexOutOfBoundsTooBig() {
        ArrayList<Integer> l = new ArrayList<>();
        l.append(5);
        l.append(2);
        l.append(19);
        l.insert(100, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testaInsertAtIndexOutOfBoundsTooBigEmptyList() {
        ArrayList<Integer> l = new ArrayList<>();
        l.insert(1, 4);
    }

    // Testen der get-Methode

    @Test
    public void testGet() {
        ArrayList<Integer> l = new ArrayList<>();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        assertEquals(5, (int) l.get(0));
        assertEquals(2, (int) l.get(1));
        assertEquals(19, (int) l.get(2));
        assertEquals(2, (int) l.get(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBoundsNegative() {
        ArrayList<Integer> l = new ArrayList<>();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.get(-100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBoundsTooBig() {
        ArrayList<Integer> l = new ArrayList<>();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.get(100);
    }

    // Testen der remove-Methode an einem bestimmten Index

    @Test
    public void testRemove() {
        ArrayList<Integer> l = new ArrayList<>();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(7);
        l.remove(1);
        assertEquals(3 , l.size());
        assertEquals(5, (int) l.get(0));
        assertEquals(19, (int) l.get(1));
        assertEquals(7, (int) l.get(2));
    }

    @Test
    public void testRemoveAtIndex0() {
        ArrayList<Integer> l = new ArrayList<>();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.remove(0);
        assertEquals(3 , l.size());
        assertEquals(2, (int) l.get(0));
        assertEquals(19, (int) l.get(1));
        assertEquals(2, (int) l.get(2));
    }

    @Test
    public void testRemoveAtLastIndex() {
        ArrayList<Integer> l = new ArrayList<>();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.remove(3);
        assertEquals(3 , l.size());
        assertEquals(5, (int) l.get(0));
        assertEquals(2, (int) l.get(1));
        assertEquals(19, (int) l.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexOutOfBoundsNegative() {
        ArrayList<Integer> l = new ArrayList<>();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.remove(-100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexOutOfBoundsTooBig() {
        ArrayList<Integer> l = new ArrayList<>();
        l.append(5);
        l.append(2);
        l.append(19);
        l.append(2);
        l.remove(100);
    }

    // Testen der contains-Methode

    @Test
    public void testContains() {
        ArrayList<Integer> l = new ArrayList<>();
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
        ArrayList<Integer> l = new ArrayList<>();

        // bei leerer Liste
        assertEquals(0, l.size());

        //bei nicht-leerer Liste
        l.append(5);
        l.append(2);
        l.append(19);
        assertEquals(3, l.size());
    }

    // Testen der toString-Methode

    @Test
    public void testToString() {
        ArrayList<Integer> l = new ArrayList<>();

        assertEquals("[]", l.toString());

        l.append(5);
        l.append(2);
        l.append(19);

        assertEquals("[5, 2, 19]", l.toString());
    }
}
