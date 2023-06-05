package se.kth.iv1350.pos.integration;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerDTOTest {
    private static final int DEFAULT_ID = 1;
    private static final int DEFAULT_POINTS = 2;
    private static final String DEFAULT_ADDRESS = "EARTH";
    private static CustomerDTO customerDTO;

    @Before
    public void setUp(){
        customerDTO = new CustomerDTO(
            DEFAULT_ID, DEFAULT_POINTS, DEFAULT_ADDRESS);
    }

    @After
    public void tearDown(){
        customerDTO = null;
    }

    @Test
    public void testGetId(){
        assertEquals("GetId() returned wrong id", customerDTO.getId(), DEFAULT_ID);
    }

    @Test
    public void testGetPoints(){
        assertEquals("GetPoints() returned wrong points", customerDTO.getPoints(), DEFAULT_POINTS);
    }

    @Test
    public void testGetAddress(){
        assertEquals("GetAddress() returned wrong address", customerDTO.getAddress(), DEFAULT_ADDRESS);
    }

    
}
