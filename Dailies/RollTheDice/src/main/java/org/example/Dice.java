package org.example;

public class Dice {
    public static void main(String[] args) {

    }

    public int roll(){

        int randomNumber = (int)(Math.random() * 6) + 1;
        return randomNumber;
    }
}
