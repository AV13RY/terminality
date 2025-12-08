package characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Boss Tests")
class BossTest {

    private Boss boss;

    @BeforeEach
    public void setUp() {
        boss = new Boss("Ancient Guardian", 200, 30);
    }

    @Test
    @DisplayName("Should initialize Boss with correct stats")
    void shouldInitializeBossWithCorrectStats() {
        assertEquals("Ancient Guardian", boss.getName());
        assertEquals(200, boss.getMaxHealth());
        assertEquals(200, boss.getCurrentHealth());
        assertEquals(30, boss.getAttack());
        assertEquals(12, boss.getDefense());
        assertEquals(200, boss.getExperienceValue());
        assertEquals(100, boss.getGoldDrop());
        assertEquals("Boss", boss.getEnemyType());
        assertEquals(1, boss.getPhase());
        assertFalse(boss.isDead());
    }

    @Test
    @DisplayName("Should start in phase 1")
    void shouldStartInPhase1() {
        assertEquals(1, boss.getPhase());
    }

    @Test
    @DisplayName("Should transition to phase 2 at 50% health")
    void shouldTransitionToPhase2At50PercentHealth() {
        assertEquals(1, boss.getPhase());

        boss.takeDamage(112);

        assertEquals(2, boss.getPhase());
        assertEquals(100, boss.getCurrentHealth());
    }

    @Test
    @DisplayName("Should transition to phase 2 when health drops below 50%")
    void shouldTransitionToPhase2BelowHalfHealth() {
        assertEquals(1, boss.getPhase());

        boss.takeDamage(120);

        assertEquals(2, boss.getPhase());
        assertTrue(boss.getCurrentHealth() < 100);
    }

    @Test
    @DisplayName("Should remain in phase 2 after transition")
    void shouldRemainInPhase2AfterTransition() {
        boss.takeDamage(120);
        assertEquals(2, boss.getPhase());

        boss.takeDamage(10);
        assertEquals(2, boss.getPhase());
    }

    @Test
    @DisplayName("Should not transition to phase 2 above 50% health")
    void shouldNotTransitionToPhase2Above50PercentHealth() {
        boss.takeDamage(50);

        assertEquals(1, boss.getPhase());
        assertTrue(boss.getCurrentHealth() > 100);
    }

    @Test
    @DisplayName("Should return attack damage within variance range in phase 1")
    void shouldReturnAttackDamageInPhase1() {
        int baseAttack = 30;
        int variance = (int) (baseAttack * 0.2);
        int minDamage = baseAttack - variance;
        int maxDamage = baseAttack + variance;

        for (int i = 0; i < 20; i++) {
            int damage = boss.getAttackDamage();
            assertTrue(damage >= minDamage && damage <= maxDamage,
                    "Phase 1 damage " + damage + " should be between " + minDamage + " and " + maxDamage);
        }
    }

    @Test
    @DisplayName("Should deal more damage in phase 2")
    void shouldDealMoreDamageInPhase2() {
        boss.takeDamage(120);
        assertEquals(2, boss.getPhase());

        int baseAttack = 40;
        int variance = (int) (baseAttack * 0.2);
        int minDamage = baseAttack - variance;
        int maxDamage = baseAttack + variance;

        boolean foundNormalAttack = false;
        for (int i = 0; i < 30; i++) {
            int damage = boss.getAttackDamage();
            if (damage >= minDamage && damage <= maxDamage) {
                foundNormalAttack = true;
            }
            assertTrue(damage >= minDamage && damage <= 68,
                    "Phase 2 damage " + damage + " should be reasonable");
        }
        assertTrue(foundNormalAttack, "Should have at least one normal attack");
    }

    @Test
    @DisplayName("Should return phase 1 attack message")
    void shouldReturnPhase1AttackMessage() {
        assertEquals(1, boss.getPhase());
        String message = boss.getAttackMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
    }

    @Test
    @DisplayName("Should return phase 2 attack message")
    void shouldReturnPhase2AttackMessage() {
        boss.takeDamage(120); // Trigger phase 2
        assertEquals(2, boss.getPhase());

        String message = boss.getAttackMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
    }

    @Test
    @DisplayName("Should return death message")
    void shouldReturnDeathMessage() {
        String message = boss.getDeathMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("guardian") || message.toLowerCase().contains("defeated"));
    }

    @Test
    @DisplayName("Should return phase change message")
    void shouldReturnPhaseChangeMessage() {
        String message = boss.getPhaseChangeMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("phase") || message.toLowerCase().contains("guardian"));
    }

    @Test
    @DisplayName("Should vary attack messages in phase 1")
    void shouldVaryAttackMessagesInPhase1() {
        String firstMessage = boss.getAttackMessage();
        boolean foundDifferent = false;

        for (int i = 0; i < 20; i++) {
            String message = boss.getAttackMessage();
            if (!message.equals(firstMessage)) {
                foundDifferent = true;
                break;
            }
        }

        assertTrue(foundDifferent, "Phase 1 attack messages should vary");
    }

    @Test
    @DisplayName("Should vary attack messages in phase 2")
    void shouldVaryAttackMessagesInPhase2() {
        boss.takeDamage(120);
        assertEquals(2, boss.getPhase());

        String firstMessage = boss.getAttackMessage();
        boolean foundDifferent = false;

        for (int i = 0; i < 30; i++) {
            String message = boss.getAttackMessage();
            if (!message.equals(firstMessage)) {
                foundDifferent = true;
                break;
            }
        }

        assertTrue(foundDifferent, "Phase 2 attack messages should vary");
    }

    @Test
    @DisplayName("Boss should die when health reaches zero")
    void bossShouldDieWhenHealthReachesZero() {
        boss.takeDamage(1000);

        assertEquals(0, boss.getCurrentHealth());
        assertTrue(boss.isDead());
    }

    @Test
    @DisplayName("Boss should inherit Character behavior for taking damage")
    void bossShouldInheritCharacterBehaviorForTakingDamage() {
        int initialHealth = boss.getCurrentHealth();

        boss.takeDamage(50);

        assertEquals(initialHealth - 38, boss.getCurrentHealth());
        assertFalse(boss.isDead());
    }
}
