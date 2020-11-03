/**
 * The exception CollisionException is thrown if a figure collides with the border of the playing field.
 * Version: 1.0
 * Author: Raphael Gerber
 */

package tetris.model;

public class CollisionException extends Exception {

    /**
     * Constructs a collision exception.
     * @param message = the error message
     */
    public CollisionException(String message) {
        super(message);
    }
}
