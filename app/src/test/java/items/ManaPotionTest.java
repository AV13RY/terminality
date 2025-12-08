package items;

import characters.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Mana Potion Tests")
class ManaPotionTest {

    private ManaPotion commonPotion;
    private ManaPotion rarePotion;
    private ManaPotion legendaryPotion;
    private Player player;

    @BeforeEach
    public void setUp() {
        commonPotion = new ManaPotion(Item.Rarity.COMMON);
        rarePotion = new ManaPotion(Item.Rarity.RARE);
        legendaryPotion = new ManaPotion(Item.Rarity.LEGENDARY);
        player = new Player("TestPlayer", Player.MAGE);
    }

    @Test
    @DisplayName("Should initialize with correct properties based on rarity")
    void shouldInitializeWithCorrectPropertiesBasedOnRarity() {
        // Common potion
        assertEquals("Mana Potion", commonPotion.getName());
        assertEquals("A blue potion that restores a small amount of mana.", commonPotion.getDescription());
        assertEquals(Item.ItemType.CONSUMABLE, commonPotion.getType());
        assertEquals(Item.Rarity.COMMON, commonPotion.getRarity());
        assertEquals(20, commonPotion.getValue());
        assertEquals(20, commonPotion.getManaAmount());

        // Rare potion
        assertEquals("Superior Mana Potion", rarePotion.getName());
        assertEquals("A brilliant azure potion that restores a large amount of mana.", rarePotion.getDescription());
        assertEquals(Item.ItemType.CONSUMABLE, rarePotion.getType());
        assertEquals(Item.Rarity.RARE, rarePotion.getRarity());
        assertEquals(80, rarePotion.getValue());
        assertEquals(80, rarePotion.getManaAmount());

        // Legendary potion
        assertEquals("Elixir of Magic", legendaryPotion.getName());
        assertEquals("A divine elixir that fully restores mana.", legendaryPotion.getDescription());
        assertEquals(Item.ItemType.CONSUMABLE, legendaryPotion.getType());
        assertEquals(Item.Rarity.LEGENDARY, legendaryPotion.getRarity());
        assertEquals(400, legendaryPotion.getValue());
        assertEquals(9999, legendaryPotion.getManaAmount());
    }

    @ParameterizedTest
    @EnumSource(Item.Rarity.class)
    @DisplayName("Should have correct properties for each rarity")
    void shouldHaveCorrectPropertiesForEachRarity(Item.Rarity rarity) {
        ManaPotion potion = new ManaPotion(rarity);
        
        assertNotNull(potion.getName());
        assertNotNull(potion.getDescription());
        assertEquals(Item.ItemType.CONSUMABLE, potion.getType());
        assertEquals(rarity, potion.getRarity());
        assertTrue(potion.getValue() > 0);
        assertTrue(potion.getManaAmount() > 0);
    }

    @Test
    @DisplayName("Should restore mana when not at full mana")
    void shouldRestoreManaWhenNotAtFullMana() {
        // Use some mana (mage class has mana)
        player.castSpell(30); // This uses mana
        int initialMana = player.getMana();
        
        // Use common potion (restores 20)
        boolean result = commonPotion.use(player);
        
        assertTrue(result);
        assertEquals(initialMana + 20, player.getMana());
    }

    @Test
    @DisplayName("Should not restore mana when at full mana")
    void shouldNotRestoreManaWhenAtFullMana() {
        // Player starts at full mana
        int initialMana = player.getMana();
        
        // Try to use potion
        boolean result = commonPotion.use(player);
        
        assertFalse(result);
        assertEquals(initialMana, player.getMana());
    }

    @Test
    @DisplayName("Should not restore mana beyond max mana")
    void shouldNotRestoreManaBeyondMaxMana() {
        // Use some mana
        player.castSpell(10);
        int maxMana = player.getMaxMana();
        
        // Use legendary potion (restores 9999)
        boolean result = legendaryPotion.use(player);
        
        assertTrue(result);
        assertEquals(maxMana, player.getMana());
    }

