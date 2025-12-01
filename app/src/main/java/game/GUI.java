package game;

import characters.Enemy;
import characters.Player;
import items.*;
import world.MapBuilder;
import world.Room;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static game.Messages.churchIntroMessage;

public class GUI {


    //------------------------------------------------------------------------------------------------- GAME VARIABLES
    //                                                                                                GUI DECLARATIONS
    private JTextArea display;
    private JTextField terminal;
    private JTextArea characterArea;
    private JTextArea statsArea;
    private JTextArea commandLog;

    //                                                                                        COMMAND LOG DECLARATIONS
    private List<String> commandHistory;
    private static int commandCount;

    //                                                                                         GAME STATE DECLARATIONS
    private static MapBuilder mapBuilder;
    private static Room currentRoom;
    private static Player player;
    private static String NAME = "";
    private static String CLASS = "";
    private static Enemy currentEnemy;
    private boolean inCombat;
    private final Random random;

    //                                                                                             COLOUR DECLARATIONS
    private final Color RED = Color.RED;
    private final Color GREEN = Color.GREEN;
    private final Color BLUE = Color.BLUE;
    private final Color YELLOW = Color.YELLOW;
    private final Color CYAN = Color.CYAN;
    private final Color MAGENTA = Color.MAGENTA;
    private final Color WHITE = Color.WHITE;
    private final Color BLACK = Color.BLACK;
    private final Color DEFAULT = new Color(0xD8125B);
    private final Color DEFAULT2 = new Color(0x424549);
    private final Color DEFAULT3 = new Color(0x4b0019);

    //--------------------------------------------------------------------------------------------------- CORE METHODS
    //                                                                                                GAME CONSTRUCTOR
    public GUI() {

        this.inCombat = false;
        currentEnemy = null;
        this.random = new Random();

        initialiseUI();
        initialiseWorld();
        println(Messages.tutorialTitleMessage());
        println(Messages.tutorialIntroMessage());

        testing("reaper"); // temporary testing
    }

    //------------------------------------------------------------------------------------------ SPECIFIC TEXT METHODS
    //                                                                                            DISPLAY PLAYER STATS
    private void displayStats() {
        statsArea.setText("");
        println(player.displayStatus(), statsArea);
    }

    //                                                                                     DISPLAY CHARACTER ANIMATION
    private void displayCharacter(String CLASS) {
        Thread idleAnimationThread;
        switch (CLASS) {
            case "knight":
                characterArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
                idleAnimationThread = idleAnimation(Messages.displayKnight(1), Messages.displayKnight(2), 1500, 750);
                idleAnimationThread.start();
                break;
            case "mage":
                characterArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
                idleAnimationThread = idleAnimation(Messages.displayMage(1), Messages.displayMage(2), 1500, 750);
                idleAnimationThread.start();
                break;
            case "reaper":
                characterArea.setFont(new Font("Monospaced", Font.PLAIN, 9));
                idleAnimationThread = idleAnimation(Messages.displayReaper(1), Messages.displayReaper(2), 2500, 150);
                idleAnimationThread.start();
                break;
        }
    }

