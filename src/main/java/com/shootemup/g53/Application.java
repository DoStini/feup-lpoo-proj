package com.shootemup.g53;

import com.shootemup.g53.controller.Game;

import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.ui.LanternaGui;


public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        gameDemo();

    }

    public static void gameDemo() {

        int height = 100, width = 60;

        Gui gui = new LanternaGui(height,width, 15);
        GameModel gameModel = new GameModel(width, height);
        Game game = new Game(gui, gameModel);
        game.run();

    }
}
