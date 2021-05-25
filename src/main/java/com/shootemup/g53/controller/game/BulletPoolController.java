package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.element.BulletController;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.ObjectPool;

public class BulletPoolController {
    private ObjectPool<Bullet> bulletPool;

    private GameModel gameModel;
    private GameController gameController;

    public BulletPoolController(GameModel gameModel, ObjectPool<Bullet> objectPool) {
        this.gameModel = gameModel;
        bulletPool = objectPool;

    }

    public BulletPoolController(GameModel gameModel, int cacheSize) {
        this(gameModel, new ObjectPool<>(cacheSize, new Bullet(new Position(0,0), "", 0,0, 0)));
    }

    public void addBullet(int x, int y, String color, int size, int speed, int damage, MovementStrategy movementStrategy, ColliderCategory category) {
        Bullet bullet = setupBullet(x, y, color, size, speed, damage, movementStrategy);
        BodyCollider bulletCollider = new LineCompositeFactory().createFromVerticalLine(bullet, new Position(0,0), size);
        bulletCollider.setCategory(category);
        bulletCollider.setCategoryMask((short) (ColliderCategory.PLAYER.getBits() | ColliderCategory.ENEMY.getBits() | ColliderCategory.SHIELD.getBits()));
        gameModel.addBullet(bullet);
        gameModel.addCollider(bulletCollider);
    }

    public void addBullet(int x, int y, String color, int size,int speed, int damage, MovementStrategy movementStrategy) {
        addBullet(x,y,color,size,speed, damage, movementStrategy,ColliderCategory.ENEMY_BULLET);
    }

    Bullet setupBullet(int x, int y, String color, int size,int speed, int damage, MovementStrategy movementStrategy) {
        Bullet bullet = bulletPool.retrieve();
        if (bullet == null) {
            bullet = new Bullet(new Position(x, y), color, speed, size, damage);

        }
        else {
            bullet.init(x, y, color, size,speed);

        }
        BulletController bulletController = new BulletController(bullet, movementStrategy);

        gameController.addToControllerMap(bullet, bulletController);
        gameController.addToCollisionMap(bullet, bulletController);
        return bullet;
    }

    public void restoreBullet(Bullet bullet) {
        bulletPool.restore(bullet);
    }

    public void removeInactiveBullets() {
        gameModel.getBulletList().removeIf(b -> !b.isActive());
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
