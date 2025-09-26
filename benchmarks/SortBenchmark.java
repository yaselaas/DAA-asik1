package com.dandc.benchmark;

import com.dandc.algo.*;
import com.dandc.util.ArrayUtils;
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class SortBenchmark {

    @Param({"100", "1000", "10000"})
    private int n;

    private int[] arr;

    @Setup(Level.Iteration)
    public void setup() {
        arr = ArrayUtils.generateRandomArray(n);
    }

    @Benchmark
    public void mergeSort() {
        MergeSort.sort(arr.clone());
    }

    @Benchmark
    public void quickSort() {
        QuickSort.sort(arr.clone());
    }

    @Benchmark
    public void selectMedian() {
        DeterministicSelect.select(arr.clone(), n/2);
    }
}