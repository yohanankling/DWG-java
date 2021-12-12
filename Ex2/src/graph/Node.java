package graph;
import api.*;
public class Node implements NodeData{
    private int key;
    private GeoLocation location;
    private double weight;
    private String info;
    private int tag;

    public Node(int key, GeoLocation location, double weight, String info, int tag) {
        this.key=key;
        this.location=location;
        this.weight =weight;
        this.info =info;
        this.tag =tag;
    }
    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location = p;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
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
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", location=" + location +
                '}';
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
