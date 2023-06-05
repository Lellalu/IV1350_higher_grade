package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Receipt;

/**
* This is the program's external printer, which is used for printing out the receipt.
* It will show the information of Receipt out with String.
*/ 
public class Printer {
    public Printer(){
    }

/**
* Print the information of Receipt out.
* @param receipt The final Receipt should be entered.
* @return receipt The text information in String of the Receipt will be returned.
*/
    public String printReceipt(Receipt receipt){
        System.out.println("The Receipt:");
        System.out.println(receipt);
        return receipt.toString();
    }
}
