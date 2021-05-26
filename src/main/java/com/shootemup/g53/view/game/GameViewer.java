package com.shootemup.g53.view.game;

import com.shootemup.g53.model.element.*;
import com.shootemup.g53.model.game.GameModel;

import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.element.AsteroidView;
import com.shootemup.g53.view.element.BulletView;
import com.shootemup.g53.view.element.CoinView;
import com.shootemup.g53.view.element.*;
import com.shootemup.g53.view.element.spaceship.EnemyView;
import com.shootemup.g53.view.element.spaceship.PlayerView;
import com.shootemup.g53.view.element.spaceship.SpaceshipView;
import com.shootemup.g53.view.infobar.InfoBarViewer;

public class GameViewer extends Viewer<GameModel> {
    private SpaceshipView spaceshipView;
    private SpaceshipView enemyView;
    private CoinView coinView;
    private BulletView bulletView;

    private InfoBarViewer infoBarViewer;

    private AsteroidView asteroidView;
    private ShieldView shieldView;
    private StarView starView;
    private EssenceView essenceView;
    private BackgroundView backgroundView;

    public GameViewer(Gui gui) {
        this.gui = gui;
        spaceshipView = new PlayerView(2);
        enemyView = new EnemyView(2);
        coinView = new CoinView();
        bulletView = new BulletView();
        asteroidView = new AsteroidView();
        shieldView = new ShieldView();
        essenceView = new EssenceView();
        starView = new StarView(0.2);
        backgroundView = new BackgroundView(starView);
    }

    @Override
    public void draw(GameModel model) {

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
        for(Asteroid asteroid: model.getAsteroids()){
            asteroidView.draw(gui,asteroid);
        }
        for(Shield shield: model.getShieldList()){
            shieldView.draw(gui, shield);
        }
        for(Essence essence: model.getEssenceList()){
            essenceView.draw(gui, essence);
        }
        gui.refresh();
    }
}
