package items;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Item Generator Tests")
class ItemGeneratorTest {

    @Test
    @DisplayName("Should generate non-null item for any rarity")
    void shouldGenerateNonNullItemForAnyRarity() {
        Item commonItem = ItemGenerator.generateItem(Item.Rarity.COMMON);
        Item uncommonItem = ItemGenerator.generateItem(Item.Rarity.UNCOMMON);
        Item rareItem = ItemGenerator.generateItem(Item.Rarity.RARE);
        Item legendaryItem = ItemGenerator.generateItem(Item.Rarity.LEGENDARY);

        assertNotNull(commonItem);
        assertNotNull(uncommonItem);
        assertNotNull(rareItem);
        assertNotNull(legendaryItem);
    }

    @ParameterizedTest
    @EnumSource(Item.Rarity.class)
    @DisplayName("Should generate item with correct rarity")
    void shouldGenerateItemWithCorrectRarity(Item.Rarity rarity) {
        Item item = ItemGenerator.generateItem(rarity);
        assertEquals(rarity, item.getRarity());
    }

    @Test
    @DisplayName("Should generate valid item types")
    void shouldGenerateValidItemTypes() {
        // Generate multiple items to check type distribution
        boolean hasConsumable = false;
        boolean hasArmor = false;
        boolean hasAccessory = false;

        for (int i = 0; i < 100; i++) {
            Item item = ItemGenerator.generateItem(Item.Rarity.COMMON);
            Item.ItemType type = item.getType();

            assertTrue(type == Item.ItemType.CONSUMABLE || 
                      type == Item.ItemType.ARMOR || 
                      type == Item.ItemType.ACCESSORY);

            if (type == Item.ItemType.CONSUMABLE) hasConsumable = true;
            if (type == Item.ItemType.ARMOR) hasArmor = true;
            if (type == Item.ItemType.ACCESSORY) hasAccessory = true;

            if (hasConsumable && hasArmor && hasAccessory) break;
        }

        assertTrue(hasConsumable, "Should generate at least one consumable");
        assertTrue(hasArmor, "Should generate at least one armor");
        assertTrue(hasAccessory, "Should generate at least one accessory");
    }

    @Test
    @DisplayName("Should generate consumables as health or mana potions")
    void shouldGenerateConsumablesAsHealthOrManaPotions() {
        boolean hasHealthPotion = false;
        boolean hasManaPotion = false;

        for (int i = 0; i < 50; i++) {
            Item item = ItemGenerator.generateItem(Item.Rarity.COMMON);
            
            if (item.getType() == Item.ItemType.CONSUMABLE) {
                assertTrue(item instanceof HealthPotion || item instanceof ManaPotion);
                
                if (item instanceof HealthPotion) hasHealthPotion = true;
                if (item instanceof ManaPotion) hasManaPotion = true;
            }
        }

        assertTrue(hasHealthPotion, "Should generate at least one health potion");
    }

    @Test
    @DisplayName("Should generate armor with valid properties")
    void shouldGenerateArmorWithValidProperties() {
        for (int i = 0; i < 20; i++) {
            Item item = ItemGenerator.generateItem(Item.Rarity.RARE);
            
            if (item instanceof Armor armor) {
                assertNotNull(armor.getName());
                assertNotNull(armor.getDescription());
                assertTrue(armor.getDefenseBonus() > 0);
                assertNotNull(armor.getArmorType());
                assertEquals(Item.Rarity.RARE, armor.getRarity());
                break;
            }
        }
    }

    @Test
    @DisplayName("Should generate accessories with valid properties")
    void shouldGenerateAccessoriesWithValidProperties() {
        for (int i = 0; i < 20; i++) {
            Item item = ItemGenerator.generateItem(Item.Rarity.RARE);
            
            if (item instanceof Accessory accessory) {
                assertNotNull(accessory.getName());
                assertNotNull(accessory.getDescription());
                assertTrue(accessory.getBonusAmount() > 0);
                assertNotNull(accessory.getStatBonus());
                assertEquals(Item.Rarity.RARE, accessory.getRarity());
                break;
            }
        }
    }

