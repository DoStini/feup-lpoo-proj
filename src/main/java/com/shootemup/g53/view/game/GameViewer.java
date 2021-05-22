package com.shootemup.g53.view.game;

import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.element.AsteroidView;
import com.shootemup.g53.view.element.BulletView;
import com.shootemup.g53.view.element.CoinView;
import com.shootemup.g53.view.element.StarView;
import com.shootemup.g53.view.element.spaceship.EnemyView;
import com.shootemup.g53.view.element.spaceship.PlayerView;
import com.shootemup.g53.view.element.spaceship.SpaceshipView;

public class GameViewer extends Viewer<GameModel> {
    private Gui gui;
    private SpaceshipView spaceshipView;
    private SpaceshipView enemyView;
    private CoinView coinView;
    private BulletView bulletView;
    private StarView starView;

    public GameViewer(Gui gui) {
        this.gui = gui;
        spaceshipView = new PlayerView(2);
        enemyView = new EnemyView(2);
        coinView = new CoinView();
        bulletView = new BulletView();
        starView = new StarView(0.1);
    }

    @Override
    public void draw(GameModel model) {
        //for now we only have enemies to draw
        gui.clear();


        starView.draw(gui, new Star(new Position(10, 10),0));
        starView.draw(gui, new Star(new Position(12, 10),5));
        starView.draw(gui, new Star(new Position(14, 10),10));
        starView.draw(gui, new Star(new Position(16, 10),15));
        starView.draw(gui, new Star(new Position(18, 10),20));
        starView.draw(gui, new Star(new Position(20, 10),25));
        starView.draw(gui, new Star(new Position(22, 10),30));
        starView.draw(gui, new Star(new Position(24, 10),35));

        spaceshipView.draw(gui, model.getPlayer());
        for(Spaceship enemy: model.getEnemySpaceships()){
            enemyView.draw(gui,enemy);
        }
        for(Coin coin: model.getCoins()){
            coinView.draw(gui,coin);
        }
        for(Bullet bullet: model.getBulletList()){
            bulletView.draw(gui,bullet);
        }

        gui.refresh();
    }
}
