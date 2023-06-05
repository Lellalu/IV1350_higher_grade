package se.kth.iv1350.pos.integration;

/**
* This is theItemDTO, which contains information of each item.
* including int id, String name, String itemDescription, double vat and double price.
*/ 
public class ItemDTO {
    private int id;
    private String name;
    private String itemDescription;
    private double vat;
    private double price;

/**
* Create the ItemDTO.
* @param id the int itemIdentifier should be entered.
* @param name The String of the item's name should be entered.
* @param itemDescription The String of the detailed information of the item should be entered.
* @param vat The double of the vat of the item should be entered.
* @param price The double of the item Price should be entered.
*/
    public ItemDTO(int id, String name, String itemDescription, double vat, double price){
        this.id = id;
        this.name = name;
        this.itemDescription = itemDescription;
        this.vat = vat;
        this.price = price;
    }

/**
* Get itemIdentifier of an item.
* @return id The int of the itemIdentifier will eb returned.
*/
    public int getId(){
        return id;
    }

/**
* Get name of an item.
* @return name The String of the item's name.
*/
    public String getName(){
        return name;
    }

/**
* Get itemDescription.
* @return itemDescription The String texts of the item's description.
*/
    public String getItemDescription(){
        return itemDescription;
    }

/**
* Get vat of an item.
* @return vat The double number of the item's vat.
*/
    public Double getVat(){
        return vat;
    }
 
/**
* Get price of an item.
* @return price The double of the itemPrice.
*/
    public Double getPrice(){
        return price;
    }

/**
* Check if the two ItemDTO are equal to each other.
* @param o The object in comparison should be entered.
* @return true If the ItemDTO is exactly the same or the two ItemDTOs have the same id&name&itemDescription&vat&price.
* @return false If the entered object is null or they belong to different class
* or they have the different id/name/itemDescription/vat/price.
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItemDTO itemDTO = (ItemDTO) o;
        return (this.id == itemDTO.getId() &&
                this.vat == itemDTO.getVat() &&
                this.price == itemDTO.getPrice() &&
                name.equals(itemDTO.getName()) &&
                itemDescription.equals(itemDTO.getItemDescription()));
    }

/**
* Compute Hash of the itemDTO.
* @return id The int number of the itemIdentifier.
*/
    @Override
    public int hashCode() {
        return id;
    }
}
