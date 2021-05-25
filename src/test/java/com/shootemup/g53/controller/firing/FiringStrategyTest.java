package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiringStrategyTest {
    private Spaceship spaceship;
    private BulletPoolController bulletPoolController;
    private Position position;
    private MovementStrategy movementStrategy;
    private int speed = 5;
    private int fireRate = 10;

    @BeforeEach
    void setup() {
        spaceship = Mockito.mock(Spaceship.class);
        position = Mockito.mock(Position.class);

        bulletPoolController = Mockito.mock(BulletPoolController.class);
        Mockito.when(spaceship.getPosition()).thenReturn(position);
        Mockito.when(spaceship.getSpeed()).thenReturn(speed);
        Mockito.when(spaceship.getHealth()).thenReturn(3);

        movementStrategy = Mockito.mock(FallDownMovement.class);
        Mockito.when(movementStrategy.move(position,speed)).thenReturn(position);

        Mockito.when(position.getX()).thenReturn(5);
        Mockito.when(position.getY()).thenReturn(5);

    }

    @Test
    void StraightBulletTest(){
        MovingBulletStrategy movingBulletStrategy = new MovingBulletStrategy(movementStrategy,speed,fireRate);
        for(int i = 0; i < fireRate + 1; i++){
            movingBulletStrategy.fire(spaceship, spaceship.getPosition(), bulletPoolController, "#ff0000", ColliderCategory.ENEMY_BULLET);
        }

        assertEquals(movingBulletStrategy.getFireRate(),fireRate);
        assertEquals(movingBulletStrategy.getFrame(),fireRate + 1);
        assertEquals(movingBulletStrategy.getLastFire(),11);
        Mockito.verify(bulletPoolController,Mockito.times(1)).addBullet(spaceship.getPosition().getX(),
                spaceship.getPosition().getY(),"#ff0000", 3,speed,movementStrategy, ColliderCategory.ENEMY_BULLET);
    }

    @Test
    void multipleBulletTest() {
        MultipleMovingBulletStrategy bulletStrategy = new MultipleMovingBulletStrategy(movementStrategy, 4, 2, speed, fireRate);

        for(int i = 0; i < fireRate + 1; i++){
            bulletStrategy.fire(spaceship, spaceship.getPosition(), bulletPoolController, "#ff0000", ColliderCategory.GENERAL);
        }

        assertEquals(bulletStrategy.getFireRate(),fireRate);
        assertEquals(bulletStrategy.getFrame(),fireRate + 1);
        assertEquals(bulletStrategy.getLastFire(),11);
        Mockito.verify(bulletPoolController,Mockito.times(4)).addBullet(Mockito.anyInt(),
                Mockito.anyInt(),Mockito.anyString(), Mockito.anyInt(),Mockito.anyInt(), Mockito.any(), Mockito.any());

        bulletStrategy = new MultipleMovingBulletStrategy(movementStrategy, 5, 2, speed, fireRate);

        for(int i = 0; i < fireRate + 1; i++){
            bulletStrategy.fire(spaceship, spaceship.getPosition(), bulletPoolController, "#ff0000", ColliderCategory.GENERAL);
        }

        assertEquals(bulletStrategy.getFireRate(),fireRate);
        assertEquals(bulletStrategy.getFrame(),fireRate + 1);
        assertEquals(bulletStrategy.getLastFire(),11);
        Mockito.verify(bulletPoolController,Mockito.times(9)).addBullet(Mockito.anyInt(),
                Mockito.anyInt(),Mockito.anyString(), Mockito.anyInt(),Mockito.anyInt(), Mockito.any(), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(1)).addBullet(spaceship.getPosition().getX(),
                spaceship.getPosition().getY(),"#ff0000", 3,speed, movementStrategy,  ColliderCategory.GENERAL);
        Mockito.verify(bulletPoolController,Mockito.times(1)).addBullet(spaceship.getPosition().getX()-1,
                spaceship.getPosition().getY(),"#ff0000", 3,speed, movementStrategy,  ColliderCategory.GENERAL);
        Mockito.verify(bulletPoolController,Mockito.times(1)).addBullet(spaceship.getPosition().getX()+1,
                spaceship.getPosition().getY(),"#ff0000", 3,speed, movementStrategy,  ColliderCategory.GENERAL);
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(spaceship.getPosition().getX()+3,
                spaceship.getPosition().getY()+2*2,"#ff0000", 3,speed, movementStrategy,  ColliderCategory.GENERAL);
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(spaceship.getPosition().getX()-3,
                spaceship.getPosition().getY()+2*2,"#ff0000", 3,speed, movementStrategy,  ColliderCategory.GENERAL);
        Mockito.verify(bulletPoolController,Mockito.times(1)).addBullet(spaceship.getPosition().getX()-6,
                spaceship.getPosition().getY()+2*4,"#ff0000", 3,speed, movementStrategy,  ColliderCategory.GENERAL);
        Mockito.verify(bulletPoolController,Mockito.times(1)).addBullet(spaceship.getPosition().getX()+6,
                spaceship.getPosition().getY()+2*4,"#ff0000", 3,speed, movementStrategy,  ColliderCategory.GENERAL);
    }

    @Test
    void spreadDownTest() {
        SpreadBulletDownStrategy bulletStrategy = new SpreadBulletDownStrategy(speed, fireRate);

        for(int i = 0; i < fireRate + 1; i++){
            bulletStrategy.fire(spaceship, spaceship.getPosition(), bulletPoolController, "#ff0000", ColliderCategory.GENERAL);
        }

        assertEquals(bulletStrategy.getFireRate(),fireRate);
        assertEquals(bulletStrategy.getFrame(),fireRate + 1);
        assertEquals(bulletStrategy.getLastFire(),11);
        Mockito.verify(bulletPoolController,Mockito.times(3)).addBullet(Mockito.anyInt(),
                Mockito.anyInt(),Mockito.anyString(), Mockito.anyInt(),Mockito.anyInt(), Mockito.any(), Mockito.any());

        bulletStrategy = new SpreadBulletDownStrategy(speed, fireRate);

        for(int i = 0; i < fireRate + 1; i++){
            bulletStrategy.fire(spaceship, spaceship.getPosition(), bulletPoolController, "#ff0000", ColliderCategory.GENERAL);
        }

        int x = spaceship.getPosition().getX();
        int y = spaceship.getPosition().getY();

        assertEquals(bulletStrategy.getFireRate(),fireRate);
        assertEquals(bulletStrategy.getFrame(),fireRate + 1);
        assertEquals(bulletStrategy.getLastFire(),11);
        Mockito.verify(bulletPoolController,Mockito.times(6)).addBullet(Mockito.anyInt(),
                Mockito.anyInt(),Mockito.anyString(), Mockito.anyInt(),Mockito.anyInt(), Mockito.any(), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed), Mockito.any(FallDownMovement.class), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x-3),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed/2), Mockito.any(DiagonalDownLeftMovement.class), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x+3),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed/2), Mockito.any(DiagonalDownRightMovement.class), Mockito.any());
    }

    @Test
    void spreadUpTest() {
        SpreadBulletUpStrategy bulletStrategy = new SpreadBulletUpStrategy(speed, fireRate);

        for(int i = 0; i < fireRate + 1; i++){
            bulletStrategy.fire(spaceship, spaceship.getPosition(), bulletPoolController, "#ff0000", ColliderCategory.GENERAL);
        }

        assertEquals(bulletStrategy.getFireRate(),fireRate);
        assertEquals(bulletStrategy.getFrame(),fireRate + 1);
        assertEquals(bulletStrategy.getLastFire(),11);
        Mockito.verify(bulletPoolController,Mockito.times(3)).addBullet(Mockito.anyInt(),
                Mockito.anyInt(),Mockito.anyString(), Mockito.anyInt(),Mockito.anyInt(), Mockito.any(), Mockito.any());

        bulletStrategy = new SpreadBulletUpStrategy(speed, fireRate);

        for(int i = 0; i < fireRate + 1; i++){
            bulletStrategy.fire(spaceship, spaceship.getPosition(), bulletPoolController, "#ff0000", ColliderCategory.GENERAL);
        }

        int x = spaceship.getPosition().getX();
        int y = spaceship.getPosition().getY();

        assertEquals(bulletStrategy.getFireRate(),fireRate);
        assertEquals(bulletStrategy.getFrame(),fireRate + 1);
        assertEquals(bulletStrategy.getLastFire(),11);
        Mockito.verify(bulletPoolController,Mockito.times(6)).addBullet(Mockito.anyInt(),
                Mockito.anyInt(),Mockito.anyString(), Mockito.anyInt(),Mockito.anyInt(), Mockito.any(), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed), Mockito.any(MoveUpwardsMovement.class), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x-3),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed/2), Mockito.any(CompositeMovement.class), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x+3),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed/2), Mockito.any(CompositeMovement.class), Mockito.any());
    }
    @Test
    void spreadAllTest() {
        SpreadAllStrategy bulletStrategy = new SpreadAllStrategy(speed, fireRate);

        for(int i = 0; i < fireRate + 1; i++){
            bulletStrategy.fire(spaceship, spaceship.getPosition(), bulletPoolController, "#ff0000", ColliderCategory.GENERAL);
        }

        assertEquals(bulletStrategy.getFireRate(),fireRate);
        assertEquals(bulletStrategy.getFrame(),fireRate + 1);
        assertEquals(bulletStrategy.getLastFire(),11);
        Mockito.verify(bulletPoolController,Mockito.times(8)).addBullet(Mockito.anyInt(),
                Mockito.anyInt(),Mockito.anyString(), Mockito.anyInt(),Mockito.anyInt(), Mockito.any(), Mockito.any());

        bulletStrategy = new SpreadAllStrategy(speed, fireRate);

        for(int i = 0; i < fireRate + 1; i++){
            bulletStrategy.fire(spaceship, spaceship.getPosition(), bulletPoolController, "#ff0000", ColliderCategory.GENERAL);
        }

        int x = spaceship.getPosition().getX();
        int y = spaceship.getPosition().getY();

        assertEquals(bulletStrategy.getFireRate(),fireRate);
        assertEquals(bulletStrategy.getFrame(),fireRate + 1);
        assertEquals(bulletStrategy.getLastFire(),11);
        Mockito.verify(bulletPoolController,Mockito.times(16)).addBullet(Mockito.anyInt(),
                Mockito.anyInt(),Mockito.anyString(), Mockito.anyInt(),Mockito.anyInt(), Mockito.any(), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed), Mockito.any(MoveUpwardsMovement.class), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed), Mockito.any(FallDownMovement.class), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x+3),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed/2), Mockito.any(RightMovement.class), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x+3),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed/2), Mockito.any(DiagonalDownRightMovement.class), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x+3),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed/2), Mockito.any(CompositeMovement.class), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x-3),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed/2), Mockito.any(LeftMovement.class), Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x-3),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed/2), Mockito.any(DiagonalDownLeftMovement.class),Mockito.any());
        Mockito.verify(bulletPoolController,Mockito.times(2)).addBullet(Mockito.eq(x-3),
                Mockito.eq(y),Mockito.eq("#ff0000"), Mockito.eq(3), Mockito.eq(speed/2), Mockito.any(CompositeMovement.class), Mockito.any());

    }
}
