package org.example;

import org.example.business.TransactionRepository;
import org.example.ui.UserInterface;
import org.example.utils.Constants;

public class Main {
    public static void main(String[] args) {
        TransactionRepository.loadTransactions(Constants.TRANSACTIONS_FILE_PATH);
        UserInterface userInterface = new UserInterface();
        System.out.println("BOOTING FROM DLO");
        System.out.println("Accrual World Ltd welcomes you to:");
        System.out.println("""
                _/_/ L E D G E R
               _/_/ H E L P E R"""); // only shows on startup
        System.out.println("For all of your accounting needs.\n");
        userInterface.run();
    }
}