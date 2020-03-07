package drawing.figure;

import java.awt.Color;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public abstract class Figure1D extends Figure {

    protected Figure1D() {
        super();
    }

    protected Figure1D(Point refPoint, Color lineColor) {
        super(refPoint, lineColor);
    }

    public static abstract class Builder extends Figure.Builder {

    }
}