/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package collections;
import elements.Ball;
import geomtry.Point;
import geomtry.Rectangle;
import geomtry.Velocity;

/**
 * Interface representing collidable objects in the game.
 * Objects implementing this interface can be collided with.
 */
public interface Collidable {

    /**
     * Returns the "collision shape" of the object.
     *
     * @return the rectangle representing the collision shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the specified collision point
     * with the given velocity.
     * The object returns the new velocity expected after the hit, based on the force
     * the object inflicted on the hitter.
     *
     * @param hitter the ball that hit the object.
     * @param collisionPoint the point at which the collision occurred.
     * @param currentVelocity the velocity of the ball at the moment of collision.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
