/**
 * The class Figure implements a figure of the Tetris game.
 * Version: 1.0
 * Author: Raphael Gerber
 */

package tetris.model;

import tetris.gui.Block;

public abstract class Figure {
    /**
     * The blocks of the figure.
     */
    protected final Block[] blocks = new Block[4];

    /**
     * Gets the blocks of the figure.
     * @return the blocks of the figure
     */
    public Block[] getBlocks() {
        return blocks;
    }

    /**
     * Moves the figure in the specified direction.
     * @param dx = the x coordinate of the direction
     * @param dy = the y coordinate of the direction
     */
    public void move(int dx, int dy) {
        for (Block block : blocks) {
            block.x += dx;
            block.y += dy;
        }
    }

    /**
     * Rotates the figure.
     * @param d = the direction of the rotation (+1 right, -1 left)
     */
    public void rotate(int d) {
        int cx = blocks[0].x;
        int cy = blocks[0].y;
        for (Block block : blocks) {
            int dx = block.x - cx;
            int dy = block.y - cy;
            block.x = cx + d * dy;
            block.y = cy - d * dx;
        }
    }
}
