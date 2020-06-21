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

        // set
        Profiler profiler3 = new Profiler(ProfilerSet.class);
        profiler3.start();
        profiler3.printResults();

        // hashmap
        Profiler profiler4 = new Profiler(ProfilerHM.class);
        profiler4.start();
        profiler4.printResults();
    }
}