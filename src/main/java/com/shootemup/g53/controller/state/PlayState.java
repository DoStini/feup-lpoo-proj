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
    private final GameModel gameModel;
    private GameController gameController;
    private GameBuilder gameBuilder;
    private Viewer<GameModel> gameViewer;
    private Gui gui;
    long frame = 0;

    public PlayState(Game game, GameModel gameModel, Gui gui){
        this.game = game;
        this.gameModel = gameModel;
        this.gameController = new GameController(this.gameModel);
        this.gameBuilder = new GameBuilder(gui, this.gameController, 10);
        this.gameViewer = new GameViewer(gui);
        this.gui = gui;
    }


    @Override
    public GameModel getStateModel() {
        return gameController.getGameModel();
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

                if(gameController.isGameFinished()){
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

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setGameBuilder(GameBuilder gameBuilder) {
        this.gameBuilder = gameBuilder;
    }
}
