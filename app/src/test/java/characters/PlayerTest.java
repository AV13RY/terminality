package characters;

import items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Player Tests")
class PlayerTest {

    private Player playerKnight;
    private Player playerMage;
    private Player playerReaper;

    @BeforeEach
    public void setUp() {
        playerKnight = new Player("TestKnight", Player.KNIGHT);
        playerMage = new Player("TestMage", Player.MAGE);
        playerReaper = new Player("TestReaper", Player.REAPER);
    }

    //------------------------------------------------------------------------------------------- CLASS INITIALIZATION TESTS
    @ParameterizedTest
    @CsvSource({"Knight, 120, 12, 10, 0", "Mage, 60, 20, 3, 100", "reaper, 80, 15, 5, 30"})
    @DisplayName("Should initialize player with correct class stats")
    void shouldInitializePlayerWithClassStats(String className, int expectedHealth, int expectedAttack, int expectedDefense, int expectedMana) {
        Player player = new Player("Test" + className, className);

        assertEquals(expectedHealth, player.getMaxHealth());
        assertEquals(expectedHealth, player.getCurrentHealth());
        assertEquals(expectedAttack, player.getAttack());
        assertEquals(expectedDefense, player.getDefense());
        assertEquals(expectedMana, player.getMaxMana());
        assertEquals(expectedMana, player.getMana());
        assertEquals(1, player.getLevel());
        assertEquals(0, player.getExperience());
        assertEquals(0, player.getGold());
    }

    @ParameterizedTest
    @CsvSource({"Knight, TestKnight, 'TestKnight - HP: 120/120 | ATK: 12 | DEF: 10', 'Class: Knight | Level: 1 | EXP: 0/100'", "Mage, TestMage, 'TestMage - HP: 60/60 | ATK: 20 | DEF: 3', 'Class: Mage | Level: 1 | EXP: 0/100 | MP: 100/100'", "reaper, TestReaper, 'TestReaper - HP: 80/80 | ATK: 15 | DEF: 5', 'Class: reaper | Level: 1 | EXP: 0/100 | MP: 30/30'"})
    @DisplayName("Should return correct status string for each class")
    void shouldReturnCorrectStatusForClass(String className, String playerName, String baseStatus, String classInfo) {
        Player player = new Player(playerName, className);
        String expectedStatus = baseStatus + "\n" + classInfo;
        assertEquals(expectedStatus, player.getStatus());
    }

    //-------------------------------------------------------------------------------------------------- GENERAL TESTS
    @Test
    public void shouldTakeDamage() {
        playerKnight.takeDamage(25);
        assertEquals(105, playerKnight.getCurrentHealth()); // should be 105 cause of defence
    }

    @Test
    public void shouldGainExperience() {
        playerKnight.gainExperience(99);
        assertEquals(99, playerKnight.getExperience());
    }

    @Test
    void shouldLevelUp() {
        playerKnight.levelUp();
        assertEquals(2, playerKnight.getLevel());
    }

    @Test
    void shouldGainGold() {
        playerKnight.addGold(100);
        assertEquals(100, playerKnight.getGold());
    }

    @Test
    public void shouldLoseGold() {
        playerKnight.addGold(100);
        playerKnight.removeGold(50);
        assertEquals(50, playerKnight.getGold());
    }

    @Test
    public void shouldGainMana() {
        playerMage.castSpell(100);
        playerMage.restoreMana(50);
        assertEquals(50, playerMage.getMana());
    }

    //------------------------------------------------------------------------------------------------ LEVEL UP LOOP TESTS
    @Test
    @DisplayName("Should level up once when gaining exactly 100 experience")
    public void shouldLevelUpOnceWithExactly100Experience() {
        playerKnight.gainExperience(100);
        assertEquals(2, playerKnight.getLevel());
        assertEquals(0, playerKnight.getExperience());
    }

    @Test
    @DisplayName("Should level up multiple times when gaining 200+ experience")
    public void shouldLevelUpMultipleTimesWithHighExperience() {
        playerKnight.gainExperience(250);
        assertEquals(3, playerKnight.getLevel()); // Should level up twice
        assertEquals(50, playerKnight.getExperience()); // 50 remaining
    }

