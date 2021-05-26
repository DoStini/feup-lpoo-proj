package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.MovementStrategyFactory;

import java.util.Random;

public class EssenceGenerator extends MovableElementGenerator {


    public EssenceGenerator(GameController gameController, MovementStrategyFactory movementStrategyFactory, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed) {
        super(gameController, movementStrategyFactory, xMinPos, xMaxPos, minSpeed, maxSpeed);
    }

    EssenceGenerator(Random rand, GameController gameController, MovementStrategyFactory movementStrategyFactory, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed) {
        super(rand, gameController, movementStrategyFactory, xMinPos, xMaxPos, minSpeed, maxSpeed);
    }

    @Override
    public void generateElement() {

    }
}
