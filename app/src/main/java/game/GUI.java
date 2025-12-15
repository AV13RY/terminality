package game;

import characters.Boss;
import characters.Enemy;
import characters.Player;
import items.*;
import world.MapBuilder;
import world.Room;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static game.Messages.churchIntroMessage;

public class GUI {


    //------------------------------------------------------------------------------------------------- GAME VARIABLES
    //                                                                                                GUI DECLARATIONS
    private JTextArea display;
    private JTextField terminal;
    private JTextArea characterArea;
    private JTextPane statsArea;
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
    private boolean warnedAboutChests; // already warned about chests
    private boolean showingMinimap; // minimap or stats toggle

    //                                                                                                       CONSTANTS
    private static final Set<String> VALID_CLASSES = Set.of("knight", "mage", "reaper");
    private static final Set<String> GRADER_NAMES = Set.of("matt", "matthew", "xi", "ayah");
    private static final Set<String> COMBAT_ALLOWED_COMMANDS = Set.of("flee", "status", "inventory", "help", "attack", "cast");

    //                                                                                             COLOUR DECLARATIONS
    private record ColourScheme(String name, Color primary, Color secondary) {
    }

    private final List<ColourScheme> COLOURS = List.of(
            // theme colours (primary used for text, secondary for backgrounds)
            new ColourScheme("red", Color.RED, new Color(0x4b0000)), new ColourScheme("green", Color.GREEN, new Color(0x004b00)), new ColourScheme("blue", Color.BLUE, new Color(0x00004b)), new ColourScheme("yellow", Color.YELLOW, new Color(0x4b4b00)), new ColourScheme("cyan", Color.CYAN, new Color(0x004b4b)), new ColourScheme("magenta", Color.MAGENTA, new Color(0x4b004b)), new ColourScheme("white", Color.WHITE, new Color(0x424549)), new ColourScheme("default", new Color(0xD8125B), new Color(0x4b0019)),
            // display colours (softer shades for stats/minimap readability)
            new ColourScheme("soft-red", new Color(255, 100, 100), null), new ColourScheme("soft-blue", new Color(100, 149, 237), null), new ColourScheme("soft-green", new Color(100, 255, 100), null), new ColourScheme("gold", new Color(255, 215, 0), null), new ColourScheme("soft-cyan", new Color(100, 255, 255), null), new ColourScheme("soft-magenta", new Color(255, 100, 255), null), new ColourScheme("grey", new Color(128, 128, 128), null), new ColourScheme("dim-red", new Color(180, 80, 80), null));

    private Color getColour(String name) {
        return COLOURS.stream().filter(c -> c.name().equals(name)).findFirst().map(ColourScheme::primary).orElse(Color.WHITE);
    }

    private Color getSecondaryColour(String name) {
        return COLOURS.stream().filter(c -> c.name().equals(name)).findFirst().map(ColourScheme::secondary).orElse(Color.BLACK);
    }

    private final Color BLACK = Color.BLACK;
    private final Color DEFAULT2 = new Color(0x424549);

    //--------------------------------------------------------------------------------------------------- CORE METHODS
    //                                                                                                GAME CONSTRUCTOR
    public GUI() {

        this.inCombat = false;
        currentEnemy = null;
        this.random = new Random();
        this.warnedAboutChests = false;
        this.showingMinimap = false;

        initialiseUI();
        initialiseWorld();
        println(Messages.tutorialTitleMessage());
        println(Messages.tutorialIntroMessage());

        //  testing("reaper"); // temporary testing
    }

    //----------------------------------------------------------------------------------------------- DISPLAY METHODS
    //                                                                                            DISPLAY PLAYER STATS
    private void displayStats() {
        statsArea.setText("");

        printColored("\n══════════════════════════════════════\n", getColour("white"));
        printColored("           CHARACTER STATUS\n", getColour("white"));
        printColored("══════════════════════════════════════\n", getColour("white"));

        printColored("Name: ", getColour("white"), player.getName() + "\n", getColour("gold"));
        printColored("Class: ", getColour("white"), CLASS + "\n", getColour("soft-cyan"));
        printColored("Weapon: ", getColour("white"), (player.getEquippedWeapon() != null ? player.getEquippedWeapon().getName() : "None") + "\n", getColour("soft-magenta"));
        printColored("Level: ", getColour("white"), player.getLevel() + "\n", getColour("soft-green"));
        printColored("Experience: ", getColour("white"), player.getExperience() + "/100\n", getColour("soft-green"));

        printColored("\nVitals:\n", getColour("white"));
        printColored("  Health: ", getColour("white"), player.getCurrentHealth() + "/" + player.getMaxHealth() + "\n", getColour("soft-red"));

        if (player.getMaxMana() > 0) {
            printColored("  Mana: ", getColour("white"), player.getMana() + "/" + player.getMaxMana() + "\n", getColour("soft-blue"));
        }

        printColored("\nStats:\n", getColour("white"));
        printColored("  Attack: ", getColour("white"), player.getAttack() + "\n", getColour("gold"));
        printColored("  Defense: ", getColour("white"), player.getDefense() + "\n", getColour("soft-blue"));

        printColored("═══════════════════════════════════════", getColour("white"));
    }

