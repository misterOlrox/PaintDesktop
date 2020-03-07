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

    public Figure getLast() {
        if (figures.isEmpty()) {
            return null;
        } else {
            return figures.get(figures.size() - 1);
        }
    }

    public boolean isEmpty() {
        return figures.isEmpty();
    }
}
