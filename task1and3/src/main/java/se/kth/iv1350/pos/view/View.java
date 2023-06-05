package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.integration.ItemDTO;

import java.io.IOException;
import java.util.Random;

import se.kth.iv1350.pos.controller.Controller;

/**
* This is the program's only View class, which is used for calling all the methods by cashier in controller.
* Since there is no actural activities from view, there is a hard-code to show the whole sale rocess.
*/ 
public class View {
    private Controller controller;
    private Random rand;
    

/**
* Create the View.
* @param controller The Controller should be entered.
* @param logFilename The filename of the log file.
*/
    public View(Controller controller, long seed) throws IOException
    {
        this.controller = controller;
        this.rand = new Random(seed);
    }

/**
* Hard-coded method calls to run the sale process.
* All the methods calling pass through controller.
* Everything that is returned by the controller as well as the receipt will be printed out.
*/
    public void runFakeScenario(){
        int allCustomerId[] = {1234, 5678, 9012};
        for (int customerId : allCustomerId) {

            System.out.println("\n");
            System.out.println("Customer id " + Integer.toString(customerId) + " walks into the store.");
    
            ItemDTO cola = new ItemDTO(778020, "Cola", "500ml", 0.2, 15);
            int colaId = cola.getId();
    
            ItemDTO chips = new ItemDTO(520001, "OLW chips", "250g", 0.2, 30);
            int chipsId = chips.getId();
    
            ItemDTO glass = new ItemDTO(339800, "Hagendas", "Strawberry", 0.2, 70);
            int glassId = glass.getId();
    
            ItemDTO nonExist = new ItemDTO(-1, "Heaven", "nowhere", 1, 1000);
            int nonExistId = nonExist.getId();
    
            ItemDTO failure = new ItemDTO(0, "Fail", "netConnection", 0, 0);
            int failureId = failure.getId();
    
            double paidAmount = 1000;
    
            System.out.println("The customer starts the sale.");
            controller.startSale();
    
            int colaQuantity = rand.nextInt(5);
            System.out.println("The customer buys " + Integer.toString(colaQuantity) + " Cola.");
            controller.enterItem(colaId, colaQuantity);
    
            int chipsQuantity = rand.nextInt(5);
            System.out.println("The customer buys " + Integer.toString(chipsQuantity) + " Chips.");
            controller.enterItem(chipsId, chipsQuantity);
    
            int glassQuantity = rand.nextInt(5);
            System.out.println("The customer buys " + Integer.toString(glassQuantity) + " glass.");
            controller.enterItem(glassId, glassQuantity);
            
            System.out.println("The customer buys 1 more Hagendas.");
            controller.enterItem(glassId, 1);
    
            System.out.println("The customer buys 1 invalid item.");
            controller.enterItem(nonExistId, 1);
    
            System.out.println("Try to simulate database failed to reach for item " + Integer.toString(failureId));
            controller.enterItem(failureId, 1);
            
            System.out.println("The customer asks for discounts.");
            controller.sendDiscountRequest(customerId);
    
            System.out.println("The customer asks to end the sale.");
            controller.endSale();
    
            System.out.println("The customer pays and get the receipt.");
            System.out.println("\n");
            controller.printReceipt(paidAmount);

        }
    }
}
