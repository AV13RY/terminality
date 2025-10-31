package game;

import characters.Boss;
import characters.Enemy;
import characters.Player;
import items.*;
import world.MapBuilder;
import world.Room;

import javax.swing.*;
import java.awt.*;
import java.util.*;
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
    private JTextArea commandLog;

    // Log Declarations
    private List<String> commandHistory;
    private int commandCount;

    // Game state Declarations
    private MapBuilder mapBuilder;
    private Room currentRoom;
    private Player player;
    private String NAME = "";
    private String CLASS = "";
    private Enemy currentEnemy;
    private boolean inCombat;
    private Random random;


    // Color Declarations
    private final Color RED = Color.RED;
    private final Color GREEN = Color.GREEN;
    private final Color BLUE = Color.BLUE;
    private final Color YELLOW = Color.YELLOW;
    private final Color CYAN = Color.CYAN;
    private final Color MAGENTA = Color.MAGENTA;
    private final Color WHITE = Color.WHITE;
    private final Color BLACK = Color.BLACK;
    private final Color GRAY = Color.LIGHT_GRAY;
    private final Color DEFAULT = new Color(0xD8125B);
    private final Color DEFAULT2 = new Color(0x424549);
    private final Color DEFAULT3 = new Color(0x4b0019);

    // CORE METHODS ---------------------------------------------------------------------------------------------------
    // Game Constructor
    public Game() {
        initialiseUI();
        initialiseWorld();
        tutorialTitleMessage();
        tutorialWelcomeMessage();

        // Initialise combat variables
        this.inCombat = false;
        this.currentEnemy = null;
        this.random = new Random();

        //        testing("reaper");
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
        statsArea.setText("");
        println(player.displayStatus(), statsArea);

    }

    // COMMAND METHODS ------------------------------------------------------------------------------------------------
    // Method to process different commands that are inputted by the user.e
    private void processCommand(String input) {
        // Display inputted command in display area.
        println("\n > " + input);
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
                    tutorialHelpMessage();
                    break;
                case "graveyard":
                    graveyardHelpMessage();
                    break;
                case "church":
                    churchHelpMessage();
                    break;
            }
        } else if (input.equals("clear")) {
            clearScreen();
        } else if (input.equals("map")) {
            displayMap();
        }

        switch (currentRoom.getName().toLowerCase()) {
            case "tutorial":
                if (input.equals("start")) {
                    currentRoom = new Room("Graveyard", "A dark and eerie graveyard.", 0, 0, Room.RoomType.START);

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
                    currentRoom = new Room("Church", "An ancient church, emanating an unusual presence", 0, 0, Room.RoomType.START);
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
                    displayInventory();
                } else if (input.equals("proceed")) {
                    startAdventure();
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

    private void initialiseWorld() {
        // Set the starting room
        currentRoom = new Room("Tutorial", "Area when the user opens the game for the first time.", 0, 0, Room.RoomType.START);
    }

    // NEED CATEGORY -------------------------------------------------------------------------------------------------

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

    private void displayMap() {
        println("\n═══════════════ MAP ═══════════════\n");

        // Find map bounds
        int minX = 0, maxX = 0, minY = 0, maxY = 0;
        for (Room room : mapBuilder.getAllRooms().values()) {
            minX = Math.min(minX, room.getX());
            maxX = Math.max(maxX, room.getX());
            minY = Math.min(minY, room.getY());
            maxY = Math.max(maxY, room.getY());
        }

        // Display map from top to bottom
        for (int y = maxY; y >= minY; y--) {
            StringBuilder mapRow = new StringBuilder();
            StringBuilder connectionRow = new StringBuilder();

            for (int x = minX; x <= maxX; x++) {
                String coordKey = x + "," + y;
                Room room = mapBuilder.getAllRooms().get(coordKey);

                if (room != null) {
                    // Determine room symbol
                    String symbol;
                    if (room == currentRoom) {
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

            println("  " + mapRow.toString());
            if (y > minY) {
                println("  " + connectionRow.toString());
            }
        }

        println("\nLegend: [◉]=You [·]=Visited [?]=Unexplored [T]=Treasure [B]=Boss");
        println("═══════════════════════════════════");
    }

    private void startAdventure() {
        println("\nGenerating dungeon layout...");
        mapBuilder = new MapBuilder();
        currentRoom = mapBuilder.generateMap();
        currentRoom.setVisited(true);

        println("\nYou step through the church doors into the dungeon beyond...");
        displayCurrentRoom();
        displayMap();
    }

    private void displayCurrentRoom() {
        println("\n═══ " + currentRoom.getName().toUpperCase() + " ═══");
        println(currentRoom.getDescription());

        // Show exits
        println("\nExits: " + String.join(", ", currentRoom.getExits().keySet()));

        if (currentRoom.hasEnemies()) {
            println("\nEnemies present:");
            for (Enemy enemy : currentRoom.getEnemies()) {
                println("- " + enemy.getName() + " (HP: " + enemy.getCurrentHealth() + ")");
            }
        }

        // Show accessible chests
        if (currentRoom.hasAccessibleChests()) {
            println("\nChests available:");
            for (Chest chest : currentRoom.getChests()) {
                if (!chest.isOpened()) {
                    println("- " + chest.getRarity() + " chest");
                }
            }
        }
    }

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
            displayCurrentRoom();

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
            displayGameHelp();
            return;
        } else if (input.equals("map")) {
            displayMap();
            return;
        } else if (input.equals("status")) {
            displayStats();
            return;
        } else if (input.equals("inventory")) {
            displayInventory();
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
            if (input.equals("attack")) {
                //                processAttack();
            } else if (input.equals("flee")) {
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
            displayCurrentRoom();
        } else if (input.startsWith("open ")) {
            String target = input.substring(5).trim();
            openChest(target);
        } else {
            println("Unknown command. Type 'help' for available commands.");
        }
    }


    // Update displayInventory to show all items
    private void displayInventory() {
        println("\n═══ INVENTORY ═══");

        // Equipped items
        println("\n⚔️ EQUIPPED:");
        if (player.getEquippedWeapon() != null) {
            println("  Weapon: " + player.getEquippedWeapon().getName());
        }

        for (Map.Entry<Armor.ArmorType, Armor> entry : player.getEquippedArmor().entrySet()) {
            println("  " + entry.getKey() + ": " + entry.getValue().getName() + " (+" + entry.getValue().getDefenseBonus() + " def)");
        }

        if (player.getEquippedAccessory() != null) {
            Accessory acc = player.getEquippedAccessory();
            println("  Accessory: " + acc.getName() + " (+" + acc.getBonusAmount() + " " + acc.getStatBonus().name() + ")");
        }

        // Inventory items
        println("\n🎒 BACKPACK:");
        if (player.getFullInventory().isEmpty()) {
            println("  Empty");
        } else {
            Map<Item.ItemType, List<Item>> itemsByType = new HashMap<>();
            for (Item item : player.getFullInventory()) {
                itemsByType.computeIfAbsent(item.getType(), k -> new ArrayList<>()).add(item);
            }

            for (Map.Entry<Item.ItemType, List<Item>> entry : itemsByType.entrySet()) {
                println("\n  " + entry.getKey() + ":");
                for (int i = 0; i < entry.getValue().size(); i++) {
                    Item item = entry.getValue().get(i);
                    println("    [" + (i + 1) + "] " + item.toString());
                }
            }
        }

        println("\n💰 Gold: " + player.getGold());
        println("═════════════════");
    }

    // Update displayStats to show total defense
    private void displayStats(String name) {
        statsArea.setText("");
        println("═══════════════════════════", statsArea);
        println("  " + name.toUpperCase() + " THE " + CLASS.toUpperCase(), statsArea);
        println("═══════════════════════════", statsArea);
        println(" HP: " + player.getCurrentHealth() + "/" + player.getMaxHealth(), statsArea);

        if (player.getMaxMana() > 0) {
            println(" MP: " + player.getMana() + "/" + player.getMaxMana(), statsArea);
        }

        println(" ATK: " + player.getAttack(), statsArea);
        println(" DEF: " + player.getTotalDefense(), statsArea);

        if (player.getEquippedWeapon() != null) {
            println("\n WEAPON: " + player.getEquippedWeapon().getName(), statsArea);
            println(" DMG: " + player.getEquippedWeapon().getAttackBonus(), statsArea);
        }

        println("\n GOLD: " + player.getGold(), statsArea);
    }

    // Update help to include item commands
    private void displayGameHelp() {
        println("\n═══ AVAILABLE COMMANDS ═══");
        println("EXPLORATION:");
        println("  move [direction] - Move to another room (north/south/east/west)");
        println("  look            - Examine the current room");
        println("  map             - Display the dungeon map");
        println("");
        println("COMBAT:");
        println("  attack          - Attack the current enemy");
        println("  flee            - Attempt to escape combat");
        println("");
        println("CHARACTER:");
        println("  status          - View your character stats");
        println("  inventory       - View your inventory");
        println("");
        println("ITEMS:");
        println("  use [#]         - Use a consumable item");
        println("  equip [#]       - Equip armor or accessory");
        println("  open [#]        - Open a chest");
        println("");
        println("SYSTEM:");
        println("  help            - Display this help message");
        println("  exit            - Quit the game");
        println("═════════════════════════");
    }


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

            //            if (player.getCurrentHealth() <= 0) {
            //                handlePlayerDeath();
            //            } else {
            //                displayCombatStatus();
            //            }
        }
    }

    private void openChest(String chestIdentifier) {
        if (!currentRoom.hasAccessibleChests()) {
            println("There are no chests to open here, or enemies are still present!");
            return;
        }

        List<Chest> unopenedChests = new ArrayList<>();
        for (Chest chest : currentRoom.getChests()) {
            if (!chest.isOpened()) {
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
            targetChest = unopenedChests.get(0);
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

    private void handleEnemyDefeat() {
        println("\n" + currentEnemy.getDeathMessage());
        println("Victory! You gained " + currentEnemy.getExperienceValue() + " experience!");

        // Gold drop
        int goldDrop = currentEnemy.getGoldDrop() + random.nextInt(10);
        player.addGold(goldDrop);
        println("You found " + goldDrop + " gold!");

        // Chance for item drop
        if (random.nextDouble() < 0.3) { // 30% chance for item drop
            Item.Rarity dropRarity = ItemGenerator.selectRarity();
            Item droppedItem = ItemGenerator.generateItem(dropRarity);
            player.addItem(droppedItem);
            println("The enemy dropped: " + droppedItem.toString() + "!");
        }

        // Remove enemy from room
        currentRoom.removeEnemy(currentEnemy);
        currentEnemy = null;
        inCombat = false;

        // Check for more enemies
        if (currentRoom.hasEnemies()) {
            println("\nAnother enemy approaches!");
            startCombat();
        } else {
            println("\nThe room is now clear of enemies.");

            if (currentRoom.hasAccessibleChests()) {
                println("You can now access the chests in this room!");
            }
        }
    }

    private void startCombat() {
        if (currentRoom.hasEnemies()) {
            currentEnemy = currentRoom.getEnemies().get(0);
            inCombat = true;

            println("\n⚔️ COMBAT INITIATED ⚔️");
            println("You encounter a " + currentEnemy.getName() + "!");

            // Special message for boss
            if (currentEnemy instanceof Boss) {
                println("\n⚠️ WARNING: BOSS ENCOUNTER! ⚠️");
                println("The " + currentEnemy.getName() + " towers before you, radiating dark energy!");
            }

            displayCombatStatus();
        }
    }

    private void displayCombatStatus() {
        println("\n═══ COMBAT STATUS ═══");
        println("Your HP: " + player.getCurrentHealth() + "/" + player.getMaxHealth());

        if (player.getMaxMana() > 0) {
            println("Your MP: " + player.getMana() + "/" + player.getMaxMana());
        }

        println(currentEnemy.getName() + " HP: " + currentEnemy.getCurrentHealth() + "/" + currentEnemy.getMaxHealth());

        // Show boss phase if applicable
        if (currentEnemy instanceof Boss) {
            Boss boss = (Boss) currentEnemy;
            println("Boss Phase: " + boss.getPhase());
        }

        println("═══════════════════");
    }

    private void checkForCombat() {
        if (currentRoom.hasEnemies() && !currentRoom.getEnemies().isEmpty()) {
            currentEnemy = currentRoom.getEnemies().get(0); // Get first enemy
            inCombat = true;
            println("\n⚔️ COMBAT INITIATED!");
            println("You encounter a " + currentEnemy.getName() + "!");
            println(currentEnemy.getEnemyType());
            displayCombatStatus();
        }
    }

    private void performCombat() {
        // Player attacks
        int playerDamage = calculatePlayerDamage();
        println("\nYou attack the " + currentEnemy.getName() + " for " + playerDamage + " damage!");
        currentEnemy.takeDamage(playerDamage);

        // Check if enemy is defeated
        if (!currentEnemy.isAlive()) {
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
        // Check if player is defeated
        if (!player.isAlive()) {
            println("\n💀 You have been defeated!");
            println("GAME OVER");
            System.exit(0);
        }

        // Show updated combat status
        displayCombatStatus();
    }

    private int calculatePlayerDamage() {
        int baseDamage = player.getAttack();
        if (player.getEquippedWeapon() != null) {
            baseDamage += player.getEquippedWeapon().getAttackBonus();
        }
        // Add some randomness (±20%)
        int variance = (int) (baseDamage * 0.2);
        return baseDamage + random.nextInt(variance * 2 + 1) - variance;
    }

    private void fleeCombat() {
        if (!inCombat) {
            println("You're not in combat!");
            return;
        }

        // 50% chance to flee
        if (random.nextBoolean()) {
            println("You successfully fled from combat!");
            inCombat = false;
            currentEnemy = null;
            // Move to a random adjacent room
            // ... implement flee movement logic
        } else {
            println("You failed to flee!");
            // Enemy gets a free attack
            int enemyDamage = currentEnemy.getAttack();
            println("The " + currentEnemy.getName() + " attacks you as you try to flee for " + enemyDamage + " damage!");
            player.takeDamage(enemyDamage);

            if (!player.isAlive()) {
                println("\n💀 You have been defeated while fleeing!");
                println("GAME OVER");
                System.exit(0);
            }
        }
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