package game;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Game {


    // GAME VARIABLES -------------------------------------------------------------------------------------------------
    // Section Declarations
    private JTextArea display;
    private JTextField terminal;

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
    private final Color DEFAULT2 = new Color(0x0f0207);

    // CORE METHODS ---------------------------------------------------------------------------------------------------

    // Game Constructor
    public Game() {
        initializeUI();
        terminalTitle();
        terminalWelcomeMessage();
    }

    // Main Method
    public static void main(String[] args) {
        Game game = new Game();
    }

    // TEXT METHODS ---------------------------------------------------------------------------------------------------
    // ASCII art title
    private void terminalTitle() {
        println("");
        println(" ████████╗███████╗██████╗ ███╗   ███╗██╗███╗   ██╗ █████╗ ██╗     ██╗████████╗██╗   ██╗");
        println(" ╚══██╔══╝██╔════╝██╔══██╗████╗ ████║██║████╗  ██║██╔══██╗██║     ██║╚══██╔══╝╚██╗ ██╔╝");
        println("    ██║   █████╗  ██████╔╝██╔████╔██║██║██╔██╗ ██║███████║██║     ██║   ██║    ╚████╔╝");
        println("    ██║   ██╔══╝  ██╔══██╗██║╚██╔╝██║██║██║╚██╗██║██╔══██║██║     ██║   ██║     ╚██╔╝");
        println("    ██║   ███████╗██║  ██║██║ ╚═╝ ██║██║██║ ╚████║██║  ██║███████╗██║   ██║      ██║");
        println("    ╚═╝   ╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝╚═╝   ╚═╝      ╚═╝ \n");
    }


    // Welcome message
    private void terminalWelcomeMessage() {
        println(" ═══════════════════════════════════════════════════════════════════════════════════════");
        println("                       A Java Dungeon Crawler by Jack McGillivray                       ");
        println(" ═══════════════════════════════════════════════════════════════════════════════════════");
        println(" ▖ ▖   ▄▖▖▖▄▖▄▖▄▖▄▖▄▖▖    ▖ ▖");
        println(" ▝▖▝▖  ▐ ▌▌▐ ▌▌▙▘▐ ▌▌▌   ▞ ▞ ");
        println(" ▞ ▞   ▐ ▙▌▐ ▙▌▌▌▟▖▛▌▙▖  ▝▖▝▖\n");


        println(" Hello, World.. . . . . ............... .  . . . .  .", 2);
        println(" Ah what am I saying, you probably already know this isn't your average powershell.\n", 0.5);
        println(" Anyway, welcome to Terminality, a text-based dungeon crawler.", 2);

        println(" Your goal is to navigate the crypt, slay its inhabitants, and defeat the final boss.\n", 2);
        println(" Your experience will consist of three main interactions:\n", 1);
        println(" 1. >> \uD835\uDDE0\uD835\uDDE2\uD835\uDDE9\uD835\uDDD8\uD835\uDDE0\uD835\uDDD8\uD835\uDDE1\uD835\uDDE7 << When required, you'll be prompted to move between rooms.");
        println(" - Use the command: [ move <direction> ]\n");
        println(" 2. >> \uD835\uDDD6\uD835\uDDE2\uD835\uDDE0\uD835\uDDD5\uD835\uDDD4\uD835\uDDE7 << If an enemy is in the room, combat will begin automatically. \n    It is turn-based.");
        println("        - Use the command: [ attack ]\n");
        println(" 3. >> \uD835\uDDD6\uD835\uDDDB\uD835\uDDD4\uD835\uDDE5\uD835\uDDD4\uD835\uDDD6\uD835\uDDE7\uD835\uDDD8\uD835\uDDE5 << Manage your character's state and gear.");
        println("        - Use the commands: [ status ] and [ inventory ]\n\n", 1);
        println(" To see all available commands at any time, type [ help ].");
        println(" To end your journey prematurely, type [ exit ].\n", 1);
        println(" Prepare yourself...");
        println(" ═══════════════════════════════════════════════════════════════════════════════════════");
        println("\n Type 'help' for available commands or 'exit' to quit the game.");
    }

    private void terminalTutorialMessage() {
        println(" ═══════════════════════════════════════════════════════════════════════════════════════");
        println("                       A Java Dungeon Crawler by Jack McGillivray                       ");
        println(" ═══════════════════════════════════════════════════════════════════════════════════════");
        println(" ▖ ▖   ▄▖▖▖▄▖▄▖▄▖▄▖▄▖▖    ▖ ▖");
        println(" ▝▖▝▖  ▐ ▌▌▐ ▌▌▙▘▐ ▌▌▌   ▞ ▞ ");
        println(" ▞ ▞   ▐ ▙▌▐ ▙▌▌▌▟▖▛▌▙▖  ▝▖▝▖\n");


        println(" Hello, World.. . . . . ............... .  . . . .  .");
        println(" Ah what am I saying, you probably already know this isn't just CMD 2.0.\n");
        println(" Anyway, welcome to Terminality, a text-based dungeon crawler.");

        println(" Your goal is to navigate the crypt, slay its inhabitants, and defeat the final boss.\n");
        println(" Your experience will consist of three main interactions:\n");
        println(" 1. >> \uD835\uDDE0\uD835\uDDE2\uD835\uDDE9\uD835\uDDD8\uD835\uDDE0\uD835\uDDD8\uD835\uDDE1\uD835\uDDE7 << When required, you'll be prompted to move between rooms.");
        println(" - Use the command: [ move <direction> ]\n");
        println(" 2. >> \uD835\uDDD6\uD835\uDDE2\uD835\uDDE0\uD835\uDDD5\uD835\uDDD4\uD835\uDDE7 << If an enemy is in the room, combat will begin automatically. \n    It is turn-based.");
        println("        - Use the command: [ attack ]\n");
        println(" 3. >> \uD835\uDDD6\uD835\uDDDB\uD835\uDDD4\uD835\uDDE5\uD835\uDDD4\uD835\uDDD6\uD835\uDDE7\uD835\uDDD8\uD835\uDDE5 << Manage your character's state and gear.");
        println("        - Use the commands: [ status ] and [ inventory ]\n\n");
        println(" To see all available commands at any time, type [ help ].");
        println(" To end your journey prematurely, type [ exit ].\n");
        println(" Prepare yourself...");
        println(" ═══════════════════════════════════════════════════════════════════════════════════════");
        println("\n Type 'help' for available commands or 'exit' to quit the game.");
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
        println("- exit             : Quit the game");
    }

    // COMMAND METHODS ------------------------------------------------------------------------------------------------
    // Method to process different commands that are inputted by the user.e
    private void processCommand(String input) {
        // Display inputted command in display area.
        println("\n > " + input);

        // Different commands that can be used.
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
        } else {
            println("Unknown command. Type 'help' for available commands.");
        }
    }

    // Clear Method
    private void clearScreen() {
        display.setText("");
        terminalTitle();
        terminalTutorialMessage();
    }

    // Custom println method to display text in the UI display area.
    private void println(String text) {
        display.append(text + "\n");
        display.setCaretPosition(display.getDocument().getLength());
    }

    private void println(String text, double waitTime) {
        display.append(text + "\n");
        display.setCaretPosition(display.getDocument().getLength());
        wait(waitTime);
    }

    // UI Methods -----------------------------------------------------------------------------------------------------
    private void initializeUI() {
        JFrame frame = new JFrame("Terminality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());

        JTextArea sidebarArea = new JTextArea();
        sidebarArea.setEditable(false);
        sidebarArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        sidebarArea.setBackground(DEFAULT2);
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

        JTextArea characterterminalArea = new JTextArea();
        characterterminalArea.setEditable(false);
        characterterminalArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        characterterminalArea.setBackground(DEFAULT2);
        characterterminalArea.setForeground(DEFAULT);
        JScrollPane rightScroll = new JScrollPane(characterterminalArea);
        rightScroll.setPreferredSize(new Dimension(480, frame.getHeight()));

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
}