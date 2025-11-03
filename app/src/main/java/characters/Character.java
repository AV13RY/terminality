package characters;

public abstract class Character {

    //--------------------------------------------------------------------------------------------------- DECLARATIONS
    //                                                                                       GENERAL "CHARACTER" STATS
    protected String name;
    protected int maxHealth;
    protected int currentHealth;
    protected int attack;
    protected int defense;
    protected boolean isAlive;

    // -------------------------------------------------------------------------------------------------- CORE METHODS
    //                                                                                                     CONSTRUCTOR
    public Character(String name, int maxHealth, int attack, int defense) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.isAlive = true;
    }

    //                                                                                                   TAKING DAMAGE
    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - defense);
        currentHealth -= actualDamage;
        if (currentHealth <= 0) {
            currentHealth = 0;
            isAlive = false;
        }
    }

    //                                                                                           CHECKING ALIVE STATUS
    public boolean isDead() {
        return !isAlive;
    }

    //                                                                                                         HEALING
    public void heal(int amount) {
        currentHealth = Math.min(currentHealth + amount, maxHealth);
    }


    // --------------------------------------------------------------------------------------------- GETTERS & SETTERS

    public String getName() {
        return name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getStatus() {
        return String.format("%s - HP: %d/%d | ATK: %d | DEF: %d", name, currentHealth, maxHealth, attack, defense);
    }
}
