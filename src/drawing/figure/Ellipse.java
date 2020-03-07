package drawing.figure;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Ellipse extends Figure2D {

    private Point peripheralPoint;

    public void draw() {
        int x1 = getLocation().getX();
        int x2 = peripheralPoint.getX();
        int y1 = getLocation().getY();
        int y2 = peripheralPoint.getY();

        int xLeftUpperCorner = Math.min(x1, x2);
        int yLeftUpperCorner = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);

        getGraphics().setColor(getFillingColor());
        getGraphics().fillOval(xLeftUpperCorner, yLeftUpperCorner, width, height);
        getGraphics().setColor(getLineColor());
        getGraphics().drawOval(xLeftUpperCorner, yLeftUpperCorner, width, height);
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
            Ellipse ellipse = new Ellipse();
            ellipse.setGraphics(getGraphics());
            ellipse.setLineColor(getLineColor());
            ellipse.setRefPoint(getRefPoint());
            ellipse.setFillingColor(getFillingColor());
            ellipse.peripheralPoint = peripheralPoint;

            return ellipse;
        }

        public Point getPeripheralPoint() {
            return peripheralPoint;
        }
    }
}