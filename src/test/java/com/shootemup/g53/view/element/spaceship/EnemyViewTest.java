package com.shootemup.g53.view.element.spaceship;

import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class EnemyViewTest {

    @Test
    void draw() {
        Gui gui = Mockito.mock(Gui.class);
        int size = 5;
        int lineWidth = 2;
        SpaceshipView view = new EnemyView("#aaaaaa", size, lineWidth);
        view.draw(gui, new Spaceship(new Position(0, 0)));

        Mockito.verify(gui, Mockito.times(2*size - lineWidth + 1))
                .drawLine(Mockito.anyString(), Mockito.any(), Mockito.anyInt());

    }
}