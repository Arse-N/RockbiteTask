package services;

import model.Inventory;
import model.Item;
import util.BaseUtil;

import java.util.HashMap;

public class InventoryService {

    public static Inventory createInventory(String name) {
        System.out.println("creating inventory ...");
        String inventoryID = BaseUtil.generateID();
        Inventory inventory = new Inventory(inventoryID, name, new HashMap<>());
        System.out.println("inventory successfully created inventoryId = " + inventoryID);
        return inventory;
    }

    public static void getFullInventoryData(Inventory inventory) {
        StringBuilder inventoryString = new StringBuilder("{ id: " + inventory.getInventoryId() + "," +
                "\n name: " + inventory.getName() + "," +
                "\n items: [");
        for (Item item : inventory.getItems().values()) {
            inventoryString.append(ItemService.getItemData(item));
        }
        inventoryString.append("]");
        System.out.println(inventoryString);
    }

}
