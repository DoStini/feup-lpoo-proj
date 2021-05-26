package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.model.util.objectpool.ObjectPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class BulletPoolControllerTest {

    BulletPoolController bulletPoolController;
    ObjectPool<Bullet> objectPool;
    GameController gameController;
    GameModel gameModel;

    @BeforeEach
    void setup() {
        gameModel = Mockito.mock(GameModel.class);
        gameController = Mockito.mock(GameController.class);
        objectPool = Mockito.mock(ObjectPool.class);
        bulletPoolController = new BulletPoolController(gameModel, objectPool);
        gameController.setBulletPoolController(bulletPoolController);
        bulletPoolController.setGameController(gameController);
    }

    @Test
    void poolHasObject() {
        Bullet bullet = Mockito.mock(Bullet.class);
        Mockito.when(objectPool.retrieve()).thenReturn(bullet);

        bulletPoolController.addBullet(5,5,"color", 5,0,3,null);

        Mockito.verify(gameModel, Mockito.times(1)).addBullet(bullet);
        Mockito.verify(bullet, Mockito.times(1)).init(5,5,"color", 5,0, 3);

        bulletPoolController.restoreBullet(bullet);
        Assertions.assertFalse(bullet.isActive());
        Mockito.verify(objectPool, Mockito.times(1)).restore(bullet);
    }

    @Test
    void poolHasNoObject() {
        Bullet bullet = Mockito.mock(Bullet.class);
        Mockito.when(objectPool.retrieve()).thenReturn(null);

        bulletPoolController.addBullet(5,5,"", 5,0,3, null);

        Mockito.verify(bullet, Mockito.times(0))
                .init(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(gameModel, Mockito.times(1))
                .addBullet(Mockito.any());
        Mockito.verify(gameModel, Mockito.times(1))
                .addCollider(Mockito.any());
        Mockito.verify(gameController, Mockito.times(1))
                .addToCollisionMap(Mockito.any(), Mockito.any());
        Mockito.verify(gameController, Mockito.times(1))
                .addToControllerMap(Mockito.any(), Mockito.any());

        bulletPoolController.restoreBullet(bullet);
        Assertions.assertFalse(bullet.isActive());
        Mockito.verify(objectPool, Mockito.times(1)).restore(bullet);
    }

    @Test

    void removeBullets() {
        List<Bullet> list = new ArrayList<>();
        list.add(new Bullet(new Position(0,0), "", 0,0, 3));
        list.add(new Bullet(new Position(0,0), "", 0,0, 3));
        list.add(new Bullet(new Position(0,0), "", 0,0, 3));
        Mockito.when(gameModel.getBulletList()).thenReturn(list);

        Assertions.assertTrue(list.get(0).isActive());
        list.get(2).activate();
        list.get(1).activate();
        list.get(0).deactivate();

        bulletPoolController.restoreBullet(list.get(0));
        bulletPoolController.removeInactiveBullets();
        Assertions.assertEquals(2, list.size());
    }
}
