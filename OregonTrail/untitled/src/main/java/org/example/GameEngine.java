package org.example;

import java.util.Scanner;

public class GameEngine {

    private Player[] players = new Player[4];
    // all player things belong to player class, so this instantiates all data required for the player to interact
    public Player[] getPlayers() {
        return players;
    }
    // game engine is responsible for tracking game state and running the game loop
    public void setPlayers(Player[] players) {
        this.players = players;
    }
    // this function is responsible for setting up the team
    public void setupTeam(){
    int counter = 0;
    Scanner scanner = new Scanner(System.in);
    for(int i = 0; i < 4; i++){
        System.out.println("Please enter your character's name:");
        String name = scanner.nextLine();
        System.out.println("What is your occupation?:");
        System.out.println("Farmer, Hunter, Doctor, Pastor, Carpenter, Sheriff, Prospector");
        String occupationInput = scanner.nextLine();
        Occupation occupation = convertStringToOccupation(occupationInput);
        Player player = new Player(name, occupation);
        players[i] = players;
    }
    }
    public void displayTeam(){
        for(Player player : players);
        System.out.println("Name: " + player.)
    }
    private Occupation convertStringToOccupation(String occupationInput){
        switch(occupationInput.toLowerCase()){
            case "farmer":
                return Occupation.FARMER;
                break;
            case "hunter":
                return Occupation.HUNTER;
                break;
            case "doctor":
                return Occupation.DOCTOR;
                break;
            case "pastor":
                return Occupation.PASTOR;
                break;
            case "carpenter":
                return Occupation.CARPENTER;
            case "sheriff":
                return Occupation.SHERIFF;
            case "prospector":
                return Occupation.PROSPECTOR;
                break;
            default:
                System.out.println("Sorry, that's not a valid occupation");
                break;
        }
    }
}