    @Test
    @DisplayName("Should level up three times when gaining 300+ experience")
    public void shouldLevelUpThreeTimesWithVeryHighExperience() {
        playerMage.gainExperience(325);
        assertEquals(4, playerMage.getLevel()); // Should level up three times
        assertEquals(25, playerMage.getExperience()); // 25 remaining
    }

    @Test
    @DisplayName("Should not level up when gaining less than 100 experience")
    public void shouldNotLevelUpWithLessThan100Experience() {
        playerReaper.gainExperience(99);
        assertEquals(1, playerReaper.getLevel());
        assertEquals(99, playerReaper.getExperience());
    }

    @Test
    @DisplayName("Should accumulate experience across multiple gains and level up")
    public void shouldAccumulateExperienceAndLevelUp() {
        playerKnight.gainExperience(50);
        assertEquals(1, playerKnight.getLevel());
        assertEquals(50, playerKnight.getExperience());

        playerKnight.gainExperience(75);
        assertEquals(2, playerKnight.getLevel()); // Should level up once
        assertEquals(25, playerKnight.getExperience()); // 125 - 100 = 25
    }

    //------------------------------------------------------------------------------------------- LEVEL UP STAT INCREASE TESTS
    @ParameterizedTest
    @CsvSource({"Knight, 120, 12, 10, 0, 15, 3, 2, 0", "Mage, 60, 20, 3, 100, 5, 1, 1, 20", "reaper, 80, 15, 5, 30, 10, 2, 1, 5"})
    @DisplayName("Should increase stats correctly when leveling up")
    public void shouldIncreaseStatsOnLevelUp(String className, int initialHealth, int initialAttack, int initialDefense, int initialMana, int healthGain, int attackGain, int defenseGain, int manaGain) {
        Player player = new Player("Test", className);

        player.levelUp();

        assertEquals(initialHealth + healthGain, player.getMaxHealth());
        assertEquals(initialHealth + healthGain, player.getCurrentHealth());
        assertEquals(initialAttack + attackGain, player.getAttack());
        assertEquals(initialDefense + defenseGain, player.getDefense());
        assertEquals(initialMana + manaGain, player.getMaxMana());
        assertEquals(initialMana + manaGain, player.getMana());
        assertEquals(2, player.getLevel());
    }

    @ParameterizedTest
    @CsvSource({"Knight, 135, 15, 12", "Mage, 65, 21, 4", "reaper, 90, 17, 6"})
    @DisplayName("Should increase stats correctly through gainExperience")
    public void shouldIncreaseStatsThroughExperience(String className, int expectedHealth, int expectedAttack, int expectedDefense) {
        Player player = new Player("Test", className);

        player.gainExperience(100);

        assertEquals(2, player.getLevel());
        assertEquals(expectedHealth, player.getMaxHealth());
        assertEquals(expectedHealth, player.getCurrentHealth());
        assertEquals(expectedAttack, player.getAttack());
        assertEquals(expectedDefense, player.getDefense());
    }

    //------------------------------------------------------------------------------------------------- CAST SPELL TESTS
    @ParameterizedTest
    @CsvSource({"Mage, 100, 50, 25, 50", "Mage, 100, 100, 25, 0", "reaper, 30, 20, 10, 10", "reaper, 30, 30, 10, 0"})
    @DisplayName("Should cast spell with sufficient mana and deal correct damage")
    public void shouldCastSpellWithSufficientMana(String className, int initialMana, int manaCost, int expectedDamage, int expectedRemainingMana) {
        Player player = new Player("Test", className);

        int damage = player.castSpell(manaCost);

        assertEquals(expectedDamage, damage);
        assertEquals(expectedRemainingMana, player.getMana());
    }

    @ParameterizedTest
    @CsvSource({"Mage, 100, 150", "reaper, 30, 50", "Knight, 0, 10"})
    @DisplayName("Should not cast spell with insufficient mana")
    public void shouldNotCastSpellWithInsufficientMana(String className, int initialMana, int manaCost) {
        Player player = new Player("Test", className);

        int damage = player.castSpell(manaCost);

        assertEquals(0, damage);
        assertEquals(initialMana, player.getMana());
    }

    @Test
    @DisplayName("Mage should not cast spell after depleting all mana")
    public void mageShouldNotCastSpellAfterDepletingMana() {
        playerMage.castSpell(100); // Use all mana
        int damage = playerMage.castSpell(1); // Try to cast with 0 mana

        assertEquals(0, damage);
        assertEquals(0, playerMage.getMana());
    }

