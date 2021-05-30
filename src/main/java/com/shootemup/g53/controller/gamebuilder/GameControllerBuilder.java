package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.element.BackgroundController;
import com.shootemup.g53.controller.firing.MovingBulletStrategy;
import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.movement.MoveUpwardsMovement;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.controller.player.PowerupController;
import com.shootemup.g53.model.element.Background;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.ui.Gui;

public class GameControllerBuilder {
    private final Gui gui;
    GameController gameController;

    public GameControllerBuilder(Gui gui, GameModel model) {
        this.gui = gui;
        gameController = new GameController(null);
        gameController.setGameModel(model);
    }

    protected void setupPlayer(Player player) {
        PowerupController powerupController = new PowerupController(gameController);

        PlayerController playerController = new PlayerController(player, gui, gameController.getBulletPoolController(),
                powerupController,
                new MovingBulletStrategy(new MoveUpwardsMovement(), 3, 5));
        gameController.addToControllerMap(player, playerController);
        gameController.addToCollisionMap(player, playerController);
    }

    protected void setupBackground(Background background) {
        gameController.setBackgroundController(new BackgroundController(gameController.getGameModel(), background, 30, 3.5));
    }
}
