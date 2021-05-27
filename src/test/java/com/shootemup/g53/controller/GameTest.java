package com.shootemup.g53.controller;

import com.shootemup.g53.controller.state.MenuState;
import com.shootemup.g53.controller.state.PlayState;
import com.shootemup.g53.controller.state.State;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GameTest {

    private State state;
    private GameModel gameModel;
    private Game game;

    Gui gui;

    private class CustomState extends State<GameModel> {
        int counter = 5;
        private Game game;
        CustomState (Game game) {
            this.game = game;
        }

        @Override
        public GameModel getStateModel() {
            return null;
        }

        @Override
        public void exit() {

        }

        @Override
        public Viewer<GameModel> getStateView() {
            return null;
        }

        @Override
        public GenericController getStateController() {
            return null;
        }

        @Override
        public void run() {
            if ((--counter) <= 0)
                game.setToExit();
        }
    }

    @BeforeEach
    void setup() {
        state = Mockito.mock(PlayState.class);
        gameModel = Mockito.mock(GameModel.class);
        Mockito.when(gameModel.isGameFinished()).thenReturn(true);
        game = new Game(gui, gameModel);
        state = Mockito.spy(new CustomState(game));
        gui = Mockito.mock(Gui.class);

    }

    @Test
    void testRun(){
        Assertions.assertEquals(MenuState.class, game.getState().getClass());
        game.changeState(state);
        game.run();
        Mockito.verify(state, Mockito.times(5)).run();
    }
}
