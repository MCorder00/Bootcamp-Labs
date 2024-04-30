package org.example.commands;

import org.example.business.Cart;
import org.example.business.ProductRepository;
import org.example.business.Product;
import org.example.ui.CLI;
import org.example.utils.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisplayProductsCommand implements Command {
    private final Cart cart;
    private final Scanner scanner;
    private final CLI cli;
    public DisplayProductsCommand(Cart cart, Scanner scanner, CLI cli) {
        this.cart = cart;
        this.scanner = scanner;
        this.cli = cli;
    }

    @Override
    public void execute() {
        boolean display = true;
        while (display) {
            displayProducts();
            System.out.println("\nOptions:");
            System.out.println("1. Search products");
            System.out.println("2. Add product to cart");
            System.out.println("3. Go back");

            int option = cli.getIntegerInput("Enter desired option: ");
            switch (option) {
                case 1:
                    searchProductsPrompt();
                    break;
                case 2:
                    cli.handleProductSelection(new ArrayList<>(ProductRepository.getProducts().values()));
                    break;
                case 3:
                    display = false;
                    break;
                default:
                    System.out.println("That's not a valid option. Please try again.");
            }
        }
    }

    private void displayProducts() {
        System.out.println("Available Products in Catalog:");
        for (Product product : ProductRepository.getProducts().values()) {
            System.out.println(product.getSku() + " - " + product.getName() + " ($" + product.getPrice() + ") - " + product.getDepartment());
        }
    }

    private void searchProductsPrompt() {
        System.out.println("Search options:");
        for (SearchCriteria criteria : SearchCriteria.values()) {
            System.out.println(criteria.getValue() + ". " + criteria.getDescription());
        }

        int searchOption = cli.getIntegerInput("Enter search option: ");

        switch (searchOption) {
            case 1:
                searchByDepartment();
                break;
            case 2:
                searchByName();
                break;
            case 3:
                searchByPriceRange();
                break;
            default:
                System.out.println("That's not a valid search option. Please try again.");
        }
    }

    private void searchByDepartment() {
        String department = cli.getStringInput("Enter department name: ");
        List<Product> searchResults = new ArrayList<>(); // list to simplify add/remove with .add
        for (Product product : ProductRepository.getProducts().values()) { // iterator
            if (product.getDepartment().equalsIgnoreCase(department)) { // department uses enums
                searchResults.add(product);
            }
        }
        displaySearchResults(searchResults);
    }

    private void searchByName() {
        String name = cli.getStringInput("Enter desired product name: ");
        List<Product> searchResults = new ArrayList<>();
        for (Product product : ProductRepository.getProducts().values()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                searchResults.add(product);
            }
        }
        displaySearchResults(searchResults);
    }

    private void searchByPriceRange() {
        double minPrice = cli.getDoubleInput("Enter the lowest price in the range (in digits): ");
        double maxPrice = cli.getDoubleInput("Enter the highest price in the range (in digits): ");
        List<Product> searchResults = new ArrayList<>();
        for (Product product : ProductRepository.getProducts().values()) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                searchResults.add(product);
            }
        }
        displaySearchResults(searchResults);
    }

    private void displaySearchResults(List<Product> searchResults) {
        if (searchResults.isEmpty()) { //
            System.out.println("No products found matching the search criteria.");
        } else {
            System.out.println("Search Results:");
            for (Product product : searchResults) {
                System.out.println(product.getSku() + " - " + product.getName() + " ($" + product.getPrice() + ") - " + product.getDepartment());
            }
            cli.handleProductSelection(searchResults);
        }
    }
}