package drawing.lib.figure;

import drawing.lib.figure.abstractbuilder.TwoPointBuilder;

import java.awt.Color;
import java.awt.Graphics;

public class Ellipse extends Figure2D {

    private Point peripheralPoint;

    protected Ellipse() {
        super();
    }

    public Ellipse(Point upperLeftPoint,
                   Point bottomRightPoint,
                   Color lineColor,
                   Color fillingColor) {

        super(upperLeftPoint, lineColor, fillingColor);
        this.peripheralPoint = bottomRightPoint;
    }

    @Override
    public void draw(Graphics graphics) {
        int xLeftUpperCorner = getLocation().getX();
        int yLeftUpperCorner = getLocation().getY();
        int width = getPeripheralPoint().getX() - xLeftUpperCorner;
        int height = getPeripheralPoint().getY() - yLeftUpperCorner;

        graphics.setColor(getFillingColor());
        graphics.fillOval(xLeftUpperCorner, yLeftUpperCorner, width, height);
        graphics.setColor(getLineColor());
        graphics.drawOval(xLeftUpperCorner, yLeftUpperCorner, width, height);
    }

    public void move(Point point) {
        int xChange = getLocation().getX() - point.getX();
        int yChange = getLocation().getY() - point.getY();
        setRefPoint(point);
        peripheralPoint.subtract(xChange, yChange);
    }

    public Point getPeripheralPoint() {
        return peripheralPoint;
    }

    public void setPeripheralPoint(Point peripheralPoint) {
        this.peripheralPoint = peripheralPoint;
    }

    public static class Builder extends TwoPointBuilder {
        @Override
        public Figure build() {
            return new Ellipse(
                    getUpperLeftPoint(),
                    getBottomRightPoint(),
                    getLineColor(),
                    getFillingColor()
            );
        }
    }
}