package drawing.lib.figure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

public class Ray extends Segment {

    public Ray(Point refPoint, Point guidePoint, Color lineColor) {
        super(refPoint, guidePoint, lineColor);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(getLineColor());

        Point outScreenPoint = getScreenFramePoint();
        graphics.drawLine(
                getLocation().getX(), getLocation().getY(),
                outScreenPoint.getX(), outScreenPoint.getY()
        );
    }

    private Point getScreenFramePoint() {
        Point refPoint = getLocation();
        int resultX;
        int resultY;
        double deltaX = getGuidePoint().getX() - refPoint.getX();
        double deltaY = getGuidePoint().getY() - refPoint.getY();
        if (Math.abs(deltaX) < Math.abs(deltaY)) {
            if (deltaY < 0) {
                resultY = -1;
            } else {
                resultY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() + 1);
            }
            resultX = (int) (deltaX / deltaY * (resultY - refPoint.getY()) + refPoint.getX());
        } else {
            if (deltaX < 0) {
                resultX = -1;
            } else {
                resultX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() + 1);
            }
            resultY = (int) (deltaY / deltaX * (resultX - refPoint.getX()) + refPoint.getY());
        }
        return new Point(resultX, resultY);
    }

    public static class Builder extends Segment.Builder {
        @Override
        public Figure build() {
            return new Ray(
                    getRefPoint(),
                    getGuidePoint(),
                    getLineColor()
            );
        }

        @Override
        public void addPoint(Point point) {
            if (!getRefPoint().equals(point)) {
                super.addPoint(point);
            }
        }
    }
}