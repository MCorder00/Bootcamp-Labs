package org.example.commands;

import org.example.business.Transaction;
import org.example.business.TransactionRepository;
import org.example.ui.UserInterface;

import java.time.LocalDate;
import java.time.LocalTime;

public class RecordTransactionCommand implements Command {
    private final UserInterface userInterface;
    private final boolean isDeposit;

    public RecordTransactionCommand(UserInterface userInterface, boolean isDeposit) {
        this.userInterface = userInterface;
        this.isDeposit = isDeposit;
    }

    @Override
    public void execute() {
        LocalDate date = userInterface.getDateInput("Enter date (YYYY-MM-DD): ", false);
        LocalTime time = userInterface.getTimeInput("Enter time (HH:MM:SS): ", false);
        String description = userInterface.getInput("Enter description: ", false);
        String vendor = userInterface.getInput("Enter vendor name: ", false);
        double amount = userInterface.getAmountInput("Enter transaction amount: ", false);

        if (!isDeposit) {
            amount = -amount;
        }

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        TransactionRepository.addTransaction(transaction);
        System.out.println("Transaction recorded successfully.");
    }
}