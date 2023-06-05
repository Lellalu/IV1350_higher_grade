package se.kth.iv1350.pos.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.RegisterCreator;

public class ViewTest {
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private View view;
    private String LOG_FILENAME = "/tmp/test.log";
    private String REVENUE_LOG_FILE = "/tmp/revenue.log";
    private long SEED = 42;
    
    @Before
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outStream));
        RegisterCreator registerCreator = new RegisterCreator();
        Printer printer = new Printer();
        Controller controller = new Controller(registerCreator, printer, LOG_FILENAME, REVENUE_LOG_FILE, SEED);
        view = new View(controller, SEED);
    }
    
    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testRunFakeScenarioOutput()
    {
        view.runFakeScenario();
        StringBuilder expectedOutputRegex = new StringBuilder();
        expectedOutputRegex.append("\n")
            .append("\n")
            .append("Customer id 1234 walks into the store.\n")
            .append("The customer starts the sale.\n")
            .append("The customer buys 0 Cola.\n")
            .append("The customer buys 3 Chips.\n")
            .append("The customer buys 3 glass.\n")
            .append("The customer buys 1 more Hagendas.\n")
            .append("The customer buys 1 invalid item.\n")
            .append("ERROR MESSAGE - [0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+.[0-9]+: The item -1 does not exist\n")
            .append("Try to simulate database failed to reach for item 0\n")
            .append("ERROR MESSAGE - [0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+.[0-9]+: Fail to reach the database for item: 0\n")
            .append("The customer asks for discounts.\n")
            .append("The customer asks to end the sale.\n")
            .append("The error throw by TotalRevenueView is handled.\n")
            .append("The error throw by TotalRevenueFileOutput is handled.\n")
            .append("The customer pays and get the receipt.\n")
            .append("\n")
            .append("\n")
            .append("The Receipt:\n")
            .append("Time:[0-9]+-[0-9]+-[0-9]+T[0-9]+:[0-9]+:[0-9]+.[0-9]+\n")
            .append("--------------------------------------\n")
            .append("\n")
            .append("Hagendas \\* 4:  SEK 280.0\n")
            .append("\n")
            .append("\n")
            .append("OLW chips \\* 3:  SEK 90.0\n")
            .append("\n")
            .append("\n")
            .append("Cola \\* 0:  SEK 0.0\n")
            .append("\n")
            .append("--------------------------------------\n")
            .append("Total price: SEK 370.0\n")
            .append("Total VAT 20%: SEK 74.0\n")
            .append("Total price after discount: SEK 296.0\n")
            .append("Paid amount: SEK 1000.0\n")
            .append("Changes: SEK 704.0\n")
            .append("\n")
            .append("\n")
            .append("\n")
            .append("Customer id 5678 walks into the store.\n")
            .append("The customer starts the sale.\n")
            .append("The customer buys 4 Cola.\n")
            .append("The customer buys 0 Chips.\n")
            .append("The customer buys 0 glass.\n")
            .append("The customer buys 1 more Hagendas.\n")
            .append("The customer buys 1 invalid item.\n")
            .append("ERROR MESSAGE - [0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+.[0-9]+: The item -1 does not exist\n")
            .append("Try to simulate database failed to reach for item 0\n")
            .append("ERROR MESSAGE - [0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+.[0-9]+: Fail to reach the database for item: 0\n")
            .append("The customer asks for discounts.\n")
            .append("The customer asks to end the sale.\n")
            .append("The error throw by TotalRevenueView is handled.\n")
            .append("The error throw by TotalRevenueFileOutput is handled.\n")
            .append("The customer pays and get the receipt.\n")
            .append("\n")
            .append("\n")
            .append("The Receipt:\n")
            .append("Time:[0-9]+-[0-9]+-[0-9]+T[0-9]+:[0-9]+:[0-9]+.[0-9]+\n")
            .append("--------------------------------------\n")
            .append("\n")
            .append("Hagendas \\* 1:  SEK 70.0\n")
            .append("\n")
            .append("\n")
            .append("OLW chips \\* 0:  SEK 0.0\n")
            .append("\n")
            .append("\n")
            .append("Cola \\* 4:  SEK 60.0\n")
            .append("\n")
            .append("--------------------------------------\n")
            .append("Total price: SEK 130.0\n")
            .append("Total VAT 20%: SEK 26.0\n")
            .append("Total price after discount: SEK 117.0\n")
            .append("Paid amount: SEK 1000.0\n")
            .append("Changes: SEK 883.0\n")
            .append("\n")
            .append("\n")
            .append("\n")
            .append("Customer id 9012 walks into the store.\n")
            .append("The customer starts the sale.\n")
            .append("The customer buys 0 Cola.\n")
            .append("The customer buys 3 Chips.\n")
            .append("The customer buys 4 glass.\n")
            .append("The customer buys 1 more Hagendas.\n")
            .append("The customer buys 1 invalid item.\n")
            .append("ERROR MESSAGE - [0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+.[0-9]+: The item -1 does not exist\n")
            .append("Try to simulate database failed to reach for item 0\n")
            .append("ERROR MESSAGE - [0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+.[0-9]+: Fail to reach the database for item: 0\n")
            .append("The customer asks for discounts.\n")
            .append("The customer asks to end the sale.\n")
            .append("### Current income of total sales is 712.2. ###\n")
            .append("The customer pays and get the receipt.\n")
            .append("\n")
            .append("\n")
            .append("The Receipt:\n")
            .append("Time:[0-9]+-[0-9]+-[0-9]+T[0-9]+:[0-9]+:[0-9]+.[0-9]+\n")
            .append("--------------------------------------\n")
            .append("\n")
            .append("Hagendas \\* 5:  SEK 350.0\n")
            .append("\n")
            .append("\n")
            .append("OLW chips \\* 3:  SEK 90.0\n")
            .append("\n")
            .append("\n")
            .append("Cola \\* 0:  SEK 0.0\n")
            .append("\n")
            .append("--------------------------------------\n")
            .append("Total price: SEK 440.0\n")
            .append("Total VAT 20%: SEK 88.0\n")
            .append("Total price after discount: SEK 299.2\n")
            .append("Paid amount: SEK 1000.0\n")
            .append("Changes: SEK 700.8\n")
            .append("\n");
        assertTrue("The printout in RunFakeScenarioOutput() method of View class prints out the wrong result.", outStream.toString().matches(expectedOutputRegex.toString()));
    }
}
