package com.figure;

import java.awt.Color;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public abstract class Figure {

    private Color lineColor;
    private Point refPoint;

    public Figure() {

    }

    public abstract void draw();

    public Color getLineColor() {
        return lineColor;
    }

    public Point location() {
        return refPoint;
    }

    /**
     * @param center
     */
    public void move(Point center) {

    }
}