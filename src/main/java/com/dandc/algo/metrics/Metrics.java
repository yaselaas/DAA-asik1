package com.dandc.algo.metrics;

public class Metrics {
    private static int recursionDepth = 0;
    private static int maxRecursionDepth = 0;
    private static long comparisonCount = 0;
    private static long allocationCount = 0;
    private static long startTime = 0;

    public static void startTimer() {
        startTime = System.nanoTime();
    }

    public static long stopTimer() {
        return System.nanoTime() - startTime;
    }

    public static void enterRecursion() {
        recursionDepth++;
        if (recursionDepth > maxRecursionDepth) {
            maxRecursionDepth = recursionDepth;
        }
    }

    public static void exitRecursion() {
        recursionDepth--;
    }

    public static void incrementComparisons() {
        comparisonCount++;
    }

    public static void incrementAllocations() {
        allocationCount++;
    }

    public static void reset() {
        recursionDepth = 0;
        maxRecursionDepth = 0;
        comparisonCount = 0;
        allocationCount = 0;
        startTime = 0;
    }

    // Getters
    public static int getMaxRecursionDepth() { return maxRecursionDepth; }
    public static long getComparisonCount() { return comparisonCount; }
    public static long getAllocationCount() { return allocationCount; }
}