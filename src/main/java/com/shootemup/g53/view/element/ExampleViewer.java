package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class ExampleViewer implements ElementViewer{
    @Override
    public void draw(Gui gui, Element element) {
        int mid = element.getPosition().getX();
        int posY = element.getPosition().getY();
        int height = 5;
        for (int i = 0; i < 5; i++) {
            gui.drawLine("#8c2d19", new Position(mid-i/2, posY + i), i);
        }
    }
}
