package drawing.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Polygon extends Figure2D {

    private Point[] points;

    public Polygon(Graphics graphics, Color lineColor, Color filling, Point refPoint, Point[] points) {
        super(graphics, lineColor, filling, refPoint);
        this.points = points;
    }

    public void draw() {

    }

    /**
     * @param point
     */
    public void move(Point point) {

    }
}