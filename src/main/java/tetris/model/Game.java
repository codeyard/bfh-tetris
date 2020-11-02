/**
 * The class Game implements the Tetris game.
 * Version: 1.0
 * Author: Raphael Gerber
 */

package tetris.model;

import tetris.gui.ActionEvent;
import tetris.gui.GUI;
import tetris.model.figures.*;

import java.util.Random;

public class Game {
    private final GUI gui; /* The graphical user interface. */
    private final int width; /* The width of the field. */
    private final int height; /* The height of the field. */
    private Figure figure; /* The figure of the game. */

    /**
     * Constructs a game with the specified graphical user interface.
     * @param gui = the graphical user interface
     * @param width = the width of the field
     * @param height = the height of the field
     */
    public Game(GUI gui, int width, int height) {
        this.gui = gui;
        this.width = width;
        this.height = height;
    }

    /**
     *  Start the game by creating a figure and waiting for action events.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        createFigure();

        while (true) {
            ActionEvent event = gui.waitEvent();
            handleEvent(event);
        }
    }

    /**
     * Creates a random figure at the top of the field.
     */
    private void createFigure() {
        int x = (width - 1) / 2;
        int y = height - 1;
        String[] chars = new String[] {"I", "J", "L", "O", "S", "T", "Z"};
        int index = new Random().nextInt(chars.length);
        String random = (chars[index]);

        switch (random) {
            case "I":
                figure = new IFigure(x, y);
                break;
            case "J":
                figure = new JFigure(x, y);
                break;
            case "L":
                figure = new LFigure(x, y);
                break;
            case "O":
                figure = new OFigure(x, y);
                break;
            case "S":
                figure = new SFigure(x, y);
                break;
            case "T":
                figure = new TFigure(x, y);
                break;
            default:
                figure = new ZFigure(x, y);
                break;
        }

        updateGUI();
    }

    /**
     * Handles an action event by moving the figure accordingly.
     * @param event = the event to handle
     */
    private void handleEvent(ActionEvent event) {
        switch (event) {
            case MOVE_LEFT:
                figure.move(-1, 0);
                break;
            case MOVE_RIGHT:
                figure.move(1, 0);
                break;
            case MOVE_DOWN:
                figure.move(0, -1);
                break;
            case ROTATE_LEFT:
                figure.rotate(-1);
                break;
            case ROTATE_RIGHT:
                figure.rotate(1);
                break;
            default:
                break;
        }
        updateGUI();
    }

    /**
     * Updates the graphical user interface according to the current state of the game.
     */
    public void updateGUI() {
        gui.clear();
        gui.drawBlocks(figure.getBlocks());
    }
}
