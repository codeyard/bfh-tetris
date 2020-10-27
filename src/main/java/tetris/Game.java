package tetris;

import tetris.gui.ActionEvent;
import tetris.gui.Block;
import tetris.gui.GUI;

public class Game {
    private final GUI gui;
    private final int width;
    private final int height;
    private Block block;

    public Game(GUI gui, int width, int height) {
        this.gui = gui;
        this.width = width;
        this.height = height;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        createBlock();

        while (true) {
            ActionEvent event = gui.waitEvent();
            handleEvent(event);
        }
    }

    private void createBlock() {
        block = new Block(((width - 1) / 2), (height - 1), 1);
        updateGUI();
    }

    private void handleEvent(ActionEvent event) {
        switch (event) {
            case MOVE_LEFT:
                block.x--;
                break;
            case MOVE_RIGHT:
                block.x++;
                break;
            case MOVE_DOWN:
                block.y--;
                break;
            default:
                break;
        }
        updateGUI();
    }

    public void updateGUI() {
        gui.clear();
        gui.drawBlock(block);
    }
}
