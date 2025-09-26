package com.dandc;

import com.dandc.algo.MergeSort;
import com.dandc.algo.metrics.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class MergeSortTest {

    @Test
    void testSortRandomArray() {
        int[] arr = {5, 2, 8, 1, 9, 3};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRecursionDepth() {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }

        MergeSort.sort(arr);
        int depth = Metrics.getMaxRecursionDepth();
        assertTrue(depth <= 2 * (int) (Math.log(arr.length) / Math.log(2)) + 5);
    }
}