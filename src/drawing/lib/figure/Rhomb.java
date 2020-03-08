package drawing.lib.figure;

import drawing.lib.figure.builder.TwoPointBuilder;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Rhomb extends Polygon {

    public Rhomb(Point upperLeftPoint,
                 Point bottomRightPoint,
                 Color lineColor,
                 Color fillingColor) {

        this.setFillingColor(fillingColor);
        this.setLineColor(lineColor);

        int width = (bottomRightPoint.getX() - upperLeftPoint.getX()) / 2;
        int height = (bottomRightPoint.getY() - upperLeftPoint.getY()) / 2;

        ArrayList<Point> points = new ArrayList<>();
        this.setRefPoint(new Point(upperLeftPoint.getX() + width, upperLeftPoint.getY()));
        points.add(new Point(bottomRightPoint.getX(), upperLeftPoint.getY() + height));
        points.add(new Point(upperLeftPoint.getX() + width, bottomRightPoint.getY()));
        points.add(new Point(upperLeftPoint.getX(), upperLeftPoint.getY() + height));
        points.add(getLocation());

        this.setPoints(points);
    }

    public static class Builder extends TwoPointBuilder {

        @Override
        public Figure build() {
            return new Rhomb(
                    getUpperLeftPoint(),
                    getBottomRightPoint(),
                    getLineColor(),
                    getFillingColor()
            );
        }
    }
}