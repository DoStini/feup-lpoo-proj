package com.shootemup.g53.controller.infobar;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.infobar.InfoBarModel;

public class InfoBarController {
    private InfoBarModel infoBarModel;

    public InfoBarController(GameController gameController, InfoBarModel infoBarModel){
        this.infoBarModel = infoBarModel;
        gameController.getPlayerController().getLifeController().addObserver(infoBarModel);
        gameController.getPlayerController().getScoreController().addObserver(infoBarModel);
        gameController.getScoreController().addObserver(infoBarModel);
        gameController.getWaveCompletionController().addObserver(infoBarModel);
    }
    public void handle(int frame) {
        infoBarModel.setTime(frame);
    }

    public InfoBarModel getInfoBarModel() {
        return infoBarModel;
    }
}
