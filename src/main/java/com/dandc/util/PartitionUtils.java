package com.dandc.util;

import com.dandc.algo.metrics.Metrics;

public class PartitionUtils {

    public static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        ArrayUtils.swap(arr, pivotIndex, right); // move pivot to end

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            Metrics.incrementComparisons();
            if (arr[i] < pivotValue) {
                ArrayUtils.swap(arr, storeIndex, i);
                storeIndex++;
            }
        }

        ArrayUtils.swap(arr, storeIndex, right); // move pivot to final place
        return storeIndex;
    }

    public static int randomizedPartition(int[] arr, int left, int right) {
        int pivotIndex = left + (int) (Math.random() * (right - left + 1));
        return partition(arr, left, right, pivotIndex);
    }
}