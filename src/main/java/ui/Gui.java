package ui;

import model.util.Position;

public interface Gui {
    void drawColor(String color, Position pos);
    void drawLine(String color, Position pos, int width);
    void drawCharacter(String color, Character c, Position pos);
    void refresh();
    void close();
    void clear();
}