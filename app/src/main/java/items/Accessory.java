package items;

import characters.Player;

public class Accessory extends Item {
    private StatBonus statBonus;

    public enum StatBonus {
        ATTACK("Attack", "attack"), DEFENSE("Defense", "defense"), HEALTH("Health", "health"), MANA("Mana", "mana"), CRITICAL("Critical", "critical");

        final String displayName;
        private final String statName;

        StatBonus(String displayName, String statName) {
            this.displayName = displayName;
            this.statName = statName;
        }
    }

    private int bonusAmount;

    public Accessory(String name, String description, StatBonus statBonus, int bonusAmount, Rarity rarity) {
        super(name, description, ItemType.ACCESSORY, rarity, calculateValue(bonusAmount, rarity));
        this.statBonus = statBonus;
        this.bonusAmount = bonusAmount;
    }

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

    public StatBonus getStatBonus() {
        return statBonus;
    }

    public int getBonusAmount() {
        return bonusAmount;
    }
}
