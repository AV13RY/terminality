package items;

import characters.Player;

public abstract class Item {

    //--------------------------------------------------------------------------------------------------- DECLARATIONS
    protected String name;
    protected String description;
    protected ItemType type;
    protected Rarity rarity;
    protected int value; // Gold value

    public enum ItemType {
        WEAPON, ARMOR, CONSUMABLE, ACCESSORY, KEY_ITEM
    }

    public enum Rarity {
        COMMON("Common", 0.6), UNCOMMON("Uncommon", 0.3), RARE("Rare", 0.08), LEGENDARY("Legendary", 0.02);

        private final String name;
        private final double dropChance;

        Rarity(String name, double dropChance) {
            this.name = name;
            this.dropChance = dropChance;
        }

        public String getName() {
            return name;
        }

        public double getDropChance() {
            return dropChance;
        }
    }

    //--------------------------------------------------------------------------------------------------- CORE METHODS
    //                                                                                                     CONSTRUCTOR
    public Item(String name, String description, ItemType type, Rarity rarity, int value) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.rarity = rarity;
        this.value = value;
    }

    //                                                                                         USE AN ITEM ON A PLAYER
    public abstract boolean use(Player player);

    @Override
    public String toString() {
        return name + " (" + rarity.getName() + ")";
    }

    //-------------------------------------------------------------------------------------------- GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public int getValue() {
        return value;
    }
}