package org.example.ui;

import org.example.commands.*;
import org.example.utils.Constants;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            displayHomeMenu();
            String option = getInput("Enter option: ", false).toUpperCase();
            switch (option) {
                case "D" -> new RecordTransactionCommand(this, true).execute();
                case "P" -> new RecordTransactionCommand(this, false).execute();
                case "L" -> new LedgerCommand(this).execute();
                case "X" -> {
                    running = false;
                    System.out.println("Exiting.");
                }
                default -> System.out.println("That's not a valid option. Please try again.");
            }
        }
    }

    private void displayHomeMenu() {
        System.out.println("\n------H O M E M E N U------");
        System.out.println("Please select an option:");
        System.out.println("D) D E P O S I T (Credit)");
        System.out.println("P) P A Y M E N T (Debit)");
        System.out.println("L) V I E W  L E D G E R");
        System.out.println("X) E X I T  P R O G R A M");
    }

    public LocalDate getDateInput(String prompt, boolean allowEmpty) {
        while (true) { // allowEmpty isn't used but it's there for consistency
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                return LocalDate.parse(input, DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));
            } catch (DateTimeParseException e) {
                System.out.println("That's not a valid date format. Please enter a date in the format YYYY-MM-DD.");
            }
        }
    }

    public LocalTime getTimeInput(String prompt, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalTime.parse(input, DateTimeFormatter.ofPattern(Constants.TIME_FORMAT));
            } catch (DateTimeParseException e) {
                System.out.println("That's not a valid time format. Please enter a time in the format HH:MM:SS.");
            }
        }
    }

    public double getAmountInput(String prompt, boolean allowEmpty) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("The amount cannot be empty.");
                }
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid number. Please enter a valid number (1234.56).");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getInput(String prompt, boolean allowEmpty) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (allowEmpty || !input.isEmpty()) {
            return input;
        }
        System.out.println("The input cannot be empty. Please enter a value.");
        return getInput(prompt, allowEmpty);
    }
    }
