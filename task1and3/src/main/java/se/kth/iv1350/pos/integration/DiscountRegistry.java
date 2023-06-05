package se.kth.iv1350.pos.integration;

import java.util.HashMap;

/**
* This is the DiscountRegistry class in the integration, it can be created by the RegisterCreator class.
* The DiscountRegistry contains information of all eligible discounts for all customers.
*/
public class DiscountRegistry {
    private HashMap<Integer, DiscountDTO[]> discountDtoMap;

/**
* Create the DiscountRegistry, including some faked database.
* The DiscountRegistry contains a HashMap with customerId as key and DiscountDTO as value.
*/
    public DiscountRegistry(){
        DiscountDTO discount_1 = new DiscountDTO(0.8, "Points over 100.");
        DiscountDTO discount_2 = new DiscountDTO(0.9, "Membership");
        DiscountDTO discount_3 = new DiscountDTO(0.85, "Item number over 2.");

        discountDtoMap = new HashMap<Integer, DiscountDTO[]>();
        discountDtoMap.put(1234, new DiscountDTO[]{discount_1});
        discountDtoMap.put(5678, new DiscountDTO[]{discount_2});
        discountDtoMap.put(9012, new DiscountDTO[]{discount_3, discount_1});
    }

/**
* Get copiedDiscountDtoMap of the discountRegistry database.
* @return copiedDiscountDtoMap The HashMap<Integer,DiscountDTO[]> containing customerId and their corresponding eligible discounts.
*/  
    public HashMap<Integer,DiscountDTO[]> getDiscountRegistry() {
        HashMap<Integer,DiscountDTO[]> copiedDiscountDtoMap = new HashMap<Integer,DiscountDTO[]>(discountDtoMap);
        return copiedDiscountDtoMap;
    }

/**
* Find all eligible discounts by customerDTO.
* @return discounts The DiscountDTO[] containing information of all eligible discounts of a customerDTO.
* @param customerDTO The CustomerDTO containing customerId.
*/
    public DiscountDTO[] findDiscount(CustomerDTO customerDTO){
        int customerId = customerDTO.getId();
        return discountDtoMap.get(customerId);
    }

}
