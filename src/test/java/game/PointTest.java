
package game;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class PointTest {

    @Test
    void constructorStoresCoordinates() {
        Point point = new Point(2, 3, 4);

        assertEquals(2, point.x);
        assertEquals(3, point.y);
        assertEquals(4, point.z);
    }

    @Test
    void pointsWithSameCoordinatesAreEqual() {
        Point first = new Point(1, 2, 3);
        Point second = new Point(1, 2, 3);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void pointsWithDifferentCoordinatesAreNotEqual() {
        Point original = new Point(1, 2, 3);

        assertNotEquals(original, new Point(2, 2, 3));
        assertNotEquals(original, new Point(1, 3, 3));
        assertNotEquals(original, new Point(1, 2, 4));
    }

    @Test
    void pointIsNotEqualToNullOrDifferentObjectType() {
        Point point = new Point(1, 2, 3);

        assertNotEquals(null, point);
        assertNotEquals("Point", point);
        assertEquals(point, point);
    }

    @Test
    void neighbors8ReturnsEightUniqueNeighbors() {
        Point point = new Point(5, 5, 2);

        List<Point> neighbors = point.neighbors8();
        Set<Point> unique = new HashSet<>(neighbors);

        assertEquals(8, neighbors.size());
        assertEquals(8, unique.size());

        for (Point neighbor : neighbors) {
            assertTrue(Math.abs(neighbor.x - 5) <= 1);
            assertTrue(Math.abs(neighbor.y - 5) <= 1);
            assertEquals(2, neighbor.z);
            assertNotEquals(point, neighbor);
        }
    }
}
