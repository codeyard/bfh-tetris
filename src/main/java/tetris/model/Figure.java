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
        int xCenter = 0;
        int yCenter = 0;
        int xOld = 0;
        int yOld = 0;
        int xNew = 0;
        int yNew = 0;
        for (Block block : blocks) {
            xCenter += block.x;
            yCenter += block.y;
        }
        xCenter = xCenter / blocks.length;
        yCenter = yCenter  / blocks.length;

        for (Block block : blocks) {
            xOld = block.x - xCenter;
            yOld = block.y - yCenter;

            if (d > 0) {
                xNew = yOld;
                yNew = (xOld * (-1));
            } else {
                xNew = (yOld * (-1));
                yNew = xOld;
            }

            block.x = (int) Math.round(xCenter + (double) (xNew * d));
            block.y = (int) Math.round(yCenter + (double) (yNew * d));
        }
    }
}
