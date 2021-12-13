package tests;

import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;
import graph.*;
import api.*;
import org.junit.jupiter.api.Test;

class EdgeDataTest {
    Edge edge1 = new Edge(3,5,2.7,"Tzach",7);
    Edge edge2 = new Edge(2,1,5.27,"Yohanan",0);

    @Test
    void getSrc() {
        assertEquals(edge1.getSrc(),3);
        assertEquals(edge2.getSrc(),2);
    }
    @Test
    void getDest() {
        assertEquals(edge1.getDest(),5);
        assertEquals(edge2.getDest(),1);
    }

    @Test
    void getWeight() {
        assertEquals(edge1.getWeight(),2.7);
        assertEquals(edge2.getWeight(),5.27);
    }

    @Test
    void getInfo() {
        assertEquals(edge1.getInfo(),"Tzach");
        assertEquals(edge2.getInfo(),"Yohanan");
    }

    @Test
    void setInfo() {
        edge1.setInfo("Aker");
        edge2.setInfo("Kling");
        assertEquals(edge1.getInfo(),"Aker");
        assertFalse(edge1.getInfo().equals("Tzach"));
        assertEquals(edge2.getInfo(),"Kling");
        assertFalse(edge2.getInfo().equals("Yohanan"));
    }

    @Test
    void getTag() {
        assertEquals(edge1.getTag(),7);
        assertEquals(edge2.getTag(),0);
    }

    @Test
    void setTag() {
        edge1.setTag(2);
        edge2.setTag(5);
        assertEquals(edge1.getTag(),2);
        assertNotEquals(edge1.getTag(),7);
        assertEquals(edge2.getTag(),5);
        assertNotEquals(edge2.getTag(),0);
    }
}
