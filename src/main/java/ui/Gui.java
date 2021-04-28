package ui;

import model.util.Position;

public interface Gui {
    void drawColor(String color, Position pos);
    void drawText(String color, String text, Position pos);
    void refresh();
    void close();
    void clear();
}
