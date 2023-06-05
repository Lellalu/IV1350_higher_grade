package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemDTO;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.time.LocalDateTime;

public class ReceiptTest {
    private Receipt receipt;
    private final LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.now();
    private HashMap<ItemDTO, Integer> soldItems;
    private double DEFAULT_TOTAL_PRICE = 200;
    private double DEFAULT_PAID_AMOUNT = 230;

    @Before
    public void setUp(){
        ItemDTO godis = new ItemDTO(420101, "godis", "bästa", 0.3, 50);
        ItemDTO chips = new ItemDTO(520001, "OLW", "cheddar", 0.3, 30);
        ItemDTO glass = new ItemDTO(339800, "Hagendas", "straberry", 0.4, 70);
        ItemDTO cola = new ItemDTO(778020, "Cola", "zero", 0.3, 15);
        
        soldItems = new HashMap<ItemDTO, Integer>();
        soldItems.put(godis, 1);
        soldItems.put(chips, 2);
        soldItems.put(glass, 5);
        soldItems.put(cola, 10);

        receipt = new Receipt(DEFAULT_PAID_AMOUNT, DEFAULT_TOTAL_PRICE, soldItems, DEFAULT_DATE_TIME);
    }

    @After
    public void tearDown(){
        receipt = null;
    }

    @Test
    public void testCalculateChanges(){
        double expectedChanges = DEFAULT_PAID_AMOUNT - DEFAULT_TOTAL_PRICE;
        assertEquals("calculateTotalPrice() caculated wrong total price of sold items",expectedChanges, receipt.calculateChanges(), 1e-9);
    }
}
