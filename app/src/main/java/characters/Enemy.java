package characters;

import java.util.Random;

public abstract class Enemy extends Character {

    //--------------------------------------------------------------------------------------------------- DECLARATIONS
    protected int experienceValue;
    protected int goldDrop;
    protected String enemyType;
    protected Random random;

    // -------------------------------------------------------------------------------------------------- CORE METHODS
    //                                                                                                     CONSTRUCTOR
    public Enemy(String name, int maxHealth, int attack, int defense, int experienceValue, int goldDrop) {
        super(name, maxHealth, attack, defense);
        this.experienceValue = experienceValue;
        this.goldDrop = goldDrop;
        this.random = new Random();
    }

    //---------------------------------------------------------------------------------------------- GETTERS & SETTERS
    public abstract String getAttackMessage();

    public abstract String getDeathMessage();

    public int getAttackDamage() {
        int baseDamage = this.attack;
        int variance = (int) (baseDamage * 0.2); // get attack damage with some variance
        return baseDamage + random.nextInt(variance * 2 + 1) - variance;
    }

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