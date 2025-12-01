package characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Character Tests")
class CharacterTest {

    private Player player;
    private Goblin goblin;
    private Skeleton skeleton;

    @BeforeEach
    public void setUp() {
        player = new Player("TestPlayer", Player.KNIGHT);
        goblin = new Goblin();
        skeleton = new Skeleton();
    }

    //------------------------------------------------------------------------------------------------ CONSTRUCTOR TESTS
    @ParameterizedTest
    @CsvSource({
        "Knight, 120, 12, 10",
        "Mage, 60, 20, 3"
    })
    @DisplayName("Should initialize character with correct stats")
    void shouldInitializeCharacterWithCorrectStats(String className, int expectedHealth, 
                                                    int expectedAttack, int expectedDefense) {
        Player testPlayer = new Player("Test", className);
        
        assertEquals("Test", testPlayer.getName());
        assertEquals(expectedHealth, testPlayer.getMaxHealth());
        assertEquals(expectedHealth, testPlayer.getCurrentHealth());
        assertEquals(expectedAttack, testPlayer.getAttack());
        assertEquals(expectedDefense, testPlayer.getDefense());
        assertFalse(testPlayer.isDead());
    }

    @Test
    @DisplayName("Should initialize enemy with correct stats")
    void shouldInitializeEnemyWithCorrectStats() {
        assertEquals("Goblin", goblin.getName());
        assertEquals(30, goblin.getMaxHealth());
        assertEquals(30, goblin.getCurrentHealth());
        assertEquals(8, goblin.getAttack());
        assertEquals(2, goblin.getDefense());
        assertFalse(goblin.isDead());
    }

    //------------------------------------------------------------------------------------------------ TAKE DAMAGE TESTS
    @ParameterizedTest
    @CsvSource({
        "20, 10",    // damage=20, defense=10 -> actualDamage=10, remaining=110
        "15, 5",     // damage=15, defense=10 -> actualDamage=5, remaining=115
        "5, 1",      // damage=5, defense=10 -> actualDamage=1 (min), remaining=119
        "10, 1"      // damage=10, defense=10 -> actualDamage=1 (min), remaining=119
    })
    @DisplayName("Should take damage correctly with defense calculation")
    void shouldTakeDamageWithDefenseCalculation(int damage, int expectedDamage) {
        Player testPlayer = new Player("Test", Player.KNIGHT); // Knight has 120 HP, 10 defense
        int initialHealth = testPlayer.getCurrentHealth();
        
        testPlayer.takeDamage(damage);
        
        assertEquals(initialHealth - expectedDamage, testPlayer.getCurrentHealth());
        assertFalse(testPlayer.isDead());
    }

    @Test
    @DisplayName("Should ensure minimum 1 damage even with high defense")
    void shouldEnsureMinimumOneDamage() {
        int initialHealth = player.getCurrentHealth();
        
        player.takeDamage(5); // 5 damage vs 10 defense should still deal 1 damage
        
        assertEquals(initialHealth - 1, player.getCurrentHealth());
    }

    @Test
    @DisplayName("Should set character to dead when health reaches zero")
    void shouldSetCharacterToDeadWhenHealthReachesZero() {
        goblin.takeDamage(100); // Massive damage
        
        assertEquals(0, goblin.getCurrentHealth());
        assertTrue(goblin.isDead());
    }

    @Test
    @DisplayName("Should set character to dead when health goes negative")
    void shouldSetCharacterToDeadWhenHealthGoesNegative() {
        skeleton.takeDamage(200); // Overkill damage
        
        assertEquals(0, skeleton.getCurrentHealth());
        assertTrue(skeleton.isDead());
    }

    @Test
    @DisplayName("Should handle exact lethal damage")
    void shouldHandleExactLethalDamage() {
        int exactDamage = goblin.getCurrentHealth() + goblin.getDefense();
        
        goblin.takeDamage(exactDamage);
        
        assertEquals(0, goblin.getCurrentHealth());
        assertTrue(goblin.isDead());
    }

    @Test
    @DisplayName("Should reduce health to zero but not below")
    void shouldReduceHealthToZeroButNotBelow() {
        player.takeDamage(1000); // Massive overkill
        
        assertEquals(0, player.getCurrentHealth());
        assertTrue(player.isDead());
    }

    //---------------------------------------------------------------------------------------------------- IS DEAD TESTS
    @Test
    @DisplayName("Should return false when character is alive")
    void shouldReturnFalseWhenCharacterIsAlive() {
        assertFalse(player.isDead());
        assertFalse(goblin.isDead());
        assertFalse(skeleton.isDead());
    }

    @Test
    @DisplayName("Should return true when character is dead")
    void shouldReturnTrueWhenCharacterIsDead() {
        player.takeDamage(1000);
        
        assertTrue(player.isDead());
    }

    //------------------------------------------------------------------------------------------------------- HEAL TESTS
    @ParameterizedTest
    @CsvSource({
        "50, 30, 43",    // damage=50, actualDmg=47 -> 60-47=13, 13+30=43
        "20, 10, 53",    // damage=20, actualDmg=17 -> 60-17=43, 43+10=53
        "30, 50, 60",    // damage=30, actualDmg=27 -> 60-27=33, 33+50=83 capped at 60
        "10, 5, 58"      // damage=10, actualDmg=7 -> 60-7=53, 53+5=58
    })
    @DisplayName("Should heal character correctly")
    void shouldHealCharacterCorrectly(int damage, int healAmount, int expectedHealth) {
        Player testPlayer = new Player("Test", Player.MAGE); // 60 max health, 3 defense
        testPlayer.takeDamage(damage);
        
        testPlayer.heal(healAmount);
        
        assertEquals(expectedHealth, testPlayer.getCurrentHealth());
    }

