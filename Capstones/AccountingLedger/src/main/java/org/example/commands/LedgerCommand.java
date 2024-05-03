package org.example.commands;

import org.example.business.Transaction;
import org.example.business.TransactionRepository;
import org.example.ui.UserInterface;
import java.util.List;

public class LedgerCommand implements Command {
    private final UserInterface userInterface;

    public LedgerCommand(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void execute() {
        boolean viewing = true;
        while (viewing) {
            displayLedgerMenu();
            String option = userInterface.getInput("Enter option: ", false);
            switch (option.toUpperCase()) {
                case "A" -> displayAllTransactions();
                case "D" -> displayDeposits();
                case "P" -> displayPayments();
                case "R" -> new ReportsCommand(userInterface).execute();
                case "H" -> viewing = false;
                default -> System.out.println("That's not a valid option. Please try again.");
            }
        }
    }

    private void displayLedgerMenu() {
        System.out.println("\n---- L E D G E R ----");
        System.out.println("A) A L L  T R A N S A C T I O N S");
        System.out.println("D) D E P O S I T S");
        System.out.println("P) P A Y M E N T S");
        System.out.println("R) R E P O R T S");
        System.out.println("H) H O M E  M E N U");
    }

    private void displayAllTransactions() {
        displayTransactions(TransactionRepository.getTransactions());
    }

    private void displayDeposits() {
        List<Transaction> deposits = TransactionRepository.getTransactions().stream()
                .filter(t -> t.getAmount() > 0)
                .toList();
        displayTransactions(deposits);
    }

    private void displayPayments() {
        List<Transaction> payments = TransactionRepository.getTransactions().stream()
                .filter(t -> t.getAmount() < 0)
                .toList();
        displayTransactions(payments);
    }

    private void displayTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions were found.");
        } else {
            System.out.println("Date|Time|Description|Vendor|Amount");
            for (Transaction transaction : transactions) {
                System.out.printf("%s|%s|%s|%s|%.2f\n",
                        transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }
}