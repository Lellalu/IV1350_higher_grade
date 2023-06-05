package se.kth.iv1350.pos.integration;

import java.sql.Timestamp;

public class ErrorMessageHandler {
    /**
     * Displays the specified error message.
     *
     * @param msg The error message.
    */
    public void showErrorMsg(String msg) {
        StringBuilder errorMsgBuilder = new StringBuilder();
        errorMsgBuilder.append("ERROR MESSAGE - "); 
        errorMsgBuilder.append(currentTime() + ": "); 
        errorMsgBuilder.append(msg);
        System.out.println(errorMsgBuilder);
    }

    private String currentTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }

}
