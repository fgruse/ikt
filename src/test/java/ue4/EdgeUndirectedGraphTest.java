package ue4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EdgeUndirectedGraphTest {

    @Test
    public void test() {
        NodeUndirectedGraph node1 = new NodeUndirectedGraph("Knoten 1");
        NodeUndirectedGraph node2 = new NodeUndirectedGraph("Knoten 2");
        EdgeUndirectedGraph e = new EdgeUndirectedGraph(5.0, node1, node2);
        EdgeUndirectedGraph e2 = new EdgeUndirectedGraph(5.0, node2, node1);
        EdgeUndirectedGraph e3 = new EdgeUndirectedGraph(7.0, node2, node1);
        assertEquals(e, e2);
        assertNotEquals(e2, e3);
        assertEquals(5.0, e.getWeight(), 0.0000001);
        assertEquals(node1, e.getNode1());
        assertEquals(node2, e.getNode2());
    }
}
