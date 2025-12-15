package game;

import characters.Boss;
import items.Accessory;
import items.Chest;
import items.Item;
import world.Room;

import java.util.stream.Collectors;

public class Messages {

    //---------------------------------------------------------------------------------------------------- CONSTANTS
    private static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().contains("win");

    //----------------------------------------------------------------------------------------------- TITLE MESSAGES
    //                                                                                                 TUTORIAL TITLE
    public static String tutorialTitleMessage() {
        if (IS_WINDOWS) {
            return """
                    
                     ▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄ ▄▄▄▄▄▄▄    ▄▄▄      ▄▄▄ ▄▄▄▄▄ ▄▄▄   ▄▄▄    ▄▄▄▄   ▄▄▄      ▄▄▄▄  ▄▄▄▄▄▄▄ ▄▄▄   ▄▄▄\s
                     ▀▀███▀▀ ███▀▀▀▀▀ ███▀▀███▄ ████▄ ▄███  ███  ████▄  ██ ▄██▀▀██▄ ███      ███  ▀▀███▀▀ ███   ███\s
                        ███    ███▄▄      ███▄▄███▀ ███▀██▀███  ███  ███▀█▄██ ███   ███  ███      ███     ███   ▀███▄███▀\s
                        ███    ███         ███▀▀██▄  ███  ▀▀  ███  ███  ███  ▀███ ███▀▀███ ███       ███    ███      ▀███▀  \s
                        ███    ▀███████ ███  ▀███ ███      ███ ▄███▄ ███    ███ ███   ███ ██████ ▄███▄  ███        ███   \s
                    
                    ════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                    """;


        }
        return """
                ████████╗███████╗██████╗ ███╗   ███╗██╗███╗   ██╗ █████╗ ██╗     ██╗████████╗██╗   ██╗\s
                ╚══██╔══╝██╔════╝██╔══██╗████╗ ████║██║████╗  ██║██╔══██╗██║     ██║╚══██╔══╝╚██╗ ██╔╝\s
                   ██║   █████╗  ██████╔╝██╔████╔██║██║██╔██╗ ██║███████║██║     ██║   ██║    ╚████╔╝ \s
                   ██║   ██╔══╝  ██╔══██╗██║╚██╔╝██║██║██║╚██╗██║██╔══██║██║     ██║   ██║     ╚██╔╝  \s
                   ██║   ███████╗██║  ██║██║ ╚═╝ ██║██║██║ ╚████║██║  ██║███████╗██║   ██║      ██║   \s
                   ╚═╝   ╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝╚═╝   ╚═╝      ╚═╝   \s
                ═══════════════════════════════════════════════════════════════════════════════════════""";
    }

    //                                                                                                GRAVEYARD TITLE
    public static String graveyardTitleMessage() {
        if (IS_WINDOWS) {
            return """
                    
                    
                         .d8888b.  8888888b.         d8888 888     888 8888888888 Y88b   d88P     d8888 8888888b.  8888888b. \s
                        d88P  Y88b 888   Y88b       d88888 888     888 888         Y88b d88P     d88888 888   Y88b 888  "Y88b\s
                        888    888 888    888      d88P888 888     888 888          Y88o88P     d88P888 888    888 888    888\s
                        888        888   d88P     d88P 888 Y88b   d88P 8888888       Y888P     d88P 888 888   d88P 888    888\s
                        888  88888 8888888P"     d88P  888  Y88b d88P  888            888     d88P  888 8888888P"  888    888\s
                        888    888 888 T88b     d88P   888   Y88o88P   888            888    d88P   888 888 T88b   888    888\s
                        Y88b  d88P 888  T88b   d8888888888    Y888P    888            888   d8888888888 888  T88b  888  .d88P\s
                         "Y8888P88 888   T88b d88P     888     Y8P     8888888888     888  d88P     888 888   T88b 8888888P" \s
                    
                    
                    
                    
                    ⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘
                    """;
        }

        return """
                     ▄████  ██▀███   ▄▄▄    ██▒   █▓▓█████▓██   ██▓ ▄▄▄       ██▀███  ▓█████▄    \s
                     ██▒ ▀█▒▓██ ▒ ██▒▒████▄ ▓██░   █▒▓█   ▀ ▒██  ██▒▒████▄    ▓██ ▒ ██▒▒██▀ ██▌  \s
                     ▒██░▄▄▄░▓██ ░▄█ ▒▒██  ▀█▄▓██  █▒░▒███    ▒██ ██░▒██  ▀█▄  ▓██ ░▄█ ▒░██   █▌ \s
                     ░▓█  ██▓▒██▀▀█▄  ░██▄▄▄▄██▒██ █░░▒▓█  ▄  ░ ▐██▓░░██▄▄▄▄██ ▒██▀▀█▄  ░▓█▄   ▌ \s
                     ░▒▓███▀▒░██▓ ▒██▒ ▓█   ▓██▒▒▀█░  ░▒████▒ ░ ██▒▓░ ▓█   ▓██▒░██▓ ▒██▒░▒████▓  \s
                     ░▒   ▒ ░ ▒▓ ░▒▓░ ▒▒   ▓▒█░░ ▐░  ░░ ▒░ ░  ██▒▒▒  ▒▒   ▓▒█░░ ▒▓ ░▒▓░ ▒▒▓  ▒   \s
                     ░   ░   ░▒ ░ ▒░  ▒   ▒▒ ░░ ░░   ░ ░  ░▓██ ░▒░   ▒   ▒▒ ░  ░▒ ░ ▒░ ░ ▒  ▒    \s
                     ░ ░   ░   ░░   ░   ░   ▒     ░░     ░   ▒ ▒ ░░    ░   ▒     ░░   ░  ░ ░  ░  \s
                           ░    ░           ░  ░   ░     ░  ░░ ░           ░  ░   ░        ░     \s
                                                  ░          ░ ░                         ░     \s
                
                ⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘
                """;
    }

    //                                                                                                   CHURCH TITLE
    public static String churchTitleMessage() {
        if (IS_WINDOWS) {
            return """
                          ▄████████   ▄█      █▄      ▄█     █▄      ▄████████     ▄████████   ▄█    █▄          \s
                          ███    ██    ███      ███    ███    ███    ███      ███   ███    ███   ███    ███         \s
                          ███    █▀    ███      ███    ███    ███    ███      ███   ███    █▀    ███    ███         \s
                          ███         ▄███▄▄▄▄███▄▄ ███    ███  ▄███▄▄▄▄██▀   ███         ▄███▄▄▄▄███▄▄       \s
                          ███        ▀▀███▀▀▀▀███▀  ███    ███ ▀▀███▀▀▀▀▀     ███        ▀▀███▀▀▀▀███▀        \s
                          ███    █▄    ███      ███    ███    ███ ▀██████████▄  ███      █▄   ███    ███         \s
                          ███    ███   ███     ███    ███    ███     ███    ███    ███    ███   ███    ███         \s
                          ████████▀    ███   █▀      ████████▀    ███    ███    ████████▀   ███    █▀          \s
                                                                                 ▀▀█    ██▀                                 \s
                    
                    ❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█═█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚
                    """;
        }
        return """
                      ▄████████     ▄█    █▄     ▄█    █▄     ▄████████  ▄████████    ▄█    █▄          \s
                      ███    ██    ███    ███   ███    ███   ███    ███ ███    ███   ███    ███         \s
                      ███    █▀    ███    ███   ███    ███   ███    ███ ███    █▀    ███    ███         \s
                      ███         ▄███▄▄▄▄███▄▄ ███    ███  ▄███▄▄▄▄██▀ ███         ▄███▄▄▄▄███▄▄       \s
                      ███        ▀▀███▀▀▀▀███▀  ███    ███ ▀▀███▀▀▀▀▀   ███        ▀▀███▀▀▀▀███▀        \s
                      ███    █▄    ███    ███   ███    ███ ▀██████████▄ ███    █▄    ███    ███         \s
                      ███    ███   ███    ███   ███    ███   ███    ███ ███    ███   ███    ███         \s
                      ████████▀    ███    █▀    ████████▀    ███    ███ ████████▀    ███    █▀          \s
                                                             ▀▀█    ██▀                                 \s
                
                ❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█═█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚
                """;
    }

