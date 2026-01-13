package org.example;

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
        Data data = new Data(5);
        //3 SENDERS
        new Thread(new MessageSender(data), "Sender-1").start();
        new Thread(new MessageSender(data), "Sender-2").start();
        new Thread(new MessageSender(data), "Sender-3").start();

        //3 CONSUMERS
        new Thread(new MessageReceiver(data), "Receiver-1").start();
        new Thread(new MessageReceiver(data), "Receiver-2").start();
        new Thread(new MessageReceiver(data), "Receiver-3").start();


    }
}