    @Test
    @DisplayName("Should select rarity based on drop chances")
    void shouldSelectRarityBasedOnDropChances() {
        int commonCount = 0;
        int uncommonCount = 0;
        int rareCount = 0;
        int legendaryCount = 0;

        // Generate many rarities to test distribution
        for (int i = 0; i < 1000; i++) {
            Item.Rarity rarity = ItemGenerator.selectRarity();
            
            switch (rarity) {
                case COMMON -> commonCount++;
                case UNCOMMON -> uncommonCount++;
                case RARE -> rareCount++;
                case LEGENDARY -> legendaryCount++;
            }
        }

        // Check that rarities are generated (allowing for some variance due to randomness)
        assertTrue(commonCount > 500, "Common should be most frequent (~60%)");
        assertTrue(uncommonCount > 200, "Uncommon should be frequent (~30%)");
        assertTrue(rareCount > 50, "Rare should be less frequent (~8%)");
        assertTrue(legendaryCount > 0, "Legendary should be rare but possible (~2%)");
        
        // Total should equal number of generations
        assertEquals(1000, commonCount + uncommonCount + rareCount + legendaryCount);
    }

    @Test
    @DisplayName("Should generate different items for same rarity")
    void shouldGenerateDifferentItemsForSameRarity() {
        Item item1 = ItemGenerator.generateItem(Item.Rarity.COMMON);
        Item item2 = ItemGenerator.generateItem(Item.Rarity.COMMON);
        Item item3 = ItemGenerator.generateItem(Item.Rarity.COMMON);

        // Items should be different (though occasionally they might be the same by chance)
        // We check that at least some properties differ
        boolean allSame = item1.getName().equals(item2.getName()) && 
                         item2.getName().equals(item3.getName()) &&
                         item1.getType().equals(item2.getType()) &&
                         item2.getType().equals(item3.getType());

        // This test might occasionally fail due to randomness, but should pass most of the time
        assertFalse(allSame, "Should generate variety of items even for same rarity");
    }

    @Test
    @DisplayName("Should handle all rarity enum values")
    void shouldHandleAllRarityEnumValues() {
        Item.Rarity[] rarities = Item.Rarity.values();
        assertEquals(4, rarities.length);
        
        for (Item.Rarity rarity : rarities) {
            assertNotNull(rarity.getName());
            assertTrue(rarity.getDropChance() > 0);
            assertTrue(rarity.getDropChance() <= 1.0);
        }
    }

    @Test
    @DisplayName("Should generate items with appropriate names based on rarity")
    void shouldGenerateItemsWithAppropriateNamesBasedOnRarity() {
        // Test that higher rarity items have appropriate prefixes
        for (int i = 0; i < 50; i++) {
            Item legendaryItem = ItemGenerator.generateItem(Item.Rarity.LEGENDARY);
            
            if (legendaryItem instanceof Armor armor) {
                String name = armor.getName();
                // Legendary armor should have legendary prefixes
                assertTrue(name.contains("Dragon Scale") || 
                          name.contains("Divine") || 
                          name.contains("Ethereal"));
                break;
            }
        }
    }

    @Test
    @DisplayName("Should calculate item values correctly based on rarity and type")
    void shouldCalculateItemValuesCorrectlyBasedOnRarityAndType() {
        // Generate items and check they have reasonable values
        for (int i = 0; i < 20; i++) {
            Item common = ItemGenerator.generateItem(Item.Rarity.COMMON);
            Item legendary = ItemGenerator.generateItem(Item.Rarity.LEGENDARY);
            
            assertTrue(common.getValue() > 0, "Common items should have positive value");
            assertTrue(legendary.getValue() > 0, "Legendary items should have positive value");
            assertTrue(legendary.getValue() >= common.getValue(), 
                      "Legendary items should generally be worth more than common items");
        }
    }
}
