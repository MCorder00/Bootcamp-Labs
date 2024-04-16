package org.example;

public class ForLoop {
    public static void main(String[] args) throws InterruptedException {
        int i = 10;
        for (i = 10; i > 0; i--) {
            System.out.println(i);
            Thread.sleep(1000);
            if(i == 1){
                System.out.println("Launch!");
            }

        }

    }

}
