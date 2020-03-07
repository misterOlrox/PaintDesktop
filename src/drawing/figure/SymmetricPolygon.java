package drawing.figure;

import drawing.app.SymmetricPolygonDialog;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class SymmetricPolygon extends Polygon {

    private int sidesNumber;

    protected SymmetricPolygon() {
        super();
    }

    protected SymmetricPolygon(Point refPoint,
                            Color lineColor,
                            Color fillingColor,
                            ArrayList<Point> points,
                            int sidesNumber) {
        super(refPoint, lineColor, fillingColor, points);
        this.sidesNumber = sidesNumber;
    }

    public int getSidesNumber() {
        return sidesNumber;
    }

    public static class Builder extends Polygon.Builder {
        private int sidesNumber = -1;

        @Override
        public Figure.Builder addPoint(Point point) {
            ArrayList<Point> points = getPoints();
            if (points.isEmpty()) {
                points.add(point);
            } else {
                SymmetricPolygonDialog dialog = new SymmetricPolygonDialog();
            }
            return this;
        }

        @Override
        public boolean needsMorePoints() {
            return sidesNumber > getPoints().size() - 1;
        }

        @Override
        public boolean isReadyForBuild() {
            return super.isReadyForBuild()
                    && sidesNumber != -1
                    && getPoints().size() - 1 == sidesNumber;
        }

        @Override
        public Figure build() {
            return super.build();
        }
    }
}