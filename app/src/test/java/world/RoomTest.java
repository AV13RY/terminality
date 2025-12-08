package world;

import characters.Enemy;
import characters.Goblin;
import characters.Orc;
import items.Chest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Room Tests")
class RoomTest {

    private Room startRoom;
    private Room normalRoom;
    private Room treasureRoom;
    private Room bossRoom;
    private Room emptyRoom;
    private Goblin testEnemy;
    private Chest testChest;

    @BeforeEach
    public void setUp() {
        startRoom = new Room("Church Entrance", "The entrance to the abandoned church", 0, 0, Room.RoomType.START);
        normalRoom = new Room("Dusty Corridor", "A dimly lit room with stone walls", 1, 0, Room.RoomType.NORMAL);
        treasureRoom = new Room("Treasure Chamber", "Glittering treasures catch your eye", 2, 0, Room.RoomType.TREASURE);
        bossRoom = new Room("Boss Chamber", "A massive chamber with ancient pillars", 3, 0, Room.RoomType.BOSS);
        emptyRoom = new Room("Empty Room", "An eerily quiet chamber", -5, 10, Room.RoomType.EMPTY);
        
        testEnemy = new Goblin();
        testChest = new Chest(Chest.Rarity.COMMON);
    }

    @Test
    @DisplayName("Should initialize room with correct properties")
    void shouldInitializeRoomWithCorrectProperties() {
        assertEquals("Dusty Corridor", normalRoom.getName());
        assertEquals("A dimly lit room with stone walls", normalRoom.getDescription());
        assertEquals(1, normalRoom.getX());
        assertEquals(0, normalRoom.getY());
        assertEquals(Room.RoomType.NORMAL, normalRoom.getType());
        assertFalse(normalRoom.isVisited());
    }

    @ParameterizedTest
    @EnumSource(Room.RoomType.class)
    @DisplayName("Should initialize rooms with different types")
    void shouldInitializeRoomsWithDifferentTypes(Room.RoomType type) {
        Room room = new Room("Test Room", "A test room", 0, 0, type);
        
        assertEquals(type, room.getType());
        assertEquals("Test Room", room.getName());
        assertEquals("A test room", room.getDescription());
        assertEquals(0, room.getX());
        assertEquals(0, room.getY());
        assertFalse(room.isVisited());
    }

    @Test
    @DisplayName("Should manage exits correctly")
    void shouldManageExitsCorrectly() {
        // Add exits
        normalRoom.addExit("north", startRoom);
        normalRoom.addExit("east", treasureRoom);
        
        // Test getting specific exits
        assertEquals(startRoom, normalRoom.getExit("north"));
        assertEquals(treasureRoom, normalRoom.getExit("east"));
        assertNull(normalRoom.getExit("south"));
        
        // Test getting all exits
        Map<String, Room> exits = normalRoom.getExits();
        assertEquals(2, exits.size());
        assertTrue(exits.containsKey("north"));
        assertTrue(exits.containsKey("east"));
    }

    @Test
    @DisplayName("Should manage enemies correctly")
    void shouldManageEnemiesCorrectly() {
        // Initially no enemies
        assertFalse(normalRoom.hasEnemies());
        assertTrue(normalRoom.getEnemies().isEmpty());
        
        // Add enemy
        normalRoom.addEnemy(testEnemy);
        assertTrue(normalRoom.hasEnemies());
        assertEquals(1, normalRoom.getEnemies().size());
        assertEquals(testEnemy, normalRoom.getEnemies().get(0));
        
        // Add another enemy
        Orc orc = new Orc();
        normalRoom.addEnemy(orc);
        assertEquals(2, normalRoom.getEnemies().size());
        
        // Remove enemy
        normalRoom.removeEnemy(testEnemy);
        assertEquals(1, normalRoom.getEnemies().size());
        assertEquals(orc, normalRoom.getEnemies().get(0));
    }

    @Test
    @DisplayName("Should manage chests correctly")
    void shouldManageChestsCorrectly() {
        // Initially no chests
        assertTrue(normalRoom.getChests().isEmpty());
        
        // Add chest
        normalRoom.addChest(testChest);
        List<Chest> chests = normalRoom.getChests();
        assertEquals(1, chests.size());
        assertEquals(testChest, chests.get(0));
        
        // Verify returned list is a copy
        chests.clear();
        assertEquals(1, normalRoom.getChests().size());
    }

    @Test
    @DisplayName("Should determine if chests are accessible correctly")
    void shouldDetermineIfChestsAreAccessibleCorrectly() {
        // No chests, no enemies
        assertFalse(normalRoom.hasAccessibleChests());
        
        // Add chest but no enemies
        normalRoom.addChest(testChest);
        assertTrue(normalRoom.hasAccessibleChests());
        
        // Add enemy
        normalRoom.addEnemy(testEnemy);
        assertFalse(normalRoom.hasAccessibleChests());
        
        // Remove enemy
        normalRoom.removeEnemy(testEnemy);
        assertTrue(normalRoom.hasAccessibleChests());
    }

