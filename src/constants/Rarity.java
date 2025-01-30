package constants;

public enum Rarity {
    COMMON,
    RARE,
    GREAT,
    EPIC,
    EPIC_1,
    EPIC_2,
    LEGENDARY;

    public Rarity getNextRarity(){
        Rarity[] values = Rarity.values();
        int id = (this.ordinal() + 1) % values.length;
        return values[id];
    }
}
