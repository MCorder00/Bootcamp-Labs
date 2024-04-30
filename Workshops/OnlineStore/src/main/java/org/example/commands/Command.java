package org.example.commands;

public interface Command {
    void execute();
    // command interface is for the command pattern to be implemented
    // to separate the business logic from the user interface
}