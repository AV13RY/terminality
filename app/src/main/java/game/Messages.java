package game;

import characters.Boss;
import characters.Enemy;
import items.Accessory;
import items.Armor;
import items.Chest;
import items.Item;
import world.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Messages {

    //--------------------------------------------------------------------------------------------------- TEXT METHODS
    //                                                                                                ASCII ART TITLES
    public static String tutorialTitleMessage() {
        return """
                ████████╗███████╗██████╗ ███╗   ███╗██╗███╗   ██╗ █████╗ ██╗     ██╗████████╗██╗   ██╗\s
                ╚══██╔══╝██╔════╝██╔══██╗████╗ ████║██║████╗  ██║██╔══██╗██║     ██║╚══██╔══╝╚██╗ ██╔╝\s
                   ██║   █████╗  ██████╔╝██╔████╔██║██║██╔██╗ ██║███████║██║     ██║   ██║    ╚████╔╝ \s
                   ██║   ██╔══╝  ██╔══██╗██║╚██╔╝██║██║██║╚██╗██║██╔══██║██║     ██║   ██║     ╚██╔╝  \s
                   ██║   ███████╗██║  ██║██║ ╚═╝ ██║██║██║ ╚████║██║  ██║███████╗██║   ██║      ██║   \s
                   ╚═╝   ╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝╚═╝   ╚═╝      ╚═╝   \s
                ═══════════════════════════════════════════════════════════════════════════════════════""";
    }

    public static String graveyardTitleMessage() {
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

    public static String churchTitleMessage() {
        return """
                      ▄████████     ▄█    █▄    ███    █▄     ▄████████  ▄████████    ▄█    █▄          \s
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

    //                                                                                                  INTRO MESSAGES 
    public static String tutorialIntroMessage() {

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

    public static String churchIntroMessage(String CLASS) {
        return "\nYou push open the heavy wooden doors and step into the abandoned church. Dust motes dance\n" + "in the colored light streaming through stained glass windows, casting ethereal patterns\n" + "across the crumbling pews and debris-strewn floor.\n" + "                                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⡀⡀⠀         \n" + "                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⡀⠀⡀⠀⠂⡀⢀⢰⠀⢂⠀⠀⠀⠀⠀⠀⠀⠀\n" + "                         ⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣐⣬⣄⣷⡀⢸⡃⡘⡸⡄⢸⠀⠀⡇⠀⢠⠀⠀⠀\n" + "                         ⠀⠀⠀⠀⠀⣠⡴⠚⢉⢍⢂⣼⣴⣿⣿⣿⣷⣷⣷⣣⣏⣆⣼⠀⠀⠄⠀⠀⠀\n" + "                         ⠀⠀⠀⢠⡞⠋⠀⡑⣮⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣷⣼⣆⣌⡠⢁⡤\n" + "                         ⠀⠀⣰⠋⠀⣀⣺⣾⣿⣿⣿⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠁\n" + "                         ⠀⡼⠁⢀⣿⣿⣿⡿⣿⡛⣿⣿⣿⡷⢸⣿⠀⠀⠀⠀⣹⣿⣿⣿⣿⣟⠣⠀⠀\n" + "                         ⡰⠁⢀⣼⣿⠟⢿⡇⠹⣿⣿⣿⠟⠀⢠⡿⠀⠀⣠⣾⡿⣿⡥⠊⠁⠀⠀⠀⠀\n" + "                         ⠁⢠⣾⠟⠁⠀⠈⠳⢿⣦⣠⣤⣦⣼⠟⠁⣠⣾⣿⣿⣟⠍⠒⠀⠠⠀⠀⠀⠀\n" + "                         ⢠⡟⠁⢀⣀⣀⣀⣀⡀⠈⣉⣉⣡⣤⣶⣿⡿⡿⡿⡻⠥⠑⡀⠀⠀⠀⠀⠀⠀\n" + "                         ⠏⡠⠚⠉⠋⢍⠋⢫⠋⠛⢹⢻⡟⠻⣟⢏⠌⢊⡌⠌⠄⠀⠀⠀⠀⠀⠀⠀⠀\n" + "                         ⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠘⠂⠘⠂⠿⠈⠀⠀⠀⠘⠀⠀⠀⠀⠀\n\n\n" + "At the altar, a spectre materializes - an overseer of sort.\n" + "Its hollow voice echoes through the sacred halls:\n\n" + "\"Ah, a " + CLASS + " has remembered their path.\n" + "You will need more than courage to survive what lies beyond these walls.\"\n\n" + "【﻿ＴＨＥ　ＥＹＥ　ＧＬＯＷＳ】\n\n" + displayClassWeapons(CLASS) + "\"Choose wisely, " + GUI.getName() + ". This decision will shape your journey through the darkness.\"\n\n" + "❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█═█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚\n\n" + "Which weapon calls to you?" + "  - Use the command: [ choose <1 or 2> ]\n";
    }

    public static String tutorialHelpMessage() {
        return """
                
                AVAILABLE COMMANDS:
                - colour [colour]  : Change text color (default, red, green, blue, yellow, cyan, magenta, white)
                - clear            : Clear the terminal screen
                - help             : Display this help message
                - exit             : Quit the game
                
                - start            : Start the game
                
                """;
    }

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

    public static String inGameHelpMessage() {
        return """
                
                ═══ AVAILABLE COMMANDS ═══
                EXPLORATION:
                  move [direction] - Move to another room (north/south/east/west)
                  look            - Examine the current room
                  map             - Display the dungeon map
                
                COMBAT:
                  attack          - Attack the current enemy
                  flee            - Attempt to escape combat
                
                CHARACTER:
                  inventory       - View your inventory
                
                ITEMS:
                  use [#]         - Use a consumable item
                  equip [#]       - Equip armor or accessory
                  open [#]        - Open a chest
                SYSTEM:
                  help            - Display this help message
                  exit            - Quit the game
                ═════════════════════════
                
                """;
    }

    public static String displayClassWeapons(String CLASS) {

        if ("Knight".equals(CLASS)) {
            return displayKnightWeapons();
        }

        if ("Mage".equals(CLASS)) {
            return displayMageWeapons();
        }

        return displayReaperWeapons();
    }

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

    private static String displayReaperWeapons() {

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

    public static String displayMap() {
        StringBuilder sb = new StringBuilder();
        final int CONSOLE_WIDTH = 91;
        String divider = "═".repeat(CONSOLE_WIDTH);

        // centre the map display
        String title = " MAP ";
        int titlePadding = (CONSOLE_WIDTH - title.length()) / 2;
        String centeredTitle = "═".repeat(titlePadding) + title + "═".repeat(CONSOLE_WIDTH - titlePadding - title.length());

        sb.append("\n").append(centeredTitle).append("\n\n");

        // Find map bounds
        int minX = 0, maxX = 0, minY = 0, maxY = 0;
        for (Room room : GUI.getMapBuilder().getAllRooms().values()) {
            minX = Math.min(minX, room.getX());
            maxX = Math.max(maxX, room.getX());
            minY = Math.min(minY, room.getY());
            maxY = Math.max(maxY, room.getY());
        }

        // Calculate map width for centering
        int mapWidth = (maxX - minX + 1) * 4;

        // Display map from top to bottom
        for (int y = maxY; y >= minY; y--) {
            StringBuilder mapRow = new StringBuilder();
            StringBuilder connectionRow = new StringBuilder();

            for (int x = minX; x <= maxX; x++) {
                String coordKey = x + "," + y;
                Room room = GUI.getMapBuilder().getAllRooms().get(coordKey);

                if (room != null) {
                    // Determine room symbol
                    String symbol;
                    if (room == GUI.getCurrentRoom()) {
                        symbol = "[◉]"; // Current position
                    } else if (room.getType() == Room.RoomType.BOSS) {
                        symbol = "[B]"; // Boss room
                    } else if (room.getType() == Room.RoomType.TREASURE) {
                        symbol = "[T]"; // Treasure room
                    } else if (room.isVisited()) {
                        symbol = "[·]"; // Visited room
                    } else {
                        symbol = "[?]"; // Unvisited room
                    }

                    mapRow.append(symbol);

                    // Add horizontal connections
                    if (room.getExit("east") != null) {
                        mapRow.append("─");
                    } else {
                        mapRow.append(" ");
                    }

                    // Add vertical connections
                    if (room.getExit("south") != null) {
                        connectionRow.append(" │ ");
                    } else {
                        connectionRow.append("   ");
                    }
                    connectionRow.append(" ");
                } else {
                    mapRow.append("    ");
                    connectionRow.append("    ");
                }
            }

            // Center the map rows
            int padding = (CONSOLE_WIDTH - mapWidth) / 2;
            String paddingStr = " ".repeat(Math.max(0, padding));

            sb.append(paddingStr).append(mapRow).append("\n");
            if (y > minY) {
                sb.append(paddingStr).append(connectionRow).append("\n");
            }
        }

        // Center the legend
        String legend = "Legend: [◉]=You [·]=Visited [?]=Unexplored [T]=Treasure [B]=Boss";
        int legendPadding = (CONSOLE_WIDTH - legend.length()) / 2;
        sb.append("\n").append(" ".repeat(Math.max(0, legendPadding))).append(legend).append("\n");
        sb.append(divider).append("\n");
        return sb.toString();
    }

    public static String displayCombatStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n═══ COMBAT STATUS ═══\n\n");
        sb.append("Your HP: ").append(GUI.getPlayer().getCurrentHealth()).append("/").append(GUI.getPlayer().getMaxHealth()).append("\n");

        if (GUI.getPlayer().getMaxMana() > 0) {
            sb.append("Your MP: ").append(GUI.getPlayer().getMana()).append("/").append(GUI.getPlayer().getMaxMana()).append("\n");
        }

        sb.append(GUI.getCurrentEnemy().getName()).append(" HP: ").append(GUI.getCurrentEnemy().getCurrentHealth()).append("/").append(GUI.getCurrentEnemy().getMaxHealth()).append("\n\n");

        // Show boss phase if applicable
        if (GUI.getCurrentEnemy() instanceof Boss boss) {
            sb.append("Boss Phase: ").append(boss.getPhase()).append("\n");
        }

        sb.append("═════════════════════\n");
        return sb.toString();
    }

    public static String displayCurrentRoom() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n═══ ").append(GUI.getCurrentRoom().getName().toUpperCase()).append(" ═══\n");
        sb.append(GUI.getCurrentRoom().getDescription());

        // Show exits
        sb.append("\nExits: ").append(String.join(", ", GUI.getCurrentRoom().getExits().keySet())).append("\n");

        if (GUI.getCurrentRoom().hasEnemies()) {
            sb.append("\nEnemies present:");
            for (Enemy enemy : GUI.getCurrentRoom().getEnemies()) {
                sb.append("- ").append(enemy.getName()).append(" (HP: ").append(enemy.getCurrentHealth()).append(")");
            }
        }

        // Show accessible chests
        if (GUI.getCurrentRoom().hasAccessibleChests()) {
            sb.append("\nChests available:");
            for (Chest chest : GUI.getCurrentRoom().getChests()) {
                if (chest.isClosed()) {
                    sb.append("- ").append(chest.getRarity()).append(" chest");
                }
            }
        }
        return sb.toString();
    }

    public static String displayInventory() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n═══ INVENTORY ═══\n");

        // Equipped items
        sb.append("\n⚔️ EQUIPPED: \n");
        if (GUI.getPlayer().getEquippedWeapon() != null) {
            sb.append("  Weapon: ").append(GUI.getPlayer().getEquippedWeapon().getName()).append("\n");
        }

        for (Map.Entry<Armor.ArmorType, Armor> entry : GUI.getPlayer().getEquippedArmor().entrySet()) {
            sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue().getName()).append(" (+").append(entry.getValue().getDefenseBonus()).append(" def)");
        }

        if (GUI.getPlayer().getEquippedAccessory() != null) {
            Accessory acc = GUI.getPlayer().getEquippedAccessory();
            sb.append("  Accessory: ").append(acc.getName()).append(" (+").append(acc.getBonusAmount()).append(" ").append(acc.getStatBonus().name()).append(")");
        }

        // Inventory items
        sb.append("\n🎒BACKPACK:");
        if (GUI.getPlayer().getFullInventory().isEmpty()) {
            sb.append("  Empty\n");
        } else {
            Map<Item.ItemType, List<Item>> itemsByType = new HashMap<>();
            for (Item item : GUI.getPlayer().getFullInventory()) {
                itemsByType.computeIfAbsent(item.getType(), k -> new ArrayList<>()).add(item);
            }

            for (Map.Entry<Item.ItemType, List<Item>> entry : itemsByType.entrySet()) {
                sb.append("\n  ").append(entry.getKey()).append(":");
                for (int i = 0; i < entry.getValue().size(); i++) {
                    Item item = entry.getValue().get(i);
                    sb.append("    [").append(i + 1).append("] ").append(item.toString()).append("\n");
                }
            }
        }

        sb.append("\n💰 Gold: ").append(GUI.getPlayer().getGold()).append("\n\n");
        sb.append("═════════════════\n");
        return sb.toString();
    }

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

    public static String displayDeathInfo(String CLASS) {

        return (GUI.getCurrentEnemy() != null ? "         You were slain by " + GUI.getCurrentEnemy().getName() + " in the " + GUI.getCurrentRoom().getName() + ".\n" : "         You have fallen in the " + GUI.getCurrentRoom().getName() + ".\n") + "         " + GUI.getName() + " the " + CLASS + " has met their end.\n" + "         Your journey lasted " + GUI.getCommandCount() + " commands.\n" +

                // Display final stats
                "\n         ═══════════ FINAL STATISTICS ═══════════\n" + "         Level: " + GUI.getPlayer().getLevel() + "\n" + "         Experience: " + GUI.getPlayer().getExperience() + "\n" + "         Gold Collected: " + GUI.getPlayer().getGold() + "\n" + "         Rooms Explored: " + GUI.countVisitedRooms() + "\n" + "         ═════════════════════════════════════\n\n" +

                // Options
                "         Would you like to try again?\n" + "         Type 'restart' to begin a new adventure\n" + "         Type 'exit' to quit the game\n";
    }
}
