package com.shootemup.g53.view.infobar;

import com.shootemup.g53.model.infobar.InfoBarModel;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.Viewer;
import com.shootemup.g53.view.shapes.CircleDrawer;
import com.shootemup.g53.view.shapes.Drawer;
import com.shootemup.g53.view.shapes.RectangleDrawer;

public class InfoBarViewer extends Viewer<InfoBarModel> {
    private int healthBarLength = 10;
    private int infoBarWidth;
    public InfoBarViewer(Gui gui, int infoBarWidth) {
        this.gui = gui;
        this.infoBarWidth = infoBarWidth;
    }

    @Override
    public void draw(InfoBarModel infoBarModel) {
        gui.clear();
        Drawer rectangleDrawer = new RectangleDrawer("#ff00ff", infoBarWidth,gui.getHeight());
        rectangleDrawer.draw(gui,new Position(gui.getWidth() - infoBarWidth,0));

        int XPosition = gui.getWidth() - infoBarWidth/2;
        int currY = 5;
        String text = "LIVES: ";
        gui.drawText("#00ff00","LIVES: ",new Position(XPosition - text.length()/5 , currY ),"#ffffff");
        currY = currY + 2;
        String currHP = String.valueOf(infoBarModel.getCurrentLives());
        String maxHP = String.valueOf(infoBarModel.getMaxLives());
        text = currHP + "/" + maxHP;
        gui.drawText("#00ff00",text,new Position(XPosition - text.length()/2,currY ),"#ffffff");
        currY = currY + 2;
        Drawer healthBarDrawer = new RectangleDrawer("#ff6961", 2, healthBarLength );
        Drawer healthBarCurrentDrawer = new RectangleDrawer("#00FF00", 2, (int) (healthBarLength * ( (double)(infoBarModel.getCurrentLives()) / infoBarModel.getMaxLives())) );
        currY = currY + 4;
        healthBarDrawer.draw(gui, new Position(XPosition - 2, currY));
        healthBarCurrentDrawer.draw(gui, new Position(XPosition - 2, currY));
        currY = currY + 7;
        text = "SCORE: ";
        gui.drawText("#00ff00",text,new Position(XPosition - text.length()/5,5 + currY ),"#ffffff");
        text = String.valueOf(infoBarModel.getScore());
        currY = currY + 2;
        gui.drawText("#00ff00",text,new Position(XPosition - text.length()/2,5 + currY ),"#ffffff");
        currY = currY + 3;
        text = "WAVE:";
        gui.drawText("#00ff00",text,new Position(XPosition - text.length()/5,5 + currY ),"#ffffff");
        text = String.valueOf(infoBarModel.getCurrentWave());
        currY = currY + 2;
        gui.drawText("#00ff00",text,new Position(XPosition - text.length()/2,5 + currY ),"#ffffff");
        currY = currY + 3;
        text = "TIME:";
        gui.drawText("#00ff00",text,new Position(XPosition - text.length()/5,5 + currY ),"#ffffff");
        currY = currY + 2;
        text = String.valueOf(infoBarModel.getTime());
        gui.drawText("#00ff00",text,new Position(XPosition - text.length()/2,5 + currY ),"#ffffff");
    }
}
