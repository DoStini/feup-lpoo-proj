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

        int height = 70, width = 80;

        Gui gui = new LanternaGui(height,width, 15);
        Game game = new Game(gui);
        game.run();
        gui.close();

    }
}
