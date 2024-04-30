package org.example.business;

import org.example.utils.FileOperations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private static final Map<String, Product> products = new HashMap<>();

    public static void loadProducts(String filePath) {
        products.clear(); // Clear the existing products
        List<Product> productList = FileOperations.loadProductsFromFile(filePath);
        for (Product product : productList) { // Add each product to the inventory
            products.put(product.getSku(), product);
        } // maps SKU to product
    }

    public static Map<String, Product> getProducts() {
        return products; // getter for products in inventory
    }

    public static Product getProductBySku(String sku) {
        return products.get(sku); // getter for product SKU in inventory
    }
}