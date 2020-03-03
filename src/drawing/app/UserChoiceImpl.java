package drawing.app;

import java.awt.Graphics;

public class UserChoiceImpl implements UserChoice {

    private Graphics graphics;

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }
}
