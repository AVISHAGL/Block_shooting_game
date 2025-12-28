/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package removers;
import elements.Ball;
import elements.Block;
import gameRunning.Counter;
import gameRunning.Game;
import listeners.HitListener;

/**
 * BallRemover is a HitListener that removes balls from the game and decreases the ball counter.
 * It listens for hit events and acts accordingly.
 *
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * Constructs a BallRemover with the specified game and counter for remaining balls.
     *
     * @param game the game from which balls will be removed
     * @param remainingBalls the counter for the remaining balls in the game
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Gets the counter for the remaining balls.
     *
     * @return the counter for the remaining balls
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * Handles the hit event when a ball hits a block.
     * Removes the ball from the game and decreases the counter of remaining balls.
     *
     * @param beingHit the block that is being hit
     * @param hitter the ball that hits the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
        System.out.println("Block removed");
        System.out.println(this.remainingBalls.getValue());
    }
}
