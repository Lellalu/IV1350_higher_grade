package se.kth.iv1350.pos.model;

import java.time.LocalDateTime;
import java.util.HashMap;

import se.kth.iv1350.pos.integration.ItemDTO;

/**
* This is the Receipt class, calling methods of doing calculation in receipt and getting information of the receipt.
* It has double paidAmount, double totalPrice, HashMap<ItemDTO, Integer> soldItems, DiscountDTO[] discounts, LocalDateTime dateTime as attibutes.
*/ 
public class Receipt {
    private double paidAmount;
    private double totalPrice;
    private HashMap<ItemDTO, Integer> soldItems;
    private LocalDateTime dateTime;

/**
* Create the receipt.
* @param paidAmount The double number of paidAmount should be entered.
* @param totalPrice The double of totalPrice of soldItems should be entered.
* @param dateTime The Local dateTime should be entered.
* @param soldItems The HashMap<ItemDTO, Integer> of soldItems should be entered.
*/
    public Receipt(double paidAmount, double totalPrice, HashMap<ItemDTO, Integer> soldItems, LocalDateTime dateTime){
        this.paidAmount = paidAmount;
        this.totalPrice = totalPrice;
        this.dateTime = LocalDateTime.now();
        this.soldItems = soldItems;
    }

/**
* Calculate the changes.
* @return changes The double number of the paidAmount-totalPrice will be returned.
*/
    public double calculateChanges(){
        return paidAmount-totalPrice;
    }

/**
* Get LocalDate Time.
* @return dateTime The LocalDate Time will be returned.
*/
    public LocalDateTime getTime(){
        return dateTime;
    }

/**
* Get totalPrice of the sale.
* @return totalPrice The double number of the total price of sold items will be returned.
*/
    public double getTotalPrice(){
        return totalPrice;
    }

/**
* Get paidAmount of the sale.
* @return paidAmount The double number of the paidAmount entered in will be returned.
*/
    public double getPaidAmount(){
        return paidAmount;
    }

/**
* Convert the information in receipt to String for printing out.
* @return receipt The String type of the receipt information, 
* including Total price, Total price after discount, Paid amount and the Changes
* will be returned.
*/
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Time:" + dateTime.toString()+"\n");
        sb.append("--------------------------------------" + "\n");

        double itemPrice;
        int quantity;
        double totalPriceOfSoldItems = 0;
        for (ItemDTO itemDTO : soldItems.keySet()) {
            quantity = soldItems.get(itemDTO);
            itemPrice = itemDTO.getPrice() * quantity;
            totalPriceOfSoldItems += itemPrice;
            
            sb.append("\n");
            sb.append(itemDTO.getName() + " * " + Integer.toString(quantity) + ":  " + "SEK " + Double.toString(itemPrice) + "\n");
            sb.append("\n"); 
        }
     
        sb.append("--------------------------------------" + "\n");
        sb.append("Total price: " + "SEK " + Double.toString(totalPriceOfSoldItems) + "\n");
        sb.append("Total VAT 20%: " + "SEK " + Double.toString(totalPriceOfSoldItems * 0.2) + "\n");
        sb.append("Total price after discount: " + "SEK " + Double.toString(totalPrice) + "\n");
        sb.append("Paid amount: " + "SEK " + Double.toString(paidAmount) + "\n");
        sb.append("Changes: " + "SEK " + Double.toString(paidAmount - totalPrice) + "\n");
        return sb.toString();
    }
}

