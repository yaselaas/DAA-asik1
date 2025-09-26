package com.dandc.algo;

import com.dandc.algo.metrics.Metrics;
import com.dandc.util.ArrayUtils;

public class MergeSort {
    private static final int CUTOFF = 15;

    public static void sort(int[] arr) {
        Metrics.reset();
        Metrics.startTimer();
        int[] buffer = new int[arr.length];
        Metrics.incrementAllocations(); // count buffer allocation
        sort(arr, 0, arr.length - 1, buffer);
        Metrics.stopTimer();
    }

    private static void sort(int[] arr, int left, int right, int[] buffer) {
        Metrics.enterRecursion();

        if (right - left <= CUTOFF) {
            ArrayUtils.insertionSort(arr, left, right);
            Metrics.exitRecursion();
            return;
        }

        int mid = left + (right - left) / 2;
        sort(arr, left, mid, buffer);
        sort(arr, mid + 1, right, buffer);
        merge(arr, left, mid, right, buffer);

        Metrics.exitRecursion();
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] buffer) {
        System.arraycopy(arr, left, buffer, left, right - left + 1);
        Metrics.incrementAllocations(); // count array copy

        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            Metrics.incrementComparisons();
            if (buffer[i] <= buffer[j]) {
                arr[k++] = buffer[i++];
            } else {
                arr[k++] = buffer[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = buffer[i++];
        }
    }
}