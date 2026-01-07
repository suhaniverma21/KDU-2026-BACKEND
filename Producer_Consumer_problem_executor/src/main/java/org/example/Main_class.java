package org.example;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MessageSender implements Runnable {
    private final Data data;
    public MessageSender(Data data) { this.data = data; }
    public void run() {
        int value = 0;
        try {
            for(int i=0;i<5;i++) {
                String message = Thread.currentThread().getName() + " -> " + System.currentTimeMillis();

                //String message="The timestamp is:"+timestamp;
                data.produce(message);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
class MessageReceiver  implements Runnable {
    private final Data data;
    public MessageReceiver (Data data) { this.data = data; }
    public void run() {
        try {
            for(int i=0;i<5;i++) {
                data.consume();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}

public class Main_class {
    public static void main(String[] args) {
        ExecutorService executorSender= Executors.newFixedThreadPool(3);
        ExecutorService executorReceiver= Executors.newFixedThreadPool(3);

        Data data = new Data(5);
        //3 SENDERS
        executorSender.submit(new MessageSender(data));
        executorSender.submit(new MessageSender(data));
        executorSender.submit(new MessageSender(data));

        //3 CONSUMERS
        executorReceiver.submit(new MessageReceiver(data));
        executorReceiver.submit(new MessageReceiver(data));
        executorReceiver.submit(new MessageReceiver(data));

        //graceful shutdown
        executorSender.shutdown();
        try {
            if (!executorSender.awaitTermination(30, TimeUnit.SECONDS)) {
                executorSender.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorSender.shutdownNow();
            Thread.currentThread().interrupt();
        }

        executorReceiver.shutdown();
        try {
            if (!executorReceiver.awaitTermination(30, TimeUnit.SECONDS)) {
                executorReceiver.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorReceiver.shutdownNow();
            Thread.currentThread().interrupt();
        }

    }
}

