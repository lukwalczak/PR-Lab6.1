package org.example;

import java.util.concurrent.ExecutorService;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        int numberOfProducers = 10;
        int numberOfConsumers = 2;
        try {
            ExecutorService producers = java.util.concurrent.Executors.newFixedThreadPool(numberOfProducers);
            for (int i = 0; i < numberOfProducers; i++) {
                producers.submit(new Producer(warehouse));
            }
            ExecutorService consumers = java.util.concurrent.Executors.newFixedThreadPool(numberOfConsumers);
            for (int i = 0; i < numberOfConsumers; i++) {
                consumers.submit(new Consumer(warehouse));
            }
            Thread.sleep(10000);
            warehouse.close();
            producers.shutdown();
            consumers.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}