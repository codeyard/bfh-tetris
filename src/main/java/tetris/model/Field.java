/**
 * The class Field implements the playing field of a Tetris game.
 * Version: 1.0
 * Author: Raphael Gerber
 */

package tetris.model;

import tetris.gui.Block;

import java.util.Iterator;
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

    /**
     * Removes the full rows from the field.
     * @return the number of removed rows
     */
    public int removeFullRows() {
        int removedRows = 0;
        for (int i = getHeight() - 1; i >= 0; i--) {
            if (isRowFull(i)) {
                removeRow(i);
                removedRows++;
            }
        }
        return removedRows;
    }

    /**
     * Checks if a row is full of blocks.
     * @param y = the y-coordinate of the row
     * @return true if the row is full, false otherwise
     */
    private boolean isRowFull(int y) {
        int numOfBlocksInRow = 0;
        for (Block block : placedBlocks) {
            if (block.y == y) {
                numOfBlocksInRow++;
            }
        }
        return numOfBlocksInRow == getWidth();
    }

    /**
     * Removes the blocks of a row and moves the blocks of the upper rows down.
     * @param y = the y-coordinate of the row
     */
    private void removeRow(int y) {
        /*for (Block block : placedBlocks) {
            if (block.y == y) {
                placedBlocks.remove(block);
            }
        }*/
        Iterator<Block> iter = placedBlocks.iterator();
        while (iter.hasNext()) {
            Block block = iter.next();
            if (block.y == y) {
                iter.remove();
            } else if (block.y > y) {
                block.y--;
            }
        }
    }
}
