package world;

import characters.*;
import items.Chest;

import java.util.*;

public class MapBuilder {

    //--------------------------------------------------------------------------------------------------- DECLARATIONS
    private static final int MIN_ROOMS = 25;
    private static final int MAX_ROOMS = 50;
    private static final String[] ROOM_NAMES = {"Dusty Corridor", "Ancient Chamber", "Forgotten Hall", "Cryptic Passage", "Shadow Gallery", "Bone Pit", "Cursed Sanctum", "Dark Alcove", "Ruined Chapel", "Abandoned Vault", "Echoing Cavern", "Silent Tomb"};

    private Random random;
    private Map<String, Room> allRooms;
    private Room startRoom;
    private Room bossRoom;

    //--------------------------------------------------------------------------------------------------- CORE METHODS
    //                                                                                                     CONSTRUCTOR
    public MapBuilder() {
        this.random = new Random();
        this.allRooms = new HashMap<>();
    }

    //                                                                                                    GENERATE MAP
    public Room generateMap() {
        int roomCount = random.nextInt(MAX_ROOMS - MIN_ROOMS + 1) + MIN_ROOMS;

        // Create start room at center (0,0)
        startRoom = new Room("Church Entrance", "The entrance to the abandoned church", 0, 0, Room.RoomType.START);
        allRooms.put("0,0", startRoom);

        // Generate connected rooms
        List<Room> roomsToExpand = new ArrayList<>();
        roomsToExpand.add(startRoom);

        int generatedRooms = 1;

        while (generatedRooms < roomCount && !roomsToExpand.isEmpty()) {
            Room currentRoom = roomsToExpand.remove(random.nextInt(roomsToExpand.size()));

            // Try to add rooms in each direction
            String[] directions = {"north", "south", "east", "west"};
            Collections.shuffle(Arrays.asList(directions));

            for (String direction : directions) {
                if (generatedRooms >= roomCount) break;

                int[] newCoords = getNewCoordinates(currentRoom.getX(), currentRoom.getY(), direction);
                String coordKey = newCoords[0] + "," + newCoords[1];

                // Check if position is already occupied
                if (!allRooms.containsKey(coordKey)) {
                    // Determine room type
                    Room.RoomType type = (generatedRooms == roomCount - 1) ? Room.RoomType.BOSS : getRandomRoomType();

                    // Create new room
                    String roomName = (type == Room.RoomType.BOSS) ? "Boss Chamber" : ROOM_NAMES[random.nextInt(ROOM_NAMES.length)];
                    Room newRoom = new Room(roomName, generateDescription(type), newCoords[0], newCoords[1], type);

                    // Connect rooms
                    currentRoom.addExit(direction, newRoom);
                    newRoom.addExit(getOppositeDirection(direction), currentRoom);

                    // Populate room
                    populateRoom(newRoom);

                    allRooms.put(coordKey, newRoom);
                    roomsToExpand.add(newRoom);
                    generatedRooms++;

                    if (type == Room.RoomType.BOSS) {
                        bossRoom = newRoom;
                    }
                }
            }
        }

        // Check if boss room exists, if not, make one.
        if (bossRoom == null) {
            createBossRoom(roomsToExpand);
        }

        addExtraConnections();

        return startRoom;
    }

    //                                                                          POPULATE ROOMS WITH ENEMIES AND CHESTS
    private void populateRoom(Room room) {
        switch (room.getType()) {
            case NORMAL:
                // Add 1-3 enemies
                int enemyCount = random.nextInt(3) + 1;
                for (int i = 0; i < enemyCount; i++) {
                    room.addEnemy(generateEnemy());
                }

                // 30% chance for a chest
                if (random.nextDouble() < 0.3) {
                    room.addChest(generateChest());
                }
                break;

            case TREASURE:
                // Add 1-2 enemies guarding treasure
                for (int i = 0; i < random.nextInt(2) + 1; i++) {
                    room.addEnemy(generateEnemy());
                }

                // Add 2-3 chests
                for (int i = 0; i < random.nextInt(2) + 2; i++) {
                    room.addChest(generateChest());
                }
                break;

            case BOSS:
                // Add boss enemy
                room.addEnemy(new Boss("Ancient Guardian", 150, 25));

                // Add legendary chests after boss defeat
                for (int i = 0; i < 3; i++) {
                    room.addChest(new Chest(Chest.Rarity.LEGENDARY));
                }
                break;

            case EMPTY:
                // 20% chance for a hidden chest
                if (random.nextDouble() < 0.2) {
                    room.addChest(generateChest());
                }
                break;
        }
    }

    //                                                                                       GENERATE ENEMIES RANDOMLY
    private Enemy generateEnemy() {
        // Generate random enemies based on difficulty
        int type = random.nextInt(3);
        return switch (type) {
            case 0 -> new Goblin();
            case 1 -> new Skeleton();
            case 2 -> new Orc();
            default -> new Goblin();
        };
    }

    //                                                                                        GENERATE CHESTS RANDOMLY
    private Chest generateChest() {
        double roll = random.nextDouble();
        if (roll < 0.6) return new Chest(Chest.Rarity.COMMON);
        else if (roll < 0.9) return new Chest(Chest.Rarity.UNCOMMON);
        else if (roll < 0.98) return new Chest(Chest.Rarity.RARE);
        else return new Chest(Chest.Rarity.LEGENDARY);
    }

    //                                                                                     GENERATE ROOM TYPE RANDOMLY
    private Room.RoomType getRandomRoomType() {
        double roll = random.nextDouble();
        if (roll < 0.1) return Room.RoomType.TREASURE;
        else if (roll < 0.2) return Room.RoomType.EMPTY;
        else return Room.RoomType.NORMAL;
    }

    //                                                                       GET ROOMS COORDINATES FOR THE MAP DISPLAY
    private int[] getNewCoordinates(int x, int y, String direction) {
        return switch (direction) {
            case "north" -> new int[]{x, y + 1};
            case "south" -> new int[]{x, y - 1};
            case "east" -> new int[]{x + 1, y};
            case "west" -> new int[]{x - 1, y};
            default -> new int[]{x, y};
        };
    }

    //                                                     GET THE OPPOSITE DIRECTION OF A GIVEN DIRECTION FOR FLEEING
    private String getOppositeDirection(String direction) {
        return switch (direction) {
            case "north" -> "south";
            case "south" -> "north";
            case "east" -> "west";
            case "west" -> "east";
            default -> "";
        };
    }

    //                                                                                       GENERATE ROOM DESCRIPTION
    private String generateDescription(Room.RoomType type) {
        return switch (type) {
            case BOSS -> "A massive chamber with ancient pillars. Dark energy pulses from the center.";
            case TREASURE -> "Glittering treasures catch your eye in this ornate room.";
            case EMPTY -> "An eerily quiet chamber with nothing but dust and shadows.";
            default -> "A dimly lit room with stone walls and the smell of decay.";
        };
    }

    //                                                                                            CREATE THE BOSS ROOM
    private void createBossRoom(List<Room> rooms) {
    }

    //                                                                                                 ADD EXTRA PATHS
    private void addExtraConnections() {
    }

    //---------------------------------------------------------------------------------------------- GETTERS & SETTERS
    public Map<String, Room> getAllRooms() {
        return allRooms;
    }
}