    //                                                                                     DISPLAY MINIMAP IN SIDE PANEL
    private void displayMinimap() {
        statsArea.setText("");

        // map bounds
        int minX = 0, maxX = 0, minY = 0, maxY = 0;
        for (Room room : mapBuilder.getAllRooms().values()) {
            minX = Math.min(minX, room.getX());
            maxX = Math.max(maxX, room.getX());
            minY = Math.min(minY, room.getY());
            maxY = Math.max(maxY, room.getY());
        }

        // top to bottom
        for (int y = maxY; y >= minY; y--) {
            for (int x = minX; x <= maxX; x++) {
                String coordKey = x + "," + y;
                Room room = mapBuilder.getAllRooms().get(coordKey);

                if (room != null) {
                    Color roomColor;
                    String symbol;

                    // symbol priority
                    if (room == currentRoom) {
                        symbol = "[◉]";
                        roomColor = getColour("soft-green");
                    } else if (room.getType() == Room.RoomType.BOSS) {
                        symbol = "[B]";
                        roomColor = getColour("soft-blue");
                    } else if (room.getType() == Room.RoomType.TREASURE || room.hasAccessibleChests()) {
                        symbol = "[T]";
                        roomColor = getColour("gold");
                    } else if (!room.isVisited()) {
                        symbol = "[?]";
                        roomColor = getColour("grey");
                    } else if (room.hasEnemies()) {
                        symbol = "[!]";
                        roomColor = getColour("dim-red");
                    } else {
                        symbol = "[·]";
                        roomColor = getColour("white");
                    }

                    printColored(symbol, roomColor);

                    // horizontal
                    if (room.getExit("east") != null) {
                        printColored("─", getColour("white"));
                    } else {
                        printColored(" ", getColour("white"));
                    }
                } else {
                    printColored("    ", getColour("white"));
                }
            }
            printColored("\n", getColour("white"));

            // vertical
            if (y > minY) {
                for (int x = minX; x <= maxX; x++) {
                    String coordKey = x + "," + y;
                    Room room = mapBuilder.getAllRooms().get(coordKey);

                    if (room != null && room.getExit("south") != null) {
                        printColored(" │  ", getColour("white"));
                    } else {
                        printColored("    ", getColour("white"));
                    }
                }
                printColored("\n", getColour("white"));
            }
        }

        // legend
        printColored("\n═════════════════════════════════════════════\n", getColour("white"));
        printColored("[◉]", getColour("soft-green"), "= You ", getColour("white"), "[·]", getColour("white"), "= Visited ", getColour("white"), "[?]", getColour("grey"), "= Unknown\n", getColour("white"));
        printColored("[T]", getColour("gold"), "= Treasure ", getColour("white"), "[B]", getColour("soft-blue"), "= Boss ", getColour("white"), "[!]", getColour("dim-red"), "= Enemies", getColour("white"));
    }

    //                                                                                      REFRESH THE SIDE PANEL VIEW
    private void refreshSidePanel() {
        if (showingMinimap) {
            displayMinimap();
        } else {
            displayStats();
        }
    }

    //                                                                                     DISPLAY CHARACTER ANIMATION
    private void displayCharacter(String CLASS) {
        Thread idleAnimationThread;
        switch (CLASS) {
            case "knight":
                characterArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
                characterArea.setForeground(getColour("white"));
                characterArea.setText(Messages.displayKnight(1));
                idleAnimationThread = idleAnimation(Messages.displayKnight(1), Messages.displayKnight(2), 1500, 750);
                idleAnimationThread.start();
                break;
            case "mage":
                characterArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
                characterArea.setForeground(new Color(135, 206, 250)); // light blue
                characterArea.setText(Messages.displayMage(1));
                idleAnimationThread = idleAnimation(Messages.displayMage(1), Messages.displayMage(2), 1500, 750);
                idleAnimationThread.start();
                break;
            case "reaper":
                characterArea.setFont(new Font("Monospaced", Font.PLAIN, 9));
                characterArea.setForeground(getColour("red"));
                characterArea.setText(Messages.displayReaper(1));
                idleAnimationThread = idleAnimation(Messages.displayReaper(1), Messages.displayReaper(2), 2500, 150, 1);
                idleAnimationThread.start();
                break;
        }
    }

