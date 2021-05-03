package com.shootemup.g53.view.shapes;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class CircleDrawer implements Drawer {
    private String color;
    private int radius;

    public CircleDrawer(String color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    @Override
    public void draw(Gui gui, Position position) {
        for (int i = 0; i < radius; i++) {
            int currentRadius = radius - i -1;
            gui.drawLine(color, position.getLeft(currentRadius).getUp(i), 2*currentRadius);
            gui.drawLine(color, position.getLeft(currentRadius).getDown(i), 2*currentRadius);
        }
    }
}
