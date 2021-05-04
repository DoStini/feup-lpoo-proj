package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class CoinViewer {
    public void draw(Gui gui, Coin coin) {
        Position position = coin.getPosition();
        gui.drawCharacter(coin.getColor(), 'C', position);
    }
}
