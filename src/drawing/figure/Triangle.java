package drawing.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Triangle extends Polygon {

    public Triangle(Graphics graphics, Color lineColor, Color filling, Point refPoint, Point[] points) {
        super(graphics, lineColor, filling, refPoint, points);
    }
}