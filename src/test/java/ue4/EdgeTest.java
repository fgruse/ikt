package ue4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EdgeTest {

    @Test
    public void test() {
        Node start = new Node("Knoten 1");
        Node end = new Node("Knoten 2");
        Edge e = new Edge(5.0, start, end);
        assertEquals(5.0, e.getWeight(), 0.0000001);
        assertEquals(start, e.getStart());
        assertEquals(end, e.getEnd());
    }
}
