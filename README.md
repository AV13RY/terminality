# Terminality: A Java Dungeon Crawler

![Terminality Logo Placeholder](https://img.shields.io/badge/Language-Java-blue)
![Terminality Logo Placeholder](https://img.shields.io/badge/Type-Console%20Application-orange)
![Terminality Logo Placeholder](https://img.shields.io/badge/Status-Development-red)

## Overview

[cite_start]**Terminality** is a text-based dungeon crawler game developed in **pure Java** for the 6G4Z0044: Introduction to Programming module[cite: 1]. The objective of the game is for the player to navigate a labyrinth of rooms, manage their inventory, engage in combat with different enemy types, and ultimately defeat the dungeon's final boss.

The project demonstrates core programming concepts and advanced Object-Oriented Programming (OOP) principles, including **Inheritance**, **Interfaces**, and strict **Encapsulation**.

## Features Implemented

The game is built around the following core functionalities:

### Core Gameplay Features (Introduction to Programming)

* [cite_start]**Console I/O:** All game output and player commands are handled via the console[cite: 66, 68].
* [cite_start]**Player & Enemy Objects:** Management of separate `Player` and `Enemy` entities with health and attack stats[cite: 77].
* [cite_start]**Combat Logic:** Turn-based combat managed by conditional statements (`if/else`) to handle attacks, damage, and defeat[cite: 70].
* [cite_start]**Game Loop:** The core game logic runs continuously until the player is defeated or the objective is met[cite: 71].
* [cite_start]**Methods:** Separation of concerns using dedicated methods for actions like `move()`, `attack()`, and `printStatus()`[cite: 73].
* [cite_start]**Inventory/Map Data:** Use of arrays/lists to manage the player's inventory and the map structure[cite: 74].

### Advanced OOP & Style Features (High-Grade Focus)

* [cite_start]**Inheritance:** Use of an **Abstract `Character`** class, with `Player` and multiple enemy types (`Goblin`, `Ogre`, `Boss`) inheriting common attributes and overriding specific behaviours[cite: 113, 115].
* [cite_start]**Encapsulation:** All class attributes are private, with controlled access via public getter and setter methods to ensure data integrity[cite: 115].
* [cite_start]**Interfaces:** Implementation of the **`IEquippable`** interface, allowing various items (e.g., `Sword`, `Shield`) to be handled polymorphically within the inventory system[cite: 115].
* [cite_start]**Testing:** Inclusion of a dedicated `TestHarness` class to verify the correctness of core game mechanics, such as damage calculation and room navigation[cite: 75, 113].
* [cite_start]**Professional Code Management:** Project development was managed using **Git** for version control, demonstrating self-directed code management[cite: 116, 121].

## Getting Started

### Prerequisites

You will need a Java Development Kit (JDK) installed on your system (Java 17 or higher recommended).

### Running Terminality

1.  **Clone the Repository:**
    ```bash
    git clone [YOUR-GIT-REPO-LINK]
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd Terminality
    ```
3.  **Compile the code:**
    ```bash
    # Assuming your main class is in the root and called Game.java
    javac com/rpg/game/Game.java
    ```
4.  **Run the game:**
    ```bash
    # Execute the main class
    java com.rpg.game.Game
    ```

## How to Play

Upon starting the game, you will be in the dungeon's entrance room. You control your character by typing commands into the console.

| Command | Action |
| :--- | :--- |
| `move [direction]` | Move to an adjacent room (e.g., `move north`, `move east`) |
| `attack` | Engage the current enemy in combat |
| `status` | View your current health, attack power, and equipped gear |
| `inventory` | View your collected items |
| `help` | Display a list of available commands |
| `exit` | Quit the game |

## Project Structure

[cite_start]The project is structured using appropriate Java packages [cite: 113] for clear organization:
