package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class SpaceShipViewer{
    public void draw(Gui gui, Spaceship spaceship) {
        int mid = spaceship.getPosition().getX();
        int height = spaceship.getHeight();
        int spacing = 10;
        for (int i = 0; i < height; i++) {
            gui.drawLine(spaceship.getColor(), new Position(mid-i/2, i + spaceship.getPosition().getY()), i);
            if (i > spacing)
                gui.drawLine("#000000", new Position(mid - i/2 + spacing/2, i + spaceship.getPosition().getY()), i - spacing);

        }
    }
}
