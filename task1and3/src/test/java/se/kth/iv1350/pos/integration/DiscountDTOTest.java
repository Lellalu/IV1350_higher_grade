package se.kth.iv1350.pos.integration;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiscountDTOTest {
    private static final double DEFAULT_TYPE = 0.5;
    private static final String DEFAULT_RULES = "prime";
    private static DiscountDTO discountDTO;

    @Before
    public void setUp(){
        discountDTO = new DiscountDTO(
            DEFAULT_TYPE, DEFAULT_RULES);
    }

    @After
    public void tearDown(){
        discountDTO = null;
    }

    @Test
    public void testGetType(){
        assertEquals("GetType() returned wrong type", discountDTO.getType(), DEFAULT_TYPE, 1e-9);
    }

    @Test
    public void testGetRules(){
        assertEquals("GetRules() returned wrong rules", discountDTO.getRules(), DEFAULT_RULES);
    }

}
