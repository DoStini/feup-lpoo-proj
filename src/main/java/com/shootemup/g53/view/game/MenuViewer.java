package com.shootemup.g53.view.game;

import com.shootemup.g53.model.element.Button;
import com.shootemup.g53.model.game.MenuModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.element.ButtonView;


public class MenuViewer extends Viewer<MenuModel> {

    public MenuViewer(Gui gui) {
        this.gui = gui;
    }
    private ButtonView buttonView = new ButtonView();
    @Override
    public void draw(MenuModel model) {
        gui.clear();
        String text = "WELCOME TO GUARDIANS OF THE GALAXY (LPOO VERSION)!";
        gui.drawText("#ff0000",text, new Position(gui.getWidth()/2  , 3),"#000000");
        for(Button button: model.getOptions()){
            buttonView.draw(gui,button);
        }

        gui.refresh();
    }
}
