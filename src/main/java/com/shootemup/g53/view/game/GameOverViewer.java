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
        gui.drawText("#ffffff",text, new Position(gui.getWidth() -  text.length()/6   , 2),"#00ff00");
        if(model.isPlayerWin()){
            text = "YOU HAVE WON! CONGRATULATIONS!";
            gui.drawText("#ffffff",text, new Position(gui.getWidth()  -  text.length()/6 ,4),"#00ff00");
        }else{
            text = "YOU HAVE LOST! ";
            gui.drawText("#ffffff",text, new Position(gui.getWidth()  -  text.length()/6 ,4),"#00ff00");
        }

        for(Button button: model.getOptions()){
            buttonView.draw(gui,button);
        }

        gui.refresh();
    }
}
