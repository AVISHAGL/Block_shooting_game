/**
 * ID 325742948
 * Name: Avishag Lavi.
 */

package geomtry;

/**
 * Represents a line segment in a 2D space.
 */
public class Line {

    private static final double EPSILON = 0.000001d;
    private final Point start;
    private final Point end;

    /**
     * Creates a new line from two given points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(final Point start, final Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Creates a new line from four given coordinates.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(final double x1, final double y1, final double x2, final double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Checks if two double values are approximately equal, within a small threshold.
     *
     * @param n1 the first value
     * @param n2 the second value
     * @return true if the values are approximately equal, false otherwise
     */
    public boolean threshold(final double n1, final double n2) {
        return Math.abs(n1 - n2) < EPSILON;
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks if the line is vertical.
     *
     * @return true if the line is vertical, false otherwise
     */
    public boolean isVertical() {
        return threshold(this.start.getX(), this.end.getX());
    }

    /**
     * Checks if the line is horizontal.
     *
     * @return true if the line is horizontal, false otherwise
     */
    public boolean isHorizontal() {
        return threshold(this.start.getY(), this.end.getY());
    }

    /**
     * Checks if the line is actually a point.
     *
     * @return true if the line is a point, false otherwise
     */
    public boolean isPoint() {
        return threshold(this.end.getX(), this.start.getX()) && threshold(this.end.getY(), this.start.getY());
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other the other line to check for intersection.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(final Line other) {
        // Check if both lines are points
        if (this.isPoint() && other.isPoint()) {
            return this.start().equals(other.start());
        }

        // Check if this line is a point
        if (this.isPoint()) {
            return other.pointInLine(this.start);
        }

        // Check if other line is a point
        if (other.isPoint()) {
            return this.pointInLine(other.start);
        }

        // Check if either endpoint of one line is the same as an endpoint of the other line
        if (this.end().equals(other.end) || this.end().equals(other.start) || this.start().equals(other.start())
                || this.start().equals(other.end())) {
            return true;
        }

        // Check if both lines are vertical
        if (this.isVertical() && other.isVertical()) {
            if (!threshold(this.end.getX(), other.end.getX())) {
                return false;
            }
            return this.verticalOverlap(other);
        }

        // Checks if this line is vertical
        if (this.isVertical()) {
            return checkVerticalIntersection(this, other);
        }

        // Checks if the other line is vertical
        if (other.isVertical()) {
            return checkVerticalIntersection(other, this);
        }

        // Check if the lines intersect
        Equation a = new Equation(this);
        Equation b = new Equation(other);

        if (a.isEquationIntersect(b)) {
            Point intersection = a.intersectionPoint(b);
            return this.pointInLine(intersection) && other.pointInLine(intersection);
        }

        // Check again if either endpoint of one line is the same as an endpoint of the other line
        if (this.end().equals(other.end) || this.end().equals(other.start) || this.start().equals(other.start())
                || this.start().equals(other.end())) {
            return true;
        }

        // Check if both lines are horizontal
        if (this.isHorizontal() && other.isHorizontal()) {
            return this.horizontalOverlap(other);
        }

        // Check if this horizontal line intersects with the other line
        if (this.isHorizontal()) {
            boolean a1 = this.end.getY() <= other.end().getY() && this.end.getY() >= other.start().getY();
            boolean b1 = this.end.getY() >= other.end().getY() && this.end.getY() <= other.start().getY();
            if (a1 || b1) {
                Equation e = new Equation(other);
                boolean a2 = this.start.getX() <= e.findX(this.end().getY()) && this.end.getX()
                        >= e.findX(this.end().getY());
                boolean b2 = this.start.getX() >= e.findX(this.end().getY()) && this.end.getX()
                        <= e.findX(this.end().getY());
                return (a2 || b2);
            }
            return false;
        }

        // Check if the other horizontal line intersects with this line
        if (other.isHorizontal()) {
            boolean a3 = other.end.getY() <= this.end().getY() && other.end.getY() >= this.start().getY();
            boolean b3 = other.end.getY() >= this.end().getY() && other.end.getY() <= this.start().getY();
            if (a3 || b3) {
                Equation e = new Equation(this);
                boolean a4 = other.start.getX() <= e.findX(other.end().getY()) && other.end.getX()
                        >= e.findX(other.end().getY());
                boolean b4 = other.start.getX() >= e.findX(other.end().getY()) && other.end.getX()
                        <= e.findX(other.end().getY());
                return (a4 || b4);
            }
            return false;
        }

        return false;
    }


    /**
     * Checks if this line overlaps with another line in the vertical direction.
     *
     * @param other the other line to check for overlap
     * @return true if the lines overlap vertically, false otherwise
     */
    private boolean verticalOverlap(Line other) {
        return (this.start().getY() <= other.end().getY() && this.end().getY() >= other.start().getY())
                || (other.start().getY() <= this.end().getY() && other.end().getY() >= this.start().getY());
    }

    /**
     * Checks if this line overlaps with another line in the horizontal direction.
     *
     * @param other the other line to check for overlap
     * @return true if the lines overlap horizontally, false otherwise
     */
    private boolean horizontalOverlap(Line other) {
        return (this.start().getX() <= other.end().getX() && this.end().getX() >= other.start().getX())
                || (other.start().getX() <= this.end().getX() && other.end().getX() >= this.start().getX());
    }

    /**
     * Checks if this line overlaps with another line.
     *
     * @param other the other line to check for overlap
     * @return true if the lines overlap, false otherwise
     */
    private boolean overlapping(Line other) {
        if (this.isVertical()) {
            return this.verticalOverlap(other);
        }
        if (this.isHorizontal()) {
            return this.horizontalOverlap(other);
        }
        Equation a = new Equation(this);
        Equation b = new Equation(other);
        if (threshold(a.getM(), b.getM())) {
            if (!threshold(a.getB(), b.getB())) {
                return false;
            }
            if (this.equals(other)) {
                return true;
            }
            if (this.end().equals(other.end) || this.end().equals(other.start) || this.start().equals(other.start())
                    || this.start().equals(other.end())) {
                return false;
            }
            return this.pointInLine(other.start) || this.pointInLine(other.end);
        }
        return false;
    }

    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 the first other line to check for intersection
     * @param other2 the second other line to check for intersection
     * @return true if this line intersects with both other lines, false otherwise
     */
    public boolean isIntersecting(final Line other1, final Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other the other line to find the intersection with
     * @return the intersection point if the lines intersect, and null otherwise
     */
    public Point intersectionWith(final Line other) {
        // Calculate the determinant to check for parallel lines
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        double denominator = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if (denominator == 0) {
            return null; // Lines are parallel or coincident
        }

        // Calculate the intersection point
        double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denominator;
        double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denominator;

        // Check if intersection point is within the segments
        if (ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1) {
            double x = x1 + ua * (x2 - x1);
            double y = y1 + ua * (y2 - y1);
            return new Point(x, y);
        }

        return null; // Intersection point is outside the segments
    }


    /**
     * Checks if this line is equal to another line.
     *
     * @param other the other line to compare to
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(final Line other) {
        return ((this.start.equals(other.start) && this.end.equals(other.end)) || (this.start.equals(other.end)
                && this.end.equals(other.start)));
    }

    /**
     * Checks if a point is on this line.
     *
     * @param p the point to check
     * @return true if the point is on the line, false otherwise
     */
    public boolean pointInLine(final Point p) {
        Equation e = new Equation(this);
        boolean t = threshold(e.findY(p.getX()), p.getY());
        boolean q = e.isPointInEquation(p);

        double minX = Math.min(this.start.getX(), this.end.getX());
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minY = Math.min(this.start.getY(), this.end.getY());
        double maxY = Math.max(this.start.getY(), this.end.getY());

        boolean isWithinXRange = p.getX() >= minX && p.getX() <= maxX;
        boolean isWithinYRange = p.getY() >= minY && p.getY() <= maxY;

        return (isWithinXRange && isWithinYRange && q && t);
    }

    /**
     * Checks if a vertical line intersects with another line.
     *
     * @param verticalLine the vertical line to check for intersection
     * @param other        the other line to check for intersection
     * @return true if the vertical line intersects with the other line, false otherwise
     */
    private boolean checkVerticalIntersection(Line verticalLine, Line other) {
        Equation e = new Equation(other);
        boolean outsideXRange = (verticalLine.end.getX() > other.end.getX() && verticalLine.end.getX()
                > other.start.getX())
                || (verticalLine.end.getX() < other.end.getX() && verticalLine.end.getX() < other.start.getX());
        if (outsideXRange) {
            return false;
        }
        double yAtVerticalX = e.findY(verticalLine.end.getX());
        return (verticalLine.start.getY() <= yAtVerticalX && verticalLine.end.getY() >= yAtVerticalX)
                || (verticalLine.start.getY() >= yAtVerticalX && verticalLine.end.getY() <= yAtVerticalX);
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect the rectangle to check for intersection
     * @return the closest intersection point to the start of the line, or null if no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closestPoint = null;
        double minDistance = Double.MAX_VALUE;

        for (Line edge : rect.getEdges()) {
            Point intersection = this.intersectionWith(edge);
            if (intersection != null) {
                double distance = this.start.distance(intersection);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPoint = intersection;
                }
            }
        }

        return closestPoint;
    }
}