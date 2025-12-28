/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

import gameRunning.Game;

/**
 * Ass3Game class serves as the entry point for the application.
 * This class contains the main method which creates a new Game instance,
 * initializes it, and runs it.
 */
public class Ass5Game {

    /**
     * The entry point of the application.
     * This method creates a new Game instance, initializes it, and runs it.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
