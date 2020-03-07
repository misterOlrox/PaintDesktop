package drawing.figure;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Triangle extends Polygon {

    public Triangle(Point refPoint, Color lineColor, Color fillingColor, ArrayList<Point> points) {
        super(refPoint, lineColor, fillingColor, points);
    }
}