package drawing.app;

import drawing.figure.Figure;

import java.util.ArrayList;
import java.util.List;

public class FigureStorage {

    private List<Figure> figures = new ArrayList<>();

    public FigureStorage() {
    }

    public void add(Figure figure) {
        figures.add(figure);
    }

    public Figure getByIndex(int index) {
        return figures.get(index);
    }

    public List<Figure> getAll() {
        return new ArrayList<>(figures);
    }

}
