package com.shootemup.g53;

import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.controller.Game;

import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.ui.LanternaGui;
import com.shootemup.g53.view.element.AsteroidView;
import com.shootemup.g53.view.element.BulletView;
import com.shootemup.g53.view.element.CoinView;
import com.shootemup.g53.view.element.ElementView;
import com.shootemup.g53.view.element.spaceship.EnemyView;
import com.shootemup.g53.view.element.spaceship.PlayerView;

public class Application {
    private static int height = 100;
    private static int width = 100;

    public static void main(String[] args) {
        gameDemo();
    }

    public static void viewsDemo() {
        Gui gui = new LanternaGui(50, 50, 2);

        Spaceship player = new Spaceship(new Position(20, 35), 10, "#aae243");
        Spaceship enemy = new Spaceship(new Position(20, 35), 10, "#1212ee");
    }
    public static void gameDemo() {
        Gui gui = new LanternaGui(height,width, 2);

        GameModel gameModel = new GameModel(width,height);
        Game game = new Game(gui,gameModel);
        game.run();

        gui.refresh();
    }
}
