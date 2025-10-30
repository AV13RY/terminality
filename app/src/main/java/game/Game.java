package game;

import characters.Player;
import items.Weapon;
import world.Room;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Game {


    // GAME VARIABLES -------------------------------------------------------------------------------------------------
    // Section Declarations
    private JTextArea display;
    private JTextField terminal;
    private JTextArea characterArea;
    private JTextArea statsArea;
    private JTextArea sidebarArea;

    // Log Declarations
    private List<String> commandHistory;
    private int commandCount;

    // Game state Declarations
    private Room currentRoom;
    private Player player;
    private String NAME = "";
    private String CLASS = "";

    // Color Declarations
    private final Color RED = Color.RED;
    private final Color GREEN = Color.GREEN;
    private final Color BLUE = Color.BLUE;
    private final Color YELLOW = Color.YELLOW;
    private final Color CYAN = Color.CYAN;
    private final Color MAGENTA = Color.MAGENTA;
    private final Color WHITE = Color.WHITE;
    private final Color BLACK = Color.BLACK;
    private final Color DEFAULT = new Color(0xD8125B);
    private final Color DEFAULT2 = new Color(0x230410);

    // CORE METHODS ---------------------------------------------------------------------------------------------------
    // Game Constructor
    public Game() {
        initialiseUI();
        initialiseWorld();
        tutorialTitleMessage();
        tutorialWelcomeMessage();
        testing("reaper");
    }

    // Main Method
    public static void main(String[] args) {
        Game game = new Game();
    }

    // TEXT METHODS ---------------------------------------------------------------------------------------------------
    // Tutorial ASCII Art Title
    private void tutorialTitleMessage() {
        println("");
        println("████████╗███████╗██████╗ ███╗   ███╗██╗███╗   ██╗ █████╗ ██╗     ██╗████████╗██╗   ██╗");
        println("╚══██╔══╝██╔════╝██╔══██╗████╗ ████║██║████╗  ██║██╔══██╗██║     ██║╚══██╔══╝╚██╗ ██╔╝");
        println("   ██║   █████╗  ██████╔╝██╔████╔██║██║██╔██╗ ██║███████║██║     ██║   ██║    ╚████╔╝");
        println("   ██║   ██╔══╝  ██╔══██╗██║╚██╔╝██║██║██║╚██╗██║██╔══██║██║     ██║   ██║     ╚██╔╝");
        println("   ██║   ███████╗██║  ██║██║ ╚═╝ ██║██║██║ ╚████║██║  ██║███████╗██║   ██║      ██║");
        println("   ╚═╝   ╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝╚═╝   ╚═╝      ╚═╝ \n");
        println("═══════════════════════════════════════════════════════════════════════════════════════");
    }

    // Welcome message with wait
    private void tutorialWelcomeMessage() {
        println("                      A Java Dungeon Crawler by Jack McGillivray                       ");
        println("═══════════════════════════════════════════════════════════════════════════════════════");
        println("▖ ▖   ▄▖▖▖▄▖▄▖▄▖▄▖▄▖▖    ▖ ▖");
        println("▝▖▝▖  ▐ ▌▌▐ ▌▌▙▘▐ ▌▌▌   ▞ ▞ ");
        println("▞ ▞   ▐ ▙▌▐ ▙▌▌▌▟▖▛▌▙▖  ▝▖▝▖\n");


        println("Hello, World.. . . . . ............... .  . . . .  .");
        println("Ah what am I saying, you probably already know this isn't just your average powershell.\n");
        println("Anyway, welcome to Terminality, a text-based dungeon crawler.");
        println("Your goal is to navigate the crypt, slay its inhabitants, and defeat the final boss.\n");
        println("Your experience will consist of three main interactions:\n");
        println("1. >> \uD835\uDDE0\uD835\uDDE2\uD835\uDDE9\uD835\uDDD8\uD835\uDDE0\uD835\uDDD8\uD835\uDDE1\uD835\uDDE7 << When required, you'll be prompted to move between rooms.");
        println("- Use the command: [ move <direction> ]\n");
        println("2. >> \uD835\uDDD6\uD835\uDDE2\uD835\uDDE0\uD835\uDDD5\uD835\uDDD4\uD835\uDDE7 << If an enemy is in the room, combat will start automatically. \n    It is turn-based.");
        println("        - Use the command: [ attack ]\n");
        println("3. >> \uD835\uDDD6\uD835\uDDDB\uD835\uDDD4\uD835\uDDE5\uD835\uDDD4\uD835\uDDD6\uD835\uDDE7\uD835\uDDD8\uD835\uDDE5 << Manage your character's state and gear.");
        println("       - Use the commands: [ status ] and [ inventory ]\n\n");
        println("To see all available commands at any time, type [ help ].");
        println("To end your journey prematurely, type [ exit ].\n");
        println("Prepare yourself...");
        println("═══════════════════════════════════════════════════════════════════════════════════════");
        println("\n Type 'help' for available commands or 'exit' to quit the game.");
    }

    // Terminal Help message
    private void tutorialHelpMessage() {
        println("\nAVAILABLE COMMANDS:");
        println("- colour [colour]  : Change text color (default, red, green, blue, yellow, cyan, magenta, white)");
        println("- clear            : Clear the terminal screen");
        println("- help             : Display this help message");
        println("- exit             : Quit the game\n");
        println("- start            : Start the game");

    }

    private void graveyardHelpMessage() {
        println("\nAVAILABLE COMMANDS:");
        println("- colour [colour]  : Change text color (default, red, green, blue, yellow, cyan, magenta, white)");
        println("- clear            : Clear the terminal screen");
        println("- help             : Display this help message");
        println("- exit             : Quit the game\n");
        println("- name             : Set your player name.");
        println("- class            : Set your player class (Knight, Mage, or Reaper).");
        println("- proceed          : Proceed onwards.");

    }

    private void churchHelpMessage() {
        println("\nAVAILABLE COMMANDS:");
        println("- colour [colour]  : Change text color (default, red, green, blue, yellow, cyan, magenta, white)");
        println("- clear            : Clear the terminal screen");
        println("- help             : Display this help message");
        println("- start            : Start the game");
        println("- exit             : Quit the game\n");
        println("- choose           : Choose between the options given.");
        println("- inventory        : View the player inventory.");

    }


    // Graveyard Methods

    private void graveyardTitleMessage() {
        println("     ▄████  ██▀███   ▄▄▄    ██▒   █▓▓█████▓██   ██▓ ▄▄▄       ██▀███  ▓█████▄ ");
        println("     ██▒ ▀█▒▓██ ▒ ██▒▒████▄ ▓██░   █▒▓█   ▀ ▒██  ██▒▒████▄    ▓██ ▒ ██▒▒██▀ ██▌");
        println("     ▒██░▄▄▄░▓██ ░▄█ ▒▒██  ▀█▄▓██  █▒░▒███    ▒██ ██░▒██  ▀█▄  ▓██ ░▄█ ▒░██   █▌");
        println("     ░▓█  ██▓▒██▀▀█▄  ░██▄▄▄▄██▒██ █░░▒▓█  ▄  ░ ▐██▓░░██▄▄▄▄██ ▒██▀▀█▄  ░▓█▄   ▌");
        println("     ░▒▓███▀▒░██▓ ▒██▒ ▓█   ▓██▒▒▀█░  ░▒████▒ ░ ██▒▓░ ▓█   ▓██▒░██▓ ▒██▒░▒████▓ ");
        println("     ░▒   ▒ ░ ▒▓ ░▒▓░ ▒▒   ▓▒█░░ ▐░  ░░ ▒░ ░  ██▒▒▒  ▒▒   ▓▒█░░ ▒▓ ░▒▓░ ▒▒▓  ▒ ");
        println("     ░   ░   ░▒ ░ ▒░  ▒   ▒▒ ░░ ░░   ░ ░  ░▓██ ░▒░   ▒   ▒▒ ░  ░▒ ░ ▒░ ░ ▒  ▒ ");
        println("     ░ ░   ░   ░░   ░   ░   ▒     ░░     ░   ▒ ▒ ░░    ░   ▒     ░░   ░  ░ ░  ░ ");
        println("           ░    ░           ░  ░   ░     ░  ░░ ░           ░  ░   ░        ░    ");
        println("                                  ░          ░ ░                         ░      \n");
        println("⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘");
    }

    private void graveyardIntroMessage() {
        println("\n You find yourself at the entrance of an ancient graveyard shrouded in mist.\n");
        println("Weathered tombstones and crumbling mausoleums stretch before you, their shadows");
        println("dancing in the pale moonlight that filters through the twisted branches above.");
        println("A spectral voice whispers through the fog:\n \"Traveler, before you venture forth, you must remember who you are...\"\n\n");
        println("THE PATHS YOU COULD HAVE BEEN:\n");
        println("1. KNIGHT - A stalwart warrior with enhanced strength and durability");
        println("   ♦ High Defense  ♦ Strong Attack  ♦ No Magic\n");
        println("2. MAGE - A master of arcane arts with powerful spells but fragile defenses");
        println("   ♦ Low Health  ♦ Low Weapon Attack  ♦ Powerful Magic\n");
        println("3. REAPER - An embodiment of living death, fast scythe wielder.");
        println("   ♦ Medium Health  ♦ Quick Attacks  ♦ Some Magic\n");
        println("⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘\n");
        println("What is your name, brave soul?\n");
        println("  - Use the command: [ name <your name> ]");
    }

    private void graveyardMovementMessage() {
        println("*YOU BEGIN TO MOVE TO THE NEXT ROOM.*");
    }

    private void churchTitleMessage() {

        println("      ▄████████     ▄█    █▄    ███    █▄     ▄████████  ▄████████    ▄█    █▄     ");
        println("      ███    ██    ███    ███   ███    ███   ███    ███ ███    ███   ███    ███    ");
        println("      ███    █▀    ███    ███   ███    ███   ███    ███ ███    █▀    ███    ███   ");
        println("      ███         ▄███▄▄▄▄███▄▄ ███    ███  ▄███▄▄▄▄██▀ ███         ▄███▄▄▄▄███▄▄ ");
        println("      ███        ▀▀███▀▀▀▀███▀  ███    ███ ▀▀███▀▀▀▀▀   ███        ▀▀███▀▀▀▀███▀  ");
        println("      ███    █▄    ███    ███   ███    ███ ▀██████████▄ ███    █▄    ███    ███   ");
        println("      ███    ███   ███    ███   ███    ███   ███    ███ ███    ███   ███    ███   ");
        println("      ████████▀    ███    █▀    ████████▀    ███    ███ ████████▀    ███    █▀    ");
        println("                                             ▀▀█    ██▀                         \n");
        println("❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█═█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚\n");
    }

    private void churchIntroMessage(String CLASS) {
        println("\n You push open the heavy wooden doors and step into the abandoned church. Dust motes dance");
        println("in the colored light streaming through stained glass windows, casting ethereal patterns");
        println("across the crumbling pews and debris-strewn floor.\n");
        println("                                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⡀⠀⠀⠀");
        println("                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⡀⠀⡀⠀⠂⡀⢀⢰⠀⢂⠀⠀⠀⠀⠀⠀⠀⠀");
        println("                         ⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣐⣬⣄⣷⡀⢸⡃⡘⡸⡄⢸⠀⠀⡇⠀⢠⠀⠀⠀");
        println("                         ⠀⠀⠀⠀⠀⣠⡴⠚⢉⢍⢂⣼⣴⣿⣿⣿⣷⣷⣷⣣⣏⣆⣼⠀⠀⠄⠀⠀⠀");
        println("                         ⠀⠀⠀⢠⡞⠋⠀⡑⣮⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣷⣼⣆⣌⡠⢁⡤");
        println("                         ⠀⠀⣰⠋⠀⣀⣺⣾⣿⣿⣿⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠁");
        println("                         ⠀⡼⠁⢀⣿⣿⣿⡿⣿⡛⣿⣿⣿⡷⢸⣿⠀⠀⠀⠀⣹⣿⣿⣿⣿⣟⠣⠀⠀");
        println("                         ⡰⠁⢀⣼⣿⠟⢿⡇⠹⣿⣿⣿⠟⠀⢠⡿⠀⠀⣠⣾⡿⣿⡥⠊⠁⠀⠀⠀⠀");
        println("                         ⠁⢠⣾⠟⠁⠀⠈⠳⢿⣦⣠⣤⣦⣼⠟⠁⣠⣾⣿⣿⣟⠍⠒⠀⠠⠀⠀⠀⠀");
        println("                         ⢠⡟⠁⢀⣀⣀⣀⣀⡀⠈⣉⣉⣡⣤⣶⣿⡿⡿⡿⡻⠥⠑⡀⠀⠀⠀⠀⠀⠀");
        println("                         ⠏⡠⠚⠉⠋⢍⠋⢫⠋⠛⢹⢻⡟⠻⣟⢏⠌⢊⡌⠌⠄⠀⠀⠀⠀⠀⠀⠀⠀");
        println("                         ⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠘⠂⠘⠂⠿⠈⠀⠀⠀⠘⠀⠀⠀⠀⠀⠀\n\n⠀");

        println("At the altar, a spectre materializes - an overseer of sort.");
        println("Its hollow voice echoes through the sacred halls:\n");
        println("\"Ah, a " + CLASS + " has remembered their path.");
        println("You will need more than courage to survive what lies beyond these walls.\"\n");
        println("【﻿ＴＨＥ　ＥＹＥ　ＧＬＯＷＳ】\n");

        switch (CLASS) {
            case "knight":
                println("                              ✥  ARMAMENTS OF VIRTUE  ✥                                  \n");
                println("                                                                             .-.           ");
                println("                                                                            {{#}}          ");
                println("                                                            {}               8@8           ");
                println("        |\\                                                .::::.             888          ");
                println("        | \\        /|                                 @\\\\/W\\/\\/W\\//@         8@8     ");
                println("        |  \\____  / |                                  \\\\/^\\/\\/^\\//     _    )8(    _");
                println("       /|__/AMMA\\/  |                                   \\_O_{}_O_/     (@)__/8@8\\__(@)  ");
                println("     /AMMMMMMMMMMM\\_|                              ____________________ `~\"-=):(=-\"~`   ");
                println(" ___/AMMMMMMMMMMMMMMA                             |<><><>  |  |  <><><>|     |.|           ");
                println("\\   |MVKMMM/ .\\MMMMM\\                             |<>      |  |      <>|     |S|        ");
                println(" \\__/MMMMMM\\  /MMMMMM---                          |<>      |  |      <>|     |'|         ");
                println("  |MMMMMMMMMMMMMMMMMM|  /                         |<>   .--------.   <>|     |.|           ");
                println("  |MMMM/. \\MM.--MMMMMM\\/                          |     |   ()   |     |     |P|         ");
                println("  /\\MMM\\  /MM\\  |MMMMMM   ___                     |_____| (O\\/O) |_____|     |'|       ");
                println(" /  |MMMMMMMMM\\ |MMMMMM--/   \\-.                  |     \\   /\\   /     |     |.|       ");
                println("/___/MMMMMMMMMM\\|M.--M/___/_|   \\                 |------\\  \\/  /------|     |U|       ");
                println("     \\VMM/\\MMMMMMM\\  |      /\\ \\/                 |       '.__.'       |     |'|      ");
                println("      \\V/  \\MMMMMMM\\ |     /_  /                  |        |  |        |     |.|        ");
                println("        |  /MMMV'   \\|    |/ _/                   :        |  |        :     |N|          ");
                println("        | /              _/  /                     \\       |  |       /      |'|          ");
                println("        |/              /| \\'                       \\<>    |  |    <>/       |.|         ");
                println("                       /_  /                         \\<>   |  |   <>/        |K|          ");
                println("                       /  /                           `\\<> |  | <>/'         |'|          ");
                println("                                                        `-.|__|.-`           \\ /          ");
                println("                                                                              ^          \n");


                println("            1. MACE                                      2. SWORD & SHIELD                 ");
                println("A virulent ball of swinging death.               The bastion of any successful knight.     ");
                println(" ♦ Offensive Focus  ♦ High Damage                   ♦ Well-rounded  ♦ Good Damage      \n\n");
                break;

            case "mage":
                println("                              ✥  CONDUITS OF ARCANE POWER  ✥                             \n");
                println("⠀                                                        ⠀⠀⠀⠀⢤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣄⠀         ");
                println("      _.--._  _.--._                                    ⠀⠀⠀⠀⠀⠀⠈⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⠀⠀⠀⠀      ");
                println(",-=.-\":;:;:;\\':;:;:;\"-._                             ⠀⣤⡀⠀⠀⠀⠀⠀⠀⠀⣸⣏⣽⣷⣶⣶⣦⣤⣄⡀⣠⡶⠶⢶⣶⠿⠀⠀⠀⠀ ");
                println("\\\\\\:;:;:;:;:;\\:;:;:;:;:;\\                          ⠀⠈⠛⢦⡀⠀⠀⠀⣠⣶⠟⢋⣁⣤⣤⣤⣤⣄⣉⠛⢿⣦⡀⠈⢿⡄⠀⠀⠀⠀ ");
                println(" \\\\\\:;:;:;:;:;\\:;:;:;:;:;\\                         ⠀⠀⣠⡞⠁⠀⢠⣾⠋⢀⣴⡿⠛⢁⠙⢿⣿⣿⡻⢷⣤⡈⠻⣦⡾⠃⠀⠀⠀⠀ ");
                println("  \\\\\\:;:;:;:;:;\\:;:;:;:;:;\\                        ⠀⠀⠛⠻⠶⣤⡿⢁⡄⠘⠋⠀⣴⣿⣿⣷⣿⣿⣿⣦⠉⠳⢄⠹⣧⠀⠀⠀⠀⠀ ");
                println("   \\\\\\:;:;:;:;:;\\:;::;:;:;:\\                       ⠀⠀⠀⠀⠀⣿⠁⣼⣿⣶⣤⡄⠀⢹⣿⡿⠿⠿⠛⠛⠃⢀⣀⡀⢹⡇⠀⠀⢀⠀ ");
                println("    \\\\\\;:;::;:;:;\\:;:;:;::;:\\                      ⠀⣤⡀⠀⢸⣿⠀⣿⣿⣿⠿⠿⠀⠸⣿⣷⣤⡀⠐⣿⣿⣿⣿⡇⢸⣿⠀⣰⡟⠀ ");
                println("     \\\\\\;;:;:_:--:\\:_:--:_;:;\\                     ⠀⠈⠛⠿⠛⣿⠀⠀⠀⢀⣀⣤⣤⣄⡈⠙⢿⣿⣦⣌⠻⣿⣿⠇⢸⣿⣾⠏⠀⠀ ");
                println("      \\\\_.-\"      :      \"-._\\                      ⠀⠀⠀⢀⣹⣧⠘⢦⣄⠉⠻⣿⣿⣿⡷⣦⣭⣿⣿⣷⣬⠋⢠⡿⠉⠁⠀⠀⠀ ");
                println("       \\`_..--\"\"---.;.--\"\"--..=>                   ⠀⢶⡟⠛⠉⠹⣷⡀⠉⢠⣤⣬⣿⣿⣿⣎⠙⢿⣿⠿⠃⣠⡿⠁⠀⠀⠀⠀⠀  ");
                println("                    \"                             ⠀⣀⣈⣷⡀⠀⠀⠈⠻⣦⣄⡙⠛⠶⠶⠦⠤⠄⠀⣁⣤⡾⠿⣦⡀⠀⠀⠀⠀⠀      ");
                println("                                                  ⠀⠋⠉⠉⠁⠀⠀⠀⠀⠀⢹⡛⠷⠶⣶⣶⡶⠶⢿⣿⣅⡀⠀⣨⠟⠂⠀⠀⠀⠀       ");
                println("                                                     ⠀⠀⠀⠀⠐⣶⡶⠿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⣧⡀⠀⠀⠀⠀⠀       ");
                println("                                                     ⠀⠀⠀⠀⠀⠈⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠀⠀⠀⠀⠀     \n");

                println("           1. GRIMOIRE                                     2. UNSTABLE ORB                 ");
                println("An ancient tome of forbidden knowledge.          A chaotic orb of pure annihilation.       ");
                println("  ♦ Mana Intensive  ♦ High Damage                ♦ Mana Efficient  ♦ Quick Casting     \n\n");
                break;

            case "reaper":
                println("                              ✥  INSTRUMENTS OF DEATH  ✥                                 \n");
                println("                              ⠀⠀⠀                  ⠀⠀     ⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀         ");
                println("                              ⠀⠀⠀⠀                      ⣠⣴⡿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣷⣤⡀⠀         ");
                println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀               ⠀⢀⣾⡟⡍⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⡙⣿⡄            ");
                println("⠀⠀⠀⠀⠀⣀⣠⣤⣴⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⡀⠀⠀⠀ ⠀               ⠀⣸⣿⠃⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠇⣹⣿            ");
                println("⠀⠀⣿⣷⠀⣿⣿⣿⣿⣿⣿⣿⡿⠟⠛⠛⠉⠉⠉⠉⠉⠉⠙⠛⠻⢿⣷⡀⠀⠀⠀               ⠀⣿⣿⡆⢚⢄⣀⣠⠤⠒⠈⠁⠀⠀⠈⠉⠐⠢⢄⡀⣀⢞⠀⣾⣿            ");
                println("⠀⠀⢿⣿⠀⢹⣿⣿⡿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠄⠀⠀               ⠀⠸⣿⣿⣅⠄⠙⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⠑⣄⣽⣿⡟            ");
                println("⠀⠀⠸⣿⡇⠈⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠘⢿⣿⣟⡾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠱⣾⣿⣿⠏⠀            ");
                println("⠀⠀⠀⢻⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⣸⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡉⢻⠀⠀            ");
                println("⠀⠀⠀⠀⢻⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⢿⠀⢃⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠁⢸⠀⠀            ");
                println("⠀⠀⣀⣠⣴⡿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⢸⢰⡿⢘⣦⣤⣀⠑⢦⡀⠀⣠⠖⣁⣤⣴⡊⢸⡇⡼⠀⠀            ");
                println("⠀⠈⠛⠛⠉⠀⠈⠛⢿⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⠾⡅⣿⣿⣿⣿⣿⠌⠁⠀⠁⢺⣿⣿⣿⣿⠆⣇⠃⠀⠀            ");
                println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⣿⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⢀⠂⠘⢿⣿⣿⡿⠀⣰⣦⠀⠸⣿⣿⡿⠋⠈⢀⠀⠀⠀            ");
                println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⣿⣷⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⢠⠀⠀⠀⠀⠀⠀⢠⣿⢻⣆⠀⠀⠀⠀⠀⠀⣸⠀⠀⠀            ");
                println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⠈⠓⠶⣶⣦⠤⠀⠘⠋⠘⠋⠀⠠⣴⣶⡶⠞⠃⠀⠀⠀            ");
                println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⠀⠀⠀⣿⢹⣷⠦⢀⠤⡤⡆⡤⣶⣿⢸⠇⠀⠀⠀⠀⠀            ");
                println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀               ⠀⠀⠀⠀⠀⠀⢰⡀⠘⢯⣳⢶⠦⣧⢷⢗⣫⠇⠀⡸⠀⠀⠀⠀⠀            ");
                println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠁⠀⠀⠀⠀  ⠀               ⠀⠀⠀⠀⠀⠀⠀⠑⢤⡀⠈⠋⠛⠛⠋⠉⢀⡠⠒⠁⠀⠀⠀⠀⠀            ");
                println("                              ⠀⠀⠀⠀⠀⠀⠀⠀⠀                      ⠀⠹⢦⠀⢀⣀⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀         ");
                println("                              ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                  ⠀    ⠈⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀       \n");

                println("               1. SCYTHE                                     2. DEATH MAGIC                ");
                println("The cold, clean edge all mortals must meet.        A dark conduit for siphoning souls.     ");
                println("     ♦ Non-Magic Based  ♦ High Damage                ♦ Magic Based  ♦ Strong Spells    \n\n");
                break;
        }

        println("\"Choose wisely, " + NAME + ". This decision will shape your journey through the darkness.\"\n");
        println("❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█═█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚❚█══█❚\n\n");
        println("Which weapon calls to you?");
        println("  - Use the command: [ choose <1 or 2> ]");
    }


    // ASCII Art method
    private void displayCharacter(String CLASS) {

        // KNIGHTS
        String knight1 = """
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
                """;
        String knight2 = """
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

        // MAGES
        String mage1 = """
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
                """;

        String mage2 = """
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

        String reaper1 = """
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
                """;

        String reaper2 = """
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
        //as this is an animation, a new thread is created to run the animation without blocking the main thread.

        Thread idleAnimationThread;

        switch (CLASS) {
            case "knight":
                characterArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
                idleAnimationThread = idleAnimation(knight1, knight2, 1500, 750);
                idleAnimationThread.start();
                break;
            case "mage":
                characterArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
                idleAnimationThread = idleAnimation(mage1, mage2, 1500, 750);
                idleAnimationThread.start();
                break;
            case "reaper":
                characterArea.setFont(new Font("Monospaced", Font.PLAIN, 9));
                idleAnimationThread = idleAnimation(reaper1, reaper2, 2500, 150);
                idleAnimationThread.start();
        }
    }

    private void displayStats() {
        println(player.displayStatus(), statsArea);

    }

    // COMMAND METHODS ------------------------------------------------------------------------------------------------
    // Method to process different commands that are inputted by the user.e
    private void processCommand(String input) {
        // Display inputted command in display area.
        println("\n > " + input);
        updateCommandHistory(input);

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
            switch (currentRoom.getName()) {
                case "tutorial":
                    tutorialHelpMessage();
                case "graveyard":
                    graveyardHelpMessage();
                case "church":
                    churchHelpMessage();
            }
        } else if (input.equals("clear")) {
            clearScreen();
        } else {
            println("Unknown command. Type 'help' for available commands.");
        }

        switch (currentRoom.getName()) {
            case "tutorial":
                if (input.equals("start")) {
                    currentRoom = new Room("Graveyard", "A dark and eerie graveyard.");

                    display.setText("");
                    graveyardTitleMessage();
                    graveyardIntroMessage();
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

                    graveyardMovementMessage();
                    currentRoom = new Room("Church", "An ancient church, emanating an unusual presence");
                    display.setText("");
                    churchTitleMessage();
                    churchIntroMessage(CLASS);

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
                    println("\n═══ INVENTORY ═══");
                    for (Weapon weapon : player.getInventory()) {
                        String equipped = weapon.equals(player.getEquippedWeapon()) ? " [EQUIPPED]" : "";
                        println("• " + weapon.getName() + equipped);
                        println("  " + weapon.getDescription());
                        println("  Damage: " + weapon.getDamage() + " (" + weapon.getType() + ")");
                    }
                } else {
                    println("Unknown command. Please Try Again.");
                }

                break;

        }

    }


    // Clear Method
    private void clearScreen() {
        display.setText("");

        switch (currentRoom.getName()) {
            case "tutorial":
                tutorialTitleMessage();
                tutorialWelcomeMessage();
                break;
            case "graveyard":
                graveyardTitleMessage();
                graveyardIntroMessage();
                break;
            case "church":
                churchTitleMessage();
                churchIntroMessage(CLASS);
                break;
        }
    }


    // Custom println method to display text in the UI display area.
    public void println(String text) {
        display.append(" " + text + "\n");
        display.setCaretPosition(display.getDocument().getLength());
    }

    public void println(String text, double waitTime) {
        display.append(" " + text + "\n");
        display.setCaretPosition(display.getDocument().getLength());
        wait(waitTime);
    }

    public void println(String text, JTextArea area) {
        area.append(" " + text + "\n");
        area.setCaretPosition(area.getDocument().getLength());
    }

    public void println(String text, JTextArea area, double waitTime) {
        area.append(" " + text + "\n");
        area.setCaretPosition(area.getDocument().getLength());
        wait(waitTime);
    }


    // UI Methods -----------------------------------------------------------------------------------------------------
    // Method to change the text color
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

    // MISCELLANEOUS METHODS ------------------------------------------------------------------------------------------

    // Wait method
    private void wait(double seconds) {
        CompletableFuture.delayedExecutor((long) (seconds * 1000), TimeUnit.MILLISECONDS).execute(() -> {
        });
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Method to create a new thread for the idle animation of the character.
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

    // INITIALISATION METHODS -----------------------------------------------------------------------------------------

    private void initialiseUI() {
        JFrame frame = new JFrame("Terminality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Left sidebar
        sidebarArea = new JTextArea();
        sidebarArea.setEditable(false);
        sidebarArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        sidebarArea.setBackground(BLACK);
        sidebarArea.setForeground(Color.LIGHT_GRAY);
        sidebarArea.setMargin(new Insets(10, 0, 10, 10));

        // Initialize command history
        commandHistory = new ArrayList<>();
        commandCount = 0;

        // Add initial header to sidebar
        sidebarArea.setText("   【\uFEFFＣＯＭＭＡＮＤ　ＨＩＳＴＯＲＹ】\n");
        sidebarArea.append(" ═══════════════════════════════════════\n\n");

        JScrollPane leftScroll = new JScrollPane(sidebarArea);
        leftScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        leftScroll.setPreferredSize(new Dimension(250, frame.getHeight()));

        // Center display
        display = new JTextArea();
        display.setEditable(false);
        display.setFont(new Font("Monospaced", Font.PLAIN, 14));
        display.setBackground(BLACK);
        display.setForeground(DEFAULT);
        display.setMargin(new Insets(20, 20, 20, 20));
        JScrollPane centerScroll = new JScrollPane(display);
        centerScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // Right panel - split into two sections
        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 0, 10)); // 2 rows, 1 column, 10px gap
        rightPanel.setBackground(BLACK);

        // Character area (top half)
        characterArea = new JTextArea();
        characterArea.setEditable(false);
        characterArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        characterArea.setBackground(BLACK);
        characterArea.setForeground(Color.LIGHT_GRAY);
        characterArea.setMargin(new Insets(20, 50, 20, 50));
        JScrollPane characterScroll = new JScrollPane(characterArea);
        characterScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Stats area (bottom half)
        statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        statsArea.setBackground(BLACK);
        statsArea.setForeground(WHITE);
        statsArea.setMargin(new Insets(0, 10, 0, 10));
        JScrollPane statsScroll = new JScrollPane(statsArea);
        statsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        statsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // Add both areas to right panel
        rightPanel.add(characterScroll);
        rightPanel.add(statsScroll);
        rightPanel.setPreferredSize(new Dimension(480, frame.getHeight()));

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(DEFAULT2);

        JLabel promptLabel = new JLabel(" > ");
        promptLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        promptLabel.setForeground(WHITE);
        inputPanel.add(promptLabel, BorderLayout.WEST);

        terminal = new JTextField();
        terminal.setFont(new Font("Monospaced", Font.PLAIN, 14));
        terminal.setBackground(DEFAULT2);
        terminal.setForeground(WHITE);
        terminal.setCaretColor(DEFAULT);
        terminal.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));
        inputPanel.add(terminal, BorderLayout.CENTER);

        // Assemble main panel
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

    private void initialiseWorld() {
        // Set the starting room
        currentRoom = new Room("Tutorial", "Area when the user opens the game for the first time.");
    }

    // NEED CATEGORY -------------------------------------------------------------------------------------------------

    private void handleWeaponChoice(String choice) {
        Weapon chosenWeapon = null;

        switch (CLASS) {
            case "knight":
                if (choice.equals("1")) {
                    chosenWeapon = new Weapon("Mace", 15, "A virulent ball of swinging death.", "physical");
                    println("\nYou grasp the MACE, feeling its weight and power.");
                    println("The weapon pulses with righteous fury as you swing it experimentally.");
                } else if (choice.equals("2")) {
                    chosenWeapon = new Weapon("Sword & Shield", 12, "The bastion of any successful knight.", "physical");
                    println("\nYou take up the SWORD & SHIELD, feeling their perfect balance.");
                    println("The blade gleams with deadly purpose while the shield promises protection.");
                }
                break;

            case "mage":
                if (choice.equals("1")) {
                    chosenWeapon = new Weapon("Grimoire", 20, "An ancient tome of forbidden knowledge.", "magic");
                    println("\nYou open the GRIMOIRE, its pages crackling with arcane energy.");
                    println("Forbidden knowledge floods your mind as the tome accepts you as its master.");
                } else if (choice.equals("2")) {
                    chosenWeapon = new Weapon("Unstable Orb", 18, "A chaotic orb of pure annihilation.", "magic");
                    println("\nYou grasp the UNSTABLE ORB, feeling its chaotic energy surge through you.");
                    println("The orb pulses erratically, barely contained destruction at your fingertips.");
                }
                break;

            case "reaper":
                if (choice.equals("1")) {
                    chosenWeapon = new Weapon("Scythe", 25, "The cold, clean edge all mortals must meet.", "physical");
                    println("\nYou grip the SCYTHE, its blade singing a song of endings.");
                    println("The weapon feels like an extension of death itself in your hands.");
                } else if (choice.equals("2")) {
                    chosenWeapon = new Weapon("Death Magic", 17, "A dark conduit for siphoning souls.", "magic");
                    println("\nYou channel DEATH MAGIC, feeling the cold touch of the void.");
                    println("Dark energy swirls around you, hungry for the essence of life.");
                }
                break;
        }

        if (chosenWeapon != null) {
            player.addWeapon(chosenWeapon);
            displayStats(); // Update stats display

            println("\n" + chosenWeapon.getName() + " has been added to your inventory and equipped!");
            println("\nThe spectral overseer nods approvingly.");
            println("\"Your choice is made. May it serve you well in the trials ahead.\"");
            println("\nThe overseer fades away, and you notice a door at the back of the church beginning to glow...");
            println("\nType [ move north ] to proceed to the next area.");

        }
    }

    private void updateCommandHistory(String command) {
        commandHistory.add(command);
        commandCount++;

        // Clear and rebuild the display
        sidebarArea.setText("   【\uFEFFＣＯＭＭＡＮＤ　ＨＩＳＴＯＲＹ】\n");
        sidebarArea.append(" ═══════════════════════════════════════\n\n");

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
                sidebarArea.append("  ╔═ CURRENT\n");
                sidebarArea.append("  ║\n");
                sidebarArea.append(str);
            } else if (i == 0) {
                // First command (root)
                sidebarArea.append("  ║\n");
                sidebarArea.append(str);
                sidebarArea.append("  ║\n");
                sidebarArea.append("  ╚═ ORIGIN\n");
            } else {
                // Middle commands
                sidebarArea.append("  ║\n");

                // Add branch indicators
                if (i < commandHistory.size() - 1) {
                    String nextCmd = commandHistory.get(i + 1);
                    String prevCmd = commandHistory.get(i - 1);

                    // Branch merge/split visualization
                    if (isCommandTypeChange(cmd, nextCmd)) {
                        sidebarArea.append("  ╠═╗\n");
                        sidebarArea.append("  ║ ╚─" + node + " [" + String.format("%03d", cmdNumber) + "] " + cmd + "\n");
                    } else if (isCommandTypeChange(prevCmd, cmd)) {
                        sidebarArea.append("  ╠═╝\n");
                        sidebarArea.append(str);
                    } else {
                        sidebarArea.append(str);
                    }
                } else {
                    sidebarArea.append(str);
                }
            }
        }

        // Add summary at bottom
        sidebarArea.append("\n  ───────────────\n");
        sidebarArea.append("  Total: " + commandCount + " commands\n");

        // Auto-scroll to top
        sidebarArea.setCaretPosition(0);
    }

    // Helper method to detect command type changes
    private boolean isCommandTypeChange(String cmd1, String cmd2) {
        return !getCommandType(cmd1).equals(getCommandType(cmd2));
    }

    private String getCommandType(String cmd) {
        if (cmd.startsWith("move")) return "movement";
        if (cmd.startsWith("attack")) return "combat";
        if (cmd.startsWith("name") || cmd.startsWith("class") || cmd.startsWith("choose")) return "character";
        if (cmd.equals("help") || cmd.equals("clear") || cmd.startsWith("colour")) return "utility";
        return "other";
    }


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