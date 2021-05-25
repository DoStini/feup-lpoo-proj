package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.Background;
import com.shootemup.g53.model.element.Star;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mock;
import org.mockito.AdditionalMatchers.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BackgroundControllerTest {
    GameModel model;
    Background background;
    Random rng;
    long frame = 0;
    List<StarController> controllers;

    @BeforeEach
    void setUp() {
        model = Mockito.mock(GameModel.class);
        background = Mockito.spy(new Background(1, 15));
        rng = Mockito.mock(Random.class);

        controllers = new ArrayList<>();

        Star star = Mockito.spy(new Star(new Position(0,0),4));
        star.setSpeed(1);

        controllers.add(Mockito.spy(new StarController(star, new FallDownMovement())));
        controllers.add(Mockito.spy(new StarController(star, new FallDownMovement())));
        controllers.add(Mockito.spy(new StarController(star, new FallDownMovement())));

        Mockito.when(model.getWidth()).thenReturn(20);
        Mockito.when(model.getHeight()).thenReturn(2);

        Mockito.when(rng.nextInt(AdditionalMatchers.gt(0))).thenReturn(5);
    }

    @Test
    void constructors() {
        BackgroundController backgroundController = new BackgroundController(model, background, 30, 5);

        Assertions.assertEquals(model, backgroundController.model);
        Assertions.assertEquals(background, backgroundController.background);
        Assertions.assertEquals(30, backgroundController.maxDistance);
        Assertions.assertEquals(5, backgroundController.maxSpeed);
        Assertions.assertEquals(ArrayList.class, backgroundController.starControllerList.getClass());
        Assertions.assertEquals(1, backgroundController.starControllerList.size());


        BackgroundController backgroundController1 = new BackgroundController(model, background, 15, 5, rng);

        Assertions.assertEquals(rng, backgroundController1.rng);
        Assertions.assertEquals(model, backgroundController1.model);
        Assertions.assertEquals(background, backgroundController1.background);
        Assertions.assertEquals(5, backgroundController.maxSpeed);
        Assertions.assertEquals(15, backgroundController1.maxDistance);
    }

    @Test
    void starControllers() {
        BackgroundController backgroundController = new BackgroundController(model, background, 15, 5, rng);

        backgroundController.setStarControllerList(controllers);

        Assertions.assertEquals(controllers, backgroundController.starControllerList);

        StarController controller1 = new StarController(Mockito.mock(Star.class), new FallDownMovement());

        Assertions.assertFalse(backgroundController.starControllerList.contains(controller1));

        backgroundController.addStarController(controller1);

        Assertions.assertTrue(backgroundController.starControllerList.contains(controller1));

        backgroundController.removeStarController(controller1);

        Assertions.assertFalse(backgroundController.starControllerList.contains(controller1));
    }

    @Test
    void handle() {
        BackgroundController backgroundController = Mockito.spy(new BackgroundController(model, background, 5, 5, rng));

        backgroundController.handle(frame);

        Mockito.verify(rng, Mockito.times(5)).nextInt(Mockito.anyInt());

        int top = model.getWidth();

        Mockito.verify(rng, Mockito.times(2)).nextInt(top);
        Mockito.verify(rng, Mockito.times(2)).nextInt(backgroundController.maxDistance);

        Star star = backgroundController.background.getStars().get(0);

        Mockito.verify(background, Mockito.times(1)).addStar(star);

        Assertions.assertEquals(5 , star.getDistance());
        Assertions.assertEquals(0 , star.getPosition().getY());
        Assertions.assertEquals(5, star.getPosition().getX());
        Assertions.assertEquals(Math.max(1,5/(1+star.getDistance())), star.getSpeed());

        Assertions.assertEquals(1, backgroundController.starControllerList.size());

        StarController controller = backgroundController.starControllerList.get(0);

        Mockito.verify(backgroundController, Mockito.times(1)).addStarController(controller);

        backgroundController.setStarControllerList(controllers);

        backgroundController.handle(frame);

        for(StarController controller1 : controllers) {
            Mockito.verify(controller1, Mockito.times(1)).handle(frame);
        }

        Mockito.verify(rng, Mockito.times(5)).nextInt(Mockito.anyInt());

        Assertions.assertEquals(1, backgroundController.background.getStars().size());

        backgroundController.handle(frame);

        backgroundController.handle(frame);


        Mockito.verify(background, Mockito.times(4)).removeStar(Mockito.any());
        Mockito.verify(backgroundController, Mockito.times(4)).removeStarController(Mockito.any());


        Assertions.assertEquals(1, backgroundController.background.getStars().size());


    }
}