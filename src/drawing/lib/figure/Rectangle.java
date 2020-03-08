package drawing.lib.figure;

import drawing.lib.figure.abstractbuilder.TwoPointBuilder;

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
            ArrayList<Point> points = new ArrayList<>();

            int x1 = getRefPoint().getX();
            int x2 = getPeripheralPoint().getX();
            int y1 = getRefPoint().getY();
            int y2 = getPeripheralPoint().getY();

            int xLeftUpperCorner = Math.min(x1, x2);
            int yLeftUpperCorner = Math.min(y1, y2);
            int xRightBottomCorner = Math.max(x1, x2);
            int yRightBottomCorner = Math.max(y1, y2);

            getRefPoint().move(xLeftUpperCorner, yLeftUpperCorner);
            getPeripheralPoint().move(xRightBottomCorner, yRightBottomCorner);

            return new Rectangle(
                    getRefPoint(),
                    getPeripheralPoint(),
                    getLineColor(),
                    getFillingColor()
            );
        }
    }
}