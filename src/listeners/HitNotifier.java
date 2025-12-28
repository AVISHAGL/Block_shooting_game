/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package listeners;


/**
 * The HitNotifier interface should be implemented by any class that can notify listeners of hit events.
 * It defines methods for adding and removing HitListener instances.
 */
public interface HitNotifier {
    /**
     * Adds hl as a listener to hit events.
     *
     * @param hl the HitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Removes hl from the list of listeners to hit events.
     *
     * @param hl the HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}
