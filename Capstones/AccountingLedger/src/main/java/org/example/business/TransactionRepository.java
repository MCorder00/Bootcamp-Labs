package org.example.business;

import org.example.utils.Constants;
import org.example.utils.FileOperations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TransactionRepository {
    private static final List<Transaction> transactions = new ArrayList<>();

    public static List<Transaction> getTransactions() {
        return transactions;
    }

    public static void addTransaction(Transaction transaction) {
        transactions.add(0, transaction);
        FileOperations.saveTransactionToFile(Constants.TRANSACTIONS_FILE_PATH, transaction);
    }

    public static void loadTransactions(String filePath) {
        transactions.clear();
        transactions.addAll(FileOperations.loadTransactionsFromFile(filePath));
        transactions.sort(Comparator.comparing(Transaction::getDate)
                .thenComparing(Transaction::getTime)
                .reversed());
    }
}