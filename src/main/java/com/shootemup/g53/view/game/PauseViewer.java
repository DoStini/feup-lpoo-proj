package com.shootemup.g53.view.game;

import com.shootemup.g53.model.element.Button;
import com.shootemup.g53.model.game.PauseModel;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.element.ButtonView;

public class PauseViewer extends Viewer<PauseModel> {

    public PauseViewer(Gui gui) {
        this.gui = gui;
    }
    private ButtonView buttonView = new ButtonView();
    @Override
    public void draw(PauseModel model) {
        gui.clear();
        model.getPlayState().getStateView().draw(model.getPlayState().getStateModel());
        for(Button button: model.getOptions()){
            buttonView.draw(gui,button);
        }
        gui.refresh();
    }
}
