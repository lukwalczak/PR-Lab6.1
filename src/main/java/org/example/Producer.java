package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable{

    private final Warehouse warehouse;
    private String[] products;

    public Producer(Warehouse warehouse, String[] products) {
        this.warehouse = warehouse;
        this.products = products;
    }

    public Producer(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.products = new String[]{"coffee", "tea", "milk", "sugar", "salt"};
    }

    @Override
    public void run() {
        while (warehouse.isOpen()) {
            int randomProductIndex = ThreadLocalRandom.current().nextInt(0, this.products.length);
            int randomQuantity = ThreadLocalRandom.current().nextInt(1, 11);
            warehouse.add(this.products[randomProductIndex], randomQuantity);
            System.out.println("Producer " + Thread.currentThread().getName()  + " added " + randomQuantity + " " + this.products[randomProductIndex] + " to the warehouse");
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
