package ue1;

import de.htwberlin.fiw.profiler.ProfiledClass;
import java.util.Random;

public class ProfilerEinfachVerketteteListe  extends ProfiledClass {

    // um zwischen den Implementierungen zu wechseln, die nicht gewünschte auskommentieren und eine "aktivieren"
    public void run() {
        // version 1
        //einfachVerketteteListe liste = new einfachVerketteteListe();

        // version 2 (optimiert)
        //optimierteEinfachVerketteteListe liste = new optimierteEinfachVerketteteListe();

        // version 3 (optimiert)
        ZweiteOptimierteEinfachVerketteteListe liste = new ZweiteOptimierteEinfachVerketteteListe();

        Random r = new Random();
        for(int i=0; i<10000; i++) {
            liste.append(r.nextInt());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Anfang bis Ende:");
        Element pointer = liste.getHead();
        while (pointer.getNext() != null) {
            pointer = pointer.getNext();
            System.out.println(pointer.getData());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Ende bis Anfang:");
        for(int i=9999; i>=0; i--) {
            System.out.println(liste.get(i));
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
    }
}
