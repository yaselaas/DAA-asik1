package com.dandc;

import com.dandc.algo.QuickSort;
import com.dandc.algo.metrics.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class QuickSortTest {

    @Test
    void testSortRandomArray() {
        int[] arr = {5, 2, 8, 1, 9, 3};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        QuickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRecursionDepthBounded() {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }

        QuickSort.sort(arr);
        int depth = Metrics.getMaxRecursionDepth();
        // Should be O(log n) - allow some margin
        assertTrue(depth <= 2 * (int) (Math.log(arr.length) / Math.log(2)) + 10);
    }
}