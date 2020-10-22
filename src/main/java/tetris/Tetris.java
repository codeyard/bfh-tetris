package tetris;

import tetris.gui.GUI;

public class Tetris {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;

    public static void main(String[] args) {
        GUI gui = new GUI(WIDTH, HEIGHT);
        Game game = new Game(gui);
        game.start();
    }
}
