package org.example.view;

import org.example.controller.LogicController;
import org.example.model.Order;
import org.example.model.Sandwich;
import org.example.model.enums.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CLIView {
    public final Scanner scanner = new Scanner(System.in);
    private LogicController logicController;

    public CLIView() {
        // parameterless constructor
    }

    public void setLogicController(LogicController logicController) {
        this.logicController = logicController;
    }

    private boolean selectExtra() {
        System.out.println("Would you like extra?");
        System.out.println("1) Yes");
        System.out.println("2) No");
        int choice = getIntInput();
        return choice == 1;
    }

    public int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    public String getStringInput() {
        return scanner.nextLine().trim();
    }
    public void displayHomeScreen() {
        while (true) {
            System.out.println("Welcome to DELI-cious");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            int choice = getIntInput();
            getStringInput();

            switch (choice) {
                case 1 -> logicController.startNewOrder();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public void displayOrderScreen(Order order) {
        while (true) {
            System.out.println("Order Menu");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            int choice = getIntInput();
            getStringInput();

            switch (choice) {
                case 1 -> displaySandwichScreen(order);
                case 2 -> logicController.addDrink(order);
                case 3 -> logicController.addChip(order);
                case 4 -> {
                    logicController.checkout(order);
                    return;
                }
                case 0 -> {
                    System.out.println("Order cancelled.");
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public void displaySandwichScreen(Order order) {
        Sandwich sandwich;
        if (order.getSandwiches().isEmpty()) {
            sandwich = new Sandwich.SandwichBuilder().build();
            order.addSandwich(sandwich);
        } else {
            sandwich = order.getSandwiches().get(order.getSandwiches().size() - 1);
        }

        while (true) {
            System.out.println("Sandwich Menu");
            System.out.println("Current Sandwich:");
            System.out.println(sandwich.getSandwichDetails());

            System.out.println("1) Select Bread");
            System.out.println("2) Select Size");
            System.out.println("3) Add Meat");
            System.out.println("4) Add Cheese");
            System.out.println("5) Add Topping");
            System.out.println("6) Add Sauce");
            System.out.println("7) Remove Ingredient");
            System.out.println("8) Toast Sandwich");
            System.out.println("9) Finish Sandwich");
            System.out.println("10) Choose Signature Sandwich");
            System.out.println("0) Cancel Sandwich");

            try {
            int choice = getIntInput();
            getStringInput();

                switch (choice) {
                    case 1 -> sandwich.setBreadType(selectBreadType());
                    case 2 -> sandwich.setSize(selectSandwichSize());
                    case 3 -> {
                        Meat meat = selectMeat();
                        sandwich.addPremiumTopping(meat);
                        if (selectExtra()) {
                            sandwich.addExtraPremiumTopping(meat);
                        }
                    }
                    case 4 -> {
                        Cheese cheese = selectCheese();
                        sandwich.addPremiumTopping(cheese);
                        if (selectExtra()) {
                            sandwich.addExtraPremiumTopping(cheese);
                        }
                    }
                    case 5 -> {
                        Topping topping = selectTopping();
                        sandwich.addTopping(topping);
                        if (selectExtra()) {
                            sandwich.addTopping(topping);
                        }
                    }
                    case 6 -> {
                        Sauce sauce = selectSauce();
                        sandwich.addSauce(sauce);
                        if (selectExtra()) {
                            sandwich.addSauce(sauce);
                        }
                    }
                    case 7 -> removeIngredient(sandwich);
                    case 8 -> sandwich.setToasted(selectToasting());
                    case 9 -> {
                        addSandwichToOrder(sandwich, order);
                        return;
                    }
                    case 10 -> {
                        selectSignatureSandwich(order);
                       if(!order.getSandwiches().isEmpty()) {
                        sandwich = order.getSandwiches().get(order.getSandwiches().size() - 1);
                       }
                    }
                    case 0 -> {
                        order.getSandwiches().remove(sandwich);
                        return;
                    }
                    default -> System.out.println("Invalid choice, please try again.");
                }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please try again.");
            getStringInput();
        }
        }
    }

    private void selectSignatureSandwich(Order order) {
        System.out.println("Select Signature Sandwich:");
        System.out.println("1) BLT");
        System.out.println("2) Philly Cheesesteak");
        int choice = getIntInput();

        Sandwich sandwich;
        switch (choice) {
            case 1 -> sandwich = Sandwich.SandwichBuilder.buildBLT();
            case 2 -> sandwich = Sandwich.SandwichBuilder.buildPhillyCheesesteak();
            default -> {
                System.out.println("Invalid choice, returning to sandwich menu.");
                return;
            }
        }
        if (!order.getSandwiches().isEmpty()) {
            order.getSandwiches().remove(order.getSandwiches().size() - 1);
        }
        order.getSandwiches().add(sandwich);
    }

    public SandwichSize selectSandwichSize() {
        System.out.println("Select sandwich size:");
        System.out.println("1) 4 inch");
        System.out.println("2) 8 inch");
        System.out.println("3) 12 inch");
        int choice = getIntInput();
        switch (choice) {
            case 1 -> {
                return SandwichSize.FOUR_INCH;
            }
            case 2 -> {
                return SandwichSize.EIGHT_INCH;
            }
            case 3 -> {
                return SandwichSize.TWELVE_INCH;
            }
            default -> {
                System.out.println("Invalid choice, defaulting to 8 inch.");
                return SandwichSize.EIGHT_INCH;
            }
        }
    }

    public BreadType selectBreadType() {
        System.out.println("Select bread type:");
        System.out.println("1) White");
        System.out.println("2) Wheat");
        System.out.println("3) Rye");
        System.out.println("4) Wrap");
        int choice = getIntInput();
        switch (choice) {
            case 1 -> {
                return BreadType.WHITE;
            }
            case 2 -> {
                return BreadType.WHEAT;
            }
            case 3 -> {
                return BreadType.RYE;
            }
            case 4 -> {
                return BreadType.WRAP;
            }
            default -> {
                System.out.println("Invalid choice, defaulting to White.");
                return BreadType.WHITE;
            }
        }
    }

    public Topping selectTopping() {
        System.out.println("Select topping:");
        System.out.println("1) Lettuce");
        System.out.println("2) Peppers");
        System.out.println("3) Onions");
        System.out.println("4) Tomatoes");
        System.out.println("5) Jalapenos");
        System.out.println("6) Cucumbers");
        System.out.println("7) Pickles");
        System.out.println("8) Guacamole");
        System.out.println("9) Mushrooms");
        int choice = getIntInput();
        switch (choice) {
            case 1 -> {
                return Topping.LETTUCE;
            }
            case 2 -> {
                return Topping.PEPPERS;
            }
            case 3 -> {
                return Topping.ONIONS;
            }
            case 4 -> {
                return Topping.TOMATOES;
            }
            case 5 -> {
                return Topping.JALAPENOS;
            }
            case 6 -> {
                return Topping.CUCUMBERS;
            }
            case 7 -> {
                return Topping.PICKLES;
            }
            case 8 -> {
                return Topping.GUACAMOLE;
            }
            case 9 -> {
                return Topping.MUSHROOMS;
            }
            default -> {
                System.out.println("Invalid choice, defaulting to Lettuce.");
                return Topping.LETTUCE;
            }
        }
    }

    public Meat selectMeat() {
        System.out.println("Select meat:");
        System.out.println("1) Salami");
        System.out.println("2) Roast Beef");
        System.out.println("3) Chicken");
        System.out.println("4) Bacon");
        int choice = getIntInput();
        switch (choice) {
            case 1 -> {
                return Meat.SALAMI;
            }
            case 2 -> {
                return Meat.ROAST_BEEF;
            }
            case 3 -> {
                return Meat.CHICKEN;
            }
            case 4 -> {
                return Meat.BACON;
            }
            default -> {
                System.out.println("Invalid choice, defaulting to Salami.");
                return Meat.SALAMI;
            }
        }
    }

    public Cheese selectCheese() {
        System.out.println("Select cheese:");
        System.out.println("1) American");
        System.out.println("2) Provolone");
        System.out.println("3) Cheddar");
        System.out.println("4) Swiss");
        int choice = getIntInput();
        switch (choice) {
            case 1 -> {
                return Cheese.AMERICAN;
            }
            case 2 -> {
                return Cheese.PROVOLONE;
            }
            case 3 -> {
                return Cheese.CHEDDAR;
            }
            case 4 -> {
                return Cheese.SWISS;
            }
            default -> {
                System.out.println("Invalid choice, defaulting to American.");
                return Cheese.AMERICAN;
            }
        }
    }

    public Sauce selectSauce() {
        System.out.println("Select sauce:");
        System.out.println("1) Mayo");
        System.out.println("2) Mustard");
        System.out.println("3) Ketchup");
        System.out.println("4) Ranch");
        System.out.println("5) Thousand Island");
        System.out.println("6) Vinaigrette");
        int choice = getIntInput();
        switch (choice) {
            case 1 -> {
                return Sauce.MAYO;
            }
            case 2 -> {
                return Sauce.MUSTARD;
            }
            case 3 -> {
                return Sauce.KETCHUP;
            }
            case 4 -> {
                return Sauce.RANCH;
            }
            case 5 -> {
                return Sauce.THOUSAND_ISLAND;
            }
            case 6 -> {
                return Sauce.VINAIGRETTE;
            }
            default -> {
                System.out.println("Invalid choice, defaulting to Mayo.");
                return Sauce.MAYO;
            }
        }
    }

    public DrinkSize selectDrinkSize() {
        System.out.println("Select drink size:");
        System.out.println("1) Small");
        System.out.println("2) Medium");
        System.out.println("3) Large");
        int choice = getIntInput();
        switch (choice) {
            case 1 -> {
                return DrinkSize.SMALL;
            }
            case 2 -> {
                return DrinkSize.MEDIUM;
            }
            case 3 -> {
                return DrinkSize.LARGE;
            }
            default -> {
                System.out.println("Invalid choice, defaulting to Small.");
                return DrinkSize.SMALL;
            }
        }
    }

    public String selectDrinkFlavor() {
        System.out.println("Select drink flavor:");
        System.out.println("1) Super-Cola");
        System.out.println("2) Lemonade");
        System.out.println("3) Iced Tea");
        int choice = getIntInput();
        switch (choice) {
            case 1 -> {
                return "Super-Cola";
            }
            case 2 -> {
                return "Lemonade";
            }
            case 3 -> {
                return "Iced Tea";
            }
            default -> {
                System.out.println("Invalid choice, defaulting to Super-Cola.");
                return "Super-Cola";
            }
        }
    }

    public ChipType selectChipType() {
        System.out.println("Select chip type:");
        System.out.println("1) Classic");
        System.out.println("2) BBQ");
        System.out.println("3) Salt & Vinegar");
        int choice = getIntInput();
        switch (choice) {
            case 1 -> {
                return ChipType.CLASSIC;
            }
            case 2 -> {
                return ChipType.BBQ;
            }
            case 3 -> {
                return ChipType.SALT_VINEGAR;
            }
            default -> {
                System.out.println("Invalid choice, defaulting to Classic.");
                return ChipType.CLASSIC;
            }
        }
    }
    public boolean selectToasting() {
        System.out.println("Would you like the sandwich toasted?");
        System.out.println("1) Yes");
        System.out.println("2) No");
        int choice = getIntInput();
        return choice == 1;
    }

    private void removeIngredient(Sandwich sandwich) {
        System.out.println("Select ingredient to remove:");
        List<Object> ingredients = new ArrayList<>();
        ingredients.addAll(sandwich.getPremiumToppings());
        ingredients.addAll(sandwich.getToppings());
        ingredients.addAll(sandwich.getSauces());

        for (int i = 0; i < ingredients.size(); i++) {
            System.out.println((i + 1) + ". "
                    + ingredients.get(i).getClass().getSimpleName()
                    + ": " + ingredients.get(i).toString());
        }

        int choice = getIntInput();

        if (choice >= 1 && choice <= ingredients.size()) {
            Object ingredient = ingredients.get(choice - 1);
            sandwich.removeIngredient(ingredient);
            System.out.println("Ingredient removed.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void addSandwichToOrder(Sandwich sandwich, Order order) {
        // Check if the order already contains the sandwich
        for (Sandwich existingSandwich : order.getSandwiches()) {
            if (existingSandwich.equals(sandwich)) {
                System.out.println("This sandwich is already in your order.");
                return;
            }
        }

        order.addSandwich(sandwich);
        System.out.println("Sandwich added to order.");
        System.out.println("Would you like to create a new sandwich? (y/n)");
        String response = getStringInput();
        if (response.equalsIgnoreCase("y")) {
            Sandwich newSandwich = new Sandwich.SandwichBuilder().build();
            order.addSandwich(newSandwich); // Add the new sandwich to the order
            System.out.println("New blank sandwich created.");
        }
    }

}