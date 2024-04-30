package org.example.ui;

import org.example.commands.*;
import org.example.business.Cart;
import org.example.business.ProductRepository;
import org.example.business.Product;
import org.example.utils.CommandOption;

import java.util.List;
import java.util.Scanner;

public class CLI {
    private final Cart cart;
    private final Scanner scanner;

    public CLI(Cart cart) {
        this.cart = cart;
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            displayHomeMenu();
            int commandOption = getIntegerInput("Enter option: ");
            switch (commandOption) {
                case 1:
                    new DisplayCartCommand(cart, scanner, this).execute();
                    break;
                case 2:
                    new DisplayProductsCommand(cart, scanner, this).execute();
                    break;
                case 3:
                    running = false;
                    System.out.println("Thank you for shopping at THE ELECTRONIC MALL. Exiting program.");
                    break;
                default:
                    System.out.println("That's not a valid  option. Please try again.");
            }
        }
    }

    private void displayHomeMenu() {
        System.out.println("NetSpace Esper 6 welcomes you to:");
        System.out.println("""
                _/_/ T H E
               _/_/ E L E C T R O N I C
                   M A L L (tm 1984)""");
        System.out.println("Public Access Online Products Catalog");
        System.out.println("Please select an option:");
        for (CommandOption option : CommandOption.values()) {
            System.out.println(option.getValue() + ". " + option.getDescription());
        }
    }

    public void handleProductSelection(List<Product> products) {
        String sku = getStringInput("Enter the SKU (case-sensitive) of the product to add to cart (or 'back' to return): ");

        if (sku.equalsIgnoreCase("back")) {
            return;
        }

        Product selectedProduct = ProductRepository.getProductBySku(sku);

        if (selectedProduct != null) {
            int quantity = getIntegerInput("Enter the quantity to add to cart: ");

            cart.addProduct(selectedProduct, quantity);
            System.out.println("Added " + quantity + " " + selectedProduct.getName() + "(s) to the cart.");
        } else {
            System.out.println("Invalid SKU.");
        }
    }

    public int getIntegerInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}