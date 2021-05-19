package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Button;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class ButtonView implements ElementView<Button>{

    @Override
    public void draw(Gui gui, Button element) {
        Position upperLeft = element.getPosition();
        upperLeft.setX(gui.getWidth()/ 2 + 8);
        for(int y = upperLeft.getY(); y < upperLeft.getY() + element.getHeight(); y++){
            Position leftMost = new Position(upperLeft.getX(), y);
            gui.drawLine(element.getColor(),leftMost, element.getWidth());
            if (element.isActive()) {
                gui.drawColor("#FFFF00",leftMost);
                gui.drawColor("#FFFF00",new Position(leftMost.getX() + element.getWidth(), leftMost.getY()));
            }
        }
        if (element.isActive()) {
            gui.drawLine("#FFFF00",upperLeft, element.getWidth());
            gui.drawLine("#FFFF00",new Position(upperLeft.getX(), upperLeft.getY() + element.getHeight()), element.getWidth());
        }
        gui.drawText("#FFFF00", element.getText(),new Position(upperLeft.getX() + element.getWidth()/ 2, upperLeft.getY() + element.getHeight()/ 2 ), element.getColor());

    }
}
