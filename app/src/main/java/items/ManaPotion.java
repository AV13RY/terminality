package items;

import characters.Player;

public class ManaPotion extends Item {

    //--------------------------------------------------------------------------------------------------- DECLARATIONS
    private int manaAmount;

    //--------------------------------------------------------------------------------------------------- CORE METHODS
    //                                                                                                     CONSTRUCTOR
    public ManaPotion(Rarity rarity) {
        super(getNameByRarity(rarity), getDescriptionByRarity(rarity), ItemType.CONSUMABLE, rarity, getValueByRarity(rarity));
        this.manaAmount = getManaAmountByRarity(rarity);
    }

    //                                                                                   USE A MANA POTION ON A PLAYER
    @Override
    public boolean use(Player player) {
        if (player.getMaxMana() == 0) {
            return false; // Can't use mana potions if you have no mana
        }

        if (player.getMana() >= player.getMaxMana()) {
            return false; // Can't use when at full mana
        }

        player.restoreMana(manaAmount);
        return true;
    }

    //-------------------------------------------------------------------------------------------- GETTERS AND SETTERS

    public int getManaAmount() {
        return manaAmount;
    }

    private static String getNameByRarity(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> "Mana Potion";
            case UNCOMMON -> "Greater Mana Potion";
            case RARE -> "Superior Mana Potion";
            case LEGENDARY -> "Elixir of Magic";
        };
    }

    private static String getDescriptionByRarity(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> "A blue potion that restores a small amount of mana.";
            case UNCOMMON -> "A glowing blue potion that restores a moderate amount of mana.";
            case RARE -> "A brilliant azure potion that restores a large amount of mana.";
            case LEGENDARY -> "A divine elixir that fully restores mana.";
        };
    }

    private static int getValueByRarity(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> 20;
            case UNCOMMON -> 40;
            case RARE -> 80;
            case LEGENDARY -> 400;
        };
    }

    private static int getManaAmountByRarity(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> 20;
            case UNCOMMON -> 40;
            case RARE -> 80;
            case LEGENDARY -> 9999; // Full restore
        };
    }
}
