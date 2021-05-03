package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.shapes.CircleDrawer;
import com.shootemup.g53.view.shapes.Drawer;

public class CoinView implements ElementView<Coin> {

    private final int radius;

    public CoinView(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Gui gui, Coin element) {
        Drawer drawer = new CircleDrawer("#cc9900", radius);
        drawer.draw(gui, element.getPosition());
        drawer = new CircleDrawer("#ffff00", Math.max(radius-2, 1));
        drawer.draw(gui, element.getPosition());
    }
}
