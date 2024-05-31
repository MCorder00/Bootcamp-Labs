package org.example.model;

import org.example.model.enums.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private SandwichSize size;
    private BreadType breadType;
    private final List<PremiumTopping> premiumToppings;
    private final List<Topping> toppings;
    private final List<Sauce> sauces;
    private boolean toasted;

    public Sandwich(SandwichBuilder builder) {
        this.size = builder.size;
        this.breadType = builder.breadType;
        this.premiumToppings = builder.premiumToppings;
        this.toppings = builder.toppings;
        this.sauces = builder.sauces;
        this.toasted = builder.toasted;
    }

    public void setSize(SandwichSize size) {
        this.size = size;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }

    public List<PremiumTopping> getPremiumToppings() {
        return premiumToppings;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public List<Sauce> getSauces() {
        return sauces;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public void addPremiumTopping(PremiumTopping topping) {
        premiumToppings.add(topping);
    }

    public void addExtraPremiumTopping(PremiumTopping topping) {
        premiumToppings.add(topping);
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void addSauce(Sauce sauce) {
        sauces.add(sauce);
    }

    public void removeIngredient(Object ingredient) {
        if (ingredient instanceof PremiumTopping) {
            premiumToppings.remove(ingredient);
        } else if (ingredient instanceof Topping) {
            toppings.remove(ingredient);
        } else if (ingredient instanceof Sauce) {
            sauces.remove(ingredient);
        }
    }

    public BigDecimal calculateCost() {
        BigDecimal cost = size.getPrice().add(breadType.getPrice());
        for (PremiumTopping topping : premiumToppings) {
            cost = cost.add(topping.getPrice(size));
        }
        return cost;
    }

    public String getSandwichDetails() {
        if (size == null || breadType == null) { // replace with optionals at some point
            return "Incomplete sandwich details.";
        }
        StringBuilder details = new StringBuilder(String.format("%s %s Sandwich:\n", size.name(), breadType.name()));
        for (PremiumTopping topping : premiumToppings) {
            String extra = premiumToppings.indexOf(topping) != premiumToppings.lastIndexOf(topping) ? " (Extra)" : "";
            details.append(String.format("- %s%s\n", topping.getName(), extra));
        }
        for (Topping topping : toppings) {
            String extra = toppings.indexOf(topping) != toppings.lastIndexOf(topping) ? " (Extra)" : "";
            details.append(String.format("- %s%s\n", topping.name(), extra));
        }
        for (Sauce sauce : sauces) {
            String extra = sauces.indexOf(sauce) != sauces.lastIndexOf(sauce) ? " (Extra)" : "";
            details.append(String.format("- %s%s\n", sauce.name(), extra));
        }
        if (toasted) {
            details.append("- Toasted\n");
        }
        return details.toString();
    }

    public static class SandwichBuilder {
        private SandwichSize size;
        private BreadType breadType;
        private final List<PremiumTopping> premiumToppings = new ArrayList<>();
        private final List<Topping> toppings = new ArrayList<>();
        private final List<Sauce> sauces = new ArrayList<>();
        private boolean toasted;

        public SandwichBuilder withSize(SandwichSize size) {
            this.size = size;
            return this;
        }

        public SandwichBuilder withBreadType(BreadType breadType) {
            this.breadType = breadType;
            return this;
        }

        public SandwichBuilder addPremiumTopping(PremiumTopping topping) {
            this.premiumToppings.add(topping);
            return this;
        }

        public SandwichBuilder addTopping(Topping topping) {
            this.toppings.add(topping);
            return this;
        }

        public SandwichBuilder addSauce(Sauce sauce) {
            this.sauces.add(sauce);
            return this;
        }

        public SandwichBuilder toasted(boolean toasted) {
            this.toasted = toasted;
            return this;
        }

        public Sandwich build() {
            return new Sandwich(this);
        }

        public static Sandwich buildBLT() {
            return new SandwichBuilder()
                    .withSize(SandwichSize.EIGHT_INCH)
                    .withBreadType(BreadType.WHITE)
                    .addPremiumTopping(Meat.BACON)
                    .addPremiumTopping(Cheese.CHEDDAR)
                    .addTopping(Topping.LETTUCE)
                    .addTopping(Topping.TOMATOES)
                    .addSauce(Sauce.RANCH)
                    .toasted(true)
                    .build();
        }

        public static Sandwich buildPhillyCheesesteak() {
            return new SandwichBuilder()
                    .withSize(SandwichSize.EIGHT_INCH)
                    .withBreadType(BreadType.WHITE)
                    .addPremiumTopping(Meat.STEAK)
                    .addPremiumTopping(Cheese.AMERICAN)
                    .addTopping(Topping.PEPPERS)
                    .addSauce(Sauce.MAYO)
                    .toasted(true)
                    .build();
        }
    }
}