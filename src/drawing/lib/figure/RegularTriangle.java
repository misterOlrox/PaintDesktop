package drawing.lib.figure;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class RegularTriangle extends SymmetricPolygon {

    protected RegularTriangle(Point refPoint, Point peripheralPoint, Color lineColor, Color fillingColor) {
        super(refPoint, peripheralPoint, 3, lineColor, fillingColor);
    }

    public static class Builder extends SymmetricPolygon.Builder {
        @Override
        public void addPoint(Point point) {
            ArrayList<Point> points = getPoints();
            if (points.isEmpty()) {
                points.add(point);
            }
            setSidesNumber(3);
        }

        @Override
        public Figure build() {
            return new RegularTriangle(
                    getRefPoint(),
                    getPoints().get(0),
                    getLineColor(),
                    getFillingColor()
            );
        }
    }
}