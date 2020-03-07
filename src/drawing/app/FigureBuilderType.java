package drawing.app;

import drawing.figure.Circle;
import drawing.figure.Ellipse;
import drawing.figure.Figure;
import drawing.figure.Polygon;
import drawing.figure.Segment;
import drawing.figure.SymmetricPolygon;

import java.util.function.Supplier;

public enum FigureBuilderType {

    SEGMENT("Segment", Segment.Builder::new),
    POLYGON("Polygon", Polygon.Builder::new),
    ELLIPSE("Ellipse", Ellipse.Builder::new),
    CIRCLE("Circle", Circle.Builder::new),
    SYMMETRIC_POLYGON("Symmetric Polygon", SymmetricPolygon.Builder::new);


    private String name;
    private Supplier<Figure.Builder> builderSupplier;

    FigureBuilderType(String name, Supplier<Figure.Builder> builderSupplier) {
        this.name = name;
        this.builderSupplier = builderSupplier;
    }

    public String getName() {
        return name;
    }

    public Supplier<Figure.Builder> getBuilderSupplier() {
        return builderSupplier;
    }
}
