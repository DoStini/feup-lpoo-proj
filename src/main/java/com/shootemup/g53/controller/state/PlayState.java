package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.game.GameController;

import com.shootemup.g53.controller.gameBuilder.GameBuilder;
import com.shootemup.g53.controller.input.InputController;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.game.GameOverModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.GameViewer;

import java.io.IOException;

public class PlayState extends State<GameModel> {
    private GameController gameController;
    private GameBuilder gameBuilder;
    private Viewer<GameModel> gameViewer;
    private Gui gui;
    long frame = 0;


    public PlayState(Game game, Gui gui, GameBuilder gameBuilder){
        this.game = game;
        this.gameBuilder = gameBuilder;
        this.gameViewer = new GameViewer(gui);
        this.gui = gui;
        this.gameController = this.gameBuilder.buildGame(5,3,100,50, gui);
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
                gameController.handleKeyPress(gui);
                gameController.handle(frame);
                gameViewer.draw(gameController.getGameModel());

                if(gameController.isGameFinished()){
                    game.changeState(new GameOverState(this.game,new GameOverModel(),this.gui));
                    return;
                }
            }

        }
        catch (InterruptedException  e) {
            e.printStackTrace();
        }
    }
}
