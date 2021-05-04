package com.shootemup.g53.ui;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.controller.input.InputController;
import com.shootemup.g53.model.util.Position;

public interface Gui<T> {
    void drawColor(String color, Position pos);
    void drawLine(String color, Position pos, int width);
    void drawCharacter(String color, Character c, Position pos);
    boolean isActionActive(Action act);
    void refresh();
    void close();
    void clear();
}
