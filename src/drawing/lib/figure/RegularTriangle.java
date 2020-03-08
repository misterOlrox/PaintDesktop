package drawing.lib.figure;

import java.awt.Color;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class RegularTriangle extends SymmetricPolygon {

    protected RegularTriangle(Point refPoint, Color lineColor, Color fillingColor, Point peripheralPoint, int sidesNumber) {
        super(refPoint, peripheralPoint, sidesNumber, lineColor, fillingColor);
    }
}