package drawing.lib.figure.builder;

import drawing.lib.figure.Figure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FigureBuilders {
    private Map<String, Integer> names;

    {
        names = new HashMap<>();
        for (FigureBuilderType type : FigureBuilderType.values()) {
            names.put(type.getName(), type.ordinal());
        }
    }

    public Figure.Builder get(String name) {
        int order = names.get(name);
        return FigureBuilderType.values()[order].newBuilder();
    }

    public List<String> getAvailableTypes() {
        return Arrays.stream(FigureBuilderType.values())
                .map(FigureBuilderType::getName)
                .collect(Collectors.toList());
    }
}
