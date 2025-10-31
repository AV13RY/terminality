package characters;

public class Goblin extends Enemy {
    private static final String[] ATTACK_MESSAGES = {"The goblin slashes with its rusty dagger!", "The goblin lunges forward with a snarl!", "The goblin swings wildly at you!"};

    private static final String[] DEATH_MESSAGES = {"The goblin lets out a final shriek before collapsing.", "The goblin falls to the ground, defeated.", "With a gurgle, the goblin breathes its last."};

    public Goblin() {
        super("Goblin", 30, 8, 2, 15, 10);
        this.enemyType = "Goblin";
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
