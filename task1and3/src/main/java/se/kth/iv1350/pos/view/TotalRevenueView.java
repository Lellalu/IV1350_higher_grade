package se.kth.iv1350.pos.view;

/**
 * Shows a running total of a sale process of each sale in view.
*/
public class TotalRevenueView extends RevenueDisplay{
    public TotalRevenueView(long seed){
        super(seed);
    }

    /**
    * An method to print total revenue out to the view.
    *@throws Exception the exception will be thrown when an error happens.
    */
    protected void writeRevenue() throws Exception{
        if(rand.nextDouble() > 0.5) {
            throw new Exception("We decide to throw exception in writeRevenue.");
        }
        System.out.println("### Current income of total sales is " + 
        Double.toString(totalRevenue) + ". ###");
    }

    /**
    * An method to print out the information to the view, when the error exception happens.
    *@param e Exception thrown when the error happens.
    */
    protected void handleErrors(Exception e){
        System.out.println("The error throw by TotalRevenueView is handled.");
    }
}


