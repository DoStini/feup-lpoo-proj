package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.MovementStrategyFactory;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;

import java.util.Random;

public abstract class ElementGenerator {

    private final int xMinPos;
    private final int xMaxPos;
    protected final Random rand;
    protected final GameModel gameModel;
    protected final GameController gameController;
    protected final MovementStrategyFactory movementStrategyFactory;
    
    ElementGenerator(GameController gameController, MovementStrategyFactory movementStrategyFactory, int xMinPos, int xMaxPos) {
        this(new Random(), gameController, movementStrategyFactory, xMinPos, xMaxPos);
    }

    ElementGenerator(Random rand, GameController gameController, MovementStrategyFactory movementStrategyFactory,
                     int xMinPos, int xMaxPos) {
        this.rand = rand;
        this.xMinPos = xMinPos;
        this.xMaxPos = xMaxPos;
        this.gameController = gameController;
        this.gameModel = gameController.getGameModel();
        this.movementStrategyFactory = movementStrategyFactory;
    }

    protected void setPosition(Element element) {
        element.setPosition(new Position(rand.nextInt(xMaxPos-xMinPos)+xMinPos, 0));
    }

    protected void setColor(Element element) {
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        element.setColor(String.format("#%02x%02x%02x", r, g, b));
    }

    public abstract void generateElement();
}
