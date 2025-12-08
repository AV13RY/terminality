package characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Orc Tests")
class OrcTest {

    private Orc orc;

    @BeforeEach
    public void setUp() {
        orc = new Orc();
    }

    @Test
    @DisplayName("Should initialize Orc with correct stats")
    void shouldInitializeOrcWithCorrectStats() {
        assertEquals("Orc Warrior", orc.getName());
        assertEquals(60, orc.getMaxHealth());
        assertEquals(60, orc.getCurrentHealth());
        assertEquals(15, orc.getAttack());
        assertEquals(8, orc.getDefense());
        assertEquals(40, orc.getExperienceValue());
        assertEquals(25, orc.getGoldDrop());
        assertEquals("Orc", orc.getEnemyType());
        assertFalse(orc.isDead());
    }

    @Test
    @DisplayName("Should return attack message containing 'orc'")
    void shouldReturnAttackMessageContainingOrc() {
        String message = orc.getAttackMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("orc"));
    }

    @Test
    @DisplayName("Should return death message containing 'orc'")
    void shouldReturnDeathMessageContainingOrc() {
        String message = orc.getDeathMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("orc"));
    }

    @Test
    @DisplayName("Should return attack damage within variance range")
    void shouldReturnAttackDamageWithinVarianceRange() {
        int baseAttack = 15;
        int variance = (int) (baseAttack * 0.2);
        int minDamage = baseAttack - variance;
        int maxDamage = baseAttack + variance;

        for (int i = 0; i < 20; i++) {
            int damage = orc.getAttackDamage();
            assertTrue(damage >= minDamage && damage <= maxDamage,
                    "Damage " + damage + " should be between " + minDamage + " and " + maxDamage);
        }
    }

    @Test
    @DisplayName("Should vary attack messages across multiple calls")
    void shouldVaryAttackMessagesAcrossMultipleCalls() {
        String firstMessage = orc.getAttackMessage();
        boolean foundDifferent = false;

        for (int i = 0; i < 20; i++) {
            String message = orc.getAttackMessage();
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
        String firstMessage = orc.getDeathMessage();
        boolean foundDifferent = false;

        for (int i = 0; i < 20; i++) {
            String message = orc.getDeathMessage();
            if (!message.equals(firstMessage)) {
                foundDifferent = true;
                break;
            }
        }

        assertTrue(foundDifferent, "Death messages should vary");
    }
}
