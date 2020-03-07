package drawing.figure;

import java.util.ArrayList;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Polygon extends Figure2D {

    private ArrayList<Point> points;

    protected Polygon() {
        super();
    }

    public void draw() {
        int[] pointsX = new int[points.size()];
        int[] pointsY = new int[points.size()];

        if (points.size() == 0) {
            getGraphics().setColor(getLineColor());
            getGraphics().drawLine(
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

        getGraphics().setColor(getFillingColor());
        getGraphics().fillPolygon(pointsX, pointsY, points.size());
        getGraphics().setColor(getLineColor());
        getGraphics().drawPolygon(pointsX, pointsY, points.size());
    }

    /**
     * @param point
     */
    public void move(Point point) {
        int xChange = getLocation().getX() - point.getX();
        int yChange = getLocation().getY() - point.getY();
        setRefPoint(point);

        for(Point figurePoints : points) {
            figurePoints.subtract(xChange, yChange);
        }
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public static class Builder extends Figure2D.Builder {
        private ArrayList<Point> points = new ArrayList<>();

        public Builder() {
        }

        @Override
        public Figure.Builder addPoint(Point point) {
            points.add(point);
            return this;
        }

        @Override
        public boolean needsMorePoints() {
            return true;
        }

        @Override
        public boolean isReadyForBuild() {
            return super.isReadyForBuild()
                    && getRefPoint().isCloseTo(points.get(points.size() - 1));
        }

        @Override
        public Figure build() {
            Polygon polygon = new Polygon();
            polygon.setGraphics(getGraphics());
            polygon.setLineColor(getLineColor());
            polygon.setRefPoint(getRefPoint());
            polygon.setFillingColor(getFillingColor());
            polygon.setPoints(points);

            return polygon;
        }
    }
}