    //--------------------------------------------------------------------------------------------- PROCESSING METHODS
    //                                                                                      GENERAL COMMAND PROCESSING
    private void processCommand(String input) {

        println("\n > " + input); // displays the inputted command in display area.
        updateCommandHistory(input);

        if (mapBuilder != null) {
            processGameCommand(input);
            return;
        }

        if (input.startsWith("colour ")) {
            String[] parts = input.split(" ", 2);
            if (parts.length > 1) {
                changeTextColor(parts[1]);
            } else {
                println("Please specify a color. Available colors: red, green, blue, yellow, cyan, magenta, white");
            }
        } else if (input.equals("exit")) {
            System.exit(0);
        } else if (input.equals("help")) {
            switch (currentRoom.getName().toLowerCase()) {
                case "tutorial":
                    println(Messages.tutorialHelpMessage());
                    break;
                case "graveyard":
                    println(Messages.graveyardHelpMessage());
                    break;
                case "church":
                    println(Messages.churchHelpMessage());
                    break;
            }
        } else if (input.equals("clear")) {
            clearScreen();
        } else if (input.equals("map")) {
            println(Messages.displayMap());
        }

        switch (currentRoom.getName().toLowerCase()) {
            case "tutorial":
                if (input.equals("start")) {
                    currentRoom = new Room("Graveyard", "A dark and eerie graveyard.", 0, 0, Room.RoomType.START);

                    display.setText("");
                    println(Messages.graveyardTitleMessage());
                    println(Messages.graveyardIntroMessage());
                }
                break;
            case "graveyard":

                if (input.startsWith("name")) {
                    NAME = input.replace("name", "").toLowerCase().trim();
                    println("\nYour name will be: " + NAME);
                    println("\nAnd what will your class be?");
                    println("- Use the command: [ class <knight,mage,reaper> ]");
                }

                if (input.startsWith("class")) {
                    String replace = input.replace("class", "").toLowerCase().trim();
                    if (input.contains("knight")) {
                        CLASS = replace;
                        displayCharacter(CLASS);
                    } else if (input.contains("mage")) {
                        CLASS = replace;
                        displayCharacter(CLASS);
                    } else if (input.contains("reaper")) {
                        CLASS = replace;
                        displayCharacter(CLASS);
                    } else {
                        println("Please specify one of the designated classes.");
                    }

                    if (!CLASS.isEmpty()) {
                        println("Your class will be: " + CLASS);
                        println("\n If you wish to alter your memory, this is your last chance.");
                        println("However if this is how you choose to remember yourself:");
                        println("- Use the command: [ proceed ]\n");
                    }
                }

                if (input.equals("proceed")) {
                    println(NAME + ", a " + CLASS + " from the Lands Between is ready to start their journey.");
                    switch (CLASS) {
                        case "knight":
                            player = new Player(NAME, Player.KNIGHT);
                            break;
                        case "mage":
                            player = new Player(NAME, Player.MAGE);
                            break;
                        case "reaper":
                            player = new Player(NAME, Player.REAPER);
                            break;
                    }
                    println("*YOU BEGIN TO MOVE TO THE NEXT ROOM.*");
                    currentRoom = new Room("Church", "An ancient church, emanating an unusual presence", 0, 0, Room.RoomType.START);
                    display.setText("");
                    println(Messages.churchTitleMessage());
                    println(churchIntroMessage(CLASS));
                }
                break;
            case "church":
                if (input.startsWith("choose ")) {
                    String choice = input.replace("choose", "").trim();
                    if (choice.equals("1") || choice.equals("2")) {
                        handleWeaponChoice(choice);
                    } else {
                        println("Please choose either 1 or 2.");
                    }
                } else if (input.equals("inventory")) {
                    println(Messages.displayInventory());
                } else if (input.equals("proceed")) {
                    startAdventure();
                } else {
                    println("Unknown command. Please Try Again.");
                }
                break;
        }
    }

    //                                                                                      IN-GAME COMMAND PROCESSING
    private void processGameCommand(String input) {

        if (inCombat && !input.equalsIgnoreCase("flee") && !input.equalsIgnoreCase("status") && !input.equalsIgnoreCase("inventory") && !input.equalsIgnoreCase("help")) {
            if (input.equalsIgnoreCase("attack")) {
                performCombat();
            } else {
                println("You're in combat! You must attack or flee!");
            }
            return;
        }

        // Common commands available anywhere
        if (input.equals("help")) {
            println(Messages.inGameHelpMessage());
            return;
        } else if (input.equals("map")) {
            println(Messages.displayMap());
            return;
        } else if (input.equals("status")) {
            displayStats();
            return;
        } else if (input.equals("inventory")) {
            println(Messages.displayInventory());
            return;
        } else if (input.startsWith("use ")) {
            String itemId = input.substring(4).trim();
            useItem(itemId);
        } else if (input.startsWith("equip ")) {
            String itemId = input.substring(6).trim();
            equipItem(itemId);
        }

        // Combat commands
        if (inCombat) {
            if (input.equals("flee")) {
                attemptFlee();
            } else {
                println("You're in combat! Use 'attack' to fight or 'flee' to escape!");
            }
            return;
        }

        // Movement commands
        if (input.startsWith("move ")) {
            String direction = input.substring(5).trim();
            movePlayer(direction);
        } else if (input.equals("look")) {
            println(Messages.displayCurrentRoom());
        } else if (input.startsWith("open ")) {
            String target = input.substring(5).trim();
            openChest(target);
        } else {
            println("Unknown command. Type 'help' for available commands.");
        }
    }

