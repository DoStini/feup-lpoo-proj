package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Star;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StarViewTest {
    @Test
    void constructor() {
        StarView view = new StarView(10);

        Assertions.assertEquals(10, view.attenuation);
    }

    @Test
    void draw() {
        StarView view = new StarView(15);
        Gui gui = Mockito.mock(Gui.class);
        Star star = Mockito.spy(new Star(new Position(5,4),10));

        view.draw(gui, star);

        double factor = Math.min(1, (double)1/(15*star.getDistance()));
        int value = (int)(255 * factor);

        Position pos = star.getPosition();

        Mockito.verify(gui).drawColor(Mockito.eq(value), Mockito.eq(value), Mockito.eq(value), Mockito.eq(pos));
    }
}