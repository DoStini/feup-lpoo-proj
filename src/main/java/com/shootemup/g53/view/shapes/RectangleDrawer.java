package com.shootemup.g53.view.shapes;

import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class RectangleDrawer implements Drawer{
    private String color;
    private int width;
    private int height;

    public RectangleDrawer(String color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Gui gui, Position position) {
        for (int i = 0; i < height; i++){
            gui.drawLine(color,position,width);
            position.setY(position.getY() + 1);
        }

    }
}
