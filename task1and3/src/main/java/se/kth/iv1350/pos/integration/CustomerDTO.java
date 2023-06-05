package se.kth.iv1350.pos.integration;

/**
* This is the CustomerDTO class in the integration, which contains information of customers:
* including int customerId, int points and String address.
*/
public class CustomerDTO {
    private int id;
    private int points;
    private String address;

/**
* Create the CustomerDTO.
* @param id, the int of customer identifier should be entered.
* @param points, the int of customer's shoping points of membership should be entered.
* @param address, the String of customer's address should be entered.
* @return externalInventorySystem containing information of all ItemDTOs.
*/
    public CustomerDTO(int id, int points, String address){
        this.id = id;
        this.points = points;
        this.address = address;
    }

/**
* Get Id number of the customer.
* @return id The int of customer identifier will be returned.
*/
    public int getId(){
        return id;
    }

/**
* Get membership points of the customer.
* @return points The int of customer's shoping points of membership whill be returned.
*/
    public int getPoints(){
        return points;
    }

/**
* Get address of the customer.
* @return address The String of customer's address whill be returned.
*/
    public String getAddress(){
        return address;
    }

/**
* Check if the two CustomerDTO are equal to each other.
* @param o The object in comparison shoule be entered.
* @return true If the entered object is exactly the same class or 
* they have the same id, points as well as the address
* @return false If the entered object is null or they belong to different class,
* or thet have different id/points/address.
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerDTO customerDTO = (CustomerDTO) o;
        return (this.id == customerDTO.getId() &&
                this.points == customerDTO.getPoints() &&
                address.equals(customerDTO.getAddress()));
    }
}
