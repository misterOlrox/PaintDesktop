package drawing.lib.figure.builder;

import drawing.lib.figure.Figure2D;
import drawing.lib.figure.Point;

public abstract class TwoPointBuilder extends Figure2D.Builder {
    private Point peripheralPoint;

    @Override
    public void addPoint(Point point) {
        this.peripheralPoint = point;
    }

    @Override
    public boolean needsMorePoints() {
        return getRefPoint() == null || peripheralPoint == null;
    }

    @Override
    public boolean isReadyForBuild() {
        return super.isReadyForBuild() && peripheralPoint != null;
    }

    public Point getPeripheralPoint() {
        return peripheralPoint;
    }

    public Point getUpperLeftPoint() {
        int xLeftUpperCorner = Math.min(getRefPoint().getX(), getPeripheralPoint().getX());
        int yLeftUpperCorner = Math.min(getRefPoint().getY(), getPeripheralPoint().getY());

        return new Point(xLeftUpperCorner, yLeftUpperCorner);
    }

    public Point getBottomRightPoint() {
        int xRightBottomCorner = Math.max(getRefPoint().getX(), getPeripheralPoint().getX());
        int yRightBottomCorner = Math.max(getRefPoint().getY(), getPeripheralPoint().getY());

        return new Point(xRightBottomCorner, yRightBottomCorner);
    }
}
