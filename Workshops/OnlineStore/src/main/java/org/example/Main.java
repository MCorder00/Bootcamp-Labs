package org.example;

import org.example.business.Cart;
import org.example.business.ProductRepository;
import org.example.ui.CLI;
import org.example.utils.Constants;

public class Main {
    public static void main(String[] args) {
        ProductRepository.loadProducts(Constants.INVENTORY_FILE_PATH);

        Cart cart = new Cart();
        CLI cli = new CLI(cart);
        System.out.println("BOOTING FROM DLO");
        cli.run();
    }
}
/* Pseudocode
*Reqs: Use products.csv to load store product inventory
* Create Product class to store above properties
* Customers should be able to view all products
* Customers should be able to add products to cart
* Customers should be able to remove products from cart
*Screens:
*Store Home Screen
*The home screen should have a menu with the following options:
* Display Products | Display Cart | Exit
*Display Products Screen
* Displays a list of products the store has in inventory
* The screen should let customer search or filter products
* The screen should let a customer add a product to cart
* The screen should let customer go back to the home page
* Display Cart Screen
* Displays list of line items in customers cat
* Displays total sales amount cost
* Customer should be able to Check Out
* Customer should be able to Remove Products from cart
* Customer should be able to Go Back to Home Screen
* */