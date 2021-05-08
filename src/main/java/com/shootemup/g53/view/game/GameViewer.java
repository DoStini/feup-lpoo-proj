package com.shootemup.g53.view.game;

import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.element.BulletView;
import com.shootemup.g53.view.element.spaceship.EnemyView;
import com.shootemup.g53.view.element.spaceship.PlayerView;
import com.shootemup.g53.view.element.spaceship.SpaceshipView;

public class GameViewer extends Viewer<GameModel> {
    private Gui gui;
    public GameViewer(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void draw(GameModel model) {
        //for now we only have enemies to draw
        gui.clear();
        SpaceshipView spaceshipView = new PlayerView(2);
        SpaceshipView enemyView = new EnemyView(2);
        spaceshipView.draw(gui, model.getPlayer());
        for(Spaceship enemy: model.getEnemySpaceships()){
            enemyView.draw(gui,enemy);
        }

        for (Bullet bullet: model.getEnemyBullets()) {
            new BulletView().draw(gui, bullet);
        }
        for (Bullet bullet: model.getPlayerBullets()) {
            new BulletView().draw(gui, bullet);
        }

        gui.refresh();
    }
}
