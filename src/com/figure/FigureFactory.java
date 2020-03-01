package com.figure;

import java.awt.*;

public class FigureFactory {

    Graphics graphics;

    public FigureFactory(Graphics graphics) {
        this.graphics = graphics;
    }

    public Figure createCircle(Point center, int radius) {
        return new Circle(graphics, center, radius);
    }

    public Figure createEllipse(Point center, int width, int height) {
        return new Ellipse(graphics, center, width, height);
    }
}
