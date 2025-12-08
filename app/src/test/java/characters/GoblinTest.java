package characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Goblin Tests")
class GoblinTest {

    private Goblin goblin;

    @BeforeEach
    public void setUp() {
        goblin = new Goblin();
    }

    @Test
    @DisplayName("Should initialize Goblin with correct stats")
    void shouldInitializeGoblinWithCorrectStats() {
        assertEquals("Goblin", goblin.getName());
        assertEquals(30, goblin.getMaxHealth());
        assertEquals(30, goblin.getCurrentHealth());
        assertEquals(8, goblin.getAttack());
        assertEquals(2, goblin.getDefense());
        assertEquals(15, goblin.getExperienceValue());
        assertEquals(10, goblin.getGoldDrop());
        assertEquals("Goblin", goblin.getEnemyType());
        assertFalse(goblin.isDead());
    }

    @Test
    @DisplayName("Should return attack message containing 'goblin'")
    void shouldReturnAttackMessageContainingGoblin() {
        String message = goblin.getAttackMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("goblin"));
    }

    @Test
    @DisplayName("Should return death message containing 'goblin'")
    void shouldReturnDeathMessageContainingGoblin() {
        String message = goblin.getDeathMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("goblin"));
    }

    @Test
    @DisplayName("Should return attack damage within variance range")
    void shouldReturnAttackDamageWithinVarianceRange() {
        int baseAttack = 8;
        int variance = (int) (baseAttack * 0.2);
        int minDamage = baseAttack - variance;
        int maxDamage = baseAttack + variance;

        for (int i = 0; i < 20; i++) {
            int damage = goblin.getAttackDamage();
            assertTrue(damage >= minDamage && damage <= maxDamage,
                    "Damage " + damage + " should be between " + minDamage + " and " + maxDamage);
        }
    }

    @Test
    @DisplayName("Should vary attack messages across multiple calls")
    void shouldVaryAttackMessagesAcrossMultipleCalls() {
        String firstMessage = goblin.getAttackMessage();
        boolean foundDifferent = false;

        for (int i = 0; i < 20; i++) {
            String message = goblin.getAttackMessage();
            if (!message.equals(firstMessage)) {
                foundDifferent = true;
                break;
            }
        }

        assertTrue(foundDifferent, "Attack messages should vary");
    }

    @Test
    @DisplayName("Should vary death messages across multiple calls")
    void shouldVaryDeathMessagesAcrossMultipleCalls() {
        String firstMessage = goblin.getDeathMessage();
        boolean foundDifferent = false;

        for (int i = 0; i < 20; i++) {
            String message = goblin.getDeathMessage();
            if (!message.equals(firstMessage)) {
                foundDifferent = true;
                break;
            }
        }

        assertTrue(foundDifferent, "Death messages should vary");
    }
}
