package astern;

import org.junit.Test;

import static astern.AStern.*;

public class ASternTest {

    @Test
    public void test() {
        AStern a = new AStern();
        a.astern("ortschaften-demo-klein.csv", 0, 1);
        int b = 1;
    }
}
