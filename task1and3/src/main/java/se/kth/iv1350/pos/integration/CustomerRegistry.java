package se.kth.iv1350.pos.integration;

/**
* A Singleton CustomerRegistry that creates customerRegistry instances containing all customerDTOs.
* The constructor is private, meaning that all other classes can only get
* CustomerRegistry by calling getCustomerRegistry, thus ensuring that only one
* CustomerRegistry is used in all other classes.
*/
public class CustomerRegistry {
    private static final CustomerRegistry customerRegistry = new CustomerRegistry();
    private  CustomerDTO[] customers;

    /**
    * @return Get the CustomerRegistry, which is a singleton.
    */
    public static CustomerRegistry getCustomerRegistry() { 
        return customerRegistry;
    }

    
    private CustomerRegistry(){
        CustomerDTO customer_1 = new CustomerDTO(1234, 20, "SWE");
        CustomerDTO customer_2 = new CustomerDTO(5678, 100, "JAP");
        CustomerDTO customer_3 = new CustomerDTO(9012, 50, "DAN");
        customers = new CustomerDTO[] {customer_1, customer_2, customer_3};
    }

/**
* Find the customerDTO by customerId.
* @param customerId The int should be entered.
* @return customer The customerDTO if the customerDTO is found.
* @return null if the customerDTO can not be found.
*/
    public CustomerDTO findCustomerById (int id){
        for(CustomerDTO customer : customers){
            if(customer.getId() == id){
                return customer;
            }
        }
        return null;
    }
}
