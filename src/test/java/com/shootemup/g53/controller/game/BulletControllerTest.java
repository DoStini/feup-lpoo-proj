package com.shootemup.g53.controller.game;

import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.objectpool.ObjectPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class BulletControllerTest {

    BulletController bulletController;
    ObjectPool<Bullet> objectPool;
    GameModel gameModel;

    @BeforeEach
    void setup() {
        gameModel = Mockito.mock(GameModel.class);
        objectPool = Mockito.mock(ObjectPool.class);

        bulletController = new BulletController(gameModel, objectPool);
    }

    @Test
    void poolHasObject() {
        Bullet bullet = Mockito.mock(Bullet.class);
        Mockito.when(objectPool.retrieve()).thenReturn(bullet);

        bulletController.addBullet(5,5,"color", 5);

        Mockito.verify(gameModel, Mockito.times(1)).addBullet(bullet);
        Mockito.verify(bullet, Mockito.times(1)).init(5,5,"color", 5);

        bulletController.removeBullet(bullet);
        Mockito.verify(gameModel, Mockito.times(1)).removeBullet(bullet);
        Mockito.verify(objectPool, Mockito.times(1)).restore(bullet);
    }

    @Test
    void poolHasNoObject() {
        Bullet bullet = Mockito.mock(Bullet.class);
        Mockito.when(objectPool.retrieve()).thenReturn(null);

        bulletController.addBullet(5,5,"", 5);

        Mockito.verify(bullet, Mockito.times(0))
                .init(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyString(), Mockito.anyInt());
        Mockito.verify(gameModel, Mockito.times(1))
                .addBullet(Mockito.any());

        bulletController.removeBullet(bullet);
        Mockito.verify(gameModel, Mockito.times(1)).removeBullet(bullet);
        Mockito.verify(objectPool, Mockito.times(1)).restore(bullet);
    }
}