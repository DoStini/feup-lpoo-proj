package com.shootemup.g53.controller.input;

import java.util.HashMap;
import java.util.Map;

public abstract class InputController<T> {
    protected Map<Action, Boolean> keysPressed;

    InputController() {
        keysPressed = new HashMap<>();
    }

    public boolean isActionActive(Action act) {
        return keysPressed.getOrDefault(act, false);
    }

    public abstract void handleEvent(T event);

    public abstract Action eventToAction(T event);

}
