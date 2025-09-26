package com.dandc;

import com.dandc.algo.*;
import com.dandc.algo.metrics.Metrics;
import com.dandc.algo.metrics.CSVMetricsWriter;
import com.dandc.util.ArrayUtils;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            printUsage();
            return;
        }

        String algorithm = args[0];
        int n = Integer.parseInt(args[1]);
        String dataType = args.length > 2 ? args[2] : "random";

        switch (algorithm.toLowerCase()) {
            case "mergesort":
                runMergeSort(n, dataType);
                break;
            case "quicksort":
                runQuickSort(n, dataType);
                break;
            case "select":
                runSelect(n, dataType);
                break;
            case "closest":
                runClosestPair(n);
                break;
            case "benchmark":
                runBenchmark(n);
                break;
            default:
                System.err.println("Unknown algorithm: " + algorithm);
                printUsage();
        }
    }

    private static void runMergeSort(int n, String dataType) {
        int[] arr = generateArray(n, dataType);
        MergeSort.sort(arr);
        outputMetrics("mergesort", n);
    }

    private static void runQuickSort(int n, String dataType) {
        int[] arr = generateArray(n, dataType);
        QuickSort.sort(arr);
        outputMetrics("quicksort", n);
    }

    private static void runSelect(int n, String dataType) {
        int[] arr = generateArray(n, dataType);
        int k = n / 2; // median
        DeterministicSelect.select(arr, k);
        outputMetrics("select", n);
    }

    private static void runClosestPair(int n) {
        com.dandc.algo.Point[] points = generatePoints(n);
        ClosestPair.findClosestDistance(points);
        outputMetrics("closest", n);
    }

    private static void runBenchmark(int n) {
        System.out.println("Algorithm,Time(ns),MaxDepth,Comparisons,Allocations");

        for (String algo : new String[]{"mergesort", "quicksort", "select"}) {
            for (int size = 100; size <= n; size *= 2) {
                int[] arr = ArrayUtils.generateRandomArray(size);
                Metrics.reset();
                Metrics.startTimer();

                switch (algo) {
                    case "mergesort": MergeSort.sort(arr.clone()); break;
                    case "quicksort": QuickSort.sort(arr.clone()); break;
                    case "select": DeterministicSelect.select(arr.clone(), size/2); break;
                }

                long time = Metrics.stopTimer();
                System.out.printf("%s,%d,%d,%d,%d,%d\n",
                        algo, size, time, Metrics.getMaxRecursionDepth(),
                        Metrics.getComparisonCount(), Metrics.getAllocationCount());
            }
        }
    }

    private static int[] generateArray(int n, String dataType) {
        int[] arr = ArrayUtils.generateRandomArray(n);
        if ("sorted".equals(dataType)) {
            Arrays.sort(arr);
        } else if ("reversed".equals(dataType)) {
            Arrays.sort(arr);
            for (int i = 0; i < n / 2; i++) {
                ArrayUtils.swap(arr, i, n - 1 - i);
            }
        }
        return arr;
    }

    private static com.dandc.algo.Point[] generatePoints(int n) {
        com.dandc.algo.Point[] points = new com.dandc.algo.Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new com.dandc.algo.Point(Math.random() * 1000, Math.random() * 1000);
        }
        return points;
    }

    private static void outputMetrics(String algorithm, int n) {
        long time = Metrics.stopTimer();
        CSVMetricsWriter.writeMetrics("results/metrics.csv", algorithm, n, time,
                Metrics.getMaxRecursionDepth(), Metrics.getComparisonCount(), Metrics.getAllocationCount());

        System.out.printf("%s: n=%d, time=%d ns, depth=%d, comparisons=%d, allocations=%d\n",
                algorithm, n, time, Metrics.getMaxRecursionDepth(),
                Metrics.getComparisonCount(), Metrics.getAllocationCount());
    }

    private static void printUsage() {
        System.out.println("Usage: java Main <algorithm> <n> [data-type]");
        System.out.println("Algorithms: mergesort, quicksort, select, closest, benchmark");
        System.out.println("Data types: random, sorted, reversed");
    }
}