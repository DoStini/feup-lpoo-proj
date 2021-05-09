package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.controller.spaceship.PlayerController;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameControllerTest {
    private GameModel gameModel;
    private Position position;
    private Position position2;
    private Bullet bullet;
    private Bullet bullet2;

    Gui gui;

    @BeforeEach
    void setup() {
        gameModel = Mockito.mock(GameModel.class);
        position = Mockito.mock(Position.class);
        position2 = Mockito.mock(Position.class);

        bullet = Mockito.mock(Bullet.class);
        bullet2 = Mockito.mock(Bullet.class);
        Mockito.when(gameModel.getWidth()).thenReturn(50);
        Mockito.when(gameModel.getHeight()).thenReturn(50);
        Mockito.when(gameModel.getBulletList()).thenReturn(Arrays.asList(bullet, bullet2));
        Mockito.when(position.getX()).thenReturn(5);
        Mockito.when(position.getY()).thenReturn(5);
        Mockito.when(position2.getX()).thenReturn(55);
        Mockito.when(position2.getY()).thenReturn(55);

        Mockito.when(bullet.getPosition()).thenReturn(position2);
        Mockito.when(bullet.move()).thenReturn(position2);

        Mockito.when(bullet2.getPosition()).thenReturn(position);
        Mockito.when(bullet2.move()).thenReturn(position);
        gui = Mockito.mock(Gui.class);
    }

    @Test
    void handleEscKeyPress() {
        Mockito.when(gui.isActionActive(Action.ESC)).thenReturn(true);
        GameController gameController = new GameController(gameModel);
        gameController.handleKeyPress(gui);
        Mockito.verify(gameModel, Mockito.times(1))
                .setGameFinished(true);
    }

    @Test
    void checkInsideBoounds() {
        GameController gameController = new GameController(gameModel);
        assertEquals(gameController.insideBounds(position), true);
        assertEquals(gameController.insideBounds(position2), false);
        Mockito.verify(gameModel, Mockito.times(2))
                .getWidth();
        Mockito.verify(gameModel, Mockito.times(1))
                .getHeight();
    }

    @Test
    void bulletHandleTest() {
        GameController gameController = new GameController(gameModel);
        gameController.handleBullets();
        Mockito.verify(gameModel, Mockito.times(1))
                .removeBullet(bullet);

        Mockito.verify(bullet, Mockito.times(1))
                .move();
        Mockito.verify(bullet, Mockito.times(0))
                .setPosition(position2);
        Mockito.verify(bullet2, Mockito.times(1))
                .move();
        Mockito.verify(bullet2, Mockito.times(1))
                .setPosition(position);
    }
}
