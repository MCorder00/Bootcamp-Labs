package org.example.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.business.Product;

public class FileOperations {
    public static List<Product> loadProductsFromFile(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header line
                }
                String[] values = line.split("\\|");
                String sku = values[0];
                String name = values[1];
                double price = Double.parseDouble(values[2]);
                String department = values[3];
                Product product = new Product(sku, name, price, department);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void generateReceipt(String filePath, List<String> receiptLines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : receiptLines) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Receipt generated: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}