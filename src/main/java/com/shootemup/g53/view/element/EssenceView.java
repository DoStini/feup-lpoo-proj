package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Essence;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.shapes.CircleDrawer;

public class EssenceView implements ElementView<Essence> {

    @Override
    public void draw(Gui gui, Essence element) {
        new CircleDrawer(element.getColor(), 1).draw(gui, element.getPosition());
        gui.drawColor("#006600", element.getPosition());
    }
}
