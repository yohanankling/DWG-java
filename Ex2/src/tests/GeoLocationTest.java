package tests;
import graph.Location;
import graph.Node;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import graph.*;
import api.*;

public class GeoLocationTest {
    @Test
    public void distance() {
        Location location1 = new Location(1, 2, 3),
                location2 = new Location(2, 3, 4),
                location3 = new Location(-5, -1.8, 2),
                location4 = new Location(-5,-6 , -3.5);
        assertEquals(location1.distance(location2), 1.732, 0.001);
        assertEquals(location3.distance(location4), 6.92, 0.001);
    }
}
