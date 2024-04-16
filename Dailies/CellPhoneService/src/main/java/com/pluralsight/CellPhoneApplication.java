package com.pluralsight;

import java.util.Scanner;

public class CellPhoneApplication {
    // scanner
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // instantiate cell phone

        CellPhone cell = new CellPhone();

        CellPhone cell2 = new CellPhone(11111, "iPhone 12 Pro", "ATT", "111-222-3333", "Deborah");
        // set phone information
        setPhoneInfo(cell);

        // display phone information
        display(cell);

        display(cell2);
        // dial test
        cell.dial(cell2.getPhoneNumber());

        cell2.dial("111-111-1111");
    }

    public static void display(CellPhone cell) {
        System.out.println("Displaying phone information for " + cell.getPhoneNumber() + " :");
        System.out.println("Serial number: " + cell.getSerialNumber());
        System.out.println("Cell phone model: " + cell.getModel());
        System.out.println("Cell phone carrier: " + cell.getCarrier());
        System.out.println("Cell phone number: " + cell.getPhoneNumber());
        System.out.println("Cell phone owner's name: " + cell.getOwner());
    }

    public static void setPhoneInfo(CellPhone cell)  {
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
    }

}
