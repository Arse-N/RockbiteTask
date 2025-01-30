package model;

import java.util.HashMap;

public class Inventory {

    private String inventoryId;
    private String name;
    private HashMap<String, Item> items;

    public Inventory() {
    }

    public Inventory(String inventoryId, String name, HashMap<String, Item> items) {
        this.inventoryId = inventoryId;
        this.name = name;
        this.items = items;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Item> items) {
        this.items = items;
    }
}
