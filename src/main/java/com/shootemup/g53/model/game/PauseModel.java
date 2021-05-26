package com.shootemup.g53.model.game;

import com.shootemup.g53.controller.state.PlayState;
import com.shootemup.g53.model.Model;
import com.shootemup.g53.model.element.Button;
import com.shootemup.g53.model.util.Position;

import java.util.ArrayList;
import java.util.List;

public class PauseModel extends Model {
    private List<Button> options;

    private int selected;
    private boolean isClosed = false;
    private PlayState playState;

    public PauseModel(PlayState playState){
        options = new ArrayList<>();
        options.add(new Button("RESUME", new Position(0 , 3),30,5,"#b52225"));
        options.add(new Button("RESTART", new Position(0, 15), 30,5,"#b52225"));
        options.add(new Button("EXIT", new Position(0, 23), 30,5,"#b52225"));
        options.get(1).deactivate();
        options.get(2).deactivate();
        selected = 0;
        this.playState = playState;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
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

    public PlayState getPlayState() {
        return playState;
    }

    public void setPlayState(PlayState playState) {
        this.playState = playState;
    }
}
