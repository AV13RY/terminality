package game;

import javax.swing.*;
import java.awt.*;

public class Game {
    private JTextArea outputArea;
    private JTextField inputField;

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
        println("You awaken in a dark dungeon with no memory of how you got here.");
        println("Your only goal: survive, gather loot, and defeat the final boss.");
        println("\nType 'help' for available commands or 'exit' to quit the game.");
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
                newColor = Color.RED;
                break;
            case "green":
                newColor = Color.GREEN;
                break;
            case "blue":
                newColor = Color.BLUE;
                break;
            case "yellow":
                newColor = Color.YELLOW;
                break;
            case "cyan":
                newColor = Color.CYAN;
                break;
            case "magenta":
                newColor = Color.MAGENTA;
                break;
            case "white":
                newColor = Color.WHITE;
                break;
            case "default":
                newColor = new Color(0xD8125B);
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

    // Method to clear the console output area.
    private void clearScreen() {
        outputArea.setText("");
        displayWelcomeMessage();
    }


    // Custom method for println (does the same thing just works with the interface + /n)
    private void println(String text) {
        outputArea.append(text + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    // UI METHODS
    private void initializeUI() {
        // Main frame setup
        JFrame frame = new JFrame("Terminality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Left Area (Sidebar)
        JTextArea sidebarArea = new JTextArea();
        sidebarArea.setEditable(false);
        sidebarArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        sidebarArea.setBackground(Color.BLACK);
        sidebarArea.setForeground(new Color(0xD8125B));
        JScrollPane leftScroll = new JScrollPane(sidebarArea);
        leftScroll.setPreferredSize(new Dimension(300, frame.getHeight()));

        // Centre Area (Output)
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(new Color(0xD8125B));

        JScrollPane centerScroll = new JScrollPane(outputArea);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(705, 600));
        centerPanel.add(centerScroll, BorderLayout.CENTER);

        // Right Area (Character Display)
        JTextArea characterDisplayArea = new JTextArea();
        characterDisplayArea.setEditable(false);
        characterDisplayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        characterDisplayArea.setBackground(Color.BLACK);
        characterDisplayArea.setForeground(new Color(0xD8125B));
        JScrollPane rightScroll = new JScrollPane(characterDisplayArea);
        rightScroll.setPreferredSize(new Dimension(500, frame.getHeight()));

        // Bottom Area (Input)
        inputField = new JTextField();
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        inputField.setBackground(Color.BLACK);
        inputField.setForeground(new Color(0xD8125B));
        inputField.setCaretColor(new Color(0xD8125B));
        inputField.setPreferredSize(new Dimension(705, 200));

        // Create a panel for the input field that excludes the left sidebar
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);

        // Add components to the main panel
        mainPanel.add(leftScroll, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightScroll, BorderLayout.EAST);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        // Add main panel to frame
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