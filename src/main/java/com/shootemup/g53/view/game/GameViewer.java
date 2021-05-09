package com.shootemup.g53.view.game;

import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.element.BulletView;
import com.shootemup.g53.view.element.CoinView;
import com.shootemup.g53.view.element.spaceship.EnemyView;
import com.shootemup.g53.view.element.spaceship.PlayerView;
import com.shootemup.g53.view.element.spaceship.SpaceshipView;

public class GameViewer extends Viewer<GameModel> {
    private Gui gui;
    private SpaceshipView spaceshipView;
    private SpaceshipView enemyView;
    private CoinView coinView;
    private BulletView bulletView;
    public GameViewer(Gui gui) {
        this.gui = gui;
        spaceshipView = new PlayerView(2);
        enemyView = new EnemyView(2);
        coinView = new CoinView();
        bulletView = new BulletView();
    }

    @Override
    public void draw(GameModel model) {
        //for now we only have enemies to draw
        gui.clear();

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
