package items;

public class Weapon {
    private String name;
    private int damage;
    private String description;
    private String type; // "physical" or "magic"

    public Weapon(String name, int damage, String description, String type) {
        this.name = name;
        this.damage = damage;
        this.description = description;
        this.type = type;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
}
