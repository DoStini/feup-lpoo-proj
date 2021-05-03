package com.shootemup.g53;

import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.ui.LanternaGui;
import com.shootemup.g53.view.element.AsteroidViewer;
import com.shootemup.g53.view.element.BulletViewer;
import com.shootemup.g53.view.element.CoinViewer;
import com.shootemup.g53.view.element.SpaceShipViewer;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Gui gui = new LanternaGui(250,250);
        SpaceShipViewer exampleViewer = new SpaceShipViewer();
        exampleViewer.draw(gui, new Spaceship(new Position(25, 50), 30, "#F3F3F3"));

        new CoinViewer().draw(gui,new Coin(new Position(1,1), "#F3F3F3"));
        new BulletViewer().draw(gui,new Bullet(new Position(150,150), "#F3F3F3"));
        new AsteroidViewer().draw(gui, new Asteroid(new Position(100,100), "#F3F3F3"));
        gui.refresh();
    }

    public String testing() {
        return "Hello World!";
    }

}
