package org.example.utils;

import org.example.business.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FileOperations {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static List<Transaction> loadTransactionsFromFile(String filePath) {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                try { // this try/catch is to import files and read them correctly
                    LocalDate date = LocalDate.parse(fields[0], DATE_FORMATTER);
                    LocalTime time = LocalTime.parse(fields[1], TIME_FORMATTER);
                    String description = fields[2];
                    String vendor = fields[3];
                    double amount = Double.parseDouble(fields[4]);

                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);
                } catch (DateTimeParseException | NumberFormatException e) {
                    System.out.println("Could not parse transaction." + line); // skipped line
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while loading data from the file.");
        }
        return transactions;
    }

    public static void saveTransactionToFile(String filePath, Transaction transaction) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = String.format("%s|%s|%s|%s|%.2f\n",
                    transaction.getDate().format(DATE_FORMATTER), // this is to save the file in the correct format
                    transaction.getTime().format(TIME_FORMATTER),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
            bw.write(line);
        } catch (IOException e) {
            System.out.println("Something went wrong while saving data to the file.");
        }
    }
}