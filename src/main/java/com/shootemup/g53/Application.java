package com.shootemup.g53;

import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.ui.LanternaGui;
import com.shootemup.g53.view.element.AsteroidView;
import com.shootemup.g53.view.element.BulletView;
import com.shootemup.g53.view.element.CoinView;
import com.shootemup.g53.view.element.ElementView;
import com.shootemup.g53.view.element.spaceship.EnemyView;
import com.shootemup.g53.view.element.spaceship.PlayerView;

public class Application {
    public static void main(String[] args) {
        viewsDemo();
    }

    public static void viewsDemo() {
        System.out.println("Hello world!");
        Gui gui = new LanternaGui(50,50, 2);

        Spaceship player = new Spaceship(new Position(20, 35));
        Spaceship enemy = new Spaceship(new Position(20, 10));

        Asteroid asteroid = new Asteroid(new Position(30, 30));
        Coin coin = new Coin(new Position(5, 5));
        Bullet bullet = new Bullet(new Position(40, 40));

        ElementView<Spaceship> playerViewer = new PlayerView("#aaea12", 10, 3);
        ElementView<Spaceship> enemyViewer = new EnemyView("#8c2d19", 5, 2);
        ElementView<Asteroid> asteroidViewer = new AsteroidView(5);
        ElementView<Coin> coinElementView = new CoinView(6);
        ElementView<Bullet> bulletElementView = new BulletView(5);

        playerViewer.draw(gui, player);
        enemyViewer.draw(gui, enemy);
        asteroidViewer.draw(gui, asteroid);
        coinElementView.draw(gui, coin);
        bulletElementView.draw(gui, bullet);

        gui.refresh();
    }
}
