package world;

import characters.*;
import items.Chest;

import java.util.*;

public class MapBuilder {

    //--------------------------------------------------------------------------------------------------- DECLARATIONS
    private static final int MIN_ROOMS = 30;
    private static final int MAX_ROOMS = 40;
    private static final int MAX_WIDTH = 9; // max columns (fits panel nicely)
    private static final int MAX_HEIGHT = 7; // max rows (fits panel nicely)
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
    /*
     * ADDITIONAL FEATURE: Procedural map generation using coordinate-based spatial mapping and a randomised expansion algorithm.
     * Rooms are stored in a HashMap with "x,y" coordinate keys, allowing O(1) lookup for adjacent positions.
     * The algorithm starts from (0,0) and expands outward by randomly selecting rooms from a list,
     * then attempting to create new rooms in shuffled compass directions. This creates an organic
     * dungeon layout that can be different every playthrough.
     */
    public Room generateMap() {
        int roomCount = random.nextInt(MAX_ROOMS - MIN_ROOMS + 1) + MIN_ROOMS;

        // start at (0,0)
        startRoom = new Room("Church Entrance", "The entrance to the abandoned church", 0, 0, Room.RoomType.START);
        allRooms.put("0,0", startRoom);

        // expand outward
        List<Room> roomsToExpand = new ArrayList<>();
        roomsToExpand.add(startRoom);

        int generatedRooms = 1;

        while (generatedRooms < roomCount && !roomsToExpand.isEmpty()) {
            Room currentRoom = roomsToExpand.remove(random.nextInt(roomsToExpand.size()));

            // try each direction
            String[] directions = {"north", "south", "east", "west"};
            Collections.shuffle(Arrays.asList(directions));

            for (String direction : directions) {
                if (generatedRooms >= roomCount) break;

                int[] newCoords = getNewCoordinates(currentRoom.getX(), currentRoom.getY(), direction);
                String coordKey = newCoords[0] + "," + newCoords[1];

                // bounds check
                if (!isWithinBounds(newCoords[0], newCoords[1])) continue;

                // not occupied?
                if (!allRooms.containsKey(coordKey)) {
                    // room type
                    Room.RoomType type = (generatedRooms == roomCount - 1) ? Room.RoomType.BOSS : getRandomRoomType();

                    // new room
                    String roomName = (type == Room.RoomType.BOSS) ? "Boss Chamber" : ROOM_NAMES[random.nextInt(ROOM_NAMES.length)];
                    Room newRoom = new Room(roomName, generateDescription(type), newCoords[0], newCoords[1], type);

                    // connect
                    currentRoom.addExit(direction, newRoom);
                    newRoom.addExit(getOppositeDirection(direction), currentRoom);

                    // populate
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

        // ensure boss room
        if (bossRoom == null) {
            createBossRoom(roomsToExpand);
        }

        // fill edges
        ensureMapReachesEdges();

        addExtraConnections();

        return startRoom;
    }

    //                                                                          POPULATE ROOMS WITH ENEMIES AND CHESTS
    private void populateRoom(Room room) {
        switch (room.getType()) {
            case NORMAL:
                // 1-3 enemies
                int enemyCount = random.nextInt(3) + 1;
                for (int i = 0; i < enemyCount; i++) {
                    room.addEnemy(generateEnemy());
                }

                // 30% chest
                if (random.nextDouble() < 0.3) {
                    room.addChest(generateChest());
                }
                break;

            case TREASURE:
                // 1-2 enemies
                for (int i = 0; i < random.nextInt(2) + 1; i++) {
                    room.addEnemy(generateEnemy());
                }

                // 2-3 chests
                for (int i = 0; i < random.nextInt(2) + 2; i++) {
                    room.addChest(generateChest());
                }
                break;

            case BOSS:
                // boss
                room.addEnemy(new Boss("Ancient Guardian", 150, 25));

                // legendary chests
                for (int i = 0; i < 3; i++) {
                    room.addChest(new Chest(Chest.Rarity.LEGENDARY));
                }
                break;

            case EMPTY:
                // 20% hidden chest
                if (random.nextDouble() < 0.2) {
                    room.addChest(generateChest());
                }
                break;
        }
    }

    //                                                                                       GENERATE ENEMIES RANDOMLY
    private Enemy generateEnemy() {
        // random enemy type
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

    //                                                                                    CHECK IF COORDS ARE IN BOUNDS
    private boolean isWithinBounds(int x, int y) {
        int halfWidth = MAX_WIDTH / 2;
        int halfHeight = MAX_HEIGHT / 2;
        return x >= -halfWidth && x <= halfWidth && y >= -halfHeight && y <= halfHeight;
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

    //                                                                              ENSURE MAP REACHES ALL FOUR EDGES
    private void ensureMapReachesEdges() {
        int halfWidth = MAX_WIDTH / 2;
        int halfHeight = MAX_HEIGHT / 2;

        // current bounds
        int minX = 0, maxX = 0, minY = 0, maxY = 0;
        for (Room room : allRooms.values()) {
            minX = Math.min(minX, room.getX());
            maxX = Math.max(maxX, room.getX());
            minY = Math.min(minY, room.getY());
            maxY = Math.max(maxY, room.getY());
        }

        // right edge
        if (maxX < halfWidth) {
            extendToEdge(halfWidth, random.nextInt(halfHeight * 2 + 1) - halfHeight, "east");
        }
        // left edge
        if (minX > -halfWidth) {
            extendToEdge(-halfWidth, random.nextInt(halfHeight * 2 + 1) - halfHeight, "west");
        }
        // top edge
        if (maxY < halfHeight) {
            extendToEdge(random.nextInt(halfWidth * 2 + 1) - halfWidth, halfHeight, "north");
        }
        // bottom edge
        if (minY > -halfHeight) {
            extendToEdge(random.nextInt(halfWidth * 2 + 1) - halfWidth, -halfHeight, "south");
        }
    }

    //                                                                                  EXTEND MAP TO REACH TARGET EDGE
    private void extendToEdge(int targetX, int targetY, String primaryDirection) {
        // closest room
        Room closest = null;
        int closestDist = Integer.MAX_VALUE;

        for (Room room : allRooms.values()) {
            int dist = Math.abs(room.getX() - targetX) + Math.abs(room.getY() - targetY);
            if (dist < closestDist) {
                closestDist = dist;
                closest = room;
            }
        }

        if (closest == null) return;

        // path to target
        Room current = closest;
        while (current.getX() != targetX || current.getY() != targetY) {
            String direction;
            if (current.getX() < targetX) direction = "east";
            else if (current.getX() > targetX) direction = "west";
            else if (current.getY() < targetY) direction = "north";
            else direction = "south";

            int[] newCoords = getNewCoordinates(current.getX(), current.getY(), direction);
            String coordKey = newCoords[0] + "," + newCoords[1];

            if (allRooms.containsKey(coordKey)) {
                current = allRooms.get(coordKey);
            } else {
                // new room
                Room newRoom = new Room(ROOM_NAMES[random.nextInt(ROOM_NAMES.length)], generateDescription(Room.RoomType.NORMAL), newCoords[0], newCoords[1], Room.RoomType.NORMAL);
                current.addExit(direction, newRoom);
                newRoom.addExit(getOppositeDirection(direction), current);
                populateRoom(newRoom);
                allRooms.put(coordKey, newRoom);
                current = newRoom;
            }
        }
    }

    //                                                                                                 ADD EXTRA PATHS
    private void addExtraConnections() {
    }

    //---------------------------------------------------------------------------------------------- GETTERS & SETTERS
    public Map<String, Room> getAllRooms() {
        return allRooms;
    }
}
