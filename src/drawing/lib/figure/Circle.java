package drawing.lib.figure;

import java.awt.Color;

public class Circle extends Ellipse {

    public Circle(Point center, Point pointOnCircle, Color lineColor, Color fillingColor) {
        int radius = (int) center.distanceTo(pointOnCircle);
        center.subtract(radius, radius);
        pointOnCircle.move(center.getX() + 2 * radius, center.getY() + 2 * radius);

        this.setRefPoint(center);
        this.setPeripheralPoint(pointOnCircle);
        this.setLineColor(lineColor);
        this.setFillingColor(fillingColor);
    }

    public static class Builder extends Ellipse.Builder {
        @Override
        public Figure build() {
            return new Circle(
                    getRefPoint(),
                    getPeripheralPoint(),
                    getLineColor(),
                    getFillingColor()
            );
        }
    }
}