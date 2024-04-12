package org.example;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    // import scanner and formatter in class
    public static Scanner sc = new Scanner(System.in);
    public static NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

    public static void main(String[] args) {
        // declarations in main for handlers
        char startOver = 'Y';
        boolean shouldContinue = true;

        // nested try/catch inside do/while loop to restart program
        do {
            try {
                //Menu prompts
                System.out.println("Welcome to the Financial Calculator.");
                System.out.println("--------------------------------------");
                System.out.println("1. Mortgage - Monthly Payment\n2. CD - Future Value\n3. Annuity - Present Value");
                System.out.println("--------------------------------------");
                System.out.println("Please enter your desired function:");
                int desiredCalc = sc.nextInt();
                // switch to select calculator
                switch (desiredCalc) {
                    case 1:
                        getMonthlyPayment();
                        break;
                    case 2:
                        getCompoundInterest();
                        break;
                    case 3:
                        getPresentValue();
                        break;
                    default:
                        System.out.println("Invalid operation.");
                        break;
                } // runs exception handler method and updates to false when necessary
            } catch (Exception e) {
                shouldContinue = exHandler(true);
            }
        } while (shouldContinue); // continue loop if Y
        if (!shouldContinue) {
            System.out.println("Exiting the Financial Calculator.");
        }
    }

    public static void getMonthlyPayment(){
        // value assignment
        System.out.println("Please enter your principal (total loan amount):");
        double P = sc.nextDouble();
        System.out.println("Please enter your annual interest rate in percentage (not in decimal i.e. 6.12 not 0.0612):");
        double ai = sc.nextDouble();
        System.out.println("Please enter the term of your loan in years:");
        double ll = sc.nextDouble();
        System.out.println("Calculating monthly payment...");
        // operations
        double r = ai / 12 / 100;
        double n = ll * 12;
        double M = P * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r,n) - 1);
        double ip = (M * n) - P;
        String formattedAmount = formatter.format(M);
        System.out.println("Your monthly payment is " + formattedAmount);
        String formattedAmount2 = formatter.format(ip);
        System.out.println("The total interest paid across the term of your loan is " + formattedAmount2);
    }

    public static void getCompoundInterest(){
        // value assignment
        System.out.println("Please enter your initial deposit amount:");
        double P = sc.nextDouble();
        System.out.println("Please enter your annual interest rate in percentage (not in decimal i.e. 6.12 not 0.0612):");
        double r = sc.nextDouble();
        System.out.println("Please enter the term of your deposit in years:");
        double t = sc.nextDouble();
        System.out.println("""
                Daily Compounding - 365
                Monthly Compounding - 12
                Quarterly Compounding - 4
                Annual Compounding - 1""");
        System.out.println("Please enter the compounding frequency:");
        double n = sc.nextDouble();
        System.out.println("Calculating future value and earnings...");
        // operations - convert r to decimal
        r = r / 100;
        double FV = P * Math.pow((1 + r / n), (n * t));
        // format value of FV to US currency
        String formattedAmount = formatter.format(FV);
        System.out.println("Your future value is: " + formattedAmount);
        String formattedAmount2 = formatter.format((FV - P));
        System.out.println("Your total interest earned across your term is: " + formattedAmount2);
    }

    public static void getPresentValue(){
        // value assignment
        System.out.println("Please enter your monthly payout amount:");
        double PMT = sc.nextDouble();
        System.out.println("Please enter your annual interest rate in percentage (not in decimal i.e. 6.12 not 0.0612):");
        double r = sc.nextDouble();
        System.out.println("Please enter the number of years to payout:");
        double n = sc.nextDouble();
        System.out.println("Calculating present value of annuity...");
        // operations - convert r to monthly percentage then decimal
        r = r / 12 / 100;
        n = n * 12;
        double PV = PMT * (1 - Math.pow((1 + r),-n)) / r;
        String formattedAmount = formatter.format(PV);
        System.out.println("Your present value is: " + formattedAmount);
        }

     public static boolean exHandler(boolean cont){
         sc.nextLine();
         System.out.println("Invalid operation.");
         System.out.println("Would you like to start over?");
         char checkChar = sc.next().charAt(0);
         checkChar = Character.toUpperCase(checkChar);
         if (checkChar != 'Y') {
             cont = false;
         } return cont;
     }

    }