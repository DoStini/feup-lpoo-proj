package com.shootemup.g53.controller.state;


import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.command.ExitCommand;
import com.shootemup.g53.controller.command.StartCommand;
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

    public GameOverState(Game game, Gui gui){
        this.game = game;
        this.gameOverController = new GameOverController();
        this.gameOverViewer = new GameOverViewer(gui);
        this.gui = gui;
        getStateModel().getOptions().get(0).setButtonCommand(new StartCommand(game));
        getStateModel().getOptions().get(1).setButtonCommand(new ExitCommand(game));
    }

    @Override
    public GameOverModel getStateModel() {
        return gameOverController.getGameOverModel();
    }

    @Override
    public void exit() {
        gameOverController.close();
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
        try {
            while (true) {
                Thread.sleep(100);
                gameOverController.handleKeyPress(gui);
                gameOverViewer.draw(getStateModel());
                if(gameOverController.isClose()){
                    return;
                }

            }
        }
        catch (InterruptedException  e) {
            e.printStackTrace();
        }
    }
}
