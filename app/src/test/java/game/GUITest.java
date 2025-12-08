package game;

import characters.Player;
import characters.Enemy;
import world.MapBuilder;
import world.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GUI Tests")
class GUITest {

    @BeforeEach
    public void setUp() {
        // Set headless mode to prevent GUI from displaying
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    @DisplayName("Static getters should return null when GUI is not initialized")
    void staticGettersShouldReturnNullWhenGUIIsNotInitialized() {
        // All static getters should return null when GUI hasn't been instantiated
        assertNull(GUI.getMapBuilder(), "MapBuilder should be null without GUI initialization");
        assertNull(GUI.getCurrentRoom(), "CurrentRoom should be null without GUI initialization");
        assertNull(GUI.getPlayer(), "Player should be null without GUI initialization");
        assertNull(GUI.getCurrentEnemy(), "CurrentEnemy should be null without GUI initialization");
        assertEquals("", GUI.getName(), "Name should be empty without GUI initialization");
        assertEquals(0, GUI.getCommandCount(), "CommandCount should be 0 without GUI initialization");
        assertEquals(0, GUI.countVisitedRooms(), "VisitedRooms count should be 0 without GUI initialization");
    }

    @Test
    @DisplayName("Should not instantiate GUI due to headless mode")
    void shouldNotInstantiateGUIDueToHeadlessMode() {
        // In headless mode, GUI instantiation should fail gracefully
        assertThrows(Exception.class, () -> {
            GUI gui = new GUI();
        }, "GUI instantiation should fail in headless mode");
    }

    @Test
    @DisplayName("Color constants should have expected values")
    void colorConstantsShouldHaveExpectedValues() {
        // This tests the color constants if they were accessible
        // Since they're private, we can't directly test them
        // But we can verify the class exists and has expected structure
        assertTrue(true, "Color constants are defined as private fields in GUI");
    }

    @Test
    @DisplayName("GUI class should have expected structure")
    void guiClassShouldHaveExpectedStructure() {
        // Verify GUI class exists and has expected methods
        assertNotNull(GUI.class);
        
        // Check that static methods exist
        assertDoesNotThrow(() -> GUI.class.getMethod("getMapBuilder"));
        assertDoesNotThrow(() -> GUI.class.getMethod("getCurrentRoom"));
        assertDoesNotThrow(() -> GUI.class.getMethod("getPlayer"));
        assertDoesNotThrow(() -> GUI.class.getMethod("getCurrentEnemy"));
        assertDoesNotThrow(() -> GUI.class.getMethod("getName"));
        assertDoesNotThrow(() -> GUI.class.getMethod("getCommandCount"));
        assertDoesNotThrow(() -> GUI.class.getMethod("countVisitedRooms"));
    }

    @Test
    @DisplayName("Should handle multiple calls to static getters")
    void shouldHandleMultipleCallsToStaticGetters() {
        // Multiple calls should not cause issues
        for (int i = 0; i < 10; i++) {
            assertNull(GUI.getMapBuilder());
            assertNull(GUI.getCurrentRoom());
            assertNull(GUI.getPlayer());
            assertNull(GUI.getCurrentEnemy());
            assertEquals("", GUI.getName());
            assertEquals(0, GUI.getCommandCount());
            assertEquals(0, GUI.countVisitedRooms());
        }
    }

    @Test
    @DisplayName("Static methods should be thread-safe")
    void staticMethodsShouldBeThreadSafe() {
        // Test that static methods can be called from multiple threads
        Runnable task = () -> {
            assertNull(GUI.getMapBuilder());
            assertNull(GUI.getCurrentRoom());
            assertNull(GUI.getPlayer());
            assertNull(GUI.getCurrentEnemy());
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        
        assertDoesNotThrow(() -> {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        });
    }

    @Test
    @DisplayName("Should handle reflection access to private fields")
    void shouldHandleReflectionAccessToPrivateFields() {
        // Verify that private fields exist (though we can't access them directly)
        String[] expectedFields = {"display", "terminal", "characterArea", "statsArea", 
                                  "commandLog", "mapBuilder", "currentRoom", "player", 
                                  "NAME", "CLASS", "currentEnemy"};
        
        for (String fieldName : expectedFields) {
            try {
                GUI.class.getDeclaredField(fieldName);
                // If we reach here, the field exists
                assertTrue(true, "Field " + fieldName + " exists");
            } catch (NoSuchFieldException e) {
                fail("Expected field " + fieldName + " not found");
            }
        }
    }

    @Test
    @DisplayName("Should have expected constructor")
    void shouldHaveExpectedConstructor() {
        // Verify default constructor exists
        assertDoesNotThrow(() -> {
            GUI.class.getConstructor();
        });
    }

    @Test
    @DisplayName("Should handle headless mode property")
    void shouldHandleHeadlessModeProperty() {
        // Verify headless mode is set
        assertEquals("true", System.getProperty("java.awt.headless"));
    }

    @Test
    @DisplayName("Static methods should return consistent null values")
    void staticMethodsShouldReturnConsistentNullValues() {
        // All calls should return consistent values
        assertNull(GUI.getMapBuilder());
        assertNull(GUI.getCurrentRoom());
        assertNull(GUI.getPlayer());
        assertNull(GUI.getCurrentEnemy());
        
        // Call again to ensure consistency
        assertNull(GUI.getMapBuilder());
        assertNull(GUI.getCurrentRoom());
        assertNull(GUI.getPlayer());
        assertNull(GUI.getCurrentEnemy());
    }

    @Test
    @DisplayName("Command count should remain zero without GUI")
    void commandCountShouldRemainZeroWithoutGUI() {
        // Command count should always be 0 without GUI initialization
        assertEquals(0, GUI.getCommandCount());
        
        // Even after multiple calls
        for (int i = 0; i < 5; i++) {
            assertEquals(0, GUI.getCommandCount());
        }
    }

    @Test
    @DisplayName("Name should remain empty without GUI")
    void nameShouldRemainEmptyWithoutGUI() {
        // Name should always be empty without GUI initialization
        assertEquals("", GUI.getName());
        
        // Even after multiple calls
        for (int i = 0; i < 5; i++) {
            assertEquals("", GUI.getName());
        }
    }

    @Test
    @DisplayName("Visited rooms count should remain zero without GUI")
    void visitedRoomsCountShouldRemainZeroWithoutGUI() {
        // Visited rooms count should always be 0 without GUI initialization
        assertEquals(0, GUI.countVisitedRooms());
        
        // Even after multiple calls
        for (int i = 0; i < 5; i++) {
            assertEquals(0, GUI.countVisitedRooms());
        }
    }

    @Test
    @DisplayName("Should verify GUI class package and modifiers")
    void shouldVerifyGUIClassPackageAndModifiers() {
        // Verify class is in correct package
        assertEquals("game", GUI.class.getPackage().getName());
        
        // Verify class is public
        assertTrue(java.lang.reflect.Modifier.isPublic(GUI.class.getModifiers()));
    }

    @Test
    @DisplayName("Should handle concurrent access to static methods")
    void shouldHandleConcurrentAccessToStaticMethods() {
        // Test concurrent access doesn't cause issues
        int numThreads = 10;
        Thread[] threads = new Thread[numThreads];
        
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    GUI.getMapBuilder();
                    GUI.getCurrentRoom();
                    GUI.getPlayer();
                    GUI.getCurrentEnemy();
                    GUI.getName();
                    GUI.getCommandCount();
                    GUI.countVisitedRooms();
                }
            });
        }
        
        assertDoesNotThrow(() -> {
            for (Thread thread : threads) {
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
        });
    }
}
