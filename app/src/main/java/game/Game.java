package game;

import javax.swing.*;
import java.awt.*;

public class Game {
    private JTextArea outputArea;
    private JTextField inputField;

    // Color definitions
    Color RED = Color.RED;
    Color GREEN = Color.GREEN;
    Color BLUE = Color.BLUE;
    Color YELLOW = Color.YELLOW;
    Color CYAN = Color.CYAN;
    Color MAGENTA = Color.MAGENTA;
    Color WHITE = Color.WHITE;
    Color DARK_GRAY = Color.DARK_GRAY;
    Color LIGHT_GRAY = Color.LIGHT_GRAY;
    Color BLACK = Color.BLACK;
    Color DEFAULT = new Color(0xD8125B);


    public Game() {
        initializeUI();
        displayTitle();
        displayWelcomeMessage();
    }

    public static void main(String[] args) {
        Game game = new Game();
    }

    private void displayTitle() {
        println(" ████████╗███████╗██████╗ ███╗   ███╗██╗███╗   ██╗ █████╗ ██╗     ██╗████████╗██╗   ██╗");
        println(" ╚══██╔══╝██╔════╝██╔══██╗████╗ ████║██║████╗  ██║██╔══██╗██║     ██║╚══██╔══╝╚██╗ ██╔╝");
        println("    ██║   █████╗  ██████╔╝██╔████╔██║██║██╔██╗ ██║███████║██║     ██║   ██║    ╚████╔╝");
        println("    ██║   ██╔══╝  ██╔══██╗██║╚██╔╝██║██║██║╚██╗██║██╔══██║██║     ██║   ██║     ╚██╔╝");
        println("    ██║   ███████╗██║  ██║██║ ╚═╝ ██║██║██║ ╚████║██║  ██║███████╗██║   ██║      ██║");
        println("    ╚═╝   ╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝╚═╝   ╚═╝      ╚═╝ \n");
    }

    private void displayWelcomeMessage() {
        println("════════════════════════════════════════════════════════════════════════════════════════");
        println("                       A Java Dungeon Crawler by Jack McGillivray                       ");
        println("════════════════════════════════════════════════════════════════════════════════════════");
        println(" >> B R I E F I N G <<\n");
        println(" Welcome, Adventurer, to the Labyrinth of Shadows.\n");
        println(" This is a text-based, single-player dungeon crawler. Your goal is to navigate the");
        println(" dungeon, fight monsters, and defeat the Boss at the end of your journey.\n");
        println(" Your experience will consist of three main interactions:\n");
        println(" 1.  **MOVEMENT:** You will be prompted to move between rooms (North, South, East, West)");
        println(" - Use the command: [ move <direction> ]\n");
        println(" 2.  **COMBAT:** If an enemy is in the room, combat will begin automatically. \n    It is turn-based.");
        println("        - Use the command: [ attack ]\n");
        println(" 3.  **STATUS & INVENTORY:** Manage your character's state and gear.");
        println("        - Use the commands: [ status ] and [ inventory ]\n");
        println(" Remember, the labyrinth is unforgiving. Plan your moves, manage your health, \n and use your commands wisely.\n");
        println(" To see all available commands at any time, type [ help ].");
        println(" To end your journey prematurely, type [ exit ].\n");
        println(" Prepare yourself...");
        println("\n Type 'help' for available commands or 'exit' to quit the game.");
    }

    private void processCommand(String input) {
        println("\n> " + input);

        if (input.equals("exit")) {
            println("Thank you for playing Terminality. Goodbye!");
            System.exit(0);
        } else if (input.equals("help")) {
            displayHelp();
        } else if (input.equals("clear")) {
            clearScreen();
        } else if (input.startsWith("colour ")) {
            String[] parts = input.split(" ", 2);
            if (parts.length > 1) {
                changeTextColor(parts[1]);
            } else {
                println("Please specify a color. Available colors: red, green, blue, yellow, cyan, magenta, white");
            }
        } else {
            println("Unknown command. Type 'help' for available commands.");
        }
    }

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
        outputArea.setForeground(newColor);
        inputField.setForeground(newColor);
        inputField.setCaretColor(newColor);
        println("Text color changed to " + colorName);
    }

    private void displayHelp() {
        println("\nAVAILABLE COMMANDS:");
        println("- move [direction] : Navigate to an adjacent room");
        println("- attack           : Start or continue combat with the room's enemy");
        println("- status           : Display your current health and equipped gear");
        println("- inventory        : View items in your backpack");
        println("- colour [color]   : Change text color (red, green, blue, yellow, cyan, magenta, white)");
        println("- clear            : Clear the terminal screen");
        println("- help             : Display this help message");
        println("- exit             : Quit the game");
    }

    private void clearScreen() {
        outputArea.setText("");
        displayTitle();
        displayWelcomeMessage();
    }

    private void println(String text) {
        outputArea.append(text + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Terminality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());

        JTextArea sidebarArea = new JTextArea();
        sidebarArea.setEditable(false);
        sidebarArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        sidebarArea.setBackground(DARK_GRAY);
        sidebarArea.setForeground(DEFAULT);
        JScrollPane leftScroll = new JScrollPane(sidebarArea);
        leftScroll.setPreferredSize(new Dimension(300, frame.getHeight()));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setBackground(BLACK);
        outputArea.setForeground(DEFAULT);

        JScrollPane centerScroll = new JScrollPane(outputArea);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(705, 600));
        centerPanel.add(centerScroll, BorderLayout.CENTER);

        JTextArea characterDisplayArea = new JTextArea();
        characterDisplayArea.setEditable(false);
        characterDisplayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        characterDisplayArea.setBackground(BLACK);
        characterDisplayArea.setForeground(DEFAULT);
        JScrollPane rightScroll = new JScrollPane(characterDisplayArea);
        rightScroll.setPreferredSize(new Dimension(500, frame.getHeight()));

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(BLACK);

        JLabel promptLabel = new JLabel(" > ");
        promptLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        promptLabel.setForeground(WHITE);
        inputPanel.add(promptLabel, BorderLayout.WEST);

        inputField = new JTextField();
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        inputField.setBackground(BLACK);
        inputField.setForeground(DEFAULT);
        inputField.setCaretColor(DEFAULT);
        inputField.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));
        inputPanel.add(inputField, BorderLayout.CENTER);

        mainPanel.add(leftScroll, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightScroll, BorderLayout.EAST);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);

        inputField.addActionListener(e -> {
            String input = inputField.getText().trim().toLowerCase();
            processCommand(input);
            inputField.setText("");
        });

        frame.setVisible(true);
        inputField.requestFocus();
    }
}