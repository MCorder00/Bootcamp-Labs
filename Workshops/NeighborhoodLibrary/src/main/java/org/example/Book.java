package org.example;

import java.util.Scanner;

public class Book {
    // declare variables
    static Scanner sc = new Scanner(System.in);
    static Screen screen = new Screen();
    static LogicHandler logic = new LogicHandler();
    static Book book = new Book();
    private int id;
    private String isbn;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;
    // declare library inventory
    static Book[] libraryInventory = new Book[21];
    // book constructors, setters, getters
    public Book(int id, String isbn, String title, boolean isCheckedOut, String checkedOutTo) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.isCheckedOut = isCheckedOut;
        this.checkedOutTo = checkedOutTo;
    }

    public Book() {
        this.id = 0;
        this.isbn = "";
        this.title = "";
        this.isCheckedOut = false;
        this.checkedOutTo = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;
    }
    public void checkOut(Book[] libraryInventory){
        System.out.println("Please enter your name: ");
        String name = sc.nextLine();
        // loop to restart checkout screen
        while (true) {
            System.out.println("Please enter the ID of the book you want to check out or enter 0 to exit:");
            int option = sc.nextInt();
            sc.nextLine();
            if (option == 0) {break;
            }
            if (option >= 1 && option <= libraryInventory.length && libraryInventory[option].isCheckedOut()){
                System.out.println("That book is already checked out.");
            } else if (option >= 1 && option <= libraryInventory.length && !libraryInventory[option].isCheckedOut()) {
                Book book = libraryInventory[option];
                book.setCheckedOut(true);
                book.setCheckedOutTo(name);
                System.out.println("""
                           ,   ,
                          /////|
                         ///// |
                        |~~~|  |
                        |===|  |
                        |j  |  |
                        | g |  |
                        |  s| /
                        |===|/
                        '---'""");
                System.out.println("Thanks, " + name + " you have checked out book: " + book.getTitle() + " ID: " + book.getId());
            } else {
                System.out.println("Invalid operation.");
            }
        }
    }
    public void checkIn(Book[] libraryInventory) {
        // loop to restart checkin screen
        boolean shouldContinue = true;
        do {
            try {
                System.out.println("Please enter the ID of the book you want to check in or enter 0 to exit:");
                int option = sc.nextInt();
                if (option == 0) {
                    return;
                }
                sc.nextLine();
                // if/else to select book
                if (option >= 1 && option <= libraryInventory.length && !libraryInventory[option].isCheckedOut()) {
                    System.out.println("Cannot return a book that is not checked out.");
                } else if (option >= 1 && option <= libraryInventory.length && libraryInventory[option].isCheckedOut()) {
                    Book book = libraryInventory[option];
                    book.setCheckedOut(false);
                    book.setCheckedOutTo("");
                    System.out.println("""
                             _   _                 _                     \s
                            | | | |               | |                    \s
                            | |_| |__   __ _ _ __ | | ___   _  ___  _   _\s
                            | __| '_ \\ / _` | '_ \\| |/ / | | |/ _ \\| | | |
                            | |_| | | | (_| | | | |   <| |_| | (_) | |_| |
                             \\__|_| |_|\\__,_|_| |_|_|\\_\\\\__, |\\___/ \\__,_|
                                                         __/ |           \s
                                                        |___/            \s""");
                    System.out.println("Thank you for returning the book!");
                } else {
                    System.out.println("Invalid operation.");
                }
            } catch (Exception e) {
                System.out.println("Invalid operation. Exiting program.");
                screen.exitProgram();
            }
        } while (shouldContinue);
    }

    @Override // override to string method for books (unused)
    public String toString() {
        return "ID: " + id +
                ", ISBN: '" + isbn + '\'' +
                ", Title: '" + title + '\'' +
                ", Loaned: " + isCheckedOut +
                ", Checked out to: '" + checkedOutTo + '\'';
    }
}
