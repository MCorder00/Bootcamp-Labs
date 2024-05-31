package org.example;

import org.example.controller.FileHandler;
import org.example.controller.LogicController;
import org.example.view.CLIView;

public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        CLIView view = new CLIView();
        LogicController logicController = new LogicController(view, fileHandler);

        view.setLogicController(logicController);
        view.displayHomeScreen();
    }
}
