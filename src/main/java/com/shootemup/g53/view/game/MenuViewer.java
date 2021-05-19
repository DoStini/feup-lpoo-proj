package com.shootemup.g53.view.game;

import com.shootemup.g53.model.element.Button;
import com.shootemup.g53.model.game.MenuModel;
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
        for(Button button: model.getOptions()){
            buttonView.draw(gui,button);
        }

        gui.refresh();
    }
}
