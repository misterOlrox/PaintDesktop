package drawing.lib.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Ray extends Segment {

    public Ray(Point refPoint, Point guidePoint, Color lineColor) {
        super(refPoint, guidePoint, lineColor);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(getLineColor());
        graphics.drawLine(
                getLocation().getX(), getLocation().getY(),
                getGuidePoint().getX(), getGuidePoint().getY()
        );
    }
}