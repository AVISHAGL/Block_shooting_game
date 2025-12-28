/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package elements;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collections.Collidable;
import collections.Sprite;
import gameRunning.Game;
import geomtry.Point;
import geomtry.Rectangle;
import geomtry.Velocity;

import java.awt.Color;

/**
 * The Paddle class represents the player's paddle in the game, which is controlled using the keyboard.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRectangle;
    private final Color color;
    private final int speed;

    /**
     * Constructs a Paddle with the specified properties.
     *
     * @param rectangle the rectangle representing the paddle's shape and position
     * @param color     the color of the paddle
     * @param keyboard  the keyboard sensor to control the paddle
     */
    public Paddle(Rectangle rectangle, Color color, KeyboardSensor keyboard) {
        this.paddleRectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.speed = 10; // Adjust the speed of the paddle as needed
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        double newX = this.paddleRectangle.getUpperLeft().getX() - speed;
        if (newX <= 20) {
            newX = 780 - this.paddleRectangle.getWidth();
        }
        this.paddleRectangle = new Rectangle(new Point(newX, this.paddleRectangle.getUpperLeft().getY()),
                this.paddleRectangle.getWidth(), this.paddleRectangle.getHeight());
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        double newX = this.paddleRectangle.getUpperLeft().getX() + speed;
        if (newX + this.paddleRectangle.getWidth() >= 780) {
            newX = 20;
        }
        this.paddleRectangle = new Rectangle(new Point(newX, this.paddleRectangle.getUpperLeft().getY()),
                this.paddleRectangle.getWidth(), this.paddleRectangle.getHeight());
    }

    /**
     * Notifies the paddle that time has passed, checking for keyboard input to move the paddle.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the paddle on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) paddleRectangle.getUpperLeft().getX(),
                (int) paddleRectangle.getUpperLeft().getY(),
                (int) paddleRectangle.getWidth(),
                (int) paddleRectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) paddleRectangle.getUpperLeft().getX(),
                (int) paddleRectangle.getUpperLeft().getY(),
                (int) paddleRectangle.getWidth(),
                (int) paddleRectangle.getHeight());
    }

    /**
     * Returns the rectangle representing the paddle's shape.
     *
     * @return the rectangle representing the paddle's shape
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }

    /**
     * Handles the collision with the paddle and returns the new velocity of the ball.
     *
     * @param hitter the ball that hit the paddle
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double paddleWidth = this.paddleRectangle.getWidth();
        double collisionX = collisionPoint.getX() - this.paddleRectangle.getUpperLeft().getX();
        double region = paddleWidth / 5;
        double angle;

        if (collisionX < region) {
            angle = 210;
        } else if (collisionX < 2 * region) {
            angle = 240;
        } else if (collisionX < 3 * region) {
            angle = 270;
        } else if (collisionX < 4 * region) {
            angle = 300;
        } else {
            angle = 330;
        }

        double speed = Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                + currentVelocity.getDy() * currentVelocity.getDy());
        return Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * Adds the paddle to the given game.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
