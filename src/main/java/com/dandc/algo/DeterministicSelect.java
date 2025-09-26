package com.dandc.algo;

import com.dandc.algo.metrics.Metrics;
import com.dandc.util.ArrayUtils;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        Metrics.reset();
        Metrics.startTimer();
        int result = select(arr, 0, arr.length - 1, k);
        Metrics.stopTimer();
        return result;
    }

    private static int select(int[] arr, int left, int right, int k) {
        Metrics.enterRecursion();

        if (left == right) {
            Metrics.exitRecursion();
            return arr[left];
        }

        int pivotIndex = medianOfMedians(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);

        if (k == pivotIndex) {
            Metrics.exitRecursion();
            return arr[k];
        } else if (k < pivotIndex) {
            Metrics.exitRecursion();
            return select(arr, left, pivotIndex - 1, k);
        } else {
            Metrics.exitRecursion();
            return select(arr, pivotIndex + 1, right, k);
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            return medianOfFive(arr, left, right);
        }

        // Group by 5 and find medians
        int numGroups = (n + 4) / 5;
        int[] medians = new int[numGroups];

        for (int i = 0; i < numGroups; i++) {
            int groupLeft = left + i * 5;
            int groupRight = Math.min(groupLeft + 4, right);
            medians[i] = medianOfFive(arr, groupLeft, groupRight);
        }

        // Recursively find median of medians
        return select(medians, 0, medians.length - 1, medians.length / 2);
    }

    private static int medianOfFive(int[] arr, int left, int right) {
        // Simple insertion sort for small array
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
        return left + (right - left) / 2;
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        ArrayUtils.swap(arr, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            Metrics.incrementComparisons();
            if (arr[i] < pivotValue) {
                ArrayUtils.swap(arr, storeIndex, i);
                storeIndex++;
            }
        }

        ArrayUtils.swap(arr, storeIndex, right);
        return storeIndex;
    }
}