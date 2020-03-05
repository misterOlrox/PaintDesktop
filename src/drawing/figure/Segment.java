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

    protected Segment(Graphics graphics, Color lineColor, Point refPoint, Point guidePoint) {
        super(graphics, lineColor, refPoint);
        this.guidePoint = guidePoint;
    }

    public void draw() {
        getGraphics().setColor(getLineColor());
        getGraphics().drawLine(
                getLocation().getX(), getLocation().getY(),
                guidePoint.getX(), guidePoint.getY()
        );
    }

    public void move(Point point) {

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

        public static Figure.Builder newBuilder() {
            return new Builder();
        }

        @Override
        public Figure.Builder addPoint(Point point) {
            this.guidePoint = point;
            return this;
        }

        @Override
        public boolean needsMorePoints() {
            return guidePoint == null;
        }

        @Override
        public boolean isReadyForBuild() {
            return super.isReadyForBuild() && guidePoint != null;
        }

        @Override
        public Figure build() {
            Segment segment = new Segment();
            segment.setRefPoint(getRefPoint());
            segment.setGraphics(getGraphics());
            segment.setLineColor(getLineColor());
            segment.setGuidePoint(guidePoint);

            return segment;
        }
    }
}