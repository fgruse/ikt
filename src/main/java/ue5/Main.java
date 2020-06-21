package ue5;

import de.htwberlin.fiw.profiler.Profiler;

public class Main
{

    public static void main(String[] args) {
        // singly linked list
        Profiler profiler = new Profiler(ProfilerSLL.class);
        profiler.start();
        profiler.printResults();

        // doubly linked list
        Profiler profiler2 = new Profiler(ProfilerDLL.class);
        profiler2.start();
        profiler2.printResults();
    }
}