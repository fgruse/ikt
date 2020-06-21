package ue5;

import de.htwberlin.fiw.profiler.ProfiledClass;
import java.util.Random;
import ue1.Element;
import ue1.ZweiteOptimierteEinfachVerketteteListe;

public class ProfilerSLL extends ProfiledClass {

    public void run() {
        SinglyLinkedList<Integer> liste = new SinglyLinkedList<>();

        Random r = new Random();
        for(int i=0; i<10000; i++) {
            liste.append(r.nextInt());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Anfang bis Ende:");
        ElementSLL<Integer> pointer = liste.getHead();
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
