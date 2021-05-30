package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;

public class InvulnerableState extends PlayerState{
    PlayerController playerController;
    private int cooldown;

    public InvulnerableState(PlayerController playerController, int cooldown) {
        this.playerController = playerController;
        this.cooldown = cooldown;
        playerController.setColor("#FFFFFF");
    }


    @Override
    public void fire(Gui gui, BulletPoolController bulletPoolController, long frame) {

    }

    @Override
    public void handleSpaceship(Spaceship spaceship) {

    }

    @Override
    public void handle(long frame) {
        Position newPosition = playerController.move();
        playerController.setPosition(newPosition);
        cooldown--;
        if(cooldown <= 0) playerController.changeState(new NormalState(playerController));
    }
}
