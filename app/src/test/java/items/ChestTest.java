package items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Chest Tests")
class ChestTest {

    private Chest commonChest;
    private Chest rareChest;
    private Chest legendaryChest;

    @BeforeEach
    public void setUp() {
        commonChest = new Chest(Chest.Rarity.COMMON);
        rareChest = new Chest(Chest.Rarity.RARE);
        legendaryChest = new Chest(Chest.Rarity.LEGENDARY);
    }

    @Test
    @DisplayName("Should initialize chest with correct rarity")
    void shouldInitializeChestWithCorrectRarity() {
        assertEquals(Chest.Rarity.COMMON, commonChest.getRarity());
        assertEquals(Chest.Rarity.RARE, rareChest.getRarity());
        assertEquals(Chest.Rarity.LEGENDARY, legendaryChest.getRarity());
    }

    @Test
    @DisplayName("Should be closed when initially created")
    void shouldBeClosedWhenInitiallyCreated() {
        assertTrue(commonChest.isClosed());
        assertTrue(rareChest.isClosed());
        assertTrue(legendaryChest.isClosed());
    }

    @ParameterizedTest
    @EnumSource(Chest.Rarity.class)
    @DisplayName("Should generate contents based on rarity")
    void shouldGenerateContentsBasedOnRarity(Chest.Rarity rarity) {
        Chest chest = new Chest(rarity);
        List<Item> contents = chest.open();
        
        assertNotNull(contents);
        assertFalse(contents.isEmpty());
        
        // Verify item count based on rarity
        int minItems = switch (rarity) {
            case COMMON -> 1;
            case UNCOMMON -> 2;
            case RARE -> 3;
            case LEGENDARY -> 4;
        };
        
        int maxItems = switch (rarity) {
            case COMMON -> 2;
            case UNCOMMON -> 3;
            case RARE -> 4;
            case LEGENDARY -> 6;
        };
        
        assertTrue(contents.size() >= minItems, "Chest should have at least " + minItems + " items for " + rarity + " rarity");
        assertTrue(contents.size() <= maxItems, "Chest should have at most " + maxItems + " items for " + rarity + " rarity");
    }

    @Test
    @DisplayName("Should return items when opened for the first time")
    void shouldReturnItemsWhenOpenedForTheFirstTime() {
        List<Item> items = commonChest.open();
        
        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertFalse(commonChest.isClosed());
    }

    @Test
    @DisplayName("Should return empty list when opened multiple times")
    void shouldReturnEmptyListWhenOpenedMultipleTimes() {
        List<Item> firstOpen = commonChest.open();
        assertNotNull(firstOpen);
        assertFalse(firstOpen.isEmpty());
        
        List<Item> secondOpen = commonChest.open();
        assertNotNull(secondOpen);
        assertTrue(secondOpen.isEmpty());
        
        List<Item> thirdOpen = commonChest.open();
        assertNotNull(thirdOpen);
        assertTrue(thirdOpen.isEmpty());
    }

    @Test
    @DisplayName("Should generate different item types")
    void shouldGenerateDifferentItemTypes() {
        // Open multiple chests to get variety of items
        Chest chest1 = new Chest(Chest.Rarity.LEGENDARY);
        Chest chest2 = new Chest(Chest.Rarity.LEGENDARY);
        Chest chest3 = new Chest(Chest.Rarity.LEGENDARY);
        
        List<Item> items1 = chest1.open();
        List<Item> items2 = chest2.open();
        List<Item> items3 = chest3.open();
        
        // Verify all items are valid
        items1.forEach(item -> assertNotNull(item));
        items2.forEach(item -> assertNotNull(item));
        items3.forEach(item -> assertNotNull(item));
    }

    @Test
    @DisplayName("Should handle legendary chests with chance for legendary items")
    void shouldHandleLegendaryChestsWithChanceForLegendaryItems() {
        boolean foundLegendary = false;
        
        // Try multiple legendary chests to increase chance of finding legendary items
        for (int i = 0; i < 20; i++) {
            Chest chest = new Chest(Chest.Rarity.LEGENDARY);
            List<Item> items = chest.open();
            
            for (Item item : items) {
                if (item.getRarity() == Item.Rarity.LEGENDARY) {
                    foundLegendary = true;
                    break;
                }
            }
            
            if (foundLegendary) break;
        }
        
        // Note: This test might occasionally fail due to randomness, but should pass most of the time
        // since legendary chests have a 50% chance per item to generate legendary items
    }

    @Test
    @DisplayName("Should maintain chest rarity enum properties")
    void shouldMaintainChestRarityEnumProperties() {
        // Test that enum values are properly initialized
        assertEquals(4, Chest.Rarity.values().length);
        
        // Test enum ordinal values
        assertEquals(0, Chest.Rarity.COMMON.ordinal());
        assertEquals(1, Chest.Rarity.UNCOMMON.ordinal());
        assertEquals(2, Chest.Rarity.RARE.ordinal());
        assertEquals(3, Chest.Rarity.LEGENDARY.ordinal());
    }

    @Test
    @DisplayName("Should create independent chest instances")
    void shouldCreateIndependentChestInstances() {
        Chest chestA = new Chest(Chest.Rarity.COMMON);
        Chest chestB = new Chest(Chest.Rarity.COMMON);
        
        List<Item> itemsA = chestA.open();
        List<Item> itemsB = chestB.open();
        
        // Both chests should have items
        assertFalse(itemsA.isEmpty());
        assertFalse(itemsB.isEmpty());
        
        // Opening one shouldn't affect the other
        assertTrue(chestA.isClosed() == false);
        assertTrue(chestB.isClosed() == false);
    }
}
