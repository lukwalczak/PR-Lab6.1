package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {

    private final Warehouse warehouse;
    private String[] products;

    public Consumer(Warehouse warehouse, String[] products) {
        this.warehouse = warehouse;
        this.products = products;
    }

    public Consumer(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.products = new String[]{"coffee", "tea", "milk", "sugar", "salt"};
    }

    @Override
    public void run() {
        while (warehouse.isOpen()) {
            int randomProductIndex = ThreadLocalRandom.current().nextInt(0, this.products.length);
            int randomQuantity = ThreadLocalRandom.current().nextInt(1, 11);
            Integer quantity = warehouse.get(this.products[randomProductIndex], randomQuantity);
            System.out.println("Consumer " + Thread.currentThread().getName() + " took " + quantity + " " + this.products[randomProductIndex] + " from the warehouse");
            System.out.println("--------------------");
            try {
                int randomSleepTime = ThreadLocalRandom.current().nextInt(1, 5) * 1000;
                Thread.sleep(randomSleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
