package drawing.figure;

import drawing.app.UserChoice;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Polygon extends Figure2D {

    private Point[] points;

    protected Polygon() {
        super();
    }

    public void draw() {

    }

    /**
     * @param point
     */
    public void move(Point point) {

    }

    public static class FactoryMethod implements Figure.FactoryMethod {
        @Override
        public Figure create(UserChoice userChoice) {
            return new Polygon();
        }

        @Override
        public String getFigureType() {
            return "Polygon";
        }
    }
}