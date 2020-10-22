package tetris;

import tetris.gui.ActionEvent;
import tetris.gui.Block;
import tetris.gui.GUI;

public class Game {
    private Block block;
    private final GUI gui;

    public Game(GUI gui) {
        this.gui = gui;
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
        block = new Block(5, 5, 1);
        updateGUI();
    }

    private void handleEvent(ActionEvent event) {
        if (event == ActionEvent.MOVE_LEFT) {
            block.x -= 1;
        }
        else if (event == ActionEvent.MOVE_RIGHT) {
            block.x += 1;
        }
        else if (event == ActionEvent.MOVE_DOWN) {
            block.y -= 1;
        }
        gui.drawBlock(block);
    }

    public void updateGUI() {
        gui.drawBlock(block);
    }
}
