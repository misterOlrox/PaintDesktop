package drawing.lib.figure;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Polygon extends Figure2D {

    private ArrayList<Point> points;

    protected Polygon() {
        super();
    }

    public Polygon(Point refPoint, Color lineColor, Color fillingColor, ArrayList<Point> points) {
        super(refPoint, lineColor, fillingColor);
        this.points = points;
    }

    @Override
    public void draw(Graphics graphics) {
        int[] pointsX = new int[points.size()];
        int[] pointsY = new int[points.size()];

        if (points.size() == 0) {
            graphics.setColor(getLineColor());
            graphics.drawLine(
                    getLocation().getX(), getLocation().getY(),
                    getLocation().getX() + 1, getLocation().getY() + 1
            );
            return;
        }

        Point current = getLocation();
        for(int i = 0; i < points.size() - 1; i++) {
            Point next = points.get(i);
            pointsX[i] = current.getX();
            pointsX[i+1] = next.getX();
            pointsY[i] = current.getY();
            pointsY[i+1] = next.getY();
            current = next;
        }

        graphics.setColor(getFillingColor());
        graphics.fillPolygon(pointsX, pointsY, points.size());
        graphics.setColor(getLineColor());
        graphics.drawPolygon(pointsX, pointsY, points.size());
    }

    public void move(Point point) {
        int xChange = getLocation().getX() - point.getX();
        int yChange = getLocation().getY() - point.getY();
        setRefPoint(point);

        for(Point figurePoints : points) {
            figurePoints.subtract(xChange, yChange);
        }
    }

    public ArrayList<Point> getPoints() {
        return new ArrayList<>(points);
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public static class Builder extends Figure2D.Builder {
        private ArrayList<Point> points = new ArrayList<>();

        public Builder() {
        }

        @Override
        public void addPoint(Point point) {
            points.add(point);
        }

        @Override
        public boolean needsMorePoints() {
            return true;
        }

        @Override
        public boolean isReadyForBuild() {
            return super.isReadyForBuild()
                    && (points.isEmpty()
                        || getRefPoint().isCloseTo(points.get(points.size() - 1)));
        }

        @Override
        public Figure build() {

            return new Polygon(
                    getRefPoint(),
                    getLineColor(),
                    getFillingColor(),
                    points
            );
        }

        public ArrayList<Point> getPoints() {
            return points;
        }
    }
}