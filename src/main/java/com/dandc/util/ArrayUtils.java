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
}package com.dandc.util;

import java.util.Random;

public class ArrayUtils {
    private static final Random RANDOM = new Random();

    // ... existing methods ...

    public static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RANDOM.nextInt(size * 10);
        }
        return arr;
    }

    public static void guardSmallArray(int[] arr, int minSize) {
        if (arr.length < minSize) {
            throw new IllegalArgumentException("Array too small, minimum size: " + minSize);
        }
    }
}