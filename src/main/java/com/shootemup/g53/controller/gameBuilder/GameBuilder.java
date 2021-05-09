package com.shootemup.g53.controller.gameBuilder;

import com.shootemup.g53.controller.firing.FiringController;
import com.shootemup.g53.controller.firing.StraightBulletController;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.controller.spaceship.SpaceshipController;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Direction;
import com.shootemup.g53.model.util.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameBuilder {
    private Random rand;

    public GameBuilder(){
        rand = new Random();
    }

    public GameBuilder(Random rand) {
        this.rand = rand;
    }

    public GameModel buildGame(int numOfEnemies, int numOfCoins, int width, int height){
        GameModel gameModel = new GameModel(width,height);

        //generate some enemies first
        List<Spaceship> enemiesList = new ArrayList<>();
        List<Coin> coinList = new ArrayList<>();

        Spaceship player = new Spaceship(new Position(20, 35), 3, "#aae243", 2, 10);
        player.setFiringController(new StraightBulletController(Direction.UP));
        gameModel.setPlayer(player);

        for(int i = 0; i < numOfEnemies; i++){
            int randomX = rand.nextInt(width - 10) + 5;
            int randomY = rand.nextInt(height - 10) + 5;

            Spaceship s = new Spaceship(new Position(randomX, randomY), 3, "#1212ee", 1,30);
            List<MovementController> controllers = new ArrayList<MovementController>();
            controllers.add(new CircularMovement(5, 0, 30));
            controllers.add( new DiagonalBounceMovement(3, 3, Direction.DOWN_LEFT, s.getPosition()));
            controllers.add(new FallDownMovement());
            controllers.add(new ChangingMovement(20,controllers));

            List<FiringController> firingControllers = Arrays.asList(new StraightBulletController(Direction.DOWN));

            s.setMovementController(controllers.get(0));
            s.setFiringController(firingControllers.get(0));
            enemiesList.add(s);
        }

        gameModel.setEnemySpaceships(enemiesList);

        for(int i = 0; i < numOfCoins; i++){
            int randomX = rand.nextInt(width - 10) + 5;
            int randomY = rand.nextInt(height - 10) + 5;
            Coin coin = new Coin( new Position(randomX,randomY), 2 );
            coin.setMovementController(new FallDownMovement());
            coinList.add(coin);
        }
        gameModel.setCoins(coinList);
        gameModel.setBulletList(new ArrayList<>());
        return gameModel;

    }
}
