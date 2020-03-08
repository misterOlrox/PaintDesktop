package drawing.app;

import drawing.lib.figure.Circle;
import drawing.lib.figure.Ellipse;
import drawing.lib.figure.Figure;
import drawing.lib.figure.Line;
import drawing.lib.figure.PolyLine;
import drawing.lib.figure.Polygon;
import drawing.lib.figure.Ray;
import drawing.lib.figure.Rectangle;
import drawing.lib.figure.Rhomb;
import drawing.lib.figure.Segment;
import drawing.lib.figure.SymmetricPolygon;

import java.util.function.Supplier;

public enum FigureBuilderType {

    SEGMENT("Segment", Segment.Builder::new),
    RAY("Ray", Ray.Builder::new),
    LINE("Line", Line.Builder::new),
    POLYLINE("Polyline", PolyLine.Builder::new),
    ELLIPSE("Ellipse", Ellipse.Builder::new),
    CIRCLE("Circle", Circle.Builder::new),
    POLYGON("Polygon", Polygon.Builder::new),
    RECTANGLE("Rectangle", Rectangle.Builder::new),
    RHOMB("Rhomb", Rhomb.Builder::new),
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

    public Figure.Builder newBuilder() {
        return builderSupplier.get();
    }
}
