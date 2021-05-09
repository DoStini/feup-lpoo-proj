package com.shootemup.g53;

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

import java.util.Arrays;
import java.util.List;


public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        gameDemo();
    }

    public static void gameDemo() {
        Spaceship spaceship = new Spaceship(new Position(20, 35), 5, "#aae243", 2, 2);
        Spaceship enemy = new Spaceship(new Position(20, 5), 5, "#1212ee", 0, 10);
        List<Spaceship> enemySpaceships = Arrays.asList(enemy);

        int height = 40, width = 50;

        Gui gui = new LanternaGui(height,width, 15);

        GameModel gameModel = new GameModel(width,height, enemySpaceships);
        gameModel.setPlayer(spaceship);
        Game game = new Game(gui,gameModel);
        game.run();

        gui.refresh();
    }
}
