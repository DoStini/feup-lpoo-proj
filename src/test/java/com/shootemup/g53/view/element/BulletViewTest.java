package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BulletViewTest {

    @Test
    void draw() {
        Gui gui = Mockito.mock(Gui.class);
        Position pos = new Position(0, 0);
        int size = 3;

        Bullet bullet = Mockito.mock(Bullet.class);
        Mockito.when(bullet.getSize()).thenReturn(size);
        Mockito.when(bullet.getColor()).thenReturn("#aaaaaa");
        Mockito.when(bullet.getPosition()).thenReturn(new Position(0, 0));

        BulletView view = new BulletView();
        view.draw(gui, bullet);

        Mockito.verify(gui, Mockito.times(size+1))
                .drawColor(Mockito.anyString(), Mockito.any());

        for (int i = 0; i <= size; i++) {
            Mockito.verify(gui, Mockito.times(1))
                    .drawColor("#aaaaaa", pos.getDown(i));
        }
    }
}