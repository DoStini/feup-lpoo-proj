package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.element.EssenceController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.MovementStrategyFactory;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Essence;
import com.shootemup.g53.model.util.Position;

import java.util.Random;

public class EssenceGenerator extends MovableElementGenerator {

    private int minVal = 1;
    private int maxVal = 5;

    public EssenceGenerator(GameController gameController, MovementStrategyFactory movementStrategyFactory, int xMinPos, int xMaxPos, double minSpeed, double maxSpeed) {
        super(gameController, movementStrategyFactory, xMinPos, xMaxPos, minSpeed, maxSpeed);
    }

    EssenceGenerator(Random rand, GameController gameController, MovementStrategyFactory movementStrategyFactory, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed) {
        super(rand, gameController, movementStrategyFactory, xMinPos, xMaxPos, minSpeed, maxSpeed);
    }

    protected void setValue(Essence essence) {
        essence.setValue(rand.nextInt(maxVal-minVal)+minVal);
    }

    protected void setCollider(Essence essence) {
        BodyCollider collider = new LineCompositeFactory()
                        .createFromCircle(essence, new Position(0,0), 1);
        collider.setCategory(ColliderCategory.PICKUP);
        gameModel.addCollider(collider);
    }

    protected void setMovement(Essence essence) {
        EssenceController essenceController = new EssenceController(essence, generateMovementStrategy(essence));
        gameController.addToControllerMap(essence, essenceController);
        gameController.addToCollisionMap(essence, essenceController);
    }

    @Override
    public void generateElement() {
        Essence essence = new Essence();
        setPosition(essence);
        setValue(essence);
        setMovement(essence);
        setCollider(essence);
        gameModel.addEssence(essence);
    }

    public void setMinVal(int minVal) {
        this.minVal = minVal;
    }

    public void setMaxVal(int maxVal) {
        this.maxVal = maxVal;
    }
}
