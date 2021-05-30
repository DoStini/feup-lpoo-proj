package com.shootemup.g53.controller.state;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.controller.game.GameController;

import com.shootemup.g53.controller.infobar.InfoBarController;

import com.shootemup.g53.controller.gamebuilder.GameBuilder;
import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.infobar.InfoBarModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.game.GameViewer;
import com.shootemup.g53.view.infobar.InfoBarViewer;

public class PlayState extends State<GameModel> {
    private GameModel gameModel;
    private GameController gameController;
    private GameBuilder gameBuilder;
    private Viewer<GameModel> gameViewer;
    private Gui gui;
    private InfoBarController infoBarController;
    private InfoBarViewer infoBarViewer;
    private Thread timerThread;
    private int frameSleep = 50;
    int seconds = 0;

    long frame = 0;

    public PlayState(Game game, GameModel gameModel, Gui gui) {
        this.game = game;
        this.gameModel = gameModel;
        this.gui = gui;
        setup();
    }

    private void setup() {
        this.gameController = new GameController(this.gameModel);
        this.gameBuilder = new GameBuilder(gui, this.gameController, 10);
        this.gameViewer = new GameViewer(gui);
        this.infoBarViewer = new InfoBarViewer(this.gui, 10);

        Player player = getStateModel().getPlayer();
        this.infoBarController = new InfoBarController(gameController,
                new InfoBarModel(player.getHealth(),
                1,seconds, player.getHealth()));
        gameBuilder.getWaveFactory().getWaveCompletionController().addObserver(getInfoBarModel());
        startTimerThread();
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
        timerThread.start();

        
        try{
            while(true){
                gui.clear();
                Thread.sleep(frameSleep);
                gameBuilder.handle(frame);
                gameController.handleKeyPress(gui);
                gameController.handle(frame);
                infoBarController.handle(seconds);

                if(gameController.isGameFinished()){
                    game.changeState(new GameOverState(this.game,this.gui));
                    return;
                }else if(gameController.isPaused()){
                    gameController.unpause();
                    timerThread.interrupt();
                    startTimerThread();
                    game.changeState(new PauseState(game,gui,this));
                    return;
                }
                gameViewer.draw(gameModel);
                infoBarViewer.draw(infoBarController.getInfoBarModel());
                gui.refresh();
            }

        }
        catch (InterruptedException  e) {
            e.printStackTrace();
        }
    }

    private void startTimerThread() {
        timerThread = new Thread(() -> {
            try{
                while(!gameController.isGameFinished()){

                    Thread.sleep(frameSleep);
                    frame++;
                    if(frame % (1000/frameSleep) == 0) seconds++;
                }

            }catch(InterruptedException e){
            }
        });
    }

    public void setTimerThread(Thread timerThread) {
        this.timerThread = timerThread;
    }

    public void setFrameSleep(int frameSleep) {
        this.frameSleep = frameSleep;
    }

    public InfoBarViewer getInfoBarViewer() {
        return infoBarViewer;
    }

    protected void setInfoBarViewer(InfoBarViewer infoBarViewer) {
        this.infoBarViewer = infoBarViewer;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    protected void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    protected void setGameBuilder(GameBuilder gameBuilder) {
        this.gameBuilder = gameBuilder;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    protected void setGameViewer(Viewer<GameModel> gameViewer) {
        this.gameViewer = gameViewer;
    }

    public int getSeconds() {
        return seconds;
    }

    public long getFrame() {
        return frame;
    }

    protected void setInfoBarController(InfoBarController infoBarController) {
        this.infoBarController = infoBarController;
    }

    protected Thread getTimerThread() {
        return timerThread;
    }

    protected GameBuilder getGameBuilder() {
        return gameBuilder;
    }

    public Viewer<GameModel> getGameViewer() {
        return gameViewer;
    }

    public InfoBarController getInfoBarController() {
        return infoBarController;
    }
}
