package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // declare variables
        double firstNumber;
        double secondNumber;
        boolean isValid = true;
        // do loop to repeat program
        do {
            try { // try block to restart when given invalid input

                // welcome message
                System.out.println("Welcome to the Fanglebrok Calculomatic Calculator 9000.\n Your hovercraft is full of eels.\n");
                System.out.println("-------------------------------------------------------");
                System.out.println("Please enter the first number you want to calculate:");

                // create scanner and ask for user to input two operands
                Scanner input = new Scanner(System.in);
                // surround user input with try blocks
                firstNumber = input.nextDouble();
                System.out.println("Please enter the second number you want to calculate:");
                secondNumber = input.nextDouble();

                // apparently nextDouble keeps a newline spacer in the input buffer so get rid of that
                input.nextLine();

                //confirm and ask for operator
                System.out.printf("You entered %f and %f \n", firstNumber, secondNumber);
                System.out.println("Possible operations: \n (A)dd \n (S)ubtract \n (M)ultiply \n (D)ivide");
                System.out.println("Please select the letter corresponding to the operation you want to perform:");

                // begin calculation with switch
                char whatCalc = input.next().charAt(0);

                //convert lowercase letter to uppercase
                whatCalc = Character.toUpperCase(whatCalc);

                // begin switch
                switch (whatCalc) {
                    case 'A':
                        var add1 = firstNumber + secondNumber;
                        System.out.println(firstNumber + " + " + secondNumber + " = " + add1);
                        break;
                    case 'S':
                        var sub1 = firstNumber - secondNumber;
                        System.out.println(firstNumber + " - " + secondNumber + " = " + sub1);
                        break;
                    case 'M':
                        var mult1 = firstNumber * secondNumber;
                        System.out.println(firstNumber + " * " + secondNumber + " = " + mult1);
                        break;
                    case 'D':
                        var div1 = firstNumber / secondNumber;
                        System.out.println(firstNumber + " / " + secondNumber + " = " + div1);
                        break;
                    default:
                        System.out.println("Invalid operation.");
                        break;
                }

                // restart logic
                System.out.println("(Y)es\n(N)o\nWould you like to start over?");
                char restart = input.next().charAt(0);
                restart = Character.toUpperCase(restart);
                if (restart == 'N') {
                    isValid = false;
                } // catch block sends to beginning
            } catch (Exception e) {
                System.out.println("Invalid argument. Please try again.");
            }
        } while (isValid);
    }
}

/* Pseudocode Design Logic
@author MDC
__________________________________
Tell computer what variables exist
    Variables for:
    Program Running?
    First Number (User Input)
    Second Number (User Input)
    Operations - Add
    Operation - Subtract
    Operation - Divide
    Operation - Multiply
    Operation - Remainder
Import scanner
Do loop to restart program
Use scanner input for First/Second number
Print - welcome message.
Print - ask user to select a number.
Try/catch block for invalid input and arithmetic errors (divide by zero).
Scanner - assign value to first number.
Print - ask user to select second number.
Scanner - assign value to second number.
Print - ask user to select an operation.
Scanner - select appropriate operation and:
Run operation using first/second number as operands.
Print result.
Ask user if they want to start over and close While.
    If yes then:
    Loop back to start
    If no then:
    END
 */

/* Version 2 Pseudocode Logic
    -Introduce more object-oriented programming
Program Loop at start using methods
    Call methods and assign variable names
    (firstNumber, secondNumber, userOperator)
Create Methods
    Operand
    Operator
    Calculation
Exception handling within the methods
Use *** to write on multiple lines at once
Use .equalsIgnoreCase, get, position
Use printf (%.2f)
For division: if secondNumber = 0 then "Cannot divide by zero."
 */