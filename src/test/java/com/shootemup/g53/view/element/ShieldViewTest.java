package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Shield;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ShieldViewTest {

    @Test
    void draw() {
        Gui gui = Mockito.mock(Gui.class);
        Position pos = new Position(0, 0);
        int size = 3;

        Shield shield = Mockito.mock(Shield.class);
        Mockito.when(shield.getWidth()).thenReturn(size);
        Mockito.when(shield.getColor()).thenReturn("#aaaaaa");
        Mockito.when(shield.getPosition()).thenReturn(new Position(0, 0));

        ShieldView view = new ShieldView();
        view.draw(gui, shield);

        Mockito.verify(gui, Mockito.times(2))
                .drawLine(Mockito.anyString(), Mockito.any(), Mockito.eq(size));

        for (int i = 0; i < 2; i++) {
            Mockito.verify(gui, Mockito.times(1))
                    .drawLine("#aaaaaa", pos.getLeft(size/2).getDown(i), size);
        }
    }
}