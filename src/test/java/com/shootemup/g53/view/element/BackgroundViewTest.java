package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Background;
import com.shootemup.g53.model.element.Star;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BackgroundViewTest {
    StarView starView;
    Gui gui;
    List<Star> stars;
    Background background;

    @BeforeEach
    void setUp() {
        starView = Mockito.mock(StarView.class);
        gui = Mockito.mock(Gui.class);
        stars = new ArrayList<>();
        stars.add(new Star(new Position(0,1), 1));
        stars.add(new Star(new Position(1,1), 1));
        stars.add(new Star(new Position(1,0), 4));
        background = Mockito.spy(new Background(stars, 3, 4));
    }
    @Test
    void constructor() {
        BackgroundView backgroundView = new BackgroundView(starView);

        Assertions.assertEquals(starView, backgroundView.starView);
    }

    @Test
    void draw() {
        BackgroundView backgroundView = new BackgroundView(starView);

        backgroundView.draw(gui, background);

        for(Star star : stars) {
            Mockito.verify(starView, Mockito.times(1)).draw(gui, star);
        }
    }
}