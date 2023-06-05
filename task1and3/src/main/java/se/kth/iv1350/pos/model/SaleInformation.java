package se.kth.iv1350.pos.model;

import java.util.ArrayList;
import java.util.HashMap;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ExternalInventorySystem.DatabaseFailureException;
import se.kth.iv1350.pos.view.RevenueObserver;
import se.kth.iv1350.pos.integration.DiscountDTO;
import se.kth.iv1350.pos.integration.CustomerDTO;
import se.kth.iv1350.pos.integration.DiscountRegistry;
import se.kth.iv1350.pos.integration.ExternalInventorySystem;
import se.kth.iv1350.pos.integration.CustomerRegistry;



/**
* This is the SaleInformation class, containing methods of getting information of the current sale.
* It has discounts DiscountDTO[], double totalPrice, HashMap<ItemDTO, Integer> soldItems as attibutes.
*/ 
public class SaleInformation {
    private DiscountDTO[] discounts;
    private double totalPrice;
    private HashMap<ItemDTO, Integer> soldItems;
    private ArrayList<RevenueObserver> revenueObservers = new ArrayList<>();
    
/**
 * The specified observer will be notified when a sale
 * has been ended. There will be notifications only for
 * sales that are started after this method is called.
 *
* @param observer The observer to notify.
*/
public void addSaleObserver(RevenueObserver observer) { 
    revenueObservers.add(observer);
}

/**
* Create and initialize the saleInformation.
* Set the totalPrice = 0 when the sale starts and the discounts, soldItems are null in the beginning.
*/
    public SaleInformation(){
        discounts = new DiscountDTO[]{};
        totalPrice = 0;
        soldItems = new HashMap<ItemDTO, Integer>();
    }


/**
* Get totalPrice of all soldItems.
* @return totalPrice The double of the running total price.
*/
    public double getTotalPrice(){
        return totalPrice;
    }

/**
* Get vat of all soldItems.
* @return vatPrice The VAT double of running total price.
*/
    public double getVatPrice(){
        return totalPrice * 0.2;
    }


/**
* Get eligible discounts of the customer.
* @return discounts The DiscoutDTO[] which contains all eligible discounts of a customer.
*/
    public DiscountDTO[] getDiscount(){
        return discounts;
    }

/**
* Get the information of all sold items.
* @return soldItems The HashMap<ItemDTO, Integer> which contains all the entered items and their sold quantity for the current sale.
*/
    public HashMap<ItemDTO, Integer> getSoldItems(){
        return new HashMap<ItemDTO, Integer>(soldItems);
    }

/**
 * Thrown when trying to register an item whoes identifier is invalid.
*/
    public class ItemNotFoundException extends Exception { 
        public ItemNotFoundException(String errorMessage) {
            super(errorMessage);
        }
}

/**
* Add the item to the current sale.
* If the item is invalid, ItemNotFoundException will be throwed .
* If the item is valid(can be found in inventory), the itemDTO will be added in the soldItems.
* If the item is already existed on soldItems list, the quantity of the sold item will be added
* with the alreadySoldQuantity and the newEnteredQuantity.
* @param identifier int of itemidentifier.
* @param quantity int of the number of entered item.
* @param ExternalInventorySystem the external item inventory system.
* @throws ItemNotFoundException when the entered item identifier can not be found in inventory.
* @throws DatabaseFailureException when it fails to reach the inventory database.
*/
    public void addItem(int identifier, int quantity, ExternalInventorySystem externalInventorySystem)
    throws ItemNotFoundException, DatabaseFailureException{  
        ItemDTO toBeAddedItem = externalInventorySystem.findItem(identifier);

        if (toBeAddedItem == null) {
            throw new ItemNotFoundException("The item " +Integer.toString(identifier)+ " does not exist in the inventory.");
        }
        int alreadySoldQuantity = soldItems.getOrDefault(toBeAddedItem, 0);
        soldItems.put(toBeAddedItem, alreadySoldQuantity+quantity);
    }

/**
* Find and apply discount to totalPrice.
* @param customerId The int which will be entered for getting eligible discounts of the customer.
* @return totalPrice The double of total price after that eligible discounts are applied.
*/ 
    public double includeDiscount(int customerID, DiscountRegistry discountRegistry){
        CustomerDTO customerDTO = CustomerRegistry.getCustomerRegistry().findCustomerById(customerID);
        discounts = discountRegistry.findDiscount(customerDTO);
        uppdateSaleInformation();
        return totalPrice;
    }

/**
* Uppdate the saleInformation of the current sale.
* Add price of all entered items.
* Add discount to the total price.
*/
    public void uppdateSaleInformation(){
        totalPrice = 0;
        for(ItemDTO solditem : soldItems.keySet()){
            totalPrice += soldItems.get(solditem) * solditem.getPrice();
        }
        for (DiscountDTO discount : discounts){
            totalPrice *= discount.getType();
        }
    }

    private void notifyObservers() {
        for (RevenueObserver obs : revenueObservers) {
                obs.completedSale(getTotalPrice());
            }
    }

    public void saleEnd(){
        uppdateSaleInformation();
        notifyObservers();
    }
}
