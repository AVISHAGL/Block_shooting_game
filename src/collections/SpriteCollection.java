/**
 * ID 325742948
 * Name: Avishag Lavi.
 */
package collections;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection class represents a collection of Sprite objects.
 * It provides methods to add sprites, update all sprites, and draw all sprites.
 */
public class SpriteCollection {
    private final List<Sprite> sprites;
    private final List<Sprite> spritesToRemove;

    /**
     * Constructs a new SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
        this.spritesToRemove = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.spritesToRemove.add(s);
    }

    /**
     * Calls timePassed() on all sprites.
     * This method should be called once per frame to update the state of all sprites.
     */
    public void notifyAllTimePassed() {
        // Create a copy of the list to avoid ConcurrentModificationException
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
        // Remove all marked sprites after the iteration
        this.sprites.removeAll(this.spritesToRemove);
        this.spritesToRemove.clear();
    }

    /**
     * Calls drawOn(d) on all sprites.
     * This method should be called once per frame to draw all sprites on the given DrawSurface.
     *
     * @param d the DrawSurface to draw all sprites on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }
}
