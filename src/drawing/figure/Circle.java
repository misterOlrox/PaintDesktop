package drawing.figure;

/**
 * @author vitam
 * @version 1.0
 * @created 01-мар-2020 13:40:34
 */
public class Circle extends Ellipse {

    public static class Builder extends Ellipse.Builder {
        @Override
        public Figure build() {
            int radius = (int) getRefPoint().distanceTo(getPeripheralPoint());
            getRefPoint().subtract(radius, radius);
            getPeripheralPoint().move(
                    getRefPoint().getX() + 2*radius,
                    getRefPoint().getY() + 2*radius
            );

            Ellipse circle = new Circle();
            circle.setGraphics(getGraphics());
            circle.setLineColor(getLineColor());
            circle.setRefPoint(getRefPoint());
            circle.setFillingColor(getFillingColor());
            circle.setPeripheralPoint(getPeripheralPoint());

            return circle;
        }
    }
}