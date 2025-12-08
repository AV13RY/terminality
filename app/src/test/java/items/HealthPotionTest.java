package items;

import characters.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Health Potion Tests")
class HealthPotionTest {

    private HealthPotion commonPotion;
    private HealthPotion rarePotion;
    private HealthPotion legendaryPotion;
    private Player player;

    @BeforeEach
    public void setUp() {
        commonPotion = new HealthPotion(Item.Rarity.COMMON);
        rarePotion = new HealthPotion(Item.Rarity.RARE);
        legendaryPotion = new HealthPotion(Item.Rarity.LEGENDARY);
        player = new Player("TestPlayer", Player.KNIGHT);
    }

    @Test
    @DisplayName("Should initialize with correct properties based on rarity")
    void shouldInitializeWithCorrectPropertiesBasedOnRarity() {
        // Common potion
        assertEquals("Health Potion", commonPotion.getName());
        assertEquals("A red potion that restores a small amount of health.", commonPotion.getDescription());
        assertEquals(Item.ItemType.CONSUMABLE, commonPotion.getType());
        assertEquals(Item.Rarity.COMMON, commonPotion.getRarity());
        assertEquals(25, commonPotion.getValue());
        assertEquals(30, commonPotion.getHealAmount());

        // Rare potion
        assertEquals("Superior Health Potion", rarePotion.getName());
        assertEquals("A brilliant crimson potion that restores a large amount of health.", rarePotion.getDescription());
        assertEquals(Item.ItemType.CONSUMABLE, rarePotion.getType());
        assertEquals(Item.Rarity.RARE, rarePotion.getRarity());
        assertEquals(100, rarePotion.getValue());
        assertEquals(100, rarePotion.getHealAmount());

        // Legendary potion
        assertEquals("Elixir of Life", legendaryPotion.getName());
        assertEquals("A divine elixir that fully restores health.", legendaryPotion.getDescription());
        assertEquals(Item.ItemType.CONSUMABLE, legendaryPotion.getType());
        assertEquals(Item.Rarity.LEGENDARY, legendaryPotion.getRarity());
        assertEquals(500, legendaryPotion.getValue());
        assertEquals(9999, legendaryPotion.getHealAmount());
    }

    @ParameterizedTest
    @EnumSource(Item.Rarity.class)
    @DisplayName("Should have correct properties for each rarity")
    void shouldHaveCorrectPropertiesForEachRarity(Item.Rarity rarity) {
        HealthPotion potion = new HealthPotion(rarity);
        
        assertNotNull(potion.getName());
        assertNotNull(potion.getDescription());
        assertEquals(Item.ItemType.CONSUMABLE, potion.getType());
        assertEquals(rarity, potion.getRarity());
        assertTrue(potion.getValue() > 0);
        assertTrue(potion.getHealAmount() > 0);
    }

    @Test
    @DisplayName("Should heal player when not at full health")
    void shouldHealPlayerWhenNotAtFullHealth() {
        // Damage the player
        player.takeDamage(50);
        int initialHealth = player.getCurrentHealth();
        
        // Use common potion (heals 30)
        boolean result = commonPotion.use(player);
        
        assertTrue(result);
        assertEquals(initialHealth + 30, player.getCurrentHealth());
    }

    @Test
    @DisplayName("Should not heal when player is at full health")
    void shouldNotHealWhenPlayerIsAtFullHealth() {
        // Player starts at full health
        int initialHealth = player.getCurrentHealth();
        
        // Try to use potion
        boolean result = commonPotion.use(player);
        
        assertFalse(result);
        assertEquals(initialHealth, player.getCurrentHealth());
    }

    @Test
    @DisplayName("Should not overheal beyond max health")
    void shouldNotOverhealBeyondMaxHealth() {
        // Damage player slightly
        player.takeDamage(10);
        int maxHealth = player.getMaxHealth();
        
        // Use legendary potion (heals 9999)
        boolean result = legendaryPotion.use(player);
        
        assertTrue(result);
        assertEquals(maxHealth, player.getCurrentHealth());
    }

    @Test
    @DisplayName("Should have correct heal amounts for each rarity")
    void shouldHaveCorrectHealAmountsForEachRarity() {
        HealthPotion common = new HealthPotion(Item.Rarity.COMMON);
        HealthPotion uncommon = new HealthPotion(Item.Rarity.UNCOMMON);
        HealthPotion rare = new HealthPotion(Item.Rarity.RARE);
        HealthPotion legendary = new HealthPotion(Item.Rarity.LEGENDARY);
        
        assertEquals(30, common.getHealAmount());
        assertEquals(60, uncommon.getHealAmount());
        assertEquals(100, rare.getHealAmount());
        assertEquals(9999, legendary.getHealAmount());
    }

    @Test
    @DisplayName("Should have correct values for each rarity")
    void shouldHaveCorrectValuesForEachRarity() {
        HealthPotion common = new HealthPotion(Item.Rarity.COMMON);
        HealthPotion uncommon = new HealthPotion(Item.Rarity.UNCOMMON);
        HealthPotion rare = new HealthPotion(Item.Rarity.RARE);
        HealthPotion legendary = new HealthPotion(Item.Rarity.LEGENDARY);
        
        assertEquals(25, common.getValue());
        assertEquals(50, uncommon.getValue());
        assertEquals(100, rare.getValue());
        assertEquals(500, legendary.getValue());
    }

    @Test
    @DisplayName("Should format toString correctly")
    void shouldFormatToStringCorrectly() {
        String result = commonPotion.toString();
        assertTrue(result.contains("Health Potion"));
        assertTrue(result.contains("Common"));
        
        String rareResult = rarePotion.toString();
        assertTrue(rareResult.contains("Superior Health Potion"));
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
    @DisplayName("Should work with multiple uses on damaged player")
    void shouldWorkWithMultipleUsesOnDamagedPlayer() {
        // Damage player significantly
        player.takeDamage(150);
        int initialHealth = player.getCurrentHealth();
        
        // Use multiple potions
        HealthPotion potion1 = new HealthPotion(Item.Rarity.COMMON);
        HealthPotion potion2 = new HealthPotion(Item.Rarity.UNCOMMON);
        
        boolean result1 = potion1.use(player);
        assertTrue(result1);
        
        boolean result2 = potion2.use(player);
        assertTrue(result2);
        
        assertEquals(initialHealth + 30 + 60, player.getCurrentHealth());
    }

    @Test
    @DisplayName("Should have correct names for each rarity")
    void shouldHaveCorrectNamesForEachRarity() {
        HealthPotion common = new HealthPotion(Item.Rarity.COMMON);
        HealthPotion uncommon = new HealthPotion(Item.Rarity.UNCOMMON);
        HealthPotion rare = new HealthPotion(Item.Rarity.RARE);
        HealthPotion legendary = new HealthPotion(Item.Rarity.LEGENDARY);
        
        assertEquals("Health Potion", common.getName());
        assertEquals("Greater Health Potion", uncommon.getName());
        assertEquals("Superior Health Potion", rare.getName());
        assertEquals("Elixir of Life", legendary.getName());
    }
}
