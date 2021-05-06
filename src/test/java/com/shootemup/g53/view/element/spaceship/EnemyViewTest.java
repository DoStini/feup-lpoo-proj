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
        SpaceshipView view = new EnemyView(lineWidth);
        Spaceship spaceship = Mockito.mock(Spaceship.class);
        Mockito.when(spaceship.getHeight()).thenReturn(size);
        Mockito.when(spaceship.getColor()).thenReturn("#aaaaaa");
        Mockito.when(spaceship.getPosition()).thenReturn(new Position(0, 0));

        view.draw(gui, spaceship);

        Mockito.verify(gui, Mockito.times(2*size - lineWidth + 1))
                .drawLine(Mockito.eq("#aaaaaa"), Mockito.any(), Mockito.anyInt());

    }
}