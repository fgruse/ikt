package ue2;

import de.htwberlin.fiw.profiler.ProfiledClass;
import java.util.Random;

public class ProfilerDoppeltVerketteteListe extends ProfiledClass {

    public void run() {
        doppeltVerketteteListe liste = new doppeltVerketteteListe();

        Random r = new Random();
        for(int i=0; i<10000; i++) {
            liste.append(r.nextInt());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Anfang bis Ende:");
        Element pointer = liste.getHead();
        while (pointer.getNext().getData() != null) {
            pointer = pointer.getNext();
            System.out.println(pointer.getData());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Ende bis Anfang:");
        Element pointerTail = liste.getTail();
        while (pointerTail.getPrev().getData() != null) {
            pointerTail = pointerTail.getPrev();
            System.out.println(pointerTail.getData());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
    }
}
