/**
 * The class Field implements the playing field of a Tetris game.
 * Version: 1.0
 * Author: Raphael Gerber
 */

package tetris.model;

import tetris.gui.Block;

import java.util.LinkedList;
import java.util.List;

public class Field {
    private final int width; /* The width of the field. */
    private final int height; /* The height of the field. */
    private final List<Block> placedBlocks = new LinkedList<>(); /* The blocks of the field. */

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
     * Gets the blocks of the field.
     */
    public List<Block> getBlocks() {
        return placedBlocks;
    }

    /**
     * Adds blocks to the field
     * @param blocks = the blocks to add
     */
    public void addBlocks(Block[] blocks) {
        for (Block block : blocks) {
            placedBlocks.add(block);
        }
    }

    /**
     * Removes all the blocks from the field.
     */
    public void removeAllBlocks() {
        placedBlocks.clear();
    }

    /**
     * Detects if the specified blocks collide with the border of the field.
     * @param blocks = the blocks to check for collision
     * @throws CollisionException if one of the blocks collides
     */
    public void detectCollision(Block[] blocks) throws CollisionException {
        for (Block block : blocks) {
            if (block.x < 0 || block.x >= getWidth() || block.y < 0) {
                throw new CollisionException("Collision with field border detected.");
            }
            for (Block placedBlock : placedBlocks) {
                if (placedBlock.x == block.x && placedBlock.y == block.y) {
                    throw new CollisionException("Collision with other Block detected.");
                }
            }
        }
    }
}
