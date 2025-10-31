package items;

public class Weapon extends Item {
    private int attackBonus;

    public Weapon(String name, int attackBonus, String description, Rarity rarity, int value) {
        super(name, description, ItemType.WEAPON, rarity, value);
        this.attackBonus = attackBonus;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    @Override
    public boolean use(characters.Player player) {
        // Weapons are equipped, not "used" like consumables
        // This could auto-equip the weapon or return false
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s (ATK +%d) [%s]", getName(), attackBonus, getRarity());
    }
}