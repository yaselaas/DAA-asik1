package com.dandc;

import com.dandc.algo.ClosestPair;
import com.dandc.algo.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    void testSmallSet() {
        Point[] points = {
                new Point(1, 2), new Point(4, 6),
                new Point(8, 9), new Point(2, 3)
        };

        double expected = ClosestPair.bruteForce(points);
        double actual = ClosestPair.findClosestDistance(points);

        assertEquals(expected, actual, 1e-9);
    }

    @Test
    void testRandomSmallSets() {
        for (int n = 10; n <= 2000; n *= 2) {
            Point[] points = generateRandomPoints(n);

            double expected = ClosestPair.bruteForce(points);
            double actual = ClosestPair.findClosestDistance(points);

            assertEquals(expected, actual, 1e-9, "Failed for n=" + n);
        }
    }

    private Point[] generateRandomPoints(int n) {
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(Math.random() * 1000, Math.random() * 1000);
        }
        return points;
    }
}