package com.shootemup.g53.view.element.spaceship;

import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class EnemyView extends SpaceshipView {
    public EnemyView(int lineWidth) {
        super(lineWidth);
    }

    @Override
    public void draw(Gui gui, Spaceship element) {
        Position pos = element.getPosition();
        int mid = pos.getX();
        int size = element.getHeight();
        String color = element.getColor();

        for (int i = 0; i <= size; i++) {
            if(i > lineWidth-1) {
                gui.drawLine(color, pos.getLeft(i).getUp(i), lineWidth);
                gui.drawLine(color, pos.getRight(i-lineWidth).getUp(i), lineWidth);
            } else {
                gui.drawLine(color, pos.getLeft(i).getUp(i), i*2);
            }
        }
    }
}
