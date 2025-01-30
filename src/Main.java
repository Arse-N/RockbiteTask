import constants.Rarity;
import model.Inventory;
import model.Item;
import services.InventoryService;
import services.ItemService;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static final Scanner scan = new Scanner(System.in);
    public static HashMap<String, Inventory> inventories = new HashMap<>();

    public static void main(String[] args) {
        askQuestion();
    }

    public static void askQuestion() {
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1. Add new inventory.");
            System.out.println("2. Add new item.");
            System.out.println("3. Upgrade item rarity.");
            System.out.println("4. Get an item.");
            System.out.println("5. Get full inventory.");
            System.out.println("6. Exit");
            System.out.println("Please choose the option: ");
            int option = scan.nextInt();
            scan.nextLine();
            requiredData(option);
        }
    }

    public static void requiredData(int option) {
        switch (option) {
            case 1:
                System.out.println("Inventory name: ");
                String inventoryName = scan.nextLine();
                if (inventoryName.trim().isEmpty()) {
                    System.err.println("Inventory name shouldn't be empty");
                } else {
                    Inventory inventory = InventoryService.createInventory(inventoryName);
                    inventories.put(inventory.getInventoryId(), inventory);
                }
                break;

            case 2:
                System.out.println("Inventory Id: ");
                String inventoryId = scan.nextLine();
                System.out.println("Item name: ");
                String itemName = scan.nextLine();
                System.out.println("Item rarity: ");
                try {
                    Rarity itemRarity = Rarity.valueOf(scan.nextLine().toUpperCase());
                    if (inventoryId.trim().isEmpty() || itemName.trim().isEmpty()) {
                        System.err.println("Inventory Id or Item name shouldn't be empty");
                    } else {
                        Inventory inventory = inventories.get(inventoryId);
                        if (inventory == null) {
                            System.err.println("Inventory not found!");
                        } else {
                            ItemService.createItem(inventory, itemName, itemRarity);
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Item rarity is not found.");
                }
                break;

            case 3:
                System.out.println("Inventory Id: ");
                inventoryId = scan.nextLine();
                System.out.println("Item Id which should be upgraded: ");
                String item1Id = scan.nextLine();
                System.out.println("Item Id which should be merged to first: ");
                String item2Id = scan.nextLine();
                try {
                    Inventory inventory = inventories.get(inventoryId);
                    if (inventory == null) {
                        System.err.println("Inventory not found!");
                    } else {
                        ItemService.upgradeItemRarity(inventory, item1Id, item2Id);
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;

            case 4:
                System.out.println("Inventory Id: ");
                inventoryId = scan.nextLine();
                System.out.println("Item Id to get: ");
                String itemId = scan.nextLine();
                try {
                    Inventory inventory = inventories.get(inventoryId);
                    if (inventory == null) {
                        System.err.println("Inventory not found!");
                    } else {
                        Item item = inventory.getItems().get(itemId);
                        if (item == null) {
                            System.err.println("Item not found!");
                        } else {
                            ItemService.getItemData(item);
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;

            case 5:
                System.out.println("Inventory Id: ");
                inventoryId = scan.nextLine();
                try {
                    Inventory inventory = inventories.get(inventoryId);
                    if (inventory == null) {
                        System.err.println("Inventory not found!");
                    } else {
                        InventoryService.getFullInventoryData(inventory);
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;

            case 6:
                System.out.println("Exiting...");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}
