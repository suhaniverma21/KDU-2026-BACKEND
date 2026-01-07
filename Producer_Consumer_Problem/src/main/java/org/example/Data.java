package org.example;

import java.util.LinkedList;
import java.util.Queue;
class Data {
    private final Queue<String> MessageQueue = new LinkedList<>();
    private final int capacity;
    public Data(int capacity) {
        this.capacity = capacity;
    }
    public synchronized void produce(String message) throws InterruptedException {
        while (MessageQueue.size() == capacity) {
            wait();
        }
        MessageQueue.add(message);
        System.out.println("Produced message is : " + message);
        notifyAll();
    }
    public synchronized void consume() throws InterruptedException {
        while (MessageQueue.isEmpty()) {
            wait();
        }
        //int val = MessageQueue.poll();
        //System.out.println("Consumed: " + val);
        String message= MessageQueue.poll();
        System.out.println("Consumed message is: " + message);
        notifyAll();
    }
}
