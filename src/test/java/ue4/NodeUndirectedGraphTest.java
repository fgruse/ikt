package ue4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NodeUndirectedGraphTest {

    @Test
    public void test() {
        NodeUndirectedGraph n = new NodeUndirectedGraph("Knoten 1");
        assertEquals("Knoten 1", n.getName());
        assertEquals(0, n.getAdjacentNodes().getSize());
        assertTrue(n.isLeaf());
        assertEquals(0, n.getDegree());

        NodeUndirectedGraph n2 = new NodeUndirectedGraph("Knoten 2");
        n.addEdge(n2, 4.0);
        n2.addEdge(n, 4.0);

        assertEquals(1, n.getAdjacentNodes().getSize());
        assertTrue(n.isLeaf());

        assertEquals(1, n2.getAdjacentNodes().getSize());
        assertTrue(n2.isLeaf());

        assertEquals(1, n.getDegree());
        assertEquals(1, n2.getDegree());

        n2.addEdge(new NodeUndirectedGraph("Knoten 3"), 5.0);
        assertEquals(2, n2.getDegree());
        assertFalse(n2.isLeaf());

        // testen, dass nicht doppelt eingefügt werden kann & dass equals nur auf dem namen (identifier) beruht

        NodeUndirectedGraph n3 = new NodeUndirectedGraph("Knoten 2");
        n.addEdge(n3, 7.0);
        n.addEdge(n2, 7.0);
        assertTrue(n.isLeaf());
        assertEquals(n2, n3);
        assertEquals(1, n.getAdjacentNodes().getSize());
    }
}
