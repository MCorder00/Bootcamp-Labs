package org.example;

import java.util.Scanner;

public class Screen {
    static Scanner sc = new Scanner(System.in);

    public void showHomeScreen() {

        boolean shouldContinue = true;
        // nested try/catch inside do/while loop to restart program
        do {
            try {
                //Menu prompts
                System.out.println("Welcome to the library!");
                System.out.println("--------------------------------------");
                System.out.println("1. View Available Books\n2. View Checked Out Books\n3. Exit");
                System.out.println("--------------------------------------");
                System.out.println("Please enter your desired function (1, 2, or 3):");
                int option = sc.nextInt();
                // switch to select menu option
                switch (option) {
                    case 1:
                        showAvailableBooks();
                        shouldContinue = checkContinue(true);
                        break;
                    case 2:
                        showCheckedOutBooks();
                        shouldContinue = checkContinue(true);
                        break;
                    case 3:
                        exitProgram();
                        shouldContinue = checkContinue(true);
                        break;
                    default:
                        System.out.println("Invalid operation.");
                        break;
                } // runs exception handler method and updates to false when necessary
            } catch (Exception e) {
                System.out.println("Invalid operation.");
                shouldContinue = checkContinue(true);
            }
        } while (shouldContinue); // continue loop if Y, exit text
        if (!shouldContinue) {
            System.out.println("Exiting.");
        }
    }

    public void showAvailableBooks() {
        System.out.println("List of Available Books");
        System.out.println("--------------------------------------");
        System.out.println("Please enter the ID of the book you want to check out:");
        int option = sc.nextInt();
        // switch to select book
        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid operation.");
                break;
        }

        }

    public void showCheckedOutBooks() {
        System.out.println("List of Checked Out Books");
        System.out.println("--------------------------------------");
        getBook();
        System.out.println("Please enter the ID of the book you want to return:");
        int option = sc.nextInt();
        // switch to select book
        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid operation.");
                break;
        }
    }

    public void exitProgram() {

    }

    public static boolean checkContinue (boolean cont){
        sc.nextLine();
        System.out.println("Would you like to start over? (Y)es/(N)o:");
        char checkChar = sc.next().charAt(0);
        checkChar = Character.toUpperCase(checkChar);
        if (checkChar != 'Y') {
            cont = false;
        }
        return cont;
    }

    }