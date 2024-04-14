package org.example;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    volatile boolean isClosed = false;

    final private Map<String, Integer> stock = new HashMap<String, Integer>();

    public Warehouse() {
    }

    public synchronized boolean isOpen() {
        return !isClosed;
    }

    public synchronized void close() {
        isClosed = true;
        notifyAll();
    }

    private void printStock(){
        System.out.println("--------------------");
        for(Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println("Stock left: " + entry.getKey() + " : " + entry.getValue());
        }
    }

    public synchronized void add(String product, int quantity) {
        if (stock.containsKey(product)) {
            stock.put(product, stock.get(product) + quantity);
        } else {
            stock.put(product, quantity);
        }
        this.printStock();
    }

    //returns the quantity of the product that was actually removed
    public synchronized Integer get(String product, int quantity) {
        if (stock.containsKey(product) && stock.get(product) >= quantity) {
            stock.put(product, stock.get(product) - quantity);
            this.printStock();
            return quantity;
        }else if (stock.containsKey(product) && stock.get(product) != 0){
            Integer stockQuantity = stock.get(product);
            stock.put(product, 0);
            this.printStock();
            return stockQuantity;
        }
        return 0;
    }




}
