/**
 * The class Field implements the playing field of a Tetris game.
 * Version: 1.0
 * Author: Raphael Gerber
 */

package tetris.model;

import tetris.gui.Block;

public class Field {
    private final int width; /* The width of the field. */
    private final int height; /* The height of the field. */

    /**
     * Constructs a field with the specified dimension.
     * @param width = the width of the field
     * @param height = the height of the field
     */
    public Field(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the width of the field.
     * @return the width of the field
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the field.
     * @return the height of the field
     */
    public int getHeight() {
        return height;
    }

    /**
     * Detects if the specified blocks collide with the border of the field.
     * @param blocks = the blocks to check for collision
     * @throws CollisionException if one of the blocks collides
     */
    public void detectCollision(Block[] blocks) throws CollisionException {
        for (Block block : blocks) {
            if (block.x < 0 || block.x > (getWidth() - 1)) {
                throw new CollisionException("Collision in X-dimension.");
            } else if (block.y < 0) {
                throw new CollisionException("Collision in Y-dimension.");
            }
        }
    }
}
