package com.shootemup.g53.controller.gameBuilder;

import com.shootemup.g53.controller.element.BackgroundController;
import com.shootemup.g53.controller.element.CoinController;
import com.shootemup.g53.controller.element.SpaceshipController;
import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.firing.MovingBulletStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.movement.*;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.collider.BodyCollider;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.collider.LineCompositeFactory;
import com.shootemup.g53.model.element.Background;
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
        GameModel gameModel = new GameModel(width, height);
        GameController gameController = new GameController(gameModel);
        BulletPoolController bulletPoolController = gameController.getBulletPoolController();
        //generate some enemies first
        List<Spaceship> enemiesList = new ArrayList<>();
        List<Coin> coinList = new ArrayList<>();
        List<BodyCollider> colliders = new ArrayList<>();

        Spaceship player = new Spaceship(new Position(20, 35), 3, 3, "#aae243", 2);
        //create a playerController ?

        PlayerController playerController = new PlayerController(player, gui, bulletPoolController, new MovingBulletStrategy(new MoveUpwardsMovement(), 2, 5));
        gameController.addToControllerMap(player, playerController);
        gameController.addToCollisionMap(player, playerController);
        BodyCollider playerCollider = new LineCompositeFactory().createFromIsoscelesTriangle(player, new Position(0, 0), 3);
        playerCollider.setCategory(ColliderCategory.PLAYER);
        playerCollider.setCategoryMask((short) (ColliderCategory.ENEMY.getBits() | ColliderCategory.ENEMY_BULLET.getBits()
                | ColliderCategory.PICKUP.getBits()));

        colliders.add(playerCollider);
        gameModel.setPlayer(player);

        for (int i = 0; i < numOfEnemies; i++) {
            int randomX = 10 + rand.nextInt(width - 15);
            int randomY = 10 + rand.nextInt(height - 15);

            List<MovementStrategy> controllers = new ArrayList<MovementStrategy>();
            controllers.add(new CircularMovement(5, 0, 30));
            controllers.add(new DiagonalBounceMovement(3, 3, Direction.DOWN_LEFT, new Position(randomX, randomY)));
            controllers.add(new FallDownMovement());
            controllers.add(new ChangingMovement(20, controllers));

            List<FiringStrategy> firingStrategies = Arrays.asList(new MovingBulletStrategy(new FallDownMovement(), 2, 10));

            //MovementStrategy selectedMovementStrategy = controllers.get(0);
            MovementStrategy selectedMovementStrategy = new CompositeMovement();
            FiringStrategy selectedFiringStrategy = firingStrategies.get(rand.nextInt(firingStrategies.size()));
            //FiringStrategy selectedFiringStrategy = new SpreadAllStrategy(2, 10);

            Spaceship s = new Spaceship(new Position(randomX, randomY), 3, 3, "#1212ee", 1);
            SpaceshipController sc = new SpaceshipController(s, selectedFiringStrategy, selectedMovementStrategy, bulletPoolController);
            gameController.addToControllerMap(s, sc);
            gameController.addToCollisionMap(s, sc);
            BodyCollider enemyCollider = new LineCompositeFactory().createFromInvertedIsoscelesTriangle(s, new Position(0, 0), 3);
            enemyCollider.setCategory(ColliderCategory.ENEMY);
            enemyCollider.setCategoryMask((short) (ColliderCategory.PLAYER.getBits() | ColliderCategory.PLAYER_BULLET.getBits()));

            colliders.add(enemyCollider);
            enemiesList.add(s);
        }

        gameModel.setEnemySpaceships(enemiesList);

        for(int i = 0; i < numOfCoins; i++){
            int randomX = rand.nextInt(width - 10) + 5;
            int randomY = rand.nextInt(height - 10) + 5;

            Coin coin = new Coin( new Position(randomX,randomY), 2);
            CoinController cc = new CoinController(coin,new FallDownMovement());
            coin.setSpeed(0);
            gameController.addToControllerMap(coin, cc);
            gameController.addToCollisionMap(coin, cc);
            BodyCollider coinCollider = new LineCompositeFactory().createFromCircle(coin, new Position(0,0), coin.getRadius());
            coinCollider.setCategory(ColliderCategory.PICKUP);
            coinList.add(coin);
            colliders.add(coinCollider);
        }
        Background background = new Background(25, 30);

        gameController.setBackgroundController(new BackgroundController(gameModel, background, 30, 3));
        gameModel.setBackground(background);
        gameModel.setCoins(coinList);
        gameModel.setBulletList(new ArrayList<>());
        gameModel.setColliders(colliders);
        gameModel.setAsteroids(new ArrayList<>());

        return gameController;
    }
}
