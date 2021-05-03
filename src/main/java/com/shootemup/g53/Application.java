package com.shootemup.g53;

import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.ui.LanternaGui;
import com.shootemup.g53.view.element.AsteroidView;
import com.shootemup.g53.view.element.ElementViewer;
import com.shootemup.g53.view.element.spaceship.EnemyViewer;
import com.shootemup.g53.view.element.spaceship.PlayerViewer;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Gui gui = new LanternaGui(50,50, 2);

        Spaceship player = new Spaceship(new Position(20, 35));
        Spaceship enemy = new Spaceship(new Position(20, 10));

        Asteroid asteroid = new Asteroid(new Position(30, 30));

        ElementViewer<Spaceship> playerViewer = new PlayerViewer("#aaea12", 10, 3);
        ElementViewer<Spaceship> enemyViewer = new EnemyViewer("#8c2d19", 5, 2);
        ElementViewer<Asteroid> asteroidViewer = new AsteroidView(5);

        playerViewer.draw(gui, player);
        enemyViewer.draw(gui, enemy);
        asteroidViewer.draw(gui, asteroid);

        gui.refresh();
    }

    public String testing() {
        return "Hello World!";
    }

}
