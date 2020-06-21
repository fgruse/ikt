package ue5;

import de.htwberlin.fiw.profiler.ProfiledClass;
import java.util.Random;
import ue2.DoppeltVerketteteListe;
import ue2.Element;

public class ProfilerDLL extends ProfiledClass {

    public void run() {
        DoublyLinkedList<Integer> liste = new DoublyLinkedList<>();

        Random r = new Random();
        for(int i=0; i<10000; i++) {
            liste.append(r.nextInt());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Anfang bis Ende:");
        ElementDLL<Integer> pointer = liste.getHead();
        while (pointer.getNext().getData() != null) {
            pointer = pointer.getNext();
            System.out.println(pointer.getData());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Ende bis Anfang:");
        ElementDLL<Integer> pointerTail = liste.getTail();
        while (pointerTail.getPrev().getData() != null) {
            pointerTail = pointerTail.getPrev();
            System.out.println(pointerTail.getData());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - -");
    }
}
