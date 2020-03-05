package drawing.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public abstract class Figure {

    private Color lineColor;
    private Point refPoint;
    private Graphics graphics;

    protected Figure() {
    }

    public Figure(Graphics graphics, Color lineColor, Point refPoint) {
        this.lineColor = lineColor;
        this.refPoint = refPoint;
        this.graphics = graphics;
    }

    public abstract void draw();

    public Point getLocation() {
        return refPoint;
    }

    public void move(Point point) {
        refPoint = point;
        draw();
    }

    public Color getLineColor() {
        return lineColor;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setRefPoint(Point refPoint) {
        this.refPoint = refPoint;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public abstract static class Builder {
        private Color lineColor;
        private Point refPoint;
        private Graphics graphics;

        protected Builder() {}

        public Builder setRefPoint(Point point) {
            this.refPoint = point;
            return this;
        }

        public Builder setGraphics(Graphics graphics) {
            this.graphics = graphics;
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
            return lineColor != null
                    && refPoint != null
                    && graphics != null;
        }

        public abstract Figure build();

        public Color getLineColor() {
            return lineColor;
        }

        public Point getRefPoint() {
            return refPoint;
        }

        public Graphics getGraphics() {
            return graphics;
        }

        public Color getFillingColor() {
            return null;
        }
    }
}