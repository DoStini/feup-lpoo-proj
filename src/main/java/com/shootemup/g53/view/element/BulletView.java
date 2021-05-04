package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class BulletView implements ElementView<Bullet> {

    @Override
    public void draw(Gui gui, Bullet element) {
        Position pos = element.getPosition();
        int mid = pos.getX();

        for (int i = 0; i < element.getSize(); i++)
            gui.drawColor(element.getColor(), pos.getDown(i));
    }
}
