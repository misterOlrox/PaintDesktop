package drawing.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Segment extends Figure1D {

    private Point guidePoint;

    protected Segment() {
        super();
    }

    protected Segment(Point refPoint, Point guidePoint, Color lineColor) {
        super(refPoint, lineColor);
        this.guidePoint = guidePoint;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(getLineColor());
        graphics.drawLine(
                getLocation().getX(), getLocation().getY(),
                guidePoint.getX(), guidePoint.getY()
        );
    }

    public void move(Point point) {
        int xChange = getLocation().getX() - point.getX();
        int yChange = getLocation().getY() - point.getY();
        setRefPoint(point);
        getGuidePoint().subtract(xChange, yChange);
    }

    public Point getGuidePoint() {
        return guidePoint;
    }

    public void setGuidePoint(Point guidePoint) {
        this.guidePoint = guidePoint;
    }

    public static class Builder extends Figure1D.Builder {
        private Point guidePoint;

        public Builder() {}

        @Override
        public Figure.Builder addPoint(Point point) {
            this.guidePoint = point;
            return this;
        }

        @Override
        public boolean needsMorePoints() {
            return guidePoint == null || getRefPoint() == null;
        }

        @Override
        public boolean isReadyForBuild() {
            return super.isReadyForBuild() && guidePoint != null;
        }

        @Override
        public Figure build() {
            return new Segment(
                    getRefPoint(),
                    guidePoint,
                    getLineColor()
            );
        }
    }
}