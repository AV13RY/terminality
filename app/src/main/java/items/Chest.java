package items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chest {
    public enum Rarity {
        COMMON("Common", 0.6), UNCOMMON("Uncommon", 0.3), RARE("Rare", 0.08), LEGENDARY("Legendary", 0.02);

        private final String name;
        private final double chance;

        Rarity(String name, double chance) {
            this.name = name;
            this.chance = chance;
        }
    }

    private Rarity rarity;
    private List<Item> contents;
    private boolean opened;

    public Chest(Rarity rarity) {
        this.rarity = rarity;
        this.contents = new ArrayList<>();
        this.opened = false;
        generateContents();
    }

    private void generateContents() {
        Random rand = new Random();
        int itemCount = switch (rarity) {
            case COMMON -> rand.nextInt(2) + 1; // 1-2 items
            case UNCOMMON -> rand.nextInt(2) + 2; // 2-3 items
            case RARE -> rand.nextInt(2) + 3; // 3-4 items
            case LEGENDARY -> rand.nextInt(3) + 4; // 4-6 items
        };

        for (int i = 0; i < itemCount; i++) {
            // Higher rarity chests have better chances for rare items
            Item.Rarity itemRarity = (rarity == Rarity.LEGENDARY && rand.nextDouble() < 0.5) ? Item.Rarity.LEGENDARY : ItemGenerator.selectRarity();

            contents.add(ItemGenerator.generateItem(itemRarity));
        }
    }

    public List<Item> open() {
        if (!opened) {
            opened = true;
            return new ArrayList<>(contents);
        }
        return new ArrayList<>();
    }

    public boolean isOpened() {
        return opened;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
