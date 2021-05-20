package com.shootemup.g53.controller.gameBuilder;

import com.shootemup.g53.controller.element.CoinController;
import com.shootemup.g53.controller.element.SpaceshipController;
import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.firing.MovingBulletStrategy;
import com.shootemup.g53.controller.firing.SpreadBulletUpStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Direction;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

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

    public GameController buildGame(int numOfEnemies, int numOfCoins, int width, int height, Gui gui){
        GameModel gameModel = new GameModel(width,height);
        GameController gameController = new GameController(gameModel);
        BulletPoolController bulletPoolController = gameController.getBulletPoolController();
        //generate some enemies first
        List<Spaceship> enemiesList = new ArrayList<>();
        List<Coin> coinList = new ArrayList<>();

        Spaceship player = new Spaceship(new Position(20, 35), 3,3, "#aae243", 2);
        //create a playerController ?
        //PlayerController playerController = new PlayerController(player,gui, bulletPoolController, new MovingBulletStrategy(new MoveUpwardsMovement(), 2, 5));
        PlayerController playerController = new PlayerController(player,gui, bulletPoolController, new SpreadBulletUpStrategy(2,5));
        gameController.addToControllerMap(player,playerController);

        gameModel.setPlayer(player);

        for(int i = 0; i < numOfEnemies; i++){
            int randomX = 10 + rand.nextInt(width - 15);
            int randomY = 10 + rand.nextInt(height - 15);

            List<MovementStrategy> controllers = new ArrayList<MovementStrategy>();
            controllers.add(new CircularMovement(5, 0, 30));
            controllers.add( new DiagonalBounceMovement(3, 3, Direction.DOWN_LEFT, new Position(randomX,randomY)));
            controllers.add(new FallDownMovement());
            controllers.add(new ChangingMovement(20,controllers));

            List<FiringStrategy> firingStrategies = Arrays.asList(new MovingBulletStrategy(new FallDownMovement(), 2, 10));

            MovementStrategy selectedMovementStrategy = controllers.get(0);
            FiringStrategy selectedFiringStrategy = firingStrategies.get(rand.nextInt(firingStrategies.size()));

            Spaceship s = new Spaceship(new Position(randomX, randomY), 3, 3, "#1212ee", 1);
            gameController.addToControllerMap(s,new SpaceshipController(s,selectedFiringStrategy,selectedMovementStrategy,bulletPoolController ));
            enemiesList.add(s);
        }

        gameModel.setEnemySpaceships(enemiesList);

        for(int i = 0; i < numOfCoins; i++){
            int randomX = rand.nextInt(width - 10) + 5;
            int randomY = rand.nextInt(height - 10) + 5;
            Coin coin = new Coin( new Position(randomX,randomY), 2);
            gameController.addToControllerMap(coin, new CoinController(coin,new FallDownMovement()));
            coinList.add(coin);
        }
        gameModel.setCoins(coinList);
        gameModel.setBulletList(new ArrayList<>());
        return gameController;
    }
}
