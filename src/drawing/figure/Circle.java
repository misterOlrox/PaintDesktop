package drawing.figure;

import java.awt.Color;

public class Circle extends Ellipse {

    public Circle(Point refPoint, Color lineColor, Color fillingColor, Point peripheralPoint) {
        int radius = (int) refPoint.distanceTo(peripheralPoint);
        refPoint.subtract(radius, radius);
        peripheralPoint.move(refPoint.getX() + 2 * radius, refPoint.getY() + 2 * radius);

        this.setRefPoint(refPoint);
        this.setPeripheralPoint(peripheralPoint);
        this.setLineColor(lineColor);
        this.setFillingColor(fillingColor);
    }

    public static class Builder extends Ellipse.Builder {
        @Override
        public Figure build() {
            return new Circle(
                    getRefPoint(),
                    getLineColor(),
                    getFillingColor(),
                    getPeripheralPoint()
            );
        }
    }
}