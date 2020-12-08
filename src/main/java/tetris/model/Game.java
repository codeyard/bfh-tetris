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
    private Scoring scoring; /* The scoring of the game. */
    private Game.FigureController figureController; /* The figure controller. */

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
        this.scoring = new Scoring();
        this.figureController = new FigureController();
    }

    /**
     *  Start the game by creating a figure and waiting for action events.
     */
    public void start() {
        createFigure();
        gui.setActionHandler(figureController);
        figureController.start();
    }

    /**
     * Stops the game by unregistering the action handler.
     */
    public void stop() {
        figureController.interrupt();
        gui.setStatus(Status.OVER);
        gui.setActionHandler(null);
        figure = null;
        scoring.updateHighScore();
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
        int removedRows = field.removeFullRows();
        scoring.updateScore(removedRows);
        createFigure();
    }

    /**
     * Updates the graphical user interface according to the current state of the game.
     */
    public void updateGUI() {
        gui.clear();
        gui.drawBlocks(field.getBlocks());
        if (figure != null) {
            gui.drawBlocks(figure.getBlocks());
        }
        gui.setLevel(scoring.getLevel());
        gui.setScore(scoring.getScore());
        gui.setHighScore(scoring.getHighScore());
    }

    /**
     * The class FigureController is used to control the figure of the Tetris game.
     */
    private class FigureController extends Thread implements ActionHandler {
        /* Moves the figure down. */
        @Override
        public synchronized void moveDown() {
            if (!execute(fig -> fig.move(0, -1), fig -> fig.move(0, 1))) {
                figureLanded();
            }
        }

        /* Moves the figure left. */
        @Override
        public synchronized void moveLeft() {
            execute(fig -> fig.move(-1, 0), fig-> fig.move(1, 0));
        }

        /* Moves the figure right. */
        @Override
        public synchronized void moveRight() {
            execute(fig -> fig.move(1, 0), fig-> fig.move(-1, 0));
        }

        /* Rotates the figure to the left. */
        @Override
        public synchronized void rotateLeft() {
            execute(fig -> fig.rotate(-1), fig-> fig.rotate(1));
        }

        /* Rotates the figure to the right. */
        @Override
        public synchronized void rotateRight() {
            execute(fig -> fig.rotate(1), fig-> fig.rotate(-1));
        }

        /**
         * Drops the figure.
         * Does not need figureLanded() thanks to the Thread. If we had figureLanded(), it would not be possible to
         * move the figure after dropping
         */
        @Override
        public synchronized void drop() {
            while (execute(fig -> fig.move(0, -1), fig -> fig.move(0, 1)))  {
            }
            //figureLanded();
        }

        /**
         * Makes a movement with the figure; if the resulting figure collides with the field, makes the reverse movement.
         * @param movement = the movement to make
         * @param reverseMovement = the reverse movement
         * @return true, if the movement was successful, false otherwise
         */
        private boolean execute(Movement movement, Movement reverseMovement) {
            try {
                movement.make(figure);
                field.detectCollision(figure.getBlocks());
                updateGUI();
                return true;
            } catch (CollisionException ce) {
                reverseMovement.make(figure);
                return false;
            }
        }

        /**
         * Moves the figure downwards with a speed depending on the level of the game.
         */
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    //Thread.sleep(1000 - (scoring.getLevel() * 100));
                    Thread.sleep((long) (1000 * Math.exp(-0.1 * scoring.getLevel())));
                    moveDown();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
