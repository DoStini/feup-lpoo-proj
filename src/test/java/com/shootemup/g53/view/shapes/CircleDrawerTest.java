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
        String color = "#aaaaaa";
        Position position = new Position(0, 0);

        Drawer drawer = new CircleDrawer(color, radius);
        drawer.draw(gui, position);

        // The circle should draw 2*radius vertically (radius) and 1 at the center
        Mockito.verify(gui, Mockito.times(2*radius + 1))
                .drawLine(Mockito.eq(color), Mockito.any(), Mockito.anyInt());

        // Verifying if the top and bottom 2 pixels of the circle are drawn
        Mockito.verify(gui, Mockito.times(1))
                .drawLine(color, position.getUp(radius), 0);
        Mockito.verify(gui, Mockito.times(1))
                .drawLine(color, position.getDown(radius), 0);


        // Verifying if the middle line is drawn
        Mockito.verify(gui, Mockito.times(1))
                .drawLine(color, position.getLeft(radius), 2*radius);
    }
}