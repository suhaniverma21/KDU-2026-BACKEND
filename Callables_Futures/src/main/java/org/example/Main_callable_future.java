package org.example;

import java.util.concurrent.*;

public class Main_callable_future {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        int n=5000;
        Callable<Integer> task = () -> {
            int sum=0;
            for(int i=1;i<n+1;i++)
            {
                sum+=i;
            }
            //Thread.sleep(1000); // simulate delay
            return sum;
        };

        Future<Integer> future = executor.submit(task);

        Integer result;
        try {
            result = future.get();   // blocks until result
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Result: " + result);

        // graceful shutdown
        executor.shutdown();
        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

    }
}
