package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.game.GameController;

import com.shootemup.g53.controller.gameBuilder.GameBuilder;
import com.shootemup.g53.controller.infobar.InfoBarController;
import com.shootemup.g53.controller.input.InputController;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.game.GameOverModel;
import com.shootemup.g53.model.infobar.InfoBarModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.GameViewer;
import com.shootemup.g53.view.infobar.InfoBarViewer;

import java.io.IOException;

public class PlayState extends State<GameModel> {
    private GameController gameController;
    private GameBuilder gameBuilder;
    private Viewer<GameModel> gameViewer;
    private Gui gui;
    private InfoBarController infoBarController;
    private InfoBarViewer infoBarViewer;
    int seconds = 0;

    public PlayState(Game game, Gui gui, GameBuilder gameBuilder){
        this.game = game;
        this.gameBuilder = gameBuilder;
        this.gameViewer = new GameViewer(gui);
        this.gui = gui;
        this.infoBarViewer = new InfoBarViewer(this.gui, 10);
        this.gameController = this.gameBuilder.buildGame(5,3,gui.getWidth() - 10,gui.getHeight(), gui);
        this.infoBarController = new InfoBarController(gameController, new InfoBarModel(3,1,seconds,6));

    }

    public InfoBarModel getInfoBarModel(){
        return infoBarController.getInfoBarModel();
    }

    @Override
    public GameModel getStateModel() {
        return gameController.getGameModel();
    }

    @Override
    public void exit() {
        gameController.finishGame();
    }

    @Override
    public Viewer<GameModel> getStateView() {
        return this.gameViewer;
    }

    @Override
    public GenericController getStateController() {
        return this.gameController;
    }

    @Override
    public void run(){
        Thread second_counter = new Thread(){
            @Override
            public void run(){
                try{
                    while(!gameController.isGameFinished()){
                        Thread.sleep(1000);
                        seconds++;
                    }

                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
        };
        second_counter.start();
        try{
            while(true){

                Thread.sleep(50);
                gameController.handleKeyPress(gui);
                gameController.handle();
                infoBarController.handle(seconds);
                infoBarViewer.draw(infoBarController.getInfoBarModel());
                gameViewer.draw(gameController.getGameModel());

                if(gameController.isGameFinished()){
                    game.changeState(new GameOverState(this.game,this.gui));
                    return;
                }else if(gameController.isPaused()){
                    gameController.unpause();
                    game.changeState(new PauseState(game,gui,this));
                    return;
                }
            }

        }
        catch (InterruptedException  e) {
            e.printStackTrace();
        }
    }

    public InfoBarViewer getInfoBarViewer() {
        return infoBarViewer;
    }
}
