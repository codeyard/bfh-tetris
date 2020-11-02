/**
 * The class Tetris implements the main class to start a Tetris game.
 * Version: 1.0
 * Author: Raphael Gerber
 */

package tetris;

import tetris.gui.GUI;
import tetris.model.Game;

public class Tetris {
    private static final int WIDTH = 10; /* The width of the field. */
    private static final int HEIGHT = 20; /* The height of the field. */

    /**
     * Creates a Tetris game using a graphical user interface and starts the game.
     * @param args = the width and height of the field
     */
    public static void main(String[] args) {
        int width = WIDTH;
        int height = HEIGHT;
        try {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException |NumberFormatException ex) {
        }
        GUI gui = new GUI(width, height);
        Game game = new Game(gui, width, height);
        game.start();
    }
}
