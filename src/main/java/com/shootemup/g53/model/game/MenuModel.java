package com.shootemup.g53.model.game;

import com.shootemup.g53.controller.command.StartCommand;
import com.shootemup.g53.model.Model;
import com.shootemup.g53.model.element.Button;
import com.shootemup.g53.model.util.Position;

import java.util.ArrayList;
import java.util.List;

public class MenuModel extends Model {
    private List<Button> options;
    private int selected;
    private boolean isClosed = false;

    public MenuModel(){
        options = new ArrayList<>();
        options.add(new Button("PLAY", new Position(0, 3),30,5,"#b52225"));
        options.add(new Button("EXIT", new Position(0, 15), 30,5,"#b52225"));
        options.get(1).deactivate();

        selected = 0;
    }
    public int getSelected() {
        return selected % options.size();
    }

    public Button getSelectedButton(){
        return options.get(getSelected());

    }

    public void nextOption() {
        getSelectedButton().deactivate();
        selected++;
        getSelectedButton().activate();
    }

    public void previousOption(){
        getSelectedButton().deactivate();
        selected--;
        if(selected < 0)
            selected = options.size() - 1;
        getSelectedButton().activate();
    }


    public List<Button> getOptions() {
        return options;
    }


    public void setOptions(List<Button> options) {
        this.options = options;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
