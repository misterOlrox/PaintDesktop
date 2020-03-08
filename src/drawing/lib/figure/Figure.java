package drawing.lib.figure;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figure {

    private Color lineColor;
    private Point refPoint;

    protected Figure() {
    }

    public Figure(Point refPoint, Color lineColor) {
        this.lineColor = lineColor;
        this.refPoint = refPoint;
    }

    public abstract void draw(Graphics graphics);

    public Point getLocation() {
        return refPoint;
    }

    public void move(Point point) {
        refPoint = point;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setRefPoint(Point refPoint) {
        this.refPoint = refPoint;
    }


    public abstract static class Builder {
        private Color lineColor;
        private Point refPoint;

        protected Builder() {
        }

        public Builder setRefPoint(Point point) {
            this.refPoint = point;
            return this;
        }

        public Builder setLineColor(Color lineColor) {
            this.lineColor = lineColor;
            return this;
        }

        public Builder setFillingColor(Color fillingColor) {
            // do nothing
            return this;
        }

        public abstract Builder addPoint(Point point);

        public abstract boolean needsMorePoints();

        public boolean isReadyForBuild() {
            return lineColor != null && refPoint != null;
        }

        public abstract Figure build();

        public Color getLineColor() {
            return lineColor;
        }

        public Point getRefPoint() {
            return refPoint;
        }

        public Color getFillingColor() {
            return null;
        }
    }
}