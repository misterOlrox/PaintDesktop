package drawing.lib.figure;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PolyLine extends Figure1D {

    private Segment[] segments;

    public PolyLine(Point refPoint, Segment[] segments, Color lineColor) {
        super(refPoint, lineColor);
        this.segments = segments;
    }

    @Override
    public void draw(Graphics graphics) {
        for (Segment segment : segments) {
            segment.draw(graphics);
        }
    }

    public void move(Point point) {
        int xChange = getLocation().getX() - point.getX();
        int yChange = getLocation().getY() - point.getY();
        getLocation().subtract(xChange, yChange);
        for (Segment segment : segments) {
            segment.getGuidePoint().subtract(xChange, yChange);
        }
    }

    public static class Builder extends Figure1D.Builder {
        private ArrayList<Point> points = new ArrayList<>();

        @Override
        public void addPoint(Point point) {
            points.add(point);
        }

        @Override
        public boolean needsMorePoints() {
            return true;
        }

        @Override
        public boolean isReadyForBuild() {
            return super.isReadyForBuild()
                    && (points.isEmpty()
                    || getRefPoint().isCloseTo(points.get(points.size() - 1)));
        }

        @Override
        public Figure build() {
            Segment[] segments = new Segment[points.size() - 1];
            Point current = getRefPoint();
            for (int i = 0; i < points.size() - 1; i++) {
                Point next = points.get(i);
                segments[i] = new Segment(current, next, getLineColor());
                current = next;
            }

            return new PolyLine(
                    getRefPoint(),
                    segments,
                    getLineColor()
            );
        }
    }
}