package drawing.lib.figure;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Triangle extends Polygon {

    public Triangle(Point refPoint, Color lineColor, Color fillingColor, ArrayList<Point> points) {
        super(refPoint, lineColor, fillingColor, points);
    }

    public static class Builder extends Polygon.Builder {
        @Override
        public void addPoint(Point point) {
            if (getPoints().size() < 2) {
                getPoints().add(point);
            }
        }

        @Override
        public boolean needsMorePoints() {
            return getRefPoint() == null || getPoints().size() < 2;
        }

        @Override
        public boolean isReadyForBuild() {
            return getFillingColor() != null
                    && getLineColor() != null
                    && getRefPoint() != null
                    && getPoints().size() == 2;
        }

        @Override
        public Figure build() {
            getPoints().add(getRefPoint());

            return new Triangle(
                    getRefPoint(),
                    getLineColor(),
                    getFillingColor(),
                    getPoints()
            );
        }
    }
}