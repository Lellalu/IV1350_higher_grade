package se.kth.iv1350.pos.integration;

/**
* This is the DiscountDTO class in the integration to contain all informationof discounts:
* including double Type, String rules
* 
*/
public class DiscountDTO {
    private double type;
    private String rules;

/**
* Create the DiscountDTO.
* @param type The double number of the discount should be entered.
* @param rules The String texts of the discount rules should be entered.
*/ 
    public DiscountDTO(double type, String rules){
        this.type = type;
        this.rules = rules;
    }

/**
* Get type of the discount.
* @return type The double to include calculation of the total price if an eligible discount is requested.
*/ 
    public double getType(){
        return type;
    }

/**
* Get rules of the discount.
* @return rules The String text to show detailed regulations of the discount.
*/ 
    public String getRules(){
        return rules;
    }

/**
* Check if two discounts are euqal to each other.
* @return true if the entered object is exactly the same with the compared one,
* or the two discountDTOs have the same type and the rules.
* @return false if the object does not belong to the DiscountDTO class, or 
* they have different type or they have different rules.
*/ 
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiscountDTO discountDTO = (DiscountDTO) o;
        return (this.type == discountDTO.getType() &&
                rules.equals(discountDTO.getRules()));
    }

}