/**
 * The class SFigure implements a S-shaped figure of the Tetris game.
 * Version: 1.0
 * Author: Raphael Gerber
 */

package tetris.model.figures;

import tetris.gui.Block;
import tetris.model.Figure;

public class SFigure extends Figure {
    private static final int COLOR = 5; /* The color of the figure. */

    /**
     * Creates a S-shaped figure.
     * @param x = the x coordinate of the initial position
     * @param y = the y coordinate of the initial position
     */
    public SFigure(int x, int y) {
        blocks[0] = new Block(x, y, COLOR);
        blocks[1] = new Block(x + 1, y, COLOR);
        blocks[2] = new Block(x, y - 1, COLOR);
        blocks[3] = new Block(x - 1, y - 1, COLOR);
    }
}
