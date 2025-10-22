# Terminality: A Java Dungeon Crawler

![vgy.me](https://i.vgy.me/8jl3AJ.png)

![Terminality Logo Placeholder](https://img.shields.io/badge/Language-Java-blue)
![Terminality Logo Placeholder](https://img.shields.io/badge/Type-Console%20Application-orange)
![Terminality Logo Placeholder](https://img.shields.io/badge/Style-Text%20Based%20RPG-green)

## Overview

**Terminality** is a text-based, command-line dungeon crawler game developed in **pure Java**. The project was created for the 6G4Z0044: Introduction to Programming module, by **Jack McGillivray**.

The goal is to guide a player through their journey, engaging in turn-based combat, managing items, and utilizing the player's core stats to survive the dungeon, gather and upgrade loot, and eventually defeat the final boss.

## Key Features

### Core Programming Concepts

* **Console I/O:** All user interaction is managed through typed commands and text output.
* **Selection & Loops:** Extensive use of `if/else` and `switch` statements for command parsing and combat resolution, encapsulated within a persistent **Game Loop**.
* **Methods & Objects:** The code is modularized using distinct methods and is built around key classes: `Player`, various `Enemy` types, `Room`, and `Item`.
* **Arrays/Lists:** Dynamic collection types (e.g., `ArrayList`) are used for managing the player's inventory and the list of enemies within a room.
* **Testing:** A dedicated class is used to verify the correctness of core logic, such as damage calculation and character instantiation.

### Advanced OOP & Style

* **Inheritance:** An **Abstract `Character`** class provides a blueprint for both the `Player` and the various specialized `Enemy` subclasses (e.g., `Goblin`, `Ogre`, `Boss`).
* **Encapsulation:** Strict control over data integrity is enforced using private fields and public getter/setter methods across all major classes.
* **Interfaces:** The **`IEquippable`** interface is implemented by different item classes (`Sword`, `Shield`) to ensure polymorphic handling of gear.
* **Appropriate Package Structure:** Code is professionally organized into logical packages.

* **Self-directed Code Management:** **Git / GitHub** version control software was used throughout the development of the programme to manage feature implementation and maintain code integrity.

## Getting Started

### Prerequisites

You need a **Java Development Kit (JDK)** installed on your machine (Java 17 or higher recommended) to run.

### How to Run

1.  **Clone the Repository:**
    ```bash
    git clone git@github.com:AV13RY/terminality.git
    ```
2.  **Navigate to the Source Directory:**
    ```bash
    cd Terminality/src
    ```
3.  **Compile and Run (Standard Command Line):**
    ```bash
    # You may need to adjust the path to your main class
    javac com/terminality/game/*.java com/terminality/characters/*.java com/terminality/world/*.java
    java com.terminality.game.Game
    ```
    *(Note: If using an IDE like IntelliJ or VS Code, use the integrated 'Run' feature.)*

## In-Game Commands

Control your character by typing commands when prompted. Commands are case-insensitive.

| Command | Purpose | Example |
| :--- | :--- | :--- |
| `move [direction]` | Navigate to an adjacent room. | `move north` |
| `attack` | Start or continue combat with the room's enemy. | `attack` |
| `status` | Display the player's current health and equipped gear. | `status` |
| `inventory` | View items in the player's backpack. | `inventory` |
| `help` | List all available commands. | `help` |
| `exit` | Quit the game. | `exit` |

## Project Structure

{{{{{{{{NEED TO ADD}}}}}}}}
## Author

**[Jack McGillivray]**
Software Engineer & Degree Apprentice.

## License

This project is submitted solely for academic assessment.
