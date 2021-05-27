package com.shootemup.g53.controller.input;

import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

class InputControllerTest {
    AWTTerminalFrame terminal;
    Component component;
    KeyListener keyListener;

    @BeforeEach
    void setUp() {
        terminal = Mockito.mock(AWTTerminalFrame.class);
        component = Mockito.mock(Component.class);
        Mockito.when(terminal.getComponent(0)).thenReturn(component);
        Mockito.doAnswer(invocation -> {
            keyListener = (KeyListener) invocation.getArguments()[0];
            return null;
        }).when(component).addKeyListener(Mockito.any());
    }

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

        controller.keysPressed.put(Action.A, false);

        Assertions.assertFalse(controller.isActionActive(Action.A));
    }

    @Test
    void awtConstructor() {
        AWTInputController controller = Mockito.spy(new AWTInputController(terminal));
        Mockito.verify(component, Mockito.times(1)).addKeyListener(keyListener);

        controller.setup(terminal);

        Mockito.verify(component, Mockito.times(1)).addKeyListener(keyListener);

        KeyEvent keyEvent = Mockito.mock(KeyEvent.class);

        Mockito.when(keyEvent.getKeyChar()).thenReturn('4');
        Mockito.when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_E);

        Assertions.assertEquals(0, controller.keysPressed.size());
        Assertions.assertFalse(controller.isActionActive(Action.NONE));
        keyListener.keyPressed(keyEvent);
        Mockito.verify(controller, Mockito.times(1)).eventToAction(keyEvent);
        Assertions.assertEquals(1, controller.keysPressed.size());
        Assertions.assertTrue(controller.isActionActive(Action.NONE));
        keyListener.keyReleased(keyEvent);
        Mockito.verify(controller, Mockito.times(2)).eventToAction(keyEvent);
        Assertions.assertEquals(1, controller.keysPressed.size());
        Assertions.assertFalse(controller.isActionActive(Action.NONE));
    }

    @Test
    void eventToAction() {
        AWTInputController controller = Mockito.spy(new AWTInputController(terminal));
        controller.setup(terminal);

        KeyEvent keyEvent = Mockito.mock(KeyEvent.class);

        Mockito.when(keyEvent.getKeyChar()).thenReturn('e');
        Assertions.assertEquals(Action.POWER_1, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyChar()).thenReturn('r');
        Assertions.assertEquals(Action.POWER_2, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyChar()).thenReturn('w');
        Assertions.assertEquals(Action.W, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyChar()).thenReturn('a');
        Assertions.assertEquals(Action.A, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyChar()).thenReturn('s');
        Assertions.assertEquals(Action.S, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyChar()).thenReturn('d');
        Assertions.assertEquals(Action.D, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyChar()).thenReturn('q');
        Assertions.assertEquals(Action.Q, controller.eventToAction(keyEvent));

        Mockito.when(keyEvent.getKeyChar()).thenReturn('E');
        Assertions.assertEquals(Action.POWER_1, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyChar()).thenReturn('R');
        Assertions.assertEquals(Action.POWER_2, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyChar()).thenReturn('W');
        Assertions.assertEquals(Action.W, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyChar()).thenReturn('A');
        Assertions.assertEquals(Action.A, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyChar()).thenReturn('S');
        Assertions.assertEquals(Action.S, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyChar()).thenReturn('D');
        Assertions.assertEquals(Action.D, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyChar()).thenReturn('Q');
        Assertions.assertEquals(Action.Q, controller.eventToAction(keyEvent));

        Mockito.when(keyEvent.getKeyChar()).thenReturn('0');
        Mockito.when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_ESCAPE);
        Assertions.assertEquals(Action.ESC, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);
        Assertions.assertEquals(Action.LEFT, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_UP);
        Assertions.assertEquals(Action.UP, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);
        Assertions.assertEquals(Action.RIGHT, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);
        Assertions.assertEquals(Action.DOWN, controller.eventToAction(keyEvent));
        Mockito.when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_SPACE);
        Assertions.assertEquals(Action.SPACE, controller.eventToAction(keyEvent));

        Assertions.assertEquals(Action.NONE, controller.eventToAction(null));
    }
}