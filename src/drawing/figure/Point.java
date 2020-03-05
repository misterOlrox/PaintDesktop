package drawing.figure;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isPointsEqual(Point other) {
        return Math.abs(getX() - other.getX()) <= 10
                && Math.abs(getY() - other.getY()) <= 10;
    }
}