    //                                                                                          DISPLAY PROCESSING
    // Custom println method to display text in the UI display area.
    public void println(String text) {
        display.append(text + "\n");
        display.setCaretPosition(display.getDocument().getLength());
    }

    public void println(String text, JTextArea area) {
        area.append(" " + text + "\n");
        area.setCaretPosition(area.getDocument().getLength());
    }

    //                                                                                        PROCESSING WEAPON CHOICE
    private void handleWeaponChoice(String choice) {
        Weapon chosenWeapon = null;

        switch (CLASS) {
            case "knight":
                if (choice.equals("1")) {
                    chosenWeapon = new Weapon("Mace", 15, "A virulent ball of swinging death.", Item.Rarity.COMMON, 10);
                    println("\nYou grasp the MACE, feeling its weight and power.");
                    println("The weapon pulses with righteous fury as you swing it experimentally.");
                } else if (choice.equals("2")) {
                    chosenWeapon = new Weapon("Sword & Shield", 12, "The bastion of any successful knight.", Item.Rarity.COMMON, 10);
                    println("\nYou take up the SWORD & SHIELD, feeling their perfect balance.");
                    println("The blade gleams with deadly purpose while the shield promises protection.");
                }
                break;

            case "mage":
                if (choice.equals("1")) {
                    chosenWeapon = new Weapon("Grimoire", 20, "An ancient tome of forbidden knowledge.", Item.Rarity.COMMON, 10);
                    println("\nYou open the GRIMOIRE, its pages crackling with arcane energy.");
                    println("Forbidden knowledge floods your mind as the tome accepts you as its master.");
                } else if (choice.equals("2")) {
                    chosenWeapon = new Weapon("Unstable Orb", 18, "A chaotic orb of pure annihilation.", Item.Rarity.COMMON, 10);
                    println("\nYou grasp the UNSTABLE ORB, feeling its chaotic energy surge through you.");
                    println("The orb pulses erratically, barely contained destruction at your fingertips.");
                }
                break;

            case "reaper":
                if (choice.equals("1")) {
                    chosenWeapon = new Weapon("Scythe", 25, "The cold, clean edge all mortals must meet.", Item.Rarity.COMMON, 10);
                    println("\nYou grip the SCYTHE, its blade singing a song of endings.");
                    println("The weapon feels like an extension of death itself in your hands.");
                } else if (choice.equals("2")) {
                    chosenWeapon = new Weapon("Death Magic", 17, "A dark conduit for siphoning souls.", Item.Rarity.COMMON, 10);
                    println("\nYou channel DEATH MAGIC, feeling the cold touch of the void.");
                    println("Dark energy swirls around you, hungry for the essence of life.");
                }
                break;
        }

        if (chosenWeapon != null) {
            player.addWeapon(chosenWeapon);
            displayStats(); // Update stats display

            println("\n 【 " + chosenWeapon.getName() + " has been added to your inventory and equipped! 】\n\n");
            println("\nThe spectral overseer nods approvingly.\n");
            println("\"Your choice is made. May it serve you well in the trials ahead.\"\n");
            println("\nThe overseer dissipates, and you notice a door at the back of the church beckoning to you.");
            println("\nType [ proceed ] to begin the dungeon.");

        }
    }

