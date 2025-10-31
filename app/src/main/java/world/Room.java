package world;

import characters.Enemy;
import items.Chest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Map<String, Room> exits;
    private List<Enemy> enemies;
    private List<Chest> chests;
    private boolean visited;
    private int x, y; // Coordinates for map visualization
    private RoomType type;

    public enum RoomType {
        START, NORMAL, TREASURE, BOSS, EMPTY
    }

    public Room(String name, String description, int x, int y, RoomType type) {
        this.name = name;
        this.description = description;
        this.x = x;
        this.y = y;
        this.type = type;
        this.exits = new HashMap<>();
        this.enemies = new ArrayList<>();
        this.chests = new ArrayList<>();
        this.visited = false;
    }

    // Add exit connections
    public void addExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public Map<String, Room> getExits() {
        return exits;
    }

    // Enemy management
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public boolean hasEnemies() {
        return !enemies.isEmpty();
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    // Chest management
    public void addChest(Chest chest) {
        chests.add(chest);
    }

    public List<Chest> getChests() {
        return new ArrayList<>(chests);
    }

    public boolean hasAccessibleChests() {
        return !hasEnemies() && !chests.isEmpty();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public RoomType getType() {
        return type;
    }
}
