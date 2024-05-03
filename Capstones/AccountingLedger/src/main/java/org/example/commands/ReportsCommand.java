package org.example.commands;

import org.example.business.Transaction;
import org.example.business.TransactionRepository;
import org.example.ui.UserInterface;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;

public class ReportsCommand implements Command {
    private final UserInterface userInterface;
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance();

    public ReportsCommand(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void execute() {
        boolean viewing = true;
        while (viewing) {
            displayReportsMenu();
            String option = userInterface.getInput("Enter option: ", false);
            switch (option) {
                case "1" -> runMonthToDateReport();
                case "2" -> runPreviousMonthReport();
                case "3" -> runYearToDateReport();
                case "4" -> runPreviousYearReport();
                case "5" -> searchByVendor();
                case "6" -> runCustomSearch();
                case "0" -> viewing = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayReportsMenu() {
        System.out.println("\n ---- R E P O R T S ----");
        System.out.println("1) M O N T H  T O  D A T E");
        System.out.println("2) P R E V I O U S  M O N T H");
        System.out.println("3) Y E A R  T O  D A T E");
        System.out.println("4) P R E V I O U S  Y E A R");
        System.out.println("5) S E A R C H  B Y  V E N D O R");
        System.out.println("6) C U S T O M  S E A R C H");
        System.out.println("0) H O M E  M E N U");
    }

    private void runMonthToDateReport() {
        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = LocalDate.now();
        List<Transaction> transactions = filterTransactionsByDateRange(startDate, endDate);
        displayTransactions(transactions);
    }

    private void runPreviousMonthReport() {
        LocalDate startDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        List<Transaction> transactions = filterTransactionsByDateRange(startDate, endDate);
        displayTransactions(transactions);
    }

    private void runYearToDateReport() {
        LocalDate startDate = LocalDate.now().withDayOfYear(1);
        LocalDate endDate = LocalDate.now();
        List<Transaction> transactions = filterTransactionsByDateRange(startDate, endDate);
        displayTransactions(transactions);
    }

    private void runPreviousYearReport() {
        LocalDate startDate = LocalDate.now().minusYears(1).withDayOfYear(1);
        LocalDate endDate = startDate.withDayOfYear(startDate.lengthOfYear());
        List<Transaction> transactions = filterTransactionsByDateRange(startDate, endDate);
        displayTransactions(transactions);
    }

    private void searchByVendor() {
        String vendor = userInterface.getInput("Enter vendor name: ", false);
        List<Transaction> transactions = TransactionRepository.getTransactions();
        List<Transaction> filteredTransactions = transactions.stream()
                .filter(t -> t.getVendor().equalsIgnoreCase(vendor))
                .toList();
        displayTransactions(filteredTransactions);
    }

    private void runCustomSearch() {
        LocalDate startDate = userInterface.getDateInput("Enter start date (YYYY-MM-DD) or leave empty: ", true);
        LocalDate endDate = userInterface.getDateInput("Enter end date (YYYY-MM-DD) or leave empty: ", true);
        String description = userInterface.getInput("Enter description or leave empty: ", true);
        String vendor = userInterface.getInput("Enter vendor or leave empty: ", true);
        String amountStr = userInterface.getInput("Enter amount or leave empty: ", true);
            // filters out null values and anything that doesn't match the criteria in the if statements
        List<Transaction> transactions = TransactionRepository.getTransactions();
        List<Transaction> filteredTransactions = transactions.stream()
                .filter(t -> startDate == null || !t.getDate().isBefore(startDate))
                .filter(t -> endDate == null || !t.getDate().isAfter(endDate))
                .filter(t -> description.isEmpty() || t.getDescription().toLowerCase().contains(description.toLowerCase()))
                .filter(t -> vendor.isEmpty() || t.getVendor().toLowerCase().contains(vendor.toLowerCase()))
                .filter(t -> amountStr.isEmpty() || String.valueOf(t.getAmount()).contains(amountStr))
                .toList();

        displayTransactions(filteredTransactions);
    }

    private List<Transaction> filterTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactions = TransactionRepository.getTransactions();
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))
                .toList();
    }

    private void displayTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions were found.");
        } else {
            System.out.println("Date|Time|Description|Vendor|Amount");
            for (Transaction transaction : transactions) {
                System.out.printf("%s|%s|%s|%s|%s\n",
                        transaction.getDate(),
                        transaction.getTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        CURRENCY_FORMAT.format(transaction.getAmount()));
            }
        }
    }
}