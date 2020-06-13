package ue4.versuch1;

import org.junit.Test;
import ue4.versuch1.EdgeDG;
import ue4.versuch1.NodeDG;

import static org.junit.Assert.assertEquals;

public class EdgeDGTest {

    @Test
    public void test() {
        NodeDG start = new NodeDG("Knoten 1");
        NodeDG end = new NodeDG("Knoten 2");
        EdgeDG e = new EdgeDG(5.0, start, end);
        assertEquals(5.0, e.getWeight(), 0.0000001);
        assertEquals(start, e.getStart());
        assertEquals(end, e.getEnd());
    }
}
