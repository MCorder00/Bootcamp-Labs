package org.example.controller;

import org.example.model.Chip;
import org.example.model.Drink;
import org.example.model.Order;
import org.example.view.CLIView;

public class LogicController {
    private final CLIView view;
    private final FileHandler fileHandler;

    public LogicController(CLIView view, FileHandler fileHandler) {
        this.view = view;
        this.fileHandler = fileHandler;
    }

    public void startNewOrder() {
        Order order = new Order();
        view.displayOrderScreen(order);
    }

    public void addDrink(Order order) {
        Drink drink = new Drink(view.selectDrinkSize(), view.selectDrinkFlavor());
        order.addDrink(drink);
        System.out.println("Drink added.");
    }

    public void addChip(Order order) {
        Chip chip = new Chip(view.selectChipType());
        order.addChip(chip);
        System.out.println("Chips added.");
    }

    public void checkout(Order order) {
        if (order.getSandwiches().isEmpty()) {
            System.out.println("Cannot checkout an empty order.");
            return;
        }

        System.out.println("Order Details:");
        System.out.println(order.generateOrderSummary());
        System.out.println("Total Cost: " + order.calculateTotalCost());

        if (confirmOrder()) {
            try {
                fileHandler.writeReceipt(order);
                System.out.println("Order completed and receipt generated.");
            } catch (Exception e) {
                System.out.println("Error writing receipt: " + e.getMessage());
            }
        } else {
            System.out.println("Order cancelled.");
        }
    }

    private boolean confirmOrder() {
        System.out.println("Confirm Order? (Y/N)");
        String confirmation = view.scanner.nextLine().trim().toUpperCase();
        return confirmation.equalsIgnoreCase("Y");
    }
}