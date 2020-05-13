package ue2;

import de.htwberlin.fiw.profiler.ProfiledClass;
import java.util.Random;

public class ProfilerOptimiertesSet extends ProfiledClass {

    @Override
    public void run() {
        OptimiertesSet set = new OptimiertesSet();
        Random r = new Random();
        for(int i=0; i<10000; i++) {
            set.add(r.nextInt());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Anfang bis Ende:");
        Element pointer = set.getList().getHead();
        while (pointer.getNext().getData() != null) {
            pointer = pointer.getNext();
            System.out.println(pointer.getData());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Ende bis Anfang:");
        Element pointerTail = set.getList().getTail();
        while (pointerTail.getPrev().getData() != null) {
            pointerTail = pointerTail.getPrev();
            System.out.println(pointerTail.getData());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Anzahl der Elemente: " + set.getSize());
    }
}
