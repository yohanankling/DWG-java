package tests;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;
import graph.*;
import api.*;
import org.junit.jupiter.api.Test;

class DirectedWeightedGraphTest {
    Graph graph = new Graph();
    Node node_0 = new Node(0, new Location(0.0, 0.0, 0.0), 0.0, "", 0);
    Node node_1 = new Node(1, new Location(0.0, 1.0, 0.0), 0.0, "", 1);
    Node node_2 = new Node(2, new Location(1.0, 1.0, 0.0), 0.0, "", 2);

    @Test
    void getNode() {
        graph.addNode(node_0);
        graph.addNode(node_1);
        graph.addNode(node_2);
        assertEquals(graph.getNode(0), node_0);
        assertEquals(graph.getNode(1), node_1);
        assertEquals(graph.getNode(2), node_2);
    }

    @Test
    void getEdge() {
        graph.addNode(node_0);
        graph.addNode(node_1);
        graph.addNode(node_2);
        graph.connect(0, 1, 5.2);
        graph.connect(0, 2, 2.5);
        assertEquals(graph.getEdge(0, 1).getSrc(), 0);
        assertEquals(graph.getEdge(0, 1).getDest(), 1);
        assertEquals(graph.getEdge(0, 1).getWeight(), 5.2);
        assertEquals(graph.getEdge(0, 2).getSrc(), 0);
        assertEquals(graph.getEdge(0, 2).getDest(), 2);
        assertEquals(graph.getEdge(0, 2).getWeight(), 2.5);
    }

    @Test
    void addNode() {
        graph.addNode(node_0);
        graph.addNode(node_1);
        graph.addNode(node_2);
        assertEquals(graph.getNode(0), node_0);
        assertEquals(graph.getNode(1), node_1);
        assertEquals(graph.getNode(2), node_2);
    }

    @Test
    void connect() {
        int src = 1, dest = 3;
        double w = 4.3;
        graph.connect(src, dest, w);
        assertEquals(graph.getEdge(src, dest).getSrc(),1);
        assertEquals(graph.getEdge(src, dest).getDest(),3);
        assertEquals(graph.getEdge(src, dest).getWeight(),4.3);
    }

    @Test
    void nodeIter() {
        try {
            int i = 0;
            graph.addNode(node_0);
            graph.addNode(node_1);
            graph.addNode(node_2);
            Iterator<
                    NodeData> iterNode = graph.nodeIter();
            while (iterNode.hasNext()) {
                i++;
                iterNode.next();
            }
            assertEquals(3, i);
        }
        catch (RuntimeException e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }

    @Test
    void edgeIter() {
        try {
            int i = 0;
            graph.addNode(node_0);
            graph.addNode(node_1);
            graph.addNode(node_2);
            graph.connect(0, 1, 5.4);
            graph.connect(0, 2, 7.2);
            graph.connect(2, 1, 3.0);
            Iterator<EdgeData> iterEdge = graph.edgeIter();
            while (iterEdge.hasNext()) {
                i++;
                iterEdge.next();
            }
            assertEquals(3, i);
        }
        catch (RuntimeException e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }

    @Test
    void edgeIterForNodeID() {
        int i = 0;
        graph.addNode(node_0);
        graph.addNode(node_1);
        graph.addNode(node_2);
        graph.connect(0, 1, 7.2);
        graph.connect(1, 2, 5.4);
        graph.connect(2, 1, 7.2);
        graph.connect(1, 0, 3.0);
        graph.connect(0, 2, 3.0);
        Iterator<EdgeData> iterEdgeForNodeID = graph.edgeIter(1);
        while (iterEdgeForNodeID.hasNext()) {
            i++;
            iterEdgeForNodeID.next();
        }
        assertEquals(2, i);
    }


    @Test
    void removeNode() {
        graph.addNode(node_0);
        graph.addNode(node_1);
        graph.addNode(node_2);
        graph.connect(0, 1, 7.2);
        graph.connect(1, 2, 5.4);
        graph.connect(1, 0, 7.2);
        graph.removeNode(1);
        assertEquals(graph.nodeSize(), 2);
        assertEquals(graph.edgeSize(), 0);
    }

    @Test
    void removeEdge() {
        graph.addNode(node_0);
        graph.addNode(node_1);
        graph.addNode(node_2);
        graph.connect(0, 1, 7.2);
        graph.connect(1, 2, 5.4);
        graph.connect(1, 0, 7.2);
        graph.removeEdge(1, 0);
        assertEquals(graph.nodeSize(), 3);
        assertEquals(graph.edgeSize(), 2);
    }

    @Test
    void nodeSize() {
        graph.addNode(node_0);
        graph.addNode(node_1);
        graph.addNode(node_2);
        assertEquals(graph.nodeSize(), 3);
    }

    @Test
    void edgeSize() {
        graph.addNode(node_0);
        graph.addNode(node_1);
        graph.addNode(node_2);
        graph.connect(0, 1, 7.2);
        graph.connect(1, 2, 5.4);
        graph.connect(1, 0, 7.2);
        assertEquals(graph.edgeSize(), 3);
    }

    @Test
    void getMC() {
        graph.addNode(node_0);
        graph.addNode(node_1);
        graph.addNode(node_2);
        graph.connect(0, 1, 7.2);
        graph.connect(1, 2, 5.4);
        graph.connect(2, 1, 7.2);
        graph.connect(1, 0, 3.0);
        graph.connect(0, 2, 3.0);
        graph.connect(2, 0, 3.6);
        graph.removeNode(1);
        graph.removeEdge(0, 2);
        assertEquals(15, graph.getMC());
    }
}







