import api.*;
import graph.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Graphics2D;

public class NewFrame extends JPanel {
    Graph graph;
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    int HEIGHT = (int) (size.height/1.5);
    int WIDTH = (int) (size.width/1.5);

    public NewFrame(Graph graph) {
        this.graph = graph;
        //this.graph = (Graph) graphAlgo.getGraph();
    }

    public void paint(Graphics g) {
        Graphics2D pen = (Graphics2D) g.create();
        Iterator<NodeData> nodeIter = graph.nodeIter();
        ArrayList<Double> xEdges = new ArrayList<Double>();
        ArrayList<Double> yEdges = new ArrayList<Double>();
        while (nodeIter.hasNext()) {
            Node node = (Node) nodeIter.next();
            xEdges.add(node.getLocation().x());
            yEdges.add(node.getLocation().y());
        }
        double Xmin = findMin(xEdges);
        double Ymin = findMin(yEdges);
        double Xmax = findMax(xEdges);
        double Ymax = findMax(yEdges);
        nodeIter = graph.nodeIter();
        Iterator<EdgeData> edgeIter = graph.edgeIter();
        while (nodeIter.hasNext()) {
            NodeData node = nodeIter.next();
            drawPoints(node,pen,Xmin,Xmax,Ymin,Ymax);
        }
        while (edgeIter.hasNext()) {
            EdgeData edge = edgeIter.next();
            drawEdges(edge,pen,Xmin,Xmax,Ymin,Ymax);
        }
        this.setVisible(true);
    }
    private void drawPoints(NodeData node, Graphics2D pen, double Xmin, double Xmax, double Ymin, double Ymax) {
        double xRange = (Xmax-Xmin);
        double xPoint = (node.getLocation().x() - Xmin);
        double yRange = (Ymax-Ymin);
        double yPoint = (Ymax-node.getLocation().y());
        double screen = (WIDTH-300);
        double x =(xPoint/xRange) * screen +100;
        screen = (HEIGHT-300);
        double y =(yPoint/yRange) * screen +100;
        pen.setColor(Color.red);
        pen.fillOval((int) x,(int) y,10,10);
        int key = node.getKey();
        pen.setColor(Color.BLACK);
        pen.drawString(String.valueOf(key),(int) x,(int) y);
    }
    private void drawEdges(EdgeData edge, Graphics2D pen, double Xmin, double Xmax, double Ymin, double Ymax){
        Node src = (Node) graph.getNode(edge.getSrc());
        double xRange = (Xmax-Xmin);
        double xPoint = (src.getLocation().x() - Xmin);
        double yRange = (Ymax-Ymin);
        double yPoint = (Ymax-src.getLocation().y());
        double screen = (WIDTH-300);
        double xSrc =(xPoint/xRange) * screen +100;
        screen = (HEIGHT-300);
        double ySrc =(yPoint/yRange) * screen +100;
        Node dest = (Node) graph.getNode(edge.getDest());
        xPoint = (dest.getLocation().x() - Xmin);
        yRange = (Ymax-Ymin);
        yPoint = (Ymax-dest.getLocation().y());
        screen = (WIDTH-300);
        double xDest =(xPoint/xRange) * screen +100;
        screen = (HEIGHT-300);
        double yDest =(yPoint/yRange) * screen +100;
        pen.setColor(Color.black);
        pen.drawLine((int) xSrc, (int) ySrc, (int) xDest, (int) yDest);
//        Double w = edge.getWeight();
//        String direction = w.toString();
//        direction =direction.substring(0, w.toString().indexOf(".") + 2);
//        direction = src.getKey()+"to"+dest.getKey()+":       "+direction;
//        pen.setFont(new Font("arial", Font.BOLD , 17));
//        pen.setColor(Color.blue);
//        System.out.println(direction);
//        pen.drawString(direction,(int) xSrc,(int) ySrc-30);
    }

    private double findMin(ArrayList<Double> Edges) {
        double edge = Double.MAX_VALUE;
        for (int i = 0; i <Edges.size(); i++) {
            double tmp = Edges.get(i);
            if (tmp < edge)
                edge = tmp;
        }
        return edge;
    }

    private double findMax(ArrayList<Double> Edges) {
        double edge = 0;
        for (int i = 0; i <Edges.size(); i++) {
            double tmp = Edges.get(i);
            if (tmp > edge)
                edge = tmp;
        }
        return edge;
    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
    }
}

