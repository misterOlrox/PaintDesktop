package drawing.lib.figure;

import drawing.lib.dialog.SymmetricPolygonDialog;

import java.awt.Color;
import java.util.ArrayList;

public class SymmetricPolygon extends Polygon {

    protected SymmetricPolygon() {
        super();
    }

    protected SymmetricPolygon(Point circleCenter,
                               Point pointOnCircle,
                               int sidesNumber,
                               Color lineColor,
                               Color fillingColor) {

        setFillingColor(fillingColor);
        setLineColor(lineColor);

        ArrayList<Point> points = calculatePoints(circleCenter, pointOnCircle, sidesNumber);
        setRefPoint(points.get(sidesNumber - 1));
        setPoints(points);
    }

    private ArrayList<Point> calculatePoints(Point circleCenter, Point pointOnCircle, int sidesNumber) {
        ArrayList<Point> points = new ArrayList<>(sidesNumber + 1);
        double radius = circleCenter.distanceTo(pointOnCircle);
        double phi = Math.asin((circleCenter.getY() - pointOnCircle.getY()) / radius) * 180 / Math.PI;
        double angle = 360.0 / sidesNumber;

        if (pointOnCircle.getX() < circleCenter.getX())
            phi = 180.0 - phi;

        for (int i = 0; i < sidesNumber; i++) {
            points.add(new Point(
                    circleCenter.getX() + (int) (Math.cos(phi / 180 * Math.PI) * radius),
                    circleCenter.getY() - (int) (Math.sin(phi / 180 * Math.PI) * radius)
            ));
            phi = phi + angle;
        }
        return points;
    }

    public static class Builder extends Polygon.Builder {
        private int sidesNumber = -1;
        private static SymmetricPolygonDialog dialog = new SymmetricPolygonDialog();

        @Override
        public void addPoint(Point point) {
            ArrayList<Point> points = getPoints();
            if (points.isEmpty()) {
                points.add(point);
            }
            dialog.setVisible(true);
            while (dialog.getSidesNumber() == null) {
                dialog.setVisible(true);
            }
            sidesNumber = dialog.getSidesNumber();

        }

        @Override
        public boolean needsMorePoints() {
            return getPoints().size() != 1;
        }

        @Override
        public boolean isReadyForBuild() {
            return getLineColor() != null
                    && getRefPoint() != null
                    && getFillingColor() != null
                    && sidesNumber != -1
                    && getPoints().size() == 1;
        }

        @Override
        public Figure build() {
            return new SymmetricPolygon(
                    getRefPoint(),
                    getPoints().get(0),
                    sidesNumber,
                    getLineColor(),
                    getFillingColor()
            );
        }
    }
}