package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.shapes.CircleDrawer;
import com.shootemup.g53.view.shapes.Drawer;

public class AsteroidView implements ElementView<Asteroid> {

    @Override
    public void draw(Gui gui, Asteroid element) {
        Drawer drawer = new CircleDrawer("#333333", element.getRadius());
        drawer.draw(gui, element.getPosition());
        drawer = new CircleDrawer("#555555", Math.max(element.getRadius() -2, 1));
        drawer.draw(gui, element.getPosition());
    }
}
