package items;

import characters.Player;

public class Accessory extends Item {

    //--------------------------------------------------------------------------------------------------- DECLARATIONS
    private StatBonus statBonus;
    private int bonusAmount;

    public enum StatBonus {
        ATTACK("Attack", "attack"), DEFENSE("Defense", "defense"), HEALTH("Health", "health"), MANA("Mana", "mana"), CRITICAL("Critical", "critical");

        final String displayName;
        private final String statName;

        StatBonus(String displayName, String statName) {
            this.displayName = displayName;
            this.statName = statName;
        }
    }

    //--------------------------------------------------------------------------------------------------- CORE METHODS
    //                                                                                                     CONSTRUCTOR
    public Accessory(String name, String description, StatBonus statBonus, int bonusAmount, Rarity rarity) {
        super(name, description, ItemType.ACCESSORY, rarity, calculateValue(bonusAmount, rarity));
        this.statBonus = statBonus;
        this.bonusAmount = bonusAmount;
    }

    //                                                                                 CALCULATE VALUE OF AN ACCESSORY
    private static int calculateValue(int bonusAmount, Rarity rarity) {
        int baseValue = bonusAmount * 15;
        return switch (rarity) {
            case COMMON -> baseValue;
            case UNCOMMON -> baseValue * 2;
            case RARE -> baseValue * 4;
            case LEGENDARY -> baseValue * 10;
        };
    }

    @Override
    public boolean use(Player player) {
        return player.equipAccessory(this);
    }

    //-------------------------------------------------------------------------------------------- GETTERS AND SETTERS
    public StatBonus getStatBonus() {
        return statBonus;
    }

    public int getBonusAmount() {
        return bonusAmount;
    }
}
