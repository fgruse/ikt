package astern;

import de.htwberlin.fiw.profiler.Profiler;

public class Main {
    public static void main(String[] args) {
        Profiler profiler = new Profiler(AStern.class, "src/main/java/astern/ortschaften-demo.csv", 0, 1);
        profiler.start();
        profiler.printResults();
    }
}
