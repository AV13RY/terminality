package characters;

public abstract class Character {
    // Base stats all characters need
    protected String name;
    protected int maxHealth;
    protected int currentHealth;
    protected int attack;
    protected int defense;
    protected boolean isAlive;

    // Constructor
    public Character(String name, int maxHealth, int attack, int defense) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.isAlive = true;
    }

    // Core combat methods
    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - defense);
        currentHealth -= actualDamage;
        if (currentHealth <= 0) {
            currentHealth = 0;
            isAlive = false;
        }
    }

    public int calculateDamage() {
        return attack;
    }

    // Getters and setters
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

    public boolean isAlive() {
        return isAlive;
    }

    // Heal method with max health cap
    public void heal(int amount) {
        currentHealth = Math.min(currentHealth + amount, maxHealth);
    }

    // Status display
    public String getStatus() {
        return String.format("%s - HP: %d/%d | ATK: %d | DEF: %d", name, currentHealth, maxHealth, attack, defense);
    }
}
