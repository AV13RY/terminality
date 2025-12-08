package characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Skeleton Tests")
class SkeletonTest {

    private Skeleton skeleton;

    @BeforeEach
    public void setUp() {
        skeleton = new Skeleton();
    }

    @Test
    @DisplayName("Should initialize Skeleton with correct stats")
    void shouldInitializeSkeletonWithCorrectStats() {
        assertEquals("Skeleton", skeleton.getName());
        assertEquals(40, skeleton.getMaxHealth());
        assertEquals(40, skeleton.getCurrentHealth());
        assertEquals(10, skeleton.getAttack());
        assertEquals(5, skeleton.getDefense());
        assertEquals(25, skeleton.getExperienceValue());
        assertEquals(15, skeleton.getGoldDrop());
        assertEquals("Skeleton", skeleton.getEnemyType());
        assertFalse(skeleton.isDead());
    }

    @Test
    @DisplayName("Should return attack message containing 'skeleton'")
    void shouldReturnAttackMessageContainingSkeleton() {
        String message = skeleton.getAttackMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("skeleton") || message.toLowerCase().contains("bones"));
    }

    @Test
    @DisplayName("Should return death message containing 'skeleton' or 'bones'")
    void shouldReturnDeathMessageContainingSkeletonOrBones() {
        String message = skeleton.getDeathMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("skeleton") || message.toLowerCase().contains("bones"));
    }

    @Test
    @DisplayName("Should return attack damage within variance range")
    void shouldReturnAttackDamageWithinVarianceRange() {
        int baseAttack = 10;
        int variance = (int) (baseAttack * 0.2);
        int minDamage = baseAttack - variance;
        int maxDamage = baseAttack + variance;

        for (int i = 0; i < 20; i++) {
            int damage = skeleton.getAttackDamage();
            assertTrue(damage >= minDamage && damage <= maxDamage,
                    "Damage " + damage + " should be between " + minDamage + " and " + maxDamage);
        }
    }

    @Test
    @DisplayName("Should vary attack messages across multiple calls")
    void shouldVaryAttackMessagesAcrossMultipleCalls() {
        String firstMessage = skeleton.getAttackMessage();
        boolean foundDifferent = false;

        for (int i = 0; i < 20; i++) {
            String message = skeleton.getAttackMessage();
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
        String firstMessage = skeleton.getDeathMessage();
        boolean foundDifferent = false;

        for (int i = 0; i < 20; i++) {
            String message = skeleton.getDeathMessage();
            if (!message.equals(firstMessage)) {
                foundDifferent = true;
                break;
            }
        }

        assertTrue(foundDifferent, "Death messages should vary");
    }
}
