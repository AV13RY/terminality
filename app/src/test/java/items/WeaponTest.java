package items;

import characters.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Weapon Tests")
class WeaponTest {

    private Weapon commonSword;
    private Weapon rareBow;
    private Player player;

    @BeforeEach
    public void setUp() {
        commonSword = new Weapon("Iron Sword", 10, "A basic sword", Item.Rarity.COMMON, 50);
        rareBow = new Weapon("Elven Bow", 25, "A powerful bow", Item.Rarity.RARE, 200);
        player = new Player("TestPlayer", Player.KNIGHT);
    }

    @Test
    @DisplayName("Should initialize weapon with correct stats")
    void shouldInitializeWeaponWithCorrectStats() {
        assertEquals("Iron Sword", commonSword.getName());
        assertEquals("A basic sword", commonSword.getDescription());
        assertEquals(Item.ItemType.WEAPON, commonSword.getType());
        assertEquals(Item.Rarity.COMMON, commonSword.getRarity());
        assertEquals(50, commonSword.getValue());
        assertEquals(10, commonSword.getAttackBonus());
    }

    @ParameterizedTest
    @CsvSource({
        "Dagger, 5, COMMON, 25",
        "Longsword, 15, UNCOMMON, 100",
        "Warhammer, 30, RARE, 250",
        "Excalibur, 50, LEGENDARY, 1000"
    })
    @DisplayName("Should initialize weapons with different rarities")
    void shouldInitializeWeaponsWithDifferentRarities(String name, int attackBonus, Item.Rarity rarity, int value) {
        Weapon weapon = new Weapon(name, attackBonus, "Test weapon", rarity, value);
        
        assertEquals(name, weapon.getName());
        assertEquals(attackBonus, weapon.getAttackBonus());
        assertEquals(rarity, weapon.getRarity());
        assertEquals(value, weapon.getValue());
        assertEquals(Item.ItemType.WEAPON, weapon.getType());
    }

    @Test
    @DisplayName("Should return attack bonus")
    void shouldReturnAttackBonus() {
        assertEquals(10, commonSword.getAttackBonus());
        assertEquals(25, rareBow.getAttackBonus());
    }

    @Test
    @DisplayName("Should return false when using weapon")
    void shouldReturnFalseWhenUsingWeapon() {
        boolean result = commonSword.use(player);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should format toString correctly")
    void shouldFormatToStringCorrectly() {
        String result = commonSword.toString();
        assertTrue(result.contains("Iron Sword"));
        assertTrue(result.contains("ATK +10"));
        assertTrue(result.contains("COMMON"));
    }

    @Test
    @DisplayName("Should have correct item type")
    void shouldHaveCorrectItemType() {
        assertEquals(Item.ItemType.WEAPON, commonSword.getType());
        assertEquals(Item.ItemType.WEAPON, rareBow.getType());
    }
}
