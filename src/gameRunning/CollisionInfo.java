/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package gameRunning;

import collections.Collidable;
import geomtry.Point;

/**
 * The CollisionInfo class holds information about a collision event.
 * It includes the point at which the collision occurs and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a CollisionInfo object with the specified collision point and collidable object.
     *
     * @param collisionPoint the point at which the collision occurs
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
