package ue4;

import org.junit.Test;
import ue4.versuch1.EdgeUG;
import ue4.versuch1.NodeUG;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EdgeUGTest {

    @Test
    public void test() {
        NodeUG node1 = new NodeUG("Knoten 1");
        NodeUG node2 = new NodeUG("Knoten 2");
        EdgeUG e = new EdgeUG(5.0, node1, node2);
        EdgeUG e2 = new EdgeUG(5.0, node2, node1);
        EdgeUG e3 = new EdgeUG(7.0, node2, node1);
        assertEquals(e, e2);
        assertNotEquals(e2, e3);
        assertEquals(5.0, e.getWeight(), 0.0000001);
        assertEquals(node1, e.getNode1());
        assertEquals(node2, e.getNode2());
    }
}
