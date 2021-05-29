package com.shootemup.g53.controller.player;

import com.shootemup.g53.controller.game.BulletPoolController;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.ui.Gui;

abstract public class PlayerState {
    public abstract void fire(Gui gui, BulletPoolController bulletPoolController, long frame);
    public abstract void handleSpaceship(Spaceship spaceship);
    public abstract void handle(long frame);
}
