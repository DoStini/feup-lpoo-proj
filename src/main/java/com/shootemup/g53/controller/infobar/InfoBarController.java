package com.shootemup.g53.controller.infobar;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.infobar.InfoBarModel;

public class InfoBarController {
    private InfoBarModel infoBarModel;

    public InfoBarController(GameController gameController, InfoBarModel infoBarModel){
        this.infoBarModel = infoBarModel;
        PlayerController playerController = gameController.getPlayerController();
        playerController.getLifeController().addObserver(infoBarModel);
        gameController.getPlayerController().getScoreController().addObserver(infoBarModel);
        gameController.getPlayerController().getEssenceController().addObserver(infoBarModel);
        infoBarModel.setEssenceShieldCost(gameController.getPlayerController().getPowerupController().getShieldEssenceCost());
        gameController.getPlayerController().getPowerupController().getEssenceController().addObserver(infoBarModel);
        gameController.getPlayerController().getPowerupController().getLifeController().addObserver(infoBarModel);
        infoBarModel.setEssenceHealthCost(gameController.getPlayerController().getPowerupController().getHealthCost());
        gameController.getScoreController().addObserver(infoBarModel);

    }
    public void handle(int frame) {
        infoBarModel.setTime(frame);
    }

    public InfoBarModel getInfoBarModel() {
        return infoBarModel;
    }
}
