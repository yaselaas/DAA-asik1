package com.dandc.algo;

import com.dandc.algo.metrics.Metrics;
import com.dandc.util.PartitionUtils;

public class QuickSort {

    public static void sort(int[] arr) {
        Metrics.reset();
        Metrics.startTimer();
        sort(arr, 0, arr.length - 1);
        Metrics.stopTimer();
    }

    private static void sort(int[] arr, int left, int right) {
        Metrics.enterRecursion();

        while (left < right) {
            if (right - left < 16) {
                // Use insertion sort for small arrays
                insertionSort(arr, left, right);
                break;
            }

            int pivotIndex = PartitionUtils.randomizedPartition(arr, left, right);

            // Recurse on smaller part, iterate on larger part
            if (pivotIndex - left < right - pivotIndex) {
                sort(arr, left, pivotIndex - 1);
                left = pivotIndex + 1;
            } else {
                sort(arr, pivotIndex + 1, right);
                right = pivotIndex - 1;
            }
        }

        Metrics.exitRecursion();
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) {
                Metrics.incrementComparisons();
                arr[j + 1] = arr[j];
                j--;
            }
            Metrics.incrementComparisons();
            arr[j + 1] = key;
        }
    }
}