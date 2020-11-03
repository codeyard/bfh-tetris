/**
 * The class IFigure implements an I-shaped figure of the Tetris game.
 * Version: 1.0
 * Author: Raphael Gerber
 */

package tetris.model.figures;

import tetris.gui.Block;
import tetris.model.Figure;

public class IFigure extends Figure {
    private static final int COLOR = 1; /* The color of the figure. */

    /**
     * Creates an I-shaped figure.
     * @param x = the x coordinate of the initial position
     * @param y = the y coordinate of the initial position
     */
    public IFigure(int x, int y) {
        blocks[0] = new Block(x, y, COLOR);
        blocks[1] = new Block(x - 1, y, COLOR);
        blocks[2] = new Block(x + 1, y, COLOR);
        blocks[3] = new Block(x + 2, y, COLOR);
    }
}
