package com.shootemup.g53.controller.firing;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.element.Bullet;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;

public interface FiringStrategy {
    void fire(Spaceship spaceship, BulletPoolController bulletPoolController);
}
