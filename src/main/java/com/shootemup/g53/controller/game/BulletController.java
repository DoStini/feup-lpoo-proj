package com.shootemup.g53.controller.game;

import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.ObjectPool;

public class BulletController {
    private ObjectPool<Bullet> bulletPool;

    private GameModel gameModel;

    public BulletController(GameModel gameModel, ObjectPool<Bullet> objectPool) {
        this.gameModel = gameModel;
        bulletPool = objectPool;
    }

    public BulletController(GameModel gameModel, int cacheSize) {
        this(gameModel, new ObjectPool<>(cacheSize, new Bullet(new Position(0,0), "", 0)));
    }

    public void addBullet(int x, int y, String color, int size) {
        Bullet bullet = bulletPool.retrieve();
        if (bullet == null) {
            bullet = new Bullet(new Position(x, y), color, size);
        }
        else {
            bullet.init(x, y, color, size);
        }

        gameModel.addBullet(bullet);
    }

    public void removeBullet(Bullet bullet) {
        gameModel.removeBullet(bullet);
        bulletPool.restore(bullet);
    }

}
