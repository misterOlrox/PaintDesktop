package drawing.lib.figure;

import drawing.lib.figure.builder.TwoPointBuilder;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Rectangle extends Polygon {

    public Rectangle(Point upperLeftPoint,
                     Point bottomRightPoint,
                     Color lineColor,
                     Color fillingColor) {

        this.setFillingColor(fillingColor);
        this.setLineColor(lineColor);
        this.setRefPoint(upperLeftPoint);

        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(upperLeftPoint.getX(), bottomRightPoint.getY()));
        points.add(bottomRightPoint);
        points.add(new Point(bottomRightPoint.getX(), upperLeftPoint.getY()));
        points.add(upperLeftPoint);

        this.setPoints(points);
    }

    public static class Builder extends TwoPointBuilder {
        @Override
        public Figure build() {
            return new Rectangle(
                    getUpperLeftPoint(),
                    getBottomRightPoint(),
                    getLineColor(),
                    getFillingColor()
            );
        }
    }
}