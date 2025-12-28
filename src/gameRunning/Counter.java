/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package gameRunning;

/**
 * The Counter class is used to keep track of a count value, which can be increased or decreased.
 */
public class Counter {
    private int counter;

    /**
     * Constructs a Counter with an initial count of zero.
     */
    public Counter() {
        counter = 0;
    }

    /**
     * Adds the specified number to the current count.
     *
     * @param number the number to add to the count
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * Subtracts the specified number from the current count.
     *
     * @param number the number to subtract from the count
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * Returns the current count value.
     *
     * @return the current count value
     */
    public int getValue() {
        return counter;
    }
}
