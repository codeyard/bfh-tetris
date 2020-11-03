/**
 * The class JFigure implements an J-shaped figure of the Tetris game.
 * Version: 1.0
 * Author: Raphael Gerber
 */

package tetris.model.figures;

import tetris.gui.Block;
import tetris.model.Figure;

public class JFigure extends Figure {
    private static final int COLOR = 2; /* The color of the figure. */

    /**
     * Creates an J-shaped figure.
     * @param x = the x coordinate of the initial position
     * @param y = the y coordinate of the initial position
     */
    public JFigure(int x, int y) {
        blocks[0] = new Block(x, y, COLOR);
        blocks[1] = new Block(x - 1, y, COLOR);
        blocks[2] = new Block(x + 1, y, COLOR);
        blocks[3] = new Block(x + 1, y - 1, COLOR);
    }

    /**
     * Rotates the figure.
     * @param d = the direction of the rotation (+1 right, -1 left)
     */
    @Override
    public void rotate(int d) {
        double cx = 0.5 * (blocks[0].x + blocks[3].x);
        double cy = 0.5 * (blocks[0].y + blocks[3].y);
        for (Block block : blocks) {
            double dx = block.x - cx;
            double dy = block.y - cy;
            block.x = (int) (cx + d * dy);
            block.y = (int) (cy - d * dx);
        }
    }
}
