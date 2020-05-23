package ue1;

import de.htwberlin.fiw.profiler.ProfiledClass;
import java.util.Random;

public class ProfilerFeldListe  extends ProfiledClass {

    public void run() {
        FeldListe liste = new FeldListe();

        Random r = new Random();
        for(int i=0; i<10000; i++) {
            liste.append(r.nextInt());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Anfang bis Ende:");
        for(int i=0; i<liste.size(); i++) {
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