    //----------------------------------------------------------------------------------------------- INTRO MESSAGES
    //                                                                                                  TUTORIAL INTRO
    public static String tutorialIntroMessage() {

        if (IS_WINDOWS) {
            return """
                                          A Java Dungeon Crawler by Jack McGillivray                      \s
                    ═══════════════════════════════════════════════════════════════════════════════════════
                    
                    
                        █████ ██  ██ ██████ ▄████▄ █████▄  ██ ▄████▄ ██    \s
                          ██    ██  ██    ██     ██   ██ ██▄▄██▄ ██ ██▄▄██ ██    \s
                          ██    ▀███▀    ██     ▀████▀ ██   ██  ██  ██  ██ ██████\s
                    
                    
                    Hello, World.. . . . . ............... .  . . . .  .
                    Ah what am I saying, you probably already know this isn't just your average powershell.
                    
                    Anyway, welcome to Terminality, a text-based dungeon crawler.
                    Your goal is to navigate the crypt, slay its inhabitants, and defeat the final boss.
                    
                    Your experience will consist of three main interactions:
                    
                    1. >> \uD835\uDDE0\uD835\uDDE2\uD835\uDDE9\uD835\uDDD8\uD835\uDDE0\uD835\uDDD8\uD835\uDDE1\uD835\uDDE7 << When required, you'll be prompted to move between rooms.
                    - Use the command: [ move <direction> ]
                    
                    2. >> \uD835\uDDD6\uD835\uDDE2\uD835\uDDE0\uD835\uDDD5\uD835\uDDD4\uD835\uDDE7 << If an enemy is in the room, combat will start automatically.\s
                            - Use the command: [ attack ]
                    
                    3. >> \uD835\uDDD6\uD835\uDDDB\uD835\uDDD4\uD835\uDDE5\uD835\uDDD4\uD835\uDDD6\uD835\uDDE7\uD835\uDDD8\uD835\uDDE5 << Manage your character's state and gear.
                           - Use the commands: [ status ] and [ inventory ]
                    
                    To see all available commands at any time, type [ help ].
                    To end your journey prematurely, type [ exit ].
                    
                    Prepare yourself...
                    ═══════════════════════════════════════════════════════════════════════════════════════
                    
                     Type 'help' for available commands or 'exit' to quit the game.
                    """;
        }

        return """
                                      A Java Dungeon Crawler by Jack McGillivray                      \s
                ═══════════════════════════════════════════════════════════════════════════════════════
                ▖ ▖   ▄▖▖▖▄▖▄▖▄▖▄▖▄▖▖    ▖ ▖
                ▝▖▝▖  ▐ ▌▌▐ ▌▌▙▘▐ ▌▌▌   ▞ ▞\s
                ▞ ▞   ▐ ▙▌▐ ▙▌▌▌▟▖▛▌▙▖  ▝▖▝▖
                
                Hello, World.. . . . . ............... .  . . . .  .
                Ah what am I saying, you probably already know this isn't just your average powershell.
                
                Anyway, welcome to Terminality, a text-based dungeon crawler.
                Your goal is to navigate the crypt, slay its inhabitants, and defeat the final boss.
                
                Your experience will consist of three main interactions:
                
                1. >> \uD835\uDDE0\uD835\uDDE2\uD835\uDDE9\uD835\uDDD8\uD835\uDDE0\uD835\uDDD8\uD835\uDDE1\uD835\uDDE7 << When required, you'll be prompted to move between rooms.
                - Use the command: [ move <direction> ]
                
                2. >> \uD835\uDDD6\uD835\uDDE2\uD835\uDDE0\uD835\uDDD5\uD835\uDDD4\uD835\uDDE7 << If an enemy is in the room, combat will start automatically.\s
                        - Use the command: [ attack ]
                
                3. >> \uD835\uDDD6\uD835\uDDDB\uD835\uDDD4\uD835\uDDE5\uD835\uDDD4\uD835\uDDD6\uD835\uDDE7\uD835\uDDD8\uD835\uDDE5 << Manage your character's state and gear.
                       - Use the commands: [ status ] and [ inventory ]
                
                To see all available commands at any time, type [ help ].
                To end your journey prematurely, type [ exit ].
                
                Prepare yourself...
                ═══════════════════════════════════════════════════════════════════════════════════════
                
                 Type 'help' for available commands or 'exit' to quit the game.
                """;
    }

    //                                                                                                 GRAVEYARD INTRO
    public static String graveyardIntroMessage() {
        return """
                You find yourself at the entrance of an ancient graveyard shrouded in mist.
                Weathered tombstones and crumbling mausoleums stretch before you, their shadows
                dancing in the pale moonlight that filters through the twisted branches above.
                A spectral voice whispers through the fog:
                 "Traveler, before you venture forth, you must remember who you are..."
                
                THE PATHS YOU COULD HAVE BEEN:
                
                1. KNIGHT - A stalwart warrior with enhanced strength and durability
                   ♦ High Defense  ♦ Strong Attack  ♦ No Magic
                
                2. MAGE - A master of arcane arts with powerful spells but fragile defenses
                   ♦ Low Health  ♦ Low Weapon Attack  ♦ Powerful Magic
                
                3. REAPER - An embodiment of living death, fast scythe wielder.
                   ♦ Medium Health  ♦ Quick Attacks  ♦ Some Magic
                
                ⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘
                
                What is your name, brave soul?
                  - Use the command: [ name <your name> ]
                """;
    }

    //                                                                                                    CHURCH INTRO
    public static String churchIntroMessage(String CLASS) {
        return "\nYou push open the heavy wooden doors and step into the abandoned church. Dust motes dance\n" + "in the colored light streaming through stained glass windows, casting ethereal patterns\n" + "across the crumbling pews and debris-strewn floor.\n" + "                                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⡀⡀⠀         \n" + "                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⡀⠀⡀⠀⠂⡀⢀⢰⠀⢂⠀⠀⠀⠀⠀⠀⠀⠀\n" + "                         ⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣐⣬⣄⣷⡀⢸⡃⡘⡸⡄⢸⠀⠀⡇⠀⢠⠀⠀⠀\n" + "                         ⠀⠀⠀⠀⠀⣠⡴⠚⢉⢍⢂⣼⣴⣿⣿⣿⣷⣷⣷⣣⣏⣆⣼⠀⠀⠄⠀⠀⠀\n" + "                         ⠀⠀⠀⢠⡞⠋⠀⡑⣮⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣷⣼⣆⣌⡠⢁⡤\n" + "                         ⠀⠀⣰⠋⠀⣀⣺⣾⣿⣿⣿⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠁\n" + "                         ⠀⡼⠁⢀⣿⣿⣿⡿⣿⡛⣿⣿⣿⡷⢸⣿⠀⠀⠀⠀⣹⣿⣿⣿⣿⣟⠣⠀⠀\n" + "                         ⡰⠁⢀⣼⣿⠟⢿⡇⠹⣿⣿⣿⠟⠀⢠⡿⠀⠀⣠⣾⡿⣿⡥⠊⠁⠀⠀⠀⠀\n" + "                         ⠁⢠⣾⠟⠁⠀⠈⠳⢿⣦⣠⣤⣦⣼⠟⠁⣠⣾⣿⣿⣟⠍⠒⠀⠠⠀⠀⠀⠀\n" + "                         ⢠⡟⠁⢀⣀⣀⣀⣀⡀⠈⣉⣉⣡⣤⣶⣿⡿⡿⡿⡻⠥⠑⡀⠀⠀⠀⠀⠀⠀\n" + "                         ⠏⡠⠚⠉⠋⢍⠋⢫⠋⠛⢹⢻⡟⠻⣟⢏⠌⢊⡌⠌⠄⠀⠀⠀⠀⠀⠀⠀⠀\n" + "                         ⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠘⠂⠘⠂⠿⠈⠀⠀⠀⠘⠀⠀⠀⠀⠀\n\n\n" + "At the altar, a spectre materializes - an overseer of sort.\n" + "Its hollow voice echoes through the sacred halls:\n\n" + "\"Ah, a " + CLASS + " has remembered their path.\n" + "You will need more than courage to survive what lies beyond these walls.\"\n\n" + "【﻿ＴＨＥ　ＥＹＥ　ＧＬＯＷＳ】\n\n" + displayClassWeapons(CLASS) + "\"Choose wisely, " + GUI.getName() + ". This decision will shape your journey through the darkness.\"\n\n" + "❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█═█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚\n\n" + "Which weapon calls to you?" + "  - Use the command: [ choose <1 or 2> ]\n";
    }

