package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.integration.ExternalInventorySystem.DatabaseFailureException;
import se.kth.iv1350.pos.model.Receipt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrinterTest {
    private Receipt receipt;
    private HashMap<ItemDTO, Integer> soldItems;
    private ExternalInventorySystem externalInventorySystem;
    private LocalDateTime dateTime;
    private Printer printer;
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() throws DatabaseFailureException{
        double paidAmount = 200;
        double totalPrice = 160;
        dateTime = LocalDateTime.now();
        soldItems = new HashMap<ItemDTO, Integer>(); 
        externalInventorySystem = new ExternalInventorySystem();
        ItemDTO foundItem1 = externalInventorySystem.findItem(420101);
        ItemDTO foundItem2 = externalInventorySystem.findItem(520001);
        soldItems.put(foundItem1, 2);
        soldItems.put(foundItem2, 2);
        receipt = new Receipt(paidAmount, totalPrice, soldItems, dateTime);
        printer = new Printer();
        System.setOut(new PrintStream(outStream));
    }

    @After
    public void tearDown(){
        receipt = null;
        System.setOut(originalOut);
    }

    @Test
    public void testPrintReceipt(){
        String actualString = printer.printReceipt(receipt);
        String expectedString;
        expectedString = receipt.toString();
        assertEquals("printReceipt() did not print the correct receipt.",expectedString, actualString);
    }

    @Test
    public void testprintReceiptString(){
        printer.printReceipt(receipt);
        StringBuilder expectedOutputRegex = new StringBuilder();
        expectedOutputRegex.append("The Receipt:\n")
        .append("Time:[0-9]+-[0-9]+-[0-9]+T[0-9]+:[0-9]+:[0-9]+.[0-9]+\n")
        .append("--------------------------------------\n");
        
        double itemPrice;
        int quantity;
        double totalPriceOfSoldItems = 0;
        for (ItemDTO itemDTO : soldItems.keySet()) {
            quantity = soldItems.get(itemDTO);
            itemPrice = itemDTO.getPrice() * quantity;
            totalPriceOfSoldItems += itemPrice;
            
            expectedOutputRegex.append("\n");
            expectedOutputRegex.append(itemDTO.getName() + " \\* " + Integer.toString(quantity) + ":  " + 
            "SEK " + Double.toString(itemPrice) + "\n");
            expectedOutputRegex.append("\n"); 
        }

        expectedOutputRegex.append("--------------------------------------" + "\n");
        expectedOutputRegex.append("Total price: " + "SEK " + Double.toString(totalPriceOfSoldItems) + "\n");
        expectedOutputRegex.append("Total VAT 20%: " + "SEK " + Double.toString(totalPriceOfSoldItems * 0.2) + "\n");
        expectedOutputRegex.append("Total price after discount: " + "SEK " + Double.toString(receipt.getTotalPrice()) + "\n");
        expectedOutputRegex.append("Paid amount: " + "SEK " + Double.toString(receipt.getPaidAmount()) + "\n");
        expectedOutputRegex.append("Changes: " + "SEK " + Double.toString(receipt.getPaidAmount() - receipt.getTotalPrice()) + 
        "\n");
        expectedOutputRegex.append("\n"); 

        assertTrue("The String printed out in the printReceipt() method of Printer class showing the wrong result.",
        outStream.toString().matches(expectedOutputRegex.toString()));
    }
    
}
