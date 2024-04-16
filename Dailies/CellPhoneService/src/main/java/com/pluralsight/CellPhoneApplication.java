package com.pluralsight;

import java.util.Scanner;

public class CellPhoneApplication {
    // scanner
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // instantiate cell phone
        CellPhone cell = new CellPhone();
        System.out.println("What is your phone's serial number? (Numbers only e.g. 1234):");
        cell.setSerialNumber(sc.nextInt());
        sc.nextLine();
        System.out.println("What is your phone's model? (e.g. iPhone 15 Pro Max):");
        cell.setModel(sc.nextLine());
        System.out.println("What phone carrier does your phone use? (e.g. Verizon):");
        cell.setCarrier(sc.nextLine());
        System.out.println("What is your phone number? (xxx-xxx-xxxx):");
        cell.setPhoneNumber(sc.nextLine());
        System.out.println("What is the owner's name? (e.g. Sandra)");
        cell.setOwner(sc.nextLine());

        //Print:
        System.out.println("Your serial number is: " + cell.getSerialNumber());
        System.out.println("Your cell phone model is: " + cell.getModel());
        System.out.println("Your cell phone carrier is: " + cell.getCarrier());
        System.out.println("Your cell phone number is: " + cell.getPhoneNumber());
        System.out.println("Your cell phone owner's name is: " + cell.getOwner());
    }
}
