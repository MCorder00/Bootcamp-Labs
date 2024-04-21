package org.example;

import java.util.Scanner;

public class Screen {
    static Scanner sc = new Scanner(System.in);
    static Screen screen = new Screen();
    static LogicHandler logic = new LogicHandler();
    static Book book = new Book();
    public void showHomeScreen() {
    boolean shouldContinue = true;
        // nested try/catch inside do/while loop to restart program
        do {
            try {
                //Menu prompts
                System.out.println("""
                                     .--.           .---.        .-.
                                 .---|--|   .-.     | A |  .---. |~|    .--.
                              .--|===|Ch|---|_|--.__| S |--|:::| |~|-==-|==|---.
                              |%%|NT2|oc|===| |~~|%%| C |--|   |_|~|CATS|  |___|-.
                              |  |   |ah|===| |==|  | I |  |:::|=| |    |GB|---|=|
                              |  |   |ol|   |_|__|  | I |__|   | | |    |  |___| |
                              |~~|===|--|===|~|~~|%%|~~~|--|:::|=|~|----|==|---|=|
                              ^--^---'--^---^-^--^--^---'--^---^-^-^-==-^--^---^-'""");
                System.out.println("YEAR UP PUBLIC LIBRARY");
                System.out.println("Welcome to the Online Public Access Catalog!");
                System.out.println("Please select one of the options below.");
                System.out.println("--------------------------------------");
                System.out.println("1. View Available Books\n2. View Checked Out Books\n3. Exit");
                System.out.println("--------------------------------------");
                System.out.println("Enter your selection (1, 2, or 3) and press <Enter>:");
                System.out.println("POWERED BY DYNIX | ?=Help (there is none.)");
                int option = sc.nextInt();
                // switch to select menu option
                switch (option) {
                    case 1:
                        showAvailableBooks(LogicHandler.libraryInventory);
                        break;
                    case 2:
                        showCheckedOutBooks(LogicHandler.libraryInventory);
                        break;
                    case 3:
                        exitProgram();
                        break;
                    default:
                        System.out.println("Invalid operation.");
                        break;
                } // runs exception handler method
            } catch (Exception e) {
                System.out.println("Invalid operation. Exiting program.");
                exitProgram();
            }
        } while (shouldContinue);
    }

    public void showAvailableBooks(Book[] libraryInventory) {
        boolean shouldContinue = true;
        // nested try/catch inside do/while loop to restart screen
        do {
            try {
                //Menu prompts
                System.out.println("List of Available Books");
                System.out.println("--------------------------------------");
                System.out.println("ID | Title | ISBN");
                System.out.println("--------------------------------------");
                for (Book book : libraryInventory) {
                    if (!book.isCheckedOut()) {
                        System.out.println("ID: " +book.getId() + "|" + " Title: " + book.getTitle() + "|" + " ISBN: " + book.getIsbn());
                    }
                }
                System.out.println("(C)heck out a book, or E(X)it to home screen:");
                char checkChar = sc.next().charAt(0);
                checkChar = Character.toUpperCase(checkChar);
                if (checkChar == 'C') {
                    book.checkOut(LogicHandler.libraryInventory);
                } else if (checkChar == 'X') {
                    showHomeScreen();
                }// runs exception handler method
            } catch (Exception e) {
                System.out.println("Invalid operation. Exiting program.");
                exitProgram();
            }
        } while (shouldContinue);
    }

    public void showCheckedOutBooks(Book[] libraryInventory) {
        boolean shouldContinue = true;
        // nested try/catch inside do/while loop to restart program
        do {
            try {
                //Menu prompts
                System.out.println("List of Checked Out Books");
                System.out.println("--------------------------------------");
                System.out.println("ID | Title | ISBN | Checked Out To");
                System.out.println("--------------------------------------");
                for (Book book : libraryInventory) {
                    if (book.isCheckedOut()) {
                        System.out.println("ID: " + book.getId() + "|" + " Title: " + book.getTitle() + "|" + " ISBN: " + book.getIsbn() + "|" + " Checked Out To: " + book.getCheckedOutTo());
                    }
                }
                System.out.println("(C)heck in a book, or E(X)it to home screen:");
                char checkChar = sc.next().charAt(0);
                checkChar = Character.toUpperCase(checkChar);
                if (checkChar == 'C') {
                    book.checkIn(LogicHandler.libraryInventory);
                } else if (checkChar == 'X') {
                    showHomeScreen();
                }
                // runs exception handler method and updates to false when necessary
            } catch (Exception e) {
                System.out.println("Invalid operation. Exiting program.");
                exitProgram();
            }
        } while (shouldContinue);
        }

    public void exitProgram() {
        System.out.println("Thank you for using the library!");
        System.exit(0);
    }
}