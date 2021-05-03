package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.element.spaceship.EnemyView;
import com.shootemup.g53.view.element.spaceship.SpaceshipView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BulletViewTest {

    @Test
    void draw() {
        Gui gui = Mockito.mock(Gui.class);
        Position pos = new Position(0, 0);
        int size = 3;
        BulletView view = new BulletView(size);
        view.draw(gui, new Bullet(new Position(0,0)));

        Mockito.verify(gui, Mockito.times(size))
                .drawColor(Mockito.anyString(), Mockito.any());

        for (int i = 0; i < size; i++) {
            Mockito.verify(gui, Mockito.times(1))
                    .drawColor("#FF2222", pos.getDown(i));
        }
    }
}