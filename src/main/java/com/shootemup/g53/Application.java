package com.shootemup.g53;

import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
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
        Gui gui = new LanternaGui(50,50, 2);

        Spaceship player = new Spaceship(new Position(20, 35), 10, "#aae243");
        Spaceship enemy = new Spaceship(new Position(20, 35), 10, "#1212ee");

        Asteroid asteroid = new Asteroid(new Position(30, 30), 10);
        Coin coin = new Coin(new Position(5, 5), 4);
        Bullet bullet = new Bullet(new Position(40, 40), "#ff0000", 5);

        ElementView<Spaceship> playerViewer = new PlayerView(4);
        ElementView<Spaceship> enemyViewer = new EnemyView(2);
        ElementView<Asteroid> asteroidViewer = new AsteroidView();
        ElementView<Coin> coinElementView = new CoinView();
        ElementView<Bullet> bulletElementView = new BulletView();

        playerViewer.draw(gui, player);
        enemyViewer.draw(gui, enemy);
        asteroidViewer.draw(gui, asteroid);
        coinElementView.draw(gui, coin);
        bulletElementView.draw(gui, bullet);

        gui.refresh();
    }
}
