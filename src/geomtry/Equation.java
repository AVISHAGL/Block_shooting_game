/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package geomtry;

/**
 * The Equation class represents a linear equation in the form y = mx + b.
 * It provides methods to calculate the slope (m) and y-intercept (b),
 * as well as to perform various operations with the equation.
 */
public class Equation {
    private static final double EPSILON = 0.000001d;
    private double m;
    private double b;

    /**
     * Constructs an Equation object based on the given line.
     *
     * @param l the line from which the equation is derived.
     */
    public Equation(final Line l) {
        if (threshold(l.start().getX(), l.end().getX())) {
            this.m = Double.POSITIVE_INFINITY;
            this.b = 0;
        }
        this.m = (l.start().getY() - l.end().getY()) / (l.start().getX() - l.end().getX());
        this.b = l.start().getY() - (this.m * l.start().getX());
    }

    /**
     * Checks if the difference between two values is less than a threshold (EPSILON).
     *
     * @param n1 the first value.
     * @param n2 the second value.
     * @return true if the difference is less than EPSILON, false otherwise.
     */
    public boolean threshold(final double n1, final double n2) {
        return Math.abs(n1 - n2) < EPSILON;
    }

    /**
     * Gets the slope (m) of the equation.
     *
     * @return the slope.
     */
    public double getM() {
        return this.m;
    }

    /**
     * Gets the y-intercept (b) of the equation.
     *
     * @return the y-intercept.
     */
    public double getB() {
        return this.b;
    }

    /**
     * Finds the x-coordinate corresponding to a given y-coordinate in the equation.
     *
     * @param y the y-coordinate.
     * @return the corresponding x-coordinate.
     */
    public double findX(final double y) {
        return (y - this.getB()) / this.getM();
    }

    /**
     * Finds the y-coordinate corresponding to a given x-coordinate in the equation.
     *
     * @param x the x-coordinate.
     * @return the corresponding y-coordinate.
     */
    public double findY(final double x) {
        return (this.getM() * x + this.getB());
    }

    /**
     * Checks if this equation is equal to another equation.
     *
     * @param e the other equation to compare.
     * @return true if both equations have the same slope and y-intercept, false otherwise.
     */
    public boolean isEqual(final Equation e) {
        return threshold(this.getM(), e.getM()) && threshold(this.getB(), e.getB());
    }

    /**
     * Checks if a given point lies on the equation.
     *
     * @param p the point to check.
     * @return true if the point lies on the equation, false otherwise.
     */
    public boolean isPointInEquation(final Point p) {
        return threshold(this.findY(p.getX()), p.getY());
    }

    /**
     * Checks if the equation represents a vertical line.
     *
     * @return true if the equation is vertical, false otherwise.
     */
    public boolean isVertical() {
        return threshold(this.getM(), Double.POSITIVE_INFINITY);
    }

    /**
     * Checks if the equation represents a horizontal line.
     *
     * @return true if the equation is horizontal, false otherwise.
     */
    public boolean isHorizontal() {
        return threshold(this.getM(), 0);
    }

    /**
     * Checks if this equation intersects with another equation.
     *
     * @param a the other equation to check for intersection.
     * @return true if the equations intersect, false otherwise.
     */
    public boolean isEquationIntersect(final Equation a) {
        if (threshold(this.getM(), a.getM())) {
            return threshold(this.getB(), a.getB());
        }
        if (this.isEqual(a)) {
            return true;
        }

        if (this.isVertical()) {
            return threshold(this.findX(0), a.findX(0));
        }
        if (this.isHorizontal()) {
            return threshold(this.findY(0), a.findY(0));
        }
        return true;
    }

    /**
     * Finds the intersection point of this equation with another equation.
     *
     * @param other the other equation.
     * @return the intersection point, or null if the equations do not intersect.
     */
    public Point intersectionPoint(final Equation other) {
        double x = (this.getB() - other.getB()) / (other.getM() - this.getM());
        double y = this.findY(x);
        return new Point(x, y);
    }
}
