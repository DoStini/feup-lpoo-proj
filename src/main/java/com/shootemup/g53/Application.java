package com.shootemup.g53;

import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.ui.LanternaGui;
import com.shootemup.g53.view.element.AsteroidView;
import com.shootemup.g53.view.element.CoinView;
import com.shootemup.g53.view.element.ElementView;
import com.shootemup.g53.view.element.spaceship.EnemyView;
import com.shootemup.g53.view.element.spaceship.PlayerView;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Gui gui = new LanternaGui(50,50, 2);

        Spaceship player = new Spaceship(new Position(20, 35));
        Spaceship enemy = new Spaceship(new Position(20, 10));

        Asteroid asteroid = new Asteroid(new Position(30, 30));
        Coin coin = new Coin(new Position(5, 5));

        ElementView<Spaceship> playerViewer = new PlayerView("#aaea12", 10, 3);
        ElementView<Spaceship> enemyViewer = new EnemyView("#8c2d19", 5, 2);
        ElementView<Asteroid> asteroidViewer = new AsteroidView(5);
        ElementView<Coin> coinElementView = new CoinView(6);

        playerViewer.draw(gui, player);
        enemyViewer.draw(gui, enemy);
        asteroidViewer.draw(gui, asteroid);
        coinElementView.draw(gui, coin);

        gui.refresh();
    }

    public String testing() {
        return "Hello World!";
    }

}
