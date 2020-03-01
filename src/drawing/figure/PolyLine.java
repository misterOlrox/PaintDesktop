package drawing.figure;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class PolyLine extends Figure1D {

    private Segment[] segments;
    public Segment m_Segment;

    public PolyLine(Graphics graphics, Color lineColor, Point refPoint, Segment[] segments, Segment m_Segment) {
        super(graphics, lineColor, refPoint);
        this.segments = segments;
        this.m_Segment = m_Segment;
    }

    public void draw() {

    }

    /**
     * @param point
     */
    public void move(Point point) {

    }
}