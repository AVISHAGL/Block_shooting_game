/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package geomtry;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rectangle defined by its upper-left corner, width, and height.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;
    private final Line[] edges;

    /**
     * Constructs a new rectangle with the specified location, width, and height.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.edges = new Line[4];

        // Define the sides of the rectangle
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);

        this.edges[0] = new Line(upperLeft, upperRight); // Top side
        this.edges[1] = new Line(upperLeft, lowerLeft);  // Left side
        this.edges[2] = new Line(upperRight, lowerRight); // Right side
        this.edges[3] = new Line(lowerLeft, lowerRight); // Bottom side
    }

    /**
     * Returns a list of intersection points with the specified line.
     *
     * @param line the line to check for intersections
     * @return a list of intersection points (possibly empty)
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();

        for (Line edge : edges) {
            Point intersection = line.intersectionWith(edge);
            if (intersection != null) {
                intersections.add(intersection);
            }
        }

        return intersections;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the edges of the rectangle as an array of lines.
     * The edges are ordered as follows: top, right, bottom, left.
     *
     * @return an array of lines representing the edges of the rectangle
     */
    public Line[] getEdges() {
        return this.edges;
    }

}