    //----------------------------------------------------------------------------------------------- DISPLAY STATUS TESTS
    @Test
    @DisplayName("Knight should display status without mana (maxMana = 0)")
    public void knightShouldDisplayStatusWithoutMana() {
        String status = playerKnight.displayStatus();

        // Verify all required elements are present
        assertTrue(status.contains("CHARACTER STATUS"));
        assertTrue(status.contains("Name: TestKnight"));
        assertTrue(status.contains("Class: Knight"));
        assertTrue(status.contains("Subclass Weapon: None"));
        assertTrue(status.contains("Level: 1"));
        assertTrue(status.contains("Experience: 0/100"));
        assertTrue(status.contains("Vitals:"));
        assertTrue(status.contains("Health: 120/120"));
        assertFalse(status.contains("Mana:")); // Knight should not display mana
        assertTrue(status.contains("Stats:"));
        assertTrue(status.contains("Attack: 12"));
        assertTrue(status.contains("Defense: 10"));
    }

    @Test
    @DisplayName("Mage should display status with mana (maxMana > 0)")
    public void mageShouldDisplayStatusWithMana() {
        String status = playerMage.displayStatus();

        // Verify all required elements are present
        assertTrue(status.contains("CHARACTER STATUS"));
        assertTrue(status.contains("Name: TestMage"));
        assertTrue(status.contains("Class: Mage"));
        assertTrue(status.contains("Subclass Weapon: None"));
        assertTrue(status.contains("Level: 1"));
        assertTrue(status.contains("Experience: 0/100"));
        assertTrue(status.contains("Vitals:"));
        assertTrue(status.contains("Health: 60/60"));
        assertTrue(status.contains("Mana: 100/100")); // Mage should display mana
        assertTrue(status.contains("Stats:"));
        assertTrue(status.contains("Attack: 20"));
        assertTrue(status.contains("Defense: 3"));
    }

    @Test
    @DisplayName("Reaper should display status with mana (maxMana > 0)")
    public void reaperShouldDisplayStatusWithMana() {
        String status = playerReaper.displayStatus();

        // Verify all required elements are present
        assertTrue(status.contains("CHARACTER STATUS"));
        assertTrue(status.contains("Name: TestReaper"));
        assertTrue(status.contains("Class: reaper"));
        assertTrue(status.contains("Subclass Weapon: None"));
        assertTrue(status.contains("Level: 1"));
        assertTrue(status.contains("Experience: 0/100"));
        assertTrue(status.contains("Vitals:"));
        assertTrue(status.contains("Health: 80/80"));
        assertTrue(status.contains("Mana: 30/30")); // Reaper should display mana
        assertTrue(status.contains("Stats:"));
        assertTrue(status.contains("Attack: 15"));
        assertTrue(status.contains("Defense: 5"));
    }

    @Test
    @DisplayName("Should display status with partial health and mana")
    public void shouldDisplayStatusWithPartialHealthAndMana() {
        playerMage.takeDamage(30); // Reduce health
        playerMage.castSpell(50); // Reduce mana

        String status = playerMage.displayStatus();

        assertTrue(status.contains("Health: 33/60")); // 60 - max(1, 30-3) = 60 - 27 = 33
        assertTrue(status.contains("Mana: 50/100")); // 100 - 50 = 50
    }

    @Test
    @DisplayName("Should display status with different experience values")
    public void shouldDisplayStatusWithDifferentExperience() {
        playerKnight.gainExperience(75);

        String status = playerKnight.displayStatus();

        assertTrue(status.contains("Experience: 75/100"));
        assertTrue(status.contains("Level: 1"));
    }

    @Test
    @DisplayName("Should display status after leveling up")
    public void shouldDisplayStatusAfterLevelingUp() {
        playerMage.gainExperience(150); // Level up once with 50 exp remaining

        String status = playerMage.displayStatus();

        assertTrue(status.contains("Level: 2"));
        assertTrue(status.contains("Experience: 50/100"));
        assertTrue(status.contains("Health: 65/65")); // Leveled up stats
        assertTrue(status.contains("Mana: 120/120"));
    }