    //--------------------------------------------------------------------------------------------- PROCESSING METHODS
    //                                                                                      GENERAL COMMAND PROCESSING
    private void processCommand(String input) {

        println("\n > " + input); // displays the inputted command in display area.
        updateCommandHistory(input);

        // dungeon started, use game commands
        if (mapBuilder != null) {
            processGameCommand(input);
            return;
        }

        // pre-game commands
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

        // room-specific pre-game
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

                    // easter egg
                    if (GRADER_NAMES.contains(NAME)) {
                        println("\n✨ Oh hey... thats a cool name... \n I have a cool lecturer whos called that.. huh. Enjoy ;) ✨");
                    }

                    println("\nAnd what will your class be?");
                    println("- Use the command: [ class <knight,mage,reaper> ]");
                }

                if (input.startsWith("class")) {
                    String selectedClass = input.replace("class", "").toLowerCase().trim();
                    if (VALID_CLASSES.contains(selectedClass)) {
                        CLASS = selectedClass;
                        displayCharacter(CLASS);
                        println("Your class will be: " + CLASS + '\n');
                        println("\nIf you wish to alter your memory, this is your last chance.\n    - class command can be used again.\n");
                        println("However if this is how you choose to remember yourself:");
                        println("- Use the command: [ proceed ]\n");
                    } else {
                        println("Please specify one of the designated classes.");
                    }
                }

                if (input.equals("proceed")) {
                    println(NAME + ", a " + CLASS + " from the Lands Between is ready to start their journey.");
                    // map to player constant
                    String playerClass = switch (CLASS) {
                        case "knight" -> Player.KNIGHT;
                        case "mage" -> Player.MAGE;
                        default -> Player.REAPER;
                    };
                    player = new Player(NAME, playerClass);
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

        // combat mode
        if (inCombat && !COMBAT_ALLOWED_COMMANDS.contains(input.toLowerCase())) {
            println("You're in combat! You must attack or flee!");
            return;
        }

        if (input.equalsIgnoreCase("attack")) {
            performCombat();
            return;
        }

        if (input.equalsIgnoreCase("cast")) {
            castSpell();
            return;
        }

        // common commands
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
        } else if (input.startsWith("show ")) {
            String panel = input.substring(5).trim();
            handleShowCommand(panel);
            return;
        }

        // combat
        if (inCombat) {
            if (input.equals("flee")) {
                attemptFlee();
            } else {
                println("You're in combat! Use 'attack' to fight or 'flee' to escape!");
            }
            return;
        }

        // movement
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

    //                                                                                           OUTPUT METHODS
    public void println(String text) {
        display.append(text + "\n");
        display.setCaretPosition(display.getDocument().getLength());
    }

    public void println(String text, JTextArea area) {
        area.append(" " + text + "\n");
        area.setCaretPosition(area.getDocument().getLength());
    }

