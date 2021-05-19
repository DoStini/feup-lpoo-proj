package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
            movingBulletStrategy.fire(spaceship, spaceship.getPosition(), bulletPoolController, "#ff0000");
        }
        assertEquals(movingBulletStrategy.getFireRate(),fireRate);
        assertEquals(movingBulletStrategy.getFrame(),fireRate + 1);
        assertEquals(movingBulletStrategy.getLastFire(),11);
        Mockito.verify(bulletPoolController,Mockito.times(1)).addBullet(spaceship.getPosition().getX(),
                spaceship.getPosition().getY(),"#ff0000", 3,speed,movementStrategy);
    }
}