    //--------------------------------------------------------------------------------------------------- GET STATUS TESTS
    @ParameterizedTest
    @CsvSource({"Knight, false, ''", "Mage, true, 'MP: 100/100'", "reaper, true, 'MP: 30/30'"})
    @DisplayName("Should return getStatus with correct mana display for each class")
    public void shouldReturnGetStatusWithCorrectManaDisplay(String className, boolean hasMana, String manaString) {
        Player player = new Player("Test" + className, className);
        String status = player.getStatus();

        assertTrue(status.contains("Test" + className));
        assertTrue(status.contains("Class: " + className));
        assertTrue(status.contains("Level: 1"));
        assertTrue(status.contains("EXP: 0/100"));

        if (hasMana) {
            assertTrue(status.contains(manaString));
        } else {
            assertFalse(status.contains("MP:"));
        }
    }

    //----------------------------------------------------------------------------------------------- RESTORE MANA TESTS
    @Test
    @DisplayName("Should restore mana without exceeding maxMana")
    public void shouldRestoreManaWithoutExceedingMax() {
        playerMage.castSpell(50); // Use 50 mana, leaving 50
        playerMage.restoreMana(30);

        assertEquals(80, playerMage.getMana()); // 50 + 30 = 80
    }

    @Test
    @DisplayName("Should cap mana restoration at maxMana")
    public void shouldCapManaRestorationAtMax() {
        playerMage.castSpell(20); // Use 20 mana, leaving 80
        playerMage.restoreMana(100); // Try to restore more than needed

        assertEquals(100, playerMage.getMana()); // Should cap at maxMana
    }

    @Test
    @DisplayName("Should restore mana to exact maxMana")
    public void shouldRestoreManaToExactMax() {
        playerReaper.castSpell(30); // Use all mana
        playerReaper.restoreMana(30); // Restore exact amount

        assertEquals(30, playerReaper.getMana());
    }

    //---------------------------------------------------------------------------------------------------- GETTER TESTS
    @Test
    @DisplayName("Should get full inventory")
    public void shouldGetFullInventory() {
        assertEquals(0, playerKnight.getFullInventory().size());
        assertTrue(playerKnight.getFullInventory().isEmpty());
    }

    @Test
    @DisplayName("Should get equipped armor map")
    public void shouldGetEquippedArmorMap() {
        assertNotNull(playerKnight.getEquippedArmor());
        assertTrue(playerKnight.getEquippedArmor().isEmpty());
    }

    @Test
    @DisplayName("Should get equipped accessory")
    public void shouldGetEquippedAccessory() {
        assertNull(playerKnight.getEquippedAccessory());
    }

    //-------------------------------------------------------------------------------------------- GET TOTAL DEFENSE TESTS
    @Test
    @DisplayName("Should return base defense when no armor equipped")
    public void shouldReturnBaseDefenseWithNoArmor() {
        assertEquals(10, playerKnight.getTotalDefense()); // Base defense only
    }

    @Test
    @DisplayName("Should calculate total defense with equipped armor")
    public void shouldCalculateTotalDefenseWithArmor() {
        Armor helmet = new Armor("Iron Helmet", "A sturdy helmet", Armor.ArmorType.HELMET, 5, Item.Rarity.COMMON);
        Armor chestplate = new Armor("Iron Chestplate", "A sturdy chestplate", Armor.ArmorType.CHESTPLATE, 10, Item.Rarity.COMMON);

        playerKnight.addItem(helmet);
        playerKnight.addItem(chestplate);
        playerKnight.equipArmor(helmet);
        playerKnight.equipArmor(chestplate);

        assertEquals(25, playerKnight.getTotalDefense()); // 10 base + 5 + 10
    }

    @Test
    @DisplayName("Should calculate total defense with multiple armor pieces")
    public void shouldCalculateTotalDefenseWithMultipleArmor() {
        Armor helmet = new Armor("Helmet", "Desc", Armor.ArmorType.HELMET, 3, Item.Rarity.COMMON);
        Armor boots = new Armor("Boots", "Desc", Armor.ArmorType.BOOTS, 2, Item.Rarity.COMMON);
        Armor shield = new Armor("Shield", "Desc", Armor.ArmorType.SHIELD, 7, Item.Rarity.RARE);

        playerMage.addItem(helmet);
        playerMage.addItem(boots);
        playerMage.addItem(shield);
        playerMage.equipArmor(helmet);
        playerMage.equipArmor(boots);
        playerMage.equipArmor(shield);

        assertEquals(15, playerMage.getTotalDefense()); // 3 base + 3 + 2 + 7
    }

