package com.shootemup.g53.controller.gamebuilder;

public class GameDirector {
    protected final GameModelBuilder modelBuilder;
    protected final GameControllerBuilder controllerBuilder;

    public GameDirector(GameModelBuilder modelBuilder, GameControllerBuilder controllerBuilder) {
        this.modelBuilder = modelBuilder;
        this.controllerBuilder = controllerBuilder;
    }

    public void make(int width, int height) {
        modelBuilder.setHeight(height);
        modelBuilder.setWidth(width);
        modelBuilder.setupPlayer();
        modelBuilder.setupBackground();
        modelBuilder.setupElements();

        controllerBuilder.setupModel(modelBuilder.getGameModel());
        controllerBuilder.setupPlayer(modelBuilder.getGameModel().getPlayer());
        controllerBuilder.setupBackground(modelBuilder.getGameModel().getBackground());
    }
}
