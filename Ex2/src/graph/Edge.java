package graph;
import api.*;

public class Edge implements EdgeData {
    private int src;
    private int dest;
    private double weight;
    private String info;
    private int tag;



    public Edge(int src, int dest, double weight) {
        this.src=src;
        this.dest=dest;
        this.weight =weight;
        this.info ="";
        this.tag =0;
    }
    public Edge(EdgeData e) {
        this.src=e.getSrc();
        this.dest=e.getDest();
        this.weight =e.getWeight();
        this.info ="";
        this.tag =0;
    }

    public Edge(int src, int dest, double weight, String info, int tag) {
        this.src=src;
        this.dest=dest;
        this.weight =weight;
        this.info =info;
        this.tag =tag;
    }
    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "src=" + src +
                ", dest=" + dest +
                ", weight=" + weight +
                '}';
    }
}