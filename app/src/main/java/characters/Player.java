package characters;

import items.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player extends Character {
    // Player-specific attributes
    private String playerClass;
    private int mana;
    private int maxMana;
    private int level;
    private int experience;
    private List<Weapon> inventory;
    private Weapon equippedWeapon;

    // Class constants for different player types
    public static final String KNIGHT = "Knight";
    public static final String MAGE = "Mage";
    public static final String REAPER = "reaper";

    // Constructor
    public Player(String name, String playerClass) {
        super(name, 0, 0, 0); // Initialize with base values, will be set by class
        this.playerClass = playerClass;
        this.level = 1;
        this.experience = 0;
        this.inventory = new ArrayList<>();
        this.equippedWeapon = null;

        // Set stats based on chosen class
        initializeClassStats();
    }

    // Initialize stats based on player class
    private void initializeClassStats() {
        switch (playerClass) {
            case KNIGHT:
                this.maxHealth = 120;
                this.currentHealth = 120;
                this.attack = 15;
                this.defense = 10;
                this.maxMana = 0;
                this.mana = 0;
                break;

            case MAGE:
                this.maxHealth = 60;
                this.currentHealth = 60;
                this.attack = 5;
                this.defense = 3;
                this.maxMana = 100;
                this.mana = 100;
                break;

            case REAPER:
                this.maxHealth = 80;
                this.currentHealth = 80;
                this.attack = 12;
                this.defense = 5;
                this.maxMana = 30;
                this.mana = 30;
                break;

            default:
                // Default to Knight stats if invalid class
                this.maxHealth = 100;
                this.currentHealth = 100;
                this.attack = 10;
                this.defense = 8;
                this.maxMana = 0;
                this.mana = 0;
                break;
        }
    }

    // Magic attack method (for Mage and reaper)
    public int castSpell(int manaCost) {
        if (mana >= manaCost) {
            mana -= manaCost;
            // Magic damage calculation based on class
            if (playerClass.equals(MAGE)) {
                return 25; // Powerful magic for mage
            } else if (playerClass.equals(REAPER)) {
                return 10; // Little magic for reaper
            }
        }
        return 0; // No damage if not enough mana or no magic ability
    }

    // Restore mana
    public void restoreMana(int amount) {
        mana = Math.min(mana + amount, maxMana);
    }

    // Level up method
    public void levelUp() {
        level++;
        // Increase stats based on class
        switch (playerClass) {
            case KNIGHT:
                maxHealth += 15;
                currentHealth = maxHealth;
                attack += 3;
                defense += 2;
                break;

            case MAGE:
                maxHealth += 5;
                currentHealth = maxHealth;
                attack += 1;
                defense += 1;
                maxMana += 20;
                mana = maxMana;
                break;

            case REAPER:
                maxHealth += 10;
                currentHealth = maxHealth;
                attack += 2;
                defense += 1;
                maxMana += 5;
                mana = maxMana;
                break;
        }
    }

    // Add experience
    public void gainExperience(int exp) {
        experience += exp;
        // Check if player should level up (every 100 exp)
        while (experience >= 100) {
            experience -= 100;
            levelUp();
        }
    }

    // Override getStatus to include player-specific info
    @Override
    public String getStatus() {
        String baseStatus = super.getStatus();
        String classInfo = String.format("\nClass: %s | Level: %d | EXP: %d/100", playerClass, level, experience);

        if (maxMana > 0) {
            classInfo += String.format(" | MP: %d/%d", mana, maxMana);
        }

        return baseStatus + classInfo;
    }

    // Display detailed status (for the status command)
    public String displayStatus() {
        StringBuilder status = new StringBuilder();
        status.append("\n══════════════════════════════════════\n");
        status.append("           CHARACTER STATUS\n");
        status.append("══════════════════════════════════════\n");
        status.append("Name: ").append(name).append("\n");

        status.append("Class: ").append(playerClass).append("\n");
        status.append("Subclass Weapon: ").append(equippedWeapon != null ? equippedWeapon.getName() : "None").append("\n");
        status.append("Level: ").append(level).append("\n");
        status.append("Experience: ").append(experience).append("/100\n");
        status.append("\nVitals:\n");
        status.append("  Health: ").append(currentHealth).append("/").append(maxHealth).append("\n");
        if (maxMana > 0) {
            status.append("  Mana: ").append(mana).append("/").append(maxMana).append("\n");
        }
        status.append("\nStats:\n");
        status.append("  Attack: ").append(attack).append("\n");
        status.append("  Defense: ").append(defense).append("\n");
        status.append("═══════════════════════════════════════");
        return status.toString();
    }

    // Getters
    public String getPlayerClass() {
        return playerClass;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void addWeapon(Weapon weapon) {
        inventory.add(weapon);
        if (equippedWeapon == null) {
            equippedWeapon = weapon; // Auto-equip if no weapon equipped
        }
    }

    public void equipWeapon(Weapon weapon) {
        if (inventory.contains(weapon)) {
            equippedWeapon = weapon;
        }
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public List<Weapon> getInventory() {
        return inventory;
    }

}
