package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ExternalInventorySystem.DatabaseFailureException;
import se.kth.iv1350.pos.integration.DiscountDTO;
import se.kth.iv1350.pos.integration.CustomerDTO;
import se.kth.iv1350.pos.integration.DiscountRegistry;
import se.kth.iv1350.pos.integration.ExternalInventorySystem;
import se.kth.iv1350.pos.integration.CustomerRegistry;
import se.kth.iv1350.pos.model.SaleInformation;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;

public class SaleInformationTest {
    private SaleInformation saleInformation;
    private ExternalInventorySystem externalInventorySystem;
    private DiscountRegistry discountRegistry;


    @Before
    public void setUp() throws SaleInformation.ItemNotFoundException, DatabaseFailureException{
        saleInformation = new SaleInformation();
        externalInventorySystem = new ExternalInventorySystem();
        discountRegistry = new DiscountRegistry();
        
        saleInformation.addItem(420101, 1, externalInventorySystem);
        saleInformation.addItem(520001, 2, externalInventorySystem);
    }

    @After
    public void tearDown(){
        saleInformation = null;
    }

    @Test
    public void testCalculateTotalPrice(){
        double expectedTotalPrice = 110;
        saleInformation.uppdateSaleInformation();
        assertEquals("calculateTotalPrice() caculated wrong total price of sold items",expectedTotalPrice, saleInformation.getTotalPrice(), 1e-9);
    }

    @Test
    public void testAddItemToNullSoldItems() throws SaleInformation.ItemNotFoundException, DatabaseFailureException{
        ItemDTO glass = new ItemDTO(339800, "Hagendas", "strawberry", 0.2, 70);
        int increasedQuantity = 3;

        HashMap<ItemDTO, Integer> beforeSoldItems = saleInformation.getSoldItems();
        saleInformation.addItem(glass.getId(), increasedQuantity, externalInventorySystem);

        HashMap<ItemDTO, Integer> afterSoldItems = saleInformation.getSoldItems();

        int beforeGlassSoldQuantity = beforeSoldItems.getOrDefault(glass, 0);
        int afterGlasSoldQuantity = afterSoldItems.get(glass);
        assertEquals("addItem() did not return the right item quantity in the sale information", beforeGlassSoldQuantity+increasedQuantity, afterGlasSoldQuantity);
    }

    @Test
    public void testIncludeDiscount(){
        int customerId = 1234;
        double totalPrice = 110;
        CustomerDTO costumer = CustomerRegistry.getCustomerRegistry().findCustomerById(customerId);
        DiscountDTO[] expectedDiscounts = discountRegistry.findDiscount(costumer);
        double expectedPriceAfterDiscount = totalPrice;
        for (DiscountDTO expectedDiscount : expectedDiscounts){
            expectedPriceAfterDiscount *= expectedDiscount.getType();
        }

        double actualPriceAfterDiscount = saleInformation.includeDiscount(costumer.getId(), discountRegistry);
        assertEquals("IncludeDiscount() calculated wrong price after discount",expectedPriceAfterDiscount, actualPriceAfterDiscount, 1e-9);
    }   

    @Test
    public void testUppdatSaleInformation() throws SaleInformation.ItemNotFoundException, DatabaseFailureException{
        
        saleInformation.addItem(420101, 1, externalInventorySystem);
        
        double expectedTotalPrice = 160;
        saleInformation.uppdateSaleInformation();
        assertEquals("uppdateSaleInformation() returned wrong total price of sold items", expectedTotalPrice, saleInformation.getTotalPrice(), 1e-9);
    }
}
