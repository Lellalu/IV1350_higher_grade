package se.kth.iv1350.pos.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * This LogHandler class is responsible for the log.
*/
public class LogHandler {
    private PrintWriter logFile;

    public LogHandler(String filename) throws IOException { 
        logFile = new PrintWriter(
        new FileWriter(filename), true);
    }

    /**
     * Write a log entry method for writing the log megssage in the log file.
     *
     * @param error_message The message that should be logged.
    */
    public void log(String error_message) {
        StringBuilder logMsgBuilder = new StringBuilder(); 
        logMsgBuilder.append("LOG - "); 
        logMsgBuilder.append(currentTime() + ": "); 
        logMsgBuilder.append(error_message);
        logFile.println(logMsgBuilder);
    }

    private String currentTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }
}
