package com.haripriya.grocerystore;

import java.util.ArrayList;

/**
 * Represents all the logic in a Grocery Store
 * 
 * @author Haripriya S
 */
public class GroceryStore {
    private ArrayList<ItemGroup> inventory; // Product inventory consisting of all item groups
    private ArrayList<Register> registers;  // Checkout registers
    
    // Special discounts that can applied on the checkout value
    private final double EMP_DISCOUNT = 5;
    private final double SENIOR_DISCOUNT = 10; 
    
    /*
        Constructors
    */
    public GroceryStore() {
        this.inventory = new ArrayList<>();
        this.registers = new ArrayList<>();
        
        // Let us add 5 registers by default
        Register reg;
        for (int i = 1 ; i <= 5 ; i++){
            reg = new Register(i);
            registers.add(reg);
        }
        
        // Inventory
        // Let us add some items and groups, for example
        ItemGroup fruits = new ItemGroup("Fruits");
        ItemGroup dairy = new ItemGroup("Dairy");
        ItemGroup veggies = new ItemGroup("Vegetables");
        
        // Fruits
        Item apples = new Item(101, "Apples", 56.75, 50);
        Item oranges = new Item(102, "Oranges", 50.00, 150);
        oranges.setDiscount(10);    // 10% discount on oranges
        Item mangoes = new Item(103, "Mangoes", 98.00, 100);
        fruits.addItem(apples);
        fruits.addItem(oranges);
        fruits.addItem(mangoes);
        
        // Vegetables
        Item capsicum = new Item(201, "Capsicum", 13.5, 200);
        Item potatoes = new Item(202, "Potatoes", 22.65, 100);
        veggies.addItem(capsicum);
        veggies.addItem(potatoes);
        
        // Dairy
        Item amul = new Item(301, "Amul 1 litre milk", 70, 100);
        Item amul2 = new Item(302, "Amul 0/5 litre milk", 40, 100);
        Item ice_cream = new Item(303, "Arun Ice Cream Tub", 250, 50);
        ice_cream.setDiscount(15);
        dairy.addItem(amul);
        dairy.addItem(amul2);
        dairy.addItem(ice_cream);
        
        // Add the item groups
        this.inventory.add(dairy);
        this.inventory.add(veggies);
        this.inventory.add(fruits);
    }
    
    /*
        Getters and setters
    */
    public Register getRegisterByNumber(int number){
        return this.registers.get(number-1);
    }
    
    /*
        Logic
    */
    // Find item by ID
    public Item findItemById(int id){
        Item result = null;
        for (ItemGroup group : this.inventory){
            for (Item item : group.getItems()){
                if (item.getId() == id){
                    result = item;
                    break;
                }
            }
            if (result != null){
                break;
            }
        }
        return result;
    }
    
    // Customer order
    public void buyFromStore(Order order, int regNum, boolean employee, boolean senior){
        Register reg = this.getRegisterByNumber(regNum);
        
        if (employee){
            // Apply special discount
            System.out.println(reg.checkout(order, this.EMP_DISCOUNT));
        }
        else if (senior){
            // Apply special discount
            System.out.println(reg.checkout(order, this.SENIOR_DISCOUNT));
        }
        else{
            System.out.println(reg.checkout(order));
        }
    }
    
    /*
        Utility functions
    */
    public String displayInventory(){
        String result = "";
        
        result += "\n*********************************************************";
        result += "\nInventory";
        result += "\n*********************************************************";
        
        for (ItemGroup group: this.inventory){
            result += "\n\t--> " + group.getName();
            for (Item item: group.getItems()){
                result += "\n\t\t" + item.toString();
            }
        }
        
        return result;
    }

    public String displaySales(){
        String result = "";
        
        result += "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
        result += "\nTotal Sales";
        result += "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
        
        for (ItemGroup group: this.inventory){
            result += "\n\t--> " + group.getName();
            for (Item item: group.getItems()){
                result += "\n\t\t" + item.getSoldUnitsString();
            }
        }
        
        return result;
    }
    
    /*
        Testing
    */
    public static void main(String [] args){
        // New GroceryStore object
        GroceryStore store = new GroceryStore();

        // Display inventory
        System.out.println(store.displayInventory());
        
        // Let us perform some sample transactions
        
        // Transaction 1
        Order order = new Order();
        order.addItem(store.findItemById(201), 2);
        order.addItem(store.findItemById(101), 1);
        order.addItem(store.findItemById(202), 1);
        store.buyFromStore(order, 1, false, false);
        
        // Transaction 2, special employee discount
        order = new Order();
        order.addItem(store.findItemById(301), 2);
        order.addItem(store.findItemById(303), 1);
        store.buyFromStore(order, 2, true, false);
        
        // Transaction 3, senior citizen discount
        order = new Order();
        order.addItem(store.findItemById(102), 2);
        order.addItem(store.findItemById(103), 3);
        order.addItem(store.findItemById(302), 2);
        order.addItem(store.findItemById(202), 1);
        store.buyFromStore(order, 3, false, true);
        
        // Display inventory after all transactions
        System.out.println(store.displayInventory());
        
        // Display the total sales
        System.out.println(store.displaySales());
        
    }
}
