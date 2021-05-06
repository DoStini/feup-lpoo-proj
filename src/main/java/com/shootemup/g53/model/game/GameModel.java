package com.shootemup.g53.model.game;

import com.shootemup.g53.controller.spaceship.SpaceshipController;
import com.shootemup.g53.model.Model;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.objectpool.ObjectPool;

import java.util.List;

public class GameModel extends Model {
    private int width;
    private int height;
    private boolean isGameFinished = false;
    private Spaceship player;
    private List<Spaceship> enemySpaceships;
    private List<Bullet> bullets;

    public GameModel(int width, int height, List<Spaceship> enemySpaceships) {
        this.width = width;
        this.height = height;
        this.enemySpaceships = enemySpaceships;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        isGameFinished = gameFinished;
    }

    public Spaceship getPlayer() {
        return player;
    }

    public void setPlayer(Spaceship player) {
        this.player = player;
    }

    public List<Spaceship> getEnemySpaceships() {
        return enemySpaceships;
    }

    public void setEnemySpaceships(List<Spaceship> enemySpaceships) {
        this.enemySpaceships = enemySpaceships;
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
    }
}