    //------------------------------------------------------------------------------------------------ HELP MESSAGES
    //                                                                                                   TUTORIAL HELP
    public static String tutorialHelpMessage() {
        return """
                
                AVAILABLE COMMANDS:
                - colour [colour]  : Change colour (default, red, green, blue, yellow, cyan, magenta, white)
                - clear            : Clear the terminal screen
                - help             : Display this help message
                - exit             : Quit the game
                
                - start            : Start the game
                
                """;
    }

    //                                                                                                  GRAVEYARD HELP
    public static String graveyardHelpMessage() {
        return """
                
                AVAILABLE COMMANDS:
                - colour [colour]  : Change text color (default, red, green, blue, yellow, cyan, magenta, white)
                - clear            : Clear the terminal screen
                - help             : Display this help message
                - exit             : Quit the game
                
                - name             : Set your player name.
                - class            : Set your player class (Knight, Mage, or Reaper).
                - proceed          : Proceed onwards.
                
                """;
    }

    //                                                                                                     CHURCH HELP
    public static String churchHelpMessage() {
        return """
                
                AVAILABLE COMMANDS:
                - colour [colour]  : Change text color (default, red, green, blue, yellow, cyan, magenta, white)
                - clear            : Clear the terminal screen
                - start            : Start the game
                - help             : Display this help message
                - exit             : Quit the game
                
                - choose           : Choose between the options given.
                - inventory        : View the player inventory.
                - proceed          : Proceed to the next area.
                
                """;
    }

    //                                                                                                    IN-GAME HELP
    public static String inGameHelpMessage() {
        return """
                
                ═══ AVAILABLE COMMANDS ═══
                EXPLORATION:
                  move [direction] - Move to another room (north/south/east/west)
                  look            - Examine the current room
                  map             - Display the dungeon map
                
                COMBAT:
                  attack          - Attack the current enemy
                  cast            - Cast your weapon's spell (if available)
                  flee            - Attempt to escape combat
                
                CHARACTER:
                  inventory       - View your inventory
                
                ITEMS:
                  use [#]         - Use a consumable item
                  equip [#]       - Equip armor or accessory
                  open [#]        - Open a chest
                
                SYSTEM:
                  help            - Display this help message
                  show [panel]    - Toggle between minimap/status panels. [panel = minimap/status]
                  exit            - Quit the game
                ═════════════════════════
                
                """;
    }

    //------------------------------------------------------------------------------------------ WEAPON DISPLAY MESSAGES
    //                                                                                               CLASS WEAPONS ROUTER
    // routes to the correct weapon display based on class
    public static String displayClassWeapons(String CLASS) {
        if ("knight".equalsIgnoreCase(CLASS)) return displayKnightWeapons();
        if ("mage".equalsIgnoreCase(CLASS)) return displayMageWeapons();
        return displayReaperWeapons();
    }

    //                                                                                                   KNIGHT WEAPONS
    private static String displayKnightWeapons() {


        return """
                                              ✥  ARMAMENTS OF VIRTUE  ✥                                 \s
                                                                                             .-.          \s
                                                                                            {{#}}         \s
                                                                            {}               8@8          \s
                        |\\                                                .::::.             888         \s
                        | \\        /|                                 @\\\\/W\\/\\/W\\//@         8@8    \s
                        |  \\____  / |                                  \\\\/^\\/\\/^\\//     _    )8(    _
                       /|__/AMMA\\/  |                                   \\_O_{}_O_/     (@)__/8@8\\__(@) \s
                     /AMMMMMMMMMMM\\_|                              ____________________ `~"-=):(=-"~`  \s
                 ___/AMMMMMMMMMMMMMMA                             |<><><>  |  |  <><><>|     |.|          \s
                \\   |MVKMMM/ .\\MMMMM\\                             |<>      |  |      <>|     |S|       \s
                 \\__/MMMMMM\\  /MMMMMM---                          |<>      |  |      <>|     |'|        \s
                  |MMMMMMMMMMMMMMMMMM|  /                         |<>   .--------.   <>|     |.|          \s
                  |MMMM/. \\MM.--MMMMMM\\/                          |     |   ()   |     |     |P|        \s
                  /\\MMM\\  /MM\\  |MMMMMM   ___                     |_____| (O\\/O) |_____|     |'|      \s
                 /  |MMMMMMMMM\\ |MMMMMM--/   \\-.                  |     \\   /\\   /     |     |.|      \s
                /___/MMMMMMMMMM\\|M.--M/___/_|   \\                 |------\\  \\/  /------|     |U|      \s
                     \\VMM/\\MMMMMMM\\  |      /\\ \\/                 |       '.__.'       |     |'|     \s
                      \\V/  \\MMMMMMM\\ |     /_  /                  |        |  |        |     |.|       \s
                        |  /MMMV'   \\|    |/ _/                   :        |  |        :     |N|         \s
                        | /              _/  /                     \\       |  |       /      |'|         \s
                        |/              /| \\'                       \\<>    |  |    <>/       |.|        \s
                                       /_  /                         \\<>   |  |   <>/        |K|         \s
                                       /  /                           `\\<> |  | <>/'         |'|         \s
                                                                        `-.|__|.-`           \\ /         \s
                                                                                              ^         \s
                
                            1. MACE                                      2. SWORD & SHIELD                \s
                A virulent ball of swinging death.               The bastion of any successful knight.    \s
                 ♦ Offensive Focus  ♦ High Damage                   ♦ Well-rounded  ♦ Good Damage     \s
                
                
                """;
    }

