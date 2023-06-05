package se.kth.iv1350.pos.view;


/**
* A listener interface for receiving notifications about
* completed sales. The class that is interested in such
* notifications implements this interface, and the object
* created with that class is registered with {@link
* se.kth.iv1350.pos.view;
* view #addRevenueObserver(RevenueObserver)} and show the sum of the cost for a sale When a
* sale is ended.
*/
public interface RevenueObserver {

    /**
     * called when a sale has been ended.
     * @param salePrice The running total price of a sale.
    */
    void completedSale(double salePrice);
}
