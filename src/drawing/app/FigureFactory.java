package drawing.app;

import drawing.figure.Figure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FigureFactory {
    private Map<String, Figure.FactoryMethod> factoryMethodsMap;

    public FigureFactory() {
        this.factoryMethodsMap = new HashMap<>();
    }

    public FigureFactory(Figure.FactoryMethod... factoryMethods) {
        this.factoryMethodsMap = new HashMap<>();

        for(Figure.FactoryMethod factoryMethod : factoryMethods) {
            this.factoryMethodsMap.put(factoryMethod.getFigureType(), factoryMethod);
        }
    }

    public Figure create(String type, UserChoice userChoice) {
        Figure.FactoryMethod factoryMethod = factoryMethodsMap.get(type);
        return factoryMethod.create(userChoice);
    }

    public void addCreator(Figure.FactoryMethod factoryMethod) {
        factoryMethodsMap.put(factoryMethod.getFigureType(), factoryMethod);
    }

    public Collection<String> getAvailableTypes() {
        return factoryMethodsMap.keySet();
    }
}
