package astern;

import de.htwberlin.fiw.profiler.ProfiledClass;
import de.htwberlin.fiw.profiler.Profiler;
import java.util.ArrayList;

public class Main extends ProfiledClass {
    public static void main(String[] args) {
        Profiler profiler = new Profiler(Main.class);
        profiler.start();
        profiler.printResults();
    }

    @Override
    public void run() {
        AStern a = new AStern();
        final ArrayList<Knoten> weg = a.astern("src/main/java/astern/ortschaften-demo.csv", 0, 1);
        int l = 0;
        System.out.println("Weg gefunden der Laenge " + l);
        System.out.println("Weg: ( " + weg + " )");
    }
}
