package com.shootemup.g53.model.game;

import com.shootemup.g53.model.element.Background;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {
    Background background;

    @BeforeEach
    void setUp() {
        background = Mockito.mock(Background.class);
    }

    @Test
    void settersGetters() {
        GameModel gameModel = new GameModel(10,10);

        gameModel.setBackground(background);

        Assertions.assertEquals(gameModel.getBackground(), gameModel.getBackground());
        Assertions.assertEquals(background, gameModel.getBackground());
    }
}