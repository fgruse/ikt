package ue1;

import de.htwberlin.fiw.profiler.ProfiledClass;
import de.htwberlin.fiw.profiler.Profiler;
import java.util.Random;

public class Main extends ProfiledClass
{

    public static void main(String[] args) {
        // Profiler profiler = new Profiler(Main.class);
        Profiler profiler = new Profiler(ProfilerEinfachVerketteteListe.class);
        profiler.start();
        profiler.printResults();
    }

    // run-Methode aus Ü1
    public void run() {
        long ergebnis = 1;
        for (long i = 1; i < 1000000000; i++) {
            ergebnis += i;
        }
        System.out.println("Ergebnis ist: " + ergebnis);
    }
}