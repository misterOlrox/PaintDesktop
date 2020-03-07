package drawing.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Line extends Ray {

    public Line(Point refPoint, Point guidePoint, Color lineColor) {
        super(refPoint, guidePoint, lineColor);
    }

    @Override
    public void draw(Graphics graphics) {

    }
}