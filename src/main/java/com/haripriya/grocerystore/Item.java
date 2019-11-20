package com.haripriya.grocerystore;

/**
 * Represents a single, unique item identified by its `id` field.
 *
 * @author Haripriya S
 */
public class Item {
    private final int id;       // Every item shall have a unique code
    private final String name;  // Item name, cannot be changed
    private double price;       // Item price, can be changed 
    private double discount;    // Discount percentage, optional, 0 by default
    private int available_qty;  // Units of the item available for sale
    private int sold_qty;       // Units of the sold yet
    
    /*
        Constructors
    */
    public Item(int id, String name, double price, int available_qty) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available_qty = available_qty;
        this.discount = 0;
        this.sold_qty = 0;
    }
    
    /*
        Getters and setters
    */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getAvailableQuantity() {
        return available_qty;
    }

    public void setAvailableQuantity(int available_qty) {
        this.available_qty = available_qty;
    }

    public int getSoldQuantity() {
        return sold_qty;
    }

    public void setSoldQuantity(int sold_qty) {
        this.sold_qty = sold_qty;
    }

    /*
        Utility functions
    */
    @Override
    public String toString(){
        String result = "[" + this.id + "] " + this.name + " = " + "Rs. " + this.price + 
                " (Qty = " + this.available_qty + ")";
        
        if (this.discount > 0.0){
            result += " (Discount = " + this.discount + "%)";
        }
        
        return result;
    }
    
    public String getSoldUnitsString(){
        String result = "[" + this.id + "] " + this.name + " : Sold = " + this.sold_qty;
        return result;
    }
}
