package tetris;

import tetris.gui.GUI;
import tetris.model.Game;

public class Tetris {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;

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
