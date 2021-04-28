package view.element;

import model.util.Position;
import ui.Gui;

public class ExampleViewer implements ElementViewer{
    @Override
    public void draw(Gui gui) {
        gui.drawColor("#adadad", new Position(5, 5));
        gui.drawColor("#adadad", new Position(4, 6));
        gui.drawColor("#adadad", new Position(6, 6));
    }
}
