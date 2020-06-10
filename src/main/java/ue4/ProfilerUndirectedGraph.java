package ue4;

import de.htwberlin.fiw.profiler.ProfiledClass;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import ue4.versuch1.DirectedGraph;
import ue4.versuch1.UndirectedGraph;

public class ProfilerUndirectedGraph extends ProfiledClass {

    @Override
    public void run() {
        UndirectedGraph g = new UndirectedGraph();

        URL url = ProfilerUndirectedGraph.class.getResource("demo_nodes1.csv");
        File csvFile = new File(url.getPath());
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] edgeOrNode = line.split(cvsSplitBy);
                if(edgeOrNode[0].equals("N")){
                    g.insertNode(edgeOrNode[1]);
                    System.out.println("added Node");
                }
                else{
                    g.makeEdge(edgeOrNode[1], edgeOrNode[2], Double.parseDouble(edgeOrNode[3]));
                    System.out.println("added Edge");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("starting calculation for MST");
        final DirectedGraph minimalSpanningTree = g.getMinimalSpanningTree();
        System.out.println(minimalSpanningTree.getWeight());
    }
}
