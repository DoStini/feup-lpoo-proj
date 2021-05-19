package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.game.GameController;

import com.shootemup.g53.controller.gamebuilder.GameBuilder;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.game.GameOverModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.GameViewer;

public class PlayState extends State<GameModel> {
    private GameController gameController;
    private GameModel gameModel;
    private GameBuilder gameBuilder;
    private Viewer<GameModel> gameViewer;
    private Gui gui;


    public PlayState(Game game, GameModel gameModel, Gui gui){
        this.game = game;
        this.gameModel = gameModel;
        this.gameController = new GameController(this.gameModel);
        this.gameBuilder = new GameBuilder(this.gameController, 10);
        this.gameViewer = new GameViewer(gui);
        this.gui = gui;
    }

    @Override
    public GameModel getStateModel() {
        return this.gameModel;
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
        try{
            long frame = 0;
            while(true){
                Thread.sleep(50);
                frame++;
                gameBuilder.handle(frame);
                gameController.handle(gui, frame);
                if(gameModel.isGameFinished()){
                    game.changeState(new GameOverState(this.game,new GameOverModel(),this.gui));
                    return;
                }

                gameViewer.draw(gameModel);
            }

        }
        catch (InterruptedException  e) {
            e.printStackTrace();
        }
    }
}
