package com.haripriya.grocerystore;

import java.util.HashMap;

/**
 *
 * @author Haripriya S
 */
public class Register {
    private final int number;               // Every register will be identified by a number
    
    /*
        Constructor
    */
    public Register(int number) {
        this.number = number;
    }
    
    /*
        Logic
    */
    
    // Function to checkout an order and return the generated bill
    public String checkout(Order order, double discount){
        String result = "";
        result += "\n---------------------------------------------------";
        result += "\nOrder Checkout";
        result += "\n---------------------------------------------------";
        result += "\nRegister " + this.number;
        
        // Finalize the order
        order.setDiscount(0);
        order.computeFinalCost();
        
        // Adjust inventory counts
        HashMap<Item, Integer> orderItems = order.getItems();
        for (Item item: orderItems.keySet()){
            // Adjust the available quantity of the items suitably
            item.setAvailableQuantity(item.getAvailableQuantity()-orderItems.get(item));
            
            // Set the number of units sold accordingly
            item.setSoldQuantity(item.getSoldQuantity()+orderItems.get(item));
        }
        
        result += order.toString();
        return result;
    }
    
}