    //------------------------------------------------------------------------------------------------- ADD WEAPON TESTS
    @Test
    @DisplayName("Should add weapon to inventory")
    public void shouldAddWeaponToInventory() {
        Weapon sword = new Weapon("Iron Sword", 10, "A basic sword", Item.Rarity.COMMON, 50);

        playerKnight.addWeapon(sword);

        assertEquals(1, playerKnight.getInventory().size());
        assertTrue(playerKnight.getInventory().contains(sword));
    }

    @Test
    @DisplayName("Should auto-equip weapon when none equipped")
    public void shouldAutoEquipWeaponWhenNoneEquipped() {
        Weapon sword = new Weapon("Iron Sword", 10, "A basic sword", Item.Rarity.COMMON, 50);

        playerKnight.addWeapon(sword);

        assertEquals(sword, playerKnight.getEquippedWeapon());
    }

    @Test
    @DisplayName("Should not auto-equip weapon when one already equipped")
    public void shouldNotAutoEquipWeaponWhenAlreadyEquipped() {
        Weapon sword1 = new Weapon("Iron Sword", 10, "A basic sword", Item.Rarity.COMMON, 50);
        Weapon sword2 = new Weapon("Steel Sword", 15, "A better sword", Item.Rarity.UNCOMMON, 100);

        playerKnight.addWeapon(sword1);
        playerKnight.addWeapon(sword2);

        assertEquals(sword1, playerKnight.getEquippedWeapon()); // First weapon still equipped
        assertEquals(2, playerKnight.getInventory().size());
    }

    //--------------------------------------------------------------------------------------------------- ADD ITEM TESTS
    @Test
    @DisplayName("Should add item to inventory")
    public void shouldAddItemToInventory() {
        HealthPotion potion = new HealthPotion(Item.Rarity.COMMON);

        playerKnight.addItem(potion);

        assertEquals(1, playerKnight.getInventory().size());
        assertTrue(playerKnight.getInventory().contains(potion));
    }

    @Test
    @DisplayName("Should add multiple items to inventory")
    public void shouldAddMultipleItemsToInventory() {
        HealthPotion potion1 = new HealthPotion(Item.Rarity.COMMON);
        HealthPotion potion2 = new HealthPotion(Item.Rarity.RARE);
        Armor helmet = new Armor("Helmet", "Desc", Armor.ArmorType.HELMET, 5, Item.Rarity.COMMON);

        playerMage.addItem(potion1);
        playerMage.addItem(potion2);
        playerMage.addItem(helmet);

        assertEquals(3, playerMage.getInventory().size());
    }

    //------------------------------------------------------------------------------------------------ EQUIP WEAPON TESTS
    @Test
    @DisplayName("Should equip weapon from inventory")
    public void shouldEquipWeaponFromInventory() {
        Weapon sword = new Weapon("Iron Sword", 10, "A basic sword", Item.Rarity.COMMON, 50);
        Weapon axe = new Weapon("Battle Axe", 15, "A heavy axe", Item.Rarity.UNCOMMON, 100);

        playerKnight.addWeapon(sword); // Auto-equipped
        playerKnight.addWeapon(axe);
        playerKnight.equipWeapon(axe);

        assertEquals(axe, playerKnight.getEquippedWeapon());
    }

    @Test
    @DisplayName("Should not equip weapon not in inventory")
    public void shouldNotEquipWeaponNotInInventory() {
        Weapon sword = new Weapon("Iron Sword", 10, "A basic sword", Item.Rarity.COMMON, 50);
        Weapon axe = new Weapon("Battle Axe", 15, "A heavy axe", Item.Rarity.UNCOMMON, 100);

        playerKnight.addWeapon(sword);
        playerKnight.equipWeapon(axe); // Not in inventory

        assertEquals(sword, playerKnight.getEquippedWeapon()); // Should still be sword
    }

