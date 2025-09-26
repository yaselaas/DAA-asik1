package com.dandc.algo.metrics;

import java.io.FileWriter;
import java.io.IOException;

public class CSVMetricsWriter {
    public static void writeMetrics(String filename, String algorithm, int n,
                                    long timeNs, int maxDepth, long comparisons, long allocations) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(String.format("%s,%d,%d,%d,%d,%d\n",
                    algorithm, n, timeNs, maxDepth, comparisons, allocations));
        } catch (IOException e) {
            System.err.println("Error writing metrics: " + e.getMessage());
        }
    }
}