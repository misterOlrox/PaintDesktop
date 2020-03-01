package drawing.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Ellipse extends Figure2D {

    private Point peripheralPoint;

    public Ellipse(Graphics graphics, Color lineColor, Color filling, Point refPoint, Point peripheralPoint) {
        super(graphics, lineColor, filling, refPoint);
        this.peripheralPoint = peripheralPoint;
    }

    public void draw() {

    }

    /**
     * @param point
     */
    public void move(Point point) {

    }
}