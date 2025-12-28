/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package geomtry;

/**
 * Represents a point in a two-dimensional Cartesian coordinate system.
 * Each point has an x-coordinate and a y-coordinate.
 */
public class Point {
    private static final double EPSILON = 0.000001d;
    private double x; // The x-coordinate of the point
    private double y; // The y-coordinate of the point

    /**
     * Constructs a point with the specified x and y coordinates.
     *
     * @param x1 the x-coordinate of the point
     * @param y1 the y-coordinate of the point
     */
    public Point(final double x1, final double y1) {
        this.x = x1;
        this.y = y1;
    }

    /**
     * Checks if two double values are approximately equal, considering a small epsilon.
     *
     * @param n1 the first double value
     * @param n2 the second double value
     * @return {@code true} if the values are approximately equal, {@code false} otherwise
     */
    public boolean threshold(final double n1, final double n2) {
        return Math.abs(n1 - n2) < EPSILON;
    }

    /**
     * Checks if a point is null.
     *
     * @param p the point to check
     * @return {@code true} if the point is null, {@code false} otherwise
     */
    public boolean isNull(final Point p) {
        return p == null;
    }

    /**
     * Calculates and returns the distance between this point and another point.
     *
     * @param other the other point to which the distance is calculated
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Compares this point to another point for equality.
     * Two points are considered equal if they have the same x and y coordinates.
     *
     * @param other the point to compare with this point
     * @return {@code true} if the points are equal, {@code false} otherwise
     */
    public boolean equals(final Point other) {
        if (isNull(this) || isNull(other)) {
            return false;
        }
        return threshold(this.getX(), other.getX()) && threshold(this.getY(), other.getY());
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x the new x-coordinate to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this point.
     *
     * @param y the new y-coordinate to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * The main method to test the Point class.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        Line l1 = new Line(0, 0, 1, 1);
        Line l2 = new Line(1, 5, 2, 7);
        System.out.print(l1.isIntersecting(l2));
    }
}
