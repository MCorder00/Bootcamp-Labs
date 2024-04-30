package org.example.commands;
 // holy import batman
import org.example.business.Cart;
import org.example.business.ProductRepository;
import org.example.business.Product;
import org.example.ui.CLI;
import org.example.utils.Constants;
import org.example.utils.FileOperations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckoutCommand implements Command { // interface for command pattern
    private final Cart cart;
    private final CLI cli;
    public CheckoutCommand(Cart cart, CLI cli) {
        this.cart = cart;
        this.cli = cli;
    }

    @Override
    public void execute() { // override for command
        double totalAmount = cart.getTotalAmount();
        System.out.println("Total amount: $" + totalAmount);

        String input = cli.getStringInput("Enter your payment amount (in digits - '123.45') (or enter 'back' to return): ");

        if (input.equalsIgnoreCase("back")) {
            return;
        }

        double paymentAmount;
        try {
            paymentAmount = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number (in digits - 123.45).");
            return;
        }

        if (paymentAmount >= totalAmount) {
            double change = paymentAmount - totalAmount;
            System.out.println("Your change is: $" + change);

            generateReceipt(totalAmount, paymentAmount, change);
            cart.clearCart();
        } else {
            System.out.println("That's not enough. Please enter a higher amount.");
        }
    }

    private void generateReceipt(double totalAmount, double paymentAmount, double change) {
        List<String> receiptLines = new ArrayList<>(); // Create a list to store receipt lines
        // Add receipt header information with overly long and complicated date time format syntax
        receiptLines.add("Order Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        receiptLines.add("Line Items:"); // Add line items header
        // add sout
        System.out.println("------------------------------");
        System.out.println("SALES RECEIPT"); // kinda cheesy last minute implementation here
        System.out.println("------------------------------");
        System.out.println("Order Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("------------------------------");
        System.out.println("Line Items:");
        // Add receipt line items
        Map<String, Integer> items = cart.getItems();
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String sku = entry.getKey();
            int quantity = entry.getValue();
            Product product = ProductRepository.getProductBySku(sku);
            double itemTotal = product.getPrice() * quantity;
            receiptLines.add(String.format("%-20s x %-3d $%-8.2f", product.getName(), quantity, itemTotal));
            System.out.printf("%-20s x %-3d $%-8.2f \n", product.getName(), quantity, itemTotal);
        } // Add receipt totals and change, more boilerplate

        receiptLines.add("------------------------------");
        receiptLines.add(String.format("Total: $%-8.2f", totalAmount));
        receiptLines.add(String.format("Amount Paid: $%-8.2f", paymentAmount));
        receiptLines.add(String.format("Change: $%-8.2f", change));
        // words of sout
        System.out.println("------------------------------");
        System.out.printf("Total Items: %-3d \n", cart.getTotalItems());
        System.out.println("------------------------------");
        System.out.printf("Total Amount: $%-8.2f \n", cart.getTotalAmount());
        System.out.printf("Amount Paid: $%-8.2f \n", paymentAmount);
        System.out.printf("Change: $%-8.2f \n", change);
        System.out.println("------------------------------");
        System.out.println( "Thank you for shopping with us!");
        // Generate receipt file by using FileOperations class as handler
        String receiptFilePath = generateReceiptFilePath();
        FileOperations.generateReceipt(receiptFilePath, receiptLines);
    }

    private String generateReceiptFilePath() {
        LocalDateTime now = LocalDateTime.now(); // Generate receipt file name using constants
        String fileName = now.format(DateTimeFormatter.ofPattern(Constants.RECEIPT_FILE_FORMAT)) + Constants.RECEIPT_FILE_EXTENSION;
        return Constants.RECEIPT_DIRECTORY + fileName;
    }
}