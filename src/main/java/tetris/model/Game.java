/**
 * The class Game implements the Tetris game.
 * Version: 1.0
 * Author: Raphael Gerber
 */

package tetris.model;

import tetris.gui.ActionHandler;
import tetris.gui.GUI;
import tetris.gui.Status;
import tetris.model.figures.*;

import java.util.Random;

public class Game {
    private final GUI gui; /* The graphical user interface. */
    private final Field field; /* The playing field */
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
        this.field = new Field(width, height);
    }

    /**
     *  Start the game by creating a figure and waiting for action events.
     */
    public void start() {
        createFigure();
        FigureController controller = new FigureController();
        gui.setActionHandler(controller);
    }

    /**
     * Stops the game by unregistering the action handler.
     */
    public void stop() {
        //figure = null;
        gui.setStatus(Status.OVER);
        gui.setActionHandler(null);
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

        try {
            field.detectCollision(figure.getBlocks());
            updateGUI();
        } catch (CollisionException ce) {
            stop();
        }
    }

    /**
     * Adds the current figure to the field and creates a new figure.
     */
    private void figureLanded() {
        field.addBlocks(figure.getBlocks());
        createFigure();
    }

    /**
     * Updates the graphical user interface according to the current state of the game.
     */
    public void updateGUI() {
        gui.clear();
        gui.drawBlocks(figure.getBlocks());
        gui.drawBlocks(field.getBlocks());
    }

    /**
     * The class FigureController is used to control the figure of the Tetris game.
     */
    private class FigureController implements ActionHandler {
        /* Moves the figure down. */
        @Override
        public void moveDown() {
            try {
                figure.move(0, -1);
                field.detectCollision(figure.getBlocks());
                updateGUI();
            } catch (CollisionException ce) {
                figure.move(0, 1);
                figureLanded();
            }
        }

        /* Moves the figure left. */
        @Override
        public void moveLeft() {
            try {
                figure.move(-1, 0);
                field.detectCollision(figure.getBlocks());
                updateGUI();
            } catch (CollisionException ce) {
                figure.move(1, 0);
            }
        }

        /* Moves the figure right. */
        @Override
        public void moveRight() {
            try {
                figure.move(1, 0);
                field.detectCollision(figure.getBlocks());
                updateGUI();
            } catch (CollisionException ce) {
                figure.move(-1, 0);
            }
        }

        /* Rotates the figure to the left. */
        @Override
        public void rotateLeft() {
            try {
                figure.rotate(-1);
                field.detectCollision(figure.getBlocks());
                updateGUI();
            } catch (CollisionException ce) {
                figure.rotate(1);
            }
        }

        /* Rotates the figure to the right. */
        @Override
        public void rotateRight() {
            try {
                figure.rotate(1);
                field.detectCollision(figure.getBlocks());
                updateGUI();
            } catch (CollisionException ce) {
                figure.rotate(-1);
            }
        }

        /* Drops the figure. */
        @Override
        public void drop() {
            boolean isDropped = false;
            while (!isDropped) {
                try {
                    figure.move(0, -1);
                    field.detectCollision(figure.getBlocks());
                    updateGUI();
                } catch (CollisionException ce) {
                    figure.move(0, 1);
                    figureLanded();
                    isDropped = true;
                }
            }
        }
    }
}
