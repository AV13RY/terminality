package items;

import characters.Player;

public class Armor extends Item {
    private int defenseBonus;
    private ArmorType armorType;

    public enum ArmorType {
        HELMET, CHESTPLATE, LEGGINGS, BOOTS, SHIELD
    }

    public Armor(String name, String description, ArmorType armorType, int defenseBonus, Rarity rarity) {
        super(name, description, ItemType.ARMOR, rarity, calculateValue(defenseBonus, rarity));
        this.armorType = armorType;
        this.defenseBonus = defenseBonus;
    }

    private static int calculateValue(int defenseBonus, Rarity rarity) {
        int baseValue = defenseBonus * 10;
        return switch (rarity) {
            case COMMON -> baseValue;
            case UNCOMMON -> baseValue * 2;
            case RARE -> baseValue * 4;
            case LEGENDARY -> baseValue * 10;
        };
    }

    @Override
    public boolean use(Player player) {
        // Equip the armor
        return player.equipArmor(this);
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    public ArmorType getArmorType() {
        return armorType;
    }
}
