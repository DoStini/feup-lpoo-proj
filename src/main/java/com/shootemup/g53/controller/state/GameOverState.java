package com.shootemup.g53.controller.state;


import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.game.GameOverController;
import com.shootemup.g53.model.game.GameOverModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.GameOverViewer;

public class GameOverState extends State<GameOverModel> {
    private GameOverController gameOverController;
    private GameOverModel gameOverModel;
    private Viewer<GameOverModel> gameOverViewer;
    private Gui gui;

    public GameOverState(Game game, GameOverModel gameModel, Gui gui){
        this.game = game;
        this.gameOverModel = gameModel;
        this.gameOverController = new GameOverController(this.gameOverModel);
        this.gameOverViewer = new GameOverViewer(gui);
        this.gui = gui;
    }

    @Override
    public GameOverModel getStateModel() {
        return null;
    }

    @Override
    public void exit() {

    }

    @Override
    public Viewer<GameOverModel> getStateView() {
        return null;
    }

    @Override
    public GenericController getStateController() {
        return null;
    }

    @Override
    public void run() {
        game.setToExit();
    }
}
