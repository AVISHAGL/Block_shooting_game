/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package elements;
import collections.Collidable;
import collections.Sprite;
import gameRunning.Game;
import geomtry.Point;
import geomtry.Rectangle;
import geomtry.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a block in the game.
 * A block is a collidable and drawable object with a specific shape and color.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private final Rectangle rectangle;
    private final Color color;
    private static final double EPSILON = 0.000001d;
    private final List<HitListener> hitListeners;

    /**
     * Constructs a block with the specified rectangle and color.
     *
     * @param rectangle the rectangle representing the block's shape and position
     * @param color     the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Returns the collision rectangle of the block.
     * This is the shape and position of the block used for collision detection.
     *
     * @return the rectangle representing the block's collision shape
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Returns the color of the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Checks if two double values are approximately equal, within a small threshold.
     *
     * @param n1 the first value
     * @param n2 the second value
     * @return true if the values are approximately equal, false otherwise
     */
    public boolean threshold(final double n1, final double n2) {
        return Math.abs(n1 - n2) < EPSILON;
    }

    /**
     * Handles the event of a ball hitting the block.
     * Changes the velocity of the ball based on the collision point and notifies hit listeners.
     *
     * @param hitter          the ball that hit the block
     * @param collisionPoint  the point at which the ball collided with the block
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (threshold(collisionPoint.getX(), rectangle.getUpperLeft().getX())
                || threshold(collisionPoint.getX(), rectangle.getUpperLeft().getX() + rectangle.getWidth())) {
            dx = -dx; // Reverse horizontal velocity
        }
        if (threshold(collisionPoint.getY(), rectangle.getUpperLeft().getY())
                || threshold(collisionPoint.getY(), rectangle.getUpperLeft().getY() + rectangle.getHeight())) {
            dy = -dy; // Reverse vertical velocity
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }

        return new Velocity(dx, dy);
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the block on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * Notifies the block that time has passed.
     * This method is required by the Sprite interface but does not perform any action for blocks.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Adds the block to the specified game as a collidable and sprite object.
     *
     * @param g the game to add the block to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Checks if the color of the ball matches the color of the block.
     *
     * @param b the ball to check
     * @return true if the colors match, false otherwise
     */
    public boolean ballColorMatch(Ball b) {
        return b.getColor() == this.color;
    }

    /**
     * Removes the block from the specified game.
     *
     * @param game the game to remove the block from
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Adds a hit listener to the block.
     *
     * @param hl the hit listener to add
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the block.
     *
     * @param hl the hit listener to remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all hit listeners that the block was hit by a ball.
     *
     * @param hitter the ball that hit the block
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
