//package api;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.geom.Ellipse2D;
//import java.awt.geom.Line2D;
//
//public class GUI extends JPanel {
//    Graph graph;
//    int mar = 50;
//
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        this.graph = new Graph("G3.json");
//        Graphics2D g1 = (Graphics2D) g;
//        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        int width = getWidth();
//        int height = getHeight();
//        System.out.println("Height: " + height + " Width: " + width);
//        g1.draw(new Line2D.Double(mar, mar, mar, height - mar));
//        g1.draw(new Line2D.Double(mar, height - mar, width - mar, height - mar));
//        for (int i = 0; i < this.graph.nodesSize-1; i++) {
//            System.out.println("plot");
//            double x1 = (((this.graph.getNode(i).getLocation().x() - ((int) this.graph.getNode(i).getLocation().x()))) * (10000) - 2.4*width);
//            System.out.println(x1);
//            double y1 = (this.graph.getNode(i).getLocation().y() - ((int) this.graph.getNode(i).getLocation().y())) * (10000) - 1* height;
//            System.out.println(y1);
//            double x2 = (((this.graph.getNode(i+1).getLocation().x() - ((int) this.graph.getNode(i+1).getLocation().x()))) * (10000) - 2.4*width);
//            double y2 = (((this.graph.getNode(i+1).getLocation().y() - ((int) this.graph.getNode(i+1).getLocation().y()))) * (10000) - 2.4*width);
//
//            g1.fill(new Ellipse2D.Double(x1, y1, 4, 4));
//            g1.draw(new Line2D.Double(x1, y1, x2, y2));
//        }
//    }
//
////    private int getMax() {
////        int max = -Integer.MAX_VALUE;
////        for (int i = 0; i < coordinates.length; i++) {
////            if (coordinates[i] > max)
////                max = coordinates[i];
////        }
////        return max;
////    }
//
//    public static void main(String args[]) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(new GUI());
//        frame.setSize(700, 700);
//        frame.setLocation(50, 50);
//        frame.setVisible(true);
//    }
//}