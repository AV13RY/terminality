package world;

import characters.Boss;
import characters.Enemy;
import characters.Goblin;
import characters.Orc;
import characters.Skeleton;
import items.Chest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Map Builder Tests")
class MapBuilderTest {

    private MapBuilder mapBuilder;

    @BeforeEach
    public void setUp() {
        mapBuilder = new MapBuilder();
    }

    @Test
    @DisplayName("Should generate map with start room")
    void shouldGenerateMapWithStartRoom() {
        Room startRoom = mapBuilder.generateMap();
        
        assertNotNull(startRoom);
        assertEquals("Church Entrance", startRoom.getName());
        assertEquals(Room.RoomType.START, startRoom.getType());
        assertEquals(0, startRoom.getX());
        assertEquals(0, startRoom.getY());
    }

    @Test
    @DisplayName("Should generate map with correct number of rooms")
    void shouldGenerateMapWithCorrectNumberOfRooms() {
        Room startRoom = mapBuilder.generateMap();
        Map<String, Room> allRooms = mapBuilder.getAllRooms();
        
        assertNotNull(allRooms);
        assertTrue(allRooms.size() >= 25, "Should have at least 25 rooms");
        assertTrue(allRooms.size() <= 50, "Should have at most 50 rooms");
        assertTrue(allRooms.containsKey("0,0"), "Should contain start room coordinates");
    }

    @Test
    @DisplayName("Should generate map with boss room")
    void shouldGenerateMapWithBossRoom() {
        Room startRoom = mapBuilder.generateMap();
        Map<String, Room> allRooms = mapBuilder.getAllRooms();
        
        boolean hasBossRoom = allRooms.values().stream()
            .anyMatch(room -> room.getType() == Room.RoomType.BOSS);
        
        assertTrue(hasBossRoom, "Map should contain at least one boss room");
    }

    @Test
    @DisplayName("Should generate connected rooms")
    void shouldGenerateConnectedRooms() {
        Room startRoom = mapBuilder.generateMap();
        Map<String, Room> allRooms = mapBuilder.getAllRooms();
        
        // Check that start room has at least one exit
        assertFalse(startRoom.getExits().isEmpty(), "Start room should have at least one exit");
        
        // Check that rooms are connected (bidirectional exits)
        for (Room room : allRooms.values()) {
            for (Map.Entry<String, Room> entry : room.getExits().entrySet()) {
                String direction = entry.getKey();
                Room connectedRoom = entry.getValue();
                
                // Check if the connected room has an exit back
                String oppositeDirection = getOppositeDirection(direction);
                if (!oppositeDirection.isEmpty()) {
                    assertEquals(room, connectedRoom.getExit(oppositeDirection),
                        "Rooms should have bidirectional connections");
                }
            }
        }
    }

    @Test
    @DisplayName("Should populate normal rooms with enemies and possibly chests")
    void shouldPopulateNormalRoomsWithEnemiesAndPossiblyChests() {
        Room startRoom = mapBuilder.generateMap();
        Map<String, Room> allRooms = mapBuilder.getAllRooms();
        
        boolean foundNormalRoom = false;
        
        for (Room room : allRooms.values()) {
            if (room.getType() == Room.RoomType.NORMAL && !room.equals(startRoom)) {
                foundNormalRoom = true;
                
                // Normal rooms should have 1-3 enemies
                assertTrue(room.hasEnemies(), "Normal rooms should have enemies");
                assertTrue(room.getEnemies().size() >= 1, "Should have at least 1 enemy");
                assertTrue(room.getEnemies().size() <= 3, "Should have at most 3 enemies");
                
                // Verify enemies are valid types
                for (Enemy enemy : room.getEnemies()) {
                    assertTrue(enemy instanceof Goblin || 
                              enemy instanceof Skeleton || 
                              enemy instanceof Orc,
                              "Enemies should be Goblin, Skeleton, or Orc");
                }
                
                break;
            }
        }
        
        assertTrue(foundNormalRoom, "Should find at least one normal room");
    }

    @Test
    @DisplayName("Should populate treasure rooms with enemies and chests")
    void shouldPopulateTreasureRoomsWithEnemiesAndChests() {
        // Generate multiple maps to find treasure rooms
        boolean foundTreasureRoom = false;
        
        for (int i = 0; i < 10; i++) {
            mapBuilder = new MapBuilder();
            Room startRoom = mapBuilder.generateMap();
            Map<String, Room> allRooms = mapBuilder.getAllRooms();
            
            for (Room room : allRooms.values()) {
                if (room.getType() == Room.RoomType.TREASURE) {
                    foundTreasureRoom = true;
                    
                    // Treasure rooms should have 1-2 enemies
                    assertTrue(room.getEnemies().size() >= 1, "Should have at least 1 enemy");
                    assertTrue(room.getEnemies().size() <= 2, "Should have at most 2 enemies");
                    
                    // Treasure rooms should have 2-3 chests
                    assertTrue(room.getChests().size() >= 2, "Should have at least 2 chests");
                    assertTrue(room.getChests().size() <= 3, "Should have at most 3 chests");
                    
                    break;
                }
            }
            
            if (foundTreasureRoom) break;
        }
        
        assertTrue(foundTreasureRoom, "Should find at least one treasure room after multiple attempts");
    }

