package com.shootemup.g53.view.element.spaceship;

import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class PlayerView extends SpaceshipView {

    public PlayerView(int lineWidth) {
        super(lineWidth);
    }

    @Override
    public void draw(Gui gui, Spaceship element) {
        Position pos = element.getPosition();
        int mid = pos.getX();
        String color = element.getColor();
        for (int i = 0; i <= element.getHeight(); i++) {
            if (i > lineWidth) {
                gui.drawLine(color, pos.getLeft(i).getDown(i), lineWidth);
                gui.drawLine(color, pos.getRight(i-lineWidth).getDown(i), lineWidth);
            }
            else
                gui.drawLine(color, pos.getLeft(i).getDown(i), i*2);
        }

    }
}
