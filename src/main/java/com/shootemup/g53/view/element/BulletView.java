package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class BulletView implements ElementView<Bullet> {

    private int size;

    public BulletView(int size) {
        this.size = size;
    }

    @Override
    public void draw(Gui gui, Bullet element) {
        Position pos = element.getPosition();
        int mid = pos.getX();

        for (int i = 0; i < size; i++)
            gui.drawColor("#FF2222", pos.getDown(i));
    }
}