    //                                                                                                     MAGE WEAPONS
    private static String displayMageWeapons() {

        return """
                                              ✥  CONDUITS OF ARCANE POWER  ✥                             \s
                
                ⠀                                                        ⠀⠀⠀⠀⢤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣄⠀        \s
                      _.--._  _.--._                                    ⠀⠀⠀⠀⠀⠀⠈⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⠀⠀⠀⠀     \s
                ,-=.-":;:;:;\\':;:;:;"-._                             ⠀⣤⡀⠀⠀⠀⠀⠀⠀⠀⣸⣏⣽⣷⣶⣶⣦⣤⣄⡀⣠⡶⠶⢶⣶⠿⠀⠀⠀ \s
                \\\\\\:;:;:;:;:;\\:;:;:;:;:;\\                          ⠀⠈⠛⢦⡀⠀⠀⠀⣠⣶⠟⢋⣁⣤⣤⣤⣤⣄⣉⠛⢿⣦⡀⠈⢿⡄⠀⠀⠀ \s
                 \\\\\\:;:;:;:;:;\\:;:;:;:;:;\\                         ⠀⠀⣠⡞⠁⠀⢠⣾⠋⢀⣴⡿⠛⢁⠙⢿⣿⣿⡻⢷⣤⡈⠻⣦⡾⠃⠀⠀⠀ \s
                  \\\\\\:;:;:;:;:;\\:;:;:;:;:;\\                        ⠀⠀⠛⠻⠶⣤⡿⢁⡄⠘⠋⠀⣴⣿⣿⣷⣿⣿⣿⣦⠉⠳⢄⠹⣧⠀⠀⠀⠀ \s
                   \\\\\\:;:;:;:;:;\\:;::;:;:;:\\                       ⠀⠀⠀⠀⠀⣿⠁⣼⣿⣶⣤⡄⠀⢹⣿⡿⠿⠿⠛⠛⠃⢀⣀⡀⢹⡇⠀⠀⢀ \s
                    \\\\\\;:;::;:;:;\\:;:;:;::;:\\                      ⠀⣤⡀⠀⢸⣿⠀⣿⣿⣿⠿⠿⠀⠸⣿⣷⣤⡀⠐⣿⣿⣿⣿⡇⢸⣿⠀⣰⡟⠀\s
                     \\\\\\;;:;:_:--:\\:_:--:_;:;\\                     ⠀⠈⠛⠿⠛⣿⠀⠀⠀⢀⣀⣤⣤⣄⡈⠙⢿⣿⣦⣌⠻⣿⣿⠇⢸⣿⣾⠏⠀⠀\s
                      \\\\_.-"      :      "-._\\                      ⠀⠀⠀⢀⣹⣧⠘⢦⣄⠉⠻⣿⣿⣿⡷⣦⣭⣿⣿⣷⣬⠋⢠⡿⠉⠁⠀⠀⠀\s
                       \\`_..--""---.;.--""--..=>                   ⠀⢶⡟⠛⠉⠹⣷⡀⠉⢠⣤⣬⣿⣿⣿⣎⠙⢿⣿⠿⠃⣠⡿⠁⠀⠀⠀⠀⠀ \s
                                    "                             ⠀⣀⣈⣷⡀⠀⠀⠈⠻⣦⣄⡙⠛⠶⠶⠦⠤⠄⠀⣁⣤⡾⠿⣦⡀⠀⠀⠀⠀⠀     \s
                                                                  ⠀⠋⠉⠉⠁⠀⠀⠀⠀⠀⢹⡛⠷⠶⣶⣶⡶⠶⢿⣿⣅⡀⠀⣨⠟⠂⠀⠀⠀⠀      \s
                                                                     ⠀⠀⠀⠀⠐⣶⡶⠿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⣧⡀⠀⠀⠀⠀⠀      \s
                                                                     ⠀⠀⠀⠀⠀⠈⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠀⠀⠀⠀⠀    \s
                
                           1. GRIMOIRE                                     2. UNSTABLE ORB                \s
                An ancient tome of forbidden knowledge.          A chaotic orb of pure annihilation.      \s
                  ♦ Mana Intensive  ♦ High Damage                ♦ Mana Efficient  ♦ Quick Casting    \s
                
                
                """;
    }

    //                                                                                                   REAPER WEAPONS
    private static String displayReaperWeapons() {
        if (IS_WINDOWS) {
            return """
                                                  ✥  INSTRUMENTS OF DEATH  ✥                               \s
                    
                                                  ⠀⠀⠀                  ⠀⠀      ⠀⣀⡀⠀⠀⠀⠀⠀    ⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀        \s
                                                  ⠀⠀⠀⠀                      ⣠⣴⡿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⠀⠉⠻⣷⣤⡀⠀        \s
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀               ⠀  ⢀⣾⡟⡍⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⠀⠀⠐⡙⣿⡄           \s
                    ⠀⠀⠀⠀⠀⣀⣠⣤⣴⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⡀⠀⠀⠀ ⠀             ⠀⣸⣿⠃⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⠇⣹⣿           \s
                    ⠀⠀⣿⣷⠀⣿⣿⣿⣿⣿⣿⣿⡿⠟⠛⠛⠉⠉⠉⠉⠉⠉⠙⠛⠻⢿⣷⡀⠀⠀⠀            ⠀⣿⣿⡆⢚⢄⣀⣠⠤⠒⠈⠁⠀⠈⠉⠐⠢⢄⡀⣀⢞⠀⣾⣿           \s
                    ⠀⠀⢿⣿⠀⢹⣿⣿⡿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠄⠀⠀               ⠀⠸⣿⣿⣅⠄⠙⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⡟⠑⣄⣽⣿⡟           \s
                    ⠀⠀⠸⣿⡇⠈⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠘⢿⣿⣟⡾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠱⣾⣿⣿⠏⠀           \s
                    ⠀⠀⠀⢻⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⣸⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀     ⠈⡉⢻⠀⠀           \s
                    ⠀⠀⠀⠀⢻⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⢿⠀⢃⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀      ⣰⠁⢸⠀⠀           \s
                    ⠀⠀⣀⣠⣴⡿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⢸⢰⡿⢘⣦⣤⣀⠑⢦⡀⠀⣠⠖⣁⣤⣴⡊⢸⡇⡼⠀⠀           \s
                    ⠀⠈⠛⠛⠉⠀⠈⠛⢿⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⠾⡅⣿⣿⣿⣿⣿⠌⠁⠀⠁⢺⣿⣿⣿⣿⠆⣇⠃⠀⠀           \s
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⣿⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⢀⠂⠘⢿⣿⣿⡿⠀⣰⣦⠀⠸⣿⣿⡿⠋⠈⢀⠀⠀⠀           \s
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⣿⣷⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⢠⠀⠀⠀⠀⠀⠀ ⢠⣿⢻⣆⠀⠀⠀⠀⠀   ⣸⠀⠀⠀           \s
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⠈⠓⠶⣶⣦⠤⠀⠘⠋⠘⠋ ⠀⠠⣴⣶⡶⠞⠃⠀⠀⠀           \s
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⠀⠀⠀⣿⢹⣷⠦⢀⠤⡤⡆ ⡤⣶⣿⢸⠇⠀⠀⠀⠀⠀           \s
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⠀⠀ ⢰⡀⠘⢯⣳⢶⠦⣧⢷⢗⣫⠇⠀⡸⠀⠀⠀⠀⠀           \s
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠁⠀⠀⠀⠀  ⠀               ⠀⠀⠀⠀⠀⠀  ⠀⠑⢤⡀⠈⠋⠛⠛⠋⠉⢀⡠⠒⠁⠀⠀⠀⠀⠀           \s
                                                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀                      ⠀⠹⢦⠀⢀⣀⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀        \s
                                                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                  ⠀    ⠈⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀        \s
                                   1. SCYTHE                                     2. DEATH MAGIC               \s
                    The cold, clean edge all mortals must meet.        A dark conduit for siphoning souls.    \s
                         ♦ Non-Magic Based  ♦ High Damage                ♦ Magic Based  ♦ Strong Spells   \s
                    
                    
                    """;
        }
        return """
                                              ✥  INSTRUMENTS OF DEATH  ✥                               \s
                
                                              ⠀⠀⠀                  ⠀⠀     ⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀        \s
                                              ⠀⠀⠀⠀                      ⣠⣴⡿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣷⣤⡀⠀        \s
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀               ⠀⢀⣾⡟⡍⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⡙⣿⡄           \s
                ⠀⠀⠀⠀⠀⣀⣠⣤⣴⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⡀⠀⠀⠀ ⠀               ⠀⣸⣿⠃⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠇⣹⣿           \s
                ⠀⠀⣿⣷⠀⣿⣿⣿⣿⣿⣿⣿⡿⠟⠛⠛⠉⠉⠉⠉⠉⠉⠙⠛⠻⢿⣷⡀⠀⠀⠀               ⠀⣿⣿⡆⢚⢄⣀⣠⠤⠒⠈⠁⠀⠀⠈⠉⠐⠢⢄⡀⣀⢞⠀⣾⣿           \s
                ⠀⠀⢿⣿⠀⢹⣿⣿⡿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠄⠀⠀               ⠀⠸⣿⣿⣅⠄⠙⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⠑⣄⣽⣿⡟           \s
                ⠀⠀⠸⣿⡇⠈⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠘⢿⣿⣟⡾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠱⣾⣿⣿⠏⠀           \s
                ⠀⠀⠀⢻⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⣸⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡉⢻⠀⠀           \s
                ⠀⠀⠀⠀⢻⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⢿⠀⢃⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠁⢸⠀⠀           \s
                ⠀⠀⣀⣠⣴⡿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⢸⢰⡿⢘⣦⣤⣀⠑⢦⡀⠀⣠⠖⣁⣤⣴⡊⢸⡇⡼⠀⠀           \s
                ⠀⠈⠛⠛⠉⠀⠈⠛⢿⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⠾⡅⣿⣿⣿⣿⣿⠌⠁⠀⠁⢺⣿⣿⣿⣿⠆⣇⠃⠀⠀           \s
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⣿⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⢀⠂⠘⢿⣿⣿⡿⠀⣰⣦⠀⠸⣿⣿⡿⠋⠈⢀⠀⠀⠀           \s
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⣿⣷⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⢠⠀⠀⠀⠀⠀⠀⢠⣿⢻⣆⠀⠀⠀⠀⠀⠀⣸⠀⠀⠀           \s
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⠈⠓⠶⣶⣦⠤⠀⠘⠋⠘⠋⠀⠠⣴⣶⡶⠞⠃⠀⠀⠀           \s
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⠀⠀⠀⣿⢹⣷⠦⢀⠤⡤⡆⡤⣶⣿⢸⠇⠀⠀⠀⠀⠀           \s
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⠀⠀⢰⡀⠘⢯⣳⢶⠦⣧⢷⢗⣫⠇⠀⡸⠀⠀⠀⠀⠀           \s
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠁⠀⠀⠀⠀  ⠀               ⠀⠀⠀⠀⠀⠀⠀⠑⢤⡀⠈⠋⠛⠛⠋⠉⢀⡠⠒⠁⠀⠀⠀⠀⠀           \s
                                              ⠀⠀⠀⠀⠀⠀⠀⠀⠀                      ⠀⠹⢦⠀⢀⣀⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀        \s
                                              ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                  ⠀    ⠈⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀        \s
                               1. SCYTHE                                     2. DEATH MAGIC               \s
                The cold, clean edge all mortals must meet.        A dark conduit for siphoning souls.    \s
                     ♦ Non-Magic Based  ♦ High Damage                ♦ Magic Based  ♦ Strong Spells   \s
                
                
                """;
    }

