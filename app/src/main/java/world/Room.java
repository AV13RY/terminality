package world;

public class Room {
    private String name;
    private String description;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name.toLowerCase();
    }

    public String getDescription() {
        return description;
    }

}
