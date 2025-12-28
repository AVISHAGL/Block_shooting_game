/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package collections;

import biuoop.DrawSurface;

/**
 * The Sprite interface represents a drawable object in the game.
 * Each sprite can be drawn on the screen and notified that time has passed.
 */
public interface Sprite {

    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the sprite on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is used to update the sprite's state.
     */
    void timePassed();
}
