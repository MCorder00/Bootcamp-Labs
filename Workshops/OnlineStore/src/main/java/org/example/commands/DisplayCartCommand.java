package org.example.commands;

import org.example.business.Cart;
import org.example.business.ProductRepository;
import org.example.ui.CLI;
import org.example.business.Product;

import java.util.Map;
import java.util.Scanner;


public class DisplayCartCommand implements Command {
    private final Cart cart;
    private final CLI cli;

    public DisplayCartCommand(Cart cart, Scanner scanner, CLI cli) {
        this.cart = cart;
        this.cli = cli;
    }

    @Override
    public void execute() {
        boolean display = true;
        while (display) {
            displayCart();
            System.out.println("\nOptions:");
            System.out.println("1. Check Out");
            System.out.println("2. Remove Product from Cart");
            System.out.println("3. Go Back");

            int option = cli.getIntegerInput("Enter option: ");
            switch (option) { // switch case for user input
                case 1:
                    new CheckoutCommand(cart, cli).execute();
                    break; // passed cart and cli
                case 2:
                    removeProductFromCart();
                    break;
                case 3:
                    display = false;
                    break;
                default:
                    System.out.println("That's not a valid option. Please try again.");
            }
        }
    }

    private void displayCart() {
        Map<String, Integer> items = cart.getItems(); // map to track quantities in cart (like dictionary)
        double totalCost = cart.getTotalAmount();
        int totalItems = cart.getTotalItems();

        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in your cart:");
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                String sku = entry.getKey();
                int quantity = entry.getValue();
                Product product = ProductRepository.getProductBySku(sku);
                double itemTotal = product.getPrice() * quantity;
                System.out.printf("SKU: %-10s %-20s x %-3d $%-8.2f \n", sku, product.getName(), quantity, itemTotal);
            }
            System.out.println("------------------------------"); // format syntax is yucky
            System.out.printf("Total Items: %-3d \n", totalItems);
            System.out.printf("Total Amount: $%-8.2f \n", totalCost);
        }
    }

    private void removeProductFromCart() {
        String sku = cli.getStringInput("Enter product SKU to remove (case-sensitive): ");

        Product product = cart.getProductBySku(sku);
        if (product != null) {
            int quantity = cli.getIntegerInput("Enter the amount of products to remove (in digits e.g. '1'): ");
            cart.removeProduct(sku, quantity);
            System.out.println("Product was successfully removed from the cart.");
        } else {
            System.out.println("Product was not found in the cart.");
        }
    }
}