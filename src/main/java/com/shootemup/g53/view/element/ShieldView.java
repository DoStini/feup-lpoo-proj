package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Shield;
import com.shootemup.g53.ui.Gui;

public class ShieldView implements ElementView<Shield> {

    @Override
    public void draw(Gui gui, Shield element) {
        int width = element.getWidth();
        for (int i = 0; i < 2; i++) {
            gui.drawLine(element.getColor(), element.getPosition().getLeft(width/2).getDown(i), width);
        }
    }
}
