package characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    private Player playerKnight;
    private Player playerMage;
    private Player playerReaper;

    @BeforeEach
    void setUp() {
        playerKnight = new Player("TestKnight", Player.KNIGHT);
        playerMage = new Player("TestMage", Player.MAGE);
        playerReaper = new Player("TestReaper", Player.REAPER);
    }

    @Test
    void testKnightInitialisation() {
        assertEquals("TestKnight", playerKnight.getName());
        assertEquals(120, playerKnight.getMaxHealth());
        assertEquals(120, playerKnight.getCurrentHealth());
        assertEquals(12, playerKnight.getAttack());
        assertEquals(10, playerKnight.getDefense());
        assertEquals(0, playerKnight.getMaxMana());
        assertEquals(0, playerKnight.getMana());
        assertEquals(1, playerKnight.getLevel());
        assertEquals(0, playerKnight.getExperience());
        assertEquals(0, playerKnight.getGold());
    }

    @Test
    void testMageInitialisation() {
        assertEquals("TestMage", playerMage.getName());
        assertEquals(60, playerMage.getMaxHealth());
        assertEquals(60, playerMage.getCurrentHealth());
        assertEquals(20, playerMage.getAttack());
        assertEquals(3, playerMage.getDefense());
        assertEquals(100, playerMage.getMaxMana());
        assertEquals(100, playerMage.getMana());
        assertEquals(1, playerMage.getLevel());
        assertEquals(0, playerMage.getExperience());
        assertEquals(0, playerMage.getGold());
    }

    @Test
    void testReaperInitialisation() {
        assertEquals("TestReaper", playerReaper.getName());
        assertEquals(80, playerReaper.getMaxHealth());
        assertEquals(80, playerReaper.getCurrentHealth());
        assertEquals(15, playerReaper.getAttack());
        assertEquals(5, playerReaper.getDefense());
        assertEquals(30, playerReaper.getMaxMana());
        assertEquals(30, playerReaper.getMana());
        assertEquals(1, playerReaper.getLevel());
        assertEquals(0, playerReaper.getExperience());
        assertEquals(0, playerReaper.getGold());
    }

}
