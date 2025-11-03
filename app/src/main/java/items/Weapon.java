package items;

public class Weapon extends Item {

    //--------------------------------------------------------------------------------------------------- DECLARATIONS
    private int attackBonus;

    //--------------------------------------------------------------------------------------------------- CORE METHODS
    //                                                                                                     CONSTRUCTOR
    public Weapon(String name, int attackBonus, String description, Rarity rarity, int value) {
        super(name, description, ItemType.WEAPON, rarity, value);
        this.attackBonus = attackBonus;
    }

    @Override
    public boolean use(characters.Player player) {
        return false; // returns false if the weapon cannot be equipped.
    }

    @Override
    public String toString() {
        return String.format("%s (ATK +%d) [%s]", getName(), attackBonus, getRarity());
    }

    //-------------------------------------------------------------------------------------------- GETTERS AND SETTERS
    public int getAttackBonus() {
        return attackBonus;
    }
}