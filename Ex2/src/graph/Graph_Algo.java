package graph;
import api.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;



public class Graph_Algo implements DirectedWeightedGraphAlgorithms {
    private Graph graph;

    public Graph_Algo() {
        this.graph = new Graph();
        }

    @Override
    public void init(DirectedWeightedGraph g) {
        for (int i = 0 ; i< g.nodeSize() ; i++)
            this.graph.addNode(g.getNode(i));
        Iterator<EdgeData> edges = g.edgeIter();
        while (edges.hasNext()){
            Edge edge = new Edge(edges.next());
            int src =edge.getSrc();
            int dest =edge.getDest();
            double w =edge.getWeight();
            this.graph.connect(src,dest,w);
             }
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
            Edge edge = new Edge(node.next());
            dist[edge.getDest()] = edge.getWeight();
            edge.setTag(src);
            if (edge.getWeight()<min){
                min = edge.getWeight();
                pointer = edge.getDest();
            }
        }
        gr.removeNode(src);
        for (int i = 0 ; i < gr.nodeSize()-1;i++){
            node = gr.edgeIter(pointer);
            while(node.hasNext()) {
                Edge edge = new Edge(node.next());
                if (dist[edge.getSrc()] == Double.POSITIVE_INFINITY || dist[pointer] + gr.getEdge(pointer,edge.getDest()).getWeight() < dist[edge.getDest()]) {
                    dist[edge.getDest()] = dist[pointer] + gr.getEdge(pointer,edge.getDest()).getWeight();
                    gr.getNode(edge.getDest()).setTag(edge.getSrc());
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
            Edge edge = new Edge(node.next());
            dist[edge.getDest()] = edge.getWeight();
            edge.setTag(src);
            if (edge.getWeight()<min){
                min = edge.getWeight();
                pointer = edge.getDest();
            }
        }
        gr.removeNode(src);
        for (int i = 0 ; i < gr.nodeSize()-1;i++){
            node = gr.edgeIter(pointer);
            while(node.hasNext()) {
                Edge edge = new Edge(node.next());
                if (dist[edge.getSrc()] == Double.POSITIVE_INFINITY || dist[pointer] + gr.getEdge(pointer,edge.getDest()).getWeight() < dist[edge.getDest()]) {
                    dist[edge.getDest()] = dist[pointer] + gr.getEdge(pointer,edge.getDest()).getWeight();
                    gr.getNode(edge.getDest()).setTag(edge.getSrc());
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
                    Edge edge = new Edge(node.next());
                    if (edge.getWeight() < min) {
                        min = edge.getWeight();
                        pointer = edge.getDest();
                        dist[j] = dist[j] + gr.getNode(edge.getDest()).getWeight();
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
                Edge edge = new Edge(node.next());
                if (edge.getWeight() < min) {
                    min = edge.getWeight();
                    pointer = edge.getDest();
                }

            }
            ans.add(graph.getNode(pointer));
            gr.removeNode(currNode);
        }
            return ans;
    }
    @Override
    public boolean save(String file) {
            try {
                FileWriter json_file = new FileWriter(file);
                BufferedWriter b = new BufferedWriter(json_file);
                b.write(this.graph.toString());
                b.close();
                json_file.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }



    @Override
        public boolean load(String file) {
            try {
                //load the json to general object
                Object object = new JSONParser().parse(new FileReader(file));
                JSONObject json = (JSONObject) object;
                //insert nodes list to a json array
                JSONArray jsonNodes = (JSONArray) json.get("Nodes");
                Iterator nodes = jsonNodes.iterator();
                Map mapNode;
            HashMap<Integer,NodeData> nodesMap =new HashMap<Integer, NodeData>();
            while (nodes.hasNext()) {
                //add every single nodes in the array with convert him to the right type
                    mapNode = (Map) nodes.next();
                    int key = Integer.parseInt(Objects.toString(mapNode.get("id")));
                    String [] pos = ((String) mapNode.get("pos")).split(",");
                    double x = Double.parseDouble(pos[0]);
                    double y = Double.parseDouble(pos[1]);
                    double z = Double.parseDouble(pos[2]);
                    Location location = new Location(x,y,z);
                    Node node = new Node(key,location,0,"",0);
                nodesMap.put(key,node);
            }
            HashMap<Point2D,EdgeData> edgesMap =new HashMap<Point2D, EdgeData>();
            JSONArray jsonEdges = (JSONArray) json.get("Edges");
            Iterator edges = jsonEdges.iterator();
                Map mapEdge;
                while (edges.hasNext()) {
                    mapEdge = (Map) edges.next();
                    int src = Integer.parseInt(Objects.toString(mapEdge.get("src")));
                    int dest = Integer.parseInt(Objects.toString(mapEdge.get("dest")));
                    double w = Double.parseDouble(Objects.toString(mapEdge.get("w")));
                    EdgeData edge = new Edge(src,dest,w);
                    Point2D p = new Point(src,dest);
                    edgesMap.put(p,edge);
                }
            DirectedWeightedGraph graph = new Graph(edgesMap,nodesMap);
            this.init(graph);
                return true;
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }
    @Override
    public String toString() {
        return "Graph_Algo{" +
                "graph=" + graph +
                '}';
    }
}