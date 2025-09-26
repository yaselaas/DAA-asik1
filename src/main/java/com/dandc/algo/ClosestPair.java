package com.dandc.algo;

import com.dandc.algo.metrics.Metrics;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double findClosestDistance(Point[] points) {
        Metrics.reset();
        Metrics.startTimer();

        Point[] pointsByX = points.clone();
        Arrays.sort(pointsByX);

        Point[] pointsByY = points.clone();
        Arrays.sort(pointsByY, Comparator.comparingDouble(p -> p.y));

        double result = closestUtil(pointsByX, pointsByY, 0, points.length - 1);
        Metrics.stopTimer();
        return result;
    }

    private static double closestUtil(Point[] pointsByX, Point[] pointsByY, int left, int right) {
        Metrics.enterRecursion();

        int n = right - left + 1;

        if (n <= 3) {
            // Brute force for small sets
            double minDist = Double.MAX_VALUE;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    double dist = pointsByX[i].distanceTo(pointsByX[j]);
                    Metrics.incrementComparisons();
                    if (dist < minDist) {
                        minDist = dist;
                    }
                }
            }
            Metrics.exitRecursion();
            return minDist;
        }

        int mid = left + (right - left) / 2;
        Point midPoint = pointsByX[mid];

        // Split pointsByY into left and right halves
        Point[] leftY = new Point[mid - left + 1];
        Point[] rightY = new Point[right - mid];
        Metrics.incrementAllocations();

        int leftIdx = 0, rightIdx = 0;
        for (Point p : pointsByY) {
            if (p.x <= midPoint.x) {
                leftY[leftIdx++] = p;
            } else {
                rightY[rightIdx++] = p;
            }
        }

        double dl = closestUtil(pointsByX, leftY, left, mid);
        double dr = closestUtil(pointsByX, rightY, mid + 1, right);
        double d = Math.min(dl, dr);

        // Check strip
        Point[] strip = new Point[n];
        int stripSize = 0;
        for (Point p : pointsByY) {
            if (Math.abs(p.x - midPoint.x) < d) {
                strip[stripSize++] = p;
            }
        }

        double stripMin = checkStrip(strip, stripSize, d);
        Metrics.exitRecursion();
        return Math.min(d, stripMin);
    }

    private static double checkStrip(Point[] strip, int size, double d) {
        double minDist = d;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < minDist; j++) {
                double dist = strip[i].distanceTo(strip[j]);
                Metrics.incrementComparisons();
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }

    // Brute force for validation
    public static double bruteForce(Point[] points) {
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dist = points[i].distanceTo(points[j]);
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }
}