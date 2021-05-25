package com.shootemup.g53.view.game;

import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.element.*;
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
    private BackgroundView backgroundView;

    public GameViewer(Gui gui) {
        this.gui = gui;
        spaceshipView = new PlayerView(2);
        enemyView = new EnemyView(2);
        coinView = new CoinView();
        bulletView = new BulletView();
        starView = new StarView(0.2);
        backgroundView = new BackgroundView(starView);
    }

    @Override
    public void draw(GameModel model) {
        //for now we only have enemies to draw
        gui.clear();
        if(model.getBackground()!= null) backgroundView.draw(gui, model.getBackground());

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
