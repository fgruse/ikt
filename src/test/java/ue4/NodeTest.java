package ue4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class NodeTest {

    @Test
    public void test() {
        Node n = new Node("Knoten 1");
        assertEquals("Knoten 1", n.getName());
        assertEquals(0, n.getChildren().getSize());
        assertTrue(n.isLeaf());
        assertTrue(n.isRoot());
        assertEquals(0, n.getDegree());
        assertEquals(0, n.getInputDegree());
        assertEquals(0, n.getOutputDegree());

        Node n2 = new Node("Knoten 2");
        n.addChild(n2);
        n2.addParent(n);

        assertEquals(1, n.getChildren().getSize());
        assertFalse(n.isLeaf());
        assertTrue(n.isRoot());

        assertEquals(0, n2.getChildren().getSize());
        assertTrue(n2.isLeaf());
        assertFalse(n2.isRoot());

        assertEquals(1, n.getDegree());
        assertEquals(0, n.getInputDegree());
        assertEquals(1, n.getOutputDegree());

        assertEquals(1, n2.getDegree());
        assertEquals(1, n2.getInputDegree());
        assertEquals(0, n2.getOutputDegree());

        n2.addChild(new Node("Knoten 3"));
        assertEquals(2, n2.getDegree());
        assertEquals(1, n2.getInputDegree());
        assertEquals(1, n2.getOutputDegree());

        // testen, dass nicht doppelt eingefügt werden kann & dass equals nur auf dem namen (identifier) beruht

        Node n3 = new Node("Knoten 2");
        n.addChild(n3);
        n.addChild(n2);
        assertEquals(n2, n3);
        assertEquals(1, n.getChildren().getSize());
    }
}
