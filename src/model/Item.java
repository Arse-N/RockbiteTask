package model;

import constants.Rarity;

public class Item {

    private String itemId;
    private String name;
    private Rarity rarity;
    private int upgradedCount;

    public Item(String itemId, String name, Rarity rarity) {
        this.itemId = itemId;
        this.name = name;
        this.rarity = rarity;
        this.upgradedCount = 0;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public int getUpgradedCount() {
        return upgradedCount;
    }

    public void setUpgradedCount(int upgradedCount) {
        this.upgradedCount = upgradedCount;
    }
}
