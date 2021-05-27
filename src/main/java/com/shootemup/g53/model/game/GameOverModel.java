package com.shootemup.g53.model.game;

import com.shootemup.g53.model.Model;
import com.shootemup.g53.model.element.Button;
import com.shootemup.g53.model.util.Position;

import java.util.ArrayList;
import java.util.List;

public class GameOverModel extends Model {
    private final Button tryAgainBtn;
    private final Button exitBtn;
    private List<Button> options;
    private int selected;
    private boolean isClosed = false;

    public GameOverModel(){
        options = new ArrayList<>();
        this.tryAgainBtn = new Button("TRY AGAIN", new Position(0, 6),30,5,"#b52225");
        this.exitBtn = new Button("EXIT", new Position(0, 18), 30,5,"#b52225");
        setup();
    }

    private void setup() {
        exitBtn.deactivate();
        options.add(tryAgainBtn);
        options.add(exitBtn);
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

    public Button getExitBtn() {
        return exitBtn;
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
