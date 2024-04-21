package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] testScores = new int [10];
        testScores[0] = 94;
        testScores[1] = 78;
        testScores[2] = 88;
        testScores[3] = 81;
        testScores[4] = 92;
        testScores[5] = 73;
        testScores[6] = 44;
        testScores[7] = 98;
        testScores[8] = 86;
        testScores[9] = 90;
        int sum = 0;
        int testScoresLength = testScores.length;
        for (int score : testScores) {
            sum += score;
        } double average = (double)sum / (double)testScores.length;
        System.out.println("Average of scores is: " + average);
        int maximum = Arrays.stream(testScores).max().getAsInt();
        System.out.println("Highest score is: " +maximum);
        int minimum = Arrays.stream(testScores).min().getAsInt();
        System.out.println("Lowest score is: " +minimum);
        System.out.println("Median score is: " +getMedian(testScores, testScoresLength));
    }
    public static double getMedian(int testScores[], int testScoresLength){
        Arrays.sort(testScores);
        double median;
        if (testScoresLength % 2 == 0){
            median = ((double) testScores[(testScoresLength - 1) / 2] + (double) testScores[testScoresLength / 2]) / 2.0;
        }else
            median = (double) testScores[testScoresLength / 2];
        return median;
    }

}


/* scan array slot by slot
*  take first number into memory
* compare each subsequent number
*  if greater than num in memory then replace with that number
* repeat until end of array
* */