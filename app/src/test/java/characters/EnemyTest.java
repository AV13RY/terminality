package characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Enemy Tests")
class EnemyTest {

    private Goblin goblin;
    private Skeleton skeleton;
    private Orc orc;
    private Boss boss;

    @BeforeEach
    public void setUp() {
        goblin = new Goblin();
        skeleton = new Skeleton();
        orc = new Orc();
        boss = new Boss("Ancient Guardian", 200, 30);
    }

    //------------------------------------------------------------------------------------------------ CONSTRUCTOR TESTS
    @ParameterizedTest
    @CsvSource({
        "Goblin, 30, 8, 2, 15, 10",
        "Skeleton, 40, 10, 5, 25, 15",
        "Orc Warrior, 60, 15, 8, 40, 25"
    })
    @DisplayName("Should initialize enemy with correct stats")
    void shouldInitializeEnemyWithCorrectStats(String name, int health, int attack, 
                                                int defense, int exp, int gold) {
        Enemy enemy;
        if (name.equals("Goblin")) enemy = new Goblin();
        else if (name.equals("Skeleton")) enemy = new Skeleton();
        else enemy = new Orc();
        
        assertEquals(name, enemy.getName());
        assertEquals(health, enemy.getMaxHealth());
        assertEquals(health, enemy.getCurrentHealth());
        assertEquals(attack, enemy.getAttack());
        assertEquals(defense, enemy.getDefense());
        assertEquals(exp, enemy.getExperienceValue());
        assertEquals(gold, enemy.getGoldDrop());
        assertFalse(enemy.isDead());
        assertNotNull(enemy.getEnemyType());
    }

