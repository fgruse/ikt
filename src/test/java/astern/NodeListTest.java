package astern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NodeListTest {

    // Testen der prepend-Methode (hinzufügen am Anfang der Liste)

    @Test
    public void testPrepend() {
        NodeList l = new NodeList();

        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);

        // bei leerer Liste
        l.prepend(node1);
        assertEquals(1 , l.size());
        assertEquals(node1, l.get(0));

        // bei nicht-leerer Liste
        l.prepend(node2);
        assertEquals(2 , l.size());
        assertEquals(node2, l.get(0));
        assertEquals(node1, l.get(1));
    }

    // Testen der append-Methode (hinzufügen am Ende der Liste)

    @Test
    public void testAppend() {
        NodeList l = new NodeList();

        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);

        // bei leerer Liste
        l.append(node1);
        assertEquals(1 , l.size());
        assertEquals(node1, l.get(0));

        // bei nicht-leerer Liste
        l.append(node2);
        l.append(node3);
        assertEquals(3 , l.size());
        assertEquals(node1, l.get(0));
        assertEquals(node2, l.get(1));
        assertEquals(node3, l.get(2));
    }

    // Testen der insert-Methode (einfügen an einem bestimmten Index)

    @Test
    public void testInsert() {
        NodeList l = new NodeList();
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

        // bei nicht-leerer Liste, einfügen am ende
        Node node4 = new Node(5,8,9);
        l.insert(l.size(), node4);
        assertEquals(4, l.size());
        assertEquals(node3, l.get(1));
        assertEquals(node2, l.get(0));
        assertEquals(node1, l.get(2));
        assertEquals(node4, l.get(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertAtIndexOutOfBoundsNegative() {
        NodeList l = new NodeList();
        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);
        l.append(node1);
        l.append(node2);
        l.append(node3);
        l.insert(-1, node1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertAtIndexOutOfBoundsTooBig() {
        NodeList l = new NodeList();
        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);
        l.append(node1);
        l.append(node2);
        l.append(node3);
        l.insert(100, node1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testaInsertAtIndexOutOfBoundsTooBigEmptyList() {
        NodeList l = new NodeList();
        Node node1 = new Node(0, 3, 5);
        l.insert(1, node1);
    }

    // Testen der get-Methode

    @Test
    public void testGet() {
        NodeList l = new NodeList();
        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);
        l.append(node1);
        l.append(node2);
        l.append(node3);
        assertEquals(node1, l.get(0));
        assertEquals(node2, l.get(1));
        assertEquals(node3, l.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBoundsNegative() {
        NodeList l = new NodeList();
        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);
        l.append(node1);
        l.append(node2);
        l.append(node3);
        l.get(-100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBoundsTooBig() {
        NodeList l = new NodeList();
        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);
        l.append(node1);
        l.append(node2);
        l.append(node3);
        l.get(100);
    }

    // Testen der remove-Methode an einem bestimmten Index

    @Test
    public void testRemove() {
        NodeList l = new NodeList();
        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);
        l.append(node1);
        l.append(node2);
        l.append(node3);
        l.remove(1);
        assertEquals(2 , l.size());
        assertEquals(node1, l.get(0));
        assertEquals(node3, l.get(1));
    }

    @Test
    public void testRemoveAtIndex0() {
        NodeList l = new NodeList();
        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);
        l.append(node1);
        l.append(node2);
        l.append(node3);
        l.remove(0);
        assertEquals(2 , l.size());
        assertEquals(node2, l.get(0));
        assertEquals(node3, l.get(1));
    }

    @Test
    public void testRemoveAtLastIndex() {
        NodeList l = new NodeList();
        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);
        l.append(node1);
        l.append(node2);
        l.append(node3);
        l.remove(2);
        assertEquals(2 , l.size());
        assertEquals(node1, l.get(0));
        assertEquals(node2, l.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexOutOfBoundsNegative() {
        NodeList l = new NodeList();
        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);
        l.append(node1);
        l.append(node2);
        l.append(node3);
        l.remove(-100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexOutOfBoundsTooBig() {
        NodeList l = new NodeList();
        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);
        l.append(node1);
        l.append(node2);
        l.append(node3);
        l.remove(100);
    }

    // Testen der contains-Methode

    @Test
    public void testContains() {
        NodeList l = new NodeList();
        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);
        l.append(node1);
        l.append(node2);
        l.append(node3);
        assertTrue(l.contains(node1));
        assertTrue(l.contains(node2));
        assertTrue(l.contains(node3));
        assertFalse(l.contains(new Node(3,5,6)));
    }

    // Testen der size-Methode



    // Testen der toString-Methode

    @Test
    public void testToString() {
        NodeList l = new NodeList();

        Node node1 = new Node(0, 3, 5);
        Node node2 = new Node(1, 32, 54);
        Node node3 = new Node(2, 31, 52);

        assertEquals("[]", l.toString());

        l.append(node1);
        l.append(node2);
        l.append(node3);

        assertEquals("[0, 1, 2]", l.toString());
    }
}
