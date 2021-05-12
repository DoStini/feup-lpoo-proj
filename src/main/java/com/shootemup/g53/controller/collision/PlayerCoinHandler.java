package com.shootemup.g53.controller.collision;

import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;

public class PlayerCoinHandler extends CollisionHandler<Spaceship>{
    public PlayerCoinHandler(Spaceship element, CollisionController controller) {
        super(element, controller);
    }

    @Override
    public void handleCollision(BodyCollider thisCollider, BodyCollider other) {

        if (other.getElement() instanceof Coin) {
            System.out.println("collide Coin yeah");
        } else if(other.getElement() instanceof Spaceship) {
            System.out.println("collide Spaceship yeah");
        }
    }
}
