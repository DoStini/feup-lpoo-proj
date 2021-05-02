package com.shootemup.g53.view.element;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class ExampleViewer implements ElementViewer{
    @Override
    public void draw(Gui gui) {
        int mid = 15;
        int spacing = 10;
        for (int i = 0; i < mid*2 + 1; i++) {
            gui.drawLine("#8c2d19", new Position(mid-i/2, i), i);
            if (i > spacing)
                gui.drawLine("#000000", new Position(mid - i/2 + spacing/2, i), i - spacing);

        }
    }
}
