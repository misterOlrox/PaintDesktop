package drawing.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Circle extends Ellipse {
    /**
     * @param center
     * @param onRadius
     */
    public Circle(Graphics graphics, Color lineColor, Color filling, Point center, Point onRadius) {
        super(graphics, lineColor, filling, center, onRadius);
    }
}