package org.example;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Data {
    private final Queue<String> MessageQueue = new LinkedList<>();
    private final int capacity;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    public Data(int capacity) {
        this.capacity = capacity;
    }
    public void produce(String message) throws InterruptedException {
        lock.lock();
        try {
            while (MessageQueue.size() == capacity) {
                notFull.await();
            }
            MessageQueue.add(message);
            System.out.println("Produced message is : " + message);
            notEmpty.signalAll();
        }
        finally {
            lock.unlock();
        }
    }
    public void consume() throws InterruptedException {
        lock.lock();
        try{
        while (MessageQueue.isEmpty()) {
            notEmpty.await();
        }
            String message= MessageQueue.poll();
            System.out.println("Consumed message is: " + message);
            notFull.signalAll();}
        finally {
            lock.unlock();
        }


    }
}

