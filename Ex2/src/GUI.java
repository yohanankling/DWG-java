import graph.*;
import api.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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





//        add_node.addActionListener(this);
//        graph.add(add_node);
//        add_edge.addActionListener(this);
//        graph.add(add_edge);
//        remove_node.addActionListener(this);
//        graph.add(remove_node);
//        remove_edge.addActionListener(this);
//        graph.add(remove_edge);
//        isConnected.addActionListener(this);
//        algorithm.add(isConnected);
//        shortestPathDist.addActionListener(this);
//        algorithm.add(shortestPathDist);
//        shortestPath.addActionListener(this);
//        algorithm.add(shortestPath);
//        center.addActionListener(this);
//        algorithm.add(center);
//        tsp.addActionListener(this);
//        algorithm.add(tsp);


        mb.add(save_load);
        mb.add(Graph);
        mb.add(algorithm);

        f.setJMenuBar(mb);
        f.setSize(500, 500);
        f.setVisible(true);
        this.setTitle("Ex2 - Graphs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame = new NewFrame(graph);
        this.add(frame);
    }

    public static void main(String[] args) {
        Ex2.runGUI("src/graph/G1.json");
    }
}