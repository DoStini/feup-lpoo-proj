package com.shootemup.g53.model.game;


import com.shootemup.g53.model.Model;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.*;

import java.util.List;

public class GameModel extends Model {
    private int width;
    private int height;
    private boolean isGameFinished = false;
    private Player player;
    private Background background;

    private List<Coin> coins;
    private List<Asteroid> asteroids;
    private List<Spaceship> enemySpaceships;
    private List<Bullet> bulletList;
    private List<Shield> shields;
    private List<Essence> essences;
    private List<BodyCollider> colliders;

    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void removeInactive() {
        colliders.removeIf(collider -> !collider.getElement().isActive());
        coins.removeIf(coin -> !coin.isActive());
        enemySpaceships.removeIf(enemy -> !enemy.isActive());
        asteroids.removeIf(asteroid -> !asteroid.isActive());
        shields.removeIf(shield -> !shield.isActive());
        essences.removeIf(essence -> !essence.isActive());
    }

    public void addCollider(BodyCollider collider) {
        colliders.add(collider);
    }

    public void removeCollider(Element element) {
        colliders.removeIf(collider -> collider.getElement() == element);
    }

    public List<Spaceship> getEnemySpaceships() {
        return enemySpaceships;
    }

    public void setEnemySpaceships(List<Spaceship> enemySpaceships) {
        this.enemySpaceships = enemySpaceships;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }

    public void addBullet(Bullet bullet){
        this.bulletList.add(bullet);
    }

    public void addShield(Shield shield) {
        this.shields.add(shield);
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public Background getBackground() {
        return background;
    }

    public void removeBullet(Bullet bullet){
        this.bulletList.remove(bullet);
    }

    public List<BodyCollider> getColliders() {
        return colliders;
    }

    public void setColliders(List<BodyCollider> colliders) {
        this.colliders = colliders;
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(List<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    public void addCoin(Coin coin) {
        coins.add(coin);
    }

    public void addEnemy(Spaceship spaceship) {
        enemySpaceships.add(spaceship);
    }

    public void addAsteroid(Asteroid asteroid) {
        asteroids.add(asteroid);
    }

    public List<Shield> getShieldList() {
        return shields;
    }

    public void setShields(List<Shield> shields) {
        this.shields = shields;
    }

    public List<Essence> getEssenceList() {
        return essences;
    }

    public void setEssences(List<Essence> essences) {
        this.essences = essences;
    }

    public void addEssence(Essence essence) {
        essences.add(essence);
    }
}
