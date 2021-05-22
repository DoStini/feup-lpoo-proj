package com.shootemup.g53.controller.element;

import com.shootemup.g53.model.element.Background;
import com.shootemup.g53.model.element.Star;
import com.shootemup.g53.model.game.GameModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mock;
import org.mockito.AdditionalMatchers.*;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BackgroundControllerTest {
    GameModel model;
    Background background;
    Random rng;

    @BeforeEach
    void setUp() {
        model = Mockito.mock(GameModel.class);
        background = Mockito.spy(new Background(1, 15));
        rng = Mockito.mock(Random.class);

        Mockito.when(model.getWidth()).thenReturn(20);
        Mockito.when(rng.nextInt(AdditionalMatchers.gt(0))).thenReturn(5);
    }

    @Test
    void constructors() {
        BackgroundController backgroundController = new BackgroundController(model, background, 30);

        Assertions.assertEquals(model, backgroundController.model);
        Assertions.assertEquals(background, backgroundController.background);
        Assertions.assertEquals(30, backgroundController.maxDistance);

        BackgroundController backgroundController1 = new BackgroundController(model, background, 15, rng);

        Assertions.assertEquals(rng, backgroundController1.rng);
        Assertions.assertEquals(model, backgroundController1.model);
        Assertions.assertEquals(background, backgroundController1.background);
        Assertions.assertEquals(15, backgroundController1.maxDistance);
    }

    @Test
    void handle() {
        BackgroundController backgroundController = new BackgroundController(model, background, 5, rng);

        backgroundController.handle();

        Mockito.verify(rng, Mockito.times(2)).nextInt(Mockito.anyInt());

        int top = model.getWidth();

        Mockito.verify(rng, Mockito.times(1)).nextInt(top);
        Mockito.verify(rng, Mockito.times(1)).nextInt(backgroundController.maxDistance);

        Star star = backgroundController.background.getStars().get(0);

        Mockito.verify(background, Mockito.times(1)).addStar(star);

        Assertions.assertEquals(5 , star.getDistance());
        Assertions.assertEquals(0 , star.getPosition().getY());
        Assertions.assertEquals(5, star.getPosition().getX());

        backgroundController.handle();

        Mockito.verify(rng, Mockito.times(2)).nextInt(Mockito.anyInt());

        Assertions.assertEquals(1, backgroundController.background.getStars().size());
    }
}