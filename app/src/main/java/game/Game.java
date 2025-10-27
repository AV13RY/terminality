package game;

import characters.Player;
import world.Room;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Game {


    // GAME VARIABLES -------------------------------------------------------------------------------------------------
    // Section Declarations
    private JTextArea display;
    private JTextField terminal;
    private JTextArea characterArea;  // Add this field

    // Game state variables
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
        initializeUI();
        initializeWorld();
        tutorialTitleMessage();
        terminalWelcomeMessage();
    }

    // Main Method
    public static void main(String[] args) {
        Game game = new Game();
    }

    // TEXT METHODS ---------------------------------------------------------------------------------------------------
    // ASCII art title
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


    // Welcome message
    private void terminalWelcomeMessage() {
        println("                      A Java Dungeon Crawler by Jack McGillivray                       ");
        println("═══════════════════════════════════════════════════════════════════════════════════════");
        println("▖ ▖   ▄▖▖▖▄▖▄▖▄▖▄▖▄▖▖    ▖ ▖");
        println("▝▖▝▖  ▐ ▌▌▐ ▌▌▙▘▐ ▌▌▌   ▞ ▞ ");
        println("▞ ▞   ▐ ▙▌▐ ▙▌▌▌▟▖▛▌▙▖  ▝▖▝▖\n");


        println("Hello, World.. . . . . ............... .  . . . .  .", 0.1);
        println("Ah what am I saying, you probably already know this isn't just your average powershell.\n", 0.1);
        println("Anyway, welcome to Terminality, a text-based dungeon crawler.", 0.1);
        println("Your goal is to navigate the crypt, slay its inhabitants, and defeat the final boss.\n", 0.1);
        println("Your experience will consist of three main interactions:\n", 0.1);
        println("1. >> \uD835\uDDE0\uD835\uDDE2\uD835\uDDE9\uD835\uDDD8\uD835\uDDE0\uD835\uDDD8\uD835\uDDE1\uD835\uDDE7 << When required, you'll be prompted to move between rooms.");
        println("- Use the command: [ move <direction> ]\n", 0.1);
        println("2. >> \uD835\uDDD6\uD835\uDDE2\uD835\uDDE0\uD835\uDDD5\uD835\uDDD4\uD835\uDDE7 << If an enemy is in the room, combat will begin automatically. \n    It is turn-based.");
        println("        - Use the command: [ attack ]\n", 0.1);
        println("3. >> \uD835\uDDD6\uD835\uDDDB\uD835\uDDD4\uD835\uDDE5\uD835\uDDD4\uD835\uDDD6\uD835\uDDE7\uD835\uDDD8\uD835\uDDE5 << Manage your character's state and gear.");
        println("       - Use the commands: [ status ] and [ inventory ]\n\n", 0.1);
        println("To see all available commands at any time, type [ help ].", 0.1);
        println("To end your journey prematurely, type [ exit ].\n", 0.1);
        println("Prepare yourself...", 0.1);
        println("═══════════════════════════════════════════════════════════════════════════════════════", 0.1);
        println("\n Type 'help' for available commands or 'exit' to quit the game.");
    }

    // Tutorial Message
    private void terminalTutorialMessage() {
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
        println("2. >> \uD835\uDDD6\uD835\uDDE2\uD835\uDDE0\uD835\uDDD5\uD835\uDDD4\uD835\uDDE7 << If an enemy is in the room, combat will begin automatically. \n    It is turn-based.");
        println("       - Use the command: [ attack ]\n");
        println("3. >> \uD835\uDDD6\uD835\uDDDB\uD835\uDDD4\uD835\uDDE5\uD835\uDDD4\uD835\uDDD6\uD835\uDDE7\uD835\uDDD8\uD835\uDDE5 << Manage your character's state and gear.");
        println("       - Use the commands: [ status ] and [ inventory ]\n\n");
        println("To see all available commands at any time, type [ help ].");
        println("To end your journey prematurely, type [ exit ].\n");
        println("Prepare yourself...");
        println("═══════════════════════════════════════════════════════════════════════════════════════");
        println("\nType 'help' for available commands or 'exit' to quit the game.");
    }

    // Help message
    private void terminalHelpMessage() {
        println("\nAVAILABLE COMMANDS:");
        println("- move [direction] : Navigate to an adjacent room");
        println("- attack           : Start or continue combat with the room's enemy");
        println("- status           : terminal your current health and equipped gear");
        println("- inventory        : View items in your backpack");
        println("- colour [color]   : Change text color (red, green, blue, yellow, cyan, magenta, white)");
        println("- clear            : Clear the terminal screen");
        println("- help             : terminal this help message");
        println("- start            : Start the game");
        println("- exit             : Quit the game");
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
        println("");
        println("A spectral voice whispers through the fog:\n \"Traveler, before you venture forth, you must remember who you are...\"");
        println("");
        println("");
        println("THE PATHS YOU COULD HAVE BEEN:");
        println("");
        println("1. KNIGHT - A stalwart warrior with enhanced strength and durability");
        println("   ♦ High Defense  ♦ Strong Attack  ♦ No Magic");
        println("");
        println("2. MAGE - A master of arcane arts with powerful spells but fragile defenses");
        println("   ♦ Low Health  ♦ Low Weapon Attack  ♦ Powerful Magic");
        println("");
        println("3. ROGUE - A nimble trickster who relies on speed and cunning");
        println("   ♦ Medium Health  ♦ Quick Attacks  ♦ Little Magic");
        println("");
        println("⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘\n");
        println("What is your name, brave soul?\n");
        println("  - Use the command: [ name <your name> ]");
    }

    // Knight ASCII Art method
    private void displayKnightCharacter() {
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

        //as this is an animation, a new thread is created to run the animation without blocking the main thread.
        Thread idleAnimationThread = idleAnimation(knight1, knight2);
        idleAnimationThread.start();
    }


    // COMMAND METHODS ------------------------------------------------------------------------------------------------
    // Method to process different commands that are inputted by the user.e
    private void processCommand(String input) {
        // Display inputted command in display area.
        println("\n > " + input);

        switch (currentRoom.getName()) {
            case "tutorial":
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
                    terminalHelpMessage();
                } else if (input.equals("clear")) {
                    clearScreen();
                } else if (input.equals("status")) {
                    // player.displayStatus();
                } else if (input.equals("start")) {
                    displayKnightCharacter();
                    currentRoom = new Room("Graveyard", "A dark and eerie graveyard.");

                    display.setText("");
                    graveyardTitleMessage();
                    graveyardIntroMessage();
                } else {
                    println("Unknown command. Type 'help' for available commands.");
                }
                break;
            case "graveyard":

                if (input.startsWith("name")) {
                    NAME = input.replace("name", "").toLowerCase().trim();
                    println("\nYour name will be: " + NAME);
                    println("\nAnd what will your class be?");
                    println("- Use the command: [ class <knight,mage,rogue> ]");
                }

                if (input.startsWith("class")) {
                    String replace = input.replace("class", "").toLowerCase().trim();
                    if (input.contains("knight")) {
                        CLASS = replace;
                    } else if (input.contains("mage")) {
                        CLASS = replace;
                    } else if (input.contains("rogue")) {
                        CLASS = replace;
                    } else {
                        println("Please specify one of the designated classes.");
                    }
                    println("Your class will be: " + CLASS);
                    println("\n If you wish to alter your memory, this is your last chance.");
                    println("However if this is how you choose to remember yourself:");
                    println("- Use the command: [ begin ]\n");
                }

                if (input.equals("begin")) {
                    println(NAME + ", a " + CLASS + " from the Lands Between is ready to begin their journey.");
                    switch (CLASS) {
                        case "knight":
                            player = new Player(NAME, Player.KNIGHT);
                            println(player.getStatus());
                            break;
                        case "mage":
                            player = new Player(NAME, Player.MAGE);
                            println(player.getStatus());
                            break;
                        case "rogue":
                            player = new Player(NAME, Player.ROGUE);
                            println(player.getStatus());
                            break;
                    }
                }
                break;
        }

    }

    // Clear Method
    private void clearScreen() {
        display.setText("");
        tutorialTitleMessage();
        terminalTutorialMessage();
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
    private Thread idleAnimation(String knight1, String knight2) {
        Thread animationThread = new Thread(() -> {
            while (true) {
                try {
                    SwingUtilities.invokeLater(() -> {
                        characterArea.setText(knight1);
                        characterArea.setCaretPosition(0);
                    });
                    Thread.sleep(2500); //delay till each breath.
                    SwingUtilities.invokeLater(() -> {
                        characterArea.setText(knight2);
                        characterArea.setCaretPosition(0);
                    });
                    Thread.sleep(1250); // different delay to make it look more dynamic
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

    private void initializeUI() {
        JFrame frame = new JFrame("Terminality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());

        JTextArea sidebarArea = new JTextArea();
        sidebarArea.setEditable(false);
        sidebarArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        sidebarArea.setBackground(BLACK);
        sidebarArea.setForeground(DEFAULT);
        JScrollPane leftScroll = new JScrollPane(sidebarArea);
        leftScroll.setPreferredSize(new Dimension(300, frame.getHeight()));

        display = new JTextArea();
        display.setEditable(false);
        display.setFont(new Font("Monospaced", Font.PLAIN, 14));
        display.setBackground(BLACK);
        display.setForeground(DEFAULT);

        JScrollPane centerScroll = new JScrollPane(display);
        centerScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // Set the default viewport view position to the top
        centerScroll.getViewport().setViewPosition(new Point(0, 0));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(705, 600));
        centerPanel.add(centerScroll, BorderLayout.CENTER);

        // Update the character area initialization
        characterArea = new JTextArea();
        characterArea.setEditable(false);
        characterArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        characterArea.setBackground(BLACK);
        characterArea.setForeground(DEFAULT);
        // Center the text in the character area
        characterArea.setMargin(new Insets(50, 50, 50, 50));
        JScrollPane rightScroll = new JScrollPane(characterArea);
        rightScroll.setPreferredSize(new Dimension(480, frame.getHeight()));

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

        mainPanel.add(leftScroll, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightScroll, BorderLayout.EAST);
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

    private void initializeWorld() {
        // Set the starting room
        currentRoom = new Room("Tutorial", "Area when the user opens the game for the first time.");
    }
}