package org.example.business;

import java.util.HashMap;
import java.util.Map;

public class    Cart {
    private final Map<String, Integer> items;
    private double totalAmount;

    public Cart() {
        items = new HashMap<>();
        totalAmount = 0.0;
    } // constructor

    public void addProduct(Product product, int quantity) {
        String sku = product.getSku();
        items.put(sku, items.getOrDefault(sku, 0) + quantity);
        totalAmount += product.getPrice() * quantity;
    } // using put function from HashMap to add product to cart
    // then update total amount in cart

    public void removeProduct(String sku, int quantity) {
        if (items.containsKey(sku)) {
            int currentQuantity = items.get(sku);
            if (currentQuantity <= quantity) {
                items.remove(sku);
                totalAmount -= getProductBySku(sku).getPrice() * currentQuantity;
            } else {
                items.put(sku, currentQuantity - quantity);
                totalAmount -= getProductBySku(sku).getPrice() * quantity;
            } // remove product from cart, then update total amount in cart
        }
    }
    // getters
    public Map<String, Integer> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    // used to clear cart after checkout
    public void clearCart() {
        items.clear();
        totalAmount = 0.0;
    }

    public Product getProductBySku(String sku) {
        return ProductRepository.getProductBySku(sku);
    }
    // used to display cart at checkout
    public int getTotalItems() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }
}