    //------------------------------------------------------------------------------------------- GAME STATUS MESSAGES
    //                                                                                                     DISPLAY MAP
    public static String displayMap() {
        StringBuilder sb = new StringBuilder();
        final int CONSOLE_WIDTH = 91;
        String divider = "═".repeat(CONSOLE_WIDTH);

        // centre title
        String title = " MAP ";
        int titlePadding = (CONSOLE_WIDTH - title.length()) / 2;
        String centeredTitle = "═".repeat(titlePadding) + title + "═".repeat(CONSOLE_WIDTH - titlePadding - title.length());

        sb.append("\n").append(centeredTitle).append("\n\n");

        // map bounds
        int minX = 0, maxX = 0, minY = 0, maxY = 0;
        for (Room room : GUI.getMapBuilder().getAllRooms().values()) {
            minX = Math.min(minX, room.getX());
            maxX = Math.max(maxX, room.getX());
            minY = Math.min(minY, room.getY());
            maxY = Math.max(maxY, room.getY());
        }

        int mapWidth = (maxX - minX + 1) * 4;

        // top to bottom
        for (int y = maxY; y >= minY; y--) {
            StringBuilder mapRow = new StringBuilder();
            StringBuilder connectionRow = new StringBuilder();

            for (int x = minX; x <= maxX; x++) {
                String coordKey = x + "," + y;
                Room room = GUI.getMapBuilder().getAllRooms().get(coordKey);

                if (room != null) {
                    // symbol priority
                    String symbol;
                    if (room == GUI.getCurrentRoom()) symbol = "[◉]";
                    else if (room.getType() == Room.RoomType.BOSS) symbol = "[B]";
                    else if (room.getType() == Room.RoomType.TREASURE) symbol = "[T]";
                    else if (room.isVisited()) symbol = "[·]";
                    else symbol = "[?]";

                    mapRow.append(symbol);
                    mapRow.append(room.getExit("east") != null ? "─" : " ");

                    // vertical
                    connectionRow.append(room.getExit("south") != null ? " │ " : "   ");
                    connectionRow.append(" ");
                } else {
                    mapRow.append("    ");
                    connectionRow.append("    ");
                }
            }

            // centre rows
            int padding = (CONSOLE_WIDTH - mapWidth) / 2;
            String paddingStr = " ".repeat(Math.max(0, padding));

            sb.append(paddingStr).append(mapRow).append("\n");
            if (y > minY) {
                sb.append(paddingStr).append(connectionRow).append("\n");
            }
        }

        String legend = "Legend: [◉]=You [·]=Visited [?]=Unexplored [T]=Treasure [B]=Boss";
        int legendPadding = (CONSOLE_WIDTH - legend.length()) / 2;
        sb.append("\n").append(" ".repeat(Math.max(0, legendPadding))).append(legend).append("\n");
        sb.append(divider).append("\n");
        return sb.toString();
    }

    //                                                                                               DISPLAY COMBAT STATUS
    public static String displayCombatStatus() {
        var player = GUI.getPlayer();
        var enemy = GUI.getCurrentEnemy();
        StringBuilder sb = new StringBuilder();

        sb.append("\n═══ COMBAT STATUS ═══\n\n");
        sb.append("Your HP: ").append(player.getCurrentHealth()).append("/").append(player.getMaxHealth()).append("\n");

        if (player.getMaxMana() > 0) {
            sb.append("Your MP: ").append(player.getMana()).append("/").append(player.getMaxMana()).append("\n");
        }

        sb.append(enemy.getName()).append(" HP: ").append(enemy.getCurrentHealth()).append("/").append(enemy.getMaxHealth()).append("\n\n");

        // show boss phase if applicable
        if (enemy instanceof Boss boss) {
            sb.append("Boss Phase: ").append(boss.getPhase()).append("\n");
        }

        sb.append("═════════════════════\n");
        return sb.toString();
    }

