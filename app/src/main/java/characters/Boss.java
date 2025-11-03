package characters;

public class Boss extends Enemy {

    //--------------------------------------------------------------------------------------------------- DECLARATIONS
    private int phase;
    private boolean hasUsedSpecialAttack;

    private static final String[] PHASE1_ATTACKS = {"The Ancient Guardian swings its massive blade!", "Dark energy crackles as the Guardian strikes!", "The Guardian's eyes glow red as it attacks!"};
    private static final String[] PHASE2_ATTACKS = {"The Guardian enters a berserker rage and strikes wildly!", "Shadow tendrils lash out from the Guardian!", "The Guardian channels dark magic into a devastating blow!"};

    //--------------------------------------------------------------------------------------------------- CORE METHODS
    //                                                                                                     CONSTRUCTOR
    public Boss(String name, int maxHealth, int attack) {
        super(name, maxHealth, attack, 12, 200, 100);
        this.enemyType = "Boss";
        this.phase = 1;
        this.hasUsedSpecialAttack = false;
    }

    //---------------------------------------------------------------------------------------------- GETTERS & SETTERS
    //                                                                                                   NON-OVERRIDES

    public String getPhaseChangeMessage() {
        return """
                
                ⚠️ The Ancient Guardian roars with fury! ⚠️
                Dark energy surges through its form as it enters PHASE 2!
                Its attacks become more vicious!""";
    }

    public int getPhase() {
        return phase;
    }

    //                                                                                                       OVERRIDES
    @Override
    public int getAttackDamage() {
        // Boss does more damage in phase 2
        int baseDamage = (phase == 2) ? this.attack + 10 : this.attack;

        // Special attack every 3 turns in phase 2
        if (phase == 2 && !hasUsedSpecialAttack && random.nextInt(3) == 0) {
            hasUsedSpecialAttack = true;
            return (int) (baseDamage * 1.5); // 50% more damage
        }

        hasUsedSpecialAttack = false;
        int variance = (int) (baseDamage * 0.2);
        return baseDamage + random.nextInt(variance * 2 + 1) - variance;
    }

    @Override
    public String getAttackMessage() {
        if (hasUsedSpecialAttack) {
            return "⚡ The Guardian unleashes a DEVASTATING SPECIAL ATTACK! ⚡";
        }

        String[] messages = (phase == 1) ? PHASE1_ATTACKS : PHASE2_ATTACKS;
        return messages[random.nextInt(messages.length)];
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);

        // Enter phase 2 at 50% health
        if (phase == 1 && currentHealth <= maxHealth / 2) {
            phase = 2;
        }
    }

    @Override
    public String getDeathMessage() {
        return "The Ancient Guardian lets out a final roar as dark energy dissipates from its form.\n" + "The chamber shakes as the massive creature falls, defeated at last.\n" + "You have conquered the dungeon's greatest threat!";
    }
}
