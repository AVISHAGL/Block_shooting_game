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
 * The BlockRemover class is responsible for removing blocks from the game when they are hit.
 * It also keeps track of the number of remaining blocks.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * Constructs a BlockRemover with the specified game and remaining blocks counter.
     *
     * @param game           the game from which blocks will be removed
     * @param remainingBlocks the counter that tracks the number of remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Returns the counter that tracks the number of remaining blocks.
     *
     * @return the counter for remaining blocks
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Handles the event of a block being hit.
     * Removes the block from the game, sets the ball color to the block's color,
     * decreases the remaining blocks counter,
     * and prints a message indicating the block was removed and the number of remaining blocks.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        hitter.setColor(beingHit.getColor());
        this.remainingBlocks.decrease(1);
        System.out.println("Block removed");
        System.out.println(this.remainingBlocks.getValue());
    }
}
