package org.example.controller;

import org.example.model.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileHandler {
    public void writeReceipt(Order order) {
        String directoryPath = "receipts/";
        Path directory = Paths.get(directoryPath);

        try {
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String formattedDateTime = now.format(formatter);

            String filePath = directoryPath + formattedDateTime + ".txt";
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write("Order Summary:\n");
                writer.write(order.generateOrderSummary());
            }
            System.out.println("Receipt written to file.");
        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
}