package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String[] quotes = new String[10];
        quotes[0] = "A";
        quotes[1] = "B";
        quotes[2] = "C";
        quotes[3] = "D";
        quotes[4] = "E";
        quotes[5] = "F";
        quotes[6] = "G";
        quotes[7] = "H";
        quotes[8] = "I";
        quotes[9] = "J";
        try {
            System.out.println("Please choose a number between one and ten.");
        int userInput = Integer.parseInt(scanner.nextLine());
        if (userInput > 0 && userInput < 11) {
            System.out.println(quotes[userInput - 1]);
        }
        }catch(Exception e) {
            System.out.println("Please enter a number between 1 and 10.");
            }
        }
        }
