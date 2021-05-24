package com.shootemup.g53.controller.input;

import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AWTInputController extends InputController<KeyEvent> {

    public AWTInputController(Terminal terminal) {
        super();
        setup(terminal);
    }

    void setup(Terminal terminal) {
        ((AWTTerminalFrame) terminal).getComponent(0).addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                Action act = eventToAction(e);
                keysPressed.put(act, true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Action act = eventToAction(e);
                keysPressed.put(act, false);
            }
        });
    }

    @Override
    public Action eventToAction(KeyEvent event) {
        if (event == null)
            return Action.NONE;

        switch (event.getKeyChar()) {
            case KeyEvent.VK_ESCAPE:
                return Action.ESC;
            case 'w':
            case 'W':
                return Action.W;
            case 'a':
            case 'A':
                return Action.A;
            case 's':
            case 'S':
                return Action.S;
            case 'd':
            case 'D':
                return Action.D;
            case 'q':
            case 'Q':
                return Action.Q;
        }

        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                return Action.LEFT;
            case KeyEvent.VK_UP:
                return Action.UP;
            case KeyEvent.VK_RIGHT:
                return Action.RIGHT;
            case KeyEvent.VK_DOWN:
                return Action.DOWN;
            case KeyEvent.VK_SPACE:
                return Action.SPACE;
        }

        return Action.NONE;
    }

    @Override
    public void handleEvent(KeyEvent event) {

    }
}
