package com.shootemup.g53.controller.state;


import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.command.ExitCommand;
import com.shootemup.g53.controller.command.MenuCommand;
import com.shootemup.g53.controller.command.ResumeCommand;
import com.shootemup.g53.controller.command.StartCommand;
import com.shootemup.g53.controller.game.GameOverController;
import com.shootemup.g53.controller.game.PauseStateController;
import com.shootemup.g53.controller.input.KeyPressObserver;
import com.shootemup.g53.model.game.GameOverModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.GameOverViewer;
import com.shootemup.g53.view.game.PauseViewer;

public class GameOverState extends State<GameOverModel> {
    private GameOverController gameOverController;
    private Viewer<GameOverModel> gameOverViewer;
    private Gui gui;
    private KeyPressObserver keyPressObserver = new KeyPressObserver();
    private int sleepFrame = 200;

    public GameOverState(Game game, Gui gui, int finalScore){
        this.game = game;
        this.gui = gui;
        setup(finalScore);
    }

    private void setup(int finalScore) {
        this.gameOverController = new GameOverController();
        this.gameOverViewer = new GameOverViewer(gui);
        this.gameOverController.getInputNotifier().addObserver(keyPressObserver);
        getStateModel().getOptions().get(0).setButtonCommand(new StartCommand(game));
        getStateModel().getOptions().get(1).setButtonCommand(new ExitCommand(game));
        gameOverController.setFinalScore(finalScore);
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
        return gameOverViewer;
    }

    @Override
    public GenericController getStateController() {
        return gameOverController;
    }

    @Override
    public void run() {
        gui.resetAllKeyPress();
        try {
            gameOverViewer.draw(getStateModel());
            while (true) {

                gameOverController.handleKeyPress(gui);
                if(keyPressObserver.getKeyPressed()){
                    if(gameOverController.isClosed()){
                        return;
                    }

                    gameOverViewer.draw(getStateModel());

                    Thread.sleep(sleepFrame);
                    keyPressObserver.resetKeyPress();
                }


            }
        }
        catch (InterruptedException  e) {
            e.printStackTrace();
        }
    }

    public void setSleepFrames(int sleepFrame) {
        this.sleepFrame = sleepFrame;
    }

    protected void setGameOverController(GameOverController gameOverController) {
        this.gameOverController = gameOverController;
    }

    protected void setGameOverViewer(Viewer<GameOverModel> gameOverViewer) {
        this.gameOverViewer = gameOverViewer;
    }

    protected void setKeyPressObserver(KeyPressObserver keyPressObserver) {
        this.keyPressObserver = keyPressObserver;
    }
}
