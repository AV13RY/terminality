package characters;

import java.util.Random;

public abstract class Enemy extends Character {
    protected int experienceValue;
    protected int goldDrop;
    protected String enemyType;
    protected Random random;

    public Enemy(String name, int maxHealth, int attack, int defense, int experienceValue, int goldDrop) {
        super(name, maxHealth, attack, defense);
        this.experienceValue = experienceValue;
        this.goldDrop = goldDrop;
        this.random = new Random();
    }

    // Get attack damage with some randomness
    public int getAttackDamage() {
        int baseDamage = this.attack;
        int variance = (int) (baseDamage * 0.2); // 20% variance
        return baseDamage + random.nextInt(variance * 2 + 1) - variance;
    }

    // Enemy-specific attack patterns
    public abstract String getAttackMessage();

    // Death message
    public abstract String getDeathMessage();

    // Getters
    public int getExperienceValue() {
        return experienceValue;
    }

    public int getGoldDrop() {
        return goldDrop;
    }

    public String getEnemyType() {
        return enemyType;
    }
}