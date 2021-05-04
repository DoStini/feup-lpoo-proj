package com.shootemup.g53.ui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class LanternaGuiTest {

    private Screen screen;
    private LanternaGui gui;
    private TextGraphics tg;
    private Position position;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);
        position = new Position(0, 0);

        Mockito.when(screen.newTextGraphics()).thenReturn(tg);

        gui = new LanternaGui(screen);
    }

    @Test
    void drawColor() {
        String color = "#aaaaaa";
        gui.drawColor(color, position);

        Mockito.verify(tg, Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString(color));

        Mockito.verify(tg, Mockito.times(1))
                .setCharacter(position.getX(), position.getY(), ' ');

    }

    @Test
    void drawLine() {
        String color = "#aaaaaa";
        int width = 2;
        gui.drawLine(color, position, width);

        Mockito.verify(tg, Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString(color));

        Mockito.verify(tg, Mockito.times(1))
                .drawLine(position.getX(), position.getY(), position.getX() + width, position.getY(), ' ');
    }

    @Test
    void drawCharacter() {
        String color = "#aaaaaa";
        Character c = 'c';
        gui.drawCharacter(color, c, position);

        Mockito.verify(tg, Mockito.times(1))
                .setCharacter(position.getX(), position.getY(), c);
    }

    @Test
    void refresh() {
        gui.refresh();

        try {
            Mockito.verify(screen, Mockito.times(1))
                    .refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void close() {
        gui.close();

        try {
            Mockito.verify(screen, Mockito.times(1))
                    .close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void clear() {
        gui.clear();

        Mockito.verify(screen, Mockito.times(1))
                .clear();
    }
}