    //                                                                                                DISPLAY CURRENT ROOM
    public static String displayCurrentRoom() {
        Room room = GUI.getCurrentRoom();
        StringBuilder sb = new StringBuilder();
        sb.append("\n═══ ").append(room.getName().toUpperCase()).append(" ═══\n");
        sb.append(room.getDescription());
        sb.append("\nExits: ").append(String.join(", ", room.getExits().keySet())).append("\n");

        if (room.hasEnemies()) {
            sb.append("\nEnemies present:");
            // e is the current enemy in the loop
            room.getEnemies().forEach(e -> sb.append("- ").append(e.getName()).append(" (HP: ").append(e.getCurrentHealth()).append(")"));
        }

        if (room.hasAccessibleChests()) {
            sb.append("\nChests available:");
            // only show closed chests
            room.getChests().stream().filter(Chest::isClosed).forEach(c -> sb.append("- ").append(c.getRarity()).append(" chest"));
        }
        return sb.toString();
    }

    //                                                                                                  DISPLAY INVENTORY
    public static String displayInventory() {
        var player = GUI.getPlayer();
        StringBuilder sb = new StringBuilder();
        sb.append("\n═══ INVENTORY ═══\n");

        sb.append("\n⚔️ EQUIPPED: \n");
        if (player.getEquippedWeapon() != null) {
            sb.append("  Weapon: ").append(player.getEquippedWeapon().getName()).append("\n");
        }

        // type is armor slot, armor is the item
        player.getEquippedArmor().forEach((type, armor) -> sb.append("  ").append(type).append(": ").append(armor.getName()).append(" (+").append(armor.getDefenseBonus()).append(" def)"));

        if (player.getEquippedAccessory() != null) {
            Accessory acc = player.getEquippedAccessory();
            sb.append("  Accessory: ").append(acc.getName()).append(" (+").append(acc.getBonusAmount()).append(" ").append(acc.getStatBonus().name()).append(")");
        }

        sb.append("\n🎒BACKPACK:");
        if (player.getFullInventory().isEmpty()) {
            sb.append("  Empty\n");
        } else {
            // group items by type
            player.getFullInventory().stream().collect(Collectors.groupingBy(Item::getType)).forEach((type, items) -> {
                sb.append("\n  ").append(type).append(":");
                for (int i = 0; i < items.size(); i++) {
                    sb.append("    [").append(i + 1).append("] ").append(items.get(i)).append("\n");
                }
            });
        }

        sb.append("\n💰 Gold: ").append(player.getGold()).append("\n\n");
        sb.append("═════════════════\n");
        return sb.toString();
    }

    //------------------------------------------------------------------------------------------------- CHARACTER ART
    //                                                                                                    KNIGHT ASCII
    public static String displayKnight(int image) {

        return image == 1 ? """
                                        {}
                                       .--.     \s
                                      /.--.\\
                                      |====|
                                      |`::`|
                                  .-;`\\..../`;-.
                                 /  |...::...|  \\
                                |   /'''::'''\\   |
                                ;--'\\   ::   /\\--;
                                <__>,>._::_.<,<__>
                                |  |/   ^^   \\|  |
                                \\::/|        |\\::/
                                |||\\|        |/|||
                                ''' |___/\\___| '''
                                     \\_ || _/
                                     <_ >< _>
                                     |  ||  |
                                     |  ||  |
                                    _\\.:||:./_
                                   /____/\\____\\\
                """ : """
                                        {}
                                       .--.     \s
                                      /.--.\\
                                      |====|
                                      |`::`|
                                  .-;`\\..../`;-.
                                /   |...::...|   \\
                               |  / /'''::'''\\\\   |
                               ;--' \\   ::   / '--;
                               <__>, >._::_.< ,<__>
                               |  | /   ^^   \\ |  |
                               \\::/ |        | \\::/
                               |||\\ |        | /|||
                               '''  |___/\\___|  '''
                                     \\_ || _/
                                     <_ >< _>
                                     |  ||  |
                                     |  ||  |
                                    _\\.:||:./_
                                   /____/\\____\\\
                """;
    }

    //                                                                                                      MAGE ASCII
    public static String displayMage(int image) {

        return image == 1 ? """
                                         ____\s
                                       .'* *.'
                                    __/_*_*(_
                                   / _______ \\
                                  _\\_)/___\\(_/_\s
                                 / _((\\- -/))_ \\
                                 \\ \\())(-)(()/ /
                                  ' \\(((()))/ '
                                 / ' \\)).))/ ' \\
                                / _ \\ - | - /_  \\
                               (   ( .;''';. .'  )
                               _\\"__ /    )\\ __"/_
                                 \\/  \\   ' /  \\/
                                  .'  '...' ' )
                                   / /  |  \\ \\
                                  / .   .   . \\
                                 /   .     .   \\
                                /   /   |   \\   \\
                              .'   /    b    '.  '.
                          _.-'    /     Bb     '-. '-._\s
                      _.-'       |      BBb       '-.  '-.\s
                     (___________\\____.dBBBb.________)____)
                """ : """
                       ⋆.ೃ࿔.             ____\s
                                       .'* *.'
                                    __/_*_*(_               ⟡
                          ⟡ ݁₊ .    / _______ \\   ⟡
                                  _\\_)/___\\(_/_\s
                                 / _((\\- -/))_ \\        ⋆.ೃ࿔.
                    ⋆.ೃ࿔*:･      \\ \\())(o)(()/ /
                                  ' \\(((()))/ '
                                / /' \\)).))/ ' \\ \\
                               / _/ \\ - | - /_  \\ \\  ⟡
                              (  / ( .;''';. .'  | )
                         ⟡    \\"_\\_  /𖦹꩜.ೃ࿔\\ _ _/ /
                                \\/ \\ \\⋆𖦹⋆ˎˊ˗/  \\/   ⟡ ݁₊ .
                                  .'  \\｡𖦹°★/' )
                            ⟡      / /  |  \\ \\
                                  / .   .   . \\      ⋆.ೃ࿔.
                       ⋆.ೃ࿔.     /   .     .   \\
                                /   /   |   \\   \\
                              .'   /    b    '.  '.       ⟡
                          _.-'    /     Bb     '-. '-._\s
                      _.-'       |      BBb       '-.  '-.\s
                     (___________\\____.dBBBb.________)____)
                """;
    }

    //                                                                                                    REAPER ASCII
    public static String displayReaper(int image) {
        return image == 1 ? """
                                                                 .""--..__
                                             _                     []       ``-.._
                                          .'` `'.                  ||__           `-._
                                         /    ,-.\\                 ||_ ```---..__     `-.
                                        /    /:::\\\\               /|//}          ``--._  `.
                                        |    |:::||              |////}                `-. \\
                                        |    |:::||             //'///                    `.\\
                                        |    |:::||            //  ||'                      `|
                                        /    |:::|/        _,-//\\  ||
                                       /`    |:::|`-,__,-'`  |/  \\ ||
                                     /`  |   |'' ||           \\   |||
                                   /`    \\   |   ||            |  /||
                                 |`       |  |   |)            \\ | ||
                                |          \\ |   /      ,.__    \\| ||
                                /           `         /`    `\\   | ||
                               |                     /        \\  / ||
                               |                     |        | /  ||
                               /         /           |        `(   ||
                              /          .           /          )  ||
                             |            \\          |     ________||
                            /             |          /     `-------.|
                           |\\            /          |              ||
                           \\/`-._       |           /              ||
                            //   `.    /`           |              ||
                           //`.    `. |             \\              ||
                          ///\\ `-._  )/             |              ||
                         //// )   .(/               |              ||
                         ||||   ,'` )               /              //
                         ||||  /                    /             ||\s
                         `\\\\` /`                    |             //\s
                             |`                     \\            || \s
                            /                        |           // \s
                          /`                          \\         //  \s
                        /`                            |        ||   \s
                        `-.___,-.      .-.        ___,'        (/   \s
                                 `---'`   `'----'`
                """ : """
                                                        ༒︎       .""--..__
                                      ༒︎      _                    []       ``-.._
                                           .'` `'.                 ||__           `-._
                                          /  .""\".\\                ||_ ```---..__     `-.
                                         /  /_  _`\\\\    ༒︎        /|//}          ``--._  `.
                                         | |(_)(_)||             |////}                `-. \\
                           ༒︎            | |  /\\  )|            //'///                    `.\\
                                         | |L====J |            // ||'                      `|
                                        /  /'-..-' /        _,-//\\ ||                       𓄼
                                       /   |  :: | |_.__,-'`  |/  \\||                      ۵
                                     /`|   `\\-::.| |          \\   |||    ༒︎                𓄹
                                   /`  `|   /    | |           |  /||
                            ༒︎   |`     \\   |    / /           \\ | ||                       ۵
                                |        `\\_|    |/     ,.__    \\| ||
                                /           `         /`    `\\   | ||                      ۵
                               |                     /        \\  / ||
                               |                     |        | /  ||
                         ༒︎    /         /           |        `(   ||
                              /          .           /          )  ||
                             |            \\          |     ________||
                            /             |          /     `-------.|
                           |\\            /          |              ||
                           \\/`-._       |           /              ||
                            //   `.    /`           |              ||
                           //`.    `. |             \\              ||
                          ///\\ `-._  )/             |              ||
                         //// )   .(/               |              ||
                         ||||   ,'` )               /              //
                         ||||  /                    /             ||\s
                         `\\\\` /`                    |             //\s
                             |`                     \\            || \s
                            /                        |           // \s
                          /`                          \\         //  \s
                        /`                            |        ||   \s
                        `-.___,-.      .-.        ___,'        (/   \s
                                 `---'`   `'----'`
                """;
    }

