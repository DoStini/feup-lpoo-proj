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
        Gui gui = new LanternaGui(100, 100);

        ExampleViewer viewer = new ExampleViewer();
        Spaceship spaceship = new Spaceship(new Position(20, 5));
        Spaceship spaceship2 = new Spaceship(new Position(5, 5));
        Spaceship enemy = new Spaceship(new Position(10, 5));
        SpaceshipController aiController = new AIShootingController(enemy);
        SpaceshipController controller2 = new PlayerController(spaceship2);
        SpaceshipActiveController controller = new PlayerActiveController(spaceship);

        while (true) {
            gui.clear();
            Action act = gui.readInput();
            if (act == Action.ESC) {
                gui.close();
                break;
            }
            controller.handle(gui);
            controller2.handle(act);
            aiController.handle(Action.NONE);
            viewer.draw(gui, spaceship);
            viewer.draw(gui, spaceship2);
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
