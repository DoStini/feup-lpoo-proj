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
import com.shootemup.g53.controller.Game;

import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.ui.LanternaGui;
import com.shootemup.g53.view.element.*;
import com.shootemup.g53.view.element.spaceship.EnemyView;
import com.shootemup.g53.view.element.spaceship.PlayerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Application {
    private static int height = 300;
    private static int width = 300;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        gameDemo();

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
        Gui gui = new LanternaGui(50, 50, 2);

        Spaceship player = new Spaceship(new Position(20, 35), 10, "#aae243");
        Spaceship enemy = new Spaceship(new Position(20, 35), 10, "#1212ee");
    }
    public static void gameDemo() {
        Spaceship spaceship = new Spaceship(new Position(20, 35), 10, "#aae243");
        Spaceship enemy = new Spaceship(new Position(20, 35), 10, "#1212ee");
        List<Spaceship> enemySpaceships = Arrays.asList(enemy);
        Gui gui = new LanternaGui(height,width, 5);

        GameModel gameModel = new GameModel(width,height, enemySpaceships);
        gameModel.setPlayer(spaceship);
        Game game = new Game(gui,gameModel);
        game.run();

        gui.refresh();
    }
}
