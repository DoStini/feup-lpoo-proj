package com.shootemup.g53.controller.gameBuilder;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.firing.StraightBulletStrategy;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.model.element.Asteroid;
import com.shootemup.g53.model.element.Coin;
import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.util.Direction;
import com.shootemup.g53.model.util.Position;

import java.util.*;

public class GameBuilder {
    private final GameModel gameModel;
    private long nextGeneration;
    private long baseSkip;
    private Random rand;
    private List<ElementGenerator> generators;

    public GameBuilder(GameModel gameModel, long baseSkip){
        this(new Random(), gameModel, baseSkip);
    }

    public GameBuilder(Random rand, GameModel gameModel, long baseSkip) {
        this.rand = rand;
        this.gameModel = gameModel;
        this.baseSkip = baseSkip;
        setupGame();
        setNextGeneration();
        setupGenerators();
    }

    private void setupGame() {
        Spaceship player = new Spaceship(new Position(20, 35), 3, "#aae243", 2, 2, null, new StraightBulletStrategy(new MoveUpwardsMovement(), 4));
        gameModel.setPlayer(player);
        gameModel.setCoins(new ArrayList<>());
        gameModel.setBulletList(new ArrayList<>());
        gameModel.setEnemySpaceships(new ArrayList<>());
        gameModel.setAsteroids(new ArrayList<>());
    }

    private void setupGenerators() {
        generators = Arrays.asList(
                new AsteroidGenerator(0, gameModel.getWidth(), 1, 5, 10),
                new CoinGenerator(0, gameModel.getWidth(), 1, 5, 4, -1, -1),
                new SpaceshipGenerator(0, gameModel.getWidth(), 1, 5, 10, 10)
                );
    }

    private void setNextGeneration() {
        nextGeneration = nextGeneration + baseSkip + rand.nextInt((int) baseSkip);
    }

    public void handle(GameModel game, long time) {
        if (time < nextGeneration) return;

        setNextGeneration();

        Element element = generators.get(rand.nextInt(generators.size())).generateElement();

        if (element instanceof Spaceship) {
            gameModel.addEnemy(element);
        }
        else if (element instanceof Asteroid)
            gameModel.addAsteroid(element);
        else if (element instanceof Coin)
            gameModel.addCoin(element);
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