    //----------------------------------------------------------------------------------------------- VICTORY MESSAGES
    //                                                                                                    VICTORY ART
    public static String displayVictoryArt() {
        return """
                
                
                                 ██╗   ██╗██╗ ██████╗████████╗ ██████╗ ██████╗ ██╗   ██╗██╗
                                 ██║   ██║██║██╔════╝╚══██╔══╝██╔═══██╗██╔══██╗╚██╗ ██╔╝██║
                                 ██║   ██║██║██║        ██║   ██║   ██║██████╔╝ ╚████╔╝ ██║
                                 ╚██╗ ██╔╝██║██║        ██║   ██║   ██║██╔══██╗  ╚██╔╝  ╚═╝
                                  ╚████╔╝ ██║╚██████╗   ██║   ╚██████╔╝██║  ██║   ██║   ██╗
                                   ╚═══╝  ╚═╝ ╚═════╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚═╝
                
                                              ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣤⣤⣄⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                              ⠀⠀⠀⠀⠀⣀⣤⣶⣿⠿⠟⠛⠉⠉⠉⠛⠻⠿⣿⣶⣤⣀⠀⠀⠀⠀⠀
                                              ⠀⠀⠀⣠⣾⣿⡿⠋⠀⠀⠀⠀⢀⣀⣀⡀⠀⠀⠀⠙⢿⣿⣷⣄⠀⠀⠀
                                              ⠀⢀⣴⣿⡿⠋⠀⠀⠀⠀⠀⣴⣿⣿⣿⣦⠀⠀⠀⠀⠀⠙⢿⣿⣦⡀⠀
                                              ⢀⣾⣿⡟⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⢻⣿⣷⡀
                                              ⣾⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⣿⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣷
                                              ⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿
                                              ⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣤⣤⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⣿⣿
                                              ⢻⣿⣧⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⣼⣿⡟
                                              ⠈⢿⣿⣦⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⣴⣿⡿⠁
                                              ⠀⠈⢿⣿⣷⣄⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⣠⣾⣿⡿⠁⠀
                                              ⠀⠀⠀⠙⢿⣿⣷⣤⡀⠈⠛⠿⣿⣿⣿⣿⠿⠛⢀⣤⣾⣿⡿⠋⠀⠀⠀
                                              ⠀⠀⠀⠀⠀⠙⠿⣿⣿⣶⣤⣄⣀⣀⣀⣠⣤⣶⣿⣿⠿⠋⠀⠀⠀⠀⠀
                                              ⠀⠀⠀⠀⠀⠀⠀⠈⠙⠻⠿⣿⣿⣿⣿⠿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀
                
                """;
    }

    //                                                                                                   VICTORY INFO
    public static String displayVictoryInfo(String CLASS) {
        return "         You have defeated the Ancient Guardian!\n" +
               "         " + GUI.getName() + " the " + CLASS + " has conquered the dungeon!\n\n" +
               "         ═══════════ FINAL STATISTICS ═══════════\n" +
               "         Level: " + GUI.getPlayer().getLevel() + "\n" +
               "         Experience: " + GUI.getPlayer().getExperience() + "\n" +
               "         Gold Collected: " + GUI.getPlayer().getGold() + "\n" +
               "         Rooms Explored: " + GUI.countVisitedRooms() + "\n" +
               "         Commands Used: " + GUI.getCommandCount() + "\n" +
               "         ═════════════════════════════════════\n\n" +
               "         Congratulations, champion!\n" +
               "         Type 'restart' to begin a new adventure\n" +
               "         Type 'exit' to quit the game\n";
    }

    //------------------------------------------------------------------------------------------------ ENEMY ART
    //                                                                                                    SKELETON ART
    public static String displaySkeletonArt() {
        return """
                        ⠀⠀⠀⠀⢀⣀⣤⣤⣤⣤⣄⡀⠀⠀⠀⠀
                        ⠀⢀⣤⣾⣿⣾⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀
                        ⢠⣾⣿⢛⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀
                        ⣾⣯⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧
                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                        ⣿⡿⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠻⢿⡵
                        ⢸⡇⠀⠀⠉⠛⠛⣿⣿⠛⠛⠉⠀⠀⣿⡇
                        ⢸⣿⣀⠀⢀⣠⣴⡇⠹⣦⣄⡀⠀⣠⣿⡇
                        ⠈⠻⠿⠿⣟⣿⣿⣦⣤⣼⣿⣿⠿⠿⠟⠀
                        ⠀⠀⠀⠀⠸⡿⣿⣿⢿⡿⢿⠇⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠈⠁⠈⠁⠀⠀⠀⠀⠀⠀
                """;
    }

    //                                                                                                      GOBLIN ART
    public static String displayGoblinArt() {
        return """
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⣿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⢀⡼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠢⣤⣀⡀⠀⠀⠀⢿⣧⣄⡉⠻⢿⣿⣿⡿⠟⢉⣠⣼⡿⠀⠀⠀⠀⣀⣤⠔⠀
                        ⠀⠀⠈⢻⣿⣶⠀⣷⠀⠉⠛⠿⠶⡴⢿⡿⢦⠶⠿⠛⠉⠀⣾⠀⣶⣿⡟⠁⠀⠀
                        ⠀⠀⠀⠀⠻⣿⡆⠘⡇⠘⠷⠠⠦⠀⣾⣷⠀⠴⠄⠾⠃⢸⠃⢰⣿⠟⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠋⢠⣾⣥⣴⣶⣶⣆⠘⣿⣿⠃⣰⣶⣶⣦⣬⣷⡄⠙⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⢋⠛⠻⠿⣿⠟⢹⣆⠸⠇⣰⡏⠻⣿⠿⠟⠛⡙⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠈⢧⡀⠠⠄⠀⠈⠛⠀⠀⠛⠁⠀⠠⠄⢀⡼⠁⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠈⢻⣦⡀⠃⠀⣿⡆⢰⣿⠀⠘⢀⣴⡟⠁⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣿⣦⡀⠘⠇⠸⠃⢀⣴⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣷⣄⣠⣾⣿⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⣿⣿⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                """;
    }

