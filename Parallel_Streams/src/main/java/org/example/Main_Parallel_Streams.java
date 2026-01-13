package org.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main_Parallel_Streams {
    public static void main(String[] args) {
        int start = 1;  // inclusive
        int end = 10000000;   // exclusive

        // Generate list from start to end-1
        List<Integer> numbers = IntStream.range(start, end)
                .boxed()
                .collect(Collectors.toList());
        //sum using sequential stream
        // starting time
        long start_time = System.currentTimeMillis();
        long sum = numbers.stream().reduce(0, Integer::sum);
        // ending time
        long end_time = System.currentTimeMillis();
        System.out.println("Time taken by sequential" + (end_time - start_time) + "ms");
        //sum using parallel stream
        start_time = System.currentTimeMillis();
        sum = numbers.parallelStream().reduce(0, Integer::sum);
        // ending time
        end_time = System.currentTimeMillis();
        System.out.println("Time taken by parallel" + (end_time - start_time) + "ms");
    }
}