    public void println(String text, JTextPane pane) {
        try {
            StyledDocument doc = pane.getStyledDocument();
            Style style = pane.addStyle("default", null);
            StyleConstants.setForeground(style, getColour("white"));
            doc.insertString(doc.getLength(), " " + text + "\n", style);
            pane.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    // pass pairs of (text, color)
    private void printColored(Object... args) {
        try {
            StyledDocument doc = statsArea.getStyledDocument();
            for (int i = 0; i < args.length; i += 2) {
                String text = String.valueOf(args[i]);
                Color color = (Color) args[i + 1];
                Style style = statsArea.addStyle("style" + i, null);
                StyleConstants.setForeground(style, color);
                doc.insertString(doc.getLength(), text, style);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
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
                    chosenWeapon = new Weapon("Sword & Shield", 12, "The bastion of any successful knight. Cast: Heavenly Defense (15 mana)", Item.Rarity.COMMON, 10, Weapon.SpellType.DEFENSE, 15);
                    println("\nYou take up the SWORD & SHIELD, feeling their perfect balance.");
                    println("The blade gleams with deadly purpose while the shield promises protection.");
                    println("You can now cast HEAVENLY DEFENSE! Use 'cast' in combat.");
                }
                break;

            case "mage":
                if (choice.equals("1")) {
                    chosenWeapon = new Weapon("Grimoire", 20, "An ancient tome of forbidden knowledge. Cast: Fireball (20 mana)", Item.Rarity.COMMON, 10, Weapon.SpellType.FIRE, 20);
                    println("\nYou open the GRIMOIRE, its pages crackling with arcane energy.");
                    println("Forbidden knowledge floods your mind as the tome accepts you as its master.");
                    println("You can now cast FIREBALL! Use 'cast' in combat.");
                } else if (choice.equals("2")) {
                    chosenWeapon = new Weapon("Unstable Orb", 18, "A chaotic orb of pure annihilation. Cast: Frostbolt (25 mana)", Item.Rarity.COMMON, 10, Weapon.SpellType.ICE, 25);
                    println("\nYou grasp the UNSTABLE ORB, feeling its chaotic energy surge through you.");
                    println("The orb pulses erratically, barely contained destruction at your fingertips.");
                    println("You can now cast FROSTBOLT! Use 'cast' in combat.");
                }
                break;

            case "reaper":
                if (choice.equals("1")) {
                    chosenWeapon = new Weapon("Scythe", 25, "The cold, clean edge all mortals must meet.", Item.Rarity.COMMON, 10);
                    println("\nYou grip the SCYTHE, its blade singing a song of endings.");
                    println("The weapon feels like an extension of death itself in your hands.");
                } else if (choice.equals("2")) {
                    chosenWeapon = new Weapon("Death Magic", 17, "A dark conduit for siphoning souls. Cast: Drain Life (15 mana)", Item.Rarity.COMMON, 10, Weapon.SpellType.LIFESTEAL, 15);
                    println("\nYou channel DEATH MAGIC, feeling the cold touch of the void.");
                    println("Dark energy swirls around you, hungry for the essence of life.");
                    println("You can now cast DRAIN LIFE! Use 'cast' in combat.");
                }
                break;
        }

        if (chosenWeapon != null) {
            player.addWeapon(chosenWeapon);
            refreshSidePanel(); // Update stats display

            println("\n 【 " + chosenWeapon.getName() + " has been added to your inventory and equipped! 】\n\n");
            println("\nThe spectral overseer nods approvingly.\n");
            println("\"Your choice is made. May it serve you well in the trials ahead.\"\n");
            println("\nThe overseer dissipates, and you notice a door at the back of the church beckoning to you.");
            println("\nType [ proceed ] to begin the dungeon.");

        }
    }

    //                                                                                       COMMAND HISTORY METHODS
    /*
     * ADDITIONAL FEATURE: Git-style command history visualisation with unicode branching symbols.
     * This displays the player's command history in a format inspired by git, using
     * unicode characters (╟, ╠, ╔, ╚, etc.) to create a visual tree structure.
     * Different command types get unique symbols (◆ for start, → for move, ⚔ for attack, etc.)
     * and branch indicators appear when the player switches between command categories like
     * movement vs combat vs utility commands.
     */
    private void updateCommandHistory(String command) {
        commandHistory.add(command);
        commandCount++;

        commandLog.setText("   【\uFEFFＣＯＭＭＡＮＤ　ＨＩＳＴＯＲＹ】\n");
        commandLog.append(" ═══════════════════════════════════════\n\n");

        // reverse order with branching
        for (int i = commandHistory.size() - 1; i >= 0; i--) {
            String cmd = commandHistory.get(i);
            int cmdNumber = i + 1;

            // symbol per command type
            String node = "○";
            if (cmd.startsWith("start")) node = "◆";
            else if (cmd.startsWith("move")) node = "→";
            else if (cmd.startsWith("attack")) node = "⚔";
            else if (cmd.startsWith("name") || cmd.startsWith("class")) node = "★";
            else if (cmd.equals("help") || cmd.equals("clear")) node = "◌";

            // build line
            String str = "  ╟─" + node + " [" + String.format("%03d", cmdNumber) + "] " + cmd + "\n";
            if (i == commandHistory.size() - 1) {
                // current
                commandLog.append("  ╔═ CURRENT\n");
                commandLog.append("  ║\n");
                commandLog.append(str);
            } else if (i == 0) {
                // root
                commandLog.append("  ║\n");
                commandLog.append(str);
                commandLog.append("  ║\n");
                commandLog.append("  ╚═ ORIGIN\n");
            } else {
                // middle
                commandLog.append("  ║\n");

                // branch on type change
                if (i < commandHistory.size() - 1) {
                    String nextCmd = commandHistory.get(i + 1);
                    String prevCmd = commandHistory.get(i - 1);

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

        commandLog.append("\n  ───────────────\n");
        commandLog.append("  Total: " + commandCount + " commands\n");

        commandLog.setCaretPosition(0);
    }

    // type changed?
    private boolean isCommandTypeChange(String cmd1, String cmd2) {
        return !getCommandType(cmd1).equals(getCommandType(cmd2));
    }

    // get command category
    private String getCommandType(String cmd) {
        if (cmd.startsWith("move")) return "movement";
        if (cmd.startsWith("attack") || cmd.equals("flee") || cmd.startsWith("open") || cmd.startsWith("use") || cmd.startsWith("equip"))
            return "combat";
        if (cmd.startsWith("name") || cmd.startsWith("class") || cmd.startsWith("choose")) return "character";
        if (cmd.equals("help") || cmd.equals("clear") || cmd.startsWith("colour") || cmd.equals("look") || cmd.equals("map") || cmd.equals("inventory"))
            return "utility";
        return "other";
    }

    //----------------------------------------------------------------------------------------------- GAME FLOW METHODS
    //                                                                                                   START ADVENTURE
    private void startAdventure() {
        display.setText("");
        println("\nGenerating dungeon layout...");
        mapBuilder = new MapBuilder();
        currentRoom = mapBuilder.generateMap();
        currentRoom.setVisited(true);

        println("\nYou step through the church doors into the dungeon beyond...");
        println("Type /help for a list of all game commands.");
        println(Messages.displayCurrentRoom());

        showingMinimap = true;
        refreshSidePanel();
    }

    //                                                                                                   PLAYER MOVEMENT
    private void movePlayer(String direction) {
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom != null) {
            if (inCombat) {
                println("You can't leave while in combat! Defeat your enemies first!");
                return;
            }

            // warn about chests once
            int unopenedCount = countUnopenedChests();
            if (unopenedCount > 0 && !warnedAboutChests) {
                String chestWord = unopenedCount == 1 ? "chest" : "chests";
                println("Are you sure? There's still " + unopenedCount + " " + chestWord + " available to open in this room!");
                println("Run look to see them. You can also run open {chestNumber} to open them.");
                warnedAboutChests = true;
                return;
            }

            currentRoom = nextRoom;
            currentRoom.setVisited(true);
            warnedAboutChests = false;

            display.setText("");
            println(Messages.displayCurrentRoom());
            refreshSidePanel();

            if (currentRoom.hasEnemies()) {
                checkForCombat();
            }
        } else {
            println("You can't go that way!");
        }
    }

    private int countUnopenedChests() {
        return (int) currentRoom.getChests().stream().filter(Chest::isClosed).count();
    }

    //                                                                                                SHOW PANEL TOGGLE
    private void handleShowCommand(String panel) {
        if (panel.equals("minimap")) {
            showingMinimap = true;
            displayMinimap();
            println("Side panel now showing minimap.");
        } else if (panel.equals("status")) {
            showingMinimap = false;
            displayStats();
            println("Side panel now showing status.");
        } else {
            println("Unknown panel. Use 'show minimap' or 'show status'.");
        }
    }

    //-------------------------------------------------------------------------------------------------- ITEM METHODS
    //                                                                                                     OPEN CHEST
    private void openChest(String chestIdentifier) {
        if (!currentRoom.hasAccessibleChests()) {
            println("There are no chests to open here, or enemies are still present!");
            return;
        }

        List<Chest> unopenedChests = currentRoom.getChests().stream().filter(Chest::isClosed).toList();

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

            // gold based on rarity
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

    //                                                                                                        USE ITEM
    private void useItem(String itemIdentifier) {
        // consumables only
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
                    refreshSidePanel();

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

    //                                                                                                      EQUIP ITEM
    private void equipItem(String itemIdentifier) {
        // equipables only
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
                    refreshSidePanel();
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

    //                                                                                                    RESTART GAME
    private void restartGame() {
        // reset state
        NAME = "";
        CLASS = "";
        player = null;
        mapBuilder = null;
        currentEnemy = null;
        inCombat = false;
        commandHistory.clear();
        commandCount = 0;

        // reset ui
        display.setText("");
        characterArea.setText("");
        statsArea.setText("");
        commandLog.setText("   【\uFEFFＣＯＭＭＡＮＤ　ＨＩＳＴＯＲＹ】\n");
        commandLog.append(" ═══════════════════════════════════════\n\n");

        initialiseWorld();

        // swap listener back
        terminal.removeActionListener(terminal.getActionListeners()[0]);
        terminal.addActionListener(e -> {
            String input = terminal.getText().trim().toLowerCase();
            processCommand(input);
            terminal.setText("");
        });

        println(Messages.tutorialTitleMessage());
        println(Messages.tutorialIntroMessage());
    }

    //------------------------------------------------------------------------------------------------- COMBAT METHODS
    //                                                                                                   CHECK FOR COMBAT
    private void checkForCombat() {
        if (currentRoom.hasEnemies() && !currentRoom.getEnemies().isEmpty()) {
            currentEnemy = currentRoom.getEnemies().getFirst();
            inCombat = true;

            // boss encounter
            if (currentEnemy instanceof Boss) {
                println("\n⚠️ ═══════════════════════════════════════ ⚠️");
                println("         A POWERFUL PRESENCE AWAKENS!");
                println("⚠️ ═══════════════════════════════════════ ⚠️\n");
                println("The " + currentEnemy.getName() + " rises before you!");
                println("This is the final challenge. Prepare yourself!\n");
            } else {
                println("\n⚔️ COMBAT INITIATED!");
                println("You encounter a " + currentEnemy.getName() + "!");
            }
            println(Messages.displayCombatStatus());
            println(Messages.getEnemyArt(currentEnemy.getEnemyType())); // show enemy art after status
        }
    }

    //                                                                                                   PERFORM COMBAT
    private void performCombat() {
        // player attacks
        int playerDamage = calculatePlayerDamage();
        println("\nYou attack the " + currentEnemy.getName() + " for " + playerDamage + " damage!");
        currentEnemy.takeDamage(playerDamage);

        // boss phase change check
        if (currentEnemy instanceof Boss boss && boss.hasPhaseChanged()) {
            println(boss.getPhaseChangeMessage());
        }

        if (currentEnemy.isDead()) {
            if (currentEnemy instanceof Boss) {
                handleBossVictory();
            } else {
                handleEnemyDefeat();
            }
            return;
        }

        // enemy turn
        enemyTurn();
    }

    //                                                                                                     ATTEMPT FLEE
    private void attemptFlee() {
        if (!inCombat) {
            println("You're not in combat!");
            return;
        }

        if (random.nextDouble() < 0.5) { // 50% chance to flee
            println("You successfully flee from combat!");
            inCombat = false;
            currentEnemy = null;

            // random exit
            List<String> exits = new ArrayList<>(currentRoom.getExits().keySet());
            if (!exits.isEmpty()) {
                String randomExit = exits.get(random.nextInt(exits.size()));
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

    //                                                                                              CALCULATE PLAYER DAMAGE
    private int calculatePlayerDamage() {
        int baseDamage = player.getAttack();
        if (player.getEquippedWeapon() != null) {
            baseDamage += player.getEquippedWeapon().getAttackBonus();
        }
        // ±20% variance
        int variance = (int) (baseDamage * 0.2);
        return baseDamage + random.nextInt(variance * 2 + 1) - variance;
    }

    //                                                                                                       CAST SPELL
    private void castSpell() {
        if (!inCombat) {
            println("You can only cast spells in combat!");
            return;
        }

        Weapon weapon = player.getEquippedWeapon();
        if (weapon == null || !weapon.hasSpell()) {
            println("Your weapon has no spell to cast!");
            return;
        }

        int manaCost = weapon.getManaCost();
        if (player.getMana() < manaCost) {
            println("Not enough mana! Need " + manaCost + ", have " + player.getMana());
            return;
        }

        // cast the spell
        player.useMana(manaCost);
        Weapon.SpellType spell = weapon.getSpellType();
        int baseDamage = spell.baseDamage + (player.getLevel() * 2); // scales with level
        int variance = (int) (baseDamage * 0.2);
        int damage = baseDamage + random.nextInt(variance * 2 + 1) - variance;

        // spell effects
        switch (spell) {
            case FIRE -> {
                println("\n🔥 You cast FIREBALL!");
                println("Flames engulf the " + currentEnemy.getName() + " for " + damage + " damage!");
                int burnDamage = damage / 4; // 25% burn
                println("The enemy burns for " + burnDamage + " additional damage!");
                currentEnemy.takeDamage(damage + burnDamage);
            }
            case ICE -> {
                println("\n❄️ You cast FROSTBOLT!");
                println("Ice shards pierce the " + currentEnemy.getName() + " for " + damage + " damage!");
                println("The enemy is FROZEN and skips their next attack!");
                currentEnemy.takeDamage(damage);
                // skip enemy turn by returning early after status check
                checkCombatEnd();
                return;
            }
            case LIFESTEAL -> {
                println("\n💀 You cast DRAIN LIFE!");
                println("Dark energy drains " + damage + " life from the " + currentEnemy.getName() + "!");
                int healAmount = damage / 2; // heal 50% of damage
                player.heal(healAmount);
                println("You absorb " + healAmount + " health!");
                currentEnemy.takeDamage(damage);
            }
            case DEFENSE -> {
                println("\n🛡️ You cast HEAVENLY DEFENSE!");
                println("Divine light surrounds you, doubling your defense!");
                player.activateDefenseBuff();
                refreshSidePanel();
                enemyTurn(); // no damage, just buff then enemy attacks
                return;
            }
            default -> {
                println("The spell fizzles...");
                return;
            }
        }

        // boss phase check
        if (currentEnemy instanceof Boss boss && boss.hasPhaseChanged()) {
            println(boss.getPhaseChangeMessage());
        }

        // check if enemy died
        if (currentEnemy.isDead()) {
            if (currentEnemy instanceof Boss) {
                handleBossVictory();
            } else {
                handleEnemyDefeat();
            }
            return;
        }

        // enemy turn (unless frozen)
        enemyTurn();
    }

    //                                                                                              CHECK COMBAT END STATE
    private void checkCombatEnd() {
        if (currentEnemy.isDead()) {
            if (currentEnemy instanceof Boss) {
                handleBossVictory();
            } else {
                handleEnemyDefeat();
            }
        } else {
            refreshSidePanel();
            println(Messages.displayCombatStatus());
        }
    }

    //                                                                                               HANDLE ENEMY DEFEAT
    private void handleEnemyDefeat() {
        println("\n🎉 Victory! You defeated the " + currentEnemy.getName() + "!");

        int expGained = currentEnemy.getExperienceValue();
        int goldGained = random.nextInt(20) + 10;
        player.gainExperience(expGained);
        player.addGold(goldGained);
        println("You gained " + expGained + " EXP and " + goldGained + " gold!");

        player.clearDefenseBuff(); // reset buff after combat
        refreshSidePanel();

        currentRoom.getEnemies().remove(currentEnemy);
        inCombat = false;
        currentEnemy = null;

        if (currentRoom.hasEnemies() && !currentRoom.getEnemies().isEmpty()) {
            println("\nThere are more enemies in the room!");
            checkForCombat();
        } else {
            println("\nThe room is now clear of enemies.");
        }
    }

    //                                                                                                      ENEMY TURN
    private void enemyTurn() {
        println("\n" + currentEnemy.getAttackMessage());
        int enemyDamage = currentEnemy.getAttackDamage();
        println("The " + currentEnemy.getName() + " deals " + enemyDamage + " damage!");
        player.takeDamage(enemyDamage);

        refreshSidePanel();

        if (player.isDead()) {
            handlePlayerDeath();
            return;
        }

        println(Messages.displayCombatStatus());
    }

    //                                                                                                HANDLE PLAYER DEATH
    private void handlePlayerDeath() {
        inCombat = false;
        display.setText("");

        println(Messages.displayDeathArt());
        println(Messages.displayDeathInfo(CLASS));

        currentEnemy = null;

        // death screen listener
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

    //                                                                                                 HANDLE BOSS VICTORY
    private void handleBossVictory() {
        player.clearDefenseBuff(); // reset buff after combat

        // rewards before victory screen
        int expGained = currentEnemy.getExperienceValue();
        int goldGained = currentEnemy.getGoldDrop();
        player.gainExperience(expGained);
        player.addGold(goldGained);

        currentRoom.getEnemies().remove(currentEnemy);
        inCombat = false;
        currentEnemy = null;

        display.setText("");
        println(Messages.displayVictoryArt());
        println(Messages.displayVictoryInfo(CLASS));

        // victory screen listener
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
        ColourScheme scheme = COLOURS.stream().filter(c -> c.name().equalsIgnoreCase(colorName)).findFirst().orElse(null);

        if (scheme == null) {
            println("Unknown color. Available colors: red, green, blue, yellow, cyan, magenta, white, default");
            return;
        }

        display.setForeground(scheme.primary());
        terminal.setForeground(scheme.primary());
        terminal.setCaretColor(scheme.primary());
        commandLog.setBackground(scheme.secondary());
        println("Text color changed to " + colorName);
    }

    //------------------------------------------------------------------------------------------------ UTILITY METHODS
    //                                                                                                            WAIT
    private void wait(double seconds) {
        CompletableFuture.delayedExecutor((long) (seconds * 1000), TimeUnit.MILLISECONDS).execute(() -> {
        });
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    //                                                                                                         GETTERS
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

    //                                                                                                IDLE ANIMATION
    /*
     * ADDITIONAL FEATURE: Threaded ASCII character animations using asynchronous threading & SwingUtilities.
     * This creates a separate thread that runs alongside the main game loop to animate the character art.
     * It alternates between two ascii frames at configurable intervals. SwingUtilities .invokeLater() is used to update
     * the GUI from a background thread to prevent a specific race condition I experienced during testing.
     * The daemon flag means the thread auto-closes when the main application exits.
     */
    @SuppressWarnings("BusyWait")
    private Thread idleAnimation(String character1, String character2, int milliWaitTime1, int milliWaitTime2) {
        return idleAnimation(character1, character2, milliWaitTime1, milliWaitTime2, milliWaitTime1);
    }

    @SuppressWarnings("BusyWait")
    private Thread idleAnimation(String character1, String character2, int milliWaitTime1, int milliWaitTime2, int firstFrameDelay) {
        String tempCLASS = CLASS; // detect class changes

        Thread animationThread = new Thread(() -> {
            boolean firstFrame = true;
            while (tempCLASS.equals(CLASS)) {
                try {
                    if (firstFrame) {
                        // fast first frame for reaper
                        firstFrame = false;
                        Thread.sleep(firstFrameDelay);
                    } else {
                        SwingUtilities.invokeLater(() -> {
                            // prevent overlap
                            if (tempCLASS.equals(CLASS)) {
                                characterArea.setText(character1);
                                characterArea.setCaretPosition(0);
                            }
                        });
                        Thread.sleep(milliWaitTime1);
                    }
                    SwingUtilities.invokeLater(() -> {
                        if (tempCLASS.equals(CLASS)) {
                            characterArea.setText(character2);
                            characterArea.setCaretPosition(0);
                        }
                    });
                    Thread.sleep(milliWaitTime2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        animationThread.setDaemon(true); // closes with app
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
    /*
     * ADDITIONAL FEATURE: Custom Java Swing GUI with panels replacing terminal output.
     * instead of using standard System.out terminal output, this game uses a custom-built
     * swing interface with multiple JTextArea/JTextPane panels arranged in a BorderLayout.
     * The left panel shows command history, the centre panel shows game output, the right panel
     * shows character art and stats/minimap. This provides a more immersive experience
     * than a basic console application would allow, and just 'makes' the game basically.
     */
    private void initialiseUI() {
        JFrame frame = new JFrame("Terminality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.setBackground(getSecondaryColour("default"));

        // left panel - command log
        commandLog = new JTextArea();
        commandLog.setEditable(false);
        commandLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        commandLog.setBackground(getSecondaryColour("default"));
        commandLog.setForeground(getColour("white"));
        commandLog.setMargin(new Insets(10, 0, 10, 10));

        commandHistory = new ArrayList<>();
        commandCount = 0;

        commandLog.setText("   【\uFEFFＣＯＭＭＡＮＤ　ＨＩＳＴＯＲＹ】\n");
        commandLog.append(" ═══════════════════════════════════════\n\n");

        JScrollPane leftScroll = new JScrollPane(commandLog);
        leftScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        leftScroll.setPreferredSize(new Dimension(250, frame.getHeight()));

        // center panel - main display
        display = new JTextArea();
        display.setEditable(false);
        display.setFont(new Font("Monospaced", Font.PLAIN, 14));
        display.setBackground(BLACK);
        display.setForeground(getColour("default"));
        display.setMargin(new Insets(20, 20, 20, 20));
        JScrollPane centerScroll = new JScrollPane(display);
        centerScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // right panel - character animation and stats/minimap
        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        rightPanel.setBackground(getSecondaryColour("white"));

        // top right - character animation
        characterArea = new JTextArea();
        characterArea.setEditable(false);
        characterArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        characterArea.setBackground(BLACK);
        characterArea.setForeground(Color.LIGHT_GRAY);
        characterArea.setMargin(new Insets(20, 50, 20, 50));
        JScrollPane characterScroll = new JScrollPane(characterArea);
        characterScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // bottom right - stats or minimap (JTextPane for colored text)
        statsArea = new JTextPane();
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        statsArea.setBackground(BLACK);
        statsArea.setForeground(getColour("white"));
        statsArea.setMargin(new Insets(0, 10, 0, 10));
        JScrollPane statsScroll = new JScrollPane(statsArea);
        statsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        statsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        rightPanel.add(characterScroll);
        rightPanel.add(statsScroll);
        rightPanel.setPreferredSize(new Dimension(480, frame.getHeight()));

        // bottom panel - input
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(DEFAULT2);

        JLabel promptLabel = new JLabel(" > ");
        promptLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        promptLabel.setForeground(getColour("white"));
        inputPanel.add(promptLabel, BorderLayout.WEST);

        terminal = new JTextField();
        terminal.setFont(new Font("Monospaced", Font.PLAIN, 14));
        terminal.setBackground(BLACK);
        terminal.setForeground(getColour("white"));
        terminal.setCaretColor(getColour("default"));
        terminal.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));
        inputPanel.add(terminal, BorderLayout.CENTER);

        // assemble main panel
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
        currentRoom = new Room("Tutorial", "Area when the user opens the game for the first time.", 0, 0, Room.RoomType.START);
    }

    //---------------------------------------------------------------------------------------------- TEMPORARY TESTING
    // automates early game for quick testing - uncomment in constructor to use
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