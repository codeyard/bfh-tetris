/**
 * The class Score implements the scoring of the Tetris game.
 */

package tetris.model;

import java.io.*;
import java.util.Scanner;

public class Scoring {
    private static final int[] SCORE_REWARDS = new int[] {40, 100, 300, 1200}; /* The score rewards for the removal of full rows. */
    private static final int LINES_PER_LEVEL = 10; /* The number of lines needed to enter the next level. */
    private static final String HIGH_SCORE_FILE = "highscore.txt"; /* The name of the high score file. */
    private int removedRows = 0; /* The total number of removed rows. */
    private int score = 0; /* The current score of the game. */
    private int highScore = 0; /* The high score of the game. */

    /**
     * Constructs a scoring and reads the high score file.
     */
    public Scoring() {
        loadHighScore();
    }

    /**
     * Gets the current level of the game.
     * @return the current level of the game
     */
    public int getLevel() {
        return (removedRows / LINES_PER_LEVEL) + 1;
    }

    /**
     * Gets the current score of the game.
     * @return the current score of the game
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the high score of the game.
     * @return the high score of the game
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Updates the score according to the number of removed rows.
     * @param nrows: the number of removed rows
     */
    public void updateScore(int nrows) {
        int points = 0;
        if (nrows > 0 && nrows <= SCORE_REWARDS.length) {
            points = SCORE_REWARDS[nrows - 1];
        }
        score += points;
        removedRows += nrows;
    }

    /**
     * Updates the high score according to the current score.
     */
    public void updateHighScore() {
        if (score > highScore) {
            highScore = score;
            saveHighScore();
        }
    }

    /**
     * Resets the level and the score of the game.
     */
    public void reset() {
        removedRows = 0;
        score = 0;
    }

    /**
     * Loads the high score from the high score file.
     */
    private void loadHighScore() {
        try (Scanner scanner = new Scanner(new FileInputStream(HIGH_SCORE_FILE))) {
            highScore = Integer.parseInt(scanner.nextLine());
            if (highScore < 0) {
                highScore = 0;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("High score could not be read.");
        }
    }

    /**
     * Saves the high score to the high score file.
     */
    private void saveHighScore() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(HIGH_SCORE_FILE))) {
            writer.println(highScore);
        } catch (IOException e) {
            System.err.println("High score could not be written.");
        }
    }
}
