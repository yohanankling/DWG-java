package tests;
import graph.Location;
import graph.Node;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import graph.*;
import api.*;

class NodeDataTest {
    Location location1 = new Location(0,0,0);
    Node node1 = new Node(0,location1,0.0,"Tzach",0);
    Location location2 = new Location(1,5,7);
    Node node2 = new Node(2,location2,5.3,"Yohanan",6);
    Location location3 = new Location(5,4,3);
    Node node3 = new Node(1,location3,3.26,"blabla",2);

    @Test
      void getKey() {
        assertEquals(node1.getKey(), 0);
        assertEquals(node2.getKey(), 2);
        assertEquals(node3.getKey(), 1);
    }

    @Test
    void getLocation() {
        assertEquals(node1.getLocation().x(),location1.x());
        assertEquals(node2.getLocation().x(),location2.x());
        assertEquals(node3.getLocation().x(),location3.x());
    }

    @Test
    void setLocation() {
        Location location = new Location(3,4,5);
        node1.setLocation(location);
        node2.setLocation(location);
        node3.setLocation(location);
        assertEquals(node1.getLocation().x(),location.x());
        assertEquals(node1.getLocation().y(),location.y());
        assertEquals(node1.getLocation().z(),location.z());
        assertEquals(node2.getLocation().x(),location.x());
        assertEquals(node2.getLocation().y(),location.y());
        assertEquals(node2.getLocation().z(),location.z());
        assertEquals(node3.getLocation().x(),location.x());
        assertEquals(node3.getLocation().y(),location.y());
        assertEquals(node3.getLocation().z(),location.z());
    }

    @Test
    void getWeight() {
        assertEquals(node1.getWeight(),0.0);
        assertEquals(node2.getWeight(),5.3);
        assertEquals(node3.getWeight(),3.26);
    }

    @Test
    void setWeight() {
        double w=5.5;
        node1.setWeight(w);
        node2.setWeight(w);
        node3.setWeight(w);
        assertEquals(node1.getWeight(),w);
        assertEquals(node2.getWeight(),w);
        assertEquals(node3.getWeight(),w);
    }

    @Test
    void getInfo() {
        assertEquals(node1.getInfo(),"Tzach");
        assertEquals(node2.getInfo(), "Yohanan");
        assertEquals(node3.getInfo(), "blabla");
        assertFalse(node3.getInfo().equals("bla"));
    }

    @Test
    void setInfo() {
        node1.setInfo("Aker");
        node2.setInfo("Kling");
        assertEquals(node1.getInfo(), "Aker");
        assertEquals(node2.getInfo(), "Kling");
    }

    @Test
    void getTag() {
        assertEquals(node1.getTag(), 0);
        assertEquals(node2.getTag(), 6);
        assertEquals(node3.getTag(), 2);
    }

    @Test
    void setTag() {
        int t=1;
        node1.setTag(t);
        node2.setTag(t);
        node3.setTag(t);
        assertEquals(node1.getTag(),t);
        assertEquals(node2.getTag(),t);
        assertEquals(node3.getTag(), 1);
    }
}
