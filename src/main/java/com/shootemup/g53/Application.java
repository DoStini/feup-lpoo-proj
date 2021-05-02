package com.shootemup.g53;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.controller.spaceship.PlayerController;
import com.shootemup.g53.controller.spaceship.SpaceshipController;
import com.shootemup.g53.element.ExampleViewer;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.ui.LanternaGui;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Gui gui = new LanternaGui(20, 20);

        ExampleViewer viewer = new ExampleViewer();
        PlayerController controller = new PlayerController();
        Spaceship spaceship = new Spaceship(new Position(5, 5));

        while (true) {
            gui.clear();
            Action act = gui.readInput();
            controller.handle(spaceship, act);
            viewer.draw(gui, spaceship);
            gui.refresh();
            Thread.yield();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String testing() {
        return "Hello World!";
    }

}
