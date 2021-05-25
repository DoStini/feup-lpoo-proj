package com.shootemup.g53.ui;

import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.model.util.Position;

public interface Gui {
    void drawColor(int red, int green, int blue, Position pos);
    void drawColor(String color, Position pos);
    void drawLine(String color, Position pos, int width);
    void drawCharacter(String color, Character c, Position pos);
    boolean isActionActive(Action act);
    void refresh();
    void close();
    void clear();
}
