package com.shootemup.g53;

import com.shootemup.g53.controller.gameBuilder.GameBuilder;
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

        int height = 50, width = 100;

        Gui gui = new LanternaGui(height,width, 15);

        GameModel gameModel = new GameBuilder().buildGame(5,3,width,height);
        Game game = new Game(gui,gameModel);
        game.run();
      
    }
}
