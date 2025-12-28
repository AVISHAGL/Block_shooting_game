/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package elements;

import biuoop.DrawSurface;
import collections.Sprite;
import gameRunning.CollisionInfo;
import gameRunning.Game;
import gameRunning.GameEnvironment;
import geomtry.Line;
import geomtry.Point;
import geomtry.Velocity;

import java.awt.Color;

/**
 * The Ball class represents a ball with a center point, radius, color, and velocity.
 * It can move, detect collisions with borders, and draw itself on a DrawSurface.
 */
public class Ball implements Sprite {
    private Point center;
    private final int radius;
    private Color color;
    private Velocity velocity;
    private final GameEnvironment g;

    /**
     * Constructs a Ball with the specified center coordinates, radius, color, and game environment.
     *
     * @param x      the x-coordinate of the ball's center
     * @param y      the y-coordinate of the ball's center
     * @param radius the radius of the ball
     * @param color  the color of the ball
     * @param g      the game environment the ball interacts with
     */
    public Ball(double x, double y, int radius, Color color, GameEnvironment g) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = color;
        this.g = g;
    }

    /**
     * Constructs a Ball with the specified center point, radius, color, and game environment.
     *
     * @param center the center point of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     * @param g      the game environment the ball interacts with
     */
    public Ball(Point center, int radius, Color color, GameEnvironment g) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.g = g;
    }

    /**
     * Calculates and returns the line representing the ball's movement.
     *
     * @return the line representing the ball's movement
     */
    public Line getMovementLine() {
        double magnitude = Math.sqrt(this.getVelocity().getDx() * this.getVelocity().getDx()
                + this.getVelocity().getDy() * this.getVelocity().getDy());
        double unitDx = this.getVelocity().getDx() * 2 / magnitude;
        double unitDy = this.getVelocity().getDy() * 2 / magnitude;

        double halfLength = this.getRadius();

        double startX = this.getCenter().getX() - unitDx * halfLength;
        double startY = this.getCenter().getY() - unitDy * halfLength;
        double endX = this.getCenter().getX() + unitDx * halfLength;
        double endY = this.getCenter().getY() + unitDy * halfLength;

        return new Line(startX, startY, endX, endY);
    }

    /**
     * Gets the x-coordinate of the ball's center.
     *
     * @return the x-coordinate of the ball's center
     */
    public int getX() {
        return (int) this.getCenter().getX();
    }

    /**
     * Gets the y-coordinate of the ball's center.
     *
     * @return the y-coordinate of the ball's center
     */
    public int getY() {
        return (int) this.getCenter().getY();
    }

    /**
     * Gets the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Gets the center point of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the new color of the ball
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param velocity the new velocity
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx the x component of the velocity
     * @param dy the y component of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the speed of the ball based on its radius.
     *
     * @return the speed of the ball
     */
    public int setSpeed() {
        int speed = 5;
        if (this.radius <= 50) {
            speed = 10 - this.radius % 10;
        }
        return speed;
    }

    /**
     * Moves the ball one step, checking for collisions.
     * Changes the direction of the ball based on the side it intersects with.
     */
    public void moveOneStep() {
        CollisionInfo p = this.g.getClosestCollision(this.getMovementLine());
        if (p != null) {
            this.setVelocity(p.collisionObject().hit(this, p.collisionPoint(), this.velocity));
        }
        this.center = this.velocity.applyToPoint(this.getCenter());
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the ball on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * Updates the ball's position according to its velocity.
     * This method is called once per frame.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Adds this ball to the game.
     *
     * @param g the game to add the ball to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Removes this ball from the game.
     *
     * @param g the game to remove the ball from
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}
