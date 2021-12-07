package api;

import java.nio.file.*;
import java.util.*;
import com.google.gson.*;
import java.io.*;
import java.util.List;

public class Graph_Algo implements DirectedWeightedGraphAlgorithms {
    private Graph graph;

    public Graph_Algo() {
        this.graph = new Graph();
        }

    @Override
    public void init(DirectedWeightedGraph g) {
        for (int i = 0 ; i< g.nodeSize() ; i++)
        this.graph.addNode(g.getNode(i));
        for (int i = 0 ; i< g.edgeSize() ; i++)
            this.graph.addEdge(i);
    }

    @Override
    public DirectedWeightedGraph getGraph() {
    return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        Graph copy = new Graph();
        for (int i = 0 ; i< graph.nodeSize() ; i++)
            copy.addNode(graph.getNode(i));
        for (int i = 0 ; i< graph.edgeSize() ; i++)
            copy.addEdge(i);
        return copy;
    }

    @Override
    public boolean isConnected() {
        int count = 0;
        boolean[] visited = new boolean[graph.nodeSize()];
        for (int i = 0; i < graph.nodeSize(); i++)
            visited[i] = false;
        count = dfs(graph, 0, visited,count);
        if(count < graph.nodeSize()){
            return false;
        }
        Graph reversedGr = reversedGraph(graph);
        count = 0;
        for (int i = 0; i < graph.nodeSize(); i++)
            visited[i] = false;
        count = dfs(reversedGr, 0, visited,count);
        if(count < graph.nodeSize())
            return false;
        return true;
    }

    public Graph reversedGraph(Graph graph) {
        Graph reversedGr = new Graph();
        for (int i = 0; i < graph.nodeSize(); i++) {
            reversedGr.addNode(graph.getNode(i));
        }
        for (int i = 0; i < graph.edgeSize(); i++) {
            int src = graph.getEdge(i).getDest();
            int dest = graph.getEdge(i).getSrc();
            double weight = graph.getEdge(i).getWeight();
            reversedGr.connect(src,dest,weight);
        }
        return reversedGr;
    }

    public static int dfs (DirectedWeightedGraph graph, int v, boolean visited [], int counter) {
        {
            visited[v] = true;
            Iterator<EdgeData> edges = graph.edgeIter(v);
            while (edges.hasNext()) {
                int dest = edges.next().getDest();
                if (visited[dest] == false) {
                    counter++;
                    dfs(graph, dest, visited, counter);
                }
            }
        }
    return counter;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if (src == dest)
            return 0;


        return -1;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
        }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }

   }
