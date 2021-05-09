package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.ObjectPool;

public class BulletPoolController {
    private ObjectPool<Bullet> bulletPool;

    private GameModel gameModel;

    public BulletPoolController(GameModel gameModel, ObjectPool<Bullet> objectPool) {
        this.gameModel = gameModel;
        bulletPool = objectPool;
    }

    public BulletPoolController(GameModel gameModel, int cacheSize) {
        this(gameModel, new ObjectPool<>(cacheSize, new Bullet(new Position(0,0), "", 0,0,null)));
    }

    public void addBullet(int x, int y, String color, int size,int speed, MovementStrategy movementStrategy) {
        Bullet bullet = setupBullet(x, y, color, size, speed, movementStrategy);
        gameModel.addBullet(bullet);
    }

    Bullet setupBullet(int x, int y, String color, int size,int speed, MovementStrategy movementStrategy) {
        Bullet bullet = bulletPool.retrieve();
        if (bullet == null) {
            bullet = new Bullet(new Position(x, y), color, speed, size, movementStrategy);


        }
        else {
            bullet.init(x, y, color, size,speed, movementStrategy);

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
