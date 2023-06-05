package se.kth.iv1350.pos.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.RegisterCreator;
import se.kth.iv1350.pos.integration.ExternalInventorySystem.DatabaseFailureException;
import se.kth.iv1350.pos.model.SaleInformation;
import se.kth.iv1350.pos.model.SaleInformation.ItemNotFoundException;
import se.kth.iv1350.pos.integration.Printer;


public class ControllerTest {
    private RegisterCreator registerCreator;
    private Printer printer;
    private Controller controller;
    private long SEED = 42;

    @Before
    public void setUp() throws IOException{
        registerCreator = new RegisterCreator();
        printer = new Printer();
        controller = new Controller(registerCreator, printer, "/tmp/test.log", "/tmp/revenue.log", SEED);

    }

    @After
    public void tearDown(){
        controller = null;
    }

    @Test
    public void testStartSale (){
        controller.startSale();
        SaleInformation saleInformation = controller.getSaleInformation();
        assertNotNull("startSale() did not create a new non-null saleInformation",saleInformation);
    }

    @Test
    public void testEnterItemNonExistent(){
        ItemDTO nonExistentItem = new ItemDTO(-1, "heaven", "hell", 1, -1);
        int quantity = 3;
        int beforeSoldQuantity;
        int afterSoldQuantity;
        SaleInformation saleInformation;
        HashMap<ItemDTO, Integer> soldItems;
    
        controller.startSale();

        saleInformation = controller.getSaleInformation();
        soldItems = saleInformation.getSoldItems();
        beforeSoldQuantity = soldItems.getOrDefault(nonExistentItem, 0);
        controller.enterItem(nonExistentItem.getId(), quantity);

        saleInformation = controller.getSaleInformation();
        soldItems = saleInformation.getSoldItems();
        afterSoldQuantity = soldItems.getOrDefault(nonExistentItem, 0);

        assertEquals("enterItem() returned a wrong sale information when sold item is invalid.", beforeSoldQuantity, afterSoldQuantity);
    }

    @Test
    public void testEnterItemExist(){
        ItemDTO chips = new ItemDTO(520001, "OLW chips", "250g", 0.2, 30);
        int quantity = 3;
        int beforeSoldQuantity;
        int afterSoldQuantity;
        SaleInformation saleInformation;
        HashMap<ItemDTO, Integer> soldItems;
    
        controller.startSale();

        saleInformation = controller.getSaleInformation();
        soldItems = saleInformation.getSoldItems();
        beforeSoldQuantity = soldItems.getOrDefault(chips, 0);
        controller.enterItem(chips.getId(), quantity);
        

        saleInformation = controller.getSaleInformation();
        soldItems = saleInformation.getSoldItems();
        afterSoldQuantity = soldItems.getOrDefault(chips, 0);

        assertEquals("enterItem() returned wrong quantity of the sold item in sale informaton for valid items.", 
                    beforeSoldQuantity+quantity, afterSoldQuantity);
    }

    @Test
    public void testSendDiscountRequest() throws ItemNotFoundException, DatabaseFailureException{
        int customerId = 1234;

        controller.startSale();
        controller.enterItem(520001, 1);

        double beforePrice = controller.getSaleInformation().getTotalPrice();
        controller.sendDiscountRequest(customerId);
        double afterPrice = controller.getSaleInformation().getTotalPrice();

        double expectedAfterPrice = beforePrice * 0.8;
        assertEquals("sendDiscountRequest returned wrong price after discount.", expectedAfterPrice, afterPrice, 1e-9);
    }

    @Test
    public void testEndSale() throws ItemNotFoundException, DatabaseFailureException{
        ItemDTO chips = new ItemDTO(520001, "OLW chips", "250g", 0.2, 30);
        int quantity = 3;

        controller.startSale();
        controller.enterItem(chips.getId(), quantity);
        
        int beforeInventory = registerCreator.getItemRegistry().getInventory().get(chips);

        controller.endSale();

        int afterInventory = registerCreator.getItemRegistry().getInventory().get(chips);

        assertEquals("endSale() went genom wrong sale process with wrong inventory information.", beforeInventory - quantity, afterInventory);
    }
}
