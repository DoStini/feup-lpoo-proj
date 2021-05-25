package com.shootemup.g53.view.game;


import com.shootemup.g53.model.element.Button;
import com.shootemup.g53.model.game.GameOverModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.element.ButtonView;

public class GameOverViewer extends Viewer<GameOverModel> {
    private Gui gui;

    public GameOverViewer(Gui gui) {
        this.gui = gui;
    }
    private ButtonView buttonView = new ButtonView();
    @Override
    public void draw(GameOverModel model) {
        gui.clear();
        String text = "GAME OVER!";
        gui.drawText("#ff0000",text, new Position(gui.getWidth()/2  , 2),"#000000");

        text = "WOULD YOU CARE TO TRY AGAIN?";
        gui.drawText("#ff0000",text, new Position(gui.getWidth()/2  ,4),"#000000");

        for(Button button: model.getOptions()){
            buttonView.draw(gui,button);
        }

        gui.refresh();
    }
}
