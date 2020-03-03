package drawing.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public abstract class Figure1D extends Figure {

    public Figure1D(Graphics graphics, Color lineColor, Point refPoint) {
        super(graphics, lineColor, refPoint);
    }

    public Figure1D() {
        super();
    }
}