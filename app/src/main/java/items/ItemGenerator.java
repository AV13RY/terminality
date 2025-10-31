package items;

import java.util.Random;

public class ItemGenerator {
    private static final Random random = new Random();

    // Armor name templates
    private static final String[][] ARMOR_NAMES = {
            // COMMON
            {"Leather", "Iron", "Chainmail"},
            // UNCOMMON
            {"Steel", "Reinforced", "Hardened"},
            // RARE
            {"Mithril", "Enchanted", "Blessed"},
            // LEGENDARY
            {"Dragon Scale", "Divine", "Ethereal"}};

    // Accessory names
    private static final String[][] ACCESSORY_NAMES = {
            // COMMON
            {"Ring", "Amulet", "Charm"},
            // UNCOMMON
            {"Signet", "Pendant", "Talisman"},
            // RARE
            {"Artifact", "Relic", "Medallion"},
            // LEGENDARY
            {"Crown", "Orb", "Scepter"}};

    public static Item generateItem(Item.Rarity rarity) {
        Item.ItemType type = selectItemType(rarity);

        return switch (type) {
            case CONSUMABLE -> generateConsumable(rarity);
            case ARMOR -> generateArmor(rarity);
            case ACCESSORY -> generateAccessory(rarity);
            default -> generateConsumable(rarity); // Fallback
        };
    }

    private static Item.ItemType selectItemType(Item.Rarity rarity) {
        double roll = random.nextDouble();

        // Adjust probabilities based on rarity
        if (rarity == Item.Rarity.COMMON || rarity == Item.Rarity.UNCOMMON) {
            if (roll < 0.6) return Item.ItemType.CONSUMABLE;
            else if (roll < 0.85) return Item.ItemType.ARMOR;
            else return Item.ItemType.ACCESSORY;
        } else {
            if (roll < 0.3) return Item.ItemType.CONSUMABLE;
            else if (roll < 0.65) return Item.ItemType.ARMOR;
            else return Item.ItemType.ACCESSORY;
        }
    }

    private static Item generateConsumable(Item.Rarity rarity) {
        // 70% health potion, 30% mana potion
        if (random.nextDouble() < 0.7) {
            return new HealthPotion(rarity);
        } else {
            return new ManaPotion(rarity);
        }
    }

    private static Item generateArmor(Item.Rarity rarity) {
        Armor.ArmorType type = Armor.ArmorType.values()[random.nextInt(Armor.ArmorType.values().length)];

        int rarityIndex = rarity.ordinal();
        String prefix = ARMOR_NAMES[rarityIndex][random.nextInt(ARMOR_NAMES[rarityIndex].length)];
        String name = prefix + " " + type.name().toLowerCase();

        // Calculate defense based on rarity and armor type
        int baseDefense = switch (type) {
            case HELMET -> 2;
            case BOOTS -> 2;
            case LEGGINGS -> 3;
            case CHESTPLATE -> 5;
            case SHIELD -> 4;
        };

        int defenseBonus = baseDefense * (rarityIndex + 1);

        String description = "A " + rarity.getName().toLowerCase() + " " + type.name().toLowerCase() + " that provides " + defenseBonus + " defense.";

        return new Armor(name, description, type, defenseBonus, rarity);
    }

    private static Item generateAccessory(Item.Rarity rarity) {
        Accessory.StatBonus statBonus = Accessory.StatBonus.values()[random.nextInt(Accessory.StatBonus.values().length)];

        int rarityIndex = rarity.ordinal();
        String type = ACCESSORY_NAMES[rarityIndex][random.nextInt(ACCESSORY_NAMES[rarityIndex].length)];
        String name = statBonus.displayName + " " + type;

        // Calculate bonus based on rarity
        int bonusAmount = switch (statBonus) {
            case HEALTH, MANA -> (rarityIndex + 1) * 10;
            case ATTACK, DEFENSE -> (rarityIndex + 1) * 2;
            case CRITICAL -> (rarityIndex + 1) * 5;
        };

        String description = "A mystical " + type.toLowerCase() + " that grants +" + bonusAmount + " " + statBonus.displayName.toLowerCase() + ".";

        return new Accessory(name, description, statBonus, bonusAmount, rarity);
    }

    public static Item.Rarity selectRarity() {
        double roll = random.nextDouble();

        if (roll < Item.Rarity.COMMON.getDropChance()) {
            return Item.Rarity.COMMON;
        } else if (roll < Item.Rarity.COMMON.getDropChance() + Item.Rarity.UNCOMMON.getDropChance()) {
            return Item.Rarity.UNCOMMON;
        } else if (roll < Item.Rarity.COMMON.getDropChance() + Item.Rarity.UNCOMMON.getDropChance() + Item.Rarity.RARE.getDropChance()) {
            return Item.Rarity.RARE;
        } else {
            return Item.Rarity.LEGENDARY;
        }
    }
}
