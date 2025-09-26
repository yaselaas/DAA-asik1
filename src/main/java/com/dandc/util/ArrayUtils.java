package com.dandc.util;

import com.dandc.algo.metrics.Metrics;

public class ArrayUtils {

    public static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) {
                Metrics.incrementComparisons();
                arr[j + 1] = arr[j];
                j--;
            }
            Metrics.incrementComparisons(); // for the last comparison that failed
            arr[j + 1] = key;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}