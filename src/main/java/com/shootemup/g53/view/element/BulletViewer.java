package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class BulletViewer {
    public void draw(Gui gui, Bullet bullet) {
        Position position = bullet.getPosition();
        gui.drawCharacter(bullet.getColor(),'B', position);
    }
}
