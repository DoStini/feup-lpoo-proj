package com.shootemup.g53.controller.gamebuilder.element;

import com.shootemup.g53.controller.element.AsteroidController;
import com.shootemup.g53.controller.element.CoinController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.game.GameModel;

import java.util.Random;

public class AsteroidGenerator extends MovableElementGenerator {

    private final int maxRadius;

    public AsteroidGenerator(GameController gameController, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed, int maxRadius) {
        this(new Random(), gameController, xMinPos, xMaxPos, minSpeed, maxSpeed, maxRadius);
    }

    AsteroidGenerator(Random rand, GameController gameController, int xMinPos, int xMaxPos, int minSpeed, int maxSpeed,
                      int maxRadius) {
        super(rand, gameController, xMinPos, xMaxPos, minSpeed, maxSpeed);
        this.maxRadius = maxRadius;
    }

    protected void setRadius(Asteroid asteroid) {
        asteroid.setRadius(rand.nextInt(maxRadius-1)+1);
    }


    private void setMovement(Asteroid asteroid) {
        gameController.addToControllerMap(asteroid, new AsteroidController(asteroid, new FallDownMovement()));
    }

    @Override
    public void generateElement() {
        Asteroid asteroid = new Asteroid();
        setPosition(asteroid);
        setRadius(asteroid);
        setMovement(asteroid);
        gameModel.addAsteroid(asteroid);
    }
}
