package se.kth.iv1350.pos.view;
import java.util.Random;

/**
* An abstract class to show total revenue.
*/
public abstract class RevenueDisplay implements RevenueObserver{
    protected double totalRevenue = 0;
    protected Random rand;

    /**
    * Starts the RevenueDisplay.
    */
    protected RevenueDisplay(long seed){
        rand = new Random(seed);
    }

    /**
    * An method to calculate total revenue by passing the double number of salePrice.
    *@param salePrice The double number of the running total price of a sale.
    *@throws Exception the exception will be thrown when an error happens.
    */
    public void completedSale(double salePrice) {
        totalRevenue += salePrice;
        try{
            writeRevenue();
        } catch(Exception e){
            handleErrors(e);
        }
    }

    /**
    * Shows the total revenue in the view.
    * @throws Exception The exception will be thrown if erorr happens by calling the method.
    */
    protected abstract void writeRevenue() throws Exception;

    /**
    * Print out the information when exception happens.
    * @param e Exception thrown when the error happens.
    */
    protected abstract void handleErrors(Exception e);
}
