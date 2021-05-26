package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.game.GameController;

import com.shootemup.g53.controller.infobar.InfoBarController;

import com.shootemup.g53.controller.gamebuilder.GameBuilder;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.infobar.InfoBarModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.GameViewer;
import com.shootemup.g53.view.infobar.InfoBarViewer;

public class PlayState extends State<GameModel> {
    private final GameModel gameModel;
    private GameController gameController;
    private GameBuilder gameBuilder;
    private Viewer<GameModel> gameViewer;
    private Gui gui;
    private InfoBarController infoBarController;
    private InfoBarViewer infoBarViewer;
    int seconds = 0;

    long frame = 0;

    public PlayState(Game game, GameModel gameModel, Gui gui) {
        this.game = game;
        this.gameModel = gameModel;
        this.gameController = new GameController(this.gameModel);
        this.gameBuilder = new GameBuilder(gui, this.gameController, 10);
        this.gameViewer = new GameViewer(gui);
        this.gui = gui;
        this.infoBarViewer = new InfoBarViewer(this.gui, 10);
        this.infoBarController = new InfoBarController(gameController, new InfoBarModel(getStateModel().getPlayer().getHealth(),1,seconds,getStateModel().getPlayer().getHealth()));
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

                        Thread.sleep(50);
                        frame++;
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
                gameBuilder.handle(frame);
                gameController.handleKeyPress(gui);
                gameController.handle(frame);
                infoBarController.handle(seconds);

                if(gameController.isGameFinished()){
                    game.changeState(new GameOverState(this.game,this.gui));
                    return;
                }else if(gameController.isPaused()){
                    gameController.unpause();
                    game.changeState(new PauseState(game,gui,this));
                    return;
                }
                infoBarViewer.draw(infoBarController.getInfoBarModel());
                gameViewer.draw(gameModel);
            }

        }
        catch (InterruptedException  e) {
            e.printStackTrace();
        }
    }

    public InfoBarViewer getInfoBarViewer() {
        return infoBarViewer;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setGameBuilder(GameBuilder gameBuilder) {
        this.gameBuilder = gameBuilder;
    }
}
