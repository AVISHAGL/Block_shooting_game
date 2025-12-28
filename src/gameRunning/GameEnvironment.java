/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package gameRunning;

import java.util.ArrayList;
import java.util.List;

import collections.Collidable;
import geomtry.Line;
import geomtry.Point;
import geomtry.Rectangle;

/**
 * The GameEnvironment class holds a collection of collidable objects
 * and provides methods to add collidables and find the closest collision.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;
    private final List<Collidable> collidablesToRemove;

    /**
     * Constructs a new GameEnvironment object with a given list of collidables.
     *
     * @param blocks the list of collidables to initialize the environment with
     */
    public GameEnvironment(List<Collidable> blocks) {
        this.collidables = blocks;
        this.collidablesToRemove = new ArrayList<>();
    }

    /**
     * Constructs a new, empty GameEnvironment object.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
        this.collidablesToRemove = new ArrayList<>();
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Marks the given collidable for removal from the environment.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidablesToRemove.add(c);
    }

    /**
     * Returns the list of collidables in the environment.
     *
     * @return the list of collidables
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the line representing the object's path
     * @return the information about the closest collision, or null if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closestCollision = null;
        double closestDistance = Double.MAX_VALUE;

        for (Collidable collidable : collidables) {
            Rectangle rect = collidable.getCollisionRectangle();
            Point closest = trajectory.closestIntersectionToStartOfLine(rect);
            if (closest == null) {
                continue;
            }
            double distance = trajectory.start().distance(closest);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestCollision = new CollisionInfo(closest, collidable);
            }
        }

        return closestCollision;
    }

    /**
     * Updates the collidables list by removing all marked collidables.
     */
    public void updateCollidables() {
        // Remove all marked collidables after the iteration
        this.collidables.removeAll(this.collidablesToRemove);
        this.collidablesToRemove.clear();
    }
}
