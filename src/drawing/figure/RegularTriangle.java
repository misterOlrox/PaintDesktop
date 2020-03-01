package drawing.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class RegularTriangle extends SymmetricPolygon {

    public RegularTriangle(Graphics graphics, Color lineColor, Color filling, Point refPoint, Point[] points, int sidesNumber) {
        super(graphics, lineColor, filling, refPoint, points, sidesNumber);
    }
}