    @Test
    @DisplayName("Should not heal beyond max health")
    void shouldNotHealBeyondMaxHealth() {
        goblin.takeDamage(10);
        int healthBefore = goblin.getCurrentHealth();
        
        goblin.heal(100); // Massive heal
        
        assertEquals(goblin.getMaxHealth(), goblin.getCurrentHealth());
        assertTrue(goblin.getCurrentHealth() >= healthBefore);
    }

    @Test
    @DisplayName("Should heal to exact max health")
    void shouldHealToExactMaxHealth() {
        skeleton.takeDamage(20);
        int exactHeal = skeleton.getMaxHealth() - skeleton.getCurrentHealth();
        
        skeleton.heal(exactHeal);
        
        assertEquals(skeleton.getMaxHealth(), skeleton.getCurrentHealth());
    }

    @Test
    @DisplayName("Should heal from critical health")
    void shouldHealFromCriticalHealth() {
        // Player has 120 HP, 10 defense. To leave at 1 HP, need to deal 119 actual damage
        // actualDamage = max(1, damage - 10), so need damage = 129 to deal 119
        player.takeDamage(129); // Leaves at 1 HP
        assertEquals(1, player.getCurrentHealth());
        
        player.heal(50);
        
        assertEquals(51, player.getCurrentHealth());
        assertFalse(player.isDead());
    }

    @Test
    @DisplayName("Heal does not revive dead character")
    void healDoesNotReviveDeadCharacter() {
        goblin.takeDamage(1000); // Kill the goblin
        assertTrue(goblin.isDead());
        assertEquals(0, goblin.getCurrentHealth());
        
        // Note: heal() uses Math.min which will work on 0, resulting in min(0+50, 30) = 30
        // This is expected behavior - heal() doesn't check if character is dead
        goblin.heal(50);
        
        // Health is restored but character remains dead (isAlive flag not changed)
        assertEquals(30, goblin.getCurrentHealth());
        assertTrue(goblin.isDead()); // Still dead because isAlive flag wasn't changed
    }

    //--------------------------------------------------------------------------------------------------- GET STATUS TESTS
    @ParameterizedTest
    @CsvSource({
        "Goblin, 30, 8, 2",
        "Skeleton, 40, 10, 5"
    })
    @DisplayName("Should return correct base status string from Character class")
    void shouldReturnCorrectBaseStatusString(String name, int health, int attack, int defense) {
        Character character = name.equals("Goblin") ? new Goblin() : new Skeleton();
        
        String status = character.getStatus();
        String expected = String.format("%s - HP: %d/%d | ATK: %d | DEF: %d", 
                                        name, health, health, attack, defense);
        
        assertEquals(expected, status);
    }

    @Test
    @DisplayName("Should return Player status with additional class info")
    void shouldReturnPlayerStatusWithClassInfo() {
        // Player.getStatus() overrides Character.getStatus() to add class info
        String status = player.getStatus();
        
        assertTrue(status.contains("TestPlayer"));
        assertTrue(status.contains("HP: 120/120"));
        assertTrue(status.contains("ATK: 12"));
        assertTrue(status.contains("DEF: 10"));
        assertTrue(status.contains("Class: Knight"));
        assertTrue(status.contains("Level: 1"));
    }

    @Test
    @DisplayName("Should return status with reduced health")
    void shouldReturnStatusWithReducedHealth() {
        goblin.takeDamage(10);
        
        String status = goblin.getStatus();
        
        assertTrue(status.contains("Goblin"));
        assertTrue(status.contains("HP: 22/30")); // 30 - max(1, 10-2) = 30 - 8 = 22
        assertTrue(status.contains("ATK: 8"));
        assertTrue(status.contains("DEF: 2"));
    }

    @Test
    @DisplayName("Should return status when dead")
    void shouldReturnStatusWhenDead() {
        skeleton.takeDamage(1000);
        
        String status = skeleton.getStatus();
        
        assertTrue(status.contains("Skeleton"));
        assertTrue(status.contains("HP: 0/40"));
        assertTrue(status.contains("ATK: 10"));
        assertTrue(status.contains("DEF: 5"));
    }

    //------------------------------------------------------------------------------------------------------ GETTER TESTS
    @Test
    @DisplayName("Should get name correctly")
    void shouldGetNameCorrectly() {
        assertEquals("TestPlayer", player.getName());
        assertEquals("Goblin", goblin.getName());
        assertEquals("Skeleton", skeleton.getName());
    }

    @Test
    @DisplayName("Should get max health correctly")
    void shouldGetMaxHealthCorrectly() {
        assertEquals(120, player.getMaxHealth());
        assertEquals(30, goblin.getMaxHealth());
        assertEquals(40, skeleton.getMaxHealth());
    }

    @Test
    @DisplayName("Should get current health correctly")
    void shouldGetCurrentHealthCorrectly() {
        assertEquals(120, player.getCurrentHealth());
        
        player.takeDamage(20);
        assertEquals(110, player.getCurrentHealth());
    }

    @Test
    @DisplayName("Should get attack correctly")
    void shouldGetAttackCorrectly() {
        assertEquals(12, player.getAttack());
        assertEquals(8, goblin.getAttack());
        assertEquals(10, skeleton.getAttack());
    }

    @Test
    @DisplayName("Should get defense correctly")
    void shouldGetDefenseCorrectly() {
        assertEquals(10, player.getDefense());
        assertEquals(2, goblin.getDefense());
        assertEquals(5, skeleton.getDefense());
    }
}
