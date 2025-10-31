package characters;

public class Orc extends Enemy {
    private static final String[] ATTACK_MESSAGES = {"The orc swings its massive club!", "With a roar, the orc charges at you!", "The orc's brutal strike shakes the ground!"};

    private static final String[] DEATH_MESSAGES = {"The mighty orc falls with a thunderous crash.", "The orc's roar fades as it collapses.", "Defeated, the orc warrior breathes no more."};

    public Orc() {
        super("Orc Warrior", 60, 15, 8, 40, 25);
        this.enemyType = "Orc";
    }

    @Override
    public String getAttackMessage() {
        return ATTACK_MESSAGES[random.nextInt(ATTACK_MESSAGES.length)];
    }

    @Override
    public String getDeathMessage() {
        return DEATH_MESSAGES[random.nextInt(DEATH_MESSAGES.length)];
    }
}