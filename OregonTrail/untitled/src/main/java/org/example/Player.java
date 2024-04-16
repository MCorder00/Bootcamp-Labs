package org.example;

public class Player {

    private int health;
    // 0 is empty 100 is full
    private int hunger;

    private String name;

    private Occupation occupation;
    // good to have a set number of jobs to pick from, enum


    public Player(int health, int hunger, String name, Occupation occupation) {
        this.health = health;
        this.hunger = hunger;
        this.name = name;
        this.occupation = occupation;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }
}
