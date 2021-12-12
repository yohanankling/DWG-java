import api.*;
import graph.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Iterator;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class NewFrame extends JPanel
{
    Graph_Algo graphAlgo;
    Graph graph;
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    int HEIGHT = size.height;
    int WIDTH = size.width;

    public NewFrame (Graph graph)
    {
        this.setBackground(new Color(1, 150, 150));
        graphAlgo = new Graph_Algo();
        this.graph = graph;
        graphAlgo.init((DirectedWeightedGraph) graph);

    }

    public void paint(Graphics g) {
        Graphics2D pen= (Graphics2D) g.create();
        Iterator<NodeData> nodeIter= graph.nodeIter();
        while(nodeIter.hasNext()) {
            drawPoints(nodeIter.next(),pen);
        }
        Iterator<EdgeData> edgeIter= graph.edgeIter();
        while (edgeIter.hasNext())
        {
            drawEdges(edgeIter.next(),pen);
            }
        this.setVisible(true);
    }

    private void drawPoints(NodeData node, Graphics2D pen) {
        pen.setColor(Color.MAGENTA);
        double x = node.getLocation().x();
        double y = node.getLocation().y();
        pen.fillOval((int) x, (int) y , 15, 15);
        pen.setColor(Color.black);
        pen.drawString("" + node.getKey(),(int)x,(int)y);
    }

    private void drawEdges(EdgeData edge, Graphics2D pen) {
        pen.setColor(Color.black);
        Node src = (Node) graph.getNode(edge.getSrc());
        Node dest = (Node) graph.getNode(edge.getDest());
        int Xsrc = (int) src.getLocation().x();
        int Ysrc = (int) src.getLocation().y();
        int Xdest =(int)  dest.getLocation().x();
        int Ydest =(int)  dest.getLocation().y();
        pen.drawLine(Xsrc,Ysrc,Xdest,Ydest);
    }

}
