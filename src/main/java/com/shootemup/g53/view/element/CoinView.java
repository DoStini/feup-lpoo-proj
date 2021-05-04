package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.shapes.CircleDrawer;
import com.shootemup.g53.view.shapes.Drawer;

public class CoinView implements ElementView<Coin> {

    @Override
    public void draw(Gui gui, Coin element) {
        Drawer drawer = new CircleDrawer("#cc9900", element.getRadius());
        drawer.draw(gui, element.getPosition());
        drawer = new CircleDrawer("#ffff00", Math.max(element.getRadius()-2, 1));
        drawer.draw(gui, element.getPosition());
    }
}
