/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package geomtry;

/**
 * Represents a velocity with components dx and dy.
 * Velocity is used to specify the change in position of an object along the x and y axes.
 */
public class Velocity {
    private double dx; // The change in position along the x-axis
    private double dy; // The change in position along the y-axis

    /**
     * Constructs a Velocity with the specified change in position along the x and y axes.
     *
     * @param dx the change in position along the x-axis
     * @param dy the change in position along the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a new Velocity object from an angle and a speed.
     * The angle is specified in degrees, and the speed determines the magnitude of the velocity.
     *
     * @param angle the angle in degrees
     * @param speed the speed
     * @return a new Velocity object with the specified angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Takes a point with position (x, y) and returns a new point with position (x + dx, y + dy).
     * The new position is calculated by applying the velocity to the original position.
     *
     * @param p the original point
     * @return a new point with the updated position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Returns the change in position along the x-axis.
     *
     * @return the change in position along the x-axis
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the change in position along the y-axis.
     *
     * @return the change in position along the y-axis
     */
    public double getDy() {
        return dy;
    }
}
