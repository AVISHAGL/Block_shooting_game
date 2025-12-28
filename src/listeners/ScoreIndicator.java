/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package listeners;


import biuoop.DrawSurface;
import collections.Sprite;

import java.awt.Color;

/**
 * The ScoreIndicator class implements the Sprite interface.
 * It displays the current score on the screen.
 */
public class ScoreIndicator implements Sprite {

    private final ScoreTrackingListener scoreTracker;

    /**
     * Constructs a ScoreIndicator with a ScoreTrackingListener.
     * @param scoreTracker the ScoreTrackingListener to track the score
     */
    public ScoreIndicator(ScoreTrackingListener scoreTracker) {
        this.scoreTracker = scoreTracker;
    }

    /**
     * Draws the score indicator on the DrawSurface.
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), 20);
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 2 - 40, 15, "Score: " + scoreTracker.getCurrentScore().getValue(), 15);
    }

    /**
     * This method does nothing for the score indicator, as it doesn't change over time.
     */
    @Override
    public void timePassed() {
        // Score indicator does not change over time
    }
}
