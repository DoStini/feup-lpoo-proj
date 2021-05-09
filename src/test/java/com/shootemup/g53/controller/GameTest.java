package com.shootemup.g53.controller;

import com.shootemup.g53.controller.firing.StraightBulletStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.MoveUpwardsMovement;
import com.shootemup.g53.controller.state.PlayState;
import com.shootemup.g53.controller.state.State;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GameTest {
    private State state;
    private GameModel gameModel;

    Gui gui;

    @BeforeEach
    void setup() {
        state = Mockito.mock(PlayState.class);
        gameModel = Mockito.mock(GameModel.class);
        Mockito.when(gameModel.isGameFinished()).thenReturn(true);

        gui = Mockito.mock(Gui.class);
    }

    @Test
    void testGameEnding(){

    }
}
