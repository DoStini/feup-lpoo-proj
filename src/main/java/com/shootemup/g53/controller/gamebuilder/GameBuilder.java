package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.firing.StraightBulletStrategy;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.element.AsteroidGenerator;
import com.shootemup.g53.controller.gamebuilder.element.CoinGenerator;
import com.shootemup.g53.controller.gamebuilder.element.ElementGenerator;
import com.shootemup.g53.controller.gamebuilder.element.SpaceshipGenerator;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Position;

import java.util.*;

public class GameBuilder {
    private final GameModel gameModel;
    private final GameController gameController;
    private long nextGeneration;
    private long baseSkip;
    private Random rand;
    private List<ElementGenerator> generators;

    public GameBuilder(GameController gameController, long baseSkip){
        this(new Random(), gameController, baseSkip);
    }

    public GameBuilder(Random rand, GameController gameController, long baseSkip) {
        this.rand = rand;
        this.gameController = gameController;
        this.gameModel = gameController.getGameModel();
        this.baseSkip = baseSkip;
        setupGame();
        setNextGeneration();
        setupGenerators();
    }

    private void setupGame() {
        Spaceship player = new Spaceship(new Position(20, 35), 3, "#aae243", 2, 15, null, new StraightBulletStrategy(new MoveUpwardsMovement(), 4));
        gameController.setPlayerController(new PlayerController(player));
        gameModel.setPlayer(player);
        gameModel.setCoins(new ArrayList<>());
        gameModel.setBulletList(new ArrayList<>());
        gameModel.setEnemySpaceships(new ArrayList<>());
        gameModel.setAsteroids(new ArrayList<>());
    }

    private void setupGenerators() {
        generators = Arrays.asList(
                new AsteroidGenerator(gameController, 0, gameModel.getWidth(), 1, 5, 10),
                new CoinGenerator(gameController, 0, gameModel.getWidth(), 1, 5, 4, -1, -1),
                new SpaceshipGenerator(gameController, 0, gameModel.getWidth(), 1, 5, 10, 10)
                );
    }

    private void setNextGeneration() {
        nextGeneration = nextGeneration + baseSkip + rand.nextInt((int) baseSkip);
    }

    public void handle(long time) {
        if (time < nextGeneration) return;

        setNextGeneration();

        generators.get(rand.nextInt(generators.size())).generateElement();

    }

   /* public GameModel buildGame(int numOfEnemies, int numOfCoins, int width, int height){
        GameModel gameModel = new GameModel(width,height);

        //generate some enemies first
        List<Spaceship> enemiesList = new ArrayList<>();
        List<Coin> coinList = new ArrayList<>();

        Spaceship player = new Spaceship(new Position(20, 35), 3, "#aae243", 2, null, new StraightBulletStrategy(new MoveUpwardsMovement(), 2, 8));
        gameModel.setPlayer(player);

        for(int i = 0; i < numOfEnemies; i++){
            int randomX = rand.nextInt(width - 10) + 5;
            int randomY = rand.nextInt(height - 10) + 5;

            List<MovementStrategy> controllers = new ArrayList<MovementStrategy>();
            controllers.add(new CircularMovement(5, 0, 30));
            controllers.add( new DiagonalBounceMovement(3, 3, Direction.DOWN_LEFT, new Position(randomX,randomY)));
            controllers.add(new FallDownMovement());
            controllers.add(new ChangingMovement(20,controllers));

            List<FiringStrategy> firingStrategies = Arrays.asList(new StraightBulletStrategy(new FallDownMovement(), 2, 10));

            MovementStrategy selectedMovementStrategy = controllers.get(rand.nextInt(controllers.size()));
            FiringStrategy selectedFiringStrategy = firingStrategies.get(rand.nextInt(firingStrategies.size()));
            Spaceship s = new Spaceship(new Position(randomX, randomY), 3, "#1212ee", 1,selectedMovementStrategy, selectedFiringStrategy);

            enemiesList.add(s);
        }

        gameModel.setEnemySpaceships(enemiesList);

        for(int i = 0; i < numOfCoins; i++){
            int randomX = rand.nextInt(width - 10) + 5;
            int randomY = rand.nextInt(height - 10) + 5;
            Coin coin = new Coin( new Position(randomX,randomY), 2, new FallDownMovement() );
            coinList.add(coin);
        }
        gameModel.setCoins(coinList);
        gameModel.setBulletList(new ArrayList<>());
        return gameModel;

    }*/
}
