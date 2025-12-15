package items;

public class Weapon extends Item {

    //--------------------------------------------------------------------------------------------------- DECLARATIONS
    private int attackBonus;
    private SpellType spellType;
    private int manaCost;

    public enum SpellType {
        NONE("None", 0),                  // no spell
        FIRE("Fireball", 15),             // mage fire staff
        ICE("Frostbolt", 20),             // mage ice staff
        LIFESTEAL("Drain Life", 10),      // reaper scythe
        DEFENSE("Heavenly Defense", 0);   // knight shield buff

        public final String name;
        public final int baseDamage;

        SpellType(String name, int baseDamage) {
            this.name = name;
            this.baseDamage = baseDamage;
        }
    }

    //--------------------------------------------------------------------------------------------------- CORE METHODS
    //                                                                                                     CONSTRUCTOR
    public Weapon(String name, int attackBonus, String description, Rarity rarity, int value) {
        super(name, description, ItemType.WEAPON, rarity, value);
        this.attackBonus = attackBonus;
        this.spellType = SpellType.NONE;
        this.manaCost = 0;
    }

    // constructor with spell
    public Weapon(String name, int attackBonus, String description, Rarity rarity, int value, SpellType spellType, int manaCost) {
        super(name, description, ItemType.WEAPON, rarity, value);
        this.attackBonus = attackBonus;
        this.spellType = spellType;
        this.manaCost = manaCost;
    }

    @Override
    public boolean use(characters.Player player) {
        return false; // weapons are equipped, not used
    }

    @Override
    public String toString() {
        if (spellType != SpellType.NONE) {
            return String.format("%s (ATK +%d, %s) [%s]", getName(), attackBonus, spellType.name, getRarity());
        }
        return String.format("%s (ATK +%d) [%s]", getName(), attackBonus, getRarity());
    }

    //-------------------------------------------------------------------------------------------- GETTERS AND SETTERS
    public int getAttackBonus() {
        return attackBonus;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public int getManaCost() {
        return manaCost;
    }

    public boolean hasSpell() {
        return spellType != SpellType.NONE;
    }
}