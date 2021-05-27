package com.shootemup.g53.controller.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class InputControllerTest {
    @Test
    void constructor() {
        InputController<?> controller = Mockito.mock(InputController.class, Mockito.withSettings().useConstructor().defaultAnswer(Mockito.CALLS_REAL_METHODS));

        Assertions.assertEquals(new HashMap<Action, Boolean>(), controller.keysPressed);
    }

    @Test
    void actionActive() {
        InputController<?> controller = Mockito.mock(InputController.class, Mockito.withSettings().useConstructor().defaultAnswer(Mockito.CALLS_REAL_METHODS));

        Assertions.assertFalse(controller.isActionActive(Action.A));

        controller.keysPressed.put(Action.A, true);

        Assertions.assertFalse(controller.isActionActive(Action.D));
        Assertions.assertTrue(controller.isActionActive(Action.A));
    }
}