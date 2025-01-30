package services;

import constants.Rarity;
import model.Inventory;
import model.Item;
import util.BaseUtil;

public class ItemService {
    public static void createItem(Inventory inventory, String name, Rarity rarity) {
        String itemID = BaseUtil.generateID();
        if (name == null) {
            throw new NullPointerException("the item name shouldn't be empty");
        } else {
            System.out.println("Creating item ...");
            Item item = new Item(itemID, name, rarity);
            inventory.getItems().put(itemID, item);
            System.out.println("item successfully created itemId = " + itemID);
        }
    }

    public static void upgradeItemRarity(Inventory inventory, String item1Id, String item2Id) {
        System.out.println("upgrading item ...");
        Item item1 = inventory.getItems().get(item1Id);
        Item item2 = inventory.getItems().get(item1Id);
        if (item1 != null && item2 != null) {
            if (item1.getName().equals(item2.getName())) {
                if (item1.getUpgradedCount() < 1) {
                    item1.setUpgradedCount(item1.getUpgradedCount() + 1);
                } else {
                    item1.setUpgradedCount(0);
                    changeRarity(item1);
                }
                inventory.getItems().replace(item1Id, item1);
                inventory.getItems().remove(item2Id);
                System.out.println("item rarity successfully upgraded.");
            } else {
                throw new IllegalArgumentException("you can only  upgrade the identical items!");
            }
        } else {
            throw new IllegalArgumentException("The item ids wrong or they're from different inventories");
        }
    }

    public static void changeRarity(Item item) {
        if (item.getRarity() == Rarity.LEGENDARY) {
            throw new IllegalArgumentException("The Item" + item.getItemId() + " already has the maximum rarity.");
        } else {
            item.setRarity(item.getRarity().getNextRarity());
        }
    }

    public static String getItemData(Item item) {
        String itemString =  "{ id: " + item.getItemId() + "," +
                "\n name: " + item.getName() + "," +
                "\n rarity: " + item.getRarity() + "," +
                "\n upgradedCount: " + item.getUpgradedCount() + "}";

        System.out.println(itemString);
        return itemString;
    }
}
