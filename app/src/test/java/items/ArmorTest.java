package items;

import characters.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Armor Tests")
class ArmorTest {

    private Armor commonHelmet;
    private Armor rareChestplate;
    private Player player;

    @BeforeEach
    public void setUp() {
        commonHelmet = new Armor("Iron Helmet", "A basic iron helmet", Armor.ArmorType.HELMET, 5, Item.Rarity.COMMON);
        rareChestplate = new Armor("Mithril Chestplate", "A sturdy mithril chestplate", Armor.ArmorType.CHESTPLATE, 20, Item.Rarity.RARE);
        player = new Player("TestPlayer", Player.KNIGHT);
    }

    @Test
    @DisplayName("Should initialize armor with correct stats")
    void shouldInitializeArmorWithCorrectStats() {
        assertEquals("Iron Helmet", commonHelmet.getName());
        assertEquals("A basic iron helmet", commonHelmet.getDescription());
        assertEquals(Item.ItemType.ARMOR, commonHelmet.getType());
        assertEquals(Item.Rarity.COMMON, commonHelmet.getRarity());
        assertEquals(50, commonHelmet.getValue()); // 5 * 10 * 1 (common multiplier)
        assertEquals(5, commonHelmet.getDefenseBonus());
        assertEquals(Armor.ArmorType.HELMET, commonHelmet.getArmorType());
    }

    @ParameterizedTest
    @CsvSource({
        "LEGGINGS, 3, COMMON, 30",
        "BOOTS, 2, UNCOMMON, 40",
        "SHIELD, 8, RARE, 320",
        "CHESTPLATE, 10, LEGENDARY, 1000"
    })
    @DisplayName("Should initialize armor with different types and rarities")
    void shouldInitializeArmorWithDifferentTypesAndRarities(Armor.ArmorType armorType, int defenseBonus, Item.Rarity rarity, int expectedValue) {
        Armor armor = new Armor("Test Armor", "A test armor piece", armorType, defenseBonus, rarity);
        
        assertEquals(armorType, armor.getArmorType());
        assertEquals(defenseBonus, armor.getDefenseBonus());
        assertEquals(rarity, armor.getRarity());
        assertEquals(expectedValue, armor.getValue());
        assertEquals(Item.ItemType.ARMOR, armor.getType());
    }

    @Test
    @DisplayName("Should return correct defense bonus")
    void shouldReturnCorrectDefenseBonus() {
        assertEquals(5, commonHelmet.getDefenseBonus());
        assertEquals(20, rareChestplate.getDefenseBonus());
    }

    @Test
    @DisplayName("Should return correct armor type")
    void shouldReturnCorrectArmorType() {
        assertEquals(Armor.ArmorType.HELMET, commonHelmet.getArmorType());
        assertEquals(Armor.ArmorType.CHESTPLATE, rareChestplate.getArmorType());
    }

    @Test
    @DisplayName("Should calculate value correctly based on rarity")
    void shouldCalculateValueCorrectlyBasedOnRarity() {
        // Common: defense * 10
        Armor common = new Armor("Common Armor", "Common armor", Armor.ArmorType.CHESTPLATE, 10, Item.Rarity.COMMON);
        assertEquals(100, common.getValue());
        
        // Uncommon: defense * 10 * 2
        Armor uncommon = new Armor("Uncommon Armor", "Uncommon armor", Armor.ArmorType.CHESTPLATE, 10, Item.Rarity.UNCOMMON);
        assertEquals(200, uncommon.getValue());
        
        // Rare: defense * 10 * 4
        Armor rare = new Armor("Rare Armor", "Rare armor", Armor.ArmorType.CHESTPLATE, 10, Item.Rarity.RARE);
        assertEquals(400, rare.getValue());
        
        // Legendary: defense * 10 * 10
        Armor legendary = new Armor("Legendary Armor", "Legendary armor", Armor.ArmorType.CHESTPLATE, 10, Item.Rarity.LEGENDARY);
        assertEquals(1000, legendary.getValue());
    }

    @Test
    @DisplayName("Should equip armor when used")
    void shouldEquipArmorWhenUsed() {
        // Add armor to inventory first
        player.addItem(commonHelmet);
        boolean result = commonHelmet.use(player);
        assertTrue(result);
    }

    @Test
    @DisplayName("Should format toString correctly")
    void shouldFormatToStringCorrectly() {
        String result = commonHelmet.toString();
        assertTrue(result.contains("Iron Helmet"));
        assertTrue(result.contains("Common"));
    }

    @Test
    @DisplayName("Should have correct item type")
    void shouldHaveCorrectItemType() {
        assertEquals(Item.ItemType.ARMOR, commonHelmet.getType());
        assertEquals(Item.ItemType.ARMOR, rareChestplate.getType());
    }

    @Test
    @DisplayName("Should handle all armor types")
    void shouldHandleAllArmorTypes() {
        Armor helmet = new Armor("Helmet", "A helmet", Armor.ArmorType.HELMET, 5, Item.Rarity.COMMON);
        Armor chestplate = new Armor("Chestplate", "A chestplate", Armor.ArmorType.CHESTPLATE, 10, Item.Rarity.COMMON);
        Armor leggings = new Armor("Leggings", "Leggings", Armor.ArmorType.LEGGINGS, 8, Item.Rarity.COMMON);
        Armor boots = new Armor("Boots", "Boots", Armor.ArmorType.BOOTS, 4, Item.Rarity.COMMON);
        Armor shield = new Armor("Shield", "A shield", Armor.ArmorType.SHIELD, 6, Item.Rarity.COMMON);

        assertEquals(Armor.ArmorType.HELMET, helmet.getArmorType());
        assertEquals(Armor.ArmorType.CHESTPLATE, chestplate.getArmorType());
        assertEquals(Armor.ArmorType.LEGGINGS, leggings.getArmorType());
        assertEquals(Armor.ArmorType.BOOTS, boots.getArmorType());
        assertEquals(Armor.ArmorType.SHIELD, shield.getArmorType());
    }
}