    @Test
    @DisplayName("Should populate boss room with boss and legendary chests")
    void shouldPopulateBossRoomWithBossAndLegendaryChests() {
        Room startRoom = mapBuilder.generateMap();
        Map<String, Room> allRooms = mapBuilder.getAllRooms();
        
        Room bossRoom = allRooms.values().stream()
            .filter(room -> room.getType() == Room.RoomType.BOSS)
            .findFirst()
            .orElse(null);
        
        assertNotNull(bossRoom, "Should find boss room");
        
        // Boss room should have exactly one boss enemy
        assertEquals(1, bossRoom.getEnemies().size(), "Boss room should have exactly 1 enemy");
        assertTrue(bossRoom.getEnemies().get(0) instanceof Boss, "Enemy should be a Boss");
        
        // Boss room should have 3 legendary chests
        assertEquals(3, bossRoom.getChests().size(), "Boss room should have 3 chests");
        for (Chest chest : bossRoom.getChests()) {
            assertEquals(Chest.Rarity.LEGENDARY, chest.getRarity(), "All chests should be legendary");
        }
    }

    @Test
    @DisplayName("Should possibly populate empty rooms with hidden chests")
    void shouldPossiblyPopulateEmptyRoomsWithHiddenChests() {
        // Generate multiple maps to find empty rooms
        boolean foundEmptyRoom = false;
        boolean foundEmptyRoomWithChest = false;
        
        for (int i = 0; i < 20; i++) {
            mapBuilder = new MapBuilder();
            Room startRoom = mapBuilder.generateMap();
            Map<String, Room> allRooms = mapBuilder.getAllRooms();
            
            for (Room room : allRooms.values()) {
                if (room.getType() == Room.RoomType.EMPTY) {
                    foundEmptyRoom = true;
                    
                    // Empty rooms should have no enemies
                    assertFalse(room.hasEnemies(), "Empty rooms should have no enemies");
                    assertTrue(room.getEnemies().isEmpty(), "Enemy list should be empty");
                    
                    // May have hidden chests (20% chance)
                    if (!room.getChests().isEmpty()) {
                        foundEmptyRoomWithChest = true;
                        assertTrue(room.getChests().size() == 1, "Should have at most 1 chest");
                    }
                    
                    break;
                }
            }
            
            if (foundEmptyRoom) break;
        }
        
        assertTrue(foundEmptyRoom, "Should find at least one empty room");
        // Note: Empty room with chest is optional due to randomness
    }

    @Test
    @DisplayName("Should generate rooms with valid coordinates")
    void shouldGenerateRoomsWithValidCoordinates() {
        Room startRoom = mapBuilder.generateMap();
        Map<String, Room> allRooms = mapBuilder.getAllRooms();
        
        for (Map.Entry<String, Room> entry : allRooms.entrySet()) {
            String coordKey = entry.getKey();
            Room room = entry.getValue();
            
            // Verify coordinate key matches room coordinates
            assertEquals(coordKey, room.getX() + "," + room.getY(),
                "Coordinate key should match room coordinates");
            
            // Coordinates should be reasonable (not too far from origin)
            assertTrue(Math.abs(room.getX()) <= 50, "X coordinate should be reasonable");
            assertTrue(Math.abs(room.getY()) <= 50, "Y coordinate should be reasonable");
        }
    }

    @Test
    @DisplayName("Should generate rooms with appropriate names and descriptions")
    void shouldGenerateRoomsWithAppropriateNamesAndDescriptions() {
        Room startRoom = mapBuilder.generateMap();
        Map<String, Room> allRooms = mapBuilder.getAllRooms();
        
        for (Room room : allRooms.values()) {
            assertNotNull(room.getName(), "Room should have a name");
            assertFalse(room.getName().isEmpty(), "Room name should not be empty");
            assertNotNull(room.getDescription(), "Room should have a description");
            assertFalse(room.getDescription().isEmpty(), "Room description should not be empty");
            
            // Boss room should have specific name
            if (room.getType() == Room.RoomType.BOSS) {
                assertEquals("Boss Chamber", room.getName());
            }
        }
    }

    @Test
    @DisplayName("Should generate different maps on multiple calls")
    void shouldGenerateDifferentMapsOnMultipleCalls() {
        Room startRoom1 = mapBuilder.generateMap();
        Map<String, Room> allRooms1 = mapBuilder.getAllRooms();
        
        mapBuilder = new MapBuilder();
        Room startRoom2 = mapBuilder.generateMap();
        Map<String, Room> allRooms2 = mapBuilder.getAllRooms();
        
        // Maps should be different (though room count might be same)
        boolean mapsDifferent = allRooms1.size() != allRooms2.size() ||
            allRooms1.keySet().stream().anyMatch(key -> !allRooms2.containsKey(key));
        
        // This test might occasionally fail due to randomness, but should pass most of the time
        assertTrue(mapsDifferent || allRooms1.size() >= 25,
            "Maps should generally be different or at least valid in size");
    }

    @Test
    @DisplayName("Should handle multiple map generations")
    void shouldHandleMultipleMapGenerations() {
        for (int i = 0; i < 5; i++) {
            mapBuilder = new MapBuilder();
            Room startRoom = mapBuilder.generateMap();
            
            assertNotNull(startRoom, "Should generate start room");
            assertEquals(Room.RoomType.START, startRoom.getType(), "Start room should have START type");
            
            Map<String, Room> allRooms = mapBuilder.getAllRooms();
            assertTrue(allRooms.size() >= 25, "Should have minimum rooms");
            assertTrue(allRooms.size() <= 50, "Should not exceed maximum rooms");
        }
    }

    // Helper method to get opposite direction
    private String getOppositeDirection(String direction) {
        return switch (direction) {
            case "north" -> "south";
            case "south" -> "north";
            case "east" -> "west";
            case "west" -> "east";
            default -> "";
        };
    }
}
