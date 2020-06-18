package astern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ASternTest {

    @Test
    public void testcase1() {
        AStern a = new AStern("src/main/java/astern/ortschaften-demo.csv", 0, 1);
        final Knoten[] weg = a.computeShortestPath();
        assertEquals(0, weg[0].getIndex());
        assertEquals(4404, weg[1].getIndex());
        assertEquals(8365, weg[2].getIndex());
        assertEquals(1847, weg[3].getIndex());
        assertEquals(9602, weg[4].getIndex());
        assertEquals(2089, weg[5].getIndex());
        assertEquals(8179, weg[6].getIndex());
        assertEquals(6431, weg[7].getIndex());
        assertEquals(8810, weg[8].getIndex());
        assertEquals(6289, weg[9].getIndex());
        assertEquals(1176, weg[10].getIndex());
        assertEquals(1, weg[11].getIndex());
    }

    @Test
    public void testcase2() {
        AStern a = new AStern("src/main/java/astern/ortschaften-demo.csv", 0, 9999);
        final Knoten[] weg = a.computeShortestPath();
        assertEquals(0, weg[0].getIndex());
        assertEquals(5960, weg[1].getIndex());
        assertEquals(6126, weg[2].getIndex());
        assertEquals(7464, weg[3].getIndex());
        assertEquals(7300, weg[4].getIndex());
        assertEquals(5089, weg[5].getIndex());
        assertEquals(8024, weg[6].getIndex());
        assertEquals(9107, weg[7].getIndex());
        assertEquals(8968, weg[8].getIndex());
        assertEquals(3302, weg[9].getIndex());
        assertEquals(8124, weg[10].getIndex());
        assertEquals(5214, weg[11].getIndex());
        assertEquals(9999, weg[12].getIndex());
    }

    @Test
    public void testcase3() {
        AStern a = new AStern("src/main/java/astern/ortschaften-demo.csv", 2000, 3000);
        final Knoten[] weg = a.computeShortestPath();
        assertEquals(2000, weg[0].getIndex());
        assertEquals(3237, weg[1].getIndex());
        assertEquals(9051, weg[2].getIndex());
        assertEquals(2443, weg[3].getIndex());
        assertEquals(9281, weg[4].getIndex());
        assertEquals(4593, weg[5].getIndex());
        assertEquals(8110, weg[6].getIndex());
        assertEquals(7902, weg[7].getIndex());
        assertEquals(5944, weg[8].getIndex());
        assertEquals(3482, weg[9].getIndex());
        assertEquals(3000, weg[10].getIndex());
    }

    @Test
    public void testcase4() {
        AStern a = new AStern("src/main/java/astern/ortschaften-demo.csv", 3361, 9261);
        final Knoten[] weg = a.computeShortestPath();
        assertEquals(3361, weg[0].getIndex());
        assertEquals(380, weg[1].getIndex());
        assertEquals(5767, weg[2].getIndex());
        assertEquals(4120, weg[3].getIndex());
        assertEquals(8134, weg[4].getIndex());
        assertEquals(3836, weg[5].getIndex());
        assertEquals(3000, weg[6].getIndex());
        assertEquals(3482, weg[7].getIndex());
        assertEquals(8039, weg[8].getIndex());
        assertEquals(9083, weg[9].getIndex());
        assertEquals(8901, weg[10].getIndex());
        assertEquals(4400, weg[11].getIndex());
        assertEquals(3204, weg[12].getIndex());
        assertEquals(1088, weg[13].getIndex());
        assertEquals(4848, weg[14].getIndex());
        assertEquals(6146, weg[15].getIndex());
        assertEquals(832, weg[16].getIndex());
        assertEquals(1489, weg[17].getIndex());
        assertEquals(4203, weg[18].getIndex());
        assertEquals(9790, weg[19].getIndex());
        assertEquals(9261, weg[20].getIndex());
    }

    @Test
    public void testcase5() {
        AStern a = new AStern("src/main/java/astern/ortschaften-demo.csv", 20, 505);
        final Knoten[] weg = a.computeShortestPath();
        StringBuilder wegString = new StringBuilder();
        for(final Knoten knoten : weg) {
            wegString.append(knoten.getIndex()).append(" ");
        }
        assertEquals("20 4920 505 ", wegString.toString());
    }

    @Test
    public void testcase6() {
        AStern a = new AStern("src/main/java/astern/ortschaften-demo.csv", 3243, 5875);
        final Knoten[] weg = a.computeShortestPath();
        StringBuilder wegString = new StringBuilder();
        for(final Knoten knoten : weg) {
            wegString.append(knoten.getIndex()).append(" ");
        }
        assertEquals("3243 404 6757 6849 2499 447 7022 4750 7017 5875 ", wegString.toString());
    }

    @Test
    public void testcase7() {
        AStern a = new AStern("src/main/java/astern/ortschaften-demo.csv", 200, 801);
        final Knoten[] weg = a.computeShortestPath();
        StringBuilder wegString = new StringBuilder();
        for(final Knoten knoten : weg) {
            wegString.append(knoten.getIndex()).append(" ");
        }
        assertEquals("200 7859 7713 2955 4567 2331 3315 2062 7523 801 ", wegString.toString());
    }

    @Test
    public void testcase8() {
        AStern a = new AStern("src/main/java/astern/ortschaften-demo.csv", 90, 1111);
        final Knoten[] weg = a.computeShortestPath();
        StringBuilder wegString = new StringBuilder();
        for(final Knoten knoten : weg) {
            wegString.append(knoten.getIndex()).append(" ");
        }
        assertEquals("90 4441 5872 8024 129 1008 887 2826 1890 4794 8964 1111 ", wegString.toString());
    }

    @Test
    public void testcase9() {
        AStern a = new AStern("src/main/java/astern/ortschaften-demo.csv", 9900, 3420);
        final Knoten[] weg = a.computeShortestPath();
        StringBuilder wegString = new StringBuilder();
        for(final Knoten knoten : weg) {
            wegString.append(knoten.getIndex()).append(" ");
        }
        assertEquals("9900 3678 9532 3236 5971 2192 1286 2319 3687 3560 1637 6186 5818 1512 6886 3420 ", wegString.toString());
    }

    @Test
    public void testcase10() {
        AStern a = new AStern("src/main/java/astern/ortschaften-demo-klein.csv", 0, 1);
        final Knoten[] weg = a.computeShortestPath();
        assertEquals(0, weg.length);
    }

    @Test
    public void testcase11() {
        AStern a = new AStern("src/main/java/astern/ortschaften-demo-klein.csv", 3, 2);
        final Knoten[] weg = a.computeShortestPath();
        StringBuilder wegString = new StringBuilder();
        for(final Knoten knoten : weg) {
            wegString.append(knoten.getIndex()).append(" ");
        }
        assertEquals("3 1 2 ", wegString.toString());
    }
}
