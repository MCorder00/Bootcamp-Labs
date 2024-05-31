package org.example.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Sandwich> sandwiches;
    private final List<Drink> drinks;
    private final List<Chip> chips;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChip(Chip chip) {
        chips.add(chip);
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public BigDecimal calculateTotalCost() {
        BigDecimal total = BigDecimal.ZERO;
        for (Sandwich sandwich : sandwiches) {
            total = total.add(sandwich.calculateCost());
        }
        for (Drink drink : drinks) {
            total = total.add(drink.getPrice());
        }
        for (Chip chip : chips) {
            total = total.add(chip.getPrice());
        }
        return total;
    }

    public String generateOrderSummary() {
        StringBuilder summary = new StringBuilder("Sandwiches:\n");
        for (Sandwich sandwich : sandwiches) {
            summary.append(sandwich.getSandwichDetails());
        }
        summary.append("Drinks:\n");
        for (Drink drink : drinks) {
            summary.append(drink.getDrinkDetails()).append("\n");
        }
        summary.append("Chips:\n");
        for (Chip chip : chips) {
            summary.append(chip.getChipDetails()).append("\n");
        }
        summary.append("Total Cost: $").append(calculateTotalCost()).append("\n");
        return summary.toString();
    }
}
