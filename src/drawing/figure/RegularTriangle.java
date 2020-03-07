package drawing.figure;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class RegularTriangle extends SymmetricPolygon {

    protected RegularTriangle(Point refPoint, Color lineColor, Color fillingColor, ArrayList<Point> points, int sidesNumber) {
        super(refPoint, lineColor, fillingColor, points, sidesNumber);
    }
}