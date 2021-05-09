package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MoveUpwardsMovement;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Direction;
import com.shootemup.g53.model.util.Position;

public class StraightBulletController implements FiringController{
    private Direction direction;

    public StraightBulletController(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Bullet fire(Spaceship spaceship, int speed) {
        spaceship.increaseFrame();
        if (spaceship.getFrame() > spaceship.getLastFire() + spaceship.getFireRate()) {
            Bullet bullet = new Bullet(spaceship.getPosition(), "#ffffff",speed,2);

            if(direction == Direction.DOWN) bullet.setMovementController(new FallDownMovement());
            else bullet.setMovementController(new MoveUpwardsMovement());

            spaceship.setLastFire(spaceship.getFrame());
            return bullet;
        }
        return null;
    }
}