    //                                                                                              COMMAND PROCESSING
    private void updateCommandHistory(String command) {
        commandHistory.add(command);
        commandCount++;

        // Clear and rebuild the display
        commandLog.setText("   【\uFEFFＣＯＭＭＡＮＤ　ＨＩＳＴＯＲＹ】\n");
        commandLog.append(" ═══════════════════════════════════════\n\n");

        // Display commands in reverse order with git-style branching
        for (int i = commandHistory.size() - 1; i >= 0; i--) {
            String cmd = commandHistory.get(i);
            int cmdNumber = i + 1;

            // Determine command type for coloring/branching
            String node = "○";

            // Special nodes for certain commands
            if (cmd.startsWith("start")) {
                node = "◆"; // Diamond for start commands
            } else if (cmd.startsWith("move")) {
                node = "→"; // Arrow for movement
            } else if (cmd.startsWith("attack")) {
                node = "⚔"; // Sword for combat
            } else if (cmd.startsWith("name") || cmd.startsWith("class")) {
                node = "★"; // Star for character creation
            } else if (cmd.equals("help") || cmd.equals("clear")) {
                node = "◌"; // Hollow circle for utility commands
            }

            // Build the visualization
            String str = "  ╟─" + node + " [" + String.format("%03d", cmdNumber) + "] " + cmd + "\n";
            if (i == commandHistory.size() - 1) {
                // Most recent command (CURRENT)
                commandLog.append("  ╔═ CURRENT\n");
                commandLog.append("  ║\n");
                commandLog.append(str);
            } else if (i == 0) {
                // First command (root)
                commandLog.append("  ║\n");
                commandLog.append(str);
                commandLog.append("  ║\n");
                commandLog.append("  ╚═ ORIGIN\n");
            } else {
                // Middle commands
                commandLog.append("  ║\n");

                // Add branch indicators
                if (i < commandHistory.size() - 1) {
                    String nextCmd = commandHistory.get(i + 1);
                    String prevCmd = commandHistory.get(i - 1);

                    // Branch merge/split visualization
                    if (isCommandTypeChange(cmd, nextCmd)) {
                        commandLog.append("  ╠═╗\n");
                        commandLog.append("  ║ ╚─" + node + " [" + String.format("%03d", cmdNumber) + "] " + cmd + "\n");
                    } else if (isCommandTypeChange(prevCmd, cmd)) {
                        commandLog.append("  ╠═╝\n");
                        commandLog.append(str);
                    } else {
                        commandLog.append(str);
                    }
                } else {
                    commandLog.append(str);
                }
            }
        }

        // Add summary at bottom
        commandLog.append("\n  ───────────────\n");
        commandLog.append("  Total: " + commandCount + " commands\n");

        // Auto-scroll to top
        commandLog.setCaretPosition(0);
    }

    private boolean isCommandTypeChange(String cmd1, String cmd2) {
        return !getCommandType(cmd1).equals(getCommandType(cmd2)); //checks if the type of cmd has changed for branches
    }

    private String getCommandType(String cmd) {
        if (cmd.startsWith("move")) return "movement";
        if (cmd.startsWith("attack") || cmd.equals("flee") || cmd.startsWith("open") || cmd.startsWith("use") || cmd.startsWith("equip"))
            return "combat";
        if (cmd.startsWith("name") || cmd.startsWith("class") || cmd.startsWith("choose")) return "character";
        if (cmd.equals("help") || cmd.equals("clear") || cmd.startsWith("colour") || cmd.equals("look") || cmd.equals("map") || cmd.equals("inventory"))
            return "utility";
        return "other";
    }

    //                                                                                        PROCESSING STARTING GAME
    private void startAdventure() {
        println("\nGenerating dungeon layout...");
        mapBuilder = new MapBuilder();
        currentRoom = mapBuilder.generateMap();
        currentRoom.setVisited(true);

        println("\nYou step through the church doors into the dungeon beyond...");
        println(Messages.displayCurrentRoom());
        println(Messages.displayMap());

    }

