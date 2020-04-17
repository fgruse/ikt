package ue1;

import de.htwberlin.fiw.profiler.ProfiledClass;
import de.htwberlin.fiw.profiler.Profiler;
import java.util.Random;

public class Main extends ProfiledClass
{

    public static void main(String[] args) {
        Profiler profiler = new Profiler(Main.class);
        profiler.start();
        profiler.printResults();
    }

    // run-Methode aus Ü1
    /*
    public void run() {
        long ergebnis = 1;
        for (long i = 1; i < 1000000000; i++) {
            ergebnis += i;
        }
        System.out.println("Ergebnis ist: " + ergebnis);
    }
    */

    //run-Methode für einfachVerketteteListe
    public void run() {
        // version 1
        einfachVerketteteListe liste = new einfachVerketteteListe();

        //version 2 (optimiert)
        //optimierteEinfachVerketteteListe liste = new optimierteEinfachVerketteteListe();

        Random r = new Random();
        for(int i=0; i<10000; i++) {
            liste.append(r.nextInt());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Anfang bis Ende:");
        for(int i=0; i<10000; i++) {
            System.out.println(liste.get(i));
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Ende bis Anfang:");
        for(int i=9999; i>=0; i--) {
            System.out.println(liste.get(i));
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
    }

}