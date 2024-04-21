package org.example;


public class LogicHandler {

    static Book[] libraryInventory = new Book[21];
    static Book book = new Book();
    static Screen screen = new Screen();
    static LogicHandler logic = new LogicHandler();

    public static void main(String[] args){
        startLibraryInventory();
        screen.showHomeScreen();
    }
    public static void startLibraryInventory(){
        libraryInventory[0] = new Book(0,"ISBN","Title", false, "");
        libraryInventory[1] = new Book(1,"978-0-14-243723-9","A Roadside Picnic", false, "");
        libraryInventory[2] = new Book(2,"978-0-345-38996-7","The Dispossessed", false, "");
        libraryInventory[3] = new Book(3,"978-0-553-29388-3","1984", false, "");
        libraryInventory[4] = new Book(4,"978-0-671-69703-2","Cat's Cradle", false, "");
        libraryInventory[5] = new Book(5,"978-0-679-73220-5","The Forever War", false, "");
        libraryInventory[6] = new Book(6,"978-0-316-24662-0","Babel-17", false, "");
        libraryInventory[7] = new Book(7,"978-0-441-56959-2","Ender's Game", false, "");
        libraryInventory[8] = new Book(8,"978-0-316-12908-4","The Moon Is A Harsh Mistress", false, "");
        libraryInventory[9] = new Book(9,"978-0-061-66113-1","Animal Farm", false, "");
        libraryInventory[10] = new Book(10,"978-0-765-31831-6","The City and The City", false, "");
        libraryInventory[11] = new Book(11,"978-0-553-28639-4","Fahrenheit 451", false, "");
        libraryInventory[12] = new Book(12,"978-0-441-79034-5","Consider Phlebas", false, "");
        libraryInventory[13] = new Book(13,"978-0-804-19677-6","Brave New World", false, "");
        libraryInventory[14] = new Book(14,"978-0-06-105488-4","Do Androids Dream of Electric Sheep?", false, "");
        libraryInventory[15] = new Book(15,"978-1-406-51210-9","Foundation", false, "");
        libraryInventory[16] = new Book(16,"978-0-345-39180-9","Catch-22", false, "");
        libraryInventory[17] = new Book(17,"978-0-553-27667-1","Slaughterhouse-Five", false, "");
        libraryInventory[18] = new Book(18,"978-0-060-93001-5","Starship Troopers", false, "");
        libraryInventory[19] = new Book(19,"978-0-553-34313-6","Neuromancer", false, "");
        libraryInventory[20] = new Book(20,"978-0-439-02352-8","Dune", false, "");
    }
    public void getBook(){
        for(int i = 1; i < libraryInventory.length; i++) {
            System.out.println(libraryInventory[i]);
        }
    }


}
