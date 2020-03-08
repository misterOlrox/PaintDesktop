package drawing.lib.figure;

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
        super.draw(graphics);
        getGuidePoint().move(
                2 * getLocation().getX() - getGuidePoint().getX(),
                2 * getLocation().getY() - getGuidePoint().getY()
        );
        super.draw(graphics);
    }

    public static class Builder extends Ray.Builder {
        @Override
        public Figure build() {
            return new Line(
                    getRefPoint(),
                    getGuidePoint(),
                    getLineColor()
            );
        }
    }
}