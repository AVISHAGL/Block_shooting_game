/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package listeners;
import elements.Ball;
import elements.Block;

/**
 * The HitListener interface should be implemented by any class that wants to be notified of hit events.
 * It defines a single method, hitEvent, which is called whenever a block is hit by a ball.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
