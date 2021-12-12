import api.*;
import graph.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Iterator;
import java.awt.Graphics2D;


public class NewFrame extends JPanel
{
    Graph_Algo graphAlgo;
    Graph graph;
    Graphics2D g2d;
    Color c;

    public NewFrame (Graph graph)
    {
        super();
        this.setBackground(new Color(1, 150, 150));
        c = Color.MAGENTA;
        graphAlgo = new Graph_Algo();
        this.graph = graph;
        graphAlgo.init((DirectedWeightedGraph) graph);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
//    public void paint(Graph graph)
//    {
        Graphics2D ggg = (Graphics2D) g;
        Iterator<NodeData> nodeIter= graph.nodeIter();
        ggg.setColor(Color.MAGENTA);
        while(nodeIter.hasNext())
        {
            Node node = (Node) nodeIter.next();
            Point2D p1 = new Point2D.Double(node.getLocation().x(),node.getLocation().y());
        g2d.fill((Shape) p1);}

        Iterator<EdgeData> edgeIter= graph.edgeIter();
        while (edgeIter.hasNext())
        {
            EdgeData edge = edgeIter.next();
            Node n1 = (Node) graph.getNode(edge.getSrc());
            Node n2 = (Node) graph.getNode(edge.getDest());
            Point2D p1 = new Point2D.Double(n1.getLocation().x(),n1.getLocation().y());
            Point2D p2 = new Point2D.Double(n2.getLocation().x(),n2.getLocation().y());
                g2d.drawLine((int) p1.getX(), (int)p1.getY(),(int) p2.getX(), (int) p2.getY());
            }
        this.setVisible(true);
    }

}
