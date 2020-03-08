package drawing.lib.figure;

import java.awt.Color;
import java.awt.Graphics;

public class Ellipse extends Figure2D {

    private Point peripheralPoint;

    protected Ellipse() {
        super();
    }

    public Ellipse(Point refPoint, Color lineColor, Color fillingColor, Point peripheralPoint) {
        super(refPoint, lineColor, fillingColor);
        this.peripheralPoint = peripheralPoint;
    }

    @Override
    public void draw(Graphics graphics) {
        int x1 = getLocation().getX();
        int x2 = peripheralPoint.getX();
        int y1 = getLocation().getY();
        int y2 = peripheralPoint.getY();

        int xLeftUpperCorner = Math.min(x1, x2);
        int yLeftUpperCorner = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);

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

    public static class Builder extends Figure2D.Builder {
        private Point peripheralPoint;

        @Override
        public Figure.Builder addPoint(Point point) {
            this.peripheralPoint = point;
            return this;
        }

        @Override
        public boolean needsMorePoints() {
            return getRefPoint() == null || peripheralPoint == null;
        }

        @Override
        public boolean isReadyForBuild() {
            return super.isReadyForBuild() && peripheralPoint != null;
        }

        @Override
        public Figure build() {
            return new Ellipse(
                    getRefPoint(),
                    getLineColor(),
                    getFillingColor(),
                    getPeripheralPoint()
            );
        }

        public Point getPeripheralPoint() {
            return peripheralPoint;
        }
    }
}