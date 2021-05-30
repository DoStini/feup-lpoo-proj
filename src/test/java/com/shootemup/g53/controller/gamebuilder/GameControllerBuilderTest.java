package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.element.BackgroundController;
import com.shootemup.g53.controller.firing.MovingBulletStrategy;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.movement.MoveUpwardsMovement;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.Background;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.ui.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

public class GameControllerBuilderTest {
    private GameController gameController;
    private GameModel gameModel;
    private Gui gui;

    private GameControllerBuilder gameControllerBuilder;

    @BeforeEach
    void setup() {
        gameController = Mockito.mock(GameController.class);
        gui = Mockito.mock(Gui.class);
        gameModel = Mockito.mock(GameModel.class);

        gameControllerBuilder = new GameControllerBuilder(gui);

        gameControllerBuilder.setupModel(gameModel);
    }

    @Test
    void constructor() {
        Assertions.assertSame(gameModel, gameControllerBuilder.gameController.getGameModel());
    }

    @Test
    void setupPlayer() {
        Player player = Mockito.mock(Player.class);

        gameControllerBuilder.setupPlayer(player);

        Assertions.assertEquals(PlayerController.class, gameControllerBuilder.gameController.getElementController(player).getClass());
        Assertions.assertEquals(PlayerController.class, gameControllerBuilder.gameController.getCollisionHandler(player).getClass());

        PlayerController playerController = (PlayerController) gameControllerBuilder.gameController.getElementController(player);
        PlayerController playerControllerCollision = (PlayerController) gameControllerBuilder.gameController.getCollisionHandler(player);

        Assertions.assertSame(playerController, playerControllerCollision);

        Assertions.assertSame(gui, playerController.getGui());
        Assertions.assertSame(player, playerController.getPlayer());
        Assertions.assertSame(gameControllerBuilder.gameController.getBulletPoolController(), playerController.getBulletPoolController());
        Assertions.assertEquals(MovingBulletStrategy.class, playerController.getFiringStrategy().getClass());
        Assertions.assertSame(gameControllerBuilder.gameController, playerController.getPowerupController().getGameController());
    }

    @Test
    void setupController() {
        Background background = Mockito.mock(Background.class);
        gameControllerBuilder.setupBackground(background);
        gameController = gameControllerBuilder.getGameController();

        BackgroundController backgroundController = gameController.getBackgroundController();

        Assertions.assertSame(background ,backgroundController.getBackground());
        Assertions.assertSame(gameModel, backgroundController.getModel());
        Assertions.assertEquals(30, backgroundController.getMaxDistance());
        Assertions.assertEquals(3.5, backgroundController.getMaxSpeed());
    }
}
