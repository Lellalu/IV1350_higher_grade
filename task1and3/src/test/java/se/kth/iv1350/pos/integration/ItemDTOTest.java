package se.kth.iv1350.pos.integration;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemDTOTest {
    private static final int DEFAULT_ID = 420010;
    private static final String DEFAULT_NAME = "foo";
    private static final String DEFAULT_ITEM_DESCRIPTION = "bar";
    private static final double DEFAULT_VAT = 0.2;
    private static final double DEFAULT_PRICE = 420;
    private static ItemDTO itemDTO;

    @Before
    public void setUp(){
        itemDTO = new ItemDTO(
            DEFAULT_ID, DEFAULT_NAME, DEFAULT_ITEM_DESCRIPTION, DEFAULT_VAT, DEFAULT_PRICE);
    }

    @After
    public void tearDown(){
        itemDTO = null;
    }

    @Test
    public void testGetId(){
        assertEquals("GetId() returned wrong id", itemDTO.getId(), DEFAULT_ID);
    }

    @Test
    public void testGetName(){
        assertEquals("getName() returned wrong name", itemDTO.getName(), DEFAULT_NAME);
    }

    @Test
    public void testGetItemDescription(){
        assertEquals("GetItemDescription() returned wrong description", itemDTO.getItemDescription(), DEFAULT_ITEM_DESCRIPTION);
    }

    @Test
    public void testGetVat(){
        assertEquals("GetVat() returned wrong vat", itemDTO.getVat(), DEFAULT_VAT, 1e-9);
    }

    @Test
    public void testGetPrice(){
        assertEquals("GetPrice() returned wrong price", itemDTO.getPrice(), DEFAULT_PRICE, 1e-9);
    }
    
}