    @Test
    @DisplayName("Should initialize boss with correct stats")
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
    }

    //-------------------------------------------------------------------------------------------- EXPERIENCE VALUE TESTS
    @ParameterizedTest
    @CsvSource({
        "15",  // Goblin
        "25",  // Skeleton
        "40"   // Orc
    })
    @DisplayName("Should return correct experience value")
    void shouldReturnCorrectExperienceValue(int expectedExp) {
        Enemy enemy;
        if (expectedExp == 15) enemy = goblin;
        else if (expectedExp == 25) enemy = skeleton;
        else enemy = orc;
        
        assertEquals(expectedExp, enemy.getExperienceValue());
    }

    @Test
    @DisplayName("Boss should return correct experience value")
    void bossShouldReturnCorrectExperienceValue() {
        assertEquals(200, boss.getExperienceValue());
    }

    //------------------------------------------------------------------------------------------------ GOLD DROP TESTS
    @ParameterizedTest
    @CsvSource({
        "10",  // Goblin
        "15",  // Skeleton
        "25"   // Orc
    })
    @DisplayName("Should return correct gold drop")
    void shouldReturnCorrectGoldDrop(int expectedGold) {
        Enemy enemy;
        if (expectedGold == 10) enemy = goblin;
        else if (expectedGold == 15) enemy = skeleton;
        else enemy = orc;
        
        assertEquals(expectedGold, enemy.getGoldDrop());
    }

    @Test
    @DisplayName("Boss should return correct gold drop")
    void bossShouldReturnCorrectGoldDrop() {
        assertEquals(100, boss.getGoldDrop());
    }

    //----------------------------------------------------------------------------------------------- ENEMY TYPE TESTS
    @ParameterizedTest
    @CsvSource({
        "Goblin",
        "Skeleton",
        "Orc"
    })
    @DisplayName("Should return correct enemy type")
    void shouldReturnCorrectEnemyType(String expectedType) {
        Enemy enemy;
        if (expectedType.equals("Goblin")) enemy = goblin;
        else if (expectedType.equals("Skeleton")) enemy = skeleton;
        else enemy = orc;
        
        assertEquals(expectedType, enemy.getEnemyType());
    }

    @Test
    @DisplayName("Boss should return correct enemy type")
    void bossShouldReturnCorrectEnemyType() {
        assertEquals("Boss", boss.getEnemyType());
    }

    //-------------------------------------------------------------------------------------------- ATTACK DAMAGE TESTS
    @Test
    @DisplayName("Should return attack damage within variance range for Goblin")
    void shouldReturnAttackDamageWithinVarianceForGoblin() {
        int baseAttack = goblin.getAttack(); // 8
        int variance = (int) (baseAttack * 0.2); // 1
        int minDamage = baseAttack - variance; // 7
        int maxDamage = baseAttack + variance; // 9
        
        // Test multiple times to ensure variance is working
        for (int i = 0; i < 20; i++) {
            int damage = goblin.getAttackDamage();
            assertTrue(damage >= minDamage && damage <= maxDamage,
                    "Damage " + damage + " should be between " + minDamage + " and " + maxDamage);
        }
    }

    @Test
    @DisplayName("Should return attack damage within variance range for Skeleton")
    void shouldReturnAttackDamageWithinVarianceForSkeleton() {
        int baseAttack = skeleton.getAttack(); // 10
        int variance = (int) (baseAttack * 0.2); // 2
        int minDamage = baseAttack - variance; // 8
        int maxDamage = baseAttack + variance; // 12
        
        for (int i = 0; i < 20; i++) {
            int damage = skeleton.getAttackDamage();
            assertTrue(damage >= minDamage && damage <= maxDamage,
                    "Damage " + damage + " should be between " + minDamage + " and " + maxDamage);
        }
    }

    @Test
    @DisplayName("Should return attack damage within variance range for Orc")
    void shouldReturnAttackDamageWithinVarianceForOrc() {
        int baseAttack = orc.getAttack(); // 15
        int variance = (int) (baseAttack * 0.2); // 3
        int minDamage = baseAttack - variance; // 12
        int maxDamage = baseAttack + variance; // 18
        
        for (int i = 0; i < 20; i++) {
            int damage = orc.getAttackDamage();
            assertTrue(damage >= minDamage && damage <= maxDamage,
                    "Damage " + damage + " should be between " + minDamage + " and " + maxDamage);
        }
    }

    @Test
    @DisplayName("Boss should return attack damage within variance range in phase 1")
    void bossShouldReturnAttackDamageInPhase1() {
        int baseAttack = boss.getAttack(); // 30
        int variance = (int) (baseAttack * 0.2); // 6
        int minDamage = baseAttack - variance; // 24
        int maxDamage = baseAttack + variance; // 36
        
        for (int i = 0; i < 20; i++) {
            int damage = boss.getAttackDamage();
            assertTrue(damage >= minDamage && damage <= maxDamage,
                    "Phase 1 damage " + damage + " should be between " + minDamage + " and " + maxDamage);
        }
    }

    @Test
    @DisplayName("Boss should deal more damage in phase 2")
    void bossShouldDealMoreDamageInPhase2() {
        // Damage boss to trigger phase 2
        boss.takeDamage(120); // Reduce to below 50% health
        assertEquals(2, boss.getPhase());
        
        int baseAttack = boss.getAttack() + 10; // 30 + 10 = 40 in phase 2
        int variance = (int) (baseAttack * 0.2); // 8
        int minDamage = baseAttack - variance; // 32
        int maxDamage = baseAttack + variance; // 48
        
        // Test multiple attacks (some might be special attacks which are higher)
        boolean foundNormalAttack = false;
        for (int i = 0; i < 30; i++) {
            int damage = boss.getAttackDamage();
            if (damage >= minDamage && damage <= maxDamage) {
                foundNormalAttack = true;
            }
            // Special attacks can be up to 1.5x base (60), so max around 68
            assertTrue(damage >= minDamage && damage <= 68,
                    "Phase 2 damage " + damage + " should be reasonable");
        }
        assertTrue(foundNormalAttack, "Should have at least one normal attack");
    }

    //------------------------------------------------------------------------------------------- ATTACK MESSAGE TESTS
    @Test
    @DisplayName("Goblin should return attack message")
    void goblinShouldReturnAttackMessage() {
        String message = goblin.getAttackMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("goblin"));
    }

    @Test
    @DisplayName("Skeleton should return attack message")
    void skeletonShouldReturnAttackMessage() {
        String message = skeleton.getAttackMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("skeleton"));
    }

    @Test
    @DisplayName("Orc should return attack message")
    void orcShouldReturnAttackMessage() {
        String message = orc.getAttackMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("orc"));
    }

    @Test
    @DisplayName("Boss should return phase 1 attack message")
    void bossShouldReturnPhase1AttackMessage() {
        assertEquals(1, boss.getPhase());
        String message = boss.getAttackMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("guardian"));
    }

    @Test
    @DisplayName("Boss should return phase 2 attack message")
    void bossShouldReturnPhase2AttackMessage() {
        boss.takeDamage(120); // Trigger phase 2
        assertEquals(2, boss.getPhase());
        
        String message = boss.getAttackMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
    }

    //-------------------------------------------------------------------------------------------- DEATH MESSAGE TESTS
    @Test
    @DisplayName("Goblin should return death message")
    void goblinShouldReturnDeathMessage() {
        String message = goblin.getDeathMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("goblin"));
    }

    @Test
    @DisplayName("Skeleton should return death message")
    void skeletonShouldReturnDeathMessage() {
        String message = skeleton.getDeathMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("skeleton") || message.toLowerCase().contains("bones"));
    }

    @Test
    @DisplayName("Orc should return death message")
    void orcShouldReturnDeathMessage() {
        String message = orc.getDeathMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("orc"));
    }

    @Test
    @DisplayName("Boss should return death message")
    void bossShouldReturnDeathMessage() {
        String message = boss.getDeathMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("guardian") || message.toLowerCase().contains("defeated"));
    }

    //------------------------------------------------------------------------------------------------- BOSS PHASE TESTS
    @Test
    @DisplayName("Boss should start in phase 1")
    void bossShouldStartInPhase1() {
        assertEquals(1, boss.getPhase());
    }

    @Test
    @DisplayName("Boss should transition to phase 2 at 50% health")
    void bossShouldTransitionToPhase2At50PercentHealth() {
        assertEquals(1, boss.getPhase());
        
        // Deal damage to bring boss to exactly 50% health
        boss.takeDamage(112); // 200 - max(1, 112-12) = 200 - 100 = 100 (50%)
        
        assertEquals(2, boss.getPhase());
        assertEquals(100, boss.getCurrentHealth());
    }

    @Test
    @DisplayName("Boss should transition to phase 2 when health drops below 50%")
    void bossShouldTransitionToPhase2BelowHalfHealth() {
        assertEquals(1, boss.getPhase());
        
        boss.takeDamage(120); // Bring below 50%
        
        assertEquals(2, boss.getPhase());
        assertTrue(boss.getCurrentHealth() < 100);
    }

    @Test
    @DisplayName("Boss should remain in phase 2 after transition")
    void bossShouldRemainInPhase2AfterTransition() {
        boss.takeDamage(120); // Trigger phase 2
        assertEquals(2, boss.getPhase());
        
        boss.takeDamage(10); // Take more damage
        assertEquals(2, boss.getPhase()); // Should still be phase 2
    }

    @Test
    @DisplayName("Boss should return phase change message")
    void bossShouldReturnPhaseChangeMessage() {
        String message = boss.getPhaseChangeMessage();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertTrue(message.toLowerCase().contains("phase") || message.toLowerCase().contains("guardian"));
    }

    //-------------------------------------------------------------------------------------------- RANDOM VARIANCE TESTS
    @Test
    @DisplayName("Attack damage should vary across multiple calls")
    void attackDamageShouldVaryAcrossMultipleCalls() {
        // Test that we get different damage values due to variance
        int firstDamage = goblin.getAttackDamage();
        boolean foundDifferent = false;
        
        for (int i = 0; i < 50; i++) {
            int damage = goblin.getAttackDamage();
            if (damage != firstDamage) {
                foundDifferent = true;
                break;
            }
        }
        
        assertTrue(foundDifferent, "Attack damage should vary due to random variance");
    }

    @Test
    @DisplayName("Attack and death messages should vary for enemies with multiple messages")
    void messagesShouldVaryForEnemiesWithMultipleMessages() {
        String firstAttack = goblin.getAttackMessage();
        boolean foundDifferentAttack = false;
        
        for (int i = 0; i < 20; i++) {
            String attack = goblin.getAttackMessage();
            if (!attack.equals(firstAttack)) {
                foundDifferentAttack = true;
                break;
            }
        }
        
        assertTrue(foundDifferentAttack, "Attack messages should vary");
    }
}
