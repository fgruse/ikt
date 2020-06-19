package astern;

import de.htwberlin.fiw.profiler.ProfiledClass;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AStern extends ProfiledClass {

    private final Graph graph;
    private final int startNodeIndex;
    private final int endNodeIndex;

    public AStern(final String file, final Integer startNodeIndex, final Integer endNodeIndex) {
        this.graph = fromFile(file);
        this.startNodeIndex = startNodeIndex;
        this.endNodeIndex = endNodeIndex;
    }

    public Node[] computeShortestPath() {
        Node[] allNodes = graph.getNodes();
        final Node startNode = allNodes[startNodeIndex];
        final Node endNode = allNodes[endNodeIndex];
        startNode.setDistanceFromStart(0.0);
        startNode.updatefScore(0.0);
        startNode.setParentNode(startNode);
        SortedNodeQueue queue = new SortedNodeQueue();
        queue.insert(startNode);

        while(queue.size()>0) {
            Node currentNode = queue.remove(); // TODO - rename ?
            if(!currentNode.isVisited()) {
                currentNode.setVisited(true);

                if(currentNode.equals(endNode)) {
                    return getPath(startNode, endNode);
                }

                final boolean[] isNeighbor = graph.getAdjacencyMatrix()[currentNode.getIndex()]; // TODO - rename?? refactor ????
                for (int i=0; i<isNeighbor.length; i++) {
                    if(isNeighbor[i]) {
                        Node currentNeighbor = allNodes[i];
                        // TODO - rename variables! refactor ?? own method ????
                        if(!currentNeighbor.isVisited()) {
                            double estimatedDistanceToEnd = Graph.getDistance(currentNeighbor, endNode);
                            double distanceCurrentNeighborToParent = Graph.getDistance(currentNeighbor, currentNode);
                            double distanceFromStart = currentNode.getDistanceFromStart() + distanceCurrentNeighborToParent;
                            double fScore = distanceFromStart + estimatedDistanceToEnd;
                            if(fScore < currentNeighbor.getfScore()) {
                                currentNeighbor.setParentNode(currentNode);
                                currentNeighbor.setDistanceFromStart(distanceFromStart);
                                currentNeighbor.updatefScore(estimatedDistanceToEnd);
                                queue.insert(currentNeighbor);
                            }
                        }
                    }
                }
            }
        }
        // TODO - or return null???
        return new Node[0];
    }

    // TODO - rename?
    static public Node[] getPath(Node startNode, Node endNode) {
        int length = endNode.getNumberOfSuccessors();
        Node[] path = new Node[length];
        int counter = length-1;
        while(!endNode.getParentNode().equals(endNode)){
            path[counter] = endNode;
            endNode = endNode.getParentNode();
            counter--;
        }
        path[0] = startNode;
        return path;
    }

    static public Graph fromFile(final String file) {
        Graph graph = new Graph(10000);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                String[] entry = line.split(",");
                if(entry[0].equals("O")) {
                    graph.insertNode(
                            Integer.parseInt(entry[1]),
                            Integer.parseInt(entry[2]),
                            Integer.parseInt(entry[3])
                    );
                }
                else {
                    graph.insertEdge(
                            Integer.parseInt(entry[1]),
                            Integer.parseInt(entry[2])
                    );
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    // TODO - refactor? class Path ????
    static public double getPathLength(Node[] weg) {
        return weg[weg.length-1].getDistanceFromStart();
    }

    @Override
    public void run() {
        Node[] shortestPath = this.computeShortestPath();
        int numberOfNodesInPath = shortestPath.length;
        if(numberOfNodesInPath>0) {
            // TODO - class path toSTring method ??
            StringBuilder pathString = new StringBuilder();
            for(final Node node : shortestPath) {
                pathString.append(node.getIndex()).append(" ");
            }
            System.out.println("Weg gefunden der Laenge " + getPathLength(shortestPath));
            System.out.println();
            System.out.println("Weg: ( " + pathString + ")");
        }
        else {
            System.out.println("Leider kein Weg gefunden!");
        }
    }
}
