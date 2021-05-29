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
    private int currY  = 5;
    private int infoBarWidth;
    private String infoBarBackground = "#FF1033";
    private int XPosition;
    private String textColor =  "#FFD892";
    public InfoBarViewer(Gui gui, int infoBarWidth) {
        this.gui = gui;
        this.infoBarWidth = infoBarWidth;
        XPosition = gui.getWidth() - infoBarWidth/2;
    }

    public void drawLives(Gui gui, InfoBarModel infoBarModel){
        String text = "LIVES: ";
        gui.drawText(textColor,"LIVES: ",new Position(XPosition - text.length()/5 , currY ),infoBarBackground);
        currY = currY + 2;
        String currHP = String.valueOf(infoBarModel.getCurrentLives());
        String maxHP = String.valueOf(infoBarModel.getMaxLives());
        text = currHP + "/" + maxHP;
        gui.drawText(textColor,text,new Position(XPosition - text.length()/2,currY ),infoBarBackground);
        currY = currY + 2;
        Drawer healthBarDrawer = new RectangleDrawer("#ff6961", 2, healthBarLength );
        Drawer healthBarCurrentDrawer = new RectangleDrawer("#00FF00", 2, (int) (healthBarLength * ( (double)(infoBarModel.getCurrentLives()) / infoBarModel.getMaxLives())) );
        currY = currY + 4;
        healthBarDrawer.draw(gui, new Position(XPosition - 2, currY));
        healthBarCurrentDrawer.draw(gui, new Position(XPosition - 2, currY));
    }

    public void drawScore(Gui gui, InfoBarModel infoBarModel){
        currY = currY + 7;
        String text = "SCORE: ";
        gui.drawText(textColor,text,new Position(XPosition - text.length()/5,5 + currY ),infoBarBackground);
        text = String.valueOf(infoBarModel.getScore());
        currY = currY + 2;
        gui.drawText(textColor,text,new Position(XPosition ,5 + currY ),infoBarBackground);
        currY = currY + 3;
    }

    public void drawWave(Gui gui, InfoBarModel infoBarModel){
        String text = "WAVE:";
        gui.drawText(textColor,text,new Position(XPosition - text.length()/5,5 + currY ),infoBarBackground);
        text = String.valueOf(infoBarModel.getCurrentWave());
        currY = currY + 2;
        gui.drawText(textColor,text,new Position(XPosition ,5 + currY ),infoBarBackground);
        currY = currY + 3;
    }
    public void drawTime(Gui gui, InfoBarModel infoBarModel){
        String text = "TIME:";
        gui.drawText(textColor,text,new Position(XPosition - text.length()/5,5 + currY ),infoBarBackground);
        currY = currY + 2;
        text = String.valueOf(infoBarModel.getTime());
        gui.drawText(textColor,text,new Position(XPosition ,5 + currY ),infoBarBackground);
        currY = currY + 3;
    }

    public void drawEssence(Gui gui, InfoBarModel infoBarModel){
        String text = "ESSENCE:";
        gui.drawText(textColor,text,new Position(XPosition - text.length()/6,5 + currY ),infoBarBackground);
        currY = currY + 2;
        text = String.valueOf(infoBarModel.getEssence());
        gui.drawText(textColor,text,new Position(XPosition ,5 + currY ),infoBarBackground);
    }

    public void drawPowerups(Gui gui, InfoBarModel infoBarModel){
        String text;
        if(infoBarModel.getEssence() >= infoBarModel.getEssenceShieldCost()){
            currY = currY + 3;
            text = "SHIELD:";
            gui.drawText(textColor,text,new Position(XPosition - text.length()/5,5 + currY ),infoBarBackground);
            currY = currY + 2;
            text = "(E) " + infoBarModel.getEssenceShieldCost();
            gui.drawText(textColor,text,new Position(XPosition - text.length()/5,5 + currY ),infoBarBackground);
        }

        if(infoBarModel.getEssence() >= infoBarModel.getEssenceHealthCost()){
            currY = currY + 3;
            text = "HEALTH:";
            gui.drawText(textColor,text,new Position(XPosition - text.length()/5,5 + currY ),infoBarBackground);
            currY = currY + 2;
            text = "(R) "+ infoBarModel.getEssenceHealthCost();
            gui.drawText(textColor,text,new Position(XPosition - text.length()/5,5 + currY ),infoBarBackground);
        }
    }

    public void drawInfoBarBackground(Gui gui){
        Drawer rectangleDrawer = new RectangleDrawer(infoBarBackground, infoBarWidth,gui.getHeight());
        rectangleDrawer.draw(gui,new Position(gui.getWidth() - infoBarWidth,0));
    }

    @Override
    public void draw(InfoBarModel infoBarModel) {
        currY = 5;
        drawInfoBarBackground(gui);
        drawLives(gui,infoBarModel);
        drawScore(gui, infoBarModel);
        drawWave(gui, infoBarModel);
        drawTime(gui,infoBarModel);
        drawEssence(gui, infoBarModel);
        drawPowerups(gui, infoBarModel);
    }
}
