package se.kth.iv1350.pos.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.ArrayList;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.LogHandler;
import se.kth.iv1350.pos.integration.DiscountRegistry;
import se.kth.iv1350.pos.integration.ErrorMessageHandler;
import se.kth.iv1350.pos.integration.ExternalInventorySystem;
import se.kth.iv1350.pos.integration.AccountingSystem;
import se.kth.iv1350.pos.integration.CustomerRegistry;
import se.kth.iv1350.pos.integration.RegisterCreator;
import se.kth.iv1350.pos.integration.TotalRevenueFileOutput;
import se.kth.iv1350.pos.integration.ExternalInventorySystem.DatabaseFailureException;
import se.kth.iv1350.pos.model.SaleInformation;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.view.TotalRevenueView;

public class Controller {
    private SaleInformation saleInformation;
    private ExternalInventorySystem externalInventorySystem;
    private DiscountRegistry discountRegistry;
    private AccountingSystem accountingSystem;
    private Printer printer;
    private ErrorMessageHandler errorMessageHandler;
    private LogHandler logHandler;
    private TotalRevenueView totalRevenueView;
    private TotalRevenueFileOutput totalRevenueFileOutput;

/**
* This is the only Controller class in the program, calling all the other methods in model and integration.
* @param registerCreator This object contains the methods to create new externalInventorySystem, discountRegistry, customerRegistry in controller.
* @param printer The object Printer which contains the method to print receipt out will be used.
 * @throws IOException
*/
    public Controller (RegisterCreator registerCreator, Printer printer, String logFilename, String revenueLogFile, long seed){
        this.externalInventorySystem = registerCreator.getItemRegistry();
        this.discountRegistry = registerCreator.getDiscountRegistry();
        this.accountingSystem = registerCreator.getAccountingSystem();
        this.printer = printer;
        this.errorMessageHandler = new ErrorMessageHandler();
        try {
            this.logHandler = new LogHandler(logFilename);
        } catch (IOException e) {
            this.errorMessageHandler.showErrorMsg("Failed to create LogHandler for file " + logFilename);
            this.logHandler.log("Failed to create LogHandlers for file " + logFilename);
        }
        this.totalRevenueView = new TotalRevenueView(seed);
        try {
            this.totalRevenueFileOutput = new TotalRevenueFileOutput(revenueLogFile, seed);
        } catch (IOException e) {
            this.errorMessageHandler.showErrorMsg("Failed to create TotalRevenueFileOutput for file " + revenueLogFile);
            this.logHandler.log("Failed to create TotalRevenueFileOutput for file " + revenueLogFile);
        }
    }

/**
* Get the SaleInformation in controller (using for test).
*
* @return saleInformation The object SaleInformation will be returned.
*/
    public SaleInformation getSaleInformation(){
        return saleInformation;
    }

/**s
* Start a new sale with a new initialized SaleInformation.
* So that sold items can be entered and added during the following sale process.
 * @throws IOException
*/
    public void startSale(){
        saleInformation = new SaleInformation();
        saleInformation.addSaleObserver(totalRevenueView);
        saleInformation.addSaleObserver(totalRevenueFileOutput);
    }

/**
* Enter the sold items to the saleInformation.
* @param identifier The int which is the soldItem ID number will be scanned in.
* @param quantity The int number which will be entered by view.
* @return saleInformation The new saleInformation uppdated with new entered items.
* @throws ItemNotFoundException when the entered item identifier can not be found in inventory.
*/
    public SaleInformation enterItem (int identifier, int quantity)
        {
            try {
                saleInformation.addItem(identifier, quantity, externalInventorySystem);
                saleInformation.uppdateSaleInformation();
                } 
            catch(DatabaseFailureException e) { 
                this.errorMessageHandler.showErrorMsg("Fail to reach the database for item: " + Integer.toString(identifier));
                this.logHandler.log("Fail to reach the databasefor item: " + Integer.toString(identifier));
            }
            catch(SaleInformation.ItemNotFoundException e){
                this.errorMessageHandler.showErrorMsg("The item " + Integer.toString(identifier) + " does not exist");
                this.logHandler.log("The item " + Integer.toString(identifier) + " does not exist.");
            }
        
        return saleInformation;
    }

/**
* Apply discount request from view.
* @param customerId The int which will be entered by view.
* @return priceAfterDiscount The double of price after that requested discounts is applied.
*/ 
    public double sendDiscountRequest (int customerId){
        double priceAfterDiscount = 0;
        priceAfterDiscount = saleInformation.includeDiscount(customerId, discountRegistry);
        return priceAfterDiscount;
    }

/**
* End the sale process with inventory system and accounting system uppdated.
* @return SaleInformation The uppdated final saleInformation object will be returned.
*/ 
    public SaleInformation endSale(){
        HashMap<ItemDTO,Integer> soldItems = saleInformation.getSoldItems();
        externalInventorySystem.uppdateInventory(soldItems);
        saleInformation.saleEnd();
        return saleInformation;
    }

/**
* Print the receipt out of the sale.
* @param paidAmount The double which will be entered by view.
*/ 
    public void printReceipt(double paidAmount){
        HashMap<ItemDTO,Integer> soldItems;
        Receipt receipt;
        double totalPrice = saleInformation.getTotalPrice();
        soldItems = saleInformation.getSoldItems();
        LocalDateTime dateTime = LocalDateTime.now();
        receipt = new Receipt(paidAmount, totalPrice, soldItems, dateTime);
        printer.printReceipt(receipt);
    }

}

