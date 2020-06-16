package astern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ASternTest {

    @Test
    public void testcase1() {
        AStern a = new AStern("ortschaften-demo.csv", 0, 1);
        final KnotenListe weg = a.computeShortestPath();
        assertEquals(0, weg.get(0).getIndex());
        assertEquals(4404, weg.get(1).getIndex());
        assertEquals(8365, weg.get(2).getIndex());
        assertEquals(1847, weg.get(3).getIndex());
        assertEquals(9602, weg.get(4).getIndex());
        assertEquals(2089, weg.get(5).getIndex());
        assertEquals(8179, weg.get(6).getIndex());
        assertEquals(6431, weg.get(7).getIndex());
        assertEquals(8810, weg.get(8).getIndex());
        assertEquals(6289, weg.get(9).getIndex());
        assertEquals(1176, weg.get(10).getIndex());
        assertEquals(1, weg.get(11).getIndex());
    }

    @Test
    public void testcase2() {
        AStern a = new AStern("ortschaften-demo.csv", 0, 9999);
        final KnotenListe weg = a.computeShortestPath();
        assertEquals(0, weg.get(0).getIndex());
        assertEquals(5960, weg.get(1).getIndex());
        assertEquals(6126, weg.get(2).getIndex());
        assertEquals(7464, weg.get(3).getIndex());
        assertEquals(7300, weg.get(4).getIndex());
        assertEquals(5089, weg.get(5).getIndex());
        assertEquals(8024, weg.get(6).getIndex());
        assertEquals(9107, weg.get(7).getIndex());
        assertEquals(8968, weg.get(8).getIndex());
        assertEquals(3302, weg.get(9).getIndex());
        assertEquals(8124, weg.get(10).getIndex());
        assertEquals(5214, weg.get(11).getIndex());
        assertEquals(9999, weg.get(12).getIndex());
    }

    @Test
    public void testcase3() {
        AStern a = new AStern("ortschaften-demo.csv", 2000, 3000);
        final KnotenListe weg = a.computeShortestPath();
        assertEquals(2000, weg.get(0).getIndex());
        assertEquals(3237, weg.get(1).getIndex());
        assertEquals(9051, weg.get(2).getIndex());
        assertEquals(2443, weg.get(3).getIndex());
        assertEquals(9281, weg.get(4).getIndex());
        assertEquals(4593, weg.get(5).getIndex());
        assertEquals(8110, weg.get(6).getIndex());
        assertEquals(7902, weg.get(7).getIndex());
        assertEquals(5944, weg.get(8).getIndex());
        assertEquals(3482, weg.get(9).getIndex());
        assertEquals(3000, weg.get(10).getIndex());
    }

    @Test
    public void testcase4() {
        AStern a = new AStern("ortschaften-demo.csv", 3361, 9261);
        final KnotenListe weg = a.computeShortestPath();
        assertEquals(3361, weg.get(0).getIndex());
        assertEquals(380, weg.get(1).getIndex());
        assertEquals(5767, weg.get(2).getIndex());
        assertEquals(4120, weg.get(3).getIndex());
        assertEquals(8134, weg.get(4).getIndex());
        assertEquals(3836, weg.get(5).getIndex());
        assertEquals(3000, weg.get(6).getIndex());
        assertEquals(3482, weg.get(7).getIndex());
        assertEquals(8039, weg.get(8).getIndex());
        assertEquals(9083, weg.get(9).getIndex());
        assertEquals(8901, weg.get(10).getIndex());
        assertEquals(4400, weg.get(11).getIndex());
        assertEquals(3204, weg.get(12).getIndex());
        assertEquals(1088, weg.get(13).getIndex());
        assertEquals(4848, weg.get(14).getIndex());
        assertEquals(6146, weg.get(15).getIndex());
        assertEquals(832, weg.get(16).getIndex());
        assertEquals(1489, weg.get(17).getIndex());
        assertEquals(4203, weg.get(18).getIndex());
        assertEquals(9790, weg.get(19).getIndex());
        assertEquals(9261, weg.get(20).getIndex());
    }
}
