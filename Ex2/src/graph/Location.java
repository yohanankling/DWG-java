package graph;
import api.*;
public class Location implements GeoLocation {
    private double x;
    private double y;
    private double z;

    public Location(double x, double y, double z) {
        this.x=x;
        this.y=y;
        this.z =z;
    }
    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public double distance(GeoLocation g) {
        double theX = Math.pow(this.x - g.x(),2);
        double theY = Math.pow(this.y - g.y(),2);
        double theZ = Math.pow(this.z - g.z(),2);
        double all = theX + theY + theZ;
        all = Math.sqrt(all);
        return all;
    }
}
