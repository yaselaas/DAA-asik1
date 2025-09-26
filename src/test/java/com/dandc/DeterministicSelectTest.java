package com.dandc;

import com.dandc.algo.DeterministicSelect;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class DeterministicSelectTest {

    @Test
    void testSelect() {
        int[] arr = {5, 2, 8, 1, 9, 3};
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        for (int k = 0; k < arr.length; k++) {
            int[] testArr = arr.clone();
            int result = DeterministicSelect.select(testArr, k);
            assertEquals(sorted[k], result);
        }
    }

    @Test
    void testMultipleRandomTrials() {
        for (int trial = 0; trial < 100; trial++) {
            int[] arr = new int[100];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) (Math.random() * 1000);
            }

            int k = (int) (Math.random() * arr.length);
            int[] testArr = arr.clone();
            int result = DeterministicSelect.select(testArr, k);

            Arrays.sort(arr);
            assertEquals(arr[k], result, "Trial " + trial + " failed");
        }
    }
}