    @Test
    @DisplayName("Should not work on classes with no mana")
    void shouldNotWorkOnClassesWithNoMana() {
        Player warrior = new Player("Warrior", Player.KNIGHT); // Assuming knights have no mana
        
        boolean result = commonPotion.use(warrior);
        
        assertFalse(result);
    }

    @Test
    @DisplayName("Should have correct mana amounts for each rarity")
    void shouldHaveCorrectManaAmountsForEachRarity() {
        ManaPotion common = new ManaPotion(Item.Rarity.COMMON);
        ManaPotion uncommon = new ManaPotion(Item.Rarity.UNCOMMON);
        ManaPotion rare = new ManaPotion(Item.Rarity.RARE);
        ManaPotion legendary = new ManaPotion(Item.Rarity.LEGENDARY);
        
        assertEquals(20, common.getManaAmount());
        assertEquals(40, uncommon.getManaAmount());
        assertEquals(80, rare.getManaAmount());
        assertEquals(9999, legendary.getManaAmount());
    }

    @Test
    @DisplayName("Should have correct values for each rarity")
    void shouldHaveCorrectValuesForEachRarity() {
        ManaPotion common = new ManaPotion(Item.Rarity.COMMON);
        ManaPotion uncommon = new ManaPotion(Item.Rarity.UNCOMMON);
        ManaPotion rare = new ManaPotion(Item.Rarity.RARE);
        ManaPotion legendary = new ManaPotion(Item.Rarity.LEGENDARY);
        
        assertEquals(20, common.getValue());
        assertEquals(40, uncommon.getValue());
        assertEquals(80, rare.getValue());
        assertEquals(400, legendary.getValue());
    }

    @Test
    @DisplayName("Should format toString correctly")
    void shouldFormatToStringCorrectly() {
        String result = commonPotion.toString();
        assertTrue(result.contains("Mana Potion"));
        assertTrue(result.contains("Common"));
        
        String rareResult = rarePotion.toString();
        assertTrue(rareResult.contains("Superior Mana Potion"));
        assertTrue(rareResult.contains("Rare"));
    }

    @Test
    @DisplayName("Should have correct item type")
    void shouldHaveCorrectItemType() {
        assertEquals(Item.ItemType.CONSUMABLE, commonPotion.getType());
        assertEquals(Item.ItemType.CONSUMABLE, rarePotion.getType());
        assertEquals(Item.ItemType.CONSUMABLE, legendaryPotion.getType());
    }

    @Test
    @DisplayName("Should work with multiple uses on player with low mana")
    void shouldWorkWithMultipleUsesOnPlayerWithLowMana() {
        // Use significant amount of mana
        player.castSpell(100);
        int initialMana = player.getMana();
        
        // Use multiple potions
        ManaPotion potion1 = new ManaPotion(Item.Rarity.COMMON);
        ManaPotion potion2 = new ManaPotion(Item.Rarity.UNCOMMON);
        
        boolean result1 = potion1.use(player);
        assertTrue(result1);
        
        boolean result2 = potion2.use(player);
        assertTrue(result2);
        
        assertEquals(initialMana + 20 + 40, player.getMana());
    }

    @Test
    @DisplayName("Should have correct names for each rarity")
    void shouldHaveCorrectNamesForEachRarity() {
        ManaPotion common = new ManaPotion(Item.Rarity.COMMON);
        ManaPotion uncommon = new ManaPotion(Item.Rarity.UNCOMMON);
        ManaPotion rare = new ManaPotion(Item.Rarity.RARE);
        ManaPotion legendary = new ManaPotion(Item.Rarity.LEGENDARY);
        
        assertEquals("Mana Potion", common.getName());
        assertEquals("Greater Mana Potion", uncommon.getName());
        assertEquals("Superior Mana Potion", rare.getName());
        assertEquals("Elixir of Magic", legendary.getName());
    }
}
