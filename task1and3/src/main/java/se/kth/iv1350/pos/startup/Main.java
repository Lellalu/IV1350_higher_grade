package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.integration.RegisterCreator;
import se.kth.iv1350.pos.integration.Printer;

import java.io.IOException;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.view.View;

/**
* This is the program's only startUp Main, in order to do all the start up initializations. 
* Including creating the Controller controller, RegisterCreator registerCreator, Printer printer and the View view.
*/

public class Main {

    public static void main(String[] args) throws IOException {
        RegisterCreator registerCreator = new RegisterCreator();
        Printer printer = new Printer();
        Controller controller = new Controller(registerCreator, printer, args[0], args[1], System.currentTimeMillis());
        View view = new View(controller, System.currentTimeMillis());
        view.runFakeScenario();
    }
}
