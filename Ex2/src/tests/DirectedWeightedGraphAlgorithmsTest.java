package tests;
import api.*;
import graph.*;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class DirectedWeightedGraphAlgorithmsTest {
    Graph_Algo graph_algo = new Graph_Algo();
    Graph graph = new Graph();
    Node node_0 = new Node(0, new Location(5, 6, 2), 5.8, "", 0);
    Node node_1 = new Node(1, new Location(3, 3, 4), 2.5, "", 1);
    Node node_2 = new Node(2, new Location(6, 2, 6), 0.7, "", 2);

    @Test
    void init() {
        graph.addNode(node_0);
        graph.addNode(node_1);
        graph.addNode(node_2);
        graph.connect(node_0.getTag(), node_1.getKey(), 5.4);
        graph.connect(node_0.getTag(), node_2.getKey(), 2.8);
        graph.connect(node_2.getTag(), node_1.getKey(), 4.0);
        graph_algo.init(graph);
        Iterator<NodeData> iterNode1 = graph.nodeIter();
        Iterator<NodeData> iterNode2 = graph_algo.getGraph().nodeIter();
        Iterator<EdgeData> iterEdge1 = graph.edgeIter();
        Iterator<EdgeData> iterEdge2 = graph_algo.getGraph().edgeIter();
        Iterator<EdgeData> iterEdge3 = graph.edgeIter();
        Iterator<EdgeData> iterEdge4 = graph_algo.getGraph().edgeIter();
        Iterator<EdgeData> iterEdge5 = graph.edgeIter();
        Iterator<EdgeData> iterEdge6 = graph_algo.getGraph().edgeIter();
        while (iterNode1.hasNext() && iterNode2.hasNext()) {
            assertEquals(iterNode1.next().getKey(), iterNode2.next().getKey());
        }
        while (iterEdge1.hasNext() && iterEdge2.hasNext()) {
            assertEquals(iterEdge1.next().getSrc(), iterEdge2.next().getSrc());
        }
        while (iterEdge3.hasNext() && iterEdge4.hasNext()) {
            assertEquals(iterEdge3.next().getDest(), iterEdge4.next().getDest());
        }
        while (iterEdge3.hasNext() && iterEdge4.hasNext()) {
            assertEquals(iterEdge5.next().getWeight(), iterEdge6.next().getWeight());
        }
    }

    @Test
    void getGraph() {
        graph.addNode(node_0);
        graph.addNode(node_1);
        graph.addNode(node_2);
        graph.connect(node_0.getTag(), node_1.getKey(), 5.4);
        graph.connect(node_0.getTag(), node_2.getKey(), 2.8);
        graph.connect(node_2.getTag(), node_1.getKey(), 4.0);
        graph_algo.init(graph);
        Iterator<NodeData> iterNode1 = graph.nodeIter();
        Iterator<NodeData> iterNode2 = graph_algo.getGraph().nodeIter();
        Iterator<EdgeData> iterEdge1 = graph.edgeIter();
        Iterator<EdgeData> iterEdge2 = graph_algo.getGraph().edgeIter();
        Iterator<EdgeData> iterEdge3 = graph.edgeIter();
        Iterator<EdgeData> iterEdge4 = graph_algo.getGraph().edgeIter();
        Iterator<EdgeData> iterEdge5 = graph.edgeIter();
        Iterator<EdgeData> iterEdge6 = graph_algo.getGraph().edgeIter();
        while (iterNode1.hasNext() && iterNode2.hasNext()) {
            assertEquals(iterNode1.next().getKey(), iterNode2.next().getKey());
        }
        while (iterEdge1.hasNext() && iterEdge2.hasNext()) {
            assertEquals(iterEdge1.next().getSrc(), iterEdge2.next().getSrc());
        }
        while (iterEdge3.hasNext() && iterEdge4.hasNext()) {
            assertEquals(iterEdge3.next().getDest(), iterEdge4.next().getDest());
        }
        while (iterEdge3.hasNext() && iterEdge4.hasNext()) {
            assertEquals(iterEdge5.next().getWeight(), iterEdge6.next().getWeight());
        }
    }

    @Test
    void copy() {
        graph.addNode(node_0);
        graph.addNode(node_1);
        graph.addNode(node_2);
        graph.connect(node_0.getTag(), node_1.getKey(), 5.4);
        graph.connect(node_0.getTag(), node_2.getKey(), 2.8);
        graph.connect(node_2.getTag(), node_1.getKey(), 4.0);
        graph_algo.init(graph);
        Graph newGraph = (Graph) graph_algo.copy();
        Iterator<NodeData> iterNode1 = graph.nodeIter();
        Iterator<NodeData> iterNode2 = newGraph.nodeIter();
        Iterator<EdgeData> iterEdge1 = graph.edgeIter();
        Iterator<EdgeData> iterEdge2 = newGraph.edgeIter();
        Iterator<EdgeData> iterEdge3 = graph.edgeIter();
        Iterator<EdgeData> iterEdge4 = newGraph.edgeIter();
        Iterator<EdgeData> iterEdge5 = graph.edgeIter();
        Iterator<EdgeData> iterEdge6 = newGraph.edgeIter();
        while (iterNode1.hasNext() && iterNode2.hasNext()) {
            assertEquals(iterNode1.next().getKey(), iterNode2.next().getKey());
        }
        while (iterEdge1.hasNext() && iterEdge2.hasNext()) {
            assertEquals(iterEdge1.next().getSrc(), iterEdge2.next().getSrc());
        }
        while (iterEdge3.hasNext() && iterEdge4.hasNext()) {
            assertEquals(iterEdge3.next().getDest(), iterEdge4.next().getDest());
        }
        while (iterEdge3.hasNext() && iterEdge4.hasNext()) {
            assertEquals(iterEdge5.next().getWeight(), iterEdge6.next().getWeight());
        }
    }

    @Test
    void isConnected() {
    }

    @Test
    void reversedGraph(){
    }

    @Test
    void dfs(){
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void center() {
    }


    @Test
    void tsp() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}

