package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.firing.FiringStrategy;
import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.controller.input.Action;
import com.shootemup.g53.model.collider.ColliderCategory;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.ColorOperation;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class NormalState extends PlayerState{
    private FiringStrategy firingStrategy;
    private PlayerController playerController;

    public NormalState(PlayerController playerController) {
        this.firingStrategy = playerController.getFiringStrategy();
        this.playerController = playerController;
        this.playerController.setColor(playerController.getNormalPlayerColor());
    }

    public void fire(Gui gui, BulletPoolController bulletPoolController, long frame) {
        Player player = playerController.getPlayer();
        if (gui.isActionActive(Action.SPACE)) {
            firingStrategy.fire(player, player.getPosition().getUp(player.getHeight()), bulletPoolController,
                    ColorOperation.invertColor(player.getColor()),
                    ColliderCategory.PLAYER_BULLET, frame);
        }
    }

    @Override
    public void handleSpaceship(Spaceship spaceship) {
        this.playerController.getPlayer().setHealth(this.playerController.getPlayer().getHealth()-5);
        this.playerController.getLifeController().setLife(playerController.getPlayer().getHealth());
        this.playerController.getLifeController().notifyObservers();
        this.playerController.changeState(new InvulnerableState(playerController, 80));
    }

    @Override
    public void handle(long frame) {
        Position newPosition = playerController.move();
        playerController.setPosition(newPosition);
        fire(playerController.getGui(), playerController.getBulletPoolController(), frame);
        playerController.usePowerups();

    }
}
