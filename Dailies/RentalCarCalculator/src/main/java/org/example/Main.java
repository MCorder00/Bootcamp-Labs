package org.example;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
      /*  System.out.println("Checking for surcharges - please enter customer age in years:");
        int age = sc.nextInt();
        System.out.println("When would you like to pick up your rental car? (MM/DD/YY):");
        String pickupDay = sc.nextLine();
        System.out.println("How long would you like to rent the car? Number of days:");
        int rentalLength = sc.nextInt();
        System.out.println("Want to breeze through tolls hassle-free? Opt for toll tags and leave spare change in the rearview mirror!: Y/N");
        char tollTagOpt = sc.next().charAt(0);
        System.out.println("GPS? Y/N");
        char gpsOpt = sc.next().charAt(0);
        System.out.println("Roadside Assistance? Y/N");
        char roadOpt = sc.next().charAt(0); */
        double rentalCost = getRentalCarCost();
        System.out.println("Your total is " + rentalCost);
    }

    public static double[] getNumbers(){
        double[] num = new double[2];
        System.out.println("Please enter the first number:");
        num[0] = sc.nextFloat();
        System.out.println("Please enter the second number:");
        num[1] = sc.nextFloat();
        return num;
    }

   /* public static double rentalCar(boolean tollTags, boolean gps, boolean roadAssist) {
        double rentalCar = 29.99;
        System.out.println("Want to breeze through tolls hassle-free? Opt for toll tags and leave spare change in the rearview mirror!: Y/N");
        if (sc.nextLine().equalsIgnoreCase("Y")){
            tollTags = true;
        }
        else {
            tollTags = false;
        }
        System.out.println("Would you like a GPS? Y/N");
        if (sc.nextLine().equalsIgnoreCase("Y")){
            gps = true;
        }
        else {
            gps = false;
        }
        System.out.println("Would you like roadside assistance? Y/N");
        if (sc.nextLine().equalsIgnoreCase("Y")){
            roadAssist = true;
        }
        else {
            roadAssist = false;
        }
        return rentalCar;
    } */

  /*  public static double getRentalCarCost() {
        double rentalCarCost = 29.99;
        double tollTags = false;
        boolean gps = false;
        boolean roadAssist = false;
        System.out.println("Want to breeze through tolls hassle-free? Opt for toll tags and leave spare change in the rearview mirror!: Y/N");
        if (sc.nextLine().equalsIgnoreCase("Y")){
            tollTags = true;
            rentalCarCost = rentalCarCost + 3.95;
        }
        System.out.println("Would you like a GPS? Y/N");
        if (sc.nextLine().equalsIgnoreCase("Y")){
            gps = true;
            rentalCarCost = rentalCarCost + 2.95;
        }
        System.out.println("Would you like roadside assistance? Y/N");
        if (sc.nextLine().equalsIgnoreCase("Y")){
            roadAssist = true;
            rentalCarCost = rentalCarCost + 3.95;
        }
        return rentalCarCost;
        */
  public static double getRentalCarCost() {
      double rentalCarCost = 29.99;
      double tollTags = 3.95;
      double gps = 2.95;
      double roadAssist = 3.95;
      System.out.println("Want to breeze through tolls hassle-free? Opt for toll tags and leave spare change in the rearview mirror!: Y/N");
      if (sc.nextLine().equalsIgnoreCase("Y")){
          rentalCarCost = rentalCarCost + tollTags;
      }
      System.out.println("Would you like a GPS? Y/N");
      if (sc.nextLine().equalsIgnoreCase("Y")){
          rentalCarCost = rentalCarCost + gps;
      }
      System.out.println("Would you like roadside assistance? Y/N");
      if (sc.nextLine().equalsIgnoreCase("Y")){
          rentalCarCost = rentalCarCost + roadAssist;
      }
      return rentalCarCost;
    }

  public static int getCustomerAge(){
      System.out.println("Please enter your age in years:");
      return sc.nextInt();
  }

  public static getPickupTime(){
      System.out.println("Please enter your pickup date (MM-DD-YY):");
      return sc.nextLine();
  }




}

//}
//  public static int calculateTotal(int age, int rentalDays) {
// calculator parameters - age (<25),4 bools, rental days (int), pickup date(Date)
// func for car and options
// need calendar
// function for menu
//  double rentalCarCost = rentalCar(true,true,true);
//  System.out.println(rentalCarCost);



/*
@author MDC
RENTAL CAR CALCULATOR PSEUDOCODE LOGIC
MODULE 4 - EXERCISE 1 - RENTAL CAR CALC
---------------------------------------
User Information and Car Options
Prompt user for:
    Pickup date (String)
    Number of days for rental
    Electronic toll tags? $3.95/d Y/N
    GPS?                  $2.95/d Y/N
    Roadside Assistance?  $3.95/d Y/N
    Renter's age (years)
Arithmetic Operations
Calculate
    Basic car rental cost $29.99/d
    Options cost
    Underage driver (<25) surcharge (30%)
    Total cost
----------------------------------------
IMPLEMENTATION LOGIC

func to display variables
separate class to contain data "if option selected, age, etc."
function to set options

Declare variables
Program Loop at beginning
Create methods with exception handling
    Calculation
    Rental Car
Ask user for age, collect
Reprint menu with updated info
Ask user for information, collect
Reprint menu with updated info
Ask user to set up car, collect options
Reprint menu with updated info
Ask user for pickup date, collect
Reprint menu with updated info
Ask user for number of days for rental, collect
Reprint menu with updated info
Ask user to verify above information is correct
If no, repeat from beginning
If yes, send data to calculator
Calculate
Print menu with all info and cost estimates
Ask if start over
If yes, go to start
If no, END
_____________
In calculator:
Check for underage modifier
If yes, add 30% to base rate
Check for price modifiers
If yes, sum, add to total
Check number of rental days
Multiply total by rental days for final cost
Print final cost
_______________
Counter for Pickup Date
+rentalDays to date unless date >30/31
use date class?
_______________
Menu Functionality
PRINT MENU SCREEN
STANDBY user input
WHEN user input THEN
SET MENU OPTION TO TRUE
REPRINT MENU SCREEN
REPEAT
________________
Primitives passed by value, local to method
Create external handler to set value
use getter/setter?
______________________
Create boxes to store user input (roughly strategy pattern)
Declare variables
Ask user for info
Collect information, send to box
needs input validation, error handling

 */