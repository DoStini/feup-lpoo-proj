package com.shootemup.g53.view.shapes;

import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.element.spaceship.EnemyView;
import com.shootemup.g53.view.element.spaceship.SpaceshipView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CircleDrawerTest {

    @Test
    void draw() {
        Gui gui = Mockito.mock(Gui.class);
        int radius = 5;

        Drawer drawer = new CircleDrawer("", radius);
        drawer.draw(gui, new Position(0, 0));

        Mockito.verify(gui, Mockito.times(2*radius))
                .drawLine(Mockito.anyString(), Mockito.any(), Mockito.anyInt());
    }
}