    //------------------------------------------------------------------------------------------------- EQUIP ARMOR TESTS
    @Test
    @DisplayName("Should equip armor from inventory")
    public void shouldEquipArmorFromInventory() {
        Armor helmet = new Armor("Iron Helmet", "A sturdy helmet", Armor.ArmorType.HELMET, 5, Item.Rarity.COMMON);

        playerKnight.addItem(helmet);
        boolean result = playerKnight.equipArmor(helmet);

        assertTrue(result);
        assertEquals(helmet, playerKnight.getEquippedArmor().get(Armor.ArmorType.HELMET));
        assertFalse(playerKnight.getInventory().contains(helmet)); // Should be removed from inventory
    }

    @Test
    @DisplayName("Should not equip armor not in inventory")
    public void shouldNotEquipArmorNotInInventory() {
        Armor helmet = new Armor("Iron Helmet", "A sturdy helmet", Armor.ArmorType.HELMET, 5, Item.Rarity.COMMON);

        boolean result = playerKnight.equipArmor(helmet);

        assertFalse(result);
        assertNull(playerKnight.getEquippedArmor().get(Armor.ArmorType.HELMET));
    }

    @Test
    @DisplayName("Should replace previously equipped armor of same type")
    public void shouldReplacePreviouslyEquippedArmorOfSameType() {
        Armor helmet1 = new Armor("Iron Helmet", "Basic helmet", Armor.ArmorType.HELMET, 5, Item.Rarity.COMMON);
        Armor helmet2 = new Armor("Steel Helmet", "Better helmet", Armor.ArmorType.HELMET, 10, Item.Rarity.UNCOMMON);

        playerKnight.addItem(helmet1);
        playerKnight.addItem(helmet2);
        playerKnight.equipArmor(helmet1);
        playerKnight.equipArmor(helmet2);

        assertEquals(helmet2, playerKnight.getEquippedArmor().get(Armor.ArmorType.HELMET));
        assertTrue(playerKnight.getInventory().contains(helmet1)); // Old helmet back in inventory
        assertFalse(playerKnight.getInventory().contains(helmet2)); // New helmet equipped
    }

    @Test
    @DisplayName("Should equip different armor types simultaneously")
    public void shouldEquipDifferentArmorTypesSimultaneously() {
        Armor helmet = new Armor("Helmet", "Desc", Armor.ArmorType.HELMET, 5, Item.Rarity.COMMON);
        Armor chestplate = new Armor("Chestplate", "Desc", Armor.ArmorType.CHESTPLATE, 10, Item.Rarity.COMMON);
        Armor boots = new Armor("Boots", "Desc", Armor.ArmorType.BOOTS, 3, Item.Rarity.COMMON);

        playerMage.addItem(helmet);
        playerMage.addItem(chestplate);
        playerMage.addItem(boots);
        playerMage.equipArmor(helmet);
        playerMage.equipArmor(chestplate);
        playerMage.equipArmor(boots);

        assertEquals(3, playerMage.getEquippedArmor().size());
        assertEquals(helmet, playerMage.getEquippedArmor().get(Armor.ArmorType.HELMET));
        assertEquals(chestplate, playerMage.getEquippedArmor().get(Armor.ArmorType.CHESTPLATE));
        assertEquals(boots, playerMage.getEquippedArmor().get(Armor.ArmorType.BOOTS));
    }

    //--------------------------------------------------------------------------------------------- EQUIP ACCESSORY TESTS
    @Test
    @DisplayName("Should equip accessory from inventory and apply attack bonus")
    public void shouldEquipAccessoryAndApplyAttackBonus() {
        Accessory ring = new Accessory("Power Ring", "Increases attack", Accessory.StatBonus.ATTACK, 5, Item.Rarity.RARE);
        int initialAttack = playerKnight.getAttack();

        playerKnight.addItem(ring);
        boolean result = playerKnight.equipAccessory(ring);

        assertTrue(result);
        assertEquals(ring, playerKnight.getEquippedAccessory());
        assertEquals(initialAttack + 5, playerKnight.getAttack());
        assertFalse(playerKnight.getInventory().contains(ring));
    }

    @Test
    @DisplayName("Should equip accessory and apply defense bonus")
    public void shouldEquipAccessoryAndApplyDefenseBonus() {
        Accessory amulet = new Accessory("Defense Amulet", "Increases defense", Accessory.StatBonus.DEFENSE, 8, Item.Rarity.LEGENDARY);
        int initialDefense = playerMage.getDefense();

        playerMage.addItem(amulet);
        playerMage.equipAccessory(amulet);

        assertEquals(initialDefense + 8, playerMage.getDefense());
    }

