package drawing.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public abstract class Figure2D extends Figure {

    private Color filling;

    public Figure2D(Graphics graphics, Color lineColor, Color filling, Point refPoint) {
        super(graphics, lineColor, refPoint);
        this.filling = filling;
    }

    public Color getFilling() {
        return filling;
    }
}