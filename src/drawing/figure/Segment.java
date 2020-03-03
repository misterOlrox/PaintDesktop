package drawing.figure;

import drawing.app.UserChoice;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Segment extends Figure1D {

    private Point guidePoint;

    protected Segment() {
        super();
    }

    public void draw() {
        getGraphics().setColor(getLineColor());
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

    public static Figure create() {
        return new Segment();
    }

    public static class FactoryMethod implements Figure.FactoryMethod {
        @Override
        public String getFigureType() {
            return "Segment";
        }

        @Override
        public Figure create(UserChoice userChoice) {
            Segment segment = new Segment();
            return segment;
        }
    }
}