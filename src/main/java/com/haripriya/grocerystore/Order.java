package com.haripriya.grocerystore;

import java.util.HashMap;

/**
 * This class represents a single order for a customer.
 *
 * @author Haripriya S
 */
public class Order {
    private HashMap<Item, Integer> items;   // Key = Item, Value = Quantity of item purchased
    private double total_cost;              // Total cost calculated from items
    private double discount;                // Order-level discount
    private double final_cost;              // Final cost after applying discount(if available)

    /*
        Constructor
    */
    public Order() {
        this.items = new HashMap<>();
        this.total_cost = 0;
        this.discount = 0;
    }
    
    /*
        Getters and setters
    */
    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public double getTotalCost() {
        return total_cost;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /*
        Utility functions
    */
    @Override
    public String toString() {
        String result = "\nOrder Summary:\n";
        
        result += "Items:\n";
        for (Item item: items.keySet()){
            result += "\t" + item.toString() + " : Qty = " + items.get(item) + "\n";
        }
        
        result += "Total Cost = Rs." + this.total_cost;
        result += "\nDiscount = " + this.discount + "%";
        result += "\nFinal Cost = Rs." + this.final_cost;
        
        return result;
    }
    
    /*
        Logic
    */
    public void addItem(Item item, int qty){
        this.items.put(item, qty);
        
        // Calculate the cost for the item
        double item_cost = item.getPrice() * qty;
        
        // Apply discount if any
        if (item.getDiscount() > 0){
            item_cost = item_cost - item_cost*item.getDiscount()/100;
        }
        
        // Add the item's price to the order cost
        this.total_cost += item_cost;
    }

    public void computeFinalCost(){
        this.final_cost = this.total_cost - this.total_cost*this.discount/100;
    }
    
}
