package se.kth.iv1350.pos.integration;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerRegistryTest {
    private static CustomerRegistry customerRegistry;

    @Before
    public void setUp(){
        customerRegistry = CustomerRegistry.getCustomerRegistry();
    }

    @After
    public void tearDown(){
        customerRegistry = null;
    }

    @Test
    public void testfindCustomerById(){
        CustomerDTO customer_1 = new CustomerDTO(1234, 20, "SWE");

        CustomerDTO expectedCustomerDTO = customer_1;
        assertEquals("findCustomerById() returned wrong customerDTO", expectedCustomerDTO, customerRegistry.findCustomerById(1234));
    }
}
