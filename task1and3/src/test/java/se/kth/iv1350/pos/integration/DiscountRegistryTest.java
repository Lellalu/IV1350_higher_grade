package se.kth.iv1350.pos.integration;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiscountRegistryTest {
    private static DiscountRegistry discountRegistry;

    @Before
    public void setUp(){
        discountRegistry = new DiscountRegistry();
    }

    @After
    public void tearDown(){
        discountRegistry = null;
    }

    @Test
    public void findDiscountSuccessfulTest(){
        CustomerDTO customer_3 = new CustomerDTO(9012, 50, "DAN");
        DiscountDTO discount_1 = new DiscountDTO(0.8, "Points over 100.");
        DiscountDTO discount_3 = new DiscountDTO(0.85, "Item number over 2.");

        DiscountDTO[] foundDiscount = discountRegistry.findDiscount(customer_3);
        DiscountDTO[] expectedFoundDiscount = {discount_3, discount_1};
        assertNotNull(foundDiscount);

        assertArrayEquals("findDiscount() returns a wrong discountDTO array", expectedFoundDiscount, foundDiscount);
    }
}
