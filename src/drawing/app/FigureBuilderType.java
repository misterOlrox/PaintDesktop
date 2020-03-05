package drawing.app;

import drawing.figure.Figure;
import drawing.figure.Polygon;
import drawing.figure.Segment;

import java.util.function.Supplier;

public enum FigureBuilderType {

    SEGMENT("Segment", Segment.Builder::new),
    POLYGON("Polygon", Polygon.Builder::new);


    private String name;
    private Supplier<Figure.Builder> builderConsumer;

    FigureBuilderType(String name, Supplier<Figure.Builder> builderConsumer) {
        this.name = name;
        this.builderConsumer = builderConsumer;
    }

    public String getName() {
        return name;
    }

    public Supplier<Figure.Builder> getBuilderConsumer() {
        return builderConsumer;
    }
}
