package se.kth.iv1350.pos.integration;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegisterCreatorTest {

    private RegisterCreator registerCreator;

    @Before
    public void setUp(){
        registerCreator = new RegisterCreator();
    }

    @After
    public void tearDown(){
        registerCreator = null;
    }

    @Test
    public void testGetItemRegistry(){
        assertEquals("getItemRegistry() returned wrong type",registerCreator.getItemRegistry().getClass(), ExternalInventorySystem.class);
    }

    @Test
    public void testGetDiscountRegistry(){
        assertEquals("getDiscountRegistry() returned wrong type",registerCreator.getDiscountRegistry().getClass(), DiscountRegistry.class);
    }
}