    //                                                                                      PROCESSING PLAYER MOVEMENT
    private void movePlayer(String direction) {
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom != null) {
            // Check if we're in combat
            if (inCombat) {
                println("You can't leave while in combat! Defeat your enemies first!");
                return;
            }

            // Move to the next room
            currentRoom = nextRoom;
            currentRoom.setVisited(true);

            // Clear display and show new room
            display.setText("");
            println(Messages.displayCurrentRoom());

            // Update stats display
            displayStats();

            // Check for enemies and start combat
            if (currentRoom.hasEnemies()) {
                checkForCombat();
            }
        } else {
            println("You can't go that way!");
        }

    }

    //                                                                                       PROCESSING PLAYER FLEEING
    private void attemptFlee() {
        if (!inCombat) {
            println("You're not in combat!");
            return;
        }

        Random rand = new Random();
        if (rand.nextDouble() < 0.5) { // 50% chance to flee
            println("You successfully flee from combat!");
            inCombat = false;
            currentEnemy = null;

            // Move to a random adjacent room
            List<String> exits = new ArrayList<>(currentRoom.getExits().keySet());
            if (!exits.isEmpty()) {
                String randomExit = exits.get(rand.nextInt(exits.size()));
                println("You run " + randomExit + "!");
                movePlayer(randomExit);
            }
        } else {
            println("You failed to escape!");
            println("\n" + currentEnemy.getAttackMessage());
            int damage = Math.max(1, currentEnemy.getAttackDamage() - player.getDefense());
            player.takeDamage(damage);
            println("You take " + damage + " damage while trying to flee!");

            if (player.getCurrentHealth() <= 0) {
                handlePlayerDeath();
            } else {
                println(Messages.displayCombatStatus());
            }
        }
    }

    //                                                                                        PROCESSING OPENING CHEST
    private void openChest(String chestIdentifier) {
        if (!currentRoom.hasAccessibleChests()) {
            println("There are no chests to open here, or enemies are still present!");
            return;
        }

        List<Chest> unopenedChests = new ArrayList<>();
        for (Chest chest : currentRoom.getChests()) {
            if (chest.isClosed()) {
                unopenedChests.add(chest);
            }
        }

        if (unopenedChests.isEmpty()) {
            println("All chests in this room have already been opened.");
            return;
        }

        Chest targetChest = null;
        try {
            int chestNum = Integer.parseInt(chestIdentifier);
            if (chestNum > 0 && chestNum <= unopenedChests.size()) {
                targetChest = unopenedChests.get(chestNum - 1);
            }
        } catch (NumberFormatException e) {
            targetChest = unopenedChests.getFirst();
        }

        if (targetChest != null) {
            println("\n📦 Opening " + targetChest.getRarity().name() + " chest...");
            List<Item> items = targetChest.open();

            println("You found:");
            for (Item item : items) {
                println("  • " + item.toString() + " - " + item.getDescription());
                player.addItem(item);
            }

            // Also add some gold based on chest rarity
            int goldAmount = switch (targetChest.getRarity()) {
                case COMMON -> 10 + random.nextInt(20);
                case UNCOMMON -> 30 + random.nextInt(40);
                case RARE -> 70 + random.nextInt(60);
                case LEGENDARY -> 150 + random.nextInt(100);
            };

            player.addGold(goldAmount);
            println("  • " + goldAmount + " gold");
        } else {
            println("Invalid chest number. Use 'open 1', 'open 2', etc.");
        }
    }

    //                                                                                        PROCESSING USING AN ITEM
    private void useItem(String itemIdentifier) {
        List<Item> consumables = player.getFullInventory().stream().filter(item -> item.getType() == Item.ItemType.CONSUMABLE).toList();

        if (consumables.isEmpty()) {
            println("You have no consumable items to use.");
            return;
        }

        try {
            int itemNum = Integer.parseInt(itemIdentifier) - 1;
            if (itemNum >= 0 && itemNum < consumables.size()) {
                Item item = consumables.get(itemNum);

                if (player.useItem(item)) {
                    println("You used " + item.getName() + "!");
                    displayStats();

                    if (item instanceof HealthPotion) {
                        println("You recovered " + ((HealthPotion) item).getHealAmount() + " health!");
                    } else if (item instanceof ManaPotion) {
                        println("You recovered " + ((ManaPotion) item).getManaAmount() + " mana!");
                    }
                } else {
                    println("You can't use that item right now.");
                }
            } else {
                println("Invalid item number.");
            }
        } catch (NumberFormatException e) {
            println("Please specify an item number. Use 'inventory' to see your items.");
        }
    }

    //                                                                                       PROCESSING EQUIPPING ITEM
    private void equipItem(String itemIdentifier) {
        List<Item> equipables = player.getFullInventory().stream().filter(item -> item.getType() == Item.ItemType.ARMOR || item.getType() == Item.ItemType.ACCESSORY).toList();

        if (equipables.isEmpty()) {
            println("You have no equipment to equip.");
            return;
        }

        try {
            int itemNum = Integer.parseInt(itemIdentifier) - 1;
            if (itemNum >= 0 && itemNum < equipables.size()) {
                Item item = equipables.get(itemNum);

                if (player.useItem(item)) {
                    println("You equipped " + item.getName() + "!");
                    displayStats(); // Update stats display
                } else {
                    println("Failed to equip item.");
                }
            } else {
                println("Invalid item number.");
            }
        } catch (NumberFormatException e) {
            println("Please specify an item number. Use 'inventory' to see your items.");
        }
    }

    //                                                                                      PROCESSING RESTARTING GAME
    private void restartGame() {
        // Reset all game state
        NAME = "";
        CLASS = "";
        player = null;
        mapBuilder = null;
        currentEnemy = null;
        inCombat = false;
        commandHistory.clear();
        commandCount = 0;

        // Reset UI
        display.setText("");
        characterArea.setText("");
        statsArea.setText("");
        commandLog.setText("   【\uFEFFＣＯＭＭＡＮＤ　ＨＩＳＴＯＲＹ】\n");
        commandLog.append(" ═══════════════════════════════════════\n\n");

        // Re-initialize
        initialiseWorld();

        // Remove death event listener and restore normal command processing
        terminal.removeActionListener(terminal.getActionListeners()[0]);
        terminal.addActionListener(e -> {
            String input = terminal.getText().trim().toLowerCase();
            processCommand(input);
            terminal.setText("");
        });

        // Show tutorial again
        println(Messages.tutorialTitleMessage());
        println(Messages.tutorialIntroMessage());
    }

    //------------------------------------------------------------------------------------------------- COMBAT METHODS

    //                                                                                             CHECKING FOR COMBAT
    private void checkForCombat() {
        if (currentRoom.hasEnemies() && !currentRoom.getEnemies().isEmpty()) {
            currentEnemy = currentRoom.getEnemies().getFirst(); // Get first enemy
            inCombat = true;
            println("\n⚔️ COMBAT INITIATED!");
            println("You encounter a " + currentEnemy.getName() + "!");
            println(currentEnemy.getEnemyType());
            println(Messages.displayCombatStatus());
        }
    }

    //                                                                                               PERFORMING COMBAT
    private void performCombat() {
        // Player attacks
        int playerDamage = calculatePlayerDamage();
        println("\nYou attack the " + currentEnemy.getName() + " for " + playerDamage + " damage!");
        currentEnemy.takeDamage(playerDamage);

        // Check if enemy is defeated
        if (currentEnemy.isDead()) {
            println("\n🎉 Victory! You defeated the " + currentEnemy.getName() + "!");

            // Give rewards
            int expGained = currentEnemy.getExperienceValue();
            int goldGained = random.nextInt(20) + 10;
            player.gainExperience(expGained);
            player.addGold(goldGained);
            println("You gained " + expGained + " EXP and " + goldGained + " gold!");
            displayStats();

            // Remove enemy from room
            currentRoom.getEnemies().remove(currentEnemy);

            // Exit combat
            inCombat = false;
            currentEnemy = null;

            if (currentRoom.hasEnemies() && !currentRoom.getEnemies().isEmpty()) {
                println("\nThere are more enemies in the room!");
                checkForCombat();
            } else {
                println("\nThe room is now clear of enemies.");
            }
            return;
        }

        // Enemy attacks back
        int enemyDamage = currentEnemy.getAttack();
        println("\nThe " + currentEnemy.getName() + " attacks you for " + enemyDamage + " damage!");
        player.takeDamage(enemyDamage);

        displayStats();

        if (player.isDead()) {
            handlePlayerDeath();
            return;
        }

        // Show updated combat status
        println(Messages.displayCombatStatus());
    }

    //                                                                                       CALCULATING PLAYER DAMAGE
    private int calculatePlayerDamage() {
        int baseDamage = player.getAttack();
        if (player.getEquippedWeapon() != null) {
            baseDamage += player.getEquippedWeapon().getAttackBonus();
        }
        // Add some randomness (±20%)
        int variance = (int) (baseDamage * 0.2);
        return baseDamage + random.nextInt(variance * 2 + 1) - variance;
    }

    //                                                                                           HANDLING PLAYER DEATH
    private void handlePlayerDeath() {
        // Stop any ongoing animations
        inCombat = false;

        // Clear the display for death screen
        display.setText("");

        // Display death messages
        println(Messages.displayDeathArt());
        println(Messages.displayDeathInfo(CLASS));

        currentEnemy = null;

        terminal.removeActionListener(terminal.getActionListeners()[0]);
        terminal.addActionListener(e -> {
            String input = terminal.getText().trim().toLowerCase();
            if (input.equals("restart")) {
                restartGame();
            } else if (input.equals("exit")) {
                System.exit(0);
            } else {
                println("\n > " + input);
                println("Please type 'restart' or 'exit'");
            }
            terminal.setText("");
        });
    }

    //----------------------------------------------------------------------------------------------------- UI METHODS
    //                                                                                                     TEXT COLOUR
    private void changeTextColor(String colorName) {
        Color newColor;

        switch (colorName.toLowerCase()) {
            case "red":
                newColor = RED;
                break;
            case "green":
                newColor = GREEN;
                break;
            case "blue":
                newColor = BLUE;
                break;
            case "yellow":
                newColor = YELLOW;
                break;
            case "cyan":
                newColor = CYAN;
                break;
            case "magenta":
                newColor = MAGENTA;
                break;
            case "white":
                newColor = WHITE;
                break;
            case "default":
                newColor = DEFAULT;
                break;
            default:
                println("Unknown color. Available colors: red, green, blue, yellow, cyan, magenta, white, default");
                return;
        }

        display.setForeground(newColor);
        terminal.setForeground(newColor);
        terminal.setCaretColor(newColor);
        characterArea.setForeground(newColor);
        println("Text color changed to " + colorName);
    }

    //------------------------------------------------------------------------------------------------ UTILITY METHODS

    //                                                                                                     WAIT METHOD
    private void wait(double seconds) {
        CompletableFuture.delayedExecutor((long) (seconds * 1000), TimeUnit.MILLISECONDS).execute(() -> {
        });
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static MapBuilder getMapBuilder() {
        return mapBuilder;
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public static String getName() {
        return NAME;
    }

    public static int getCommandCount() {
        return commandCount;
    }

    //                                                                                           IDLE ANIMATION THREAD
    // creates a new thread for the idle animation.
    @SuppressWarnings("BusyWait") // just stops the warning for sleeping
    private Thread idleAnimation(String character1, String character2, int milliWaitTime1, int milliWaitTime2) {
        String tempCLASS = CLASS;

        Thread animationThread = new Thread(() -> {
            while (tempCLASS.equals(CLASS)) {
                try {
                    SwingUtilities.invokeLater(() -> {
                        // Checks the tempCLASS again for a reason here (and below).
                        // Stops the one frame overlap with old class image when loop runs for final time.
                        if (tempCLASS.equals(CLASS)) {
                            characterArea.setText(character1);
                            characterArea.setCaretPosition(0);
                        }
                    });
                    Thread.sleep(milliWaitTime1); //delay till each breath.
                    SwingUtilities.invokeLater(() -> {
                        if (tempCLASS.equals(CLASS)) {
                            characterArea.setText(character2);
                            characterArea.setCaretPosition(0);
                        }
                    });
                    Thread.sleep(milliWaitTime2); // different delay to make it look more dynamic
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

        });
        animationThread.setDaemon(true); // makes the thread a 'background' thread so closing works properly.
        return animationThread;
    }

    //                                                                                                    CLEAR SCREEN
    private void clearScreen() {
        display.setText("");

        switch (currentRoom.getName()) {
            case "tutorial":
                println(Messages.tutorialTitleMessage());
                println(Messages.tutorialIntroMessage());
                break;
            case "graveyard":
                println(Messages.graveyardTitleMessage());
                println(Messages.graveyardIntroMessage());
                break;
            case "church":
                println(Messages.churchTitleMessage());
                println(CLASS);
                break;
        }
    }

    //                                                                                             COUNT VISITED ROOMS
    public static int countVisitedRooms() {
        if (mapBuilder == null) return 0;

        int count = 0;
        for (Room room : mapBuilder.getAllRooms().values()) {
            if (room.isVisited()) {
                count++;
            }
        }
        return count;
    }

    //----------------------------------------------------------------------------------------- INITIALISATION METHODS

    //                                                                                                   INITIALISE UI
    private void initialiseUI() {
        JFrame frame = new JFrame("Terminality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.setBackground(DEFAULT);

        // Command Log Area
        commandLog = new JTextArea();
        commandLog.setEditable(false);
        commandLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        commandLog.setBackground(DEFAULT3);
        commandLog.setForeground(WHITE);
        commandLog.setMargin(new Insets(10, 0, 10, 10));


        commandHistory = new ArrayList<>(); // Initialize command history
        commandCount = 0;

        commandLog.setText("   【\uFEFFＣＯＭＭＡＮＤ　ＨＩＳＴＯＲＹ】\n");
        commandLog.append(" ═══════════════════════════════════════\n\n");

        JScrollPane leftScroll = new JScrollPane(commandLog);
        leftScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        leftScroll.setPreferredSize(new Dimension(250, frame.getHeight()));

        // Display Area
        display = new JTextArea();
        display.setEditable(false);
        display.setFont(new Font("Monospaced", Font.PLAIN, 14));
        display.setBackground(BLACK);
        display.setForeground(DEFAULT);
        display.setMargin(new Insets(20, 20, 20, 20));
        JScrollPane centerScroll = new JScrollPane(display);
        centerScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // Character & Stats Area
        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 0, 0)); // 2 rows, 1 column, 10px gap
        rightPanel.setBackground(WHITE);

        // Character area (top bit)
        characterArea = new JTextArea();
        characterArea.setEditable(false);
        characterArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        characterArea.setBackground(BLACK);
        characterArea.setForeground(Color.LIGHT_GRAY);
        characterArea.setMargin(new Insets(20, 50, 20, 50));
        JScrollPane characterScroll = new JScrollPane(characterArea);
        characterScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Stats area (bottom bit)
        statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        statsArea.setBackground(BLACK);
        statsArea.setForeground(WHITE);
        statsArea.setMargin(new Insets(0, 10, 0, 10));
        JScrollPane statsScroll = new JScrollPane(statsArea);
        statsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        statsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        rightPanel.add(characterScroll);
        rightPanel.add(statsScroll);
        rightPanel.setPreferredSize(new Dimension(480, frame.getHeight()));

        // Input Area
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(DEFAULT2);

        JLabel promptLabel = new JLabel(" > ");
        promptLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        promptLabel.setForeground(WHITE);
        inputPanel.add(promptLabel, BorderLayout.WEST);

        terminal = new JTextField();
        terminal.setFont(new Font("Monospaced", Font.PLAIN, 14));
        terminal.setBackground(BLACK);
        terminal.setForeground(WHITE);
        terminal.setCaretColor(DEFAULT);
        terminal.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));
        inputPanel.add(terminal, BorderLayout.CENTER);

        // Whole Programme
        mainPanel.add(leftScroll, BorderLayout.WEST);
        mainPanel.add(centerScroll, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);

        terminal.addActionListener(e -> {
            String input = terminal.getText().trim().toLowerCase();
            processCommand(input);
            terminal.setText("");
        });

        frame.setVisible(true);
        terminal.requestFocus();
    }

    //                                                                                                 INITIALISE WORLD
    private void initialiseWorld() {
        // Set the starting room
        currentRoom = new Room("Tutorial", "Area when the user opens the game for the first time.", 0, 0, Room.RoomType.START);
    }

    //---------------------------------------------------------------------------------------------- TEMPORARY TESTING
    private void testing(String CLASS) {
        terminal.setText("start");
        terminal.postActionEvent();
        wait(0.2);
        terminal.setText("name aviery");
        terminal.postActionEvent();
        wait(0.2);
        terminal.setText("class " + CLASS);
        terminal.postActionEvent();
        wait(0.2);
        terminal.setText("proceed");
        terminal.postActionEvent();
    }
}