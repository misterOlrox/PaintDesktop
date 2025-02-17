package drawing.lib.figure;

import java.awt.Color;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public abstract class Figure2D extends Figure {

    private Color fillingColor;

    protected Figure2D() {
        super();
    }

    public Figure2D(Point refPoint, Color lineColor, Color fillingColor) {
        super(refPoint, lineColor);
        this.fillingColor = fillingColor;
    }

    public Color getFillingColor() {
        return fillingColor;
    }

    public void setFillingColor(Color fillingColor) {
        this.fillingColor = fillingColor;
    }

    public abstract static class Builder extends Figure.Builder {
        private Color fillingColor;

        @Override
        public Figure.Builder setFillingColor(Color fillingColor) {
            this.fillingColor = fillingColor;
            return this;
        }

        @Override
        public Color getFillingColor() {
            return fillingColor;
        }

        @Override
        public boolean isReadyForBuild() {
            return super.isReadyForBuild() && fillingColor != null;
        }
    }
}