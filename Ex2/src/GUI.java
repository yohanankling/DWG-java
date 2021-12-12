import graph.*;
import api.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class GUI extends JFrame {

    JFrame f;
    NewFrame frame;
    JMenuBar mb;
    JMenu save_load;
    JMenu algorithm;
    JMenu Graph;
    JMenuItem save;
    JMenuItem load;
    JMenuItem add_node;
    JMenuItem add_edge;
    JMenuItem remove_node;
    JMenuItem remove_edge;
    JMenuItem isConnected;
    JMenuItem shortestPathDist;
    JMenuItem shortestPath;
    JMenuItem tsp;
    JMenuItem center;
    JMenuItem exit;

    Graph_Algo graphAlgo;
    Graph graph;


    public GUI(Graph_Algo alg) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        graphAlgo = new Graph_Algo();
        graph = new Graph();
        graph = (Graph) alg.getGraph();
        graphAlgo.init(graph);
        frame = new NewFrame(graph);
        f = new JFrame("Menu");

        mb = new JMenuBar();
        save_load = new JMenu("Save/Load");
        save = new JMenuItem("Save");
        load = new JMenuItem("Load");


        Graph = new JMenu("edit");
        add_node = new JMenuItem("add node");
        add_edge = new JMenuItem("add edge");
        remove_node = new JMenuItem("remove edge");
        remove_edge = new JMenuItem("remove node");


        algorithm = new JMenu("algorithm");
        isConnected = new JMenuItem("is Connected");
        shortestPathDist = new JMenuItem("shortest path distance");
        shortestPath = new JMenuItem("shortest path");
        center = new JMenuItem("center");
        tsp = new JMenuItem("tsp");





        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser("path");
                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    graphAlgo.save(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        save_load.add(save);

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser chooser = new JFileChooser("path");
                if (chooser.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION)
                {
                    graphAlgo.load(chooser.getSelectedFile().getAbsolutePath());
                    setVisible(false);
                    new GUI(graphAlgo);
                }
            }
        });
        save_load.add(load);

        add_node.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            String dialogP = JOptionPane.showInputDialog(frame, "enter the node key", null);
            int key = Integer.parseInt(dialogP);
                Node n = (Node) graphAlgo.getGraph().getNode(Integer.parseInt(dialogP));
                String x = JOptionPane.showInputDialog(frame, "enter the node x location. example : 5.2", null);
                double xL = Double.parseDouble(x);
                String y = JOptionPane.showInputDialog(frame, "enter the node y location. example : 0.2", null);
                double yL = Double.parseDouble(y);
                String z = JOptionPane.showInputDialog(frame, "enter the node z location. example : -6.2", null);
                double zL = Double.parseDouble(z);
                Location l = new Location(xL,yL,zL);
                Node tmp = new Node(key,l,0.0,"",0);
                if(n == null){
                    graph.addNode(tmp);
                }
                else {
                    graph.removeNode(key);
                    graph.addNode(tmp);
            }
                setVisible(false);
                graphAlgo.init(graph);
                new GUI((graphAlgo));
        }
    });
        Graph.add(add_node);

        add_edge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String dialogP = JOptionPane.showInputDialog(frame, "enter the first node id to connect", null);
                int node1 = Integer.parseInt(dialogP);
                dialogP = JOptionPane.showInputDialog(frame, "enter the second node id to connect", null);
                int node2 = Integer.parseInt(dialogP);
                Node n1 = (Node) graphAlgo.getGraph().getNode(node1);
                Node n2 = (Node) graphAlgo.getGraph().getNode(node2);
                if (n1 == null || n2 == null)
                    JOptionPane.showInputDialog("one of the nodes do not exist!");
                else {
                dialogP = JOptionPane.showInputDialog(frame, "enter the edge weight. example : 5.2", null);
                double w = Double.parseDouble(dialogP);
                Point2D p = new Point(node1,node2);
                Edge edge = (Edge) graph.getEdge(node1,node2);
                if (edge==null){
                    graph.connect(node1,node2,w);
                }
                else {
                    graph.removeEdge(node1,node2);
                    graph.connect(node1,node2,w);
                }
                setVisible(false);
                graphAlgo.init(graph);
                new GUI((graphAlgo));
            }}
        });
        Graph.add(add_edge);

        remove_node.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String dialogP = JOptionPane.showInputDialog(frame, "enter the node id you want to disconnect", null);
                int node1 = Integer.parseInt(dialogP);
                Node n1 = (Node) graphAlgo.getGraph().getNode(node1);
                if (n1 == null )
                    JOptionPane.showInputDialog("the node does not exist!");
                else {
                    graph.removeNode(node1);
                    setVisible(false);
                    graphAlgo.init(graph);
                    new GUI((graphAlgo));
                }}
        });
        Graph.add(remove_node);

        remove_edge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String dialogP = JOptionPane.showInputDialog(frame, "enter the src you want to disconnect", null);
                int node1 = Integer.parseInt(dialogP);
                dialogP = JOptionPane.showInputDialog(frame, "enter the destination you want to disconnect", null);
                int node2 = Integer.parseInt(dialogP);
                Node n1 = (Node) graphAlgo.getGraph().getNode(node1);
                Node n2 = (Node) graphAlgo.getGraph().getNode(node2);
                if (n1 == null || n2 == null)
                    JOptionPane.showInputDialog("one of the nodes do not exist!");
                else {
                dialogP = JOptionPane.showInputDialog(frame, "enter the edge weight. example : 5.2", null);
                double w = Double.parseDouble(dialogP);
                graph.connect(node1,node2,w);
                setVisible(false);
                graphAlgo.init(graph);
                new GUI((graphAlgo));
            }}
        });
        Graph.add(remove_edge);

        isConnected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                boolean ans = graphAlgo.isConnected();
                if (ans)
                    JOptionPane.showInputDialog("the graph is connected!");
                else {
                    JOptionPane.showInputDialog("the graph is not connected!");
                }}
        });
        algorithm.add(isConnected);

        shortestPathDist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String dialogP = JOptionPane.showInputDialog(frame, "enter the src", null);
                int node1 = Integer.parseInt(dialogP);
                dialogP = JOptionPane.showInputDialog(frame, "enter the destination", null);
                int node2 = Integer.parseInt(dialogP);
                Node n1 = (Node) graphAlgo.getGraph().getNode(node1);
                Node n2 = (Node) graphAlgo.getGraph().getNode(node2);
                if (n1 == null || n2 == null)
                    JOptionPane.showInputDialog("one of the nodes do not exist!");
                else {
                    double ans = graphAlgo.shortestPathDist(node1,node2);
                    JOptionPane.showInputDialog("the shortest distance is:"+ans);
                }}
        });
        algorithm.add(shortestPathDist);

        shortestPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String dialogP = JOptionPane.showInputDialog(frame, "enter the src", null);
                int node1 = Integer.parseInt(dialogP);
                dialogP = JOptionPane.showInputDialog(frame, "enter the destination", null);
                int node2 = Integer.parseInt(dialogP);
                Node n1 = (Node) graphAlgo.getGraph().getNode(node1);
                Node n2 = (Node) graphAlgo.getGraph().getNode(node2);
                if (n1 == null || n2 == null)
                    JOptionPane.showInputDialog("one of the nodes do not exist!");
                else {
                    List<NodeData> ans = graphAlgo.shortestPath(node1,node2);
                    JOptionPane.showInputDialog("the shortest is through the nodes:"+ans);
                }}
        });
        algorithm.add(shortestPath);

        center.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Node n2 = (Node) graphAlgo.center();
                JOptionPane.showInputDialog("the center of the grpah is node:"+ n2.getKey());
                }
        });
        algorithm.add(center);

        tsp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                List<NodeData> cities=new LinkedList<>();
                String dialogP = JOptionPane.showInputDialog(frame, "enter the id's to check the path, for finish enter '-1' ", null);
                int node1 = Integer.parseInt(dialogP);
                while (node1 != -1){
                    Node n1 = (Node) graphAlgo.getGraph().getNode(node1);
                    if (n1 != null)
                    cities.add(n1);
                    else JOptionPane.showInputDialog("the node doesnt exist! keep entering");
                }
                List<NodeData> ans = graphAlgo.tsp(cities);
                JOptionPane.showInputDialog("the shortest is through the nodes:"+ans);
                }
        });
        algorithm.add(tsp);

        mb.add(save_load);
        mb.add(Graph);
        mb.add(algorithm);
        f.setJMenuBar(mb);
        f.setBackground(Color.WHITE);
        f.setSize(500, 500);
        f.setVisible(true);
        this.setTitle("Ex2 - Graphs");
        this.setLayout(new BorderLayout());
        frame = new NewFrame(graph);
        this.add(frame);
    }

    public static void main(String[] args) {
        Ex2.runGUI("src/graph/G1.json");
    }
}
