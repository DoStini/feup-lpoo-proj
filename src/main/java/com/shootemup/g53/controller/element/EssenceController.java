package com.shootemup.g53.controller.element;

import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.element.Essence;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.util.Position;

public class EssenceController extends MovableElementController implements ElementInterface, CollisionHandlerController {

    private Essence essence;

    public EssenceController(Essence essence, MovementStrategy movementStrategy) {
        super(essence, movementStrategy);
        this.essence = essence;
    }

    @Override
    public void handle(long frame) {
        Position newPosition = move();
        essence.setPosition(newPosition);
    }

    @Override
    public void handleCollision(BodyCollider thisCollider, BodyCollider otherCollider, CollisionHandlerController otherController) {
        otherController.handleEssence(essence);
    }

    @Override
    public void handlePlayer(Player player) {
        essence.deactivate();
    }
}
