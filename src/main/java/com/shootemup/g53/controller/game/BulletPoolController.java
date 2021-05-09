package com.shootemup.g53.controller.game;

import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.ObjectPool;

import java.util.List;

public class BulletPoolController {
    private ObjectPool<Bullet> bulletPool;

    private GameModel gameModel;

    public BulletPoolController(GameModel gameModel, ObjectPool<Bullet> objectPool) {
        this.gameModel = gameModel;
        bulletPool = objectPool;
    }

    public BulletPoolController(GameModel gameModel, int cacheSize) {
        this(gameModel, new ObjectPool<>(cacheSize, new Bullet(new Position(0,0), "", 0,0)));
    }

    public void addPlayerBullet(int x, int y, String color, int size) {
        Bullet bullet = setupBullet(x, y, color, size);
        gameModel.addBullet(bullet);
    }


    public void addEnemyBullet(int x, int y, String color, int size) {
        Bullet bullet = setupBullet(x, y, color, size);
        gameModel.addBullet(bullet);
    }

    Bullet setupBullet(int x, int y, String color, int size) {
        Bullet bullet = bulletPool.retrieve();
        if (bullet == null) {
            bullet = new Bullet(new Position(x, y), color, bullet.getSpeed(), size);
            System.out.println("Not found");
        }
        else {
            bullet.init(x, y, color, size);
            System.out.println("Found");
        }

        return bullet;
    }

    public void restoreBullet(Bullet bullet) {
        bulletPool.restore(bullet);
    }

    public void removeInactiveBullets() {
        gameModel.getBulletList().removeIf(b -> !b.isActive());

    }
}
