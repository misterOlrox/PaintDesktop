package drawing.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public abstract class Figure {

    private Color lineColor;
    private Point refPoint;
    private Graphics graphics;

    public Figure(Graphics graphics, Color lineColor, Point refPoint) {
        this.lineColor = lineColor;
        this.refPoint = refPoint;
        this.graphics = graphics;
    }

    public abstract void draw();

    public Point getLocation() {
        return refPoint;
    }

    /**
     * @param point
     */
    public void move(Point point) {
        refPoint = point;
        draw();
    }

    public Color getLineColor() {
        return lineColor;
    }

    public Graphics getGraphics() {
        return graphics;
    }
}