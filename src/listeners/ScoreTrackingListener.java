/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package listeners;
import elements.Ball;
import elements.Block;
import gameRunning.Counter;

/**
 * The ScoreTrackingListener class implements the HitListener interface.
 * It tracks and updates the score whenever a hit event occurs.
 */
public class ScoreTrackingListener implements HitListener {

    private final Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener with a Counter for tracking the score.
     * @param scoreCounter the Counter object to track the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Gets the current score Counter.
     * @return the current score Counter
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }

    /**
     * Updates the score by increasing it when a hit event occurs.
     * @param beingHit the Block that was hit
     * @param hitter the Ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5); // Increase score by 5 points on hit
        System.out.println("Score: " + this.currentScore.getValue());
    }
}
