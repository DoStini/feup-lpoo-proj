package com.shootemup.g53.view.element.spaceship;

import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class EnemyView extends SpaceshipView {
    public EnemyView(String color, int size, int lineWidth) {
        super(color, size, lineWidth);
    }

    @Override
    public void draw(Gui gui, Spaceship element) {
        Position pos = element.getPosition();
        int mid = pos.getX();

        for (int i = 0; i < size; i++) {
            int reverse = size - i - 1;
            if (i < size - lineWidth) {
                gui.drawLine(color, pos.getLeft(reverse).getDown(i), lineWidth);
                gui.drawLine(color, pos.getRight(reverse-lineWidth).getDown(i), lineWidth);
            }
            else
                gui.drawLine(color, pos.getLeft(reverse).getDown(i), reverse*2);
        }
    }
}
