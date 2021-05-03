package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class AsteroidViewer {
    public void draw(Gui gui, Asteroid asteroid) {
        Position position = asteroid.getPosition();
        gui.drawCharacter(asteroid.getColor(), 'A', position);
    }
}
