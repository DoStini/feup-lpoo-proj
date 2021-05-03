package com.shootemup.g53.view.game;

import com.shootemup.g53.model.game.GameModel;
import com.shootemup.g53.model.utility.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;

public class GameViewer extends Viewer<GameModel> {
    private Gui gui;
    public GameViewer(Gui gui) {
        this.gui = gui;

    }

    @Override
    public void draw(GameModel model) {
        //for now we only have enemies to draw
        gui.clear();
        /*
        EnemyView enemyView = new EnemyView(this.gui);
        for(Element ele: model.getEnemyList()){
            enemyView.draw(ele);
        }
        */

        gui.drawColor("#F3F3F3", new Position(50,50));
        gui.refresh();
    }
}
