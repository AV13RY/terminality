package items;

import characters.Player;

public class HealthPotion extends Item {

    //--------------------------------------------------------------------------------------------------- DECLARATIONS
    private int healAmount;

    //--------------------------------------------------------------------------------------------------- CORE METHODS
    //                                                                                                     CONSTRUCTOR
    public HealthPotion(Rarity rarity) {
        super(getNameByRarity(rarity), getDescriptionByRarity(rarity), ItemType.CONSUMABLE, rarity, getValueByRarity(rarity));
        this.healAmount = getHealAmountByRarity(rarity);
    }

    @Override
    public boolean use(Player player) {
        if (player.getCurrentHealth() >= player.getMaxHealth()) {
            return false; // Can't use when at full health
        }

        player.heal(healAmount);
        return true; // Item was used successfully
    }

    //-------------------------------------------------------------------------------------------- GETTERS AND SETTERS

    public int getHealAmount() {
        return healAmount;
    }

    private static int getHealAmountByRarity(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> 30;
            case UNCOMMON -> 60;
            case RARE -> 100;
            case LEGENDARY -> 9999; // Full heal
        };
    }

    private static String getNameByRarity(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> "Health Potion";
            case UNCOMMON -> "Greater Health Potion";
            case RARE -> "Superior Health Potion";
            case LEGENDARY -> "Elixir of Life";
        };
    }

    private static String getDescriptionByRarity(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> "A red potion that restores a small amount of health.";
            case UNCOMMON -> "A glowing red potion that restores a moderate amount of health.";
            case RARE -> "A brilliant crimson potion that restores a large amount of health.";
            case LEGENDARY -> "A divine elixir that fully restores health.";
        };
    }

    private static int getValueByRarity(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> 25;
            case UNCOMMON -> 50;
            case RARE -> 100;
            case LEGENDARY -> 500;
        };
    }
}
