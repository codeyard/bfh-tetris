package tetris.model;

import tetris.gui.Block;

public class Figure {
    /**
     * The blocks of the fiugre.
     */
    private final Block[] blocks = new Block[4];

    /**
     * The color of the figure.
     */
    private static final int COLOR = 1;

    /**
     * Creates a figure.
     * @param x = the x coordinate of the initial position
     * @param y = the y coordinate of the initial position
     */
    public Figure(int x, int y) {
        blocks[0] = new Block(x, y, COLOR);
        blocks[1] = new Block(x - 1, y, COLOR);
        blocks[2] = new Block(x + 1, y, COLOR);
        blocks[3] = new Block(x + 2, y, COLOR);
    }

    /**
     * Gets the blocks of the figure.
     * @return
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
        switch (d) {
            case 1:

                break;
            case -1:

                break;
            default:
                break;
        }
    }
}
