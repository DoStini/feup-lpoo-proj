package com.shootemup.g53.controller.game;

import com.shootemup.g53.controller.Game;
import com.shootemup.g53.controller.GenericController;
import com.shootemup.g53.model.game.GameOverModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;

public class GameOverController extends GenericController {
    private GameOverModel gameOverModel;

    public GameOverController(GameOverModel gameModel) {
        this.gameOverModel = gameModel;
    }

    @Override
    public void handleKeyPress(Gui gui) {

    }
}