    @Test
    @DisplayName("Should equip accessory and apply health bonus")
    public void shouldEquipAccessoryAndApplyHealthBonus() {
        Accessory pendant = new Accessory("Health Pendant", "Increases health", Accessory.StatBonus.HEALTH, 20, Item.Rarity.UNCOMMON);
        int initialMaxHealth = playerReaper.getMaxHealth();
        int initialCurrentHealth = playerReaper.getCurrentHealth();

        playerReaper.addItem(pendant);
        playerReaper.equipAccessory(pendant);

        assertEquals(initialMaxHealth + 20, playerReaper.getMaxHealth());
        assertEquals(initialCurrentHealth + 20, playerReaper.getCurrentHealth());
    }

    @Test
    @DisplayName("Should equip accessory and apply mana bonus")
    public void shouldEquipAccessoryAndApplyManaBonus() {
        Accessory crystal = new Accessory("Mana Crystal", "Increases mana", Accessory.StatBonus.MANA, 15, Item.Rarity.RARE);
        int initialMaxMana = playerMage.getMaxMana();

        playerMage.addItem(crystal);
        playerMage.equipAccessory(crystal);

        assertEquals(initialMaxMana + 15, playerMage.getMaxMana());
    }

    @Test
    @DisplayName("Should not equip accessory not in inventory")
    public void shouldNotEquipAccessoryNotInInventory() {
        Accessory ring = new Accessory("Power Ring", "Increases attack", Accessory.StatBonus.ATTACK, 5, Item.Rarity.RARE);

        boolean result = playerKnight.equipAccessory(ring);

        assertFalse(result);
        assertNull(playerKnight.getEquippedAccessory());
    }

    @Test
    @DisplayName("Should replace previously equipped accessory")
    public void shouldReplacePreviouslyEquippedAccessory() {
        Accessory ring1 = new Accessory("Ring 1", "Desc", Accessory.StatBonus.ATTACK, 3, Item.Rarity.COMMON);
        Accessory ring2 = new Accessory("Ring 2", "Desc", Accessory.StatBonus.DEFENSE, 5, Item.Rarity.RARE);

        playerKnight.addItem(ring1);
        playerKnight.addItem(ring2);
        playerKnight.equipAccessory(ring1);
        playerKnight.equipAccessory(ring2);

        assertEquals(ring2, playerKnight.getEquippedAccessory());
        assertTrue(playerKnight.getInventory().contains(ring1)); // Old accessory back in inventory
        assertFalse(playerKnight.getInventory().contains(ring2));
    }

    //--------------------------------------------------------------------------------------------------- USE ITEM TESTS
    @Test
    @DisplayName("Should use consumable item and remove from inventory")
    public void shouldUseConsumableItemAndRemoveFromInventory() {
        playerKnight.takeDamage(50); // Reduce health
        HealthPotion potion = new HealthPotion(Item.Rarity.COMMON);

        playerKnight.addItem(potion);
        boolean result = playerKnight.useItem(potion);

        assertTrue(result);
        assertFalse(playerKnight.getInventory().contains(potion)); // Should be removed
    }

    @Test
    @DisplayName("Should not use item not in inventory")
    public void shouldNotUseItemNotInInventory() {
        HealthPotion potion = new HealthPotion(Item.Rarity.COMMON);

        boolean result = playerKnight.useItem(potion);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should not use health potion at full health")
    public void shouldNotUseHealthPotionAtFullHealth() {
        HealthPotion potion = new HealthPotion(Item.Rarity.COMMON);

        playerKnight.addItem(potion);
        boolean result = playerKnight.useItem(potion); // At full health

        assertFalse(result);
        assertTrue(playerKnight.getInventory().contains(potion)); // Should still be in inventory
    }

    @Test
    @DisplayName("Should use armor item and equip it")
    public void shouldUseArmorItemAndEquipIt() {
        Armor helmet = new Armor("Helmet", "Desc", Armor.ArmorType.HELMET, 5, Item.Rarity.COMMON);

        playerMage.addItem(helmet);
        boolean result = playerMage.useItem(helmet);

        assertTrue(result);
        assertEquals(helmet, playerMage.getEquippedArmor().get(Armor.ArmorType.HELMET));
        assertFalse(playerMage.getInventory().contains(helmet)); // Armor removed from inventory when equipped
    }
}
