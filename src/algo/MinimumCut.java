package algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MinimumCut {
    List<AdjacentVertices> vertexList;
    List<Edge> edgeList;

    public static void main(String[] args) {
        int i = 0;
        int trials = 10000;
        long start = System.currentTimeMillis();
        int min = Integer.MAX_VALUE;
        while(i++ < trials) {
            File f = new File("C:\\Users\\Mayank Singh\\Downloads\\kargerMinCut.txt");
            MinimumCut mc = new MinimumCut();
            try {
                mc.readInput(f);
            } catch (Exception ex) {
                System.out.println("Bummer");
                ex.printStackTrace();
            }

            min = Math.min(min, mc.cut());
        }
        System.out.println("Min Cut is : " + min);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public MinimumCut() {
        vertexList = new ArrayList<>();
        edgeList = new ArrayList<>();
    }

    /**
     * reads the input
     * @param f the file which contains the representation of the graph in an adjacency list
     * @throws FileNotFoundException
     */
    public void readInput(File f) throws FileNotFoundException {
        Scanner in = new Scanner(f);
        int i = 0;
        while (in.hasNext()) {
            Scanner line = new Scanner(in.nextLine());
            AdjacentVertices av = new AdjacentVertices();
            while (line.hasNextInt()) {
                int vertex = line.nextInt() - 1;
                if (vertex == i) continue;
                av.add(vertex);
                Edge ed;
                if (vertex > i) {
                    ed = new Edge(i, vertex);
                    edgeList.add(ed);
                }
            }
            vertexList.add(av);
            i++;
        }
    }

    public String toString() {
        StringBuilder ans = new StringBuilder();
            ans.append(noVertices() + " Vertex :\n");
        for(int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i) != null)
                ans.append(i + "\t" + vertexList.get(i).toString() + "\n");
        }
        ans.append(noEdges() + " algo.Edge :\n");
        for(int i = 0; i < edgeList.size(); i++) {
            if(edgeList.get(i) != null)
                ans.append(i + "\t" + edgeList.get(i).toString() + "\n");
        }
        return ans.toString();
    }

    private int noVertices() {
        int n = 0;
        for(int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i) != null) n++;
        }
        return n;
    }
    private int noEdges() {
        int n = 0;
        for(int i = 0; i < edgeList.size(); i++) {
            if(edgeList.get(i) != null) n++;
        }
        return n;
    }

    private int chooseEdge() {
        int edge;
        do {
            edge = (int) (Math.random()*edgeList.size());
        } while(edgeList.get(edge) == null);
        return edge;
    }

    private void deleteSelfLoops() {
        for(int i = 0; i < edgeList.size(); i++){
            if(edgeList.get(i) == null) continue;
            if(edgeList.get(i).isSelfLoop()){
                edgeList.add(i, null);
                edgeList.remove(i+1);
            }
        }
    }
    public int cut() {
        while(noVertices() > 2) {
//            System.out.println(toString()); //Debug
            int n = chooseEdge();
            Edge chosenOne = edgeList.get(n);
            edgeList.add(n, null);
            edgeList.remove(chosenOne);
            int vertexOne = chosenOne.getVertexOne();
            int vertexTwo = chosenOne.getVertexTwo();
            vertexList.add(vertexTwo, null);
            vertexList.remove(vertexTwo+1);
            for(Edge ed : edgeList) {
                if(ed != null)
                    ed.changeVetex(vertexTwo, vertexOne);
            }
            deleteSelfLoops();
            for(AdjacentVertices v : vertexList) {
                if(v != null) {
                    v.remove(vertexTwo);
                    v.add(vertexOne);
                }
            }
        }
        return noEdges();
    }
}