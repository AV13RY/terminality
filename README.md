# Terminality: A Java Dungeon Crawler

![Terminality Logo](https://i.vgy.me/GBzj6O.png)

![Language Logo](https://img.shields.io/badge/Language-Java-blue)
![Application Logo](https://img.shields.io/badge/Type-GUI%20Application-orange)
![Game Type Logo](https://img.shields.io/badge/Style-Text%20Based%20RPG-green)

## Overview

**Terminality** is a text-based dungeon crawler game with a custom terminal-style GUI developed in **Java Swing**.

It was Created for the 6G4Z0044: Introduction to Programming module by **Jack McGillivray**.

Players embark on a journey through procedurally generated dungeons, engaging in turn-based combat, managing inventory,
and utilizing character-specific abilities to survive encounters and ultimately defeat the final boss.

## Key Features

### Core Programming Concepts

* **Custom GUI Terminal:** Built with Java Swing, featuring multiple panels for game display, character visualization,
  stats, and command history.
* **Command Parser:** Sophisticated input processing system handling various game commands through text input.
* **Procedural Generation:** Dynamic dungeon layout creation using the `MapBuilder` class for unique playthroughs.
* **State Management:** Complex game state handling including combat, exploration, and character creation phases.
* **Collections & Data Structures:** Extensive use of `HashMap`, `ArrayList`, and other collections for inventory, room
  management, and game data.

### Advanced Features

* **Character Classes:** Three unique playable classes with distinct playstyles:
    - **Knight:** High defense warrior with sword/mace options
    - **Mage:** Glass cannon spellcaster with grimoire/orb choices
    - **Reaper:** Balanced fighter with scythe/death magic abilities

* **Combat System:**
    - Turn-based combat with attack/flee options
    - Enemy AI with unique attack patterns
    - Boss encounters with multiple phases
    - Damage calculation incorporating equipment bonuses

* **Item System:**
    - Multiple item types: Weapons, Armor, Accessories, Consumables
    - Rarity tiers: Common, Uncommon, Rare, Legendary
    - Equipment management with stat bonuses
    - Chest loot system with rarity-based rewards

* **Visual Features:**
    - ASCII art for characters, titles, and scenes
    - Animated character sprites using threading
    - Color-coded terminal output
    - Interactive map display with room symbols

### Technical Implementation

* **Object-Oriented Design:**
    - Abstract `Character` base class for Player and Enemy inheritance
    - `Item` hierarchy with specialized subclasses
    - `Room` and `MapBuilder` for world generation
    - Interface usage for equipment handling

* **Multi-threading:** Separate threads for character animations to maintain responsive gameplay

* **Event-Driven Architecture:** Swing event listeners for real-time command processing

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)** 17 or higher
- **Gradle** (for build management)

### How to Run

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/AV13RY/terminality.git
   cd terminality
   ```
2. **Run with IDE built in functionality.**

OR

2. **Build with Gradle:**
   ```bash
   ./gradlew build
   ```

3. **Run the Game:**
   ```bash
   ./gradlew run
   ```

   Or run the JAR directly:
   ```bash
   java -jar app/build/libs/app.jar
   ```

## Game Commands

### System Commands

| Command          | Purpose                        | Example      |
|------------------|--------------------------------|--------------|
| `colour [color]` | Change terminal text color     | `colour red` |
| `clear`          | Clear the terminal display     | `clear`      |
| `help`           | Display context-sensitive help | `help`       |
| `exit`           | Quit the game                  | `exit`       |

### Character Creation

| Command        | Purpose                | Example        |
|----------------|------------------------|----------------|
| `start`        | Begin the game         | `start`        |
| `name [name]`  | Set character name     | `name Jack`    |
| `class [type]` | Choose character class | `class knight` |
| `choose [1/2]` | Select starting weapon | `choose 1`     |
| `proceed`      | Continue to next area  | `proceed`      |

### Exploration

| Command            | Purpose              | Example      |
|--------------------|----------------------|--------------|
| `move [direction]` | Navigate rooms       | `move north` |
| `look`             | Examine current room | `look`       |
| `map`              | Display dungeon map  | `map`        |
| `open [#]`         | Open a chest         | `open 1`     |

### Combat

| Command  | Purpose              | Example  |
|----------|----------------------|----------|
| `attack` | Attack current enemy | `attack` |
| `flee`   | Attempt to escape    | `flee`   |

### Character Management

| Command     | Purpose                  | Example     |
|-------------|--------------------------|-------------|
| `status`    | View character stats     | `status`    |
| `inventory` | View items and equipment | `inventory` |
| `use [#]`   | Use consumable item      | `use 1`     |
| `equip [#]` | Equip armor/accessory    | `equip 2`   |

## Project Structure

```
terminality/
├── app/
│   └── src/
│       └── main/
│           └── java/
│               ├── game/
│               │   └── Game.java          # Main game class with GUI
│               ├── characters/
│               │   ├── Character.java     # Abstract base class
│               │   ├── Player.java        # Player implementation
│               │   ├── Enemy.java         # Enemy base class
│               │   ├── Boss.java          # Boss enemy type
│               │   └── [Other enemies]    # Goblin, Skeleton, etc.
│               ├── items/
│               │   ├── Item.java          # Item base class
│               │   ├── Weapon.java        # Weapon implementation
│               │   ├── Armor.java         # Armor with types
│               │   ├── Accessory.java     # Stat-boosting items
│               │   └── [Consumables]      # Potions, etc.
│               └── world/
│                   ├── Room.java          # Room representation
│                   ├── MapBuilder.java    # Dungeon generation
│                   └── Chest.java         # Loot containers
├── build.gradle                           # Gradle build configuration
└── README.md                              # This file
```

## Map Legend

When viewing the in-game map:

- `[◉]` - Your current position
- `[·]` - Visited room
- `[?]` - Unexplored room
- `[T]` - Treasure room
- `[B]` - Boss room
- `─/│` - Room connections

## Author

**Jack McGillivray**  
Software Engineer & Degree Apprentice

## License

This project is submitted for academic assessment as part of the 6G4Z0044: Introduction to Programming module.