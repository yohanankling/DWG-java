package graph;
import api.*;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Graph implements DirectedWeightedGraph {
    private HashMap<Integer,NodeData> nodes;
    private HashMap<Point2D,EdgeData> edges;
    private int mcCounter;


    public Graph(){
        this.nodes=new HashMap<Integer, NodeData>();
        this.edges=new HashMap<Point2D, EdgeData>();
        this.mcCounter=0;
    }
    public Graph(HashMap<Point2D,EdgeData> w, HashMap<Integer, NodeData> n){
        this.edges=w;
        this.nodes=n;
        this.mcCounter=0;
    }

    @Override
    public NodeData getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        Point2D p1 =new Point(src,dest);
        if (edges.get(p1)!= null )
        return edges.get(p1);
        else return null;
    }

    public EdgeData getEdge(int i) {
        return edges.get(i);
    }

    public EdgeData addEdge(int i) {
        try {
            Point2D p1 =new Point(edges.get(i).getSrc(),edges.get(i).getDest());
            return edges.put(p1,edges.get(i));
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public void addNode(NodeData n) {
    nodes.put(n.getKey(),n);
    mcCounter = mcCounter + 1;
    }

    @Override
    public void connect(int src, int dest, double w) {
        EdgeData newEdge = new Edge(src, dest, w);
        Point2D p1 =new Point(src,dest);
        edges.put(p1,newEdge);
        mcCounter = mcCounter + 1;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        try {
            return nodes.values().iterator();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        try {
            return edges.values().iterator();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        HashMap<Point2D, EdgeData> connectedEdges =new HashMap<Point2D, EdgeData>();
        for (int i = 0 ; i<edges.size();i++){
            if (edges.get(i).getSrc() == node_id){
                Point2D p1 =new Point(edges.get(i).getSrc(),edges.get(i).getDest());
            connectedEdges.put(p1,edges.get(i));
        }}
        return connectedEdges.values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        Node node = (Node) this.getNode(key);
        for (int i = 0; i < edges.size(); i++) {
            int src = edges.get(i).getSrc();
            int dest = edges.get(i).getDest();
            if (src == key || dest == key) {
                removeEdge(src, dest);
            }
        }
        mcCounter = mcCounter + 1;
        nodes.remove(key);
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Point2D p1 =new Point(src,dest);
        Edge edge = (Edge) getEdge(src, dest);
        edges.remove(p1);
        mcCounter = mcCounter + 1;
        return edge;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "edges=" + edges +
                ", nodes=" + nodes +
                '}';
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int getMC() {
        return mcCounter;
    }
}
