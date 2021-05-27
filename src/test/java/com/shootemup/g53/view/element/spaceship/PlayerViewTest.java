package com.shootemup.g53.view.element.spaceship;

import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.ColorOperation;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerViewTest {

    @Test
    void draw() {
        Gui gui = Mockito.mock(Gui.class);
        int size = 5;
        int lineWidth = 2;
        PlayerView view = new PlayerView(lineWidth);
        Player spaceship = Mockito.mock(Player.class);
        Mockito.when(spaceship.getHeight()).thenReturn(size);
        Mockito.when(spaceship.getColor()).thenReturn("#aaaaaa");
        Mockito.when(spaceship.getHitHeight()).thenReturn(1);
        List<Integer> colors = ColorOperation.parseColor("#aaaaaa");
        IntStream.range(0, colors.size()).forEach(i -> colors.set(i, (int) (colors.get(i) * 0.8)));

        String inverseColor = ColorOperation.parseColor(colors.get(0),colors.get(1),colors.get(2));
        Mockito.when(spaceship.getPosition()).thenReturn(new Position(0, 0));

        view.draw(gui, spaceship);
        int hitHeight = spaceship.getHitHeight();

        Mockito.verify(gui, Mockito.times(2*(hitHeight+1)-Math.min(hitHeight+1, lineWidth)))
                .drawLine(Mockito.eq(inverseColor), Mockito.any(), Mockito.anyInt());

        Mockito.verify(gui, Mockito.times(2*(size+1-(hitHeight+1)) - (lineWidth-(hitHeight+1))))
                .drawLine(Mockito.eq("#aaaaaa"), Mockito.any(), Mockito.anyInt());


    }
}