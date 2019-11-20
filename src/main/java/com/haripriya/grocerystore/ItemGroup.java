package com.haripriya.grocerystore;

import java.util.ArrayList;

/**
 * An ItemGroup is a collection of similar items, say "Fruits" or "Dairy".
 * 
 * @author Haripriya S
 */
public class ItemGroup {
    private final String name;      // Name for the group like "Fruits"
    private double group_discount;  // Group-level discount
    private ArrayList<Item> items;  // Items belonging to this group
    
    /*
        Constructors
    */
    public ItemGroup(String name) {
        this.name = name;
        this.group_discount = 0;
        this.items = new ArrayList<>();
    }
    
    /*
        Getters and setters
    */
    public double getGroupDiscount() {
        return group_discount;
    }

    public void setGroupDiscount(double group_discount) {
        this.group_discount = group_discount;
        
        // Group discount applies for all the items in the group
        for (Item item: items){
            item.setDiscount(group_discount);
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    
    public void addItem(Item item){
        items.add(item);
        
        // Check if a group discount is present, and if so apply it
        if (this.group_discount > 0.0){
            item.setDiscount(this.group_discount);
        }
    }
    
    /*
        Utility functions
    */
    @Override
    public String toString() {
        String result = this.name + "\n";
        for (Item item : this.items){
            result += "\t" + item.toString() + "\n";
        }
        
        return result;
    }

}
