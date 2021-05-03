package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class AsteroidView implements ElementViewer<Asteroid> {

    private int radius;

    public AsteroidView(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Gui gui, Asteroid element) {
        Position pos = element.getPosition();
        String color = "#555555";
        for (int i = 0; i < radius; i++) {
            int currentRadius = radius - i -1;
            gui.drawLine(color, pos.getLeft(currentRadius).getUp(i), 2*currentRadius);
            gui.drawLine(color, pos.getLeft(currentRadius).getDown(i), 2*currentRadius);
        }
    }
}
