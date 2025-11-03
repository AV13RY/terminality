package characters;

public class Skeleton extends Enemy {

    //--------------------------------------------------------------------------------------------------- DECLARATIONS
    private static final String[] ATTACK_MESSAGES = {"The skeleton swings its ancient sword!", "Bones rattle as the skeleton strikes!", "The skeleton's bony fingers claw at you!"};
    private static final String[] DEATH_MESSAGES = {"The skeleton crumbles into a pile of bones.", "With a final rattle, the skeleton falls apart.", "The unholy magic animating the skeleton fades away."};

    //--------------------------------------------------------------------------------------------------- CORE METHODS
    //                                                                                                     CONSTRUCTOR
    public Skeleton() {
        super("Skeleton", 40, 10, 5, 25, 15);
        this.enemyType = "Skeleton";
    }

    //---------------------------------------------------------------------------------------------- GETTERS & SETTERS
    //                                                                                                       OVERRIDES
    @Override
    public String getAttackMessage() {
        return ATTACK_MESSAGES[random.nextInt(ATTACK_MESSAGES.length)];
    }

    @Override
    public String getDeathMessage() {
        return DEATH_MESSAGES[random.nextInt(DEATH_MESSAGES.length)];
    }
}
