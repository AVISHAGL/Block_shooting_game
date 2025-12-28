/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package gameRunning;
import collections.Collidable;
import collections.Sprite;
import collections.SpriteCollection;
import elements.Ball;
import elements.Block;
import elements.Paddle;
import geomtry.Point;
import geomtry.Rectangle;
import geomtry.Velocity;
import listeners.ScoreIndicator;
import listeners.ScoreTrackingListener;
import removers.BallRemover;
import removers.BlockRemover;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Game class initializes and manages the game, including the game environment and sprite collection.
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private final BlockRemover blockRemover;
    private final BallRemover ballRemover;
    private final ScoreTrackingListener scoreTrackingListener;

    /**
     * Constructs a new Game, and creates the borders.
     */
    public Game() {
        this.blockRemover = new BlockRemover(this, new Counter());
        this.ballRemover = new BallRemover(this, new Counter());
        this.scoreTrackingListener = new ScoreTrackingListener(new Counter());
        this.sprites = new SpriteCollection();
        sprites.addSprite(new ScoreIndicator(this.scoreTrackingListener));
        this.environment = new GameEnvironment();
        int bWidth = 800;
        int bHeight = 600;
        this.gui = new GUI("Game", bWidth, bHeight);
        Block border1 = new Block(new Rectangle(new Point(0, 20), 800, 20), Color.gray);
        Block border2 = new Block(new Rectangle(new Point(0, 40), 20, bHeight - 2 * 20), Color.gray);
        Block border3 = new Block(new Rectangle(new Point(bWidth - 20, 40), 20, bHeight - 2 * 20), Color.gray);
        Block border4 = new Block(new Rectangle(new Point(0, bHeight), bWidth, 20), Color.WHITE);
        border1.addToGame(this);
        border2.addToGame(this);
        border3.addToGame(this);
        border4.addToGame(this);
        border4.addHitListener(this.ballRemover);
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the sprite collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes a new game: creates the blocks, ball, paddle, and adds them to the game.
     */
    public void initialize() {
        List<Block> blocks = createBlocks();
        createBallAndPaddle();
        for (Block b : blocks) {
            b.addHitListener(this.blockRemover);
            b.addHitListener(this.scoreTrackingListener);
        }
    }

    /**
     * Creates blocks and adds them to the game.
     *
     * @return a list of blocks created
     */
    private List<Block> createBlocks() {
        int blockWidth = 50;
        int blockHeight = 25;
        int rows = 6;
        int cols = 12;
        int startY = 100;
        int startX = 730;
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.magenta};
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Point upperLeft = new Point(startX - j * blockWidth, startY + i * blockHeight);
                Rectangle rect = new Rectangle(upperLeft, blockWidth, blockHeight);
                Block block = new Block(rect, colors[i % colors.length]);
                block.addToGame(this);
                blocks.add(block);
                this.blockRemover.getRemainingBlocks().increase(1);
            }
            cols--;
        }
        return blocks;
    }

    /**
     * Creates a paddle and balls, and adds them to the game.
     */
    private void createBallAndPaddle() {
        // Create and add balls
        Point ballCenter = new Point(400, 500);
        int ballRadius = 7;
        Ball ball1 = new Ball(ballCenter, ballRadius, Color.BLACK, environment);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(245, 4));
        ball1.addToGame(this);
        Ball ball2 = new Ball(ballCenter, ballRadius, Color.BLACK, environment);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(60, 4));
        ball2.addToGame(this);
        Ball ball3 = new Ball(ballCenter, ballRadius, Color.BLACK, environment);
        ball3.setVelocity(Velocity.fromAngleAndSpeed(100, 4));
        ball3.addToGame(this);
        this.ballRemover.getRemainingBalls().increase(3);

        // Create and add a paddle
        Point paddleUpperLeft = new Point(350, 560);
        int paddleWidth = 100;
        int paddleHeight = 20;
        Paddle paddle = new Paddle(new Rectangle(paddleUpperLeft, paddleWidth, paddleHeight), Color.BLACK,
                gui.getKeyboardSensor());
        paddle.addToGame(this);
    }

    /**
     * Runs the game by starting the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (this.blockRemover.getRemainingBlocks().getValue() > 0
                && this.ballRemover.getRemainingBalls().getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.lightGray);
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (this.blockRemover.getRemainingBlocks().getValue() == 0) {
            this.scoreTrackingListener.getCurrentScore().increase(100);
        }
        System.out.println(this.scoreTrackingListener.getCurrentScore().getValue());
        this.getEnvironment().updateCollidables();
        gui.close();
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
        System.out.println("Collidable removed");
    }

    /**
     * Removes a sprite from the sprite collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Returns the game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }
}
