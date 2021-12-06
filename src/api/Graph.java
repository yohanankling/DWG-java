package api;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Graph implements DirectedWeightedGraph {
    private HashMap<Integer,NodeData> nodes;
    private HashMap<Point2D,EdgeData> edges;
    private int nodeCounter;
    private int edgeCounter;
    private int mcCounter;



    public Graph(){
        this.nodes=new HashMap<Integer, NodeData>();
        this.edges=new HashMap<Point2D, EdgeData>();;
        this.nodeCounter=nodes.size();
        this.nodeCounter=0;
        this.edgeCounter=0;
        this.mcCounter=0;

    }

    @Override
    public NodeData getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        Point2D p1 =new Point(src,dest);
        return edges.get(p1);
    }

    public EdgeData getEdge(int i) {
        return edges.get(i);
    }

    public EdgeData addEdge(int i) {
        Point2D p1 =new Point(edges.get(i).getSrc(),edges.get(i).getDest());
        return edges.put(p1,edges.get(i));
    }

    @Override
    public void addNode(NodeData n) {
    nodes.put(n.getKey(),n);
    nodeCounter = nodeCounter + 1;
    mcCounter = mcCounter + 1;
    }

    @Override
    public void connect(int src, int dest, double w) {
        Edge newEdge = new Edge(src , dest, w);
        Point2D p1 =new Point(src,dest);
        edges.put(p1,newEdge);
        edgeCounter = edgeCounter + 1;
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
        int k = nodes.get(key).getKey();
        for (int i = 0; i < edges.size(); i++) {
            int src = edges.get(i).getSrc();
            int dest = edges.get(i).getDest();
            if (src == k || dest == k) {
                Node e= (Node) removeEdge(src, dest);
            }
        }
        mcCounter = mcCounter + 1;
        nodeCounter = nodeCounter - 1;
        nodes.remove(key);
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Point2D p1 =new Point(src,dest);
        Edge edge = (Edge) getEdge(src, dest);
        edges.remove(p1);
        edgeCounter = edgeCounter - 1;
        mcCounter = mcCounter + 1;
        return edge;
    }

    @Override
    public int nodeSize() {
        return nodeCounter;
    }

    @Override
    public int edgeSize() {
        return edgeCounter;
    }

    @Override
    public int getMC() {
        return mcCounter;
    }
}