    @Test
    @DisplayName("Should manage visited state correctly")
    void shouldManageVisitedStateCorrectly() {
        // Initially not visited
        assertFalse(normalRoom.isVisited());
        
        // Mark as visited
        normalRoom.setVisited(true);
        assertTrue(normalRoom.isVisited());
        
        // Mark as not visited
        normalRoom.setVisited(false);
        assertFalse(normalRoom.isVisited());
    }

    @Test
    @DisplayName("Should handle multiple exits in different directions")
    void shouldHandleMultipleExitsInDifferentDirections() {
        Room north = new Room("North Room", "A room to the north", 0, 1, Room.RoomType.NORMAL);
        Room south = new Room("South Room", "A room to the south", 0, -1, Room.RoomType.NORMAL);
        Room east = new Room("East Room", "A room to the east", 1, 0, Room.RoomType.NORMAL);
        Room west = new Room("West Room", "A room to the west", -1, 0, Room.RoomType.NORMAL);
        
        normalRoom.addExit("north", north);
        normalRoom.addExit("south", south);
        normalRoom.addExit("east", east);
        normalRoom.addExit("west", west);
        
        assertEquals(north, normalRoom.getExit("north"));
        assertEquals(south, normalRoom.getExit("south"));
        assertEquals(east, normalRoom.getExit("east"));
        assertEquals(west, normalRoom.getExit("west"));
        
        assertEquals(4, normalRoom.getExits().size());
    }

    @Test
    @DisplayName("Should handle multiple enemies and chests")
    void shouldHandleMultipleEnemiesAndChests() {
        // Add multiple enemies
        Goblin goblin1 = new Goblin();
        Goblin goblin2 = new Goblin();
        Orc orc = new Orc();
        
        normalRoom.addEnemy(goblin1);
        normalRoom.addEnemy(goblin2);
        normalRoom.addEnemy(orc);
        
        assertEquals(3, normalRoom.getEnemies().size());
        assertTrue(normalRoom.hasEnemies());
        
        // Add multiple chests
        Chest common = new Chest(Chest.Rarity.COMMON);
        Chest rare = new Chest(Chest.Rarity.RARE);
        
        normalRoom.addChest(common);
        normalRoom.addChest(rare);
        
        assertEquals(2, normalRoom.getChests().size());
        
        // Chests not accessible with enemies present
        assertFalse(normalRoom.hasAccessibleChests());
        
        // Remove all enemies
        normalRoom.removeEnemy(goblin1);
        normalRoom.removeEnemy(goblin2);
        normalRoom.removeEnemy(orc);
        
        assertTrue(normalRoom.hasAccessibleChests());
    }

    @Test
    @DisplayName("Should maintain coordinates correctly")
    void shouldMaintainCoordinatesCorrectly() {
        assertEquals(0, startRoom.getX());
        assertEquals(0, startRoom.getY());
        
        assertEquals(1, normalRoom.getX());
        assertEquals(0, normalRoom.getY());
        
        assertEquals(2, treasureRoom.getX());
        assertEquals(0, treasureRoom.getY());
        
        assertEquals(-5, emptyRoom.getX());
        assertEquals(10, emptyRoom.getY());
    }

    @Test
    @DisplayName("Should handle room type specific behaviors")
    void shouldHandleRoomTypeSpecificBehaviors() {
        assertEquals(Room.RoomType.START, startRoom.getType());
        assertEquals(Room.RoomType.NORMAL, normalRoom.getType());
        assertEquals(Room.RoomType.TREASURE, treasureRoom.getType());
        assertEquals(Room.RoomType.BOSS, bossRoom.getType());
        assertEquals(Room.RoomType.EMPTY, emptyRoom.getType());
    }

    @Test
    @DisplayName("Should overwrite existing exits when adding to same direction")
    void shouldOverwriteExistingExitsWhenAddingToSameDirection() {
        normalRoom.addExit("north", startRoom);
        assertEquals(startRoom, normalRoom.getExit("north"));
        
        // Overwrite north exit
        normalRoom.addExit("north", treasureRoom);
        assertEquals(treasureRoom, normalRoom.getExit("north"));
        assertEquals(1, normalRoom.getExits().size());
    }

    @Test
    @DisplayName("Should handle removing non-existent enemy gracefully")
    void shouldHandleRemovingNonExistentEnemyGracefully() {
        normalRoom.addEnemy(testEnemy);
        assertEquals(1, normalRoom.getEnemies().size());
        
        // Try to remove enemy that's not in the room
        Orc differentOrc = new Orc();
        normalRoom.removeEnemy(differentOrc);
        
        assertEquals(1, normalRoom.getEnemies().size());
    }
}
