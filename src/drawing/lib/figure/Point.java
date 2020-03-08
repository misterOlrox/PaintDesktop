package drawing.lib.figure;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point source) {
        this.x = source.x;
        this.y = source.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCloseTo(Point other) {
        return Math.abs(getX() - other.getX()) <= 10
                && Math.abs(getY() - other.getY()) <= 10;
    }

    Point subtract(int xChange, int yChange) {
        x -= xChange;
        y -= yChange;

        return this;
    }

    void move(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public double distanceTo(Point point) {
        return Math.sqrt((x - point.x) * (x - point.x) + (y - point.y) * (y - point.y));
    }

    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }
}
