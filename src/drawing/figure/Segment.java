package drawing.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Segment extends Figure1D {

    private Point guidePoint;

    public Segment(Graphics graphics, Color lineColor, Point refPoint, Point guidePoint) {
        super(graphics, lineColor, refPoint);
        this.guidePoint = guidePoint;
    }

    public void draw() {
        getGraphics().drawLine(
                getLocation().getX(), getLocation().getY(),
                guidePoint.getX(), guidePoint.getY()
        );
    }

    /**
     * @param point
     */
    public void move(Point point) {

    }
}