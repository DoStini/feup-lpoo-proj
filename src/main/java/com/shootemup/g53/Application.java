package com.shootemup.g53;

import com.shootemup.g53.controller.Action;
import com.shootemup.g53.controller.spaceship.*;
import com.shootemup.g53.element.ExampleViewer;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.ui.LanternaGui;


public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        controllersExample();
    }

    public static void controllersExample() {
        Gui gui = new LanternaGui(100, 100);

        ExampleViewer viewer = new ExampleViewer();
        Spaceship spaceship = new Spaceship(new Position(20, 5));
        Spaceship spaceship2 = new Spaceship(new Position(5, 5));
        Spaceship enemy = new Spaceship(new Position(10, 5));
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

    public String testing() {
        return "Hello World!";
    }

}