    //                                                                                                         ORC ART
    public static String displayOrcArt() {
        return """
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⣧⣄⣉⣉⣠⣼⣶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⡿⣿⣿⣿⣿⢿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⣼⣤⣤⣈⠙⠳⢄⣉⣋⡡⠞⠋⣁⣤⣤⣧⠀⠀⠀⠀⠀⠀⠀
                        ⢲⣶⣤⣄⡀⢀⣿⣄⠙⠿⣿⣦⣤⡿⢿⣤⣴⣿⠿⠋⣠⣿⠀⢀⣠⣤⣶⡖⠀
                        ⠀⠙⣿⠛⠇⢸⣿⣿⡟⠀⡄⢉⠉⢀⡀⠉⡉⢠⠀⢻⣿⣿⡇⠸⠛⣿⠋⠀⠀
                        ⠀⠀⠘⣷⠀⢸⡏⠻⣿⣤⣤⠂⣠⣿⣿⣄⠑⣤⣤⣿⠟⢹⡇⠀⣾⠃⠀⠀⠀
                        ⠀⠀⠀⠘⠀⢸⣿⡀⢀⠙⠻⢦⣌⣉⣉⣡⡴⠟⠋⡀⢀⣿⡇⠀⠃⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⢸⣿⣧⠈⠛⠂⠀⠉⠛⠛⠉⠀⠐⠛⠁⣼⣿⡇⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠸⣏⠀⣤⡶⠖⠛⠋⠉⠉⠙⠛⠲⢶⣤⠀⣹⠇⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣶⣿⣿⣿⣿⣿⣿⣶⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠛⠛⠛⠛⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
                """;
    }

    //                                                                                                        BOSS ART
    public static String displayBossArt() {
        return """
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⢒⣩⣥⠡⠀⠀⠀⠀⠌⣬⣍⡒⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠫⣶⣿⣿⣿⣧⡑⢄⡠⢊⣼⣿⣿⣿⣦⠝⢆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⡜⡅⣿⣦⡙⢿⣿⣿⣿⣦⣴⣿⣿⣿⡿⢋⣴⣿⢨⢣⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⡜⣼⡇⣿⣿⣿⣦⡹⣿⣿⣿⣿⣿⣿⢋⣴⣿⣿⣿⢸⣧⢣⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⣼⣿⠗⠛⠻⠿⣿⣷⡘⡿⠟⠻⢿⢃⣾⣿⠿⠟⠛⠺⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠸⠟⠀⣀⢀⣄⣀⠈⠙⡗⣰⠿⠿⣆⢺⠋⠁⣀⣤⡀⣀⠈⠻⠇⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⢀⢦⣍⡛⠶⣤⠤⠜⣀⣤⠀⣶⣶⠀⣤⣐⠣⢤⣤⠶⢛⣩⡶⡀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠈⢧⡙⢿⣷⡆⣶⣿⡿⢋⢸⣿⣿⡆⡙⢿⣿⣶⢰⣾⡿⢋⡼⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⢙⠦⣍⠰⣿⡟⣡⣿⢸⣿⣿⡇⣿⡌⢻⣿⠆⣩⠶⡋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠐⢌⢷⢈⢳⣌⠁⣤⡌⢸⣿⣿⡇⢡⣤⠈⣡⡞⡁⡾⡡⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡎⣧⡹⢰⣿⣷⣈⠛⠛⣡⣾⣿⡆⢏⣼⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⣿⣇⢸⣿⣿⣿⣿⣿⣿⣿⣿⡇⣸⣿⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠃⣿⣿⣄⠙⢉⣩⣉⣉⣍⡉⠋⣨⣿⣿⠘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⣄⠻⣿⣿⣷⡆⢀⣀⣀⡀⢰⣾⣿⣿⠟⣠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⣌⠻⢋⡤⠉⠉⠉⠁⣤⡙⠟⣡⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⣿⡇⠀⠀⠀⠀⢸⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                """;
    }

    //                                                                                                   GET ENEMY ART
    public static String getEnemyArt(String enemyType) {
        return switch (enemyType) {
            case "Skeleton" -> displaySkeletonArt();
            case "Goblin" -> displayGoblinArt();
            case "Orc" -> displayOrcArt();
            case "Boss" -> displayBossArt();
            default -> ""; // no art for unknown types
        };
    }

    //------------------------------------------------------------------------------------------------ DEATH MESSAGES
    //                                                                                                      DEATH ART
    public static String displayDeathArt() {
        return """
                
                
                                     ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
                                    ██░███░█▄░▄██░▄▄▄██░██░██░████░▄▄▀██░▄▄▄░██░▄▄▀
                                    ██░█░█░██░███░▄▄▄██░██░██░▄▄▄█░██░██░███░██░██░
                                    ██▄▀▄▀▄██▄███░▀▀▀██░▀▀░██░████░▀▀░██░▀▀▀░██░▀▀░
                                     ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
                
                                              ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣤⣤⣄⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                              ⠀⠀⠀⠀⠀⣀⣤⣶⣿⠿⠟⠛⠉⠉⠉⠛⠻⠿⣿⣶⣤⣀⠀⠀⠀⠀⠀
                                              ⠀⠀⠀⣠⣾⣿⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣷⣄⠀⠀⠀
                                              ⠀⢀⣴⣿⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣦⡀⠀
                                              ⢀⣾⣿⡟⠀⠀⠀⠀⣠⣤⣤⣤⣤⣤⣤⣤⣄⠀⠀⠀⠀⠀⠹⣿⣷⡀\s
                                              ⣾⣿⡟⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⢻⣿⣷\s
                                              ⣿⣿⠁⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠈⣿⣿\s
                                              ⣿⣿⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⣿⣿\s
                                              ⢻⣿⣧⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⠀⠀⠀⠀⣼⣿⡟\s
                                              ⠈⢿⣿⣦⠀⠀⠀⠀⠈⠛⠿⠿⠿⠿⠛⠁⠀⠀⠀⠀⠀⠀⣴⣿⡿⠁\s
                                              ⠀⠈⢿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⣿⡿⠁⠀\s
                                              ⠀⠀⠀⠙⢿⣿⣷⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⣿⡿⠋⠀⠀⠀\s
                                              ⠀⠀⠀⠀⠀⠙⠿⣿⣿⣶⣤⣄⣀⣀⣠⣤⣶⣿⣿⠿⠋⠀⠀⠀⠀⠀\s
                                              ⠀⠀⠀⠀⠀⠀⠀⠈⠙⠻⠿⣿⣿⣿⣿⠿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀\s
                
                """;
    }

    //                                                                                                     DEATH INFO
    public static String displayDeathInfo(String CLASS) {
        // builds death message with optional enemy info, final stats, and restart options
        return (GUI.getCurrentEnemy() != null ? "         You were slain by " + GUI.getCurrentEnemy().getName() + " in the " + GUI.getCurrentRoom().getName() + ".\n" : "         You have fallen in the " + GUI.getCurrentRoom().getName() + ".\n") + "         " + GUI.getName() + " the " + CLASS + " has met their end.\n" + "         Your journey lasted " + GUI.getCommandCount() + " commands.\n" + "\n         ═══════════ FINAL STATISTICS ═══════════\n" + "         Level: " + GUI.getPlayer().getLevel() + "\n" + "         Experience: " + GUI.getPlayer().getExperience() + "\n" + "         Gold Collected: " + GUI.getPlayer().getGold() + "\n" + "         Rooms Explored: " + GUI.countVisitedRooms() + "\n" + "         ═════════════════════════════════════\n\n" + "         Would you like to try again?\n" + "         Type 'restart' to begin a new adventure\n" + "         Type 'exit' to quit the game\n";
    }
}
