package api;
import java.util.*;
import java.io.*;
import java.util.List;
import com.google.gson.Gson;

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
        return count >= graph.nodeSize();
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

    public static int dfs (DirectedWeightedGraph graph, int v, boolean[] visited, int counter) {
        {
            visited[v] = true;
            Iterator<EdgeData> edges = graph.edgeIter(v);
            while (edges.hasNext()) {
                int dest = edges.next().getDest();
                if (!visited[dest]) {
                    counter++;
                    dfs(graph, dest, visited, counter);
                }
            }
        }
    return counter;
    }


    @Override
    public double shortestPathDist(int src, int dest) {
        int pointer = 0;
        double ans = 0;
        Graph gr = new Graph();
        gr = (Graph) copy();
        double[] dist = new double [gr.nodeSize()];
        for (int i = 0; i<gr.nodeSize(); i++){
            dist[i]=(Double.POSITIVE_INFINITY);
            gr.getNode(i).setTag(-1);
        }
        dist[src]=0;
        double min = Double.POSITIVE_INFINITY;
        Iterator<EdgeData> node = gr.edgeIter(src);
        while(node.hasNext()){
            dist[node.next().getDest()] = node.next().getWeight();
            node.next().setTag(src);
            if (node.next().getWeight()<min){
                min = node.next().getWeight();
                pointer = node.next().getDest();
            }
            node = (Iterator<EdgeData>) node.next();
        }
        gr.removeNode(src);
        for (int i = 0 ; i < gr.nodeSize()-1;i++){
            node = gr.edgeIter(pointer);
            while(node.hasNext()) {
                if (dist[node.next().getSrc()] == Double.POSITIVE_INFINITY || dist[pointer] + gr.getEdge(pointer,node.next().getDest()).getWeight() < dist[node.next().getDest()]) {
                    dist[node.next().getDest()] = dist[pointer] + gr.getEdge(pointer,node.next().getDest()).getWeight();
                    gr.getNode(node.next().getDest()).setTag(node.next().getSrc());
                }
            }
            gr.removeNode(pointer);
            pointer = i ;
        }

        while (graph.getNode(dest).getTag() != src) {
            ans = ans + dist[dest];
            dest = graph.getNode(dest).getTag();
        }
        return ans;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> ans = new LinkedList<>();
        int pointer = 0;
        Graph gr = new Graph();
        gr = (Graph) copy();
        double[] dist = new double [gr.nodeSize()];
        for (int i = 0; i<gr.nodeSize(); i++){
            dist[i]=(Double.POSITIVE_INFINITY);
            gr.getNode(i).setTag(-1);
        }
        dist[src]=0;
        double min = Double.POSITIVE_INFINITY;
        Iterator<EdgeData> node = gr.edgeIter(src);
        while(node.hasNext()){
            dist[node.next().getDest()] = node.next().getWeight();
            node.next().setTag(src);
            if (node.next().getWeight()<min){
                min = node.next().getWeight();
                pointer = node.next().getDest();
            }
            node = (Iterator<EdgeData>) node.next();
        }
        gr.removeNode(src);
        for (int i = 0 ; i < gr.nodeSize()-1;i++){
            node = gr.edgeIter(pointer);
            while(node.hasNext()) {
                if (dist[node.next().getSrc()] == Double.POSITIVE_INFINITY || dist[pointer] + gr.getEdge(pointer,node.next().getDest()).getWeight() < dist[node.next().getDest()]) {
                    dist[node.next().getDest()] = dist[pointer] + gr.getEdge(pointer,node.next().getDest()).getWeight();
                    gr.getNode(node.next().getDest()).setTag(node.next().getSrc());
                }
            }
            gr.removeNode(pointer);
            pointer = i ;
        }
        ans.add(graph.getNode(dest));
        while (graph.getNode(dest).getTag() != src) {
            ans.add(graph.getNode(dest));
            dest = graph.getNode(dest).getTag();
        }
        ans.add(graph.getNode(src));
        Collections.reverse(ans);
        return ans;
    }

    @Override
    public NodeData center() {
        if (!this.isConnected()) {
            return null;
        }
        double[] dist = new double[graph.nodeSize()];
        for (int i = 0; i < graph.nodeSize(); i++) {
            dist[i] =0;
            for (int j = 0; j < graph.nodeSize(); j++) {
                dist [i] = dist[i]+shortestPathDist(i,j);
            }
        }
        int pointer =0;
        double ans =Double.MAX_VALUE;
        for (int i = 0; i < graph.nodeSize(); i++) {
            if (dist[i]<ans){
                ans = dist[i];
                pointer = i;
            }
        }
        return graph.getNode(pointer);
        }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List<NodeData> ans = new LinkedList<>();
        Graph gr = new Graph();
        gr = (Graph) copy();
        double min =Double.POSITIVE_INFINITY;
        int pointer = 0;
        int currNode;
        double[] dist = new double[cities.size()];
        for (int i = 0; i < cities.size(); i++) {
        dist [i]=0;
        }
        for (int j = 0; j <cities.size(); j++) {
            currNode = pointer;
            gr = (Graph) copy();
            for (int i = 0; i < cities.size() - 1; i++) {
                Iterator<EdgeData> node = gr.edgeIter(pointer);
                while (node.hasNext()) {
                    if (node.next().getWeight() < min) {
                        min = node.next().getWeight();
                        pointer = node.next().getDest();
                        dist[j] = dist[j] + gr.getNode(node.next().getDest()).getWeight();
                    }
                }
                gr.removeNode(currNode);
            }
        }
        min = Double.POSITIVE_INFINITY;
        for (int j = 0; j <cities.size(); j++) {
            if (dist[j]<min){
                min = dist[j];
                pointer = j;
            }
        }
        ans.add(graph.getNode(pointer));
        for (int i = 0; i < cities.size() ; i++) {
            currNode = pointer;
            Iterator<EdgeData> node = gr.edgeIter(pointer);
            while (node.hasNext()) {
                if (node.next().getWeight() < min) {
                    min = node.next().getWeight();
                    pointer = node.next().getDest();
                }

            }
            ans.add(graph.getNode(pointer));
            gr.removeNode(currNode);
        }
            return ans;
    }
    @Override
    public boolean save(String file) {
        return true;
    }

    @Override
    public boolean load(String file) {
        return false;
    }

   }
