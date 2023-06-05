package se.kth.iv1350.pos.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import se.kth.iv1350.pos.view.RevenueDisplay;

public class TotalRevenueFileOutput extends RevenueDisplay{
    private PrintWriter logFile;

    public TotalRevenueFileOutput(String filename, long seed) throws IOException {
        super(seed);
        logFile = new PrintWriter(new FileWriter(filename), true);
    }

    /**
     * Show total revenue in a file.
     * @throws Exception
    */
    protected void writeRevenue() throws Exception{
        if(rand.nextDouble() > 0.5) {
            throw new Exception("We decide to throw exception in writeRevenue.");
        }
        StringBuilder logMsgBuilder = new StringBuilder(); 
        logMsgBuilder.append("Revenue - "); 
        logMsgBuilder.append(currentTime() + ": "); 
        logMsgBuilder.append(Double.toString(totalRevenue));
        logFile.println(logMsgBuilder.toString());
    }

    private String currentTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }

    protected void handleErrors(Exception e){
        System.out.println("The error throw by TotalRevenueFileOutput is handled.");
    }
}