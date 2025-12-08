package items;

import characters.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Accessory Tests")
class AccessoryTest {

    private Accessory commonRing;
    private Accessory rareAmulet;
    private Player player;

    @BeforeEach
    public void setUp() {
        commonRing = new Accessory("Attack Ring", "A simple ring that boosts attack", Accessory.StatBonus.ATTACK, 5, Item.Rarity.COMMON);
        rareAmulet = new Accessory("Health Amulet", "A mystical amulet that boosts health", Accessory.StatBonus.HEALTH, 30, Item.Rarity.RARE);
        player = new Player("TestPlayer", Player.KNIGHT);
    }

    @Test
    @DisplayName("Should initialize accessory with correct stats")
    void shouldInitializeAccessoryWithCorrectStats() {
        assertEquals("Attack Ring", commonRing.getName());
        assertEquals("A simple ring that boosts attack", commonRing.getDescription());
        assertEquals(Item.ItemType.ACCESSORY, commonRing.getType());
        assertEquals(Item.Rarity.COMMON, commonRing.getRarity());
        assertEquals(75, commonRing.getValue()); // 5 * 15 * 1 (common)
        assertEquals(5, commonRing.getBonusAmount());
        assertEquals(Accessory.StatBonus.ATTACK, commonRing.getStatBonus());
    }

    @ParameterizedTest
    @CsvSource({"DEFENSE, 3, COMMON, 45", "MANA, 20, UNCOMMON, 600", "CRITICAL, 15, RARE, 900", "HEALTH, 50, LEGENDARY, 7500"})
    @DisplayName("Should initialize accessories with different rarities and stats")
    void shouldInitializeAccessoriesWithDifferentRaritiesAndStats(Accessory.StatBonus statBonus, int bonusAmount, Item.Rarity rarity, int expectedValue) {
        Accessory accessory = new Accessory("Test Accessory", "A test accessory", statBonus, bonusAmount, rarity);

        assertEquals(statBonus, accessory.getStatBonus());
        assertEquals(bonusAmount, accessory.getBonusAmount());
        assertEquals(rarity, accessory.getRarity());
        assertEquals(expectedValue, accessory.getValue());
        assertEquals(Item.ItemType.ACCESSORY, accessory.getType());
    }

    @Test
    @DisplayName("Should return correct stat bonus")
    void shouldReturnCorrectStatBonus() {
        assertEquals(Accessory.StatBonus.ATTACK, commonRing.getStatBonus());
        assertEquals(Accessory.StatBonus.HEALTH, rareAmulet.getStatBonus());
    }

    @Test
    @DisplayName("Should return correct bonus amount")
    void shouldReturnCorrectBonusAmount() {
        assertEquals(5, commonRing.getBonusAmount());
        assertEquals(30, rareAmulet.getBonusAmount());
    }

    @Test
    @DisplayName("Should calculate value correctly based on rarity")
    void shouldCalculateValueCorrectlyBasedOnRarity() {
        // Common: bonus * 15
        Accessory common = new Accessory("Common Ring", "A common ring", Accessory.StatBonus.ATTACK, 10, Item.Rarity.COMMON);
        assertEquals(150, common.getValue());

        // Uncommon: bonus * 15 * 2
        Accessory uncommon = new Accessory("Uncommon Ring", "An uncommon ring", Accessory.StatBonus.ATTACK, 10, Item.Rarity.UNCOMMON);
        assertEquals(300, uncommon.getValue());

        // Rare: bonus * 15 * 4
        Accessory rare = new Accessory("Rare Ring", "A rare ring", Accessory.StatBonus.ATTACK, 10, Item.Rarity.RARE);
        assertEquals(600, rare.getValue());

        // Legendary: bonus * 15 * 10
        Accessory legendary = new Accessory("Legendary Ring", "A legendary ring", Accessory.StatBonus.ATTACK, 10, Item.Rarity.LEGENDARY);
        assertEquals(1500, legendary.getValue());
    }

    @Test
    @DisplayName("Should equip accessory when used")
    void shouldEquipAccessoryWhenUsed() {
        // Add accessory to inventory first
        player.addItem(commonRing);
        boolean result = commonRing.use(player);
        assertTrue(result);
    }

    @Test
    @DisplayName("Should format toString correctly")
    void shouldFormatToStringCorrectly() {
        String result = commonRing.toString();
        assertTrue(result.contains("Attack Ring"));
        assertTrue(result.contains("Common"));
    }

    @Test
    @DisplayName("Should have correct item type")
    void shouldHaveCorrectItemType() {
        assertEquals(Item.ItemType.ACCESSORY, commonRing.getType());
        assertEquals(Item.ItemType.ACCESSORY, rareAmulet.getType());
    }

    @Test
    @DisplayName("Should handle all stat bonus types")
    void shouldHandleAllStatBonusTypes() {
        Accessory attackAccessory = new Accessory("Attack", "Boosts attack", Accessory.StatBonus.ATTACK, 5, Item.Rarity.COMMON);
        Accessory defenseAccessory = new Accessory("Defense", "Boosts defense", Accessory.StatBonus.DEFENSE, 5, Item.Rarity.COMMON);
        Accessory healthAccessory = new Accessory("Health", "Boosts health", Accessory.StatBonus.HEALTH, 10, Item.Rarity.COMMON);
        Accessory manaAccessory = new Accessory("Mana", "Boosts mana", Accessory.StatBonus.MANA, 10, Item.Rarity.COMMON);
        Accessory criticalAccessory = new Accessory("Critical", "Boosts critical", Accessory.StatBonus.CRITICAL, 5, Item.Rarity.COMMON);

        assertEquals(Accessory.StatBonus.ATTACK, attackAccessory.getStatBonus());
        assertEquals(Accessory.StatBonus.DEFENSE, defenseAccessory.getStatBonus());
        assertEquals(Accessory.StatBonus.HEALTH, healthAccessory.getStatBonus());
        assertEquals(Accessory.StatBonus.MANA, manaAccessory.getStatBonus());
        assertEquals(Accessory.StatBonus.CRITICAL, criticalAccessory.getStatBonus());
    }
}
