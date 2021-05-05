package com.shootemup.g53;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.controller.spaceship.AIShootingController;
import com.shootemup.g53.controller.spaceship.PlayerController;
import com.shootemup.g53.controller.spaceship.SpaceshipController;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.ui.LanternaGui;
import com.shootemup.g53.view.element.*;
import com.shootemup.g53.view.element.spaceship.EnemyView;
import com.shootemup.g53.view.element.spaceship.PlayerView;


public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        controllersExample();
        viewsDemo();
    }

    public static void controllersExample() {
        Gui gui = new LanternaGui(100, 100, 10);

        ExampleViewer viewer = new ExampleViewer();
        Spaceship spaceship = new Spaceship(new Position(20, 35), 10, "#aae243");
        Spaceship spaceship2 = new Spaceship(new Position(10, 35), 10, "#1212ee");
        Spaceship enemy = new Spaceship(new Position(30, 35), 10, "#1212ee");
        SpaceshipController aiController = new AIShootingController(enemy);
        SpaceshipController controller = new PlayerController(spaceship);

        while (true) {
            gui.clear();

            if (gui.isActionActive(Action.ESC)) {
                gui.close();
                break;
            }

            controller.handle(gui);
            aiController.handle(gui);
            viewer.draw(gui, spaceship);
            viewer.draw(gui, enemy);
            gui.refresh();
            Thread.yield();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
