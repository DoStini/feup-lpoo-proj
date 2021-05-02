package com.shootemup.g53.element;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class ExampleViewer implements ElementViewer{
    @Override
    public void draw(Gui gui) {
        gui.drawColor("#adadad", new Position(20, 20));
        gui.drawColor("#adadad", new Position(19, 21));
        gui.drawColor("#adadad", new Position(21, 